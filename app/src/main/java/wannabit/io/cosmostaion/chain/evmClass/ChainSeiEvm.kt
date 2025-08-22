package wannabit.io.cosmostaion.chain.evmClass

import android.content.Context
import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.common.BaseKey

@Parcelize
class ChainSeiEvm : BaseChain(), Parcelable {

    override var name: String = "Sei"
    override var tag: String = "sei60"
    override var apiName: String = "sei"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_GRPC
    override var stakeDenom: String = "usei"
    override var accountPrefix: String = "sei"
    override var grpcHost: String = "grpc-sei.cosmostation.io"
    override var lcdUrl: String = "https://lcd-sei.cosmostation.io/"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "SEI"
    override var evmRpcURL: String = "https://evm-rpc.sei-apis.com"

    override suspend fun setInfoWithPrivateKey(context: Context, privateKey: ByteArray?) {
        this.privateKey = privateKey
        publicKey = BaseKey.getPubKeyFromPKey(privateKey, accountKeyType.pubkeyType)
        address = BaseKey.getAddressFromPubKey(
            context, publicKey, accountKeyType.pubkeyType, accountPrefix()
        )
        evmAddress = BaseKey.getAddressFromPubKey(context, publicKey, PubKeyType.ETH_KECCAK256)
    }
}