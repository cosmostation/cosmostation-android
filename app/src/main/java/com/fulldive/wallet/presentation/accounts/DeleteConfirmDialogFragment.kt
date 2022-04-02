package com.fulldive.wallet.presentation.accounts

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.fulldive.wallet.extensions.unsafeLazy
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.AccountDetailActivity

class DeleteConfirmDialogFragment : DialogFragment() {
    private val accountId by unsafeLazy {
        arguments?.getLong(KEY_ACCOUNT_ID)
            ?: throw IllegalStateException("argument accountId can't be null")
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
        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_delete_confirm, null)
        view.findViewById<Button>(R.id.deleteButton).setOnClickListener {
            (activity as? AccountDetailActivity?)?.onStartDeleteUser(accountId)
            dismiss()
        }
        view.findViewById<Button>(R.id.closeButton).setOnClickListener { dismiss() }
        return AlertDialog.Builder(activity).setView(view).create()
    }

    companion object {
        private const val KEY_ACCOUNT_ID = "accountId"

        fun newInstance(accountId: Long): DeleteConfirmDialogFragment {
            return DeleteConfirmDialogFragment().apply {
                arguments = bundleOf(
                    KEY_ACCOUNT_ID to accountId
                )
            }
        }
    }
}