package wannabit.io.cosmostaion.ui.main.setting.wallet.account

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.ItemPrivateBinding
import wannabit.io.cosmostaion.databinding.ItemStickyHeaderBinding
import wannabit.io.cosmostaion.ui.main.edit.ChainEditAdapter

class PrivateAdapter(
    val account: BaseAccount,
    private val allEvmLines: MutableList<EthereumLine>,
    private val allCosmosLines: MutableList<CosmosLine>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_ETHEREUM_HEADER = 0
        const val VIEW_TYPE_ETHEREUM_ITEM = 1
        const val VIEW_TYPE_COSMOS_HEADER = 2
        const val VIEW_TYPE_COSMOS_ITEM = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ETHEREUM_HEADER, VIEW_TYPE_COSMOS_HEADER -> {
                val binding = ItemStickyHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                PrivateHeaderViewHolder(parent.context, binding)
            }

            VIEW_TYPE_ETHEREUM_ITEM, VIEW_TYPE_COSMOS_ITEM -> {
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
                holder.bind(position)
            }

            is PrivateViewHolder -> {
                if (holder.itemViewType == VIEW_TYPE_ETHEREUM_ITEM) {
                    val evmChain = allEvmLines[position - 1]
                    holder.evmBind(account, evmChain)

                } else {
                    val line = allCosmosLines[position - (allEvmLines.size + 2)]
                    holder.bind(account, line)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) ChainEditAdapter.VIEW_TYPE_EVM_HEADER
        else if (position < allEvmLines.size + 1) ChainEditAdapter.VIEW_TYPE_EVM_ITEM
        else if (position < allEvmLines.size + 2) ChainEditAdapter.VIEW_TYPE_COSMOS_HEADER
        else ChainEditAdapter.VIEW_TYPE_COSMOS_ITEM
    }

    override fun getItemCount(): Int {
        return allEvmLines.size + allCosmosLines.size + 2
    }

    inner class PrivateHeaderViewHolder(
        private val context: Context, private val binding: ItemStickyHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewType: Int) {
            binding.apply {
                if (viewType == VIEW_TYPE_ETHEREUM_HEADER) {
                    headerTitle.text = context.getString(R.string.str_ethereum_class)
                    headerCnt.text = allEvmLines.size.toString()
                } else {
                    headerTitle.text = context.getString(R.string.str_cosmos_class)
                    headerCnt.text = allCosmosLines.size.toString()
                }
            }
        }
    }
}