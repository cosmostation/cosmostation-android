package com.fulldive.wallet.interactors.chains.okex

import com.fulldive.wallet.di.modules.DefaultRepositoryModule
import com.joom.lightsaber.ProvidedBy
import io.reactivex.Completable
import io.reactivex.Single
import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.network.res.ResNodeInfo
import wannabit.io.cosmostaion.network.res.ResOkAccountInfo
import javax.inject.Inject

@ProvidedBy(DefaultRepositoryModule::class)
class OkexRepository @Inject constructor(
    private val okexLocalSource: OkexLocalSource,
    private val okexRemoteSource: OkexRemoteSource
) {

    fun requestAccount(address: String): Single<ResOkAccountInfo> {
        return okexRemoteSource.requestAccount(address)
    }

    fun setAccount(account: Account): Completable {
        return okexLocalSource.setAccount(account)
    }

    fun updateValidatorsList(): Completable {
        return okexRemoteSource
            .requestValidatorsList()
            .flatMapCompletable(okexLocalSource::setValidators)
    }

    fun updateTickersList(): Completable {
        return okexRemoteSource
            .requestTickersList()
            .flatMapCompletable(okexLocalSource::setTickers)
    }

    fun updateTokensList(): Completable {
        return okexRemoteSource
            .requestTokensList()
            .flatMapCompletable(okexLocalSource::setTokens)
    }

    fun updateAccountBalance(account: Account): Completable {
        return okexRemoteSource
            .requestAccountBalance(account.address)
            .flatMapCompletable { balance ->
                okexLocalSource.setBalance(account.id, balance)
            }
    }

    fun updateUnbonding(account: Account): Completable {
        return okexRemoteSource
            .requestUnbonding(account.address)
            .flatMapCompletable(okexLocalSource::setUnbonding)
    }

    fun updateStakingInfo(account: Account): Completable {
        return okexRemoteSource
            .requestStakingInfo(account.address)
            .flatMapCompletable(okexLocalSource::setStakingInfo)
    }

    fun requestNodeInfo(): Single<ResNodeInfo> {
        return okexRemoteSource.requestNodeInfo()
    }

    fun setNodeInfo(nodeInfo: ResNodeInfo): Completable {
        return okexLocalSource.setNodeInfo(nodeInfo)
    }
}
