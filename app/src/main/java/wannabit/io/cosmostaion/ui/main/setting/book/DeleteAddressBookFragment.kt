package wannabit.io.cosmostaion.ui.main.setting.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.databinding.FragmentDeleteAddressBookBinding
import wannabit.io.cosmostaion.ui.viewmodel.address.AddressBookViewModel

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
        }
    }

    private fun setUpClickAction() {
        binding.btnDelete.setOnClickListener {
            addressBookViewModel.deleteAddressBook(addressBook)
            dismiss()
        }
    }
}
