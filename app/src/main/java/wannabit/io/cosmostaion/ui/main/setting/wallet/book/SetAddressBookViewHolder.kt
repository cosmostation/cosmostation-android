package wannabit.io.cosmostaion.ui.main.setting.wallet.book

import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.common.setChainLogo
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.databinding.ItemSetAddressBookBinding

class SetAddressBookViewHolder(
    private val binding: ItemSetAddressBookBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(addressBook: AddressBook) {
        binding.apply {
            addressBookView.setBackgroundResource(R.drawable.item_bg)

            if (addressBook.chainName == "EVM-universal") {
                chainImg.setImageResource(R.drawable.evm_universal)

            } else {
                allChains().firstOrNull { it.tag == addressBook.chainName }?.let { chain ->
                    chainImg.setChainLogo(chain)
                }
            }

            addressName.text = addressBook.bookName
            addressMemo.text = addressBook.memo
            address.text = addressBook.address
        }
    }
}