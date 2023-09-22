package wannabit.io.cosmostaion.chain.cosmosClass

import com.google.common.collect.ImmutableList
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.ChainType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.PubKeyType

class ChainLum118 : CosmosLine() {

    override var chainType: ChainType? = ChainType.COSMOS_TYPE
    override var isDefault = false
    override var name: String = "Lum"
    override var id: String = "lum118"
    override var logo: Int = R.drawable.chain_lum
    override var swipeLogo: Int = R.drawable.chain_swipe_lum
    override var apiName: String = "lum"
    override var stakeDenom: String = "ulum"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true),
        ChildNumber(118, true),
        ChildNumber.ZERO_HARDENED,
        ChildNumber.ZERO
    )
    override var accountPrefix: String? = "lum"

    override var grpcHost: String = "grpc-lum.cosmostation.io"
}