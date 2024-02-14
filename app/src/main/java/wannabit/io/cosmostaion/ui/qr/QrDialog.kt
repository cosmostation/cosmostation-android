package wannabit.io.cosmostaion.ui.qr

import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.core.content.ContextCompat
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.journeyapps.barcodescanner.BarcodeEncoder
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.dialogResize
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.databinding.DialogQrBinding

class QrDialog(
    context: Context, val selectedEvmChain: EthereumLine?, val selectedChain: CosmosLine?
) : Dialog(context, R.style.CustomDialogTheme) {

    private lateinit var binding: DialogQrBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogQrBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        context.dialogResize(this, 1f, 0.75f)

        initData()
        setUpClickAction()
    }

    private fun initData() {
        var bitmap: Bitmap?
        val hints = mutableMapOf<EncodeHintType, Int>()
        hints[EncodeHintType.MARGIN] = 0
        val barcodeEncoder = BarcodeEncoder()

        binding.apply {
            BaseData.baseAccount?.let { account ->
                selectedEvmChain?.let { evmChain ->
                    chainName.text = evmChain.name
                    addressView.setBackgroundResource(R.drawable.cell_bg)
                    address.text = evmChain.address
                    accountName.text = "(" + account.name + ")"
                    accountPath.text = evmChain.getHDPath(account.lastHDPath)
                    chainBadge.visibility = View.GONE
                    chainImg.setImageResource(evmChain.logo)

                    bitmap = barcodeEncoder.encodeBitmap(
                        evmChain.address, BarcodeFormat.QR_CODE, 540, 540, hints
                    )
                    qrImg.setImageBitmap(bitmap)
                    qrView.radius = context.resources.getDimension(R.dimen.space_12)
                    qrImg.clipToOutline = true
                }

                selectedChain?.let { chain ->
                    chainName.text = chain.name
                    addressView.setBackgroundResource(R.drawable.cell_bg)
                    address.text = chain.address
                    accountName.text = "(" + account.name + ")"
                    accountPath.text = chain.getHDPath(account.lastHDPath)

                    if (chain.evmCompatible) {
                        chainBadge.text = context.getString(R.string.str_evm)
                        chainBadge.setBackgroundResource(R.drawable.round_box_evm)
                        chainBadge.setTextColor(
                            ContextCompat.getColor(
                                context, R.color.color_base01
                            )
                        )
                    } else if (!chain.isDefault) {
                        chainBadge.text = context.getString(R.string.str_deprecated)
                        chainBadge.setBackgroundResource(R.drawable.round_box_deprecated)
                        chainBadge.setTextColor(
                            ContextCompat.getColor(
                                context, R.color.color_base02
                            )
                        )
                    } else {
                        chainBadge.visibility = View.GONE
                    }
                    chainImg.setImageResource(chain.logo)

                    bitmap = barcodeEncoder.encodeBitmap(
                        chain.address, BarcodeFormat.QR_CODE, 540, 540, hints
                    )
                    qrImg.setImageBitmap(bitmap)
                    qrView.radius = context.resources.getDimension(R.dimen.space_12)
                    qrImg.clipToOutline = true
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnClose.setOnClickListener {
                this@QrDialog.dismiss()
            }

            addressView.setOnClickListener {
                val clipboard =
                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                selectedEvmChain?.let { evmChain ->
                    val clip = ClipData.newPlainText("address", evmChain.address)
                    clipboard.setPrimaryClip(clip)
                }
                selectedChain?.let { chain ->
                    val clip = ClipData.newPlainText("address", chain.address)
                    clipboard.setPrimaryClip(clip)
                }
                context.makeToast(R.string.str_msg_address_copied)
            }

            btnShare.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                selectedEvmChain?.let { evmChain ->
                    intent.putExtra(Intent.EXTRA_TEXT, evmChain.address)
                }
                selectedChain?.let { chain ->
                    intent.putExtra(Intent.EXTRA_TEXT, chain.address)
                }
                intent.type = "text/plain"

                context.startActivity(Intent.createChooser(intent, "share"))
            }
        }
    }
}