package wannabit.io.cosmostaion.network.req.neutron

data class TotalPowerReq(val total_power_at_height: TotalPower)
class TotalPower

data class VotingPowerReq(val voting_power_at_height: VotingPower)
data class VotingPower(val address: String)

data class ProposalListReq(val reverse_proposals: ProposalList)
class ProposalList

data class GroupContractReq(val group_contract: GroupContract)
class GroupContract

data class ListMemberReq(val list_members: ListMember)
class ListMember