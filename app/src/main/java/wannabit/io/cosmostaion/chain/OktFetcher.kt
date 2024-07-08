package wannabit.io.cosmostaion.chain

import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.chain.cosmosClass.OKT_GECKO_ID
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.safeApiCall
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.Token
import java.math.BigDecimal
import java.math.RoundingMode

class OktFetcher(chain: BaseChain) : FetcherLcd(chain) {

    var lcdAccountInfo: JsonObject? = JsonObject()
    var lcdOktDeposits: JsonObject? = JsonObject()
    var lcdOktWithdaws: JsonObject? = JsonObject()
    var lcdOktTokens: JsonObject? = JsonObject()
    var lcdValidatorInfo: MutableList<JsonObject> = mutableListOf()

    var tokens = mutableListOf<Token>()

    override fun loadValidators() = CoroutineScope(Dispatchers.IO).launch {
        if (lcdValidatorInfo.size > 0) {
            return@launch
        }

        when (val response = safeApiCall { RetrofitInstance.oktApi.oktValidators() }) {
            is NetworkResult.Success -> {
                lcdValidatorInfo.clear()
                response.data.sortWith { o1, o2 ->
                    when {
                        o1?.get("description")?.asJsonObject?.get("moniker")?.asString == "Cosmostation" -> -1
                        o2?.get("description")?.asJsonObject?.get("moniker")?.asString == "Cosmostation" -> 1
                        o1.get("jailed").asBoolean && !o2.get("jailed").asBoolean -> 1
                        o1.get("delegator_shares").asString.toDouble() > o2.get("delegator_shares").asString.toDouble() -> -1
                        o1.get("delegator_shares").asString.toDouble() < o2.get("delegator_shares").asString.toDouble() -> 1
                        else -> 0
                    }
                }
                lcdValidatorInfo = response.data
            }

            is NetworkResult.Error -> {
                return@launch
            }
        }
    }

    override fun allAssetValue(isUsd: Boolean?): BigDecimal {
        return lcdBalanceValue(chain.stakeDenom, isUsd).add(lcdOktDepositValue(isUsd))
            .add(lcdOktWithdrawValue(isUsd))
    }

    override fun tokenValue(address: String, isUsd: Boolean?): BigDecimal {
        tokens.firstOrNull { it.address == address }?.let { tokenInfo ->
            val price = BaseData.getPrice(tokenInfo.coinGeckoId, isUsd)
            return price.multiply(tokenInfo.amount?.toBigDecimal())
                .movePointLeft(tokenInfo.decimals).setScale(6, RoundingMode.DOWN)
        } ?: run {
            return BigDecimal.ZERO
        }
    }

    override fun allTokenValue(isUsd: Boolean?): BigDecimal {
        var result = BigDecimal.ZERO
        tokens.forEach { token ->
            val price = BaseData.getPrice(token.coinGeckoId, isUsd)
            val value = price.multiply(token.amount?.toBigDecimal()).movePointLeft(token.decimals)
                .setScale(6, RoundingMode.DOWN)
            result = result.add(value)
        }
        return result
    }

//    override fun isTxFeePayable(c: Context): Boolean {
//        val availableAmount = lcdBalanceAmount(chain.stakeDenom)
//        return availableAmount > BigDecimal(OKT_BASE_FEE)
//    }

    override fun lcdBalanceAmount(denom: String?): BigDecimal {
        lcdAccountInfo?.get("value")?.asJsonObject?.get("coins")?.asJsonArray?.firstOrNull { it.asJsonObject["denom"].asString == denom }
            ?.let { balance ->
                return balance.asJsonObject["amount"].asString.toBigDecimal()
            }
        return BigDecimal.ZERO
    }

    fun lcdBalanceValue(denom: String, isUsd: Boolean?): BigDecimal {
        if (denom == chain.stakeDenom) {
            val amount = lcdBalanceAmount(denom)
            val price = BaseData.getPrice(OKT_GECKO_ID, isUsd)
            return price.multiply(amount).setScale(6, RoundingMode.DOWN)
        }
        return BigDecimal.ZERO
    }

    fun lcdOktDepositAmount(): BigDecimal {
        return lcdOktDeposits?.get("tokens")?.asString?.toBigDecimal() ?: BigDecimal.ZERO
    }

    private fun lcdOktDepositValue(isUsd: Boolean? = false): BigDecimal {
        val price = BaseData.getPrice(OKT_GECKO_ID, isUsd)
        val amount = lcdOktDepositAmount()
        return price.multiply(amount).setScale(6, RoundingMode.DOWN)
    }

    fun lcdOktWithdrawAmount(): BigDecimal {
        return lcdOktWithdaws?.get("quantity")?.asString?.toBigDecimal() ?: BigDecimal.ZERO
    }

    private fun lcdOktWithdrawValue(isUsd: Boolean? = false): BigDecimal {
        val price = BaseData.getPrice(OKT_GECKO_ID, isUsd)
        val amount = lcdOktWithdrawAmount()
        return price.multiply(amount).setScale(6, RoundingMode.DOWN)
    }
}