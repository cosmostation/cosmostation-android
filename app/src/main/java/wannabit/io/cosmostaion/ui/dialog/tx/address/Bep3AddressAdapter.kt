package wannabit.io.cosmostaion.ui.dialog.tx.address

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.databinding.ItemBep3AddressBinding

class Bep3AddressAdapter : ListAdapter<CosmosLine, Bep3AddressViewHolder>(ChainDiffCallback()) {

    private var onItemClickListener: ((String?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Bep3AddressViewHolder {
        val binding = ItemBep3AddressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Bep3AddressViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: Bep3AddressViewHolder, position: Int) {
        val line = currentList[position]
        holder.bind(line)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(line.address)
            }
        }
    }

    private class ChainDiffCallback : DiffUtil.ItemCallback<CosmosLine>() {

        override fun areItemsTheSame(oldItem: CosmosLine, newItem: CosmosLine): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: CosmosLine, newItem: CosmosLine): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (String?) -> Unit) {
        onItemClickListener = listener
    }
}