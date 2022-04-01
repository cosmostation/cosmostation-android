package com.fulldive.wallet.presentation.base

import androidx.annotation.StringRes
import com.fulldive.wallet.extensions.toast
import moxy.MvpAppCompatDialogFragment

abstract class BaseMvpDialogFragment : MvpAppCompatDialogFragment() {

    open fun showMessage(@StringRes resourceId: Int) {
        context?.toast(resourceId)
    }

    open fun showMessage(message: String) {
        context?.toast(message)
    }
}