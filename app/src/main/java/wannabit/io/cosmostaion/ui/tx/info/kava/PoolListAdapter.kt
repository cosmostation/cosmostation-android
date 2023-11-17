package wannabit.io.cosmostaion.ui.tx.info.kava

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kava.swap.v1beta1.QueryProto
import com.kava.swap.v1beta1.QueryProto.PoolResponse
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.databinding.ItemPoolBinding
import wannabit.io.cosmostaion.databinding.ItemStickyHeaderBinding

class PoolListAdapter(
    val context: Context,
    private val selectChain: CosmosLine,
    private val swapMyList: MutableList<PoolResponse>,
    private val swapOtherList: MutableList<PoolResponse>,
    private val swapMyDeposit: MutableList<QueryProto.DepositResponse>?,
    val listener: ClickListener,
) : ListAdapter<PoolResponse, RecyclerView.ViewHolder>(PoolListDiffCallback()) {

    companion object {
        const val VIEW_TYPE_POOL_MY_HEADER = 0
        const val VIEW_TYPE_POOL_MY_ITEM = 1
        const val VIEW_TYPE_POOL_OTHER_HEADER = 2
        const val VIEW_TYPE_POOL_OTHER_ITEM = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_POOL_MY_HEADER, VIEW_TYPE_POOL_OTHER_HEADER -> {
                val binding = ItemStickyHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                PoolListHeaderViewHolder(binding)
            }

            VIEW_TYPE_POOL_MY_ITEM,  VIEW_TYPE_POOL_OTHER_ITEM-> {
                val binding = ItemPoolBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                PoolListViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PoolListHeaderViewHolder -> {
                holder.bind(holder.itemViewType)
            }

            is PoolListViewHolder -> {
                if (holder.itemViewType == VIEW_TYPE_POOL_MY_ITEM) {
                    val deposit = swapMyDeposit?.get(position - 1)
                    val pool = swapMyList.firstOrNull { it.name == deposit?.poolId }
                    holder.bindMyPool(selectChain, pool, deposit, listener)
                } else {
                    val pool = if (swapMyList.isNotEmpty()) {
                        swapOtherList[position - swapMyList.size - 2]
                    } else {
                        swapOtherList[position - 1]
                    }
                    holder.bindOtherPool(selectChain, pool, listener)
                }

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (swapMyList.isNotEmpty()) {
            return if (position == 0) {
                VIEW_TYPE_POOL_MY_HEADER
            } else if (position < swapMyList.size + 1) {
                VIEW_TYPE_POOL_MY_ITEM
            } else if (position < swapMyList.size + 2) {
                VIEW_TYPE_POOL_OTHER_HEADER
            } else {
                VIEW_TYPE_POOL_OTHER_ITEM
            }

        } else {
            return if (position == 0) {
                VIEW_TYPE_POOL_OTHER_HEADER
            } else {
                VIEW_TYPE_POOL_OTHER_ITEM
            }
        }
    }

    override fun getItemCount(): Int {
        val allSwapList = swapMyList.size + swapOtherList.size
        return if (swapMyList.isNotEmpty()) {
            allSwapList + 2
        } else {
            allSwapList + 1
        }
    }

    private class PoolListDiffCallback : DiffUtil.ItemCallback<PoolResponse>() {

        override fun areItemsTheSame(oldItem: PoolResponse, newItem: PoolResponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PoolResponse, newItem: PoolResponse): Boolean {
            return oldItem == newItem
        }
    }

    inner class PoolListHeaderViewHolder(
        private val binding: ItemStickyHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewType: Int) {
            binding.apply {
                if (viewType == VIEW_TYPE_POOL_MY_HEADER) {
                    headerTitle.text = "Deposit pool list"
                    headerCnt.text = swapMyList.size.toString()

                } else {
                    headerTitle.text = "Pool list"
                    headerCnt.text = swapOtherList.size.toString()
                }
            }
        }
    }

    interface ClickListener {
        fun myPoolSelect(poolId: String, deposit: QueryProto.DepositResponse)
        fun otherPoolSelect(name: String)
    }
}