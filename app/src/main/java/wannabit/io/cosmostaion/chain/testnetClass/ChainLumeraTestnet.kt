package wannabit.io.cosmostaion.chain.testnetClass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.cosmosClass.ChainLumera

@Parcelize
class ChainLumeraTestnet : ChainLumera(), Parcelable {

    override var name: String = "Lumera Testnet"
    override var tag: String = "lumera118_T"
    override var isTestnet: Boolean = true
    override var apiName: String = "lumera-testnet"

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var grpcHost: String = "grpc.testnet.lumera.io"
    override var lcdUrl: String = "https://lcd.testnet.lumera.io/"
}