package com.fulldive.wallet.interactors.chains.binance

import com.fulldive.wallet.di.modules.DefaultLocalStorageModule
import com.fulldive.wallet.extensions.safeCompletable
import com.joom.lightsaber.ProvidedBy
import io.reactivex.Completable
import wannabit.io.cosmostaion.base.BaseData
import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.dao.Balance
import wannabit.io.cosmostaion.dao.BnbTicker
import wannabit.io.cosmostaion.dao.BnbToken
import wannabit.io.cosmostaion.network.res.ResBnbFee
import wannabit.io.cosmostaion.network.res.ResNodeInfo
import javax.inject.Inject

@ProvidedBy(DefaultLocalStorageModule::class)
class BinanceLocalSource @Inject constructor(
    private val baseData: BaseData
) {
    fun setTokens(items: List<BnbToken>): Completable {
        return safeCompletable {
            baseData.mBnbTokens = items
        }
    }

    fun setTickers(items: List<BnbTicker>): Completable {
        return safeCompletable {
            baseData.mBnbTickers = items
        }
    }

    fun setFees(items: List<ResBnbFee>): Completable {
        return safeCompletable {
            baseData.mBnbFees = items
        }
    }

    fun setAccount(account: Account): Completable {
        return safeCompletable {
            baseData.updateAccount(account)
        }
    }

    fun setAccountBalances(accountId: Long, balances: List<Balance>): Completable {
        return safeCompletable {
            baseData.updateBalances(accountId, balances)
        }
    }

    fun setNodeInfo(nodeInfo: ResNodeInfo): Completable {
        return safeCompletable {
            baseData.mNodeInfo = nodeInfo.node_info
        }
    }
}