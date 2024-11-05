package wannabit.io.cosmostaion.chain.testnetClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.fetcher.CosmosFetcher
import wannabit.io.cosmostaion.chain.fetcher.InitiaFetcher

@Parcelize
class ChainInitiaTestnet : BaseChain(), Parcelable {

    override var name: String = "Initia Testnet"
    override var tag: String = "initia_T"
    override var logo: Int = R.drawable.chain_initia_testnet
    override var isTestnet: Boolean = true
    override var apiName: String = "initia-testnet"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_GRPC
    override var stakeDenom: String = "uinit"
    override var accountPrefix: String = "init"
    override var grpcHost: String = "grpc-office-initia-2.cosmostation.io"
    override var lcdUrl: String = "https://lcd-office.cosmostation.io/initia-2-testnet/"

    override fun cosmosFetcher(): CosmosFetcher? {
        if (cosmosFetcher == null) {
            cosmosFetcher = InitiaFetcher(this)
        }
        return cosmosFetcher
    }

    fun initiaFetcher(): InitiaFetcher? {
        if (cosmosFetcher == null) {
            cosmosFetcher = InitiaFetcher(this)
        }
        return cosmosFetcher as? InitiaFetcher
    }
}