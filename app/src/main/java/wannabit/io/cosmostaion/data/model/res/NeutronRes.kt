package wannabit.io.cosmostaion.data.model.res

data class VestingData(
    val allocated_amount: String?,
    val withdrawn_amount: String?,
)

data class Vault(
    val name: String?,
    val description: String?,
    val address: String?,
)

data class Dao(
    val name: String?,
    val description: String?,
    val dao_uri: String?,
    val address: String?,
    val voting_module: String?,
    val group_contract_address: String?,
    val proposal_modules: MutableList<ProposalModule?>
)
data class ProposalModule(
    val name: String?,
    val description: String?,
    val address: String?,
    val prefix: String?,
    val status: String?
)


data class ResProposalData(val proposals: MutableList<ProposalData?>)
data class ProposalData(val id: String?, val proposal: Proposal?, var isSwitchChecked: Boolean = false) {
    var myVote: String? = ""
}
data class Proposal(
    val title: String?,
    val expiration: Expiration?,
    val status: String?,
    val choices: MutableList<Choice?>,
    val allow_revoting: Boolean
)
data class Expiration(val at_time: String?, val at_height: String?)
data class Choice(val index: Int?, val option_type: String?, val description: String?)

data class ResDaoVoteStatus(
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