package wannabit.io.cosmostaion.chain

import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.majorClass.SUI_FEE_DEFAULT
import wannabit.io.cosmostaion.chain.majorClass.SUI_FEE_SEND
import wannabit.io.cosmostaion.chain.majorClass.SUI_FEE_STAKE
import wannabit.io.cosmostaion.chain.majorClass.SUI_FEE_UNSTAKE
import wannabit.io.cosmostaion.chain.majorClass.SUI_MAIN_DENOM
import wannabit.io.cosmostaion.chain.majorClass.SUI_TYPE_COIN
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.ui.tx.genTx.SuiTxType
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.concurrent.CopyOnWriteArrayList

class SuiFetcher(private val chain: BaseChain) : CosmosFetcher(chain) {

    var suiSystem = JsonObject()
    var suiBalances: MutableList<Pair<String?, BigDecimal?>> = mutableListOf()
    var suiStakedList: MutableList<JsonObject> = mutableListOf()
    var suiObjects: MutableList<JsonObject> = mutableListOf()
    var suiValidators: MutableList<JsonObject> = mutableListOf()
    var suiApys: MutableList<JsonObject> = mutableListOf()
    val suiCoinMeta: MutableMap<String, JsonObject> = mutableMapOf()
    val suiHistory: MutableList<JsonObject> = mutableListOf()

    var suiState = true

    override fun allAssetValue(isUsd: Boolean?): BigDecimal {
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
        var staked = BigDecimal.ZERO
        var earned = BigDecimal.ZERO
        suiStakedList.forEach { suiStaked ->
            suiStaked["stakes"].asJsonArray.forEach { stakes ->
                staked = staked.add(stakes.asJsonObject["principal"].asLong.toBigDecimal())
                earned = earned.add(
                    stakes.asJsonObject.get("estimatedReward")?.asLong?.toBigDecimal()
                        ?: BigDecimal.ZERO
                )
            }
        }
        return staked.add(earned)
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
        var staked = BigDecimal.ZERO
        suiStakedList.forEach { suiStaked ->
            suiStaked["stakes"].asJsonArray.forEach { stakes ->
                staked = staked.add(stakes.asJsonObject["principal"].asLong.toBigDecimal())
            }
        }
        return staked
    }

    fun estimateRewardAmount(): BigDecimal {
        var earned = BigDecimal.ZERO
        suiStakedList.forEach { suiStaked ->
            suiStaked["stakes"].asJsonArray.forEach { stakes ->
                if (stakes.asJsonObject["estimatedReward"] != null) {
                    earned =
                        earned.add(stakes.asJsonObject["estimatedReward"].asLong.toBigDecimal())
                }
            }
        }
        return earned
    }

    fun suiAllNfts(): MutableList<JsonObject> {
        return suiObjects.filter { suiObject ->
            val types = suiObject["data"].asJsonObject["type"].asString.lowercase()
            (!types.contains("stakedsui") && !types.contains("coin"))
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

    fun hasFee(txType: SuiTxType): Boolean {
        val suiBalance = suiBalanceAmount(SUI_MAIN_DENOM)
        return suiBalance?.compareTo(suiBaseFee(txType))!! > 0
    }

    fun suiRpc(): String {
        val endpoint = Prefs.getEvmRpcEndpoint(chain)
        return if (endpoint?.isNotEmpty() == true) {
            endpoint
        } else {
            chain.mainUrl
        }
    }
}

fun String.suiIsCoinType(): Boolean {
    return this.startsWith(SUI_TYPE_COIN)
}

fun String?.suiCoinType(): String? {
    if (this?.suiIsCoinType() == false) {
        return null
    }
    this?.split("<")?.last()?.let { s1 ->
        return s1.split(">").first()
    }
    return null
}

fun String?.suiCoinSymbol(): String? {
    if (this?.suiIsCoinType() == false) {
        return null
    }
    this?.split("<")?.last()?.let { s1 ->
        s1.split(">").first().let { s2 ->
            return s2.split("::").last()
        }
    }
    return null
}

fun JsonObject?.assetImg(): String {
    return try {
        this?.get("iconUrl")?.asString ?: ""
    } catch (e: Exception) {
        ""
    }
}

fun JsonObject.suiRawNftUrlString(): String? {
    return try {
        this["display"].asJsonObject["data"].asJsonObject["image_url"].asString
    } catch (e: Exception) {
        null
    }
}

fun JsonObject.suiNftUrl(): String? {
    var urlString: String?
    suiRawNftUrlString()?.let { url ->
        if (url.startsWith("ipfs://")) {
            urlString = url.replace("ipfs://", "https://ipfs.io/ipfs/")
            return urlString
        }
        return url
    }
    return null
}

fun JsonObject.suiValidatorImg(): String? {
    if (this["imageUrl"].asString != null) {
        return this["imageUrl"].asString
    }
    return null
}

fun JsonObject.suiValidatorName(): String {
    return this["name"].asString ?: ""
}

fun JsonObject.suiValidatorCommission(): BigDecimal {
    return this["commissionRate"].asString.toBigDecimal().movePointLeft(2)
        .setScale(2, RoundingMode.DOWN)
}

fun JsonObject.suiValidatorVp(): BigDecimal {
    return this["stakingPoolSuiBalance"].asString.toBigDecimal().movePointLeft(9)
        .setScale(9, RoundingMode.DOWN)
}