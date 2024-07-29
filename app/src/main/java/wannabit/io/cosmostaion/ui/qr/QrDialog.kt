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
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.journeyapps.barcodescanner.BarcodeEncoder
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.dialogResize
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.databinding.DialogQrBinding

class QrDialog(
    context: Context, val selectedChain: BaseChain?
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
                selectedChain?.let { chain ->
                    if (chain.supportCosmos()) {
                        chainName.text = chain.name
                        addressView.setBackgroundResource(R.drawable.cell_bg)
                        address.text = chain.address
                        accountPath.text = chain.getHDPath(account.lastHDPath)

                        if (!chain.isDefault) {
                            chainBadge.text = context.getString(R.string.str_old)
                            when (selectedChain.tag) {
                                "okt996_Keccak" -> {
                                    chainTypeBadge.text =
                                        context.getString(R.string.str_ethsecp256k1)
                                    chainTypeBadge.visibility = View.VISIBLE
                                }

                                "okt996_Secp" -> {
                                    chainTypeBadge.text = context.getString(R.string.str_secp256k1)
                                    chainTypeBadge.visibility = View.VISIBLE
                                }

                                else -> {
                                    chainTypeBadge.visibility = View.GONE
                                }
                            }

                        } else {
                            chainBadge.visibility = View.GONE
                            chainTypeBadge.visibility = View.GONE
                        }
                        chainImg.setImageResource(chain.logo)

                        bitmap = barcodeEncoder.encodeBitmap(
                            chain.address, BarcodeFormat.QR_CODE, 1040, 1040, hints
                        )

                        // eth, l2
                    } else {
                        chainName.text = chain.name
                        addressView.setBackgroundResource(R.drawable.cell_bg)
                        address.text = chain.evmAddress
                        accountPath.text = chain.getHDPath(account.lastHDPath)
                        chainBadge.visibility = View.GONE
                        chainTypeBadge.visibility = View.GONE
                        chainImg.setImageResource(chain.logo)

                        bitmap = barcodeEncoder.encodeBitmap(
                            chain.evmAddress, BarcodeFormat.QR_CODE, 540, 540, hints
                        )
                    }
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
                val clip = if (selectedChain?.supportCosmos() == true) {
                    ClipData.newPlainText("address", selectedChain.address)
                } else {
                    ClipData.newPlainText("address", selectedChain?.evmAddress)
                }
                clipboard.setPrimaryClip(clip)
                context.makeToast(R.string.str_msg_address_copied)
            }

            btnShare.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                val address = if (selectedChain?.supportCosmos() == true) {
                    selectedChain.address
                } else {
                    selectedChain?.evmAddress
                }
                intent.putExtra(Intent.EXTRA_TEXT, address)
                intent.type = "text/plain"

                context.startActivity(Intent.createChooser(intent, "share"))
            }
        }
    }
}