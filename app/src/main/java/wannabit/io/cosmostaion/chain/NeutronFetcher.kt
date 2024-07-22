package wannabit.io.cosmostaion.chain

import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.data.model.res.VestingData
import java.math.BigDecimal
import java.math.RoundingMode

class NeutronFetcher(private val chain: BaseChain) : CosmosFetcher(chain) {

    var neutronDeposited: BigDecimal = BigDecimal.ZERO
    var neutronVesting: VestingData? = null

    override fun denomValue(denom: String, isUsd: Boolean?): BigDecimal? {
        return if (denom == chain.stakeDenom) {
            chain.cosmosFetcher?.balanceValue(denom, isUsd)?.add(neutronVestingValue(isUsd))
                ?.add(neutronDepositedValue(isUsd))
        } else {
            balanceValue(denom, isUsd)
        }
    }

    override fun allStakingDenomAmount(): BigDecimal {
        return chain.cosmosFetcher?.balanceAmount(chain.stakeDenom)?.add(neutronVestingAmount())
            ?.add(neutronDeposited) ?: BigDecimal.ZERO
    }
    override fun allAssetValue(isUsd: Boolean?): BigDecimal {
        return chain.cosmosFetcher?.balanceValueSum(isUsd)?.add(neutronVestingValue(isUsd))
            ?.add(neutronDepositedValue(isUsd)) ?: BigDecimal.ZERO
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
        BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            val amount = neutronVestingAmount()
            return price.multiply(amount).movePointLeft(asset.decimals ?: 6)
                .setScale(6, RoundingMode.DOWN)
        }
        return BigDecimal.ZERO
    }

    private fun neutronDepositedValue(isUsd: Boolean? = false): BigDecimal {
        BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            val amount = neutronDeposited
            return price.multiply(amount).movePointLeft(asset.decimals ?: 6)
        }
        return BigDecimal.ZERO
    }
}