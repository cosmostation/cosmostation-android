package wannabit.io.cosmostaion.ui.wallet

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.ItemWalletSelectBinding
import wannabit.io.cosmostaion.databinding.ItemWalletSelectHeaderBinding

class WalletSelectAdapter(
    private val context: Context,
    private val account: BaseAccount,
    private val selectedCosmosTags: MutableList<String>,
    var listener: SelectListener
) : ListAdapter<CosmosLine, RecyclerView.ViewHolder>(PathDiffCallback()) {

    companion object {
        const val VIEW_TYPE_COSMOS_HEADER = 0
        const val VIEW_TYPE_COSMOS_ITEM = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_COSMOS_HEADER -> {
                val binding = ItemWalletSelectHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                WalletSelectHeaderViewHolder(binding)
            }

            VIEW_TYPE_COSMOS_ITEM -> {
                val binding = ItemWalletSelectBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                WalletSelectViewHolder(parent.context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is WalletSelectHeaderViewHolder -> {
                holder.bind()
            }

            is WalletSelectViewHolder -> {
                val line = currentList[position - 1]
                holder.bind(account, line, selectedCosmosTags, listener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> VIEW_TYPE_COSMOS_HEADER
            else -> VIEW_TYPE_COSMOS_ITEM
        }
    }

    override fun getItemCount(): Int {
        return currentList.size + 1
    }

    inner class WalletSelectHeaderViewHolder(
        private val binding: ItemWalletSelectHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.apply {
                headerTitle.text = context.getString(R.string.str_cosmos_class)
                headerCnt.text = currentList.size.toString()
            }
        }
    }

    private class PathDiffCallback : DiffUtil.ItemCallback<CosmosLine>() {

        override fun areItemsTheSame(oldItem: CosmosLine, newItem: CosmosLine): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: CosmosLine, newItem: CosmosLine): Boolean {
            return oldItem == newItem
        }
    }

    interface SelectListener {
        fun select(selectTags: MutableList<String>)
    }
}