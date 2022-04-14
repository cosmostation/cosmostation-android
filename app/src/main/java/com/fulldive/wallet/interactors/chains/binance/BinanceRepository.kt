package com.fulldive.wallet.interactors.chains.binance

import com.fulldive.wallet.di.modules.DefaultRepositoryModule
import com.joom.lightsaber.ProvidedBy
import io.reactivex.Completable
import io.reactivex.Single
import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.dao.Balance
import wannabit.io.cosmostaion.dao.BnbTicker
import wannabit.io.cosmostaion.dao.BnbToken
import wannabit.io.cosmostaion.network.res.ResBnbAccountInfo
import wannabit.io.cosmostaion.network.res.ResBnbFee
import wannabit.io.cosmostaion.network.res.ResNodeInfo
import javax.inject.Inject

@ProvidedBy(DefaultRepositoryModule::class)
class BinanceRepository @Inject constructor(
    private val binanceLocalSource: BinanceLocalSource,
    private val binanceRemoteSource: BinanceRemoteSource
) {

    fun requestTokens(limit: Int): Single<List<BnbToken>> {
        return binanceRemoteSource.requestTokens(limit).map { items ->
            items.onEach { token -> token.type = BnbToken.BNB_TOKEN_TYPE_BEP2 }
        }
    }

    fun requestMiniTokens(limit: Int): Single<List<BnbToken>> {
        return binanceRemoteSource.requestMiniTokens(limit).map { items ->
            items.onEach { token -> token.type = BnbToken.BNB_TOKEN_TYPE_MINI }
        }
    }

    fun setTokens(items: List<BnbToken>): Completable {
        return binanceLocalSource.setTokens(items)
    }

    fun requestTickers(): Single<List<BnbTicker>> {
        return binanceRemoteSource.requestTickers()
    }

    fun requestMiniTickers(): Single<List<BnbTicker>> {
        return binanceRemoteSource.requestMiniTickers()
    }

    fun setTickers(items: List<BnbTicker>): Completable {
        return binanceLocalSource.setTickers(items)
    }

    fun requestFees(): Single<List<ResBnbFee>> {
        return binanceRemoteSource.requestFees()
    }

    fun setFees(items: List<ResBnbFee>): Completable {
        return binanceLocalSource.setFees(items)
    }

    fun requestAccount(address: String): Single<ResBnbAccountInfo> {
        return binanceRemoteSource.requestAccount(address)
    }

    fun setAccount(account: Account): Completable {
        return binanceLocalSource.setAccount(account)
    }

    fun setAccountBalances(accountId: Long, balances: List<Balance>): Completable {
        return binanceLocalSource.setAccountBalances(accountId, balances)
    }

    fun requestNodeInfo(): Single<ResNodeInfo> {
        return binanceRemoteSource.requestNodeInfo()
    }

    fun setNodeInfo(nodeInfo: ResNodeInfo): Completable {
        return binanceLocalSource.setNodeInfo(nodeInfo)
    }

}
