package wannabit.io.cosmostaion.ui.main.setting.wallet.account

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.ItemPrivateBinding
import wannabit.io.cosmostaion.databinding.ItemStickyHeaderBinding
import wannabit.io.cosmostaion.ui.main.DashboardAdapter

class PrivateAdapter(
    val account: BaseAccount,
    private val mainnetChains: MutableList<BaseChain>,
    private val testnetChains: MutableList<BaseChain>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_MAINNET_HEADER = 0
        const val VIEW_TYPE_MAINNET_ITEM = 1
        const val VIEW_TYPE_TESTNET_HEADER = 2
        const val VIEW_TYPE_TESTNET_ITEM = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_MAINNET_HEADER, VIEW_TYPE_TESTNET_HEADER -> {
                val binding = ItemStickyHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                PrivateHeaderViewHolder(binding)
            }

            VIEW_TYPE_MAINNET_ITEM, VIEW_TYPE_TESTNET_ITEM -> {
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
                val chain = if (holder.itemViewType == VIEW_TYPE_MAINNET_ITEM) {
                    mainnetChains[position - 1]
                } else {
                    testnetChains[position - (mainnetChains.size + 2)]
                }
                holder.bind(account, chain)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (testnetChains.isNotEmpty()) {
            if (position == 0) DashboardAdapter.VIEW_TYPE_MAINNET_HEADER
            else if (position < mainnetChains.size + 1) DashboardAdapter.VIEW_TYPE_MAINNET_ITEM
            else if (position < mainnetChains.size + 2) DashboardAdapter.VIEW_TYPE_TESTNET_HEADER
            else DashboardAdapter.VIEW_TYPE_TESTNET_ITEM

        } else {
            if (position == 0) DashboardAdapter.VIEW_TYPE_MAINNET_HEADER
            else DashboardAdapter.VIEW_TYPE_MAINNET_ITEM
        }
    }

    override fun getItemCount(): Int {
        return if (testnetChains.isNotEmpty()) {
            mainnetChains.size + testnetChains.size + 2
        } else {
            mainnetChains.size + 1
        }
    }

    inner class PrivateHeaderViewHolder(
        private val binding: ItemStickyHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewType: Int) {
            binding.apply {
                if (viewType == VIEW_TYPE_MAINNET_HEADER) {
                    headerTitle.text = "Mainnet"
                    headerCnt.text = mainnetChains.size.toString()
                    headerMsg.visibility = View.VISIBLE
                } else {
                    headerTitle.text = "Testnet"
                    headerCnt.text = testnetChains.size.toString()
                }
            }
        }
    }
}