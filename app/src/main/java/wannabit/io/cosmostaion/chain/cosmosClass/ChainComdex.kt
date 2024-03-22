package wannabit.io.cosmostaion.chain.cosmosClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.PubKeyType

@Parcelize
class ChainComdex : CosmosLine(), Parcelable {

    override var name: String = "Comdex"
    override var tag: String = "comdex118"
    override var logo: Int = R.drawable.chain_comdex
    override var swipeLogo: Int = R.drawable.chain_swipe_comdex
    override var apiName: String = "comdex"
    override var stakeDenom: String? = "ucmdx"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )
    override var accountPrefix: String? = "comdex"

    override var grpcHost: String = "grpc-comdex.cosmostation.io"
}