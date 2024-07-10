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
class ChainBase : BaseChain(), Parcelable {

    override var name: String = "Base"
    override var tag: String = "base60"
    override var logo: Int = R.drawable.chain_base
    override var swipeLogo: Int = R.drawable.chain_swipe_base
    override var apiName: String = "base"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "ETH"
    override var coinGeckoId: String = "ethereum"
    override var coinLogo: Int = R.drawable.token_eth_base
    override var supportStaking = false

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var evmRpcURL: String = "https://mainnet.base.org"
}