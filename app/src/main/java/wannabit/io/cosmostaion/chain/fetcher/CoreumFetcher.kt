package wannabit.io.cosmostaion.chain.fetcher

import com.cosmos.base.v1beta1.CoinProto
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import java.math.BigDecimal
import java.math.RoundingMode

class CoreumFetcher(private val chain: BaseChain) : CosmosFetcher(chain) {

    var spendableBalances: MutableList<CoinProto.Coin>? = null

    override fun denomValue(denom: String, isUsd: Boolean?): BigDecimal? {
        return if (denom == chain.stakeDenom) {
            balanceValue(denom, isUsd).add(vestingValue(denom, isUsd))
                .add(rewardValue(denom, isUsd)).add(delegationValueSum(isUsd))
                .add(unbondingValueSum(isUsd)).add(lockedValue(isUsd))

        } else {
            balanceValue(denom, isUsd).add(vestingValue(denom, isUsd))
        }
    }

    override fun allAssetValue(isUsd: Boolean?): BigDecimal {
        return balanceValueSum(isUsd).add(vestingValueSum(isUsd)).add(delegationValueSum(isUsd))
            .add(unbondingValueSum(isUsd)).add(rewardValueSum(isUsd)).add(lockedValue(isUsd))
    }

    override fun valueCoinCnt(): Int {
        return spendableBalances?.count { BaseData.getAsset(chain.apiName, it.denom) != null } ?: 0
    }

    override fun balanceAmount(denom: String): BigDecimal {
        if (spendableBalances?.isNotEmpty() == true) {
            return spendableBalances?.firstOrNull { it.denom == denom }?.amount?.toBigDecimal()
                ?: BigDecimal.ZERO
        }
        return BigDecimal.ZERO
    }

    override fun balanceValueSum(isUsd: Boolean?): BigDecimal {
        var sum = BigDecimal.ZERO
        if (spendableBalances?.isNotEmpty() == true) {
            spendableBalances?.forEach { balance ->
                sum = sum.add(balanceValue(balance.denom, isUsd))
            }
        }
        return sum
    }

    private fun originalBalanceAmount(denom: String): BigDecimal {
        if (cosmosBalances?.isNotEmpty() == true) {
            return cosmosBalances?.firstOrNull { it.denom == denom }?.amount?.toBigDecimal()
                ?: BigDecimal.ZERO
        }
        return BigDecimal.ZERO
    }

    fun lockedAmount(denom: String): BigDecimal {
        if (spendableBalances?.isNotEmpty() == true && cosmosBalances?.isNotEmpty() == true) {
            return originalBalanceAmount(denom).subtract(balanceAmount(denom))
        }
        return BigDecimal.ZERO
    }

    private fun lockedValue(isUsd: Boolean? = false): BigDecimal {
        BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            val amount = lockedAmount(chain.stakeDenom)
            return price.multiply(amount).movePointLeft(asset.decimals ?: 6)
                .setScale(6, RoundingMode.DOWN)
        }
        return BigDecimal.ZERO
    }
}