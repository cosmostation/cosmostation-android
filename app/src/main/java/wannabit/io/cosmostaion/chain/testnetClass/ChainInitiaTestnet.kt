package wannabit.io.cosmostaion.chain.testnetClass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.cosmosClass.ChainInitia

@Parcelize
class ChainInitiaTestnet : ChainInitia(), Parcelable {

    override var name: String = "Initia Testnet"
    override var tag: String = "initia60_T"
    override var isTestnet: Boolean = true
    override var apiName: String = "initia-testnet"

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var grpcHost: String = ""
    override var lcdUrl: String = "https://rest.testnet.initia.xyz/"
}