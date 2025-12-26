package wannabit.io.cosmostaion.chain.testnetClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.cosmosClass.ChainAirchains

@Parcelize
class ChainAirchainsTestnet : ChainAirchains(), Parcelable {

    override var name: String = "Airchains Testnet"
    override var tag: String = "airchains118_T"
    override var isTestnet: Boolean = true
    override var apiName: String = "airchains-testnet"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var stakeDenom: String = "uamf"
    override var accountPrefix: String = "air"
    override var grpcHost: String = "airchains-testnet-grpc.cosmonautstakes.com:14190"
    override var lcdUrl: String = "https://airchains.api.t.stavr.tech/"
}