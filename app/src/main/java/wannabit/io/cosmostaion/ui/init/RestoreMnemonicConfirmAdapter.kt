package wannabit.io.cosmostaion.ui.init

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.databinding.ItemMnemonicBinding

class RestoreMnemonicConfirmAdapter(
    val context: Context
) : ListAdapter<String, RestoreMnemonicConfirmViewHolder>(PathDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestoreMnemonicConfirmViewHolder {
        val binding = ItemMnemonicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestoreMnemonicConfirmViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: RestoreMnemonicConfirmViewHolder, position: Int) {
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