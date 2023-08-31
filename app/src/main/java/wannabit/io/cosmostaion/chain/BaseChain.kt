package wannabit.io.cosmostaion.chain

import java.io.Serializable

sealed class BaseChain : Serializable {
    data class Cosmos(
        val chainId: String,
        val bech32Prefix: String,
        val baseDenom: String,
        val chainName: String,
        val decimal: Int,
        val displayDenom: String,
        val gasInfo: String,
        val gasRate: String,
        val restUrl: String,
        val grpcUrl: String
    ) : BaseChain()

    data class Ethereum(
        val chainId: String,
        val networkName: String,
        val rpcUrl: String,
        val chainName: String,
        val decimal: Int,
        val displayDenom: String,
    ) : BaseChain()

    data class Sui(
        val chainId: String,
        val networkName: String,
        val rpcUrl: String,
        val chainName: String,
        val decimal: Int,
        val displayDenom: String,
    ) : BaseChain()
}