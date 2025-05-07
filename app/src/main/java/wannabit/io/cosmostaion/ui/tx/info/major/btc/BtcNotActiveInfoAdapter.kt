package wannabit.io.cosmostaion.ui.tx.info.major.btc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.fetcher.FinalityProvider
import wannabit.io.cosmostaion.databinding.ItemBtcNotActiveBinding
import wannabit.io.cosmostaion.databinding.ItemStickyHeaderBinding
import wannabit.io.cosmostaion.ui.main.chain.evm.EvmEcoSystemAdapter.Companion.VIEW_TYPE_INJECT_HEADER

class BtcNotActiveInfoAdapter(
    private val selectedChain: BaseChain,
    private val unBondingList: List<Pair<JsonObject, FinalityProvider>>?,
    private val withdrawAbleList: List<Pair<JsonObject, FinalityProvider>>?,
    private var listener: ClickListener
) : ListAdapter<Pair<JsonObject, FinalityProvider>, RecyclerView.ViewHolder>(
    BtcUnStakingInfoDiffCallback()
) {

    companion object {
        const val VIEW_TYPE_WITHDRAW_HEADER = 0
        const val VIEW_TYPE_WITHDRAW_ITEM = 1
        const val VIEW_TYPE_UNBONDING_HEADER = 2
        const val VIEW_TYPE_UNBONDING_ITEM = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_WITHDRAW_HEADER, VIEW_TYPE_UNBONDING_HEADER -> {
                val binding = ItemStickyHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                BtcNotHeaderViewHolder(binding)
            }

            VIEW_TYPE_WITHDRAW_ITEM, VIEW_TYPE_UNBONDING_ITEM -> {
                val binding = ItemBtcNotActiveBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                BtcNotActiveViewHolder(parent.context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BtcNotHeaderViewHolder -> {
                holder.bind(position)
            }

            is BtcNotActiveViewHolder -> {
                if (holder.itemViewType == VIEW_TYPE_WITHDRAW_ITEM) {
                    val staked = withdrawAbleList?.get(position - 1)
                    holder.bind(selectedChain, staked, listener)

                } else {
                    val staked = if (withdrawAbleList?.isEmpty() == true) {
                        unBondingList?.get(position - 1)
                    } else {
                        unBondingList?.get(position - ((withdrawAbleList?.size ?: 0) + 2))
                    }
                    holder.bind(selectedChain, staked, listener)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (withdrawAbleList?.isEmpty() == true) {
            if (unBondingList?.isEmpty() == true) {
                -1
            } else {
                if (position == 0) VIEW_TYPE_UNBONDING_HEADER
                else VIEW_TYPE_UNBONDING_ITEM
            }

        } else {
            if (unBondingList?.isEmpty() == true) {
                if (position == 0) VIEW_TYPE_WITHDRAW_HEADER
                else VIEW_TYPE_WITHDRAW_ITEM
            } else {
                if (position == 0) VIEW_TYPE_WITHDRAW_HEADER
                else if (position < (withdrawAbleList?.size ?: 0) + 1) VIEW_TYPE_WITHDRAW_ITEM
                else if (position < (withdrawAbleList?.size ?: 0) + 2) VIEW_TYPE_UNBONDING_HEADER
                else VIEW_TYPE_UNBONDING_ITEM
            }
        }
    }

    override fun getItemCount(): Int {
        return if (withdrawAbleList?.isEmpty() == true) {
            if (unBondingList?.isEmpty() == true) {
                0
            } else {
                (unBondingList?.size ?: 0) + 1
            }

        } else {
            if (unBondingList?.isEmpty() == true) {
                (withdrawAbleList?.size ?: 0) + 1
            } else {
                (withdrawAbleList?.size ?: 0) + (unBondingList?.size ?: 0) + 2
            }
        }
    }

    inner class BtcNotHeaderViewHolder(
        private val binding: ItemStickyHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.apply {
                if (getItemViewType(position) == VIEW_TYPE_WITHDRAW_HEADER) {
                    headerTitle.text = "Withdrawable"
                    headerCnt.text = withdrawAbleList?.size.toString()
                } else {
                    headerTitle.text = "Unbonding"
                    headerCnt.text = unBondingList?.size.toString()
                }
            }
        }
    }

    private class BtcUnStakingInfoDiffCallback :
        DiffUtil.ItemCallback<Pair<JsonObject, FinalityProvider>>() {

        override fun areItemsTheSame(
            oldItem: Pair<JsonObject, FinalityProvider>, newItem: Pair<JsonObject, FinalityProvider>
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Pair<JsonObject, FinalityProvider>, newItem: Pair<JsonObject, FinalityProvider>
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface ClickListener {
        fun selectWithdrawAction(staked: Pair<JsonObject, FinalityProvider>?)
    }
}