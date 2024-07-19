package wannabit.io.cosmostaion.ui.qr

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.ItemHeaderBinding
import wannabit.io.cosmostaion.databinding.ItemQrBinding
import wannabit.io.cosmostaion.ui.main.chain.cosmos.ReceiveAdapter

class QrCodAdapter(
    private val account: BaseAccount, private val selectedChain: BaseChain
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
                QrHeaderViewHolder(binding)
            }

            VIEW_TYPE_EVM_ITEM, VIEW_TYPE_COSMOS_ITEM -> {
                val binding =
                    ItemQrBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                QrCodeViewHolder(parent.context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is QrHeaderViewHolder -> {
                holder.bind(position)
            }

            is QrCodeViewHolder -> {
                if (holder.itemViewType == ReceiveAdapter.VIEW_TYPE_EVM_ITEM) {
                    holder.evmBind(account, selectedChain)
                } else {
                    holder.bind(account, selectedChain)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (selectedChain.supportEvm) {
            if (selectedChain.isCosmos()) {
                when (position) {
                    0 -> VIEW_TYPE_EVM_HEADER
                    1 -> VIEW_TYPE_EVM_ITEM
                    2 -> VIEW_TYPE_COSMOS_HEADER
                    else -> VIEW_TYPE_COSMOS_ITEM
                }
            } else {
                when (position) {
                    0 -> VIEW_TYPE_EVM_HEADER
                    else -> VIEW_TYPE_EVM_ITEM
                }
            }

        } else {
            when (position) {
                0 -> VIEW_TYPE_COSMOS_HEADER
                else -> VIEW_TYPE_COSMOS_ITEM
            }
        }
    }

    override fun getItemCount(): Int {
        return if (selectedChain.supportEvm) {
            if (selectedChain.isCosmos()) {
                4
            } else {
                2
            }
        } else {
            2
        }
    }

    inner class QrHeaderViewHolder(
        private val binding: ItemHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.apply {
                if (itemCount == 4) {
                    if (getItemViewType(position) == VIEW_TYPE_EVM_HEADER) {
                        headerTitle.text = "My address (EVM Style)"
                    } else {
                        headerTitle.text = "My address (COSMOS Style)"
                    }

                } else {
                    headerTitle.text = "My address"
                }
            }
        }
    }
}