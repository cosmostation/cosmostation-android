package wannabit.io.cosmostaion.ui.option.tx.address

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin84
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
            addressBookTitle.text = "Address book"
            addressBookCnt.text = cnt.toString()

            accountName.text = addressBook.bookName
            accountAddress.text = addressBook.address
            accountMemo.text = addressBook.memo
        }
    }

    fun accountBind(refAddress: RefAddress, position: Int, cnt: Int) {
        binding.apply {
            addressTitleLayout.visibleOrGone(position == 0)
            topView.visibleOrGone(position == 0)
            addressBookTitle.text = "My account"
            addressBookCnt.text = cnt.toString()

            CoroutineScope(Dispatchers.IO).launch {
                val account =
                    AppDatabase.getInstance().baseAccountDao().selectAccount(refAddress.accountId)

                withContext(Dispatchers.Main) {
                    accountName.text = account?.name
                    accountAddress.text = refAddress.dpAddress

                    allChains().firstOrNull { it.tag == refAddress.chainTag }?.let { chain ->
                        if (chain is ChainBitCoin84) {
                            chainTypeBadge.visibility = View.GONE
                            if (chain.accountKeyType.pubkeyType == PubKeyType.BTC_NATIVE_SEGWIT) {
                                chainBitSideBadge.visibility = View.VISIBLE
                                chainBadge.visibility = View.GONE

                            } else {
                                chainBitSideBadge.visibility = View.GONE
                                chainBadge.visibility = View.VISIBLE
                                chainBadge.text = if (chain.accountKeyType.pubkeyType == PubKeyType.BTC_NESTED_SEGWIT) {
                                    "NESTED SEGWIT"
                                } else {
                                    "LEGACY"
                                }
                            }

                        } else {
                            chainBitSideBadge.visibility = View.GONE
                            if (!chain.isDefault) {
                                chainBadge.visibility = View.VISIBLE
                                chainBadge.text = context.getString(R.string.str_old)
                                chainBadge.setBackgroundResource(R.drawable.round_box_deprecated)
                                chainBadge.setTextColor(
                                    ContextCompat.getColor(
                                        context, R.color.color_base02
                                    )
                                )
                                when (chain.tag) {
                                    "okt996_Keccak" -> {
                                        chainTypeBadge.text =
                                            context.getString(R.string.str_ethsecp256k1)
                                        chainTypeBadge.visibility = View.VISIBLE
                                    }

                                    "okt996_Secp" -> {
                                        chainTypeBadge.text = context.getString(R.string.str_secp256k1)
                                        chainTypeBadge.visibility = View.VISIBLE
                                    }

                                    else -> {
                                        chainTypeBadge.visibility = View.GONE
                                    }
                                }

                            } else {
                                chainBadge.visibility = View.GONE
                                chainTypeBadge.visibility = View.GONE
                            }
                        }
                    }
                }
            }
        }
    }

    fun evmBookBind(evmAddressBook: AddressBook, cnt: Int) {
        binding.apply {
            addressTitleLayout.visibleOrGone(adapterPosition == 0)
            topView.visibleOrGone(adapterPosition == 0)
            addressBookTitle.text = "Address book"
            addressBookCnt.text = cnt.toString()

            accountName.text = evmAddressBook.bookName
            accountAddress.text = evmAddressBook.address
            accountMemo.text = ""
        }
    }

    fun accountEvmBind(refEvmAddress: RefAddress, position: Int, cnt: Int) {
        binding.apply {
            addressTitleLayout.visibleOrGone(position == 0)
            topView.visibleOrGone(position == 0)
            addressBookTitle.text = "My account"
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