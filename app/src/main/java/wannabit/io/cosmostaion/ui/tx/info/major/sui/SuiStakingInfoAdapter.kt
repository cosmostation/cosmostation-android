package wannabit.io.cosmostaion.ui.tx.info.major.sui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.fetcher.StakeReward
import wannabit.io.cosmostaion.databinding.ItemSuiStakingInfoBinding

class SuiStakingInfoAdapter(
    private val selectedChain: BaseChain
) : ListAdapter<StakeReward, SuiStakingInfoViewHolder>(SuiStakingInfoDiffCallback()) {

    private var onItemClickListener: ((StakeReward) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuiStakingInfoViewHolder {
        val binding =
            ItemSuiStakingInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuiStakingInfoViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: SuiStakingInfoViewHolder, position: Int) {
        val staked = currentList[position]
        holder.bind(selectedChain, staked)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(staked)
            }
        }
    }

    private class SuiStakingInfoDiffCallback : DiffUtil.ItemCallback<StakeReward>() {

        override fun areItemsTheSame(
            oldItem: StakeReward, newItem: StakeReward
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: StakeReward, newItem: StakeReward
        ): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (StakeReward) -> Unit) {
        onItemClickListener = listener
    }
}