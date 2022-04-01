package com.fulldive.wallet.presentation.accounts

import android.content.Intent
import com.fulldive.wallet.presentation.base.BaseMoxyView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface ShareAccountMoxyView : BaseMoxyView {
    @AddToEndSingle
    fun startActivity(intent: Intent)

    @AddToEndSingle
    fun dismiss()
}
