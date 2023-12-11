package wannabit.io.cosmostaion.ui.tx.info

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.databinding.ItemRewardAddressBinding

class RewardAddressViewHolder(
    val context: Context,
    private val binding: ItemRewardAddressBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(rewardAddress: String?) {
        binding.apply {
            rewardAddressView.setBackgroundResource(R.drawable.item_reward_cell_bg)
            anotherRewardAddress.text = rewardAddress

            rewardAddressView.setOnClickListener {
                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("rewardAddress", rewardAddress)
                clipboard.setPrimaryClip(clip)
                context.makeToast(R.string.str_msg_address_copied)
            }
        }
    }
}