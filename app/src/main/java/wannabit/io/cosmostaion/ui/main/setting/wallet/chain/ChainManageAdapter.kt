package wannabit.io.cosmostaion.ui.main.setting.wallet.chain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.databinding.ItemChainManageBinding
import wannabit.io.cosmostaion.databinding.ItemHeaderBinding

class ChainManageAdapter(
    private val mainnetChains: MutableList<BaseChain>,
    private val testnetChains: MutableList<BaseChain>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_MAINNET_HEADER = 0
        const val VIEW_TYPE_MAINNET_ITEM = 1
        const val VIEW_TYPE_TESTNET_HEADER = 2
        const val VIEW_TYPE_TESTNET_ITEM = 3
    }

    private var onItemClickListener: ((BaseChain) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_MAINNET_HEADER, VIEW_TYPE_TESTNET_HEADER -> {
                val binding = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                ManageHeaderViewHolder(binding)
            }

            VIEW_TYPE_MAINNET_ITEM, VIEW_TYPE_TESTNET_ITEM -> {
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
                if (holder.itemViewType == VIEW_TYPE_MAINNET_ITEM) {
                    val mainnet = mainnetChains[position - 1]
                    holder.bind(mainnet)

                    holder.itemView.setOnClickListener {
                        onItemClickListener?.let { it(mainnet) }
                    }

                } else {
                    val testnet = testnetChains[position - (mainnetChains.size + 2)]
                    holder.testnetBind(testnet)

                    holder.itemView.setOnClickListener {
                        onItemClickListener?.let { it(testnet) }
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_MAINNET_HEADER
        else if (position < mainnetChains.size + 1) VIEW_TYPE_MAINNET_ITEM
        else if (position < mainnetChains.size + 2) VIEW_TYPE_TESTNET_HEADER
        else VIEW_TYPE_TESTNET_ITEM
    }

    override fun getItemCount(): Int {
        return mainnetChains.size + testnetChains.size + 2
    }

    inner class ManageHeaderViewHolder(
        private val binding: ItemHeaderBinding
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

    fun setOnItemClickListener(listener: (BaseChain) -> Unit) {
        onItemClickListener = listener
    }
}