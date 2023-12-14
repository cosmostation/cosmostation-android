package wannabit.io.cosmostaion.ui.main.setting.account

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.databinding.ItemSetAddressBookBinding

class SetAddressBookAdapter(
    private var listener: ClickListener
) : ListAdapter<AddressBook, SetAddressBookViewHolder>(SetAddressDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetAddressBookViewHolder {
        val binding = ItemSetAddressBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SetAddressBookViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: SetAddressBookViewHolder, position: Int) {
        val addressBook = currentList[position]
        holder.bind(addressBook, listener)
    }

    private class SetAddressDiffCallback : DiffUtil.ItemCallback<AddressBook>() {

        override fun areItemsTheSame(oldItem: AddressBook, newItem: AddressBook): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: AddressBook, newItem: AddressBook): Boolean {
            return oldItem == newItem
        }
    }

    interface ClickListener {
        fun editAddressBook(book: AddressBook, position: Int)
        fun deleteAddressBook(book: AddressBook)
    }
}