package wannabit.io.cosmostaion.ui.main.setting.wallet.book

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.databinding.ItemSetChainBookBinding

class SetChainBookAdapter(
    private val toChain: BaseChain?,
) : ListAdapter<BaseChain?, SetChainBookViewHolder>(ChainDiffCallback()) {

    private var onItemClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetChainBookViewHolder {
        val binding =
            ItemSetChainBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SetChainBookViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: SetChainBookViewHolder, position: Int) {
        val chain = currentList[position]
        holder.bind(chain, toChain)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                val tag = chain?.tag ?: "EVM-universal"
                it(tag)
            }
        }
    }

    private class ChainDiffCallback : DiffUtil.ItemCallback<BaseChain?>() {

        override fun areItemsTheSame(oldItem: BaseChain, newItem: BaseChain): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: BaseChain, newItem: BaseChain): Boolean {
            return oldItem.tag == newItem.tag
        }
    }

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}