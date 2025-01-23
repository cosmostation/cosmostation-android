package wannabit.io.cosmostaion.chain.cosmosClass

import android.content.Context
import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.common.ByteUtils

@Parcelize
open class ChainOkt996Keccak : ChainOktEvm(), Parcelable {

    override var name: String = "OKT"
    override var tag: String = "okt996_Keccak"
    override var logo: Int = R.drawable.chain_okt
    override var isDefault: Boolean = false
    override var apiName: String = "okc"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/996'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(996, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var stakeDenom: String = "okt"
    override var accountPrefix: String = "ex"
    override var lcdUrl: String = "https://exchainrpc.okex.org/okexchain/v1/"

    override var supportEvm: Boolean = false

    override suspend fun setInfoWithPrivateKey(context: Context, privateKey: ByteArray?) {
        this.privateKey = privateKey
        publicKey = BaseKey.getPubKeyFromPKey(privateKey, accountKeyType.pubkeyType)
        evmAddress =
            BaseKey.getAddressFromPubKey(context, publicKey, accountKeyType.pubkeyType, accountPrefix)
        address = ByteUtils.convertEvmToBech32(evmAddress, accountPrefix)
    }
}

const val OKT_BASE_FEE = "0.008000000000000000"
const val OKT_GECKO_ID = "oec-token"