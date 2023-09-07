package wannabit.io.cosmostaion.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.databinding.ItemDashBinding
import wannabit.io.cosmostaion.databinding.ItemStickyHeaderBinding

class DashboardAdapter(val context: Context) : ListAdapter<Any, RecyclerView.ViewHolder>(DashboardDiffCallback()) {

    companion object {
        const val VIEW_TYPE_COSMOS_HEADER = 0
        const val VIEW_TYPE_COSMOS_ITEM = 1
        const val VIEW_TYPE_COSMOS_ETHEREUM_ITEM = 2
    }

    private var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_COSMOS_HEADER -> {
                val binding = ItemStickyHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                DashboardHeaderViewHolder(binding)
            }

            VIEW_TYPE_COSMOS_ITEM -> {
                val binding = ItemDashBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                DashboardViewHolder(context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DashboardHeaderViewHolder -> {
                holder.bind(position)
            }

            is DashboardViewHolder -> {
                val line = currentList[position - 1] as CosmosLine
                holder.bind(line)

                holder.itemView.setOnClickListener {
                    onItemClickListener?.let { it(position - 1) }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_COSMOS_HEADER else VIEW_TYPE_COSMOS_ITEM
    }

    override fun getItemCount(): Int {
        return currentList.size + 1
    }

    private class DashboardDiffCallback : DiffUtil.ItemCallback<Any>() {

        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return when {
                oldItem is CosmosLine && newItem is CosmosLine -> oldItem.id == newItem.id
                else -> false
            }
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }
    }

    inner class DashboardHeaderViewHolder(
        private val binding: ItemStickyHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.apply {
                if (getItemViewType(position) == VIEW_TYPE_COSMOS_HEADER) {
                    headerTitle.text = "Cosmos"
                }
            }
        }
    }

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }
}