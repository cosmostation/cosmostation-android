package wannabit.io.cosmostaion.ui.main.setting.account

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.ItemAccountListBinding
import wannabit.io.cosmostaion.databinding.ItemStickyHeaderBinding

class AccountListAdapter(
    val context: Context,
    private val mnemonicAccounts: List<BaseAccount>,
    private val privateAccounts: List<BaseAccount>,
    private var listener: ClickListener
) : ListAdapter<BaseAccount, RecyclerView.ViewHolder>(AccountListDiffCallback()) {

    companion object {
        const val VIEW_TYPE_MNEMONIC_HEADER = 0
        const val VIEW_TYPE_MNEMONIC_ITEM = 1
        const val VIEW_TYPE_PRIVATE_HEADER = 2
        const val VIEW_TYPE_PRIVATE_ITEM = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_MNEMONIC_HEADER, VIEW_TYPE_PRIVATE_HEADER -> {
                val binding = ItemStickyHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                AccountListHeaderViewHolder(binding)
            }

            VIEW_TYPE_MNEMONIC_ITEM, VIEW_TYPE_PRIVATE_ITEM -> {
                val binding = ItemAccountListBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                AccountListViewHolder(context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AccountListHeaderViewHolder -> {
                holder.bind(holder.itemViewType)
            }

            is AccountListViewHolder -> {
                if (mnemonicAccounts.isNotEmpty()) {
                    if (holder.itemViewType == VIEW_TYPE_MNEMONIC_ITEM) {
                        val mnemonicAccount = mnemonicAccounts[position - 1]
                        holder.bind(mnemonicAccount, listener)

                    } else {
                        val privateAccount = privateAccounts[position - (mnemonicAccounts.size + 2)]
                        holder.bind(privateAccount, listener)
                    }

                } else {
                    if (holder.itemViewType == VIEW_TYPE_PRIVATE_ITEM) {
                        val privateAccount = privateAccounts[position - 1]
                        holder.bind(privateAccount, listener)
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (mnemonicAccounts.isNotEmpty()) {
            return if (position == 0) {
                VIEW_TYPE_MNEMONIC_HEADER
            } else if (position < mnemonicAccounts.size + 1) {
                VIEW_TYPE_MNEMONIC_ITEM
            } else if (position < mnemonicAccounts.size + 2) {
                VIEW_TYPE_PRIVATE_HEADER
            } else {
                VIEW_TYPE_PRIVATE_ITEM
            }

        } else {
            return if (position == 0) {
                VIEW_TYPE_PRIVATE_HEADER
            } else {
                VIEW_TYPE_PRIVATE_ITEM
            }
        }
    }

    override fun getItemCount(): Int {
        return if (mnemonicAccounts.isNotEmpty()) {
            if (privateAccounts.isNotEmpty()) {
                currentList.size + 2
            } else {
                currentList.size + 1
            }

        } else {
            currentList.size + 1
        }
    }

    private class AccountListDiffCallback : DiffUtil.ItemCallback<BaseAccount>() {

        override fun areItemsTheSame(oldItem: BaseAccount, newItem: BaseAccount): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BaseAccount, newItem: BaseAccount): Boolean {
            return oldItem == newItem
        }
    }

    inner class AccountListHeaderViewHolder(
        private val binding: ItemStickyHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewType: Int) {
            binding.apply {
                if (viewType == VIEW_TYPE_MNEMONIC_HEADER) {
                    headerTitle.text = context.getString(R.string.title_mnemonic_account)
                    headerCnt.text = currentList.filter { it.type == BaseAccountType.MNEMONIC }.size.toString()

                } else {
                    headerTitle.text = context.getString(R.string.title_private_account)
                    headerCnt.text = currentList.filter { it.type == BaseAccountType.PRIVATE_KEY }.size.toString()
                }
            }
        }
    }

    interface ClickListener {
        fun checkMnemonicAction(account: BaseAccount)
        fun checkPrivateAction(account: BaseAccount)
    }
}