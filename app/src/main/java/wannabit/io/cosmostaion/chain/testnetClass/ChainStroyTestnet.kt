package wannabit.io.cosmostaion.chain.testnetClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.PubKeyType

@Parcelize
class ChainStroyTestnet : BaseChain(), Parcelable {

    override var name: String = "Story Testnet"
    override var tag: String = "story60_T"
    override var logo: Int = R.drawable.chain_story_testnet
    override var isTestnet: Boolean = true
    override var apiName: String = "story-testnet"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "IP"
    override var coinGeckoId: String = ""
    override var coinLogo: Int = R.drawable.token_ip

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var evmRpcURL: String = "https://rpc-office-evm.cosmostation.io/story-testnet/"
}