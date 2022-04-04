package com.fulldive.wallet.interactors.accounts

import com.fulldive.wallet.di.modules.DefaultRepositoryModule
import com.joom.lightsaber.ProvidedBy
import io.reactivex.Completable
import io.reactivex.Single
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.dao.Account
import javax.inject.Inject

@ProvidedBy(DefaultRepositoryModule::class)
class AccountsRepository @Inject constructor(
    private val accountsLocalStorage: AccountsLocalStorage
) {

    fun getAccounts(): Single<List<Account>> {
        return accountsLocalStorage.getAccounts()
    }

    fun getSelectAccount(accountId: Long): Single<Account> {
        return accountsLocalStorage.getAccount(accountId)
    }

    fun getSelectedAccount(): Single<Account> {
        return accountsLocalStorage.getSelectedAccount()
            .flatMap(::getSelectAccount)
    }

    fun getSortedChains(): Single<List<BaseChain>> {
        return accountsLocalStorage.getSortedChains()
    }

    fun selectAccount(id: Long): Completable {
        return accountsLocalStorage.selectAccount(id)
    }

    fun deleteAccount(accountId: Long): Completable {
        return accountsLocalStorage.deleteAccount(accountId)
    }
}