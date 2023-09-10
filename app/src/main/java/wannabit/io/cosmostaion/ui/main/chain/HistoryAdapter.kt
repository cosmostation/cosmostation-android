package wannabit.io.cosmostaion.ui.main.chain

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.data.model.CosmosHistory
import wannabit.io.cosmostaion.databinding.ItemHistoryBinding
import wannabit.io.cosmostaion.databinding.ItemStickyHeaderBinding

class HistoryAdapter(
    val context: Context,
    val line: CosmosLine
) : ListAdapter<CosmosHistory, RecyclerView.ViewHolder>(HistoryDiffCallback()) {

    companion object {
        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_HISTORY_ITEM = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val binding = ItemStickyHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                HistoryHeaderViewHolder(binding)
            }

            VIEW_TYPE_HISTORY_ITEM -> {
                val binding = ItemHistoryBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                HistoryViewHolder(parent.context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {

        }
    }

    private class HistoryDiffCallback : DiffUtil.ItemCallback<CosmosHistory>() {
        override fun areItemsTheSame(oldItem: CosmosHistory, newItem: CosmosHistory): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CosmosHistory, newItem: CosmosHistory): Boolean {
            return oldItem == newItem
        }
    }

    inner class HistoryHeaderViewHolder(
        private val binding: ItemStickyHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.apply {

            }
        }
    }
}