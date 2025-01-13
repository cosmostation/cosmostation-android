package wannabit.io.cosmostaion.chain.testnetClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin84
import wannabit.io.cosmostaion.common.BaseKey

@Parcelize
open class ChainBitcoin84Testnet : ChainBitCoin84(), Parcelable {

    override var pubKeyHash: Byte = 0x6F
    override var scriptHash: Byte = (-60).toByte()
    override var bech32PrefixPattern: String = "tb"

    override var name: String = "Bitcoin Signet"
    override var tag: String = "bitcoin84_T"
    override var logo: Int = R.drawable.chain_bitcoin_signet
    override var isTestnet: Boolean = true
    override var apiName: String = "bitcoin-testnet"

    override var accountKeyType = AccountKeyType(PubKeyType.BTC_NATIVE_SEGWIT, "m/84'/1'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(84, true), ChildNumber(1, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var coinSymbol: String = "BTC"
    override var coinGeckoId: String = ""
    override var coinLogo: Int = R.drawable.token_btc

    override var mainUrl: String = "https://rpc-office.cosmostation.io/bitcoin-testnet"

    override fun setInfoWithPrivateKey(privateKey: ByteArray?) {
        this.privateKey = privateKey
        publicKey = BaseKey.getPubKeyFromPKey(privateKey, accountKeyType.pubkeyType)
        mainAddress = BaseKey.getAddressFromPubKey(publicKey, accountKeyType.pubkeyType, bech32PrefixPattern, pubKeyHash, scriptHash)
    }
}