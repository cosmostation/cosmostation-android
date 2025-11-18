package wannabit.io.cosmostaion.chain.fetcher

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import okhttp3.Response
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.jsonRpcResponse
import wannabit.io.cosmostaion.common.toHex
import wannabit.io.cosmostaion.data.model.req.JsonRpcRequest
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.sign.SolanaJs
import java.math.BigDecimal
import java.math.RoundingMode

class SolanaFetcher(private val chain: BaseChain) {

    var solanaAccountInfo = JsonObject()
    val solanaTokenInfo: MutableList<JsonObject> = mutableListOf()

    var splTokens = mutableListOf<Token>()

    fun solanaBalanceAmount(): BigDecimal {
        if (solanaAccountInfo.has("value")) {
            if (!solanaAccountInfo["value"].isJsonNull) {
                return solanaAccountInfo["value"].asJsonObject["lamports"].asLong.toBigDecimal()
            }
            return BigDecimal.ZERO
        }
        return BigDecimal.ZERO
    }

    fun solanaBalanceValue(isUsd: Boolean? = false): BigDecimal {
        val amount = solanaBalanceAmount()
        if (amount == BigDecimal.ZERO) return BigDecimal.ZERO
        BaseData.getAsset(chain.apiName, chain.coinSymbol)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            return price.multiply(amount).movePointLeft(asset.decimals ?: 6)
                .setScale(6, RoundingMode.DOWN)
        }
        return BigDecimal.ZERO
    }

    fun splTokenValue(address: String?, isUsd: Boolean? = false): BigDecimal {
        splTokens.firstOrNull { it.address == address }?.let { splToken ->
            val price = BaseData.getPrice(splToken.coinGeckoId, isUsd)
            return price.multiply(splToken.amount?.toBigDecimal())
                .movePointLeft(splToken.decimals).setScale(6, RoundingMode.DOWN)
        } ?: run {
            return BigDecimal.ZERO
        }
    }

    fun allSplTokenValue(isUsd: Boolean? = false): BigDecimal {
        var result = BigDecimal.ZERO

        solanaTokenInfo.forEach { splToken ->
            BaseData.getToken(chain, chain.apiName, splToken["mint"].asString)?.let { token ->
                val price = BaseData.getPrice(token.coinGeckoId, isUsd)
                val value =
                    price.multiply(token.amount?.toBigDecimal()).movePointLeft(token.decimals)
                        .setScale(6, RoundingMode.DOWN)
                result = result.add(value)
            }
        }
        return result
    }

    fun solanaRpc(): String {
        val endpoint = Prefs.getEvmRpcEndpoint(chain)
        return if (endpoint?.isNotEmpty() == true) {
            endpoint
        } else {
            chain.mainUrl
        }
    }

    fun parseMessage(solanaJs: SolanaJs, message: String): String? {
        val parseMessageFunction =
            """function parseMessageFunction() {
                const parse = parseMessage('${message}');
                return parse;
                }""".trimMargin()
        solanaJs.mergeFunction(parseMessageFunction)
        return solanaJs.executeFunction("parseMessageFunction()")
    }

    fun signMessage(solanaJs: SolanaJs, message: String): JsonObject {
        val privateKey = chain.privateKey?.toHex()
        val signMessageFunction =
            """function signMessageFunction() {
                const encodedMessage = signMessage('${message}', '${privateKey}');
                return encodedMessage;
                }""".trimMargin()
        solanaJs.mergeFunction(signMessageFunction)
        val signMessage =
            SolanaJs.executeFunction("signMessageFunction()")
        return Gson().fromJson(
            signMessage, JsonObject::class.java
        )
    }

    fun parseInstructionsFromTx(solanaJs: SolanaJs, serializedTx: String): String? {
        val parseInstructionsFromTxFunction =
            """function parseInstructionsFromTxFunction() {
                const parse = parseInstructionsFromTx('${serializedTx}');
                return parse;
                }""".trimMargin()
        solanaJs.mergeFunction(parseInstructionsFromTxFunction)
        return solanaJs.executeFunction("parseInstructionsFromTxFunction()")
    }

    fun accountsToTrack(solanaJs: SolanaJs, serializedTx: String): String? {
        val getAccountsToTrackFunction =
            """function getAccountsToTrackFunction() {
                const accounts = getAccountsToTrack('${serializedTx}', '${chain.mainAddress}');
                return accounts;
                }""".trimMargin()
        solanaJs.mergeFunction(getAccountsToTrackFunction)
        return solanaJs.executeFunction("getAccountsToTrackFunction()")
    }

    fun serializedTxMessageFromTx(solanaJs: SolanaJs, serializedTx: String): String? {
        val getSerializedTxMessageFromTxFunction =
            """function getSerializedTxMessageFromTxFunction() {
                const serializedTxMessage = getSerializedTxMessageFromTx('${serializedTx}');
                return serializedTxMessage;
                }""".trimMargin()
        solanaJs.mergeFunction(getSerializedTxMessageFromTxFunction)
        return solanaJs.executeFunction("getSerializedTxMessageFromTxFunction()")
    }

    fun baseFee(serializedTxMessage: String?): Long {
        val feeParams = listOf(
            serializedTxMessage, mapOf("commitment" to "processed")
        )
        val feeForMessageRequest =
            JsonRpcRequest(method = "getFeeForMessage", params = feeParams)
        val feeForMessageResponse = jsonRpcResponse(
            solanaRpc(), feeForMessageRequest
        )
        val feeForMessageJsonObject = Gson().fromJson(
            feeForMessageResponse.body?.string(), JsonObject::class.java
        )
        return feeForMessageJsonObject["result"].asJsonObject["value"].asLong
    }

    fun simulateValue(serializedTx: String, accountList: List<String>): JsonObject {
        val simulateParams = listOf(
            serializedTx, mapOf(
                "commitment" to "confirmed",
                "encoding" to "base64",
                "replaceRecentBlockhash" to true,
                "accounts" to mapOf(
                    "encoding" to "base64",
                    "addresses" to accountList
                )
            )
        )

        val simulationRequest =
            JsonRpcRequest(
                method = "simulateTransaction",
                params = simulateParams
            )
        val simulationResponse = jsonRpcResponse(
            solanaRpc(), simulationRequest
        )
        val simulationJsonObject = Gson().fromJson(
            simulationResponse.body?.string(), JsonObject::class.java
        )
        return simulationJsonObject["result"].asJsonObject["value"].asJsonObject
    }

    fun multiAccountsValue(accountList: List<String>): JsonArray {
        val multiAccountsParams = listOf(
            accountList,
            mapOf("encoding" to "base64", "commitment" to "finalized")
        )
        val multiAccountsRequest =
            JsonRpcRequest(
                method = "getMultipleAccounts",
                params = multiAccountsParams
            )
        val multiAccountsResponse = jsonRpcResponse(
            solanaRpc(),
            multiAccountsRequest
        )
        val multiAccountsJsonObject = Gson().fromJson(
            multiAccountsResponse.body?.string(), JsonObject::class.java
        )
        return multiAccountsJsonObject["result"].asJsonObject["value"].asJsonArray
    }

    fun analyzeTokenChanges(
        solanaJs: SolanaJs,
        accounts: String?,
        dpMultiAccounts: String,
        simulateValue: JsonObject
    ): String? {
        val analyzeTokenChangesFunction =
            """function analyzeTokenChangesFunction() {
                const changes = analyzeTokenChanges('${chain.mainAddress}', `$accounts`, `$dpMultiAccounts`, `$simulateValue`);
                return changes;
                }""".trimMargin()
        solanaJs.mergeFunction(analyzeTokenChangesFunction)
        return solanaJs.executeFunction("analyzeTokenChangesFunction()")
    }

    fun signTransaction(solanaJs: SolanaJs, serializedTx: String): String? {
        val privateKey = chain.privateKey?.toHex()
        val signTransactionFunction = """function signTransactionFunction() {
            const txHex = signTransaction('${serializedTx}', '${privateKey}');
            return txHex;
            }""".trimMargin()
        solanaJs.mergeFunction(signTransactionFunction)
        return SolanaJs.executeFunction("signTransactionFunction()")
    }

    fun sendTransaction(solanaJs: SolanaJs, param: JsonObject): Response {
        val serializedTx = param["serializedTx"]?.asString ?: ""
        val skipPreflight = param["skipPreflight"]?.asBoolean ?: false
        val preflightCommitment = param["preflightCommitment"].asString ?: "finalized"
        val maxRetries = param["maxRetries"]?.asLong ?: 0
        val minContextSlot = param["minContextSlot"]?.asLong ?: 0

        val txHex = signTransaction(solanaJs, serializedTx)

        val sendParams = listOf(
            txHex,
            mapOf(
                "encoding" to "base64",
                "skipPreflight" to skipPreflight,
                "preflightCommitment" to preflightCommitment,
                "maxRetries" to maxRetries,
                "minContextSlot" to minContextSlot
            )
        )
        val sendTransactionRequest =
            JsonRpcRequest(method = "sendTransaction", params = sendParams)
        return jsonRpcResponse(
            solanaRpc(), sendTransactionRequest
        )
    }
}