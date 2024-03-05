package wannabit.io.cosmostaion.chain.cosmosClass

import com.google.common.collect.ImmutableList
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.PubKeyType

class ChainXpla : CosmosLine() {

    override var name: String = "Xpla"
    override var tag: String = "xpla60"
    override var logo: Int = R.drawable.chain_xpla
    override var swipeLogo: Int = R.drawable.chain_swipe_xpla
    override var apiName: String = "xpla"
    override var stakeDenom: String? = "axpla"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )
    override var accountPrefix: String? = "xpla"

    override var grpcHost: String = "grpc-xpla.cosmostation.io"
}