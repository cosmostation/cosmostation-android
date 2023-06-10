package wannabit.io.cosmostaion.database.model

data class EthereumChain(
    val id: Long,
    val chainId: String,
    val bech32Prefix: String,
    val baseDenom: String,
    val bip44: String,
    val chainName: String,
    val decimal: Int,
    val displayDenom: String,
    val gasInfo: String,
    val gasRate: String,
    val restUrl: String
)