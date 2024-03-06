package wannabit.io.cosmostaion.ui.qr

import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.Window
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.journeyapps.barcodescanner.BarcodeEncoder
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.dialogResize
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.databinding.DialogQrEvmBinding

class QrEvmDialog(
    context: Context, private val selectedEvmChain: EthereumLine?
) : Dialog(context, R.style.CustomDialogTheme) {

    private lateinit var binding: DialogQrEvmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogQrEvmBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        context.dialogResize(this, 1f, 0.80f)

        initData()
        setUpClickAction()
    }

    private fun initData() {
        var bitmap: Bitmap?
        val hints = mutableMapOf<EncodeHintType, Int>()
        hints[EncodeHintType.MARGIN] = 0
        val barcodeEncoder = BarcodeEncoder()

        binding.apply {
            selectedEvmChain?.let { evmChain ->
                ethAddressView.setBackgroundResource(R.drawable.cell_bg)
                ethAddress.text = ByteUtils.convertBech32ToEvm(evmChain.address)

                bitmap = barcodeEncoder.encodeBitmap(
                    ByteUtils.convertBech32ToEvm(evmChain.address),
                    BarcodeFormat.QR_CODE,
                    540,
                    540,
                    hints
                )
                ethQrImg.setImageBitmap(bitmap)
                ethQrView.radius = context.resources.getDimension(R.dimen.space_12)
                ethQrImg.clipToOutline = true

                chainLogo.setImageResource(evmChain.addressLogo)
                chainName.text = evmChain.name
                chainImg.setImageResource(evmChain.logo)
                addressView.setBackgroundResource(R.drawable.cell_bg)
                address.text = evmChain.address

                bitmap = barcodeEncoder.encodeBitmap(
                    evmChain.address, BarcodeFormat.QR_CODE, 540, 540, hints
                )
                qrImg.setImageBitmap(bitmap)
                qrView.radius = context.resources.getDimension(R.dimen.space_12)
                qrImg.clipToOutline = true
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            ethAddressView.setOnClickListener {
                val clipboard =
                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                selectedEvmChain?.let { evmChain ->
                    val clip = ClipData.newPlainText(
                        "address", ByteUtils.convertBech32ToEvm(evmChain.address)
                    )
                    clipboard.setPrimaryClip(clip)
                }
                context.makeToast(R.string.str_msg_address_copied)
            }

            addressView.setOnClickListener {
                val clipboard =
                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                selectedEvmChain?.let { evmChain ->
                    val clip = ClipData.newPlainText("address", evmChain.address)
                    clipboard.setPrimaryClip(clip)
                }
                context.makeToast(R.string.str_msg_address_copied)
            }

            btnEthShare.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                selectedEvmChain?.let { evmChain ->
                    intent.putExtra(
                        Intent.EXTRA_TEXT,
                        ByteUtils.convertBech32ToEvm(evmChain.address)
                    )
                }
                intent.type = "text/plain"

                context.startActivity(Intent.createChooser(intent, "share"))
            }

            btnShare.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                selectedEvmChain?.let { evmChain ->
                    intent.putExtra(Intent.EXTRA_TEXT, evmChain.address)
                }
                intent.type = "text/plain"

                context.startActivity(Intent.createChooser(intent, "share"))
            }
        }
    }
}