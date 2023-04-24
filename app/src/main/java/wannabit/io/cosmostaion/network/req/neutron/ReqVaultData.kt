package wannabit.io.cosmostaion.network.req.neutron

data class ConfigReq(val get_config: GetConfig)
class GetConfig

data class TotalPowerReq(val total_power_at_height: TotalPower)
class TotalPower

data class VotingPowerReq(val voting_power_at_height: VotingPower)
data class VotingPower(val address: String)

data class ProposalListReq(val reverse_proposals: ProposalList)
class ProposalList