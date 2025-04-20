package wannabit.io.cosmostaion.chain.majorClass

import android.content.Context
import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.common.BaseKey

@Parcelize
class ChainBitCoin49 : ChainBitCoin86(), Parcelable {

    override var name: String = "Bitcoin"
    override var tag: String = "bitcoin49"
    override var isDefault: Boolean = false
    override var apiName: String = "bitcoin"

    override var accountKeyType = AccountKeyType(PubKeyType.BTC_NESTED_SEGWIT, "m/49'/0'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(49, true), ChildNumber(0, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var coinSymbol: String = "BTC"

    override var mainUrl: String = "https://rpc-office.cosmostation.io/bitcoin-mainnet"

    override suspend fun setInfoWithPrivateKey(context: Context, privateKey: ByteArray?) {
        this.privateKey = privateKey
        publicKey = BaseKey.getPubKeyFromPKey(privateKey, accountKeyType.pubkeyType)
        mainAddress = BaseKey.getAddressFromPubKey(context, publicKey, accountKeyType.pubkeyType, network = "mainnet")
    }
}