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
class ChainAssetMantle : BaseChain(), Parcelable {

    override var name: String = "AssetMantle"
    override var tag: String = "assetmantle118"
    override var logo: Int = R.drawable.chain_assetmantle
    override var apiName: String = "asset-mantle"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_GRPC
    override var stakeDenom: String = "umntl"
    override var accountPrefix: String = "mantle"
    override var grpcHost: String = "grpc-asset-mantle.cosmostation.io"
    override var lcdUrl: String = "https://lcd-asset-mantle.cosmostation.io/"
}