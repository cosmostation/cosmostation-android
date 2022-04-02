package com.fulldive.wallet.presentation.accounts

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.fulldive.wallet.extensions.unsafeLazy
import com.fulldive.wallet.presentation.base.BaseMvpDialogFragment
import moxy.ktx.moxyPresenter
import wannabit.io.cosmostaion.R

class ShareAccountDialogFragment : BaseMvpDialogFragment(), ShareAccountMoxyView {

    private val address by unsafeLazy {
        arguments?.getString(KEY_ADDRESS)
            ?: throw IllegalStateException("argument address can't be null")
    }

    private val presenter by moxyPresenter {
        ShareAccountPresenter().also {
            it.address = address
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(0))
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_share_type, null)
        view.findViewById<View>(R.id.shareQRCodeButton).setOnClickListener {
            presenter.onShareQRClicked(view.context.applicationContext)
        }
        view.findViewById<View>(R.id.shareTextButton).setOnClickListener {
            presenter.onShareTextClicked(view.context.applicationContext)
            dismiss()
        }
        val builder = AlertDialog.Builder(activity)
        builder.setView(view)
        return builder.create()
    }

    companion object {
        private const val KEY_ADDRESS = "address"
        fun newInstance(address: String): ShareAccountDialogFragment {
            return ShareAccountDialogFragment().apply {
                arguments = bundleOf(
                    KEY_ADDRESS to address
                )
            }
        }
    }
}