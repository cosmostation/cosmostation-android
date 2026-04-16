package wannabit.io.cosmostaion.chain.testnetClass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCheqd

@Parcelize
class ChainCheqdTestnet : ChainCheqd(), Parcelable {

    override var name: String = "Cheqd Testnet"
    override var tag: String = "cheqd118_T"
    override var isTestnet: Boolean = true
    override var apiName: String = "cheqd-testnet"

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var grpcHost: String = "grpc.cheqd.network:443"
    override var lcdUrl: String = "https://api.cheqd.network/"
}