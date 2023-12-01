package wannabit.io.cosmostaion.ui.dialog.tx.address

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.database.model.RefAddress
import wannabit.io.cosmostaion.databinding.ItemAddressBinding

class AddressBookAdapter(
    private val addressType: AddressType?
) : ListAdapter<RefAddress, AddressBookViewHolder>(ChainDiffCallback()) {

    private var onItemClickListener: ((String?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressBookViewHolder {
        val binding = ItemAddressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddressBookViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: AddressBookViewHolder, position: Int) {
        val refAddress = currentList[position]
        holder.bind(refAddress, addressType)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(refAddress.dpAddress)
            }
        }
    }

    private class ChainDiffCallback : DiffUtil.ItemCallback<RefAddress>() {

        override fun areItemsTheSame(oldItem: RefAddress, newItem: RefAddress): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RefAddress, newItem: RefAddress): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (String?) -> Unit) {
        onItemClickListener = listener
    }
}
