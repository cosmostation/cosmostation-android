package wannabit.io.cosmostaion.chain.testnetClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.PubKeyType

@Parcelize
class ChainNeutronTestnet : BaseChain(), Parcelable {

    override var name: String = "Neutron Testnet"
    override var tag: String = "neutron118_T"
    override var logo: Int = R.drawable.chain_neutron_testnet
    override var isTestnet: Boolean = true
    override var apiName: String = "neutron-testnet"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var supportCosmosGrpc: Boolean = true
    override var stakeDenom: String = "untrn"
    override var accountPrefix: String = "neutron"
    override var grpcHost: String = "grpc-office-neutron.cosmostation.io"
}