package wannabit.io.cosmostaion.ui.wallet

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.ItemWalletSelectBinding
import wannabit.io.cosmostaion.databinding.ItemWalletSelectHeaderBinding

class WalletSelectAdapter(
    private val account: BaseAccount,
    private val mainnetChains: MutableList<BaseChain>,
    private val testnetChains: MutableList<BaseChain>,
    private val selectedTags: MutableList<String>,
    var listener: SelectListener
) : ListAdapter<BaseChain, RecyclerView.ViewHolder>(PathDiffCallback()) {

    companion object {
        const val VIEW_TYPE_MAINNET_HEADER = 0
        const val VIEW_TYPE_MAINNET_ITEM = 1
        const val VIEW_TYPE_TESTNET_HEADER = 2
        const val VIEW_TYPE_TESTNET_ITEM = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_MAINNET_HEADER, VIEW_TYPE_TESTNET_HEADER -> {
                val binding = ItemWalletSelectHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                WalletSelectHeaderViewHolder(binding)
            }

            VIEW_TYPE_MAINNET_ITEM, VIEW_TYPE_TESTNET_ITEM -> {
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
                holder.bind(position)
            }

            is WalletSelectViewHolder -> {
                if (holder.itemViewType == VIEW_TYPE_MAINNET_ITEM) {
                    val mainnet = mainnetChains[position - 1]
                    holder.mainnetBind(account, mainnet, selectedTags, listener)
                }

//                } else {
//                    val line = testnetChains[position - (mainnetChains.size + 2)]
//                    holder.bind(account, line, selectedTags, listener)
//                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            VIEW_TYPE_MAINNET_HEADER
        } else if (position < mainnetChains.size + 1) {
            VIEW_TYPE_MAINNET_ITEM
        } else if (position < mainnetChains.size + 2) {
            VIEW_TYPE_TESTNET_HEADER
        } else {
            VIEW_TYPE_TESTNET_ITEM
        }
    }

    override fun getItemCount(): Int {
        return currentList.size + 2
    }

    inner class WalletSelectHeaderViewHolder(
        private val binding: ItemWalletSelectHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewType: Int) {
            binding.apply {
                if (viewType == VIEW_TYPE_MAINNET_HEADER) {
                    headerTitle.text = "Mainnet"
                    headerCnt.text = mainnetChains.size.toString()
                } else {
                    headerTitle.text = "Testnet"
                    headerCnt.text = testnetChains.size.toString()
                }
            }
        }
    }

    private class PathDiffCallback : DiffUtil.ItemCallback<BaseChain>() {

        override fun areItemsTheSame(oldItem: BaseChain, newItem: BaseChain): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: BaseChain, newItem: BaseChain): Boolean {
            return oldItem == newItem
        }
    }

    interface SelectListener {
        fun select(selectTags: MutableList<String>)
    }
}