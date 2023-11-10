package wannabit.io.cosmostaion.data.model.req

data class AllocationReq(val allocation: Allocation)
data class Allocation(val address: String?)

data class VotingPowerReq(val voting_power_at_height: VotingPower)
data class VotingPower(val address: String?)

data class ProposalListReq(val reverse_proposals: ProposalList)
class ProposalList

data class BondReq(val bond: Bond)
class Bond

data class UnbondReq(val unbond: Unbond)
data class Unbond(val amount: String?)

data class VoteReq(val vote: Vote)
data class Vote(val proposal_id: Int?, val vote: String?)

data class MultiVoteReq(val vote: MultiVote?)
data class MultiVote(val proposal_id: Int?, val vote: WeightVote?)
data class WeightVote(val option_id: Int?)