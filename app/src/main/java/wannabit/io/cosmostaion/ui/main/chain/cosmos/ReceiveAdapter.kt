package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.ItemByFooterBinding
import wannabit.io.cosmostaion.databinding.ItemHeaderBinding
import wannabit.io.cosmostaion.databinding.ItemReceiveBinding
import wannabit.io.cosmostaion.ui.qr.QrCodAdapter
import wannabit.io.cosmostaion.ui.qr.QrCodAdapter.Companion

class ReceiveAdapter(
    val context: Context,
    private val account: BaseAccount,
    private val selectedChain: BaseChain
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_EVM_HEADER = 0
        const val VIEW_TYPE_EVM_ITEM = 1
        const val VIEW_TYPE_COSMOS_HEADER = 2
        const val VIEW_TYPE_COSMOS_ITEM = 3
        const val VIEW_TYPE_FOOTER = 4
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_EVM_HEADER, VIEW_TYPE_COSMOS_HEADER -> {
                val binding = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                AddressHeaderViewHolder(binding)
            }

            VIEW_TYPE_EVM_ITEM, VIEW_TYPE_COSMOS_ITEM -> {
                val binding =
                    ItemReceiveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ReceiveViewHolder(parent.context, binding)
            }

            VIEW_TYPE_FOOTER -> {
                val binding =
                    ItemByFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ByFooterViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AddressHeaderViewHolder -> {
                holder.bind(position)
            }

            is ReceiveViewHolder -> {
                if (holder.itemViewType == VIEW_TYPE_EVM_ITEM) {
                    holder.evmBind(account, selectedChain)
                } else {
                    holder.bind(account, selectedChain)
                }
            }

            is ByFooterViewHolder -> {
                holder.bind()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (selectedChain.supportEvm) {
            when (position) {
                0 -> VIEW_TYPE_EVM_HEADER
                1 -> VIEW_TYPE_EVM_ITEM
                2 -> VIEW_TYPE_COSMOS_HEADER
                3 -> VIEW_TYPE_COSMOS_ITEM
                else -> VIEW_TYPE_FOOTER
            }
        } else {
            when (position) {
                0 -> VIEW_TYPE_COSMOS_HEADER
                1 -> VIEW_TYPE_COSMOS_ITEM
                else -> VIEW_TYPE_FOOTER
            }
        }
    }

    override fun getItemCount(): Int {
        return if (selectedChain.supportEvm) {
            5
        } else {
            3
        }
    }

    inner class AddressHeaderViewHolder(
        private val binding: ItemHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.apply {
                if (itemCount == 5) {
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

    inner class ByFooterViewHolder(
        private val binding: ItemByFooterBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {}
    }
}