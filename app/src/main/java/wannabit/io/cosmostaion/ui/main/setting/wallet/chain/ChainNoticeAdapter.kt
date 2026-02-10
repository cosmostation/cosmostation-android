package wannabit.io.cosmostaion.ui.main.setting.wallet.chain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.data.model.res.Ads
import wannabit.io.cosmostaion.databinding.ItemChainNoticeBinding

class ChainNoticeAdapter : ListAdapter<Ads, ChainNoticeViewHolder>(NoticeDiffCallback()) {

    private var onItemClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChainNoticeViewHolder {
        val binding =
            ItemChainNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChainNoticeViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: ChainNoticeViewHolder, position: Int) {
        val ad = currentList[position]
        holder.bind(ad)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(ad.id)
            }
        }
    }

    private class NoticeDiffCallback : DiffUtil.ItemCallback<Ads>() {

        override fun areItemsTheSame(oldItem: Ads, newItem: Ads): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Ads, newItem: Ads): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}