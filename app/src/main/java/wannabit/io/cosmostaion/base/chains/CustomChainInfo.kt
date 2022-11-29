package wannabit.io.cosmostaion.base.chains

data class CustomChainInfo(
    val id: Int,
    val name: String,
    val denom: String,
    val chainId: String,
    val prefix: String,
    val grpcUrl: String
)