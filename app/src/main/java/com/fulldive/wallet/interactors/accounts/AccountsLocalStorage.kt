package com.fulldive.wallet.interactors.accounts

import com.fulldive.wallet.di.modules.DefaultLocalStorageModule
import com.fulldive.wallet.extensions.safeCompletable
import com.fulldive.wallet.extensions.safeSingle
import com.joom.lightsaber.ProvidedBy
import io.reactivex.Completable
import io.reactivex.Single
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseData
import wannabit.io.cosmostaion.dao.Account
import javax.inject.Inject

@ProvidedBy(DefaultLocalStorageModule::class)
class AccountsLocalStorage @Inject constructor(
    private val baseData: BaseData
) {

    fun getAccount(accountId: Long): Single<Account> {
        return safeSingle {
            baseData.onSelectAccount("$accountId")
        }
    }

    fun getAccounts(): Single<List<Account>> {
        return safeSingle {
            baseData.onSelectAccounts()
        }
    }

    fun getSelectedAccount(): Single<Long> {
        return safeSingle {
            baseData.lastUserId
        }
    }

    fun getSortedChains(): Single<List<BaseChain>> {
        return safeSingle {
            baseData.dpSortedChains()
        }
    }

    fun selectAccount(id: Long): Completable {
        return safeCompletable {
            baseData.setLastUser(id)
        }
    }

    fun deleteAccount(accountId: Long): Completable {
        return safeCompletable {
            baseData.onDeleteBalance("$accountId")
            baseData.onDeleteAccount("$accountId")
        }
    }
}