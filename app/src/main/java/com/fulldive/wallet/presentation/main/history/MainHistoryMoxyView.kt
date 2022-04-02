package com.fulldive.wallet.presentation.main.history

import com.fulldive.wallet.presentation.base.BaseMoxyView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution
import wannabit.io.cosmostaion.model.type.BnbHistory
import wannabit.io.cosmostaion.network.res.ResApiNewTxListCustom
import wannabit.io.cosmostaion.network.res.ResOkHistoryHit

interface MainHistoryMoxyView : BaseMoxyView {
    @AddToEndSingle
    fun showBinanceItems(items: List<BnbHistory>)

    @AddToEndSingle
    fun showOkItems(items: List<ResOkHistoryHit>)

    @AddToEndSingle
    fun showItems(items: List<ResApiNewTxListCustom>)

    @OneExecution
    fun hideProgress()
}
