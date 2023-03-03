package wannabit.io.cosmostaion.dialog

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.databinding.DialogAccountShowBinding

class AccountShowDialog : DialogFragment() {
    private var mBitmap: Bitmap? = null
    private var dialogAccountShowBinding: DialogAccountShowBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window!!.setBackgroundDrawableResource(R.color.colorTrans)
        dialogAccountShowBinding = DialogAccountShowBinding.inflate(inflater, container, false)
        val address = arguments!!.getString("address")
        dialogAccountShowBinding!!.walletName.text = arguments!!.getString("title")
        dialogAccountShowBinding!!.walletAddressTv.text = address
        val qrCodeWriter = QRCodeWriter()
        try {
            mBitmap = toBitmap(qrCodeWriter.encode(address, BarcodeFormat.QR_CODE, 480, 480))
            dialogAccountShowBinding!!.walletAddressQr.setImageBitmap(mBitmap)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
        dialogAccountShowBinding!!.btnNega.setOnClickListener {
            (activity as BaseActivity?)!!.onShareType(arguments!!.getString("address"))
            dismiss()
        }
        dialogAccountShowBinding!!.btnPosi.setOnClickListener {
            val clipboard =
                activity!!.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("address", address)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(activity, R.string.str_copied, Toast.LENGTH_SHORT).show()
            dismiss()
        }
        return dialogAccountShowBinding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialogAccountShowBinding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle?): AccountShowDialog {
            val frag = AccountShowDialog()
            frag.arguments = bundle
            return frag
        }

        private fun toBitmap(matrix: BitMatrix): Bitmap {
            val height = matrix.height
            val width = matrix.width
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bmp.setPixel(x, y, if (matrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
            return bmp
        }
    }
}