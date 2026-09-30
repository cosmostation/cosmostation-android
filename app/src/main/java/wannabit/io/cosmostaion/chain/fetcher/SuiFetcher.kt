package wannabit.io.cosmostaion.chain.fetcher

import com.google.gson.JsonObject
import com.google.protobuf.Value
import com.sui.rpc.v2.EpochProto
import com.sui.rpc.v2.LedgerServiceGrpc
import com.sui.rpc.v2.LedgerServiceProto
import com.sui.rpc.v2.ObjectProto
import com.sui.rpc.v2.StateServiceProto
import com.sui.rpc.v2.SystemStateProto
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.SUI_FEE_DEFAULT
import wannabit.io.cosmostaion.chain.majorClass.SUI_FEE_SEND
import wannabit.io.cosmostaion.chain.majorClass.SUI_FEE_STAKE
import wannabit.io.cosmostaion.chain.majorClass.SUI_FEE_UNSTAKE
import wannabit.io.cosmostaion.chain.majorClass.SUI_MAIN_DENOM
import wannabit.io.cosmostaion.chain.majorClass.SUI_TYPE_COIN
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.sign.SuiJS
import wannabit.io.cosmostaion.ui.tx.genTx.SuiTxType
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.TimeUnit

class SuiFetcher(private val chain: BaseChain) {

    var suiSystem: EpochProto.Epoch? = null
    var suiBalances: MutableList<Pair<String?, BigDecimal?>> = mutableListOf()
    val suiObjects: MutableList<ObjectProto.Object> = mutableListOf()
    var suiStakedList: MutableList<StakeReward> = mutableListOf()
    var suiValidators: MutableList<SystemStateProto.Validator> = mutableListOf()
    val suiCoinMeta: MutableMap<String, StateServiceProto.CoinMetadata> = mutableMapOf()
    val suiHistory: MutableList<JsonObject> = mutableListOf()
    var suiApys: MutableList<JsonObject> = mutableListOf()

    fun allAssetValue(isUsd: Boolean? = false): BigDecimal {
        return suiBalanceValueSum(isUsd).add(suiStakedValue(isUsd))
    }

    fun allSuiAmount(): BigDecimal? {
        return suiBalanceAmount(SUI_MAIN_DENOM)?.add(stakedAmount()) ?: BigDecimal.ZERO
    }

