package wannabit.io.cosmostaion.chain.testnetClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.cosmosClass.ChainTerraClassic

@Parcelize
class ChainTerraClassicTestnet : ChainTerraClassic(), Parcelable {

    override var name: String = "Terra Classic Testnet"
    override var tag: String = "terraclassic330_T"
    override var isTestnet: Boolean = true
    override var apiName: String = "terra-classic-testnet"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/330'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(330, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var stakeDenom: String = "uluna"
    override var accountPrefix: String = "terra"
    override var grpcHost: String = "rebel-rpc.luncgoblins.com"
    override var lcdUrl: String = "https://rebel-lcd.luncgoblins.com/"
}
