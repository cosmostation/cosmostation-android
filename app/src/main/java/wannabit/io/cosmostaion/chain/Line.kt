package wannabit.io.cosmostaion.chain

import wannabit.io.cosmostaion.database.model.Wallet
import java.io.Serializable

abstract class Line(val chainName: String, val imageUrl: String, val bip44: String) : Serializable {
    abstract suspend fun loadBalances(wallet: Wallet)
    abstract fun getAddress(seed: ByteArray): String
}