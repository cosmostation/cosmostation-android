package wannabit.io.cosmostaion.ui.main.setting.wallet.chain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.databinding.ItemChainManageBinding
import wannabit.io.cosmostaion.databinding.ItemHeaderBinding
import wannabit.io.cosmostaion.ui.main.edit.ChainEditAdapter

class ChainManageAdapter(
    private val allEvmLines: MutableList<EthereumLine>,
    private val allCosmosLines: MutableList<CosmosLine>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_EVM_HEADER = 0
        const val VIEW_TYPE_EVM_ITEM = 1
        const val VIEW_TYPE_COSMOS_HEADER = 2
        const val VIEW_TYPE_COSMOS_ITEM = 3
    }

    private var onItemClickListener: ((CosmosLine) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_EVM_HEADER, VIEW_TYPE_COSMOS_HEADER -> {
                val binding = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                ManageHeaderViewHolder(binding)
            }

            VIEW_TYPE_EVM_ITEM, VIEW_TYPE_COSMOS_ITEM -> {
                val binding = ItemChainManageBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                ChainManageViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ManageHeaderViewHolder -> {
                holder.bind(position)
            }

            is ChainManageViewHolder -> {
                if (holder.itemViewType == VIEW_TYPE_EVM_ITEM) {
                    val line = allEvmLines[position - 1]
                    holder.evmBind(line)

                    holder.itemView.setOnClickListener {
                        onItemClickListener?.let { it(line) }
                    }

                } else {
                    val line = allCosmosLines[position - (allEvmLines.size + 2)]
                    holder.bind(line)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_EVM_HEADER
        else if (position < allEvmLines.size + 1) VIEW_TYPE_EVM_ITEM
        else if (position < allEvmLines.size + 2) VIEW_TYPE_COSMOS_HEADER
        else ChainEditAdapter.VIEW_TYPE_COSMOS_ITEM
    }

    override fun getItemCount(): Int {
        return allEvmLines.size + allCosmosLines.size + 2
    }

    inner class ManageHeaderViewHolder(
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

    fun setOnItemClickListener(listener: (CosmosLine) -> Unit) {
        onItemClickListener = listener
    }
}