package wannabit.io.cosmostaion.ui.main.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.ItemEditBinding
import wannabit.io.cosmostaion.databinding.ItemHeaderBinding

class ChainEditAdapter(
    val account: BaseAccount,
    private val allEvmLines: MutableList<EthereumLine>,
    private val displayEvmChains: MutableList<String>,
    private val allCosmosLines: MutableList<CosmosLine>,
    private val displayChains: MutableList<String>,
    private var evmListener: EvmSelectListener,
    private var listener: SelectListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_EVM_HEADER = 0
        const val VIEW_TYPE_EVM_ITEM = 1
        const val VIEW_TYPE_COSMOS_HEADER = 2
        const val VIEW_TYPE_COSMOS_ITEM = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_EVM_HEADER, VIEW_TYPE_COSMOS_HEADER -> {
                val binding = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                EditHeaderViewHolder(binding)
            }

            VIEW_TYPE_EVM_ITEM, VIEW_TYPE_COSMOS_ITEM -> {
                val binding =
                    ItemEditBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ChainEditViewHolder(parent.context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EditHeaderViewHolder -> {
                holder.bind(position)
            }

            is ChainEditViewHolder -> {
                if (holder.itemViewType == VIEW_TYPE_EVM_ITEM) {
                    val evmChain = allEvmLines[position - 1]
                    holder.evmBind(account, evmChain, displayEvmChains, evmListener)

                } else {
                    val line = allCosmosLines[position - (allEvmLines.size + 2)]
                    holder.bind(account, line, displayChains, listener)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_EVM_HEADER
        else if (position < allEvmLines.size + 1) VIEW_TYPE_EVM_ITEM
        else if (position < allEvmLines.size + 2) VIEW_TYPE_COSMOS_HEADER
        else VIEW_TYPE_COSMOS_ITEM
    }

    override fun getItemCount(): Int {
        return allEvmLines.size + allCosmosLines.size + 2
    }

    inner class EditHeaderViewHolder(
        private val binding: ItemHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewType: Int) {
            binding.apply {
                if (viewType == VIEW_TYPE_EVM_HEADER) {
                    headerTitle.text = "Evm class"
                    headerCnt.text = allEvmLines.size.toString()
                } else {
                    headerTitle.text = "Cosmos class"
                    headerCnt.text = allCosmosLines.size.toString()
                }
            }
        }
    }

    interface EvmSelectListener {
        fun select(displayEvmChains: MutableList<String>)
    }

    interface SelectListener {
        fun select(displayChains: MutableList<String>)
    }
}