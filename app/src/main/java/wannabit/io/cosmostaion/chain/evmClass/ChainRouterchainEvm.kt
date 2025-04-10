package wannabit.io.cosmostaion.chain.evmClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.PubKeyType

@Parcelize
class ChainRouterchainEvm : BaseChain(), Parcelable {

    override var name: String = "RouterChain"
    override var tag: String = "routerchain60"
    override var logo: Int = R.drawable.chain_routerchain
    override var apiName: String = "routerchain"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var stakeDenom: String = "route"
    override var accountPrefix: String = "router"
    override var grpcHost: String = "grpc.router.nodestake.org"
    override var lcdUrl: String = "https://sentry.lcd.routerprotocol.com/"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "ROUTE"
    override var evmRpcURL: String = "https://sentry.evm.rpc.routerprotocol.com"
}