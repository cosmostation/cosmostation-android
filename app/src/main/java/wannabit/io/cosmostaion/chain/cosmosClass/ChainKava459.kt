package wannabit.io.cosmostaion.chain.cosmosClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.evmClass.ChainKavaEvm

@Parcelize
open class ChainKava459 : ChainKavaEvm(), Parcelable {

    override var name: String = "Kava"
    override var tag: String = "kava459"
    override var isDefault: Boolean = false
    override var apiName: String = "kava"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/459'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(459, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_GRPC
    override var stakeDenom: String = "ukava"
    override var accountPrefix: String = "kava"
    override var grpcHost: String = "grpc-kava.cosmostation.io"
    override var lcdUrl: String = "https://lcd-kava.cosmostation.io/"

    override var supportEvm: Boolean = false
    override var coinSymbol: String = ""
    override var evmRpcURL: String = ""
}