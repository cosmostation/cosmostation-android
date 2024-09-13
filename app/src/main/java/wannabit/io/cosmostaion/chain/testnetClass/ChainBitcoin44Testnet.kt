package wannabit.io.cosmostaion.chain.testnetClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.common.BaseKey

@Parcelize
class ChainBitcoin44Testnet : ChainBitcoin84Testnet(), Parcelable {

    override var name: String = "BitCoin Testnet"
    override var tag: String = "bitcoin44_T"
    override var logo: Int = R.drawable.chain_bitcoin_testnet
    override var isDefault: Boolean = false
    override var isTestnet: Boolean = true
    override var apiName: String = "bitcoin-testnet"

    override var accountKeyType = AccountKeyType(PubKeyType.BTC_LEGACY, "m/44'/1'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(1, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var coinSymbol: String = "BTC"
    override var coinGeckoId: String = ""
    override var coinLogo: Int = R.drawable.token_btc

    override var mainUrl: String = ""

    override fun setInfoWithPrivateKey(privateKey: ByteArray?) {
        this.privateKey = privateKey
        publicKey = BaseKey.getPubKeyFromPKey(privateKey, accountKeyType.pubkeyType)
        mainAddress = BaseKey.getAddressFromPubKey(publicKey, accountKeyType.pubkeyType, bech32PrefixPattern, pubKeyHash, scriptHash)
    }
}