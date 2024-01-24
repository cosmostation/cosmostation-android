package wannabit.io.cosmostaion.ui.main.setting.wallet.account

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.databinding.ItemMnemonicBinding

class MnemonicAdapter(
    val context: Context
) : ListAdapter<String, MnemonicViewHolder>(MnemonicDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MnemonicViewHolder {
        val binding = ItemMnemonicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MnemonicViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: MnemonicViewHolder, position: Int) {
        holder.bind(currentList, position)
    }

    private class MnemonicDiffCallback : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}