package com.fulldive.wallet.presentation.base

import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import com.fulldive.wallet.di.IEnrichableActivity
import com.fulldive.wallet.di.IInjectorHolder
import com.fulldive.wallet.extensions.toast
import com.joom.lightsaber.Injector
import moxy.MvpAppCompatDialogFragment

abstract class BaseMvpDialogFragment : MvpAppCompatDialogFragment(), IInjectorHolder {

    private val appInjector: Injector
        get() = (activity as IEnrichableActivity).appInjector

    override fun getInjector() = appInjector

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