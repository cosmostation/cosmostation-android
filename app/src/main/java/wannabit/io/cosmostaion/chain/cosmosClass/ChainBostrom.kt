package wannabit.io.cosmostaion.chain.cosmosClass

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
class ChainBostrom : BaseChain(), Parcelable {

    override var name: String = "Bostrom"
    override var tag: String = "bostrom118"
    override var logo: Int = R.drawable.chain_bostrom
    override var apiName: String = "bostrom"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var stakeDenom: String = "boot"
    override var accountPrefix: String = "bostrom"
    override var grpcHost: String = "grpc-cyber-ia.cosmosia.notional.ventures"
    override var lcdUrl: String = "https://lcd.bostrom.cybernode.ai/"
}