package wannabit.io.cosmostaion.ui.tx.info.kava

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.base.v1beta1.CoinProto.Coin
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.databinding.ItemEarnBinding
import wannabit.io.cosmostaion.databinding.ItemEarnStatusBinding
import wannabit.io.cosmostaion.databinding.ItemStickyHeaderBinding

class EarnListAdapter(
    val context: Context, private val selectedChain: CosmosLine?
) : ListAdapter<Coin, RecyclerView.ViewHolder>(EarnListDiffCallback()) {

    companion object {
        const val VIEW_TYPE_MY_STATUS_HEADER = 0
        const val VIEW_TYPE_MY_STATUS = 1
        const val VIEW_TYPE_MY_DEPOSIT_HEADER = 2
        const val VIEW_TYPE_MY_DEPOSIT = 3
    }

    private var onItemClickListener: ((Coin) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_MY_STATUS_HEADER, VIEW_TYPE_MY_DEPOSIT_HEADER -> {
                val binding = ItemStickyHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                EarnListHeaderViewHolder(binding)
            }

            VIEW_TYPE_MY_STATUS -> {
                val binding = ItemEarnStatusBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                EarnStatusViewHolder(binding)
            }

            VIEW_TYPE_MY_DEPOSIT -> {
                val binding = ItemEarnBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                EarnViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> VIEW_TYPE_MY_STATUS_HEADER
            1 -> VIEW_TYPE_MY_STATUS
            2 -> VIEW_TYPE_MY_DEPOSIT_HEADER
            else -> VIEW_TYPE_MY_DEPOSIT
        }
    }

    override fun getItemCount(): Int {
        return currentList.size + 3
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EarnListHeaderViewHolder -> {
                holder.bind(holder.itemViewType)
            }

            is EarnStatusViewHolder -> {
                holder.bind(selectedChain, currentList)
            }

            is EarnViewHolder -> {
                holder.bind(selectedChain, currentList[position - 3])

                holder.itemView.setOnClickListener {
                    onItemClickListener?.let { it(currentList[position - 3]) }
                }
            }
        }
    }

    inner class EarnListHeaderViewHolder(
        private val binding: ItemStickyHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewType: Int) {
            binding.apply {
                if (viewType == VIEW_TYPE_MY_STATUS_HEADER) {
                    headerTitle.text = "My deposit status"
                } else {
                    headerTitle.text = "Deposit list"
                    headerCnt.text = currentList.size.toString()
                }
            }
        }
    }

    private class EarnListDiffCallback : DiffUtil.ItemCallback<Coin>() {

        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (Coin) -> Unit) {
        onItemClickListener = listener
    }
}