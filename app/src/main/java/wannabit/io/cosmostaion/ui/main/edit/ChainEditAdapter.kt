package wannabit.io.cosmostaion.ui.main.edit

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.ItemEditBinding

class ChainEditAdapter(
    val context: Context,
    val account: BaseAccount,
    private val displayChainLines: MutableList<String>
) : ListAdapter<Any, ChainEditHolder>(ChainEditDiffCallback()) {

    companion object {
        const val VIEW_TYPE_COSMOS_ITEM = 1
        const val VIEW_TYPE_ETHEREUM_ITEM = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChainEditHolder {
        val binding = ItemEditBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChainEditHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: ChainEditHolder, position: Int) {
        val line = currentList[position] as CosmosLine
        holder.bind(account, line, itemCount, displayChainLines)
    }

    private class ChainEditDiffCallback : DiffUtil.ItemCallback<Any>() {

        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }
    }
}