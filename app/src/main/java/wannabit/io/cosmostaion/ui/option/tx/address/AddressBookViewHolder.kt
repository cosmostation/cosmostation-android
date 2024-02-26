package wannabit.io.cosmostaion.ui.option.tx.address

import android.content.Context
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.allCosmosLines
import wannabit.io.cosmostaion.chain.allEvmLines
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.database.model.RefAddress
import wannabit.io.cosmostaion.databinding.ItemAddressBookBinding

class AddressBookViewHolder(
    val context: Context, private val binding: ItemAddressBookBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bookBind(addressBook: AddressBook, cnt: Int) {
        binding.apply {
            addressTitleLayout.visibleOrGone(adapterPosition == 0)
            topView.visibleOrGone(adapterPosition == 0)
            addressBookTitle.text = "Address book (Cosmos style)"
            addressBookCnt.text = cnt.toString()

            accountName.text = addressBook.bookName
            accountAddress.text = addressBook.address
            accountMemo.text = addressBook.memo
        }
    }

    fun evmBookBind(evmAddressBook: AddressBook, position: Int, cnt: Int) {
        binding.apply {
            addressTitleLayout.visibleOrGone(position == 0)
            topView.visibleOrGone(position == 0)
            addressBookTitle.text = "Address book (Evm style)"
            addressBookCnt.text = cnt.toString()

            accountName.text = evmAddressBook.bookName
            accountAddress.text = evmAddressBook.address
            accountMemo.text = ""
        }
    }

    fun accountBind(toChain: BaseChain, refAddress: RefAddress, position: Int, cnt: Int) {
        binding.apply {
            addressTitleLayout.visibleOrGone(position == 0)
            topView.visibleOrGone(position == 0)
            addressBookTitle.text = "My account (Cosmos style)"
            addressBookCnt.text = cnt.toString()

            CoroutineScope(Dispatchers.IO).launch {
                val account =
                    AppDatabase.getInstance().baseAccountDao().selectAccount(refAddress.accountId)

                withContext(Dispatchers.Main) {
                    accountName.text = account?.name
                    accountAddress.text = refAddress.dpAddress

                    allCosmosLines().firstOrNull { it.tag == refAddress.chainTag }?.let { chain ->
                        if (chain.evmCompatible) {
                            chainBadge.visibility = View.VISIBLE
                            chainBadge.text = context.getString(R.string.str_evm)
                            chainBadge.setBackgroundResource(R.drawable.round_box_evm)
                            chainBadge.setTextColor(
                                ContextCompat.getColor(
                                    context, R.color.color_base01
                                )
                            )

                        } else if (!chain.isDefault) {
                            chainBadge.visibility = View.VISIBLE
                            chainBadge.text = context.getString(R.string.str_legacy)
                            chainBadge.setBackgroundResource(R.drawable.round_box_deprecated)
                            chainBadge.setTextColor(
                                ContextCompat.getColor(
                                    context, R.color.color_base02
                                )
                            )
//                            if (toChain is ChainOkt60) {
//                                accountAddress.text = ByteUtils.convertBech32ToEvm(refAddress.dpAddress)
//                            } else {
//                                accountAddress.text = refAddress.dpAddress
//                            }

                        } else {
                            chainBadge.visibility = View.GONE
                        }

                    } ?: run {
                        allEvmLines().firstOrNull { it.tag == refAddress.chainTag }?.let {
                            chainBadge.visibility = View.VISIBLE
                            chainBadge.text = context.getString(R.string.str_evm)
                            chainBadge.setBackgroundResource(R.drawable.round_box_evm)
                            chainBadge.setTextColor(
                                ContextCompat.getColor(
                                    context, R.color.color_base01
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    fun accountEvmBind(refEvmAddress: RefAddress, position: Int, cnt: Int) {
        binding.apply {
            addressTitleLayout.visibleOrGone(position == 0)
            topView.visibleOrGone(position == 0)
            addressBookTitle.text = "My account (Evm style)"
            addressBookCnt.text = cnt.toString()

            CoroutineScope(Dispatchers.IO).launch {
                val account = AppDatabase.getInstance().baseAccountDao()
                    .selectAccount(refEvmAddress.accountId)

                withContext(Dispatchers.Main) {
                    accountName.text = account?.name
                    accountAddress.text = refEvmAddress.evmAddress
                }
            }
        }
    }
}