package wannabit.io.cosmostaion.chain.evmClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.PubKeyType

@Parcelize
class ChainBinanceSmart : EthereumLine(), Parcelable {

    override var name: String = "BSC"
    override var tag: String = "binance60"
    override var logo: Int = R.drawable.chain_binance
    override var swipeLogo: Int = R.drawable.chain_swipe_binance
    override var apiName: String = "bnb-smart-chain"

    override var coinSymbol: String = "BNB"
    override var coinGeckoId: String = "binancecoin"
    override var coinLogo: Int = R.drawable.token_bnb
    override var supportStaking = false

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var rpcUrl: String = "https://bsc-dataseed.binance.org"
}