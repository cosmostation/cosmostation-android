package wannabit.io.cosmostaion.chain.evmClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.PubKeyType

@Parcelize
open class ChainMantraEvm : BaseChain(), Parcelable {

    override var name: String = "Mantra"
    override var tag: String = "mantra60"
    override var apiName: String = "mantra"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_GRPC
    override var stakeDenom: String = "uom"
    override var accountPrefix: String = "mantra"
    override var grpcHost: String = "grpc-mantra.cosmostation.io"
    override var lcdUrl: String = "https://lcd-mantra.cosmostation.io/"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "OM"
    override var evmRpcURL: String = "https://rpc.evm.mantra.mainnet.cosmostation.io"
}