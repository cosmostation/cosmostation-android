package wannabit.io.cosmostaion.chain.cosmosClass

import com.google.common.collect.ImmutableList
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.PubKeyType

class ChainLikeCoin : CosmosLine() {

    override var name: String = "Likecoin"
    override var tag: String = "likecoin118"
    override var logo: Int = R.drawable.chain_likecoin
    override var swipeLogo: Int = R.drawable.chain_swipe_likecoin
    override var apiName: String = "likecoin"
    override var stakeDenom: String? = "nanolike"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )
    override var accountPrefix: String? = "like"

    override var grpcHost: String = "grpc-likecoin.cosmostation.io"
}