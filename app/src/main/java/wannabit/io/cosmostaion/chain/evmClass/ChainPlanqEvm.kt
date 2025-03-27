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
class ChainPlanqEvm : BaseChain(), Parcelable {

    override var name: String = "Planq"
    override var tag: String = "planq60"
    override var logo: Int = R.drawable.chain_planq
    override var apiName: String = "planq"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var stakeDenom: String = "aplanq"
    override var accountPrefix: String = "plq"
    override var grpcHost: String = "grpc.planq.network"
    override var lcdUrl: String = "https://planq.api.m.stavr.tech/"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "PLANQ"
    override var coinGeckoId: String = "planq"
    override var coinLogo: Int = R.drawable.token_planq
    override var evmRpcURL: String = "https://planq-rpc.nodies.app"
}