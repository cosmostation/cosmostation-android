package com.fulldive.wallet.presentation.accounts

import android.app.AlertDialog
import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import com.fulldive.wallet.presentation.base.BaseMvpDialogFragment
import moxy.ktx.moxyPresenter
import wannabit.io.cosmostaion.R

class AccountShowDialogFragment : BaseMvpDialogFragment(), AccountShowMoxyView {

    private val title by lazy(LazyThreadSafetyMode.NONE) {
        arguments?.getString(KEY_TITLE)
            ?: throw IllegalStateException("argument title can't be null")
    }

    private val address by lazy(LazyThreadSafetyMode.NONE) {
        arguments?.getString(KEY_ADDRESS)
            ?: throw IllegalStateException("argument address can't be null")
    }

    private val presenter by moxyPresenter {
        AccountShowPresenter().also {
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
        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_account_show, null)
        view.findViewById<TextView>(R.id.wallet_name).text = title
        view.findViewById<TextView>(R.id.wallet_address_tv).text = address
        view.findViewById<Button>(R.id.shareButton).setOnClickListener {
            onShareType()
            dialog?.dismiss()
        }
        view.findViewById<Button>(R.id.copyButton).setOnClickListener {
            copyAddressToClipboard()
            dialog?.dismiss()
        }

        val builder = AlertDialog.Builder(activity)
        builder.setView(view)
        return builder.create()
    }

    override fun showQRCode(bitmap: Bitmap) {
        dialog
            ?.findViewById<ImageView>(R.id.wallet_address_qr)
            ?.setImageBitmap(bitmap)
    }

    private fun copyAddressToClipboard() {
        (requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)
            .setPrimaryClip(ClipData.newPlainText("address", address))
        showMessage(R.string.str_copied)
    }


    private fun onShareType() {
        val add = ShareAccountDialogFragment.newInstance(address)
        add.isCancelable = true
        parentFragmentManager
            .beginTransaction()
            .add(add, "dialog")
            .commitNowAllowingStateLoss()
    }

    companion object {
        private const val KEY_TITLE = "title"
        private const val KEY_ADDRESS = "address"

        fun newInstance(title: String, address: String): AccountShowDialogFragment {
            return AccountShowDialogFragment()
                .apply {
                    arguments = bundleOf(
                        KEY_TITLE to title,
                        KEY_ADDRESS to address
                    )
                }
        }
    }
}