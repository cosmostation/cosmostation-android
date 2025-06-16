package wannabit.io.cosmostaion.common

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import org.bitcoinj.core.Address
import org.bitcoinj.params.MainNetParams
import org.bitcoinj.params.TestNet3Params
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.database.Prefs
import java.util.Locale
import java.util.regex.Pattern

object BaseUtils {

    fun checkPasscodePattern(pinCode: String): Boolean {
        if (pinCode.length != 5) return false
        val regex = "^\\d{4}+[A-Z]{1}$"
        val p = Pattern.compile(regex)
        val m = p.matcher(pinCode)
        return m.matches()
    }

    const val LANGUAGE_ENGLISH = 1
    const val LANGUAGE_KOREAN = 2
    const val LANGUAGE_JAPANESE = 3

    fun updateResources(context: Context?): Context? {
        val locale: Locale = when (Prefs.language) {
            LANGUAGE_ENGLISH -> {
                Locale("en")
            }

            LANGUAGE_KOREAN -> {
                Locale("ko")
            }

            LANGUAGE_JAPANESE -> {
                Locale("ja")
            }

            else -> {
                Resources.getSystem().configuration.locales[0]
            }
        }
        Locale.setDefault(locale)
        val config = Configuration(context?.resources?.configuration)
        config.setLocale(locale)
        return context?.createConfigurationContext(config)
    }

    fun isValidChainAddress(chain: BaseChain?, address: String?): Boolean {
        if (address?.isEmpty() == true) {
            return false
        }
        if (address?.startsWith("0x") == true) {
            if (BaseKey.isValidEthAddress(address)) {
                return true
            }
            return false
        }

        if (!BaseKey.isValidBech32(address)) {
            return false
        }
        if (address?.startsWith(chain?.accountPrefix + 1) == false) {
            return false
        }
        return true
    }

    fun isValidBechAddress(line: BaseChain?, address: String?): Boolean {
        if (address?.isEmpty() == true) {
            return false
        }
        if (address?.startsWith("0x") == true) {
            return false
        }

        if (!BaseKey.isValidBech32(address)) {
            return false
        }
        if (address?.startsWith(line?.accountPrefix + 1) == false) {
            return false
        }
        return true
    }

    fun isValidBitAddress(chain: ChainBitCoin86, address: String): Boolean {
        return try {
            val network = if (chain.isTestnet) {
                TestNet3Params.get()
            } else {
                MainNetParams.get()
            }
            Address.fromString(network, address)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun isValidSuiAddress(address: String?): Boolean {
        val suiPattern = "^(0x)[A-Fa-f0-9]{64}$"
        val p = Pattern.compile(suiPattern)
        val m = p.matcher(address)
        return m.matches()
    }
}