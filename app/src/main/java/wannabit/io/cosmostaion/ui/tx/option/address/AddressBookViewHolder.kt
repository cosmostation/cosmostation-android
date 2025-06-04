package wannabit.io.cosmostaion.ui.tx.option.address

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.databinding.ItemAddressBookBinding

class AddressBookViewHolder(
    val context: Context, private val binding: ItemAddressBookBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(addressBook: AddressBook) {
        binding.apply {
            accountName.text = addressBook.bookName
            accountAddress.text = addressBook.address
            memoLayout.visibleOrGone(addressBook.memo.isNotEmpty())
            accountMemo.text = addressBook.memo
        }
    }
}