package wannabit.io.cosmostaion.ui.main.setting.wallet.book

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.databinding.ItemSetChainBookBinding

class SetChainBookAdapter(
    private val toChain: String?,
) : ListAdapter<String, SetChainBookViewHolder>(ChainDiffCallback()) {

    private var onItemClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetChainBookViewHolder {
        val binding =
            ItemSetChainBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SetChainBookViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: SetChainBookViewHolder, position: Int) {
        val chainTag = currentList[position]
        holder.bind(chainTag, toChain)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(chainTag)
            }
        }
    }

    private class ChainDiffCallback : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}