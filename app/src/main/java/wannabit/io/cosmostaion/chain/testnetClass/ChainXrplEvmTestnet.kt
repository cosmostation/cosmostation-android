package wannabit.io.cosmostaion.chain.testnetClass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import wannabit.io.cosmostaion.chain.evmClass.ChainXrplEvm

@Parcelize
class ChainXrplEvmTestnet : ChainXrplEvm(), Parcelable {

    override var name: String = "XRPL EVM Testnet"
    override var tag: String = "xrplevm60_T"
    override var isTestnet: Boolean = true
    override var apiName: String = "xrplevm-testnet"

    override var evmRpcURL: String = "https://rpc-office-evm.cosmostation.io/xrplevm-testnet/"
}