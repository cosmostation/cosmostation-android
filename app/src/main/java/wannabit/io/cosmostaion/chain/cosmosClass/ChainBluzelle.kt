package wannabit.io.cosmostaion.chain.cosmosClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.PubKeyType

@Parcelize
class ChainBluzelle : BaseChain(), Parcelable {

    override var name: String = "Bluzelle"
    override var tag: String = "bluzellee118"
    override var apiName: String = "bluzelle"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/483'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(483, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var stakeDenom: String = "ubnt"
    override var accountPrefix: String = "bluzelle"
    override var grpcHost: String = "a.client.sentry.net.bluzelle.com:9090"
    override var lcdUrl: String = "https://b.client.sentry.net.bluzelle.com:1317/"
}