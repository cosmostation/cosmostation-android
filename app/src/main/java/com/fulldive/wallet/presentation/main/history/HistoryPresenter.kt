package com.fulldive.wallet.presentation.main.history

import android.content.Context
import com.fulldive.wallet.extensions.withDefaults
import com.fulldive.wallet.interactors.HistoryInteractor
import com.fulldive.wallet.presentation.base.BaseMoxyPresenter
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.utils.WDp

class MainHistoryPresenter(
    context: Context
) : BaseMoxyPresenter<MainHistoryMoxyView>() {
    private val historyInteractor = HistoryInteractor(context)
    private val historyErrorConsumer = object : OnErrorConsumer() {
        override fun onError(error: Throwable) {
            logError(error)
            viewState.hideProgress()
        }
    }

    fun onFetchHistory(account: Account, chain: BaseChain) {
        when (chain) {
            BaseChain.BNB_MAIN -> {
                historyInteractor
                    .getBinanceAccountTxHistory(
                        account.address,
                        WDp.threeMonthAgoTimeString(),
                        WDp.cTimeString()
                    )
                    .withDefaults()
                    .compositeSubscribe(
                        onSuccess = { items ->
                            viewState.showBinanceItems(items)
                        },
                        onError = historyErrorConsumer
                    )
            }
            BaseChain.OKEX_MAIN -> {
                historyInteractor
                    .getOkAccountTxHistory(account.address)
                    .withDefaults()
                    .compositeSubscribe(
                        onSuccess = { items ->
                            viewState.showOkItems(items)
                        },
                        onError = historyErrorConsumer
                    )
            }
            else -> {
                historyInteractor
                    .getAccountTxHistory(chain, account.address)
                    .withDefaults()
                    .compositeSubscribe(
                        onSuccess = { items ->
                            viewState.showItems(items)
                        },
                        onError = historyErrorConsumer
                    )
            }
        }
    }
}