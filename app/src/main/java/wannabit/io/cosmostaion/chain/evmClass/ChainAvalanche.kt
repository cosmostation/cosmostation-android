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
class ChainAvalanche : BaseChain(), Parcelable {

    override var name: String = "Avalanche"
    override var tag: String = "avalanche60"
    override var logo: Int = R.drawable.chain_avalanche
    override var swipeLogo: Int = R.drawable.chain_swipe_avalanche
    override var apiName: String = "avalanche"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "AVAX"
    override var coinGeckoId: String = "avalanche-2"
    override var coinLogo: Int = R.drawable.token_avax
    override var supportStaking = false

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var evmRpcURL: String = "https://avalanche.public-rpc.com"
}