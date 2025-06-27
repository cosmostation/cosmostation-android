package wannabit.io.cosmostaion.chain.testnetClass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.evmClass.ChainMantraEvm

@Parcelize
class ChainMantraEvmTestnet : ChainMantraEvm(), Parcelable {

    override var name: String = "Dukong"
    override var tag: String = "dukong60_T"
    override var isTestnet: Boolean = true
    override var apiName: String = "mantra-testnet"

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_GRPC
    override var grpcHost: String = "grpc-office-mantra.cosmostation.io"
    override var lcdUrl: String = "https://lcd-office.cosmostation.io/mantra-testnet/"

    override var evmRpcURL: String = "https://rpc.evm.mantra.testnet.cosmostation.io"
}