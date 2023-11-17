package wannabit.io.cosmostaion.ui.tx.info.kava

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.base.v1beta1.CoinProto
import com.kava.hard.v1beta1.HardProto.MoneyMarket
import com.kava.hard.v1beta1.QueryProto.MoneyMarketInterestRate
import com.kava.pricefeed.v1beta1.QueryProto
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.ItemLendBinding
import wannabit.io.cosmostaion.databinding.ItemLendStatusBinding
import wannabit.io.cosmostaion.databinding.ItemStickyHeaderBinding

class LendListAdapter(
    val context: Context,
    private val lendMoneyMarkets: MutableList<MoneyMarket>,
    private val priceFeed: QueryProto.QueryPricesResponse?,
    private val lendMyDeposits: MutableList<CoinProto.Coin>,
    private val lendMyBorrows: MutableList<CoinProto.Coin>,
    var listener: ClickListener
) : ListAdapter<MoneyMarketInterestRate, RecyclerView.ViewHolder>(LendListDiffCallback()) {

    companion object {
        const val VIEW_TYPE_LEND_STATUS_HEADER = 0
        const val VIEW_TYPE_LEND_STATUS_ITEM = 1
        const val VIEW_TYPE_LEND_LIST_HEADER = 2
        const val VIEW_TYPE_LEND_LIST_ITEM = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_LEND_STATUS_HEADER, VIEW_TYPE_LEND_LIST_HEADER -> {
                val binding = ItemStickyHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                LendListHeaderViewHolder(binding)
            }

            VIEW_TYPE_LEND_STATUS_ITEM -> {
                val binding = ItemLendStatusBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                LendStatusViewHolder(context, binding)
            }

            VIEW_TYPE_LEND_LIST_ITEM -> {
                val binding = ItemLendBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                LendListViewHolder(context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LendListHeaderViewHolder -> {
                holder.bind(holder.itemViewType)
            }

            is LendStatusViewHolder -> {
                holder.bind(lendMoneyMarkets, priceFeed, lendMyDeposits, lendMyBorrows)
            }

            is LendListViewHolder -> {
                holder.bind(lendMoneyMarkets[position -3], priceFeed, lendMyDeposits, lendMyBorrows, listener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> { VIEW_TYPE_LEND_STATUS_HEADER }
            1 -> { VIEW_TYPE_LEND_STATUS_ITEM }
            2 -> { VIEW_TYPE_LEND_LIST_HEADER }
            else -> { VIEW_TYPE_LEND_LIST_ITEM }
        }
    }

    override fun getItemCount(): Int {
        return currentList.size + 3
    }

    private class LendListDiffCallback : DiffUtil.ItemCallback<MoneyMarketInterestRate>() {

        override fun areItemsTheSame(oldItem: MoneyMarketInterestRate, newItem: MoneyMarketInterestRate): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MoneyMarketInterestRate, newItem: MoneyMarketInterestRate): Boolean {
            return oldItem == newItem
        }
    }

    inner class LendListHeaderViewHolder(
        private val binding: ItemStickyHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewType: Int) {
            binding.apply {
                if (viewType == VIEW_TYPE_LEND_STATUS_HEADER) {
                    headerTitle.text = "My lend status"

                } else {
                    headerTitle.text = context.getString(R.string.str_market_list)
                    headerCnt.text = currentList.size.toString()
                }
            }
        }
    }

    interface ClickListener {
        fun lendOption(denom: String?)
    }
}