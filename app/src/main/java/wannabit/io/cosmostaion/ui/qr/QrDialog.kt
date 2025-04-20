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
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.dialogResize
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.setChainLogo
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
                        chainImg.setChainLogo(chain)

                        bitmap = barcodeEncoder.encodeBitmap(
                            chain.address, BarcodeFormat.QR_CODE, 1040, 1040, hints
                        )

                    } else if (selectedChain is ChainSui || selectedChain is ChainBitCoin86) {
                        chainName.text = chain.name
                        addressView.setBackgroundResource(R.drawable.cell_bg)
                        address.text = chain.mainAddress
                        accountPath.text = chain.getHDPath(account.lastHDPath)
                        chainImg.setChainLogo(chain)

                        if (selectedChain is ChainBitCoin86) {
                            when (selectedChain.accountKeyType.pubkeyType) {
                                PubKeyType.BTC_LEGACY -> {
                                    chainTypeBadge.visibility = View.VISIBLE
                                    chainBadge.visibility = View.GONE
                                    chainTypeBadge.text = context.getString(R.string.str_legacy)
                                    chainBadge.setTextColor(
                                        ContextCompat.getColorStateList(
                                            context,
                                            R.color.color_base02
                                        )
                                    )
                                }

                                PubKeyType.BTC_NESTED_SEGWIT -> {
                                    chainTypeBadge.visibility = View.VISIBLE
                                    chainBadge.visibility = View.GONE
                                    chainTypeBadge.text =
                                        context.getString(R.string.str_nested_segwit)
                                    chainBadge.setTextColor(
                                        ContextCompat.getColorStateList(
                                            context,
                                            R.color.color_base02
                                        )
                                    )
                                }

                                PubKeyType.BTC_NATIVE_SEGWIT -> {
                                    chainBadge.visibility = View.GONE
                                    chainTypeBadge.visibility = View.VISIBLE
                                    chainTypeBadge.text =
                                        context.getString(R.string.str_native_segwit)
                                    chainTypeBadge.setTextColor(
                                        ContextCompat.getColorStateList(
                                            context,
                                            R.color.color_base01
                                        )
                                    )
                                    chainTypeBadge.setBackgroundResource(R.drawable.round_box_bit)
                                }

                                else -> {
                                    chainBadge.visibility = View.GONE
                                    chainTypeBadge.visibility = View.VISIBLE
                                    chainTypeBadge.text = context.getString(R.string.str_taproot)
                                    chainTypeBadge.setTextColor(
                                        ContextCompat.getColorStateList(
                                            context,
                                            R.color.color_base01
                                        )
                                    )
                                    chainTypeBadge.setBackgroundResource(R.drawable.round_box_bit_taproot)
                                }
                            }

                        } else {
                            chainBadge.visibility = View.GONE
                            chainTypeBadge.visibility = View.GONE
                        }

                        bitmap = barcodeEncoder.encodeBitmap(
                            chain.mainAddress, BarcodeFormat.QR_CODE, 540, 540, hints
                        )

                        // eth, l2
                    } else {
                        chainName.text = chain.name
                        addressView.setBackgroundResource(R.drawable.cell_bg)
                        address.text = chain.evmAddress
                        accountPath.text = chain.getHDPath(account.lastHDPath)
                        chainBadge.visibility = View.GONE
                        chainTypeBadge.visibility = View.GONE
                        chainImg.setChainLogo(chain)

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
                } else if (selectedChain is ChainSui || selectedChain is ChainBitCoin86) {
                    ClipData.newPlainText("address", selectedChain.mainAddress)
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
                } else if (selectedChain is ChainSui || selectedChain is ChainBitCoin86) {
                    selectedChain.mainAddress
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