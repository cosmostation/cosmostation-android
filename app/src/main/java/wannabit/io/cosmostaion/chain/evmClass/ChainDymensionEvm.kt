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
class ChainDymensionEvm : BaseChain(), Parcelable {

    override var name: String = "Dymension"
    override var tag: String = "dymension60"
    override var apiName: String = "dymension"
    override var isOtherChainImage: Boolean = true

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_GRPC
    override var stakeDenom: String = "adym"
    override var accountPrefix: String = "dym"
    override var grpcHost: String = "grpc-dymension.cosmostation.io"
    override var lcdUrl: String = "https://lcd-dymension.cosmostation.io/"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "DYM"
    override var evmRpcURL: String = "https://rpc-dymension-evm.cosmostation.io"
}