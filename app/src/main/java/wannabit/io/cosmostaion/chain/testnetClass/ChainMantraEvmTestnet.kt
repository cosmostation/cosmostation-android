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

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var grpcHost: String = "grpc.dukong.mantrachain.io"
    override var lcdUrl: String = "https://api.dukong.mantrachain.io/"

    override var evmRpcURL: String = "https://evm.dukong.mantrachain.io"
}