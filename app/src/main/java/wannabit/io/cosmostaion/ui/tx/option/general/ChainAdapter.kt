package wannabit.io.cosmostaion.ui.tx.option.general

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.databinding.ItemChainBinding
import wannabit.io.cosmostaion.databinding.ItemTransferChainBinding

class ChainAdapter(
    private val fromChain: BaseChain?,
    private val toChain: BaseChain?,
    private val chainListType: ChainListType
) :
    ListAdapter<BaseChain, RecyclerView.ViewHolder>(ChainDiffCallback()) {

    private var onItemClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (chainListType) {
            ChainListType.SELECT_TRANSFER -> {
                val binding = ItemTransferChainBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                TransferChainViewHolder(parent.context, binding)
            }

            else -> {
                val binding =
                    ItemChainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ChainViewHolder(parent.context, binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chain = currentList[position]

        when (holder) {
            is TransferChainViewHolder -> {
                holder.bind(fromChain, toChain, chain)

                holder.itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(chain.name)
                    }
                }
            }

            is ChainViewHolder -> {
                holder.bind(chain, toChain)

                holder.itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(chain.name)
                    }
                }
            }
        }
    }

    private class ChainDiffCallback : DiffUtil.ItemCallback<BaseChain>() {

        override fun areItemsTheSame(oldItem: BaseChain, newItem: BaseChain): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: BaseChain, newItem: BaseChain): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}
