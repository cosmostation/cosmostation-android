package wannabit.io.cosmostaion.chain.cosmosClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.PubKeyType

@Parcelize
class ChainOkt996Secp : ChainOkt996Keccak(), Parcelable {

    override var isDefault = false
    override var tag: String = "okt996_Secp"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/996'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(996, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )
    override var evmCompatible: Boolean = false
}