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
class ChainBand : CosmosLine(), Parcelable {

    override var name: String = "Band"
    override var tag: String = "band494"
    override var logo: Int = R.drawable.chain_band
    override var swipeLogo: Int = R.drawable.chain_swipe_band
    override var apiName: String = "band"
    override var stakeDenom: String? = "uband"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/494'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(494, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )
    override var accountPrefix: String? = "band"

    override var grpcHost: String = "grpc-band.cosmostation.io"
}