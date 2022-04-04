package com.fulldive.wallet.interactors.secret

import com.fulldive.wallet.di.modules.DefaultInteractorsModule
import com.fulldive.wallet.extensions.safeCompletable
import com.joom.lightsaber.ProvidedBy
import io.reactivex.Completable
import wannabit.io.cosmostaion.crypto.CryptoHelper
import javax.inject.Inject

@ProvidedBy(DefaultInteractorsModule::class)
class SecretInteractor @Inject constructor(
    private val secretRepository: SecretRepository
) {

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
                        throw InvalidPasswordException();
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

    companion object {
        private const val PASSWORD_KEY = "PASSWORD_KEY"
        private const val PRIVATE_KEY = "PRIVATE_KEY"
        private const val MNEMONIC_KEY = "MNEMONIC_KEY"
    }
}