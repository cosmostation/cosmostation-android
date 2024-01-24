package wannabit.io.cosmostaion.ui.main.setting.wallet.account

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
import wannabit.io.cosmostaion.databinding.ItemPrivateBinding
import wannabit.io.cosmostaion.databinding.ItemStickyHeaderBinding
import wannabit.io.cosmostaion.ui.wallet.WalletSelectAdapter

class PrivateAdapter(
    val account: BaseAccount
) : ListAdapter<Any, RecyclerView.ViewHolder>(PrivateDiffCallback()) {

    companion object {
        const val VIEW_TYPE_COSMOS_HEADER = 0
        const val VIEW_TYPE_COSMOS_ITEM = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_COSMOS_HEADER -> {
                val binding = ItemStickyHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                PrivateHeaderViewHolder(parent.context, binding)
            }

            VIEW_TYPE_COSMOS_ITEM -> {
                val binding = ItemPrivateBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                PrivateViewHolder(parent.context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PrivateHeaderViewHolder -> {
                holder.bind()
            }

            is PrivateViewHolder -> {
                val line = currentList[position - 1] as CosmosLine
                holder.bind(account, line)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> WalletSelectAdapter.VIEW_TYPE_COSMOS_HEADER
            else -> WalletSelectAdapter.VIEW_TYPE_COSMOS_ITEM
        }
    }

    override fun getItemCount(): Int {
        return currentList.size + 1
    }

    inner class PrivateHeaderViewHolder(
        private val context: Context, private val binding: ItemStickyHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.apply {
                headerTitle.text = context.getString(R.string.str_cosmos_class)
                headerCnt.text = currentList.size.toString()
            }
        }
    }

    private class PrivateDiffCallback : DiffUtil.ItemCallback<Any>() {

        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }
    }
}