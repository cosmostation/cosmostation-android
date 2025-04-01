package wannabit.io.cosmostaion.ui.main.dapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.databinding.ItemDappChainBinding

class DappChainAdapter(private val selectChain: String?) :
    ListAdapter<String, DappChainViewHolder>(DappChainDiffCallback()) {

    private var onItemClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DappChainViewHolder {
        val binding =
            ItemDappChainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DappChainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DappChainViewHolder, position: Int) {
        val chain = currentList[position]
        holder.bind(chain, selectChain)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(chain)
            }
        }
    }

    private class DappChainDiffCallback : DiffUtil.ItemCallback<String>() {

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
