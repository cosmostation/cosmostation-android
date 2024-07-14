package wannabit.io.cosmostaion.ui.main.setting.wallet.book

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.databinding.ItemSetAddressBookBinding

class SetAddressBookViewHolder(
    private val binding: ItemSetAddressBookBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(addressBook: AddressBook) {
        binding.apply {
            addressBookView.setBackgroundResource(R.drawable.item_bg)

            if (addressBook.address.startsWith("0x")) {
                addressMemo.visibility = View.GONE
                chainImg.setImageResource(R.drawable.chain_default)

            } else {
                allChains().firstOrNull { it.name == addressBook.chainName }?.let { chain ->
                    chainImg.setImageResource(chain.logo)
                }
            }
            addressName.text = addressBook.bookName
            addressMemo.text = addressBook.memo
            address.text = addressBook.address
        }
    }
}