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
class ChainOptimism : BaseChain(), Parcelable {

    override var name: String = "Optimism"
    override var tag: String = "optimism60"
    override var logo: Int = R.drawable.chain_optimism
    override var apiName: String = "optimism"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "ETH"
    override var coinLogo: Int = R.drawable.token_eth_op

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var evmRpcURL: String = "https://mainnet.optimism.io"
}