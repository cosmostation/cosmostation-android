package wannabit.io.cosmostaion.ui.main.setting.wallet.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.common.setChainLogo
import wannabit.io.cosmostaion.data.viewmodel.address.AddressBookViewModel
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.databinding.FragmentDeleteAddressBookBinding

class DeleteAddressBookFragment(
    private val addressBook: AddressBook
) : BottomSheetDialogFragment() {

    private var _binding: FragmentDeleteAddressBookBinding? = null
    private val binding get() = _binding!!

    private val addressBookViewModel: AddressBookViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeleteAddressBookBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
    }

    private fun initView() {
        binding.apply {
            addressBookName.text = addressBook.bookName
            addressBookAddress.text = addressBook.address
            if (addressBook.memo.isNotEmpty()) {
                addressBookMemo.text = "Memo : " + addressBook.memo
            } else {
                addressBookMemo.visibility = View.GONE
            }

            if (addressBook.chainName == "EVM-universal") {
                chainImg.setImageResource(R.drawable.evm_universal)
                chainName.text = "EVM Networks(Universal)"

            } else {
                allChains().firstOrNull { it.tag == addressBook.chainName }?.let { chain ->
                    chainImg.setChainLogo(chain)
                    chainName.text = chain.getChainName()
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnCancel.setOnClickListener {
                dismiss()
            }

            btnDelete.setOnClickListener {
                addressBookViewModel.deleteAddressBook(addressBook)
                dismiss()
            }
        }
    }
}
