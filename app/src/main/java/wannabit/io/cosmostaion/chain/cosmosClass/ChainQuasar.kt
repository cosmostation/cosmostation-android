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
class ChainQuasar : CosmosLine(), Parcelable {

    override var name: String = "Quasar"
    override var tag: String = "quasar118"
    override var logo: Int = R.drawable.chain_quasar
    override var swipeLogo: Int = R.drawable.chain_swipe_quasar
    override var apiName: String = "quasar"
    override var stakeDenom: String? = "uqsr"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )
    override var accountPrefix: String? = "quasar"

    override var grpcHost: String = "grpc-quasar.cosmostation.io"
}