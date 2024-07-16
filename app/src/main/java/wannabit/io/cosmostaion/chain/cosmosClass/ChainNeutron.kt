package wannabit.io.cosmostaion.chain.cosmosClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.NeutronFetcher
import wannabit.io.cosmostaion.chain.PubKeyType

@Parcelize
open class ChainNeutron : BaseChain(), Parcelable {

    var neutronFetcher: NeutronFetcher? = null

    override var name: String = "Neutron"
    override var tag: String = "neutron118"
    override var logo: Int = R.drawable.chain_neutron
    override var swipeLogo: Int = R.drawable.chain_swipe_neutron
    override var apiName: String = "neutron"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var supportCosmosGrpc: Boolean = true
    override var supportCw20: Boolean = true
    override var stakeDenom: String = "untrn"
    override var supportStaking = false
    override var accountPrefix: String = "neutron"
    override var grpcHost: String = "grpc-neutron.cosmostation.io"

    override fun grpcFetcher(): NeutronFetcher? {
        super.grpcFetcher()
        if (neutronFetcher == null) {
            neutronFetcher = NeutronFetcher(this)
        }
        return neutronFetcher
    }
}

const val NEUTRON_VESTING_CONTRACT_ADDRESS =
    "neutron1h6828as2z5av0xqtlh4w9m75wxewapk8z9l2flvzc29zeyzhx6fqgp648z"

const val NEUTRON_SINGLE_MODULE = 1
const val NEUTRON_MULTI_MODULE = 2
const val NEUTRON_OVERRULE_MODULE = 3