package wannabit.io.cosmostaion.chain.fetcher

import com.google.gson.JsonObject
import com.trustwallet.walletconnect.extensions.toHex
import org.json.JSONObject
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.sign.AptosJs
import xyz.mcxross.kaptos.Aptos
import xyz.mcxross.kaptos.generated.getaccountcoinsdata.current_fungible_asset_balances
import xyz.mcxross.kaptos.model.AccountAddress
import xyz.mcxross.kaptos.model.AptosConfig
import xyz.mcxross.kaptos.model.AptosSettings
import java.math.BigDecimal
import java.math.RoundingMode

class AptosFetcher(private val chain: BaseChain) {

    var aptosAssetBalance: MutableList<current_fungible_asset_balances>? = mutableListOf()

    fun aptosClient(): Aptos {
        val config = AptosConfig(
            AptosSettings(
                fullNode = getApi(),
                indexer = getGraphQL()
            )
        )
        return Aptos(config)
    }

    fun aptosAccount(): AccountAddress {
        return AccountAddress.fromString(chain.mainAddress)
    }

    fun allAssetValue(isUsd: Boolean? = false): BigDecimal {
        var sum = BigDecimal.ZERO
        if (aptosAssetBalance?.isNotEmpty() == true) {
            aptosAssetBalance?.forEach { asset ->
                BaseData.getAsset(chain.apiName, asset.asset_type)?.let {
                    sum += aptosBalanceValue(asset.asset_type, isUsd)
                }
            }
        }
        return sum
    }

    fun aptosBalanceAmount(assetType: String): BigDecimal {
        if (aptosAssetBalance?.isNotEmpty() == true) {
            return aptosAssetBalance?.firstOrNull { it.asset_type == assetType }?.amount?.toBigDecimal()
                ?: BigDecimal.ZERO
        }
        return BigDecimal.ZERO
    }

    fun aptosBalanceValue(assetType: String, isUsd: Boolean? = false): BigDecimal {
        if (aptosBalanceAmount(assetType) <= BigDecimal.ZERO) return BigDecimal.ZERO
        BaseData.getAsset(chain.apiName, assetType)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            return price.multiply(aptosBalanceAmount(assetType)).movePointLeft(asset.decimals ?: 8)
                .setScale(6, RoundingMode.DOWN)
        }
        return BigDecimal.ZERO
    }

    fun getApi(): String {
        val endpoint = Prefs.getLcdEndpoint(chain)
        return endpoint.ifEmpty {
            chain.apiUrl
        }
    }

    fun getGraphQL(): String {
        val endpoint = Prefs.getEvmRpcEndpoint(chain)
        return if (endpoint?.isNotEmpty() == true) {
            endpoint
        } else {
            chain.mainUrl
        }
    }

    fun signAptosMessage(aptosJs: AptosJs, param: JsonObject, dAppUrl: String?): String? {
        val privateKey = chain.privateKey?.toHex()
        val signAptosMessageFunction =
            """function signAptosMessageFunction() {
                const message = signAptosMessage('${privateKey}', $param, '${chain.mainAddress}', '${dAppUrl}');
                return message;
                }""".trimMargin()
        aptosJs.mergeFunction(signAptosMessageFunction)
        return aptosJs.executeFunction("signAptosMessageFunction()")
    }

    fun originalTx(aptosJs: AptosJs, serializedTxHex: String): String? {
        val getOriginalTxFunction =
            """function getOriginalTxFunction() {
                const originalTx = getOriginalTx('${serializedTxHex}');
                return originalTx;
                }""".trimMargin()
        aptosJs.mergeFunction(getOriginalTxFunction)
        return aptosJs.executeFunction("getOriginalTxFunction()")
    }

    fun signAptosTx(aptosJs: AptosJs, serializedTxHex: String): String? {
        val privateKey = chain.privateKey?.toHex()
        val signAptosTxFunction =
            """function signAptosTxFunction() {
                const tx = signAptosTx('${serializedTxHex}', '${privateKey}', '${getApi()}');
                return tx;
                }""".trimMargin()
        aptosJs.mergeFunction(signAptosTxFunction)
        return aptosJs.executeFunction("signAptosTxFunction()")
    }
}
