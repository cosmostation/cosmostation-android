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
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.dialogResize
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.setChainLogo
import wannabit.io.cosmostaion.databinding.DialogQrEvmBinding

class QrEvmDialog(
    context: Context, private val selectedChain: BaseChain?
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
            selectedChain?.let { chain ->
                ethAddressView.setBackgroundResource(R.drawable.cell_bg)
                ethChainName.text = chain.getChainName() + " Evm Type Address"
                ethAddress.text = chain.evmAddress
                ethChainImg.setChainLogo(chain)

                bitmap = barcodeEncoder.encodeBitmap(
                    chain.evmAddress, BarcodeFormat.QR_CODE, 400, 400, hints
                )
                ethQrImg.setImageBitmap(bitmap)
                ethQrView.radius = context.resources.getDimension(R.dimen.space_12)
                ethQrImg.clipToOutline = true

                chainName.text = chain.getChainName() + " Cosmos Type Address"
                chainImg.setChainLogo(chain)
                addressView.setBackgroundResource(R.drawable.cell_bg)
                address.text = chain.address

                bitmap = barcodeEncoder.encodeBitmap(
                    chain.address, BarcodeFormat.QR_CODE, 400, 400, hints
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
                val clip = ClipData.newPlainText(
                    "address", selectedChain?.evmAddress
                )
                clipboard.setPrimaryClip(clip)
                context.makeToast(R.string.str_msg_address_copied)
            }

            addressView.setOnClickListener {
                val clipboard =
                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("address", selectedChain?.address)
                clipboard.setPrimaryClip(clip)
                context.makeToast(R.string.str_msg_address_copied)
            }

            btnEthShare.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(
                    Intent.EXTRA_TEXT, selectedChain?.evmAddress
                )
                intent.type = "text/plain"

                context.startActivity(Intent.createChooser(intent, "share"))
            }

            btnShare.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, selectedChain?.address)
                intent.type = "text/plain"

                context.startActivity(Intent.createChooser(intent, "share"))
            }
        }
    }
}