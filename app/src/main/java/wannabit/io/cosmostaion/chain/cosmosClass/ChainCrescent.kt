package wannabit.io.cosmostaion.chain.cosmosClass

import com.google.common.collect.ImmutableList
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.ChainType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.PubKeyType

class ChainCrescent : CosmosLine() {

    override var chainType: ChainType? = ChainType.COSMOS_TYPE
    override var name: String = "Crescent"
    override var tag: String = "crescent118"
    override var logo: Int = R.drawable.chain_cresent
    override var swipeLogo: Int = R.drawable.chain_swipe_cresent
    override var apiName: String = "crescent"
    override var stakeDenom: String? = "ucre"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )
    override var accountPrefix: String? = "cre"

    override var grpcHost: String = "grpc-crescent.cosmostation.io"
}