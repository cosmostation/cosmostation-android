package wannabit.io.cosmostaion.ui.main.setting.wallet.chain

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.formatTxTime
import wannabit.io.cosmostaion.data.model.res.Ads
import wannabit.io.cosmostaion.databinding.ItemChainNoticeBinding

class ChainNoticeViewHolder(
    private val binding: ItemChainNoticeBinding, private val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(ad: Ads) {
        binding.apply {
            noticeView.setBackgroundResource(R.drawable.item_bg)
            noticeTitle.text = ad.title
            noticeCreated.text = ad.startAt?.let {
                formatTxTime(context, it)
            } ?: run {
                ""
            }
        }
    }
}