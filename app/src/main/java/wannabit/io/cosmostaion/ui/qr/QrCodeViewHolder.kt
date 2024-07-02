package wannabit.io.cosmostaion.ui.qr

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.journeyapps.barcodescanner.BarcodeEncoder
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.ItemQrBinding

class QrCodeViewHolder(
    val context: Context,
    private val binding: ItemQrBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun evmBind(account: BaseAccount, selectChain: EthereumLine) {
        binding.apply {
            receiveView.setBackgroundResource(R.drawable.item_bg)
            if (account.type == BaseAccountType.MNEMONIC) {
                accountPath.text = selectChain.getHDPath(account.lastHDPath)
            } else {
                accountPathLayout.visibility = View.GONE
            }

            receiveTitle.text =
                context.getString(R.string.str_deposit_caution_msg, selectChain.name + " EVM")
            chainImg.setImageResource(selectChain.logo)
            val address = if (selectChain.supportCosmos) {
                ByteUtils.convertBech32ToEvm(selectChain.address)
            } else {
                selectChain.address
            }
            setQrAddress(context, address)

            chainBadge.visibility = View.GONE
            chainTypeBadge.visibility = View.GONE

            receiveView.setOnClickListener {
                val clipboard =
                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText(
                    "address", address
                )
                clipboard.setPrimaryClip(clip)
                context.makeToast(R.string.str_msg_address_copied)
            }
        }
    }

    fun bind(account: BaseAccount, selectChain: BaseChain) {
        binding.apply {
            receiveView.setBackgroundResource(R.drawable.item_bg)
            if (account.type == BaseAccountType.MNEMONIC) {
                accountPath.text = selectChain.getHDPath(account.lastHDPath)
            } else {
                accountPathLayout.visibility = View.GONE
            }

            receiveTitle.text =
                context.getString(R.string.str_deposit_caution_msg, selectChain.name)
            chainImg.setImageResource(selectChain.logo)
            setQrAddress(context, selectChain.address)

            if (!selectChain.isDefault) {
                chainBadge.visibility = View.VISIBLE
                chainBadge.setBackgroundResource(R.drawable.round_box_badge)
                chainBadge.setTextColor(
                    ContextCompat.getColor(
                        context, R.color.color_base02
                    )
                )
                chainBadge.text = context.getString(R.string.str_legacy)
                when (selectChain.tag) {
                    "okt996_Keccak" -> {
                        chainTypeBadge.text = context.getString(R.string.str_ethsecp256k1)
                    }

                    "okt996_Secp" -> {
                        chainTypeBadge.text = context.getString(R.string.str_secp256k1)
                    }

                    else -> {
                        chainTypeBadge.visibility = View.GONE
                    }
                }

            } else {
                chainBadge.visibility = View.GONE
                chainTypeBadge.visibility = View.GONE
            }

            receiveView.setOnClickListener {
                val clipboard =
                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("address", selectChain.address)
                clipboard.setPrimaryClip(clip)
                context.makeToast(R.string.str_msg_address_copied)
            }
        }
    }

    private fun setQrAddress(context: Context, selectAddress: String?) {
        binding.apply {
            val hints = mutableMapOf<EncodeHintType, Int>()
            hints[EncodeHintType.MARGIN] = 0

            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.encodeBitmap(
                selectAddress, BarcodeFormat.QR_CODE, 540, 540, hints
            )
            address.text = selectAddress
            if (address.text.toString().length >= 45) {
                address.letterSpacing = -0.03f
            } else {
                address.letterSpacing = 0.0f
            }
            qrImg.setImageBitmap(bitmap)

            qrView.radius = context.resources.getDimension(R.dimen.space_8)
            qrImg.clipToOutline = true
        }
    }
}