package wannabit.io.cosmostaion.database.model

sealed class ChainConfig {
    data class Cosmos(
        val chainId: String,
        val bech32Prefix: String,
        val baseDenom: String,
        val chainName: String,
        val decimal: Int,
        val displayDenom: String,
        val gasInfo: String,
        val gasRate: String,
        val restUrl: String
    ) : ChainConfig()

    data class Ethereum(
        val chainId: String,
        val networkName: String,
        val rpcUrl: String,
        val chainName: String,
        val decimal: Int,
        val displayDenom: String,
    ) : ChainConfig()

    data class Sui(
        val chainId: String,
        val networkName: String,
        val rpcUrl: String,
        val chainName: String,
        val decimal: Int,
        val displayDenom: String,
    ) : ChainConfig()
}