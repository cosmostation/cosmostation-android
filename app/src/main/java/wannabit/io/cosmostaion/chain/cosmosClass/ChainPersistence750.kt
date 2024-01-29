package wannabit.io.cosmostaion.chain.cosmosClass

import com.google.common.collect.ImmutableList
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.PubKeyType

class ChainPersistence750 : ChainPersistence118() {

    override var isDefault = false
    override var tag: String = "persistence750"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/750'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(750, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )
}