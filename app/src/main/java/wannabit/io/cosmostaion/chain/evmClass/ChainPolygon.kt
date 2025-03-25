package wannabit.io.cosmostaion.chain.evmClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.PubKeyType

@Parcelize
class ChainPolygon : BaseChain(), Parcelable {

    override var name: String = "Polygon"
    override var tag: String = "polygon60"
    override var logo: Int = R.drawable.chain_polygon
    override var apiName: String = "polygon"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "MATIC"
    override var coinLogo: Int = R.drawable.token_matic

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var evmRpcURL: String = "https://polygon-rpc.com"
}