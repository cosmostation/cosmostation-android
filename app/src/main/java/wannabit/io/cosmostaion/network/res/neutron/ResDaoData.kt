package wannabit.io.cosmostaion.network.res.neutron

data class ResDaoData(
    val name: String?,
    val description: String?,
    val dao_uri: String?,
    val address: String?,
    val voting_module: String?,
    val proposal_modules: List<ProposalModule?>
)

data class ProposalModule(
    val name: String?,
    val description: String?,
    val address: String?,
    val prefix: String?,
    val status: String?
)

