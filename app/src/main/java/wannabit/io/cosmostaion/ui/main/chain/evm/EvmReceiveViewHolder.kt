package wannabit.io.cosmostaion.ui.main.chain.evm

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
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.majorClass.ChainIota
import wannabit.io.cosmostaion.chain.majorClass.ChainSolana
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.setChainLogo
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.ItemReceiveBinding

class EvmReceiveViewHolder(
    val context: Context,
    private val binding: ItemReceiveBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun evmBind(account: BaseAccount, selectChain: BaseChain) {
        binding.apply {
            receiveView.setBackgroundResource(R.drawable.item_bg)
            if (account.type == BaseAccountType.MNEMONIC) {
                accountPath.text = selectChain.getHDPath(account.lastHDPath)
            } else {
                accountPathLayout.visibility = View.GONE
            }

            if (selectChain is ChainSui || selectChain is ChainIota || selectChain is ChainBitCoin86 || selectChain is ChainSolana) {
                receiveTitle.text =
                    context.getString(R.string.str_deposit_caution_msg, selectChain.getChainName())
                setQrAddress(context, selectChain.mainAddress)

            } else {
                receiveTitle.text =
                    context.getString(R.string.str_deposit_caution_msg, selectChain.getChainName() + " EVM")
                setQrAddress(context, selectChain.evmAddress)
            }

            chainImg.setChainLogo(selectChain)
            accountPath.text = selectChain.getHDPath(account.lastHDPath)
            if (selectChain is ChainBitCoin86) {
                when (selectChain.accountKeyType.pubkeyType) {
                    PubKeyType.BTC_LEGACY -> {
                        chainTypeBadge.visibility = View.VISIBLE
                        chainBadge.visibility = View.GONE
                        chainTypeBadge.text = context.getString(R.string.str_legacy)
                        chainBadge.setTextColor(
                            ContextCompat.getColorStateList(
                                context, R.color.color_base02
                            )
                        )
                    }

                    PubKeyType.BTC_NESTED_SEGWIT -> {
                        chainTypeBadge.visibility = View.VISIBLE
                        chainBadge.visibility = View.GONE
                        chainTypeBadge.text = context.getString(R.string.str_nested_segwit)
                        chainBadge.setTextColor(
                            ContextCompat.getColorStateList(
                                context, R.color.color_base02
                            )
                        )
                    }

                    PubKeyType.BTC_NATIVE_SEGWIT -> {
                        chainBadge.visibility = View.GONE
                        chainTypeBadge.visibility = View.VISIBLE
                        chainTypeBadge.text = context.getString(R.string.str_native_segwit)
                        chainTypeBadge.setTextColor(
                            ContextCompat.getColorStateList(
                                context, R.color.color_base01
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
                                context, R.color.color_base01
                            )
                        )
                        chainTypeBadge.setBackgroundResource(R.drawable.round_box_bit_taproot)
                    }
                }

            } else {
                chainBadge.visibility = View.GONE
                chainTypeBadge.visibility = View.GONE
            }

            receiveView.setOnClickListener {
                val address =
                    if (selectChain is ChainSui || selectChain is ChainIota || selectChain is ChainBitCoin86 || selectChain is ChainSolana) {
                        selectChain.mainAddress
                    } else {
                        selectChain.evmAddress
                    }

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