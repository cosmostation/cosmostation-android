package wannabit.io.cosmostaion.ui.option.tx.address

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.database.model.RefAddress
import wannabit.io.cosmostaion.databinding.ItemAddressBookBinding

class AddressBookAdapter(
    private val refAddresses: MutableList<RefAddress>,
    private val addressBooks: MutableList<AddressBook>
) : RecyclerView.Adapter<AddressBookViewHolder>() {

    private var onItemClickListener: ((String, String) -> Unit)? = null

    companion object {
        const val VIEW_TYPE_ADDRESS_BOOK_ITEM = 0
        const val VIEW_TYPE_MY_ACCOUNT_ITEM = 1
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
                        it(addressBook.address, addressBook.memo)
                    }
                }
            }

            VIEW_TYPE_MY_ACCOUNT_ITEM -> {
                val index = position - addressBooks.size
                val refAddress = refAddresses[index]
                holder.accountBind(refAddress, index, refAddresses.size)

                holder.itemView.setOnClickListener {
                    onItemClickListener?.let {
                        refAddress.dpAddress?.let { address ->
                            it(address, "")
                        }
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < addressBooks.size) VIEW_TYPE_ADDRESS_BOOK_ITEM
        else VIEW_TYPE_MY_ACCOUNT_ITEM
    }

    override fun getItemCount(): Int {
        return addressBooks.size + refAddresses.size
    }

    fun setOnItemClickListener(listener: (String, String) -> Unit) {
        onItemClickListener = listener
    }
}
