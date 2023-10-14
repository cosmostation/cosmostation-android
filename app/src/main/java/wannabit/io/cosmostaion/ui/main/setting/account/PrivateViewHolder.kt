package wannabit.io.cosmostaion.ui.main.setting.account

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.trustwallet.walletconnect.extensions.toHex
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.ItemPrivateBinding

class PrivateViewHolder(
    val context: Context,
    private val binding: ItemPrivateBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(account: BaseAccount, line: CosmosLine) {
        binding.apply {
            privateView.setBackgroundResource(R.drawable.item_bg)

            chainImg.setImageResource(line.logo)
            chainName.text = line.name.uppercase()
            chainPath.text = line.getHDPath(account.lastHDPath)
            chainPrivateKey.text = "0x" + line.privateKey?.toHex()
        }
    }
}