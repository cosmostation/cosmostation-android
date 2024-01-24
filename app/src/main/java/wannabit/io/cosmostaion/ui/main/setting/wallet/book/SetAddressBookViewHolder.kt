package wannabit.io.cosmostaion.ui.main.setting.wallet.book

import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.allCosmosLines
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.databinding.ItemSetAddressBookBinding

class SetAddressBookViewHolder(
    private val binding: ItemSetAddressBookBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(addressBook: AddressBook) {
        binding.apply {
            addressBookView.setBackgroundResource(R.drawable.item_bg)

            allCosmosLines().firstOrNull { it.name == addressBook.chainName }?.let { line ->
                chainImg.setImageResource(line.logo)
            }
            addressName.text = addressBook.bookName
            addressMemo.text = addressBook.memo
            address.text = addressBook.address
        }
    }
}