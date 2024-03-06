package wannabit.io.cosmostaion.chain.cosmosClass

import com.google.common.collect.ImmutableList
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.PubKeyType

class ChainStride : CosmosLine() {

    override var name: String = "Stride"
    override var tag: String = "stride118"
    override var logo: Int = R.drawable.chain_stride
    override var swipeLogo: Int = R.drawable.chain_swipe_stride
    override var apiName: String = "stride"
    override var stakeDenom: String? = "ustrd"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true),
        ChildNumber(118, true),
        ChildNumber.ZERO_HARDENED,
        ChildNumber.ZERO
    )
    override var accountPrefix: String? = "stride"

    override var grpcHost: String = "grpc-stride.cosmostation.io"
}