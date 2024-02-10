package wannabit.io.cosmostaion.ui.tx.step

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.databinding.ItemAllChainClaimBinding

class AllChainClaimAdapter :
    ListAdapter<ValueAbleReward, AllChainClaimViewHolder>(AllChainClaimDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllChainClaimViewHolder {
        val binding =
            ItemAllChainClaimBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllChainClaimViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: AllChainClaimViewHolder, position: Int) {
        val reward = currentList[position]
        holder.bind(reward)
    }

    private class AllChainClaimDiffCallback : DiffUtil.ItemCallback<ValueAbleReward>() {

        override fun areItemsTheSame(
            oldItem: ValueAbleReward, newItem: ValueAbleReward
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ValueAbleReward, newItem: ValueAbleReward
        ): Boolean {
            return oldItem == newItem
        }
    }
}