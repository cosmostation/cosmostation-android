package wannabit.io.cosmostaion.chain.testnetClass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.cosmosClass.ChainTerraClassic

@Parcelize
class ChainTerraClassicTestnet : ChainTerraClassic(), Parcelable {

    override var name: String = "Terra Classic Testnet"
    override var tag: String = "terraclassic330_T"
    override var isTestnet: Boolean = true
    override var apiName: String = "terra-classic-testnet"

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var grpcHost: String = "rebel-rpc.luncgoblins.com"
    override var lcdUrl: String = "https://rebel-lcd.luncgoblins.com/"
}