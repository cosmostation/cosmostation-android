package wannabit.io.cosmostaion.chain.cosmosClass

import com.google.common.collect.ImmutableList
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.PubKeyType

class ChainOkt996Keccak : ChainOkt60() {

    override var isDefault = false
    override var tag: String = "okt996_Keccak"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/996'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(996, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )
    override var evmCompatible: Boolean = false
}