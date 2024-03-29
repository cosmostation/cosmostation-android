package wannabit.io.cosmostaion.chain.cosmosClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.common.CosmostationConstants

@Parcelize
open class ChainKava459 : CosmosLine(), Parcelable {

    override var isDefault = false
    override var name: String = "Kava"
    override var tag: String = "kava459"
    override var logo: Int = R.drawable.chain_kava
    override var swipeLogo: Int = R.drawable.chain_swipe_kava
    override var apiName: String = "kava"
    override var stakeDenom: String? = "ukava"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/459'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(459, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var accountPrefix: String? = "kava"

    override var grpcHost: String = "grpc-kava.cosmostation.io"
}

const val KAVA_MINT_IMG_URL = CosmostationConstants.CHAIN_BASE_URL + "kava/module/mint/"
const val KAVA_LEND_IMG_URL = CosmostationConstants.CHAIN_BASE_URL + "kava/module/lend/"