package wannabit.io.cosmostaion.chain.fetcher

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.IOTA_FEE_SEND
import wannabit.io.cosmostaion.chain.majorClass.IOTA_FEE_STAKE
import wannabit.io.cosmostaion.chain.majorClass.IOTA_FEE_UNSTAKE
import wannabit.io.cosmostaion.chain.majorClass.IOTA_MAIN_DENOM
import wannabit.io.cosmostaion.chain.majorClass.IOTA_TYPE_COIN
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.jsonRpcResponse
import wannabit.io.cosmostaion.data.model.req.JsonRpcRequest
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.sign.SuiJS
import wannabit.io.cosmostaion.ui.tx.genTx.IotaTxType
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.concurrent.CopyOnWriteArrayList

class IotaFetcher(private val chain: BaseChain) {

    var iotaSystem = JsonObject()
    var iotaBalances: MutableList<Pair<String?, BigDecimal?>> = mutableListOf()
    var iotaStakedList: MutableList<JsonObject> = mutableListOf()
    var iotaObjects: MutableList<JsonObject> = mutableListOf()
    var iotaValidators: MutableList<JsonObject> = mutableListOf()
    var iotaApys: MutableList<JsonObject> = mutableListOf()
    val iotaCoinMeta: MutableMap<String, JsonObject> = mutableMapOf()
    val iotaHistory: MutableList<JsonObject> = mutableListOf()

    fun allAssetValue(isUsd: Boolean? = false): BigDecimal {
        return iotaBalanceValueSum(isUsd).add(iotaStakedValue(isUsd))
    }

    fun allIotaAmount(): BigDecimal? {
        return iotaBalanceAmount(IOTA_MAIN_DENOM)?.add(stakedAmount()) ?: BigDecimal.ZERO
    }

