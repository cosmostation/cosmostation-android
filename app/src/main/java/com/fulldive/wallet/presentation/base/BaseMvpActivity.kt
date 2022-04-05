package com.fulldive.wallet.presentation.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.fulldive.wallet.di.IEnrichableActivity
import com.fulldive.wallet.extensions.or
import com.fulldive.wallet.extensions.toast
import com.fulldive.wallet.presentation.system.WaitDialogFragment
import com.fulldive.wallet.presentation.system.WaitDialogFragment.Companion.newInstance
import com.joom.lightsaber.Injector
import moxy.MvpAppCompatActivity
import wannabit.io.cosmostaion.utils.WLog

abstract class BaseMvpActivity<VB : ViewBinding> : MvpAppCompatActivity(), IEnrichableActivity {
    private lateinit var injector: Injector
    private var waitDialogFragment: WaitDialogFragment? = null

    protected var binding: VB? = null

    abstract fun getViewBinding(): VB

    override var appInjector: Injector
        get() = injector
        set(value) {
            injector = value
        }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            try {
                getViewBinding().also { binding = it }.root
            } catch (ex: Exception) {
                WLog.e(ex.toString())
                throw ex
            }
        )
    }

    @CallSuper
    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    open fun showMessage(@StringRes resourceId: Int) {
        toast(resourceId)
    }

    open fun showMessage(message: String) {
        toast(message)
    }

    fun showWaitDialog() {
        val fragment = supportFragmentManager.findFragmentByTag(TAG_WAIT_DIALOG)
        if (fragment == null || !fragment.isAdded) {
            val dialogFragment = waitDialogFragment.or {
                newInstance().also {
                    waitDialogFragment = it
                }
            }
            showDialog(dialogFragment, TAG_WAIT_DIALOG, false)
        }
    }

    fun hideWaitDialog() {
        waitDialogFragment?.dismissAllowingStateLoss()
    }

    fun showDialog(
        dialogFragment: DialogFragment,
        tag: String = "dialog",
        cancelable: Boolean = true
    ) {
        dialogFragment.isCancelable = cancelable
        supportFragmentManager
            .beginTransaction()
            .add(dialogFragment, tag)
            .commitNowAllowingStateLoss()
    }

    protected fun binding(viewBinding: VB.() -> Unit) {
        binding?.apply { viewBinding() }
    }

    companion object {
        private const val TAG_WAIT_DIALOG = "wait"

    }
}