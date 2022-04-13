package com.fulldive.wallet.presentation.security

import com.fulldive.wallet.presentation.base.BaseMoxyView
import com.fulldive.wallet.presentation.system.keyboard.KeyboardType
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

interface SetPasswordMoxyView : BaseMoxyView {
    @OneExecution
    fun showWaitDialog()

    @OneExecution
    fun hideWaitDialog()

    @AddToEndSingle
    fun setCheckPasswordHintVisible(isVisible: Boolean)

    @OneExecution
    fun shakeView()

    @AddToEndSingle
    fun switchKeyboard(type: KeyboardType)

    @AddToEndSingle
    fun updatePasswordField(inputLength: Int)

    @OneExecution
    fun shuffleKeyboard()

    @OneExecution
    fun clear()

    @OneExecution
    fun finishWithResult(resultCode: Int)
}