    fun allSuiValue(isUsd: Boolean? = false): BigDecimal {
        val amount = allSuiAmount()
        if (amount == BigDecimal.ZERO) return BigDecimal.ZERO
        BaseData.getAsset(chain.apiName, SUI_MAIN_DENOM)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            return price.multiply(amount).movePointLeft(asset.decimals ?: 6)
                .setScale(6, RoundingMode.DOWN)
        }
        return BigDecimal.ZERO
    }

    fun suiBalanceAmount(coinType: String): BigDecimal? {
        suiBalances.firstOrNull { it.first == coinType }?.let { suiCoin ->
            return suiCoin.second
        }
        return BigDecimal.ZERO
    }

    fun suiBalanceValue(coinType: String, isUsd: Boolean? = false): BigDecimal {
        val amount = suiBalanceAmount(coinType)
        if (amount == BigDecimal.ZERO) return BigDecimal.ZERO
        BaseData.getAsset(chain.apiName, coinType)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            return price.multiply(amount).movePointLeft(asset.decimals ?: 6)
                .setScale(6, RoundingMode.DOWN)
        }
        return BigDecimal.ZERO
    }

    private fun suiBalanceValueSum(isUsd: Boolean? = false): BigDecimal {
        var sum = BigDecimal.ZERO
        if (suiBalances.isNotEmpty()) {
            synchronized(suiBalances) {
                val balanceList = CopyOnWriteArrayList(suiBalances)
                val iterator = balanceList.iterator()
                while (iterator.hasNext()) {
                    val balance = iterator.next()
                    balance.first?.let {
                        sum = sum.add(suiBalanceValue(it, isUsd))
                    }
                }
            }
        }
        return sum
    }

    fun stakedAmount(): BigDecimal {
        return principalAmount().add(estimateRewardAmount())
    }

    private fun suiStakedValue(isUsd: Boolean? = false): BigDecimal {
        val amount = stakedAmount()
        if (amount == BigDecimal.ZERO) {
            return BigDecimal.ZERO
        }
        BaseData.getAsset(chain.apiName, SUI_MAIN_DENOM)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            return price.multiply(amount).movePointLeft(asset.decimals ?: 6)
                .setScale(6, RoundingMode.DOWN)
        }
        return BigDecimal.ZERO
    }

    fun principalAmount(): BigDecimal {
        return suiStakedList.sumOf { it.principal }.toBigDecimal()
    }

    fun estimateRewardAmount(): BigDecimal {
        return suiStakedList.sumOf { it.estimatedReward }.toBigDecimal()
    }

    fun suiAllNfts(): MutableList<ObjectProto.Object> {
        return suiObjects.filter { suiObject ->
            val type = suiObject.objectType.lowercase()
            !type.contains("stakedsui") && !type.contains("coin")
        }.toMutableList()
    }

    fun suiBaseFee(txType: SuiTxType): BigDecimal {
        when (txType) {
            SuiTxType.SUI_SEND_COIN, SuiTxType.SUI_SEND_NFT -> {
                return SUI_FEE_SEND.toBigDecimal()
            }

            SuiTxType.SUI_STAKE -> {
                return SUI_FEE_STAKE.toBigDecimal()
            }

            SuiTxType.SUI_UNSTAKE -> {
                return SUI_FEE_UNSTAKE.toBigDecimal()
            }

            else -> return SUI_FEE_DEFAULT.toBigDecimal()
        }
    }

    fun getSuiGrpc(): Pair<String, Int> {
        val endPoint = Prefs.getGrpcEndpoint(chain)
        return if (endPoint.isNotEmpty() && endPoint.split(":").count() == 2) {
            val host = endPoint.split(":")[0].trim()
            val port = endPoint.split(":").getOrNull(1)?.trim()?.toIntOrNull() ?: 443
            Pair(host, port)

        } else {
            if (chain.grpcHost.split(":").count() == 2) {
                val host = chain.grpcHost.split(":")[0].trim()
                val port = chain.grpcHost.split(":").getOrNull(1)?.trim()?.toIntOrNull() ?: 443
                Pair(host, port)
            } else {
                Pair(chain.grpcHost, chain.grpcPort)
            }
        }
    }

    fun getChannel(): ManagedChannel? {
        return if (getSuiGrpc().first.isEmpty()) {
            null
        } else {
            ManagedChannelBuilder.forAddress(
                getSuiGrpc().first, getSuiGrpc().second
            ).useTransportSecurity().build()
        }
    }

    fun suiRpc(): String {
        val endpoint = Prefs.getEvmRpcEndpoint(chain)
        return if (endpoint?.isNotEmpty() == true) {
            endpoint
        } else {
            chain.mainUrl
        }
    }

    fun buildPoolMap(systemState: SystemStateProto.SystemState): Map<String, PoolInfo> {
        return systemState.validators.activeValidatorsList.associate { activeValidator ->
            activeValidator.stakingPool.id to PoolInfo(
                activeValidator.address,
                activeValidator.stakingPool.exchangeRates.id
            )
        }
    }

    fun rate(suiAmount: Long, poolTokenAmount: Long): Double =
        if (suiAmount == 0L) 1.0 else poolTokenAmount.toDouble() / suiAmount.toDouble()

    private fun referenceGasPrice(): String {
        return suiSystem?.systemState?.referenceGasPrice?.toString() ?: "1000"
    }

    private fun suixCoins(): ObjectProto.Object? {
        return suiObjects.firstOrNull { it.objectType.suiCoinType() == SUI_MAIN_DENOM }
    }

    private val suiSuspiciousPattern = Regex(
        "(https?://|www\\.|[a-zA-Z0-9-]+\\.(com|io|net|org|xyz|app|co|me|gg|link|finance))",
        RegexOption.IGNORE_CASE
    )

    fun isSuiSuspiciousCoin(metadata: StateServiceProto.CoinMetadata?): Boolean {
        if (metadata == null) return false
        return suiSuspiciousPattern.containsMatchIn(metadata.name) ||
                suiSuspiciousPattern.containsMatchIn(metadata.description)
    }

    fun buildSendRequest(
        suiJs: SuiJS,
        amount: String,
        sender: String,
        recipient: String,
        coins: List<ObjectProto.Object>?,
        coinType: String,
        gasBudget: String,
        gasCoin: ObjectProto.Object
    ): String? {
        val gasPrice = referenceGasPrice()

        val coinsJs = coins?.takeIf { it.isNotEmpty() }?.joinToString(",", "[", "]") {
            """{"coinType":"${it.objectType.suiCoinType()}","coinObjectId":"${it.objectId}","version":"${it.version}","digest":"${it.digest}"}"""
        }

        val buildSendSuiRequestFunction =
            """function buildSendSuiRequestFunction() {
        const txHex = buildSendSuiRequest('${amount}', '${sender}', '${recipient}', $coinsJs, '${coinType}', 
        '${gasPrice}', '${gasBudget}', '${gasCoin.objectId}', '${gasCoin.version}', '${gasCoin.digest}');
        return txHex;
        }""".trimMargin()
        suiJs.mergeFunction(buildSendSuiRequestFunction)
        return suiJs.executeFunction("buildSendSuiRequestFunction()")
    }

    fun buildStakingRequest(suiJs: SuiJS, amount: String, validatorAddress: String?): String? {
        val gasPrice = referenceGasPrice()
        val coinData = suixCoins()

        return if (coinData != null) {
            val gasBudget = suiBaseFee(SuiTxType.SUI_STAKE)
            val coinObjectId = coinData.objectId
            val version = coinData.version
            val digest = coinData.digest

            val buildStakingRequestFunction =
                """function buildStakingRequestFunction() {
                const txHex = buildStakingRequest('${amount}', '${validatorAddress}', '${chain.mainAddress}', 
                '${gasPrice}', '${gasBudget}', '${coinObjectId}', '${version}', '${digest}');
                return txHex;
                }""".trimMargin()
            suiJs.mergeFunction(buildStakingRequestFunction)
            return suiJs.executeFunction("buildStakingRequestFunction()")

        } else {
            ""
        }
    }

    private fun suiObject(objectId: String): ObjectProto.Object? {
        return try {
            val stub = LedgerServiceGrpc.newBlockingStub(getChannel())
                .withDeadlineAfter(8, TimeUnit.SECONDS)
            val request = LedgerServiceProto.GetObjectRequest.newBuilder()
                .setObjectId(objectId)
                .build()

            stub.getObject(request).getObject()
        } catch (e: Exception) {
            null
        }
    }

    fun buildUnstakingRequest(suiJs: SuiJS, objectId: String): String? {
        val gasPrice = referenceGasPrice()
        val coinData = suixCoins()

        return if (coinData != null) {
            val gasBudget = suiBaseFee(SuiTxType.SUI_UNSTAKE)
            val coinObjectId = coinData.objectId
            val version = coinData.version
            val digest = coinData.digest

            val stakedObject = suiObject(objectId)
            val stakedObjectVersion = stakedObject?.version.toString()
            val stakedObjectDigest = stakedObject?.digest ?: ""

            val buildUnstakingRequestFunction =
                """function buildUnstakingRequestFunction() {
                const txHex = buildUnstakingRequest('${chain.mainAddress}', '${gasPrice}', '${gasBudget}', '${coinObjectId}', '${version}', '${digest}',
                '${objectId}', '${stakedObjectVersion}', '${stakedObjectDigest}');
                return txHex;
                }""".trimMargin()
            suiJs.mergeFunction(buildUnstakingRequestFunction)
            return suiJs.executeFunction("buildUnstakingRequestFunction()")

        } else {
            ""
        }
    }

    fun buildSendNFTRequest(
        suiJs: SuiJS,
        sender: String,
        recipient: String,
        nftObject: ObjectProto.Object,
        gasBudget: String,
        gasCoin: ObjectProto.Object
    ): String? {
        val gasPrice = referenceGasPrice()

        val buildSendSuiNFTRequestFunction =
            """function buildSendSuiNFTRequestFunction() {
        const txHex = buildSendSuiNFTRequest('${sender}', '${recipient}', '${nftObject.objectId}', '${nftObject.version}', '${nftObject.digest}',
        '${gasPrice}', '${gasBudget}', '${gasCoin.objectId}', '${gasCoin.version}', '${gasCoin.digest}');
        return txHex;
        }""".trimMargin()
        suiJs.mergeFunction(buildSendSuiNFTRequestFunction)
        return suiJs.executeFunction("buildSendSuiNFTRequestFunction()")
    }
}

