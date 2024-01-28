package wannabit.io.cosmostaion.ui.option.tx.address

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.database.model.RefAddress
import wannabit.io.cosmostaion.databinding.ItemAddressBinding
import wannabit.io.cosmostaion.databinding.ItemAddressBookBinding
import wannabit.io.cosmostaion.databinding.ItemAddressBookHeaderBinding

class AddressBookAdapter(
    private val targetChain: CosmosLine,
    private val refAddresses: MutableList<RefAddress>,
    private val addressBooks: MutableList<AddressBook>,
    private val addressType: AddressType?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onItemClickListener: ((RefAddress?, AddressBook?) -> Unit)? = null

    companion object {
        const val VIEW_TYPE_ADDRESS_BOOK_HEADER = 0
        const val VIEW_TYPE_ADDRESS_BOOK_ITEM = 1
        const val VIEW_TYPE_MY_ACCOUNT_HEADER = 2
        const val VIEW_TYPE_MY_ACCOUNT_ITEM = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ADDRESS_BOOK_HEADER, VIEW_TYPE_MY_ACCOUNT_HEADER -> {
                val binding = ItemAddressBookHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                AddressBookHeaderViewHolder(binding)
            }

            VIEW_TYPE_ADDRESS_BOOK_ITEM -> {
                val binding = ItemAddressBookBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                AddressBookViewHolder(parent.context, binding)
            }

            VIEW_TYPE_MY_ACCOUNT_ITEM -> {
                val binding = ItemAddressBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                AddressAccountViewHolder(parent.context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AddressBookHeaderViewHolder -> {
                holder.bind(holder.itemViewType)
            }

            is AddressBookViewHolder -> {
                val addressBook = addressBooks[position - 1]
                holder.bind(addressBook)

                holder.itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(null, addressBook)
                    }
                }
            }

            is AddressAccountViewHolder -> {
                val refAddress: RefAddress = if (addressBooks.isNotEmpty()) {
                    refAddresses[position - (addressBooks.size + 2)]
                } else {
                    refAddresses[position - 1]
                }
                holder.bind(targetChain, refAddress, addressType)
                holder.itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(refAddress, null)
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (addressBooks.isNotEmpty()) {
            return if (position == 0) {
                VIEW_TYPE_ADDRESS_BOOK_HEADER
            } else if (position < addressBooks.size + 1) {
                VIEW_TYPE_ADDRESS_BOOK_ITEM
            } else if (position < addressBooks.size + 2) {
                VIEW_TYPE_MY_ACCOUNT_HEADER
            } else {
                VIEW_TYPE_MY_ACCOUNT_ITEM
            }

        } else {
            return if (position == 0) {
                VIEW_TYPE_MY_ACCOUNT_HEADER
            } else {
                VIEW_TYPE_MY_ACCOUNT_ITEM
            }
        }
    }

    override fun getItemCount(): Int {
        return if (addressBooks.isNotEmpty()) {
            if (refAddresses.isNotEmpty()) {
                addressBooks.size + refAddresses.size + 2
            } else {
                addressBooks.size + 1
            }

        } else {
            refAddresses.size + 1
        }
    }

    inner class AddressBookHeaderViewHolder(
        private val binding: ItemAddressBookHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewType: Int) {
            binding.apply {
                if (viewType == VIEW_TYPE_ADDRESS_BOOK_HEADER) {
                    addressBookTitle.text = "Address book"
                    addressBookCnt.text = addressBooks.size.toString()
                } else {
                    addressBookTitle.text = "My account"
                    addressBookCnt.text = refAddresses.size.toString()
                }
            }
        }
    }

    fun setOnItemClickListener(listener: (RefAddress?, AddressBook?) -> Unit) {
        onItemClickListener = listener
    }
}
