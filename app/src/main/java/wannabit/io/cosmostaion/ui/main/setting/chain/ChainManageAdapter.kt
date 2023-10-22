package wannabit.io.cosmostaion.ui.main.setting.chain

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.databinding.ItemChainManageBinding

class ChainManageAdapter : ListAdapter<CosmosLine, ChainManageViewHolder>(ChainManageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChainManageViewHolder {
        val binding = ItemChainManageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChainManageViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: ChainManageViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    private class ChainManageDiffCallback : DiffUtil.ItemCallback<CosmosLine>() {

        override fun areItemsTheSame(oldItem: CosmosLine, newItem: CosmosLine): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: CosmosLine, newItem: CosmosLine): Boolean {
            return oldItem == newItem
        }
    }
}