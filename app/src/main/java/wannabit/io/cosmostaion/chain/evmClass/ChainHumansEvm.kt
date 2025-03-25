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
class ChainHumansEvm : BaseChain(), Parcelable {

    override var name: String = "Humans"
    override var tag: String = "humans60"
    override var logo: Int = R.drawable.chain_humans
    override var apiName: String = "humans"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_GRPC
    override var stakeDenom: String = "aheart"
    override var accountPrefix: String = "human"
    override var grpcHost: String = "grpc-humans.cosmostation.io"
    override var lcdUrl: String = "https://lcd-humans.cosmostation.io/"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "HEART"
    override var addressLogo: Int = R.drawable.icon_humans_address
    override var evmRpcURL: String = "https://rpc-humans-evm.cosmostation.io"
}