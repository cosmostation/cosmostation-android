package wannabit.io.cosmostaion.ui.main.setting.account

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.ItemPrivateBinding

class PrivateAdapter(val account: BaseAccount) :
    ListAdapter<Any, PrivateViewHolder>(PrivateDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrivateViewHolder {
        val binding = ItemPrivateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PrivateViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: PrivateViewHolder, position: Int) {
        val line = currentList[position] as CosmosLine
        holder.bind(account, line)
    }

    private class PrivateDiffCallback : DiffUtil.ItemCallback<Any>() {

        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }
    }
}