package wannabit.io.cosmostaion.ui.main.setting.wallet.account

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Handler
import androidx.recyclerview.widget.RecyclerView
import com.trustwallet.walletconnect.extensions.toHex
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.ItemPrivateBinding

class PrivateViewHolder(
    val context: Context, private val binding: ItemPrivateBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(account: BaseAccount, chain: BaseChain) {
        binding.apply {
            privateView.setBackgroundResource(R.drawable.item_bg)
            chainImg.setImageResource(chain.logo)
            chainName.text = chain.name.uppercase()
            chainPath.text = chain.getHDPath(account.lastHDPath)
            chainPrivateKey.text = "0x" + chain.privateKey?.toHex()

            chainLegacy.visibleOrGone(!chain.isDefault)

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
                        val clip =
                            ClipData.newPlainText("private", "0x" + chain.privateKey?.toHex())
                        clipboard.setPrimaryClip(clip)
                        context.makeToast(R.string.str_msg_private_copy)
                    }, 300)
                }
                true
            }
        }
    }
}