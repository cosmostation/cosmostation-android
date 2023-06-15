package wannabit.io.cosmostaion.database.model

class Chain(val chainName: String, val imageUrl: String, val bip44: String, val chainConfig: ChainConfig) {
    companion object {
        fun allChains(): List<Chain> {
            val chains = mutableListOf<Chain>()
            //add all cosmos chains
            val cosmos = Chain("Cosmos", "", "118", ChainConfig.Cosmos("cosmoshub-4", "cosmos", "uatom", "Cosmos", 6, "ATOM", "", "", "", "grpc-cosmos.cosmostation.io"))
            chains.add(cosmos)

            //add all ethereum chains
            val ethereum = Chain("Ethereum", "", "60", ChainConfig.Ethereum("", "", "", "Ethereum", 18, "ETH"))
            chains.add(ethereum)

            //add all sui

            //add all aptos

            //add custom chains from database

            //filter unchecked chains
            return chains
        }
    }
}