package wannabit.io.cosmostaion.database.model

class Chain(val chainName: String, val imageUrl: String, val bip44: String, val chainConfig: ChainConfig) {
    companion object {
        fun allChains(): List<Chain> {
            //add all cosmos chains
            Chain("Cosmos", "", "", ChainConfig.Cosmos("", "", "", "", 6, "", "", "", ""))
            Chain("Cosmos", "", "", ChainConfig.Cosmos("", "", "", "", 6, "", "", "", ""))
            Chain("Cosmos", "", "", ChainConfig.Cosmos("", "", "", "", 6, "", "", "", ""))

            //add all ethereum chains
            Chain("Ethereum", "", "", ChainConfig.Ethereum("", "", "", "", 18, ""))

            //add all sui
            Chain("Ethereum", "", "", ChainConfig.Ethereum("", "", "", "", 18, ""))

            //add all aptos
            Chain("Ethereum", "", "", ChainConfig.Ethereum("", "", "", "", 18, ""))

            //add custom chains from database

            //filter unchecked chains
            return listOf()
        }
    }
}