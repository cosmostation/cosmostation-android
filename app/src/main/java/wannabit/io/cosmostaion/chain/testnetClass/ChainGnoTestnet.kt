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
import wannabit.io.cosmostaion.chain.fetcher.GnoFetcher

@Parcelize
class ChainGnoTestnet : BaseChain(), Parcelable {

    override var name: String = "Gno Testnet"
    override var tag: String = "gno118_T"
    override var isTestnet: Boolean = true
    override var apiName: String = "gno-testnet"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_RPC
    override var stakeDenom: String = "ugnot"
    override var accountPrefix: String = "g"
    override var mainUrl: String = "https://rpc.gno.land"

    fun gnoRpcFetcher(): GnoFetcher? {
        if (gnoRpcFetcher == null) {
            gnoRpcFetcher = GnoFetcher(this)
        }
        return gnoRpcFetcher
    }
}