package com.fulldive.wallet.interactors.chains.okex

import com.fulldive.wallet.di.modules.DefaultLocalStorageModule
import com.fulldive.wallet.extensions.or
import com.fulldive.wallet.extensions.safeCompletable
import com.joom.lightsaber.ProvidedBy
import io.reactivex.Completable
import wannabit.io.cosmostaion.base.BaseData
import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.dao.Balance
import wannabit.io.cosmostaion.model.type.Validator
import wannabit.io.cosmostaion.network.res.*
import java.math.BigDecimal
import javax.inject.Inject

@ProvidedBy(DefaultLocalStorageModule::class)
class OkexLocalSource @Inject constructor(
    private val baseData: BaseData
) {

    fun setValidators(items: List<Validator>): Completable {
        return safeCompletable {
            baseData.mAllValidators = items

            baseData.mTopValidators = items.filter { it.status == Validator.BONDED }
            baseData.mOtherValidators = items.filter { it.status != Validator.BONDED }
            baseData.mMyValidators.clear() // TODO: remove
        }
    }

    fun setTickers(tickers: ResOkTickersList): Completable {
        return safeCompletable {
            baseData.mOkTickersList = tickers
        }
    }

    fun setTokens(tokens: ResOkTokenList): Completable {
        return safeCompletable {
            baseData.mOkTokenList = tokens
        }
    }

    fun setUnbonding(unbonding: ResOkUnbonding): Completable {
        return safeCompletable {
            baseData.mOkUnbonding = unbonding
        }
    }

    fun setStakingInfo(stakingInfo: ResOkStaking): Completable {
        return safeCompletable {
            baseData.mOkStaking = stakingInfo
            baseData.mMyValidators.clear() // TODO: remove
        }
    }

    fun setBalance(accountId: Long, accountToken: ResOkAccountToken): Completable {
        return safeCompletable {
            baseData.updateBalances(accountId,
                accountToken
                    .data.currencies
                    ?.map { currency ->
                        Balance().apply {
                            this.accountId = accountId
                            symbol = currency.symbol
                            balance = BigDecimal(currency.available)
                            locked = BigDecimal(currency.locked)
                            fetchTime = System.currentTimeMillis()
                        }
                    }
                    .or(emptyList())
            )
        }
    }

    fun setNodeInfo(nodeInfo: ResNodeInfo): Completable {
        return safeCompletable {
            baseData.mNodeInfo = nodeInfo.node_info
        }
    }

    fun setAccount(account: Account): Completable {
        return safeCompletable {
            baseData.updateAccount(account)
        }
    }
}