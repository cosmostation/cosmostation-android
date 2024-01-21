package wannabit.io.cosmostaion.ui.main.setting.account

import android.content.Context
import android.content.Intent
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
import wannabit.io.cosmostaion.ui.intro.IntroActivity

class AccountListAdapter(
    val context: Context,
    private var mnemonicAccounts: List<BaseAccount>,
    private var privateAccounts: List<BaseAccount>,
    val listener: AccountSortListener
) : ListAdapter<BaseAccount, RecyclerView.ViewHolder>(AccountListDiffCallback()),
    ItemTouchHelperListener {

    private var onItemClickListener: ((BaseAccount) -> Unit)? = null

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
                if (mnemonicAccounts.isEmpty() && privateAccounts.isEmpty()) {
                    Intent(context, IntroActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        context.startActivity(this)
                    }

                } else {
                    if (mnemonicAccounts.isNotEmpty()) {
                        if (holder.itemViewType == VIEW_TYPE_MNEMONIC_ITEM) {
                            val mnemonicAccount = mnemonicAccounts[position - 1]
                            holder.bind(mnemonicAccount)

                            holder.itemView.setOnClickListener {
                                onItemClickListener?.let {
                                    it(mnemonicAccount)
                                }
                            }

                        } else {
                            val privateAccount =
                                privateAccounts[position - (mnemonicAccounts.size + 2)]
                            holder.bind(privateAccount)

                            holder.itemView.setOnClickListener {
                                onItemClickListener?.let {
                                    it(privateAccount)
                                }
                            }
                        }

                    } else {
                        if (holder.itemViewType == VIEW_TYPE_PRIVATE_ITEM) {
                            val privateAccount = privateAccounts[position - 1]
                            holder.bind(privateAccount)

                            holder.itemView.setOnClickListener {
                                onItemClickListener?.let {
                                    it(privateAccount)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (mnemonicAccounts.isEmpty() && privateAccounts.isEmpty()) return VIEW_TYPE_PRIVATE_ITEM
        return if (mnemonicAccounts.isNotEmpty()) {
            when {
                position == 0 -> VIEW_TYPE_MNEMONIC_HEADER
                position < 1 + mnemonicAccounts.size -> VIEW_TYPE_MNEMONIC_ITEM
                position < 2 + mnemonicAccounts.size -> VIEW_TYPE_PRIVATE_HEADER
                else -> VIEW_TYPE_PRIVATE_ITEM
            }
        } else {
            if (position == 0) VIEW_TYPE_PRIVATE_HEADER else VIEW_TYPE_PRIVATE_ITEM
        }
    }

    override fun getItemCount(): Int {
        return currentList.size + if (mnemonicAccounts.isNotEmpty() && privateAccounts.isNotEmpty()) 2 else 1
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
                    headerCnt.text =
                        currentList.filter { it.type == BaseAccountType.MNEMONIC }.size.toString()

                } else {
                    headerTitle.text = context.getString(R.string.title_private_account)
                    headerCnt.text =
                        currentList.filter { it.type == BaseAccountType.PRIVATE_KEY }.size.toString()
                }
            }
        }
    }

    fun setOnItemClickListener(listener: (BaseAccount) -> Unit) {
        onItemClickListener = listener
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        when {
            getItemViewType(fromPosition) == VIEW_TYPE_MNEMONIC_ITEM -> {
                val mnemonicFromPosition = fromPosition - 1
                val mnemonicToPosition = toPosition - 1
                if (mnemonicToPosition < 0) {
                    return false
                }
                val fromItem = mnemonicAccounts[mnemonicFromPosition]
                val toItem = mnemonicAccounts[mnemonicToPosition]

                val tempMnemonicAccountsList = mnemonicAccounts.toMutableList()
                tempMnemonicAccountsList[mnemonicFromPosition] = toItem
                tempMnemonicAccountsList[mnemonicToPosition] = fromItem

                mnemonicAccounts = tempMnemonicAccountsList

                notifyItemMoved(fromPosition, toPosition)
                listener.updateMnemonicOrder(mnemonicAccounts)
            }

            getItemViewType(fromPosition) == VIEW_TYPE_PRIVATE_ITEM -> {
                val privateFromPosition = fromPosition - mnemonicAccounts.size - 2
                val privateToPosition = toPosition - mnemonicAccounts.size - 2
                if (privateToPosition < 0) {
                    return false
                }
                val fromItem = privateAccounts[privateFromPosition]
                val toItem = privateAccounts[privateToPosition]

                val tempPrivateAccountsList = privateAccounts.toMutableList()
                tempPrivateAccountsList[privateFromPosition] = toItem
                tempPrivateAccountsList[privateToPosition] = fromItem

                privateAccounts = tempPrivateAccountsList

                notifyItemMoved(fromPosition, toPosition)
                listener.updatePrivateOrder(privateAccounts)
            }
        }
        return true
    }

    interface AccountSortListener {
        fun updateMnemonicOrder(mnemonicAccounts: List<BaseAccount>)
        fun updatePrivateOrder(privateAccounts: List<BaseAccount>)
    }
}