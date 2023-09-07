package wannabit.io.cosmostaion.chain

import io.grpc.ManagedChannel
import wannabit.io.cosmostaion.database.model.Wallet
import java.io.Serializable

abstract class Line : BaseChain(), Serializable {

    // Cosmos
    open fun loadAuth() {}
    open fun loadBalance(channel: ManagedChannel) {}
    open fun loadDelegation(channel: ManagedChannel) {}
    open fun loadUnbonding() {}
    open fun loadReward() {}


    // Ethereum
    abstract fun getAddress(seed: ByteArray): String
    abstract suspend fun loadBalances(wallet: Wallet)
}