package wannabit.io.cosmostaion.ui.dialog.account

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.ItemAccountSelectBinding

class AccountSelectAdapter(
    val context: Context
) : ListAdapter<BaseAccount, AccountSelectViewHolder>(ChainEditDiffCallback()) {

    private var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountSelectViewHolder {
        val binding = ItemAccountSelectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AccountSelectViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: AccountSelectViewHolder, position: Int) {
        val account = currentList[position]
        holder.bind(account)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(position) }
        }
    }

    private class ChainEditDiffCallback : DiffUtil.ItemCallback<BaseAccount>() {

        override fun areItemsTheSame(oldItem: BaseAccount, newItem: BaseAccount): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: BaseAccount, newItem: BaseAccount): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }
}