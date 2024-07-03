package wannabit.io.cosmostaion.ui.option.tx.general

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.databinding.ItemChainBinding

class ChainAdapter : ListAdapter<BaseChain, ChainViewHolder>(ChainDiffCallback()) {

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
                it(line.chainIdCosmos)
            }
        }
    }

    private class ChainDiffCallback : DiffUtil.ItemCallback<BaseChain>() {

        override fun areItemsTheSame(oldItem: BaseChain, newItem: BaseChain): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: BaseChain, newItem: BaseChain): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}
