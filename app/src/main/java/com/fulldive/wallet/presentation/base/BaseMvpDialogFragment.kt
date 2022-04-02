package com.fulldive.wallet.presentation.base

import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import com.fulldive.wallet.extensions.toast
import moxy.MvpAppCompatDialogFragment

abstract class BaseMvpDialogFragment : MvpAppCompatDialogFragment() {

    open fun showMessage(@StringRes resourceId: Int) {
        context?.toast(resourceId)
    }

    open fun showMessage(message: String) {
        context?.toast(message)
    }

    open fun showDialog(
        dialogFragment: DialogFragment,
        tag: String = "dialog",
        cancelable: Boolean = true
    ) {
        dialogFragment.isCancelable = cancelable
        parentFragmentManager // maybe we have to use  getChildFragmentManager()
            .beginTransaction()
            .add(dialogFragment, tag)
            .commitNowAllowingStateLoss()
    }
}