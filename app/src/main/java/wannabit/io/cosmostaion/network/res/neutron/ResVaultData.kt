package wannabit.io.cosmostaion.network.res.neutron

data class ResConfigData(
    val name: String?,
    val description: String?,
    val owner: String?,
    val manager: String?,
    val denom: String?,
)

data class ResVotingData(val power: String?)
