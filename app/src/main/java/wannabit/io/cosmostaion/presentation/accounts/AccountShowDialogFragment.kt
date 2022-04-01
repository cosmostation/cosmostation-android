package wannabit.io.cosmostaion.presentation.accounts

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
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.utils.toBitmap

class AccountShowDialogFragment : DialogFragment() {

    private val title by lazy(LazyThreadSafetyMode.NONE) {
        arguments?.getString(KEY_TITLE)
            ?: throw IllegalStateException("argument title can't be null")
    }

    private val address by lazy(LazyThreadSafetyMode.NONE) {
        arguments?.getString(KEY_ADDRESS)
            ?: throw IllegalStateException("argument address can't be null")
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

        try {
            view
                .findViewById<ImageView>(R.id.wallet_address_qr)
                .setImageBitmap(generateQRCode())
        } catch (e: WriterException) {
            e.printStackTrace()
        }

        val builder = AlertDialog.Builder(activity)
        builder.setView(view)
        return builder.create()
    }

    private fun copyAddressToClipboard() {
        (requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)
            .setPrimaryClip(ClipData.newPlainText("address", address))
        Toast.makeText(activity, R.string.str_copied, Toast.LENGTH_SHORT).show()
    }

    private fun generateQRCode(): Bitmap {
        return QRCodeWriter()
            .encode(address, BarcodeFormat.QR_CODE, 480, 480)
            .toBitmap()
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