package wannabit.io.cosmostaion.ui.main.setting.book

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.databinding.ItemSetAddressBookBinding

class SetAddressBookAdapter :
    ListAdapter<AddressBook, SetAddressBookViewHolder>(SetAddressDiffCallback()) {

    private var onItemClickListener: ((AddressBook) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetAddressBookViewHolder {
        val binding =
            ItemSetAddressBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SetAddressBookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SetAddressBookViewHolder, position: Int) {
        val addressBook = currentList[position]
        holder.bind(addressBook)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(addressBook)
            }
        }
    }

    private class SetAddressDiffCallback : DiffUtil.ItemCallback<AddressBook>() {

        override fun areItemsTheSame(oldItem: AddressBook, newItem: AddressBook): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: AddressBook, newItem: AddressBook): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (AddressBook) -> Unit) {
        onItemClickListener = listener
    }
}