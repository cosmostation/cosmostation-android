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
class ChainAlthea118 : CosmosLine(), Parcelable {

    override var name: String = "Althea"
    override var tag: String = "althea118"
    override var logo: Int = R.drawable.chain_althea
    override var swipeLogo: Int = R.drawable.chain_swipe_althea
    override var apiName: String = "althea"
    override var stakeDenom: String? = "aalthea"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )
    override var accountPrefix: String? = "althea"

    override var grpcHost: String = "grpc-althea.cosmostation.io"
}