package com.fulldive.wallet.presentation.lockscreen

import com.fulldive.wallet.presentation.base.BaseMoxyView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

interface CheckPasswordMoxyView : BaseMoxyView {
    @OneExecution
    fun showWaitDialog()

    @OneExecution
    fun hideWaitDialog()

    @OneExecution
    fun shakeView()

    @AddToEndSingle
    fun switchKeyboard(type: KeyboardType)

    @AddToEndSingle
    fun updatePasswordField(inputLength: Int)

    @OneExecution
    fun clear()

    @OneExecution
    fun finishWithResult(resultCode: Int)
}