    fun allIotaValue(isUsd: Boolean? = false): BigDecimal {
        val amount = allIotaAmount()
        if (amount == BigDecimal.ZERO) return BigDecimal.ZERO
        BaseData.getAsset(chain.apiName, IOTA_MAIN_DENOM)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            return price.multiply(amount).movePointLeft(asset.decimals ?: 6)
                .setScale(6, RoundingMode.DOWN)
        }
        return BigDecimal.ZERO
    }

    fun iotaBalanceAmount(coinType: String): BigDecimal? {
        iotaBalances.firstOrNull { it.first == coinType }?.let { iotaCoin ->
            return iotaCoin.second
        }
        return BigDecimal.ZERO
    }

    fun iotaBalanceValue(coinType: String, isUsd: Boolean? = false): BigDecimal {
        val amount = iotaBalanceAmount(coinType)
        if (amount == BigDecimal.ZERO) return BigDecimal.ZERO
        BaseData.getAsset(chain.apiName, coinType)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            return price.multiply(amount).movePointLeft(asset.decimals ?: 6)
                .setScale(6, RoundingMode.DOWN)
        }
        return BigDecimal.ZERO
    }

    private fun iotaBalanceValueSum(isUsd: Boolean? = false): BigDecimal {
        var sum = BigDecimal.ZERO
        if (iotaBalances.isNotEmpty()) {
            synchronized(iotaBalances) {
                val balanceList = CopyOnWriteArrayList(iotaBalances)
                val iterator = balanceList.iterator()
                while (iterator.hasNext()) {
                    val balance = iterator.next()
                    balance.first?.let {
                        sum = sum.add(iotaBalanceValue(it, isUsd))
                    }
                }
            }
        }
        return sum
    }

    fun stakedAmount(): BigDecimal {
        var staked = BigDecimal.ZERO
        var earned = BigDecimal.ZERO
        iotaStakedList.forEach { iotaStaked ->
            iotaStaked["stakes"].asJsonArray.forEach { stakes ->
                staked = staked.add(stakes.asJsonObject["principal"].asLong.toBigDecimal())
                earned = earned.add(
                    stakes.asJsonObject.get("estimatedReward")?.asLong?.toBigDecimal()
                        ?: BigDecimal.ZERO
                )
            }
        }
        return staked.add(earned)
    }

    private fun iotaStakedValue(isUsd: Boolean? = false): BigDecimal {
        val amount = stakedAmount()
        if (amount == BigDecimal.ZERO) {
            return BigDecimal.ZERO
        }
        BaseData.getAsset(chain.apiName, IOTA_MAIN_DENOM)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            return price.multiply(amount).movePointLeft(asset.decimals ?: 6)
                .setScale(6, RoundingMode.DOWN)
        }
        return BigDecimal.ZERO
    }

    fun principalAmount(): BigDecimal {
        var staked = BigDecimal.ZERO
        iotaStakedList.forEach { iotaStaked ->
            iotaStaked["stakes"].asJsonArray.forEach { stakes ->
                staked = staked.add(stakes.asJsonObject["principal"].asLong.toBigDecimal())
            }
        }
        return staked
    }

    fun estimateRewardAmount(): BigDecimal {
        var earned = BigDecimal.ZERO
        iotaStakedList.forEach { iotaStaked ->
            iotaStaked["stakes"].asJsonArray.forEach { stakes ->
                if (stakes.asJsonObject["estimatedReward"] != null) {
                    earned =
                        earned.add(stakes.asJsonObject["estimatedReward"].asLong.toBigDecimal())
                }
            }
        }
        return earned
    }

    fun iotaAllNfts(): MutableList<JsonObject> {
        return iotaObjects.filter { iotaObject ->
            val types = iotaObject["data"].asJsonObject["type"].asString.lowercase()
            (!types.contains("stakediota") && !types.contains("coin"))
        }.toMutableList()
    }

    fun iotaBaseFee(txType: IotaTxType): BigDecimal {
        return when (txType) {
            IotaTxType.IOTA_SEND_COIN, IotaTxType.IOTA_SEND_NFT -> {
                IOTA_FEE_SEND.toBigDecimal()
            }

            IotaTxType.IOTA_STAKE -> {
                IOTA_FEE_STAKE.toBigDecimal()
            }

            IotaTxType.IOTA_UNSTAKE -> {
                IOTA_FEE_UNSTAKE.toBigDecimal()
            }
        }
    }

    fun iotaRpc(): String {
        val endpoint = Prefs.getEvmRpcEndpoint(chain)
        return if (endpoint?.isNotEmpty() == true) {
            endpoint
        } else {
            chain.mainUrl
        }
    }

    private fun referenceGasPrice(): String {
        val iotaxReferenceGasPriceRequest =
            JsonRpcRequest(method = "iotax_getReferenceGasPrice", params = listOf())
        val iotaxReferenceGasPriceResponse = jsonRpcResponse(
            iotaRpc(), iotaxReferenceGasPriceRequest
        )
        val iotaxReferenceGasPriceJsonObject = Gson().fromJson(
            iotaxReferenceGasPriceResponse.body?.string(), JsonObject::class.java
        )
        return iotaxReferenceGasPriceJsonObject["result"].asString
    }

    private fun iotaxCoins(): JsonArray {
        val iotaxGetCoinsRequest = JsonRpcRequest(
            method = "iotax_getCoins", params = listOf(chain.mainAddress, IOTA_MAIN_DENOM, null, 1)
        )
        val iotaxGetCoinsResponse = jsonRpcResponse(chain.mainUrl, iotaxGetCoinsRequest)
        val iotaxGetCoinsJsonObject = Gson().fromJson(
            iotaxGetCoinsResponse.body?.string(), JsonObject::class.java
        )

        return iotaxGetCoinsJsonObject["result"].asJsonObject["data"].asJsonArray
    }

    fun buildStakingRequest(suiJs: SuiJS, amount: String, validatorAddress: String?): String? {
        val gasPrice = referenceGasPrice()
        val coinDatas = iotaxCoins()

        return if (coinDatas.size() > 0) {
            val coinData = coinDatas[0].asJsonObject
            val gasBudget = iotaBaseFee(IotaTxType.IOTA_STAKE)
            val coinObjectId = coinData["coinObjectId"].asString
            val version = coinData["version"].asString
            val digest = coinData["digest"].asString

            val buildStakingRequestFunction =
                """function buildStakingRequestFunction() {
                const txHex = buildIotaStakingRequest('${amount}', '${validatorAddress}', '${chain.mainAddress}', 
                '${gasPrice}', '${gasBudget}', '${coinObjectId}', '${version}', '${digest}');
                return txHex;
                }""".trimMargin()
            suiJs.mergeFunction(buildStakingRequestFunction)
            return suiJs.executeFunction("buildStakingRequestFunction()")

        } else {
            ""
        }
    }

    private fun iotaObject(objectId: String): JsonObject {
        val iotaGetObjectRequest = JsonRpcRequest(
            method = "iota_getObject", params = listOf(objectId, mapOf("showContent" to false))
        )
        val iotaGetObjectResponse = jsonRpcResponse(chain.mainUrl, iotaGetObjectRequest)
        val iotaGetObjectJsonObject = Gson().fromJson(
            iotaGetObjectResponse.body?.string(), JsonObject::class.java
        )

        return iotaGetObjectJsonObject["result"].asJsonObject["data"].asJsonObject
    }

    fun buildUnstakingRequest(suiJs: SuiJS, objectId: String): String? {
        val gasPrice = referenceGasPrice()
        val coinDatas = iotaxCoins()

        return if (coinDatas.size() > 0) {
            val coinData = coinDatas[0].asJsonObject
            val gasBudget = iotaBaseFee(IotaTxType.IOTA_UNSTAKE)
            val coinObjectId = coinData["coinObjectId"].asString
            val version = coinData["version"].asString
            val digest = coinData["digest"].asString

            val stakedObject = iotaObject(objectId)
            val stakedObjectVersion = stakedObject["version"].asString
            val stakedObjectDigest = stakedObject["digest"].asString

            val buildUnstakingRequestFunction =
                """function buildUnstakingRequestFunction() {
                const txHex = buildIotaUnstakingRequest('${chain.mainAddress}', '${gasPrice}', '${gasBudget}', '${coinObjectId}', '${version}', '${digest}',
                '${objectId}', '${stakedObjectVersion}', '${stakedObjectDigest}');
                return txHex;
                }""".trimMargin()
            suiJs.mergeFunction(buildUnstakingRequestFunction)
            return suiJs.executeFunction("buildUnstakingRequestFunction()")

        } else {
            ""
        }
    }
}

fun String.iotaIsCoinType(): Boolean {
    return this.startsWith(IOTA_TYPE_COIN)
}

fun String?.iotaCoinType(): String? {
    if (this?.iotaIsCoinType() == false) {
        return null
    }
    val regex = Regex("<(.+)>")
    this?.let {
        val matchResult = regex.find(it)
        return matchResult?.groups?.get(1)?.value
    }
    return null
}

fun String?.iotaCoinSymbol(): String? {
    val regex = Regex("::([a-zA-Z0-9_]+)(?:<.*>)?$")
    this?.let {
        val matchResult = regex.find(it)
        return matchResult?.groups?.get(1)?.value
    }
    return "Unknown"
}

fun JsonObject.iotaValidatorVp(): BigDecimal {
    return this["stakingPoolIotaBalance"].asString.toBigDecimal().movePointLeft(9)
        .setScale(9, RoundingMode.DOWN)
}