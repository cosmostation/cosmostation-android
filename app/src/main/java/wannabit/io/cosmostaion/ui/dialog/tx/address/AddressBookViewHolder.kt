package wannabit.io.cosmostaion.ui.dialog.tx.address

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.databinding.ItemAddressBookBinding

class AddressBookViewHolder(
    val context: Context,
    private val binding: ItemAddressBookBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(addressBook: AddressBook) {
        binding.apply {
            accountName.text = addressBook.bookName

            accountSubAddress.visibility = View.GONE
            accountAddress.text = addressBook.address
            accountMemo.text = addressBook.memo
        }
    }
}