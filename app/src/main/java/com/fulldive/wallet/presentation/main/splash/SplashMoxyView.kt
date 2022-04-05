package com.fulldive.wallet.presentation.main.splash

import androidx.fragment.app.DialogFragment
import com.fulldive.wallet.presentation.base.BaseMoxyView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

interface SplashMoxyView : BaseMoxyView {
    @OneExecution
    fun showDialog(
        dialogFragment: DialogFragment,
        tag: String,
        cancelable: Boolean
    )

    @AddToEndSingle
    fun showButtonsPanel()

    @OneExecution
    fun showLockScreen()

    @OneExecution
    fun showMainActivity(tabIndex: Int)
}
