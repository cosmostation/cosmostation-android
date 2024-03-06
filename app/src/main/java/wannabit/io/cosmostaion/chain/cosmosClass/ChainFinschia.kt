package wannabit.io.cosmostaion.chain.cosmosClass

import com.google.common.collect.ImmutableList
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.PubKeyType

class ChainFinschia : CosmosLine() {

    override var name: String = "Finschia"
    override var tag: String = "finschia438"
    override var logo: Int = R.drawable.chain_finschia
    override var swipeLogo: Int = R.drawable.chain_swipe_finschia
    override var apiName: String = "finschia"
    override var stakeDenom: String? = "cony"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/438'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(438, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )
    override var accountPrefix: String? = "link"

    override var grpcHost: String = "grpc-finschia.cosmostation.io"
}