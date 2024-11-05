package wannabit.io.cosmostaion.chain.fetcher

import com.initia.distribution.v1.DistributionProto
import com.initia.mstaking.v1.StakingProto.DelegationResponse
import com.initia.mstaking.v1.StakingProto.UnbondingDelegation
import wannabit.io.cosmostaion.chain.BaseChain

class InitiaFetcher(private val chain: BaseChain) : CosmosFetcher(chain) {

    var initiaDelegations = mutableListOf<DelegationResponse>()
    var initiaUnbondings = mutableListOf<UnbondingDelegation>()
    var initiaRewards = mutableListOf<DistributionProto.DelegationDelegatorReward>()
}