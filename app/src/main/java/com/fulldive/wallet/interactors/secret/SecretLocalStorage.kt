package com.fulldive.wallet.interactors.secret

import com.fulldive.wallet.di.modules.DefaultLocalStorageModule
import com.fulldive.wallet.extensions.safeSingle
import com.joom.lightsaber.ProvidedBy
import io.reactivex.Single
import wannabit.io.cosmostaion.base.BaseData
import wannabit.io.cosmostaion.dao.Password
import javax.inject.Inject

@ProvidedBy(DefaultLocalStorageModule::class)
class SecretLocalStorage @Inject constructor(
    private val baseData: BaseData
) {

    fun getSelectedPassword(): Single<Password> {
        return safeSingle {
            baseData.onSelectPassword()
        }
    }
}