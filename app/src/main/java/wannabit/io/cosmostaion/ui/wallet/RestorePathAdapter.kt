package wannabit.io.cosmostaion.ui.wallet

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.databinding.ItemMnemonicBinding

class RestorePathAdapter(
    val context: Context
) : ListAdapter<String, RestorePathViewHolder>(PathDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestorePathViewHolder {
        val binding = ItemMnemonicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestorePathViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: RestorePathViewHolder, position: Int) {
        holder.bind(currentList, position)
    }

    private class PathDiffCallback : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}