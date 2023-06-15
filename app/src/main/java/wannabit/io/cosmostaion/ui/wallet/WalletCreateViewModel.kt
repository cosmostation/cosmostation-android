package wannabit.io.cosmostaion.ui.wallet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.i2p.crypto.eddsa.Utils
import org.bitcoinj.crypto.MnemonicCode
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.CipherHelper
import wannabit.io.cosmostaion.database.CryptoHelper
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.Wallet
import wannabit.io.cosmostaion.database.model.WalletType
import java.security.SecureRandom
import java.util.Date
import java.util.UUID

class WalletCreateViewModel : ViewModel() {
    val mnemonic = MutableLiveData<List<String>>()
    val generated = MutableLiveData(false)

    fun create(name: String) = CoroutineScope(Dispatchers.IO).launch {
        mnemonic.value?.let { mnemonic ->
            val uuid = UUID.randomUUID().toString()
            val entropy = MnemonicCode.INSTANCE.toEntropy(mnemonic)
            val hex = Utils.bytesToHex(entropy)
            val encryptData = CryptoHelper.doEncryptData(CosmostationConstants.ENCRYPT_MNEMONIC_KEY + uuid, hex, false)
            val seed = MnemonicCode.toSeed(MnemonicCode.INSTANCE.toMnemonic(entropy), "")
            val encSeed = CipherHelper.encrypt(Utils.bytesToHex(seed))
            encryptData?.let { data ->
                data.encDataString?.let { resource ->
                    data.ivDataString?.let { spec ->
                        val wallet = Wallet(0, uuid, resource, spec, encSeed, name, mnemonic.size, WalletType.MNEMONIC, 0, Date().time)
                        val id = AppDatabase.getInstance().walletDao().insert(wallet)
                        Prefs.lastUserId = id
                        generated.postValue(true)
                    }
                }
            }
        }
    }

    fun generateMnemonic() = CoroutineScope(Dispatchers.IO).launch {
        val seed = ByteArray(32)
        SecureRandom().nextBytes(seed)
        mnemonic.postValue(MnemonicCode.INSTANCE.toMnemonic(seed))
    }
}