package wannabit.io.cosmostaion.ui.option.tx.address

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.database.model.RefAddress
import wannabit.io.cosmostaion.databinding.ItemAddressBookBinding

class AddressBookAdapter(
    private val toChain: BaseChain,
    private val refEvmAddresses: MutableList<RefAddress>,
    private val refAddresses: MutableList<RefAddress>,
    private val evmAddressBooks: MutableList<AddressBook>,
    private val addressBooks: MutableList<AddressBook>
) : RecyclerView.Adapter<AddressBookViewHolder>() {

    private var onItemClickListener: ((RefAddress?, AddressBook?) -> Unit)? = null

    companion object {
        const val VIEW_TYPE_ADDRESS_BOOK_ITEM = 0
        const val VIEW_TYPE_ADDRESS_BOOK_EVM_ITEM = 1
        const val VIEW_TYPE_MY_ACCOUNT_ITEM = 2
        const val VIEW_TYPE_MY_ACCOUNT_EVM_ITEM = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressBookViewHolder {
        val binding = ItemAddressBookBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AddressBookViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: AddressBookViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_ADDRESS_BOOK_ITEM -> {
                val addressBook = addressBooks[position]
                holder.bookBind(addressBook, addressBooks.size)

                holder.itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(null, addressBook)
                    }
                }
            }

            VIEW_TYPE_ADDRESS_BOOK_EVM_ITEM -> {
                val evmAddressBook = evmAddressBooks[position - (addressBooks.size)]
                holder.evmBookBind(evmAddressBook, addressBooks.size, evmAddressBooks.size)

                holder.itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(null, evmAddressBook)
                    }
                }
            }

            VIEW_TYPE_MY_ACCOUNT_ITEM -> {
                val index = position - (addressBooks.size + evmAddressBooks.size)
                val refAddress = refAddresses[index]
                holder.accountBind(toChain, refAddress, index, refAddresses.size)

                holder.itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(refAddress, null)
                    }
                }
            }

            else -> {
                val index = position - (addressBooks.size + evmAddressBooks.size + refAddresses.size)
                val refEvmAddress =
                    refEvmAddresses[position - (addressBooks.size + evmAddressBooks.size + refAddresses.size)]
                holder.accountEvmBind(refEvmAddress, index, refEvmAddresses.size)

                holder.itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(refEvmAddress, null)
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < addressBooks.size) VIEW_TYPE_ADDRESS_BOOK_ITEM
        else if (position < addressBooks.size + evmAddressBooks.size) VIEW_TYPE_ADDRESS_BOOK_EVM_ITEM
        else if (position < addressBooks.size + evmAddressBooks.size + refAddresses.size) VIEW_TYPE_MY_ACCOUNT_ITEM
        else VIEW_TYPE_MY_ACCOUNT_EVM_ITEM
    }

    override fun getItemCount(): Int {
        return addressBooks.size + evmAddressBooks.size + refAddresses.size + refEvmAddresses.size
    }

    fun setOnItemClickListener(listener: (RefAddress?, AddressBook?) -> Unit) {
        onItemClickListener = listener
    }
}
