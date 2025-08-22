package wannabit.io.cosmostaion.chain.fetcher

import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.data.model.res.VestingData
import java.math.BigDecimal
import java.math.RoundingMode

class NeutronFetcher(private val chain: BaseChain) : CosmosFetcher(chain) {

    var neutronDeposited: BigDecimal = BigDecimal.ZERO
    var neutronVesting: VestingData? = null
    var neutronRewards: BigDecimal = BigDecimal.ZERO

    override fun denomValue(denom: String, isUsd: Boolean?): BigDecimal? {
        return if (denom == chain.getMainAssetDenom()) {
            chain.cosmosFetcher?.balanceValue(denom, isUsd)?.add(neutronVestingValue(isUsd))
                ?.add(chain.cosmosFetcher?.rewardValue(denom, isUsd))
                ?.add(delegationValueSum(isUsd))?.add(unbondingValueSum(isUsd))
                ?.add(neutronDepositedValue(isUsd))?.add(neutronRewardsValue(isUsd))

        } else {
            balanceValue(denom, isUsd)
        }
    }

    override fun allStakingDenomAmount(): BigDecimal {
        return chain.cosmosFetcher?.balanceAmount(chain.getMainAssetDenom())?.add(neutronVestingAmount())
            ?.add(delegationValueSum())?.add(unbondingValueSum())
            ?.add(rewardAmountSum(chain.getMainAssetDenom()))?.add(neutronDeposited)?.add(neutronRewards)
            ?: BigDecimal.ZERO
    }

    override fun allAssetValue(isUsd: Boolean?): BigDecimal {
        return chain.cosmosFetcher?.balanceValueSum(isUsd)?.add(neutronVestingValue(isUsd))
            ?.add(delegationValueSum(isUsd))?.add(unbondingValueSum(isUsd))
            ?.add(rewardValueSum(isUsd))?.add(neutronDepositedValue(isUsd))
            ?.add(neutronRewardsValue(isUsd)) ?: BigDecimal.ZERO
    }

    fun neutronVestingAmount(): BigDecimal? {
        neutronVesting?.let {
            val allocatedAmount = it.allocated_amount?.toBigDecimal()
            val withdrawnAmount = it.withdrawn_amount?.toBigDecimal()
            return allocatedAmount?.subtract(withdrawnAmount)
        }
        return BigDecimal.ZERO
    }

    private fun neutronVestingValue(isUsd: Boolean? = false): BigDecimal {
        BaseData.getAsset(chain.apiName, chain.getMainAssetDenom())?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            val amount = neutronVestingAmount()
            return price.multiply(amount).movePointLeft(asset.decimals ?: 6)
                .setScale(6, RoundingMode.DOWN)
        }
        return BigDecimal.ZERO
    }

    private fun neutronDepositedValue(isUsd: Boolean? = false): BigDecimal {
        BaseData.getAsset(chain.apiName, chain.getMainAssetDenom())?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            val amount = neutronDeposited
            return price.multiply(amount).movePointLeft(asset.decimals ?: 6)
        }
        return BigDecimal.ZERO
    }

    private fun neutronRewardsValue(isUsd: Boolean? = false): BigDecimal {
        BaseData.getAsset(chain.apiName, chain.getMainAssetDenom())?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            val amount = neutronRewards
            return price.multiply(amount).movePointLeft(asset.decimals ?: 6)
        }
        return BigDecimal.ZERO
    }
}