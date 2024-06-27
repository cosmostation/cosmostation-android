package wannabit.io.cosmostaion.ui.main.setting.wallet.book

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.databinding.FragmentSetAddressBinding
import wannabit.io.cosmostaion.ui.viewmodel.address.AddressBookViewModel
import java.util.Calendar

class SetAddressFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSetAddressBinding? = null
    private val binding get() = _binding!!

    private var addressBook: AddressBook? = null
    private var toChain: BaseChain? = null
    private var recipientAddress: String = ""
    private var memo: String = ""
    private lateinit var addressBookType: AddressBookType

    private val addressBookViewModel: AddressBookViewModel by activityViewModels()

    companion object {
        @JvmStatic
        fun newInstance(
            addressBook: AddressBook?,
            toChain: BaseChain?,
            recipientAddress: String?,
            memo: String?,
            addressBookType: AddressBookType
        ): SetAddressFragment {
            val args = Bundle().apply {
                putParcelable("addressBook", addressBook)
                putSerializable("toChain", toChain)
                putString("recipientAddress", recipientAddress)
                putString("memo", memo)
                putSerializable("addressBookType", addressBookType)
            }
            val fragment = SetAddressFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSetAddressBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        textChange()
        setUpClickAction()
    }

    private fun initData() {
        binding.apply {
            arguments?.apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    getParcelable("addressBook", AddressBook::class.java)?.let { addressBook = it }
                    getSerializable("toChain", BaseChain::class.java)?.let { toChain = it }
                    getSerializable(
                        "addressBookType", AddressBookType::class.java
                    )?.let { addressBookType = it }

                } else {
                    (getParcelable("addressBook") as? AddressBook)?.let {
                        addressBook = it
                    }
                    (getSerializable("toChain") as? BaseChain)?.let {
                        toChain = it
                    }
                    (getSerializable("addressBookType") as? AddressBookType)?.let {
                        addressBookType = it
                    }
                }
                getString("recipientAddress")?.let { recipientAddress = it }
                getString("memo")?.let { memo = it }
            }

            initView()
        }
    }

    private fun initView() {
        binding.apply {
            when (addressBookType) {
                AddressBookType.ManualNew -> {
                    addressSetMsg.setText(R.string.str_set_address_msg)
                }

                AddressBookType.ManualEdit -> {
                    nameTxt.text = Editable.Factory.getInstance().newEditable(addressBook?.bookName)
                    addressTxt.text =
                        Editable.Factory.getInstance().newEditable(addressBook?.address)
                    if (memo.isNotEmpty()) {
                        memoTxt.text = Editable.Factory.getInstance().newEditable(memo)
                    } else {
                        memoTxt.text = Editable.Factory.getInstance().newEditable(addressBook?.memo)
                    }
                    addressTxt.isEnabled = false
                    addressTxt.setTextColor(
                        ContextCompat.getColorStateList(
                            requireContext(), R.color.color_base04
                        )
                    )
                    addressSetMsg.setText(R.string.str_set_address_msg)
                }

                AddressBookType.AfterTxEdit -> {
                    nameTxt.text = Editable.Factory.getInstance().newEditable(addressBook?.bookName)
                    addressTxt.text =
                        Editable.Factory.getInstance().newEditable(addressBook?.address)
                    memoTxt.text = Editable.Factory.getInstance().newEditable(memo)
                    addressTxt.isEnabled = false
                    addressTxt.setTextColor(
                        ContextCompat.getColorStateList(
                            requireContext(), R.color.color_base04
                        )
                    )
                    addressSetMsg.setText(R.string.str_addressbook_memo_changed_msg)
                }

                AddressBookType.AfterTxNew -> {
                    addressTxt.text = Editable.Factory.getInstance().newEditable(recipientAddress)
                    memoTxt.text = Editable.Factory.getInstance().newEditable(memo)
                    addressTxt.isEnabled = false
                    memoTxt.isEnabled = false
                    addressTxt.setTextColor(
                        ContextCompat.getColorStateList(
                            requireContext(), R.color.color_base04
                        )
                    )
                    memoTxt.setTextColor(
                        ContextCompat.getColorStateList(
                            requireContext(), R.color.color_base04
                        )
                    )
                    addressSetMsg.setText(R.string.str_set_address_msg)
                }
            }

            updateView()
        }
    }

    private fun updateView() {
        binding.apply {
            val addressInput = addressTxt.text.toString().trim()
            if (BaseKey.isValidEthAddress(addressInput)) {
                memoLayout.visibility = View.GONE

            } else {
//                allIbcChains().firstOrNull { addressInput.startsWith(it.accountPrefix + "1") }
//                    ?.let { chain ->
//                        if (BaseUtils.isValidBechAddress(chain, addressInput)) {
//                            memoLayout.visibility = View.VISIBLE
//                        }
//                    }
            }
        }
    }

    private fun textChange() {
        binding.apply {
            addressTxt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?, start: Int, count: Int, after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    updateView()
                }

                override fun afterTextChanged(s: Editable?) {}
            })
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
                if (!validateAddress(addressInput)) {
                    requireContext().makeToast(R.string.error_invalid_address)
                    return@setOnClickListener
                }
                val memoInput = memoTxt.text.toString().trim()

                when (addressBookType) {
                    AddressBookType.ManualNew -> {
                        getRecipientChain(addressInput)?.let { targetChain ->
                            val addressBook = AddressBook(
                                nameInput,
                                targetChain.name,
                                addressInput,
                                memoInput,
                                Calendar.getInstance().timeInMillis
                            )
                            addressBookViewModel.updateAddressBook(addressBook)
                            dismiss()

                        } ?: run {
                            requireActivity().makeToast(R.string.error_invalid_address)
                            return@setOnClickListener
                        }
                    }

                    AddressBookType.ManualEdit, AddressBookType.AfterTxEdit -> {
                        addressBook?.bookName = nameInput
                        addressBook?.address = addressInput
                        addressBook?.memo = memoInput
                        addressBook?.lastTime = Calendar.getInstance().timeInMillis
                        addressBookViewModel.updateAddressBook(addressBook!!)
                        dismiss()
                    }

                    AddressBookType.AfterTxNew -> {
                        val addressBook = AddressBook(
                            nameInput,
                            toChain!!.name,
                            addressInput,
                            memoInput,
                            Calendar.getInstance().timeInMillis
                        )
                        addressBookViewModel.updateAddressBook(addressBook)
                        dismiss()
                    }
                }
            }
        }
    }

    private fun validateAddress(address: String?): Boolean {
        if (address?.isEmpty() == true) {
            return false
        }
        if (BaseKey.isValidEthAddress(address)) {
            return true

        } else {
//            allIbcChains().firstOrNull { address?.startsWith(it.accountPrefix + "1") == true }
//                ?.let { chain ->
//                    if (BaseUtils.isValidBechAddress(chain, address)) {
//                        return true
//                    }
//                }
        }
        return false
    }

    private fun getRecipientChain(address: String?): BaseChain? {
        if (address?.isEmpty() == true) {
            return null
        }
        if (BaseKey.isValidEthAddress(address)) {
            return EthereumLine()
        } else {
//            allIbcChains().firstOrNull { address?.startsWith(it.accountPrefix + "1") == true }
//                ?.let { chain ->
//                    return chain
//                }
        }
        return null
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

enum class AddressBookType { ManualNew, ManualEdit, AfterTxNew, AfterTxEdit }