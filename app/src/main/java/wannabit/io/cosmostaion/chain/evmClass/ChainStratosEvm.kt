package wannabit.io.cosmostaion.chain.evmClass

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
class ChainStratosEvm : BaseChain(), Parcelable {

    override var name: String = "Stratos"
    override var tag: String = "stratos60"
    override var logo: Int = R.drawable.chain_stratos
    override var apiName: String = "stratos"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var stakeDenom: String = "wei"
    override var accountPrefix: String = "st"
    override var grpcHost: String = "grpc.thestratos.org"
    override var lcdUrl: String = "https://rest.thestratos.org/"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "STOS"
    override var coinGeckoId: String = "stratos"
    override var coinLogo: Int = R.drawable.token_stos
    override var addressLogo: Int = R.drawable.icon_ethereum_address
    override var evmRpcURL: String = "https://web3-rpc.thestratos.org"
}