package com.fulldive.wallet.interactors.accounts

import com.fulldive.wallet.di.modules.DefaultInteractorsModule
import com.fulldive.wallet.extensions.combine
import com.fulldive.wallet.extensions.safeSingle
import com.fulldive.wallet.extensions.singleCallable
import com.fulldive.wallet.interactors.secret.SecretInteractor
import com.fulldive.wallet.models.local.AccountSecrets
import com.fulldive.wallet.rx.AppSchedulers
import com.joom.lightsaber.ProvidedBy
import io.reactivex.Completable
import io.reactivex.Single
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.dao.Account
import java.util.*
import javax.inject.Inject

@ProvidedBy(DefaultInteractorsModule::class)
class AccountsInteractor @Inject constructor(
    private val accountsRepository: AccountsRepository,
    private val secretInteractor: SecretInteractor
) {

    fun getAccount(accountId: Long): Single<Account> {
        return accountsRepository.getSelectAccount(accountId)
    }

    fun getSelectedAccount(): Single<Account> {
        return accountsRepository.getSelectedAccount()
    }

    fun getAccounts(): Single<List<Account>> {
        return accountsRepository.getAccounts()
    }

    // TODO: migrate to rx
    fun getAccountsByChain(chain: BaseChain): List<Account> {
        return accountsRepository.getAccountsByChain(chain)
    }

    fun selectAccountForAddress(address: String): Completable {
        return accountsRepository
            .getAccountsByAddress(address)
            .flatMapCompletable { accounts ->
                if (accounts.isEmpty()) {
                    Completable.error(AccountsListEmptyException())
                } else {
                    accountsRepository.selectAccount(accounts[0].id)
                }
            }
    }


    fun selectAccount(): Single<Account> {
        return getAccounts()
            .flatMap { accounts ->
                if (accounts.isEmpty()) {
                    accountsRepository
                        .selectAccount(-1)
                        .andThen(Single.error(AccountsListEmptyException()))
                } else {
                    accountsRepository
                        .getSortedChains()
                        .flatMap { sortedChains ->
                            safeSingle {
                                var account: Account = accounts[0]
                                for (chain in sortedChains) {
                                    val foundAccount = accounts
                                        .find { chain.hasChainName(it.baseChain) }
                                    if (foundAccount != null) {
                                        account = foundAccount
                                        break
                                    }
                                }
                                account
                            }
                        }
                        .onErrorReturnItem(accounts[0])
                        .flatMap { account ->
                            accountsRepository
                                .selectAccount(account.id)
                                .toSingleDefault(account)
                        }
                }
            }
    }

    fun createAccount(chain: BaseChain, accountSecrets: AccountSecrets): Completable {
        return singleCallable { UUID.randomUUID().toString() }
            .flatMap { uuid ->
                secretInteractor.encryptData(uuid, accountSecrets.entropy)
                    .map { encryptData ->
                        Account(
                            uuid,
                            accountSecrets.address,
                            chain.chain,
                            true,
                            encryptData.encDataString,
                            encryptData.ivDataString,
                            true,
                            accountSecrets.mnemonic.size,
                            System.currentTimeMillis(),
                            accountSecrets.path,
                            accountSecrets.customPath
                        )
                    }
            }
            .flatMap(accountsRepository::addAccount)
            .flatMapCompletable(accountsRepository::selectAccount)
            .andThen(showChain(chain.chain))
            .andThen(selectChain(chain.chain))
    }

    fun createEmptyAccount(chain: BaseChain, address: String): Completable {
        return singleCallable {
            val uuid = UUID.randomUUID().toString()
            Account(
                uuid,
                address.lowercase(),
                chain.chain,
                System.currentTimeMillis()
            )
        }
            .flatMap(accountsRepository::addAccount)
            .flatMapCompletable(accountsRepository::selectAccount)
            .andThen(showChain(chain.chain))
            .andThen(selectChain(chain.chain))
    }

    private fun selectChain(chain: String): Completable {
        return accountsRepository.selectChain(chain)
    }

    private fun showChain(chain: String): Completable {
        return accountsRepository
            .getHiddenChains()
            .flatMapCompletable { items ->
                val index = items.indexOfFirst { it.chain == chain }
                if (index >= 0) {
                    singleCallable {
                        items.toMutableList().apply {
                            removeAt(index)
                        }
                    }
                        .flatMapCompletable(accountsRepository::setHiddenChains)
                } else {
                    Completable.complete()
                }
            }
    }

    fun deleteAccount(accountId: Long): Completable {
        return Single.zip(
            getSelectedAccount(),
            getAccount(accountId),
            ::combine
        )
            .flatMapCompletable { (selectedAccount, account) ->
                deleteAccount(account)
                    .andThen(
                        if (selectedAccount.id.equals(account.id)) {
                            selectAccount().ignoreElement()
                        } else {
                            Completable.complete()
                        }
                    )
            }
    }

    fun upgradeAccountAddressForPath(): Completable {
        return accountsRepository.upgradeAccountAddressForPath()
    }

    fun checkExistsPassword(): Single<Boolean> {
        return accountsRepository.checkExistsPassword()
    }

    private fun deleteAccount(account: Account): Completable {
        return Completable.merge(
            listOf(
                secretInteractor.deleteMnemonicKey(account.uuid).onErrorComplete()
                    .subscribeOn(AppSchedulers.io()),
                secretInteractor.deletePrivateKey(account.uuid).onErrorComplete()
                    .subscribeOn(AppSchedulers.io()),
                accountsRepository.deleteAccount(account.id).onErrorComplete()
                    .subscribeOn(AppSchedulers.io())
            )
        )
    }

}