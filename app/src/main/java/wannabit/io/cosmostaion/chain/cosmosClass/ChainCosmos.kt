package wannabit.io.cosmostaion.chain.cosmosClass

import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.ChainType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.PubKeyType

class ChainCosmos : CosmosLine() {

    override var chainType: ChainType? = ChainType.COSMOS_TYPE
    override var name: String = "Cosmos"
    override var id: String = "cosmos118"
    override var logo: Int = R.drawable.chain_cosmos
    override var swipeLogo: Int = -1
    override var apiName: String = "cosmos"
    override var stakeDenom: String = "uatom"
    override var grpcHost: String = "grpc-cosmos.cosmostation.io"

    override var accountKeyType: AccountKeyType? = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var accountPrefix: String? = "cosmos"
}