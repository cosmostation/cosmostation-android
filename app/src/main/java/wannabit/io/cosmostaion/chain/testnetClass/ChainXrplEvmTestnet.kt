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
class ChainXrplEvmTestnet : BaseChain(), Parcelable {

    override var name: String = "XRPL EVM Testnet"
    override var tag: String = "xrplevm60_T"
    override var logo: Int = R.drawable.chain_xrpl_testnet
    override var isTestnet: Boolean = true
    override var apiName: String = "xrplevm-testnet"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "XRP"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var evmRpcURL: String = "https://rpc-office-evm.cosmostation.io/xrplevm-testnet/"
}