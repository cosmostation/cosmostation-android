package wannabit.io.cosmostaion.ui.dialog.tx

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.databinding.ItemChainBinding

class ChainAdapter : ListAdapter<CosmosLine, ChainViewHolder>(ChainDiffCallback()) {

    private var onItemClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChainViewHolder {
        val binding = ItemChainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChainViewHolder, position: Int) {
        val line = currentList[position]
        holder.bind(line)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(line.chainId)
            }
        }
    }

    private class ChainDiffCallback : DiffUtil.ItemCallback<CosmosLine>() {

        override fun areItemsTheSame(oldItem: CosmosLine, newItem: CosmosLine): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: CosmosLine, newItem: CosmosLine): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}