data class PoolInfo(val validatorAddress: String, val exchangeRatesTableId: String)
data class StakeReward(
    val objectId: String,
    val poolId: String,
    val validatorAddress: String,
    val principal: Long,
    val activationEpoch: Long,
    val isPending: Boolean,
    val estimatedReward: Long
)

fun String.suiNormalizeType(): String {
    return Regex("0x0*([0-9a-fA-F]+)(?=::)").replace(this) { "0x${it.groupValues[1]}" }
}

fun String.suiIsCoinType(): Boolean {
    return this.suiNormalizeType().startsWith(SUI_TYPE_COIN)
}

fun String?.suiCoinType(): String? {
    val normalized = this?.suiNormalizeType()
    if (normalized?.suiIsCoinType() == false) {
        return null
    }
    val regex = Regex("<(.+)>")
    normalized?.let {
        val matchResult = regex.find(it)
        return matchResult?.groups?.get(1)?.value
    }
    return null
}

fun String?.suiCoinSymbol(): String? {
    val regex = Regex("::([a-zA-Z0-9_]+)(?:<.*>)?$")
    this?.let {
        val matchResult = regex.find(it)
        return matchResult?.groups?.get(1)?.value
    }
    return "Unknown"
}

fun JsonObject?.assetImg(): String {
    return try {
        this?.get("iconUrl")?.asString ?: ""
    } catch (e: Exception) {
        ""
    }
}

