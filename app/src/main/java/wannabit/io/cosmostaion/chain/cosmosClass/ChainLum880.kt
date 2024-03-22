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
open class ChainLum880 : CosmosLine(), Parcelable {

    override var name: String = "Lum"
    override var tag: String = "lum880"
    override var logo: Int = R.drawable.chain_lum
    override var swipeLogo: Int = R.drawable.chain_swipe_lum
    override var apiName: String = "lum"
    override var stakeDenom: String? = "ulum"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/880'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(880, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )
    override var accountPrefix: String? = "lum"

    override var grpcHost: String = "grpc-lum.cosmostation.io"
}