package wannabit.io.cosmostaion.ui.option.tx.address

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.database.model.RefAddress
import wannabit.io.cosmostaion.databinding.ItemAddressBookBinding

class EvmAddressBookAdapter(
    private val refEvmAddresses: MutableList<RefAddress>,
    private val evmAddressBooks: MutableList<AddressBook>
    ) : RecyclerView.Adapter<AddressBookViewHolder>() {

    private var onItemClickListener: ((String, String) -> Unit)? = null

    companion object {
        const val VIEW_TYPE_ADDRESS_BOOK_EVM_ITEM = 0
        const val VIEW_TYPE_MY_ACCOUNT_EVM_ITEM = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressBookViewHolder {
        val binding = ItemAddressBookBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AddressBookViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: AddressBookViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_ADDRESS_BOOK_EVM_ITEM -> {
                val evmAddressBook = evmAddressBooks[position]
                holder.evmBookBind(evmAddressBook, evmAddressBooks.size)

                holder.itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(evmAddressBook.address, evmAddressBook.memo)
                    }
                }
            }

            VIEW_TYPE_MY_ACCOUNT_EVM_ITEM -> {
                val index = position - evmAddressBooks.size
                val refEvmAddress = refEvmAddresses[index]
                holder.accountEvmBind(refEvmAddress, index, refEvmAddresses.size)

                holder.itemView.setOnClickListener {
                    onItemClickListener?.let {
                        refEvmAddress.evmAddress?.let { address ->
                            it(address, "")
                        }
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < evmAddressBooks.size) VIEW_TYPE_ADDRESS_BOOK_EVM_ITEM
        else VIEW_TYPE_MY_ACCOUNT_EVM_ITEM
    }

    override fun getItemCount(): Int {
        return evmAddressBooks.size + refEvmAddresses.size
    }

    fun setOnItemClickListener(listener: (String, String) -> Unit) {
        onItemClickListener = listener
    }
}