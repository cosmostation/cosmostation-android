package com.fulldive.wallet.interactors.secret

import com.fulldive.wallet.di.modules.DefaultRepositoryModule
import com.joom.lightsaber.ProvidedBy
import io.reactivex.Completable
import io.reactivex.Single
import wannabit.io.cosmostaion.dao.Password
import javax.inject.Inject

@ProvidedBy(DefaultRepositoryModule::class)
class SecretRepository @Inject constructor(
    private val secretLocalStorage: SecretLocalStorage
) {

    fun setPassword(password: Password): Completable {
        return secretLocalStorage.setPassword(password)
    }

    fun getPassword(): Single<Password> {
        return secretLocalStorage.getPassword()
    }
}