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
class ChainNyx : CosmosLine(), Parcelable {

    override var name: String = "Nyx"
    override var tag: String = "nyx118"
    override var logo: Int = R.drawable.chain_nyx
    override var swipeLogo: Int = R.drawable.chain_swipe_nyx
    override var apiName: String = "nyx"
    override var stakeDenom: String? = "unyx"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )
    override var accountPrefix: String? = "n"

    override var grpcHost: String = "grpc-nyx.cosmostation.io"
}