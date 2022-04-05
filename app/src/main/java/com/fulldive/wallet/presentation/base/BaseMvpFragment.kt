package com.fulldive.wallet.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.fulldive.wallet.di.IEnrichableActivity
import com.fulldive.wallet.di.IInjectorHolder
import com.fulldive.wallet.extensions.toast
import com.joom.lightsaber.Injector
import moxy.MvpAppCompatFragment
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.utils.WLog

abstract class BaseMvpFragment<VB : ViewBinding> : MvpAppCompatFragment(), IInjectorHolder {

    private val appInjector: Injector
        get() = (activity as IEnrichableActivity).appInjector

    protected var binding: VB? = null

    override fun getInjector() = appInjector

    abstract fun getViewBinding(): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return try {
            getViewBinding().also { binding = it }.root
        } catch (ex: Exception) {
            WLog.e(ex.toString())
            throw ex
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    protected fun binding(viewBinding: VB.() -> Unit) {
        binding?.apply { viewBinding() }
    }

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
        parentFragmentManager
            .beginTransaction()
            .add(dialogFragment, tag)
            .commitNowAllowingStateLoss()
    }

    @Deprecated("It's soooo bad hack. Taket from Base Fragment")
    protected open fun getBaseActivity(): BaseActivity? {
        return activity as? BaseActivity
    }
}