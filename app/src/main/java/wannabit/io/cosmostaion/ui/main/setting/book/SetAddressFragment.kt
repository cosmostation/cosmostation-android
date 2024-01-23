package wannabit.io.cosmostaion.ui.main.setting.book

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.allCosmosLines
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.databinding.FragmentSetAddressBinding
import wannabit.io.cosmostaion.ui.viewmodel.address.AddressBookViewModel
import java.util.Calendar

class SetAddressFragment(
    private val addressBook: AddressBook?,
    private val recipientLine: CosmosLine?,
    private val recipientAddress: String?,
    private val memo: String?
) : BottomSheetDialogFragment() {

    private var _binding: FragmentSetAddressBinding? = null
    private val binding get() = _binding!!

    private val addressBookViewModel: AddressBookViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSetAddressBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
    }

    private fun initView() {
        binding.apply {
            if (addressBook != null) {
                nameTxt.text = Editable.Factory.getInstance().newEditable(addressBook.bookName)
                addressTxt.text = Editable.Factory.getInstance().newEditable(addressBook.address)
                if (memo?.isNotEmpty() == true) {
                    memoTxt.text = Editable.Factory.getInstance().newEditable(memo)
                } else {
                    memoTxt.text = Editable.Factory.getInstance().newEditable(addressBook.memo)
                }

            } else if (recipientAddress?.isNotEmpty() == true) {
                addressTxt.text = Editable.Factory.getInstance().newEditable(recipientAddress)
                memoTxt.text = Editable.Factory.getInstance().newEditable(memo)
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnConfirm.setOnClickListener {
                val nameInput = nameTxt.text.toString().trim()
                if (nameInput.isEmpty()) {
                    requireContext().makeToast(R.string.error_name)
                    return@setOnClickListener
                }
                val addressInput = addressTxt.text.toString().trim()
                if (addressInput.isEmpty()) {
                    requireContext().makeToast(R.string.error_invalid_address)
                    return@setOnClickListener
                }
                val memoInput = memoTxt.text.toString().trim()

                if (addressBook != null) {
                    val line = allCosmosLines().firstOrNull { it.name == addressBook.chainName }
                    if (line != null) {
                        if (BaseUtils.isValidChainAddress(line, addressInput)) {
                            addressBook.bookName = nameInput
                            addressBook.address = addressInput
                            addressBook.memo = memoInput
                            addressBook.lastTime = Calendar.getInstance().timeInMillis
                            addressBookViewModel.updateAddressBook(addressBook)
                            dismiss()

                        } else {
                            requireContext().makeToast(R.string.error_invalid_address)
                            return@setOnClickListener
                        }
                    }

                } else if (recipientAddress?.isNotEmpty() == true) {
                    if (recipientLine != null) {
                        val addressBook = AddressBook(
                            nameInput,
                            recipientLine.name,
                            addressInput,
                            memoInput,
                            Calendar.getInstance().timeInMillis
                        )
                        addressBookViewModel.updateAddressBook(addressBook)
                        dismiss()

                    } else {
                        requireContext().makeToast(R.string.error_invalid_address)
                        return@setOnClickListener
                    }

                } else {
                    val line =
                        allCosmosLines().firstOrNull { addressInput.startsWith(it.accountPrefix + "1") }
                    if (line != null) {
                        if (BaseUtils.isValidChainAddress(line, addressInput)) {
                            val addressBook = AddressBook(
                                nameInput,
                                line.name,
                                addressInput,
                                memoInput,
                                Calendar.getInstance().timeInMillis
                            )
                            addressBookViewModel.updateAddressBook(addressBook)
                            dismiss()

                        } else {
                            requireContext().makeToast(R.string.error_invalid_address)
                            return@setOnClickListener
                        }

                    } else {
                        requireContext().makeToast(R.string.error_invalid_address)
                        return@setOnClickListener
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}