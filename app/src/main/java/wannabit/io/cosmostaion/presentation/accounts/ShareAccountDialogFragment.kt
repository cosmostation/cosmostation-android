package wannabit.io.cosmostaion.presentation.accounts

import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.utils.toBitmap

class ShareAccountDialogFragment : DialogFragment() {

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
        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_share_type, null)
        view.findViewById<View>(R.id.shareQRCodeButton).setOnClickListener {
            shareAddress(false, address)
            dialog?.dismiss()
        }
        view.findViewById<View>(R.id.shareTextButton).setOnClickListener {
            shareAddress(true, address)
            dialog?.dismiss()
        }
        val builder = AlertDialog.Builder(activity)
        builder.setView(view)
        return builder.create()
    }

    private fun shareAddress(isText: Boolean, address: String?) {
        if (isText) {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.putExtra(Intent.EXTRA_TEXT, address)
            shareIntent.type = "text/plain"
            startActivity(Intent.createChooser(shareIntent, "send"))
        } else {
            try {
                val context = requireContext()
                val contentResolver = context.contentResolver ?: return
                val mBitmap = QRCodeWriter()
                    .encode(address, BarcodeFormat.QR_CODE, 480, 480)
                    .toBitmap()
                TedPermission(context)
                    .setPermissionListener(object : PermissionListener {
                        override fun onPermissionGranted() {
                            try {
                                val values = ContentValues()
                                values.put(MediaStore.Images.Media.TITLE, address)
                                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                                val uri = contentResolver.insert(
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                    values
                                ) ?: return
                                val outstream = contentResolver.openOutputStream(uri) ?: return
                                mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outstream)
                                outstream.close()
                                val shareIntent = Intent()
                                shareIntent.action = Intent.ACTION_SEND
                                shareIntent.putExtra(Intent.EXTRA_TEXT, address)
                                shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
                                shareIntent.type = "image/jpeg"
                                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                                startActivity(Intent.createChooser(shareIntent, "send"))
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }

                        override fun onPermissionDenied(deniedPermissions: ArrayList<String>) {
                            Toast.makeText(
                                context,
                                R.string.error_permission,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                    .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .setRationaleMessage(getString(R.string.str_permission_qr))
                    .check()
            } catch (e: WriterException) {
                e.printStackTrace()
            }
        }
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