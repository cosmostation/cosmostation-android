package wannabit.io.cosmostaion.chain.testnetClass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.evmClass.ChainMonad

@Parcelize
class ChainMonadTestnet : ChainMonad(), Parcelable {

    override var name: String = "Monad Testnet"
    override var tag: String = "monad60_T"
    override var logo: Int = R.drawable.chain_monad_testnet
    override var isTestnet: Boolean = true
    override var apiName: String = "monad-testnet"

    override var evmRpcURL: String = "https://testnet-rpc.monad.xyz"
}