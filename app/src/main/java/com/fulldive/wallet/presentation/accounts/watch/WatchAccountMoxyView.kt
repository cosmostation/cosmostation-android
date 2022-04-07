package com.fulldive.wallet.presentation.accounts.watch

import androidx.fragment.app.DialogFragment
import com.fulldive.wallet.presentation.base.BaseMoxyView
import moxy.viewstate.strategy.alias.OneExecution

interface WatchAccountMoxyView : BaseMoxyView {
    @OneExecution
    fun setAddress(address: String)

    @OneExecution
    fun showWaitDialog()

    @OneExecution
    fun hideWaitDialog()

    @OneExecution
    fun showDialog(
        dialogFragment: DialogFragment,
        tag: String,
        cancelable: Boolean
    )

    @OneExecution
    fun showMainActivity()

    @OneExecution
    fun finish()
}
