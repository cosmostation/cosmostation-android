package wannabit.io.cosmostaion.chain

import wannabit.io.cosmostaion.database.model.Wallet

abstract class Line(val chainName: String, val imageUrl: String, val bip44: String) {
    abstract suspend fun loadBalances(wallet: Wallet)
    abstract fun getAddress(seed: ByteArray): String
}