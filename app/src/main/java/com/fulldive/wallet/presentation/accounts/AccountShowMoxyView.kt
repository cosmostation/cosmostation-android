package com.fulldive.wallet.presentation.accounts

import android.graphics.Bitmap
import com.fulldive.wallet.presentation.base.BaseMoxyView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

interface AccountShowMoxyView : BaseMoxyView {
    @AddToEndSingle
    fun showQRCode(bitmap: Bitmap)

    @OneExecution
    fun dismiss()
}
