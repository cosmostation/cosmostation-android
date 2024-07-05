package wannabit.io.cosmostaion.ui.main.chain.evm

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.ItemHeaderBinding
import wannabit.io.cosmostaion.databinding.ItemReceiveBinding

class EvmReceiveAdapter(
    val context: Context, private val account: BaseAccount, private val selectedChain: BaseChain
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_EVM_HEADER = 0
        const val VIEW_TYPE_EVM_ITEM = 1
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
                EvmReceiveViewHolder(parent.context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EvmAddressHeaderViewHolder -> {
                holder.bind()
            }

            is EvmReceiveViewHolder -> {
                holder.evmBind(account, selectedChain)
            }
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_EVM_HEADER
        else VIEW_TYPE_EVM_ITEM
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
}