package com.fulldive.wallet.presentation.accounts

import android.graphics.Bitmap
import com.fulldive.wallet.presentation.base.BaseMoxyView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface AccountShowMoxyView : BaseMoxyView {
    @AddToEndSingle
    fun showQRCode(bitmap: Bitmap)
}
