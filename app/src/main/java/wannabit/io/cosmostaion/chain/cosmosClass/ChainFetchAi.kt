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
open class ChainFetchAi : CosmosLine(), Parcelable {

    override var name: String = "Fetch.Ai"
    override var tag: String = "fetchai118"
    override var logo: Int = R.drawable.chain_fetchai
    override var swipeLogo: Int = R.drawable.chain_swipe_fetchai
    override var apiName: String = "fetchai"
    override var stakeDenom: String? = "afet"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )
    override var accountPrefix: String? = "fetch"

    override var grpcHost: String = "grpc-fetchai.cosmostation.io"
}