package wannabit.io.cosmostaion.ui.tx.option.address

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.databinding.ItemAddressBookBinding
import wannabit.io.cosmostaion.databinding.ItemMyAccountBinding

class AddressBookAdapter(
    private val addressBooks: MutableList<AddressBook>
) : RecyclerView.Adapter<AddressBookViewHolder>() {

    private var onItemClickListener: ((String, String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressBookViewHolder {
        val binding = ItemAddressBookBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AddressBookViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: AddressBookViewHolder, position: Int) {
        val addressBook = addressBooks[position]
        holder.bind(addressBook)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(addressBook.address, addressBook.memo)
            }
        }
    }

    override fun getItemCount(): Int {
        return addressBooks.size
    }

    fun setOnItemClickListener(listener: (String, String) -> Unit) {
        onItemClickListener = listener
    }
}
