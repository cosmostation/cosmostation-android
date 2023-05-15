package wannabit.io.cosmostaion.network.res.neutron

data class ResProposalData(val proposals: List<ProposalData?>)
data class ProposalData(val id: String?, val proposal: Proposal?)

data class Proposal(
    val title: String?,
    val description: String?,
    val expiration: Expiration?,
    val status: String?,
    val threshold: Threshold?,
    val choices: List<Choice?>,
    val allow_revoting: Boolean
)

data class Expiration(val at_time: String?, val at_height: String?)
data class Threshold(val threshold_quorum: ThresholdQuorum?)
data class ThresholdQuorum(val threshold: Hold?, val quorum: Quorum?)
data class Hold(val percent: String?)
data class Quorum(val percent: String?)

data class Choice(val index: Int?, val option_type: String?, val description: String?)

data class ResMyVoteStatus(
    val id: Int?,
    val chain: String?,
    val chain_id: String?,
    val height: Long?,
    val tx_hash: String?,
    val contract_address: String?,
    val address: String?,
    val proposal_id: Int?,
    val power: String?,
    val option: String?,
    val voted_at: String?
)

data class ResMemberList(val members: List<Member>)
data class Member(val addr: String?, val weight: Int?)