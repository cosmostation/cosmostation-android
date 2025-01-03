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
class ChainFxcoreEvm : BaseChain(), Parcelable {

    override var name: String = "FUNCTION-X"
    override var tag: String = "function-x60"
    override var logo: Int = R.drawable.chain_fxcore
    override var apiName: String = "fxcore"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var stakeDenom: String = "FX"
    override var accountPrefix: String = "fx"
    override var grpcHost: String = ""
    override var lcdUrl: String = "https://fx-rest.functionx.io/"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "FX"
    override var coinGeckoId: String = "fx-coin"
    override var coinLogo: Int = R.drawable.token_fx
    override var addressLogo: Int = R.drawable.icon_ethereum_address
    override var evmRpcURL: String = "https://fx-json-web3.functionx.io:8545/"
}