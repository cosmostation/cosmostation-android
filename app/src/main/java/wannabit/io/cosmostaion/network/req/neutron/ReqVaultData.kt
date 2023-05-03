package wannabit.io.cosmostaion.network.req.neutron

data class TotalPowerReq(val total_power_at_height: TotalPower)
class TotalPower

data class VotingPowerReq(val voting_power_at_height: VotingPower)
data class VotingPower(val address: String)

data class ProposalListReq(val reverse_proposals: ProposalList)
class ProposalList

data class ProposalDataReq(val proposal: Proposal)
data class Proposal(val proposal_id: Int)

data class ProposalMyVote(val get_vote: MyVote)
data class MyVote(val proposal_id: Int, val voter: String)