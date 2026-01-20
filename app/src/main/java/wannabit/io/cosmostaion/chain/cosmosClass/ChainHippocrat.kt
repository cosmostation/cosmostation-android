package wannabit.io.cosmostaion.chain.cosmosClass

import android.os.Parcelable

import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.PubKeyType

@Parcelize
class ChainHippocrat : BaseChain(), Parcelable {

    override var name: String = "Hippocrat"
    override var tag: String = "hippocrat118"
    override var apiName: String = "hippocrat"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/0'/0'/0/X")
    override var setParentPath: List<ChildNumber> = listOf(
        ChildNumber(44, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_GRPC
    override var stakeDenom: String = "ahp"
    override var accountPrefix: String = "hippo"
    override var grpcHost: String = "grpc.hippocrat.mainnet.cosmostation.io"
    override var lcdUrl: String = "https://lcd.hippocrat.mainnet.cosmostation.io/"
}