package wannabit.io.cosmostaion.ui.main.setting.wallet.book

import android.view.View
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

            addressName.text = addressBook.bookName
            address.text = addressBook.address
            if (addressBook.memo.isNotEmpty()) {
                memoTitle.visibility = View.VISIBLE
                addressBookMemo.visibility = View.VISIBLE
                addressBookMemo.text = addressBook.memo
            } else {
                memoTitle.visibility = View.GONE
                addressBookMemo.visibility = View.GONE
            }

            if (addressBook.chainName == "EVM-universal") {
                chainImg.setImageResource(R.drawable.evm_universal)
                chainImgName.text = "EVM Networks(Universal)"

            } else {
                allChains().firstOrNull { it.tag == addressBook.chainName }?.let { chain ->
                    chainImg.setChainLogo(chain)
                    chainImgName.text = chain.getChainName()
                }
            }
        }
    }
}