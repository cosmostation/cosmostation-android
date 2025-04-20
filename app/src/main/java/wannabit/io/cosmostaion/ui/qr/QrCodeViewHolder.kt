package wannabit.io.cosmostaion.ui.qr

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.journeyapps.barcodescanner.BarcodeEncoder
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.setChainLogo
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.ItemQrBinding

class QrCodeViewHolder(
    val context: Context,
    private val binding: ItemQrBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun evmBind(account: BaseAccount, selectChain: BaseChain) {
        binding.apply {
            receiveView.setBackgroundResource(R.drawable.item_bg)
            if (account.type == BaseAccountType.MNEMONIC) {
                accountPath.text = selectChain.getHDPath(account.lastHDPath)
            } else {
                accountPathLayout.visibility = View.GONE
            }

            receiveTitle.text =
                context.getString(R.string.str_deposit_caution_msg, selectChain.name + " EVM")
            chainImg.setChainLogo(selectChain)
            setQrAddress(context, selectChain.evmAddress)

            chainBadge.visibility = View.GONE
            chainTypeBadge.visibility = View.GONE

            receiveView.setOnClickListener {
                val clipboard =
                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText(
                    "address", selectChain.evmAddress
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

            val address = if (selectChain is ChainSui || selectChain is ChainBitCoin86) {
                selectChain.mainAddress
            } else {
                selectChain.address
            }

            receiveTitle.text =
                context.getString(R.string.str_deposit_caution_msg, selectChain.name)
            setQrAddress(context, address)
            chainImg.setChainLogo(selectChain)

            if (selectChain is ChainBitCoin86) {
                chainTypeBadge.visibility = View.GONE
                chainBadge.visibility = View.VISIBLE
                when (selectChain.accountKeyType.pubkeyType) {
                    PubKeyType.BTC_NESTED_SEGWIT -> {
                        chainBadge.defaultSet()
                        chainBadge.text = context.getString(R.string.str_nested_segwit)
                    }

                    PubKeyType.BTC_LEGACY -> {
                        chainBadge.defaultSet()
                        chainBadge.text = context.getString(R.string.str_legacy)
                    }

                    PubKeyType.BTC_NATIVE_SEGWIT -> {
                        chainBadge.setBackgroundResource(R.drawable.round_box_bit)
                        chainBadge.setTextColor(
                            ContextCompat.getColorStateList(
                                context, R.color.color_base01
                            )
                        )
                        chainBadge.text = context.getString(R.string.str_native_segwit)
                    }

                    else -> {
                        chainBadge.setBackgroundResource(R.drawable.round_box_bit_taproot)
                        chainBadge.setTextColor(
                            ContextCompat.getColorStateList(
                                context, R.color.color_base01
                            )
                        )
                        chainBadge.text = context.getString(R.string.str_taproot)
                    }
                }

            } else {
                if (!selectChain.isDefault) {
                    chainBadge.visibility = View.VISIBLE
                    chainBadge.defaultSet()
                    chainBadge.text = context.getString(R.string.str_old)
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
            }

            receiveView.setOnClickListener {
                val clipboard =
                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("address", address)
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

private fun TextView.defaultSet() {
    setBackgroundResource(R.drawable.round_box_badge)
    setTextColor(ContextCompat.getColorStateList(context, R.color.color_base02))
}