fun Value.getStringField(key: String): String? = structValue.fieldsMap[key]?.stringValue

fun Value.suiNftUrl(): String? {
    val raw = getStringField("image_url") ?: return null
    return if (raw.startsWith("ipfs://")) {
        raw.replace("ipfs://", "https://ipfs.io/ipfs/")
    } else {
        raw
    }
}

fun JsonObject.moveRawNftUrlString(): String? {
    return try {
        this["display"].asJsonObject["data"].asJsonObject["image_url"].asString
    } catch (e: Exception) {
        null
    }
}

fun JsonObject.moveNftUrl(): String? {
    var urlString: String?
    moveRawNftUrlString()?.let { url ->
        if (url.startsWith("ipfs://")) {
            urlString = url.replace("ipfs://", "https://ipfs.io/ipfs/")
            return urlString
        }
        return url
    }
    return null
}

fun JsonObject.moveValidatorImg(): String? {
    if (this.has("imageUrl")) {
        return this["imageUrl"].asString
    }
    return null
}

fun JsonObject.moveValidatorName(): String {
    return this["name"].asString ?: ""
}

fun JsonObject.moveValidatorCommission(): BigDecimal {
    return this["commissionRate"].asString.toBigDecimal().movePointLeft(2)
        .setScale(2, RoundingMode.DOWN)
}