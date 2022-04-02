package com.fulldive.wallet.presentation.accounts

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.fulldive.wallet.extensions.getColorCompat
import com.fulldive.wallet.extensions.unsafeLazy
import com.fulldive.wallet.presentation.base.BaseMvpDialogFragment
import moxy.ktx.moxyPresenter
import wannabit.io.cosmostaion.R

class AddAccountDialogFragment : BaseMvpDialogFragment(), AddAccountMoxyView {
    private val chain by unsafeLazy {
        arguments?.getString(KEY_CHAIN).orEmpty()
    }

    private val presenter by moxyPresenter {
        AddAccountPresenter().also {
            it.chain = chain
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(requireContext().getColorCompat(R.color.colorBlack)))
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_add_account, null)
        view.findViewById<View>(R.id.btn_import_key).setOnClickListener {
            presenter.onImportKeyClicked(requireActivity())
        }
        view.findViewById<View>(R.id.btn_import_mnemonic).setOnClickListener {
            presenter.onImportMnemonicClicked(requireActivity())
        }
        view.findViewById<View>(R.id.btn_watch_address).setOnClickListener {
            presenter.onWatchAddressClicked(requireActivity())
        }
        view.findViewById<View>(R.id.btn_create).setOnClickListener {
            presenter.onCreateClicked(requireActivity())
        }

        return AlertDialog.Builder(activity)
            .setView(view)
            .create()
    }

    companion object {
        private const val KEY_CHAIN = "chain"

        fun newInstance(chain: String): AddAccountDialogFragment {
            return AddAccountDialogFragment().apply {
                arguments = bundleOf(
                    KEY_CHAIN to chain
                )
            }
        }
    }
}