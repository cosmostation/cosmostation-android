package wannabit.io.cosmostaion.database.model

abstract class Line(val chainName: String, val imageUrl: String, val bip44: String, val chainConfig: ChainConfig) {
    abstract fun loadBalances()

    abstract fun getAddress(): String
}