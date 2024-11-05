package wannabit.io.cosmostaion.chain.fetcher

import com.initia.mstaking.v1.StakingProto.DelegationResponse
import com.initia.mstaking.v1.StakingProto.UnbondingDelegation
import com.initia.mstaking.v1.StakingProto.Validator
import wannabit.io.cosmostaion.chain.BaseChain
import java.math.BigDecimal

class InitiaFetcher(private val chain: BaseChain) : CosmosFetcher(chain) {

    var initiaDelegations = mutableListOf<DelegationResponse>()
    var initiaUnbondings = mutableListOf<UnbondingDelegation>()
    var initiaValidators = mutableListOf<Validator>()
    var initiaOriginValidators = mutableListOf<Validator>()

    override fun delegationAmountSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        val delegationListCopy = initiaDelegations.toList()
        for (delegation in delegationListCopy) {
            delegation.balanceList.filter { it.denom == chain.stakeDenom }.forEach { balance ->
                balance?.let {
                    sum = sum.add(balance.amount.toBigDecimal())
                } ?: run {
                    sum = BigDecimal.ZERO
                }
            }
        }
        return sum
    }

    override fun unbondingAmountSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        initiaUnbondings.forEach { unbonding ->
            unbonding.entriesList.forEach { entry ->
                entry.balanceList.filter { it.denom == chain.stakeDenom }.forEach { balance ->
                    sum = sum.add(balance.amount.toBigDecimal())
                }
            }
        }
        return sum
    }
}