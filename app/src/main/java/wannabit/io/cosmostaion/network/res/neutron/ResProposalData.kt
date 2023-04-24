package wannabit.io.cosmostaion.network.res.neutron

data class ResProposalData(val proposals: List<ProposalData?>)

data class ProposalData(val id: String?, val proposal: Proposal?)

data class Proposal(
    val title: String?,
    val expiration: Expiration?,
    val status: String?
)

data class Expiration(val at_time: String?)