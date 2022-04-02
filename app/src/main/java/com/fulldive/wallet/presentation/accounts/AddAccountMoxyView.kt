package com.fulldive.wallet.presentation.accounts

import android.content.Intent
import com.fulldive.wallet.presentation.base.BaseMoxyView
import moxy.viewstate.strategy.alias.OneExecution

interface AddAccountMoxyView : BaseMoxyView {
    @OneExecution
    fun startActivity(intent: Intent)

    @OneExecution
    fun dismiss()
}
