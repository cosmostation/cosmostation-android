package wannabit.io.cosmostaion.chain.cosmosClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.fetcher.CoreumFetcher
import wannabit.io.cosmostaion.chain.fetcher.CosmosFetcher

@Parcelize
class ChainCoreum : BaseChain(), Parcelable {

    override var name: String = "Coreum"
    override var tag: String = "coreum990"
    override var apiName: String = "coreum"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/990'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(990, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_GRPC
    override var stakeDenom: String = "ucore"
    override var accountPrefix: String = "core"
    override var grpcHost: String = "grpc-coreum.cosmostation.io"
    override var lcdUrl: String = "https://lcd-coreum.cosmostation.io/"

    override fun cosmosFetcher(): CosmosFetcher? {
        if (cosmosFetcher == null) {
            cosmosFetcher = CoreumFetcher(this)
        }
        return cosmosFetcher
    }

    fun coreumFetcher(): CoreumFetcher? {
        if (cosmosFetcher == null) {
            cosmosFetcher = CoreumFetcher(this)
        }
        return cosmosFetcher as? CoreumFetcher
    }
}