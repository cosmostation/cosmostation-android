package wannabit.io.cosmostaion.chain.cosmosClass

import android.os.Parcelable

import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.PubKeyType

@Parcelize
class ChainArkeo : BaseChain(), Parcelable {

    override var name: String = "Arkeo"
    override var tag: String = "arkeo118"
    override var apiName: String = "arkeo"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = listOf(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var stakeDenom: String = "uarkeo"
    override var accountPrefix: String = "arkeo"
    override var grpcHost: String = ""
    override var lcdUrl: String = "https://rest-seed.arkeo.network/"
}