package wannabit.io.cosmostaion.ui.main.chain.major

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.ItemByFooterBinding
import wannabit.io.cosmostaion.databinding.ItemHeaderBinding
import wannabit.io.cosmostaion.databinding.ItemReceiveBinding

class MajorReceiveAdapter(
    private val account: BaseAccount, private val selectedChain: BaseChain
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_EVM_HEADER = 0
        const val VIEW_TYPE_EVM_ITEM = 1
        const val VIEW_TYPE_FOOTER = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_EVM_HEADER -> {
                val binding = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                EvmAddressHeaderViewHolder(binding)
            }

            VIEW_TYPE_EVM_ITEM -> {
                val binding =
                    ItemReceiveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MajorReceiveViewHolder(parent.context, binding)
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
            is EvmAddressHeaderViewHolder -> {
                holder.bind()
            }

            is MajorReceiveViewHolder -> {
                holder.evmBind(account, selectedChain)
            }

            is ByFooterViewHolder -> {
                holder.bind()
            }
        }
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> VIEW_TYPE_EVM_HEADER
            1 -> VIEW_TYPE_EVM_ITEM
            else -> VIEW_TYPE_FOOTER
        }
    }

    inner class EvmAddressHeaderViewHolder(
        private val binding: ItemHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.apply {
                headerTitle.text = "My address"
            }
        }
    }

    inner class ByFooterViewHolder(
        binding: ItemByFooterBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {}
    }
}