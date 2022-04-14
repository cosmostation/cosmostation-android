package com.fulldive.wallet.interactors.secret

import com.fulldive.wallet.di.modules.DefaultLocalStorageModule
import com.fulldive.wallet.extensions.safeCompletable
import com.fulldive.wallet.extensions.safeSingle
import com.joom.lightsaber.ProvidedBy
import io.reactivex.Completable
import io.reactivex.Single
import wannabit.io.cosmostaion.base.BaseData
import wannabit.io.cosmostaion.dao.Password
import javax.inject.Inject

@ProvidedBy(DefaultLocalStorageModule::class)
class SecretLocalSource @Inject constructor(
    private val baseData: BaseData
) {

    fun setPassword(password: Password): Completable {
        return safeCompletable {
            val insert: Long = baseData.setPassword(password)
            if (insert < 0) {
                throw SavePasswordException()
            }
        }
    }

    fun getPassword(): Single<Password> {
        return safeSingle {
            baseData.password
        }
    }
}