package com.fulldive.wallet.interactors.secret

import com.fulldive.wallet.di.modules.DefaultInteractorsModule
import com.fulldive.wallet.extensions.safeCompletable
import com.fulldive.wallet.extensions.safeSingle
import com.fulldive.wallet.models.local.AccountSecrets
import com.joom.lightsaber.ProvidedBy
import io.reactivex.Completable
import io.reactivex.Single
import org.bitcoinj.crypto.MnemonicCode
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.crypto.CryptoHelper
import wannabit.io.cosmostaion.crypto.EncResult
import java.security.SecureRandom
import javax.inject.Inject

@ProvidedBy(DefaultInteractorsModule::class)
class SecretInteractor @Inject constructor(
    private val secretRepository: SecretRepository
) {

    fun createSecrets(chain: BaseChain): Single<AccountSecrets> {
        return safeSingle {
            val entropy = getEntropy()
            val words = MnemonicCode.INSTANCE.toMnemonic(entropy)
            val customPath = when (chain) {
                BaseChain.KAVA_MAIN,
                BaseChain.SECRET_MAIN,
                BaseChain.LUM_MAIN -> 1
                BaseChain.OKEX_MAIN -> 2
                else -> 0
            }
            val hexEntropy = MnemonicUtils.byteArrayToHexString(entropy)
            val address = MnemonicUtils.createAddress(
                chain,
                hexEntropy,
                0,
                customPath
            )

            AccountSecrets(hexEntropy, words, address, 0, customPath)
        }
    }

    fun checkPassword(
        password: String
    ): Completable {
        return secretRepository
            .getSelectedPassword()
            .flatMapCompletable { selectedPassword ->
                safeCompletable {
                    if (!CryptoHelper.verifyData(
                            password,
                            selectedPassword.resource,
                            PASSWORD_KEY
                        )
                    ) {
                        throw InvalidPasswordException()
                    }
                }
            }
    }

    fun deleteMnemonicKey(uuid: String): Completable {
        return safeCompletable {
            CryptoHelper.deleteKey(MNEMONIC_KEY + uuid)
        }
    }

    fun deletePrivateKey(uuid: String): Completable {
        return safeCompletable {
            CryptoHelper.deleteKey(PRIVATE_KEY + uuid)
        }
    }

    fun encryptData(uuid: String, entropy: String): Single<EncResult> {
        return safeSingle {
            CryptoHelper.doEncryptData(
                MNEMONIC_KEY + uuid, entropy, false
            )
        }
    }

    private fun getEntropy(): ByteArray {
        return ByteArray(ENTROPY_SIZE).also { seeds ->
            SecureRandom().nextBytes(seeds)
        }
    }

    companion object {
        private const val ENTROPY_SIZE = 32

        private const val PASSWORD_KEY = "PASSWORD_KEY"
        private const val PRIVATE_KEY = "PRIVATE_KEY"
        private const val MNEMONIC_KEY = "MNEMONIC_KEY"
    }
}