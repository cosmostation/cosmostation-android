package wannabit.io.cosmostaion.ui.main.setting.wallet.book

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.databinding.ItemDefaultHeaderBinding
import wannabit.io.cosmostaion.databinding.ItemSetAddressBookBinding

class SetAddressBookAdapter(private val addressBooks: MutableList<AddressBook>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_BOOK_HEADER = 0
        const val VIEW_TYPE_BOOK_ITEM = 1
    }

    private var onItemClickListener: ((AddressBook) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_BOOK_HEADER -> {
                val binding = ItemDefaultHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                AddressBookHeaderViewHolder(binding)
            }

            else -> {
                val binding = ItemSetAddressBookBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                SetAddressBookViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AddressBookHeaderViewHolder -> {
                holder.bind()
            }

            is SetAddressBookViewHolder -> {
                val addressBook = addressBooks[position - 1]
                holder.bind(addressBook)

                holder.itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(addressBook)
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            VIEW_TYPE_BOOK_HEADER
        } else {
            VIEW_TYPE_BOOK_ITEM
        }
    }

    override fun getItemCount(): Int {
        return addressBooks.size + 1
    }

    inner class AddressBookHeaderViewHolder(
        private val binding: ItemDefaultHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.apply {
                headerTitle.text = "Address List"
                headerCnt.text = addressBooks.size.toString()
            }
        }
    }

    fun setOnItemClickListener(listener: (AddressBook) -> Unit) {
        onItemClickListener = listener
    }
}