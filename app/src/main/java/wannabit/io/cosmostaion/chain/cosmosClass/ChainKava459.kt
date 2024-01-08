package wannabit.io.cosmostaion.chain.cosmosClass

import com.google.common.collect.ImmutableList
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.PubKeyType

open class ChainKava459 : ChainKava60() {

    override var isDefault = true
    override var tag: String = "kava459"

    override var supportErc20: Boolean = false
    override var evmCompatible: Boolean = false

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/459'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(459, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )
}