package wannabit.io.cosmostaion.ui.main.setting.wallet.account

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.trustwallet.walletconnect.extensions.toHex
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.ItemPrivateBinding

class PrivateViewHolder(
    val context: Context, private val binding: ItemPrivateBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun evmBind(account: BaseAccount, evmLine: EthereumLine) {
        binding.apply {
            privateView.setBackgroundResource(R.drawable.item_bg)

            chainImg.setImageResource(evmLine.logo)
            chainName.text = evmLine.name.uppercase()
//            chainPath.text = evmLine.getHDPath(account.lastHDPath)
            chainPrivateKey.text = "0x" + evmLine.privateKey?.toHex()

            privateView.setOnLongClickListener { view ->
                val scaleX = view.scaleX
                val scaleY = view.scaleY

                if (scaleX == 1.0f && scaleY == 1.0f) {
                    view.animate().scaleX(1.05f).scaleY(1.05f).setDuration(300).start()

                    val handler = Handler()
                    handler.postDelayed({
                        view.animate().scaleX(1.0f).scaleY(1.0f).setDuration(300).start()
                        val clipboard =
                            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = ClipData.newPlainText("private", "0x" + evmLine.privateKey?.toHex())
                        clipboard.setPrimaryClip(clip)
                        context.makeToast(R.string.str_msg_private_copy)
                    }, 300)
                }
                true
            }
        }
    }

    fun bind(account: BaseAccount, line: CosmosLine) {
        binding.apply {
            privateView.setBackgroundResource(R.drawable.item_bg)

            chainImg.setImageResource(line.logo)
            chainName.text = line.name.uppercase()
//            chainPath.text = line.getHDPath(account.lastHDPath)
            chainPrivateKey.text = "0x" + line.privateKey?.toHex()

            if (!line.isDefault) {
                chainLegacy.visibility = View.VISIBLE
                when (line.tag) {
                    "okt996_Keccak" -> {
                        chainTypeBadge.visibility = View.VISIBLE
                        chainTypeBadge.text = context.getString(R.string.str_ethsecp256k1)
                    }

                    "okt996_Secp" -> {
                        chainTypeBadge.visibility = View.VISIBLE
                        chainTypeBadge.text = context.getString(R.string.str_secp256k1)
                    }

                    else -> {
                        chainTypeBadge.visibility = View.GONE
                    }
                }

            } else {
                chainLegacy.visibility = View.GONE
                chainTypeBadge.visibility = View.GONE
            }

            privateView.setOnLongClickListener { view ->
                val scaleX = view.scaleX
                val scaleY = view.scaleY

                if (scaleX == 1.0f && scaleY == 1.0f) {
                    view.animate().scaleX(1.05f).scaleY(1.05f).setDuration(300).start()

                    val handler = Handler()
                    handler.postDelayed({
                        view.animate().scaleX(1.0f).scaleY(1.0f).setDuration(300).start()
                        val clipboard =
                            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = ClipData.newPlainText("private", "0x" + line.privateKey?.toHex())
                        clipboard.setPrimaryClip(clip)
                        context.makeToast(R.string.str_msg_private_copy)
                    }, 300)
                }
                true
            }
        }
    }
}