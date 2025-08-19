package wannabit.io.cosmostaion.chain.evmClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.PubKeyType

@Parcelize
class ChainSonic : BaseChain(), Parcelable {

    override var name: String = "Sonic"
    override var tag: String = "sonic60"
    override var apiName: String = "sonic"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "S"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var evmRpcURL: String = "https://rpc.soniclabs.com"
}