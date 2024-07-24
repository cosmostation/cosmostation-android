package wannabit.io.cosmostaion.chain.cosmosClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.PubKeyType

@Parcelize
class ChainLcdKava459 : BaseChain(), Parcelable {

    override var name: String = "LCDKava"
    override var tag: String = "kavaLcd459"
    override var logo: Int = R.drawable.chain_kava
    override var swipeLogo: Int = R.drawable.chain_swipe_kava
    override var isDefault: Boolean = false
    override var apiName: String = "kava"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/459'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(459, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var supportCosmosLcd: Boolean = true
    override var stakeDenom: String = "ukava"
    override var accountPrefix: String = "kava"
    override var lcdUrl: String = "https://lcd-kava.cosmostation.io/"
}