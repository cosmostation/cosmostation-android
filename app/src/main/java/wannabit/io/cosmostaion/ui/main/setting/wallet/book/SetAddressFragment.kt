package wannabit.io.cosmostaion.ui.main.setting.wallet.book

import android.app.Activity
import android.app.Dialog
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.bitcoinj.core.Address
import org.bitcoinj.params.MainNetParams
import org.bitcoinj.params.TestNet3Params
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.majorClass.ChainIota
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.setChainLogo
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.viewmodel.address.AddressBookViewModel
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.databinding.FragmentSetAddressBinding
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

    private var isClickable = true

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
                putParcelable("toChain", toChain)
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
                    getParcelable("toChain", BaseChain::class.java)?.let { toChain = it }
                    getSerializable(
                        "addressBookType", AddressBookType::class.java
                    )?.let { addressBookType = it }

                } else {
                    (getParcelable("addressBook") as? AddressBook)?.let {
                        addressBook = it
                    }
                    (getParcelable("toChain") as? BaseChain)?.let {
                        toChain = it
                    }
                    (getSerializable("addressBookType") as? AddressBookType)?.let {
                        addressBookType = it
                    }
                }
                getString("recipientAddress")?.let { recipientAddress = it }
                getString("memo")?.let { memo = it }
            }

            recipientChainView.setBackgroundResource(R.drawable.cell_bg)
            memoDescriptionView.setBackgroundResource(R.drawable.cell_bg_no_border)
            memoImg.setColorFilter(
                ContextCompat.getColor(requireContext(), R.color.color_sub_blue),
                PorterDuff.Mode.SRC_IN
            )
            btnConfirm.updateButtonView(false)
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
            toChain?.let { chain ->
                chainName.text = chain.getChainName()
                chainImg.setChainLogo(chain)

                val addressInput = addressTxt.text.toString().trim()
                if (chain.isEvmCosmos()) {
                    if (addressInput.isNotEmpty() && BaseKey.isValidEthAddress(addressInput)) {
                        memoLayout.visibility = View.GONE
                        memoDescriptionView.visibility = View.GONE
                    } else {
                        memoLayout.visibility = View.VISIBLE
                        memoDescriptionView.visibility = View.VISIBLE
                    }

                } else if (chain.supportCosmos() || chain is ChainBitCoin86) {
                    memoLayout.visibility = View.VISIBLE
                    memoDescriptionView.visibility = View.VISIBLE
                } else {
                    memoLayout.visibility = View.GONE
                    memoDescriptionView.visibility = View.GONE
                }

                memoTitle.text = getString(R.string.title_enter_memo)
                memoDescription.text = getString(R.string.str_memo_description)

            } ?: run {
                chainName.text = "EVM Networks (Universal)"
                chainImg.setImageResource(R.drawable.evm_universal)
                memoLayout.visibility = View.GONE
                memoDescriptionView.visibility = View.VISIBLE

                memoTitle.text = getString(R.string.title_universal_address)
                memoDescription.text = getString(R.string.str_memo_description_msg)
            }
            chainImg.alpha = 0.2f
        }
    }

    private fun updateMemoView() {
        binding.apply {
            val addressInput = addressTxt.text.toString().trim()
            if (toChain?.isEvmCosmos() == true) {
                if (BaseKey.isValidEthAddress(addressInput) || BaseUtils.isValidSuiAddress(
                        addressInput
                    )
                ) {
                    memoLayout.visibility = View.GONE
                    memoDescriptionView.visibility = View.GONE

                } else {
                    allChains().firstOrNull { addressInput.startsWith(it.accountPrefix + "1") }
                        ?.let { chain ->
                            if (BaseUtils.isValidBechAddress(chain, addressInput)) {
                                memoLayout.visibility = View.VISIBLE
                                memoDescription.visibility = View.VISIBLE
                            }
                        }
                }
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

                override fun onTextChanged(
                    s: CharSequence?, start: Int, before: Int, count: Int
                ) {
                    updateMemoView()
                    updateBtnConfirm()
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            nameTxt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?, start: Int, count: Int, after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    updateBtnConfirm()
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }
    }

    private fun updateBtnConfirm() {
        binding.apply {
            val nameText = nameTxt.text?.trim().toString()
            val addressText = addressTxt.text?.trim().toString()

            if (nameText.isNotEmpty() && addressText.isNotEmpty()) {
                btnConfirm.updateButtonView(true)
            } else {
                btnConfirm.updateButtonView(false)
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            recipientChainView.setOnClickListener {
                if (addressBookType != AddressBookType.ManualNew) {
                    return@setOnClickListener
                }
                handleOneClickWithDelay(
                    SetChainTypeBookFragment.newInstance(
                        toChain,
                        object : ChainSelectAddressListener {
                            override fun select(chainTag: String) {
                                toChain = allChains().firstOrNull { it.tag == chainTag }
                                updateView()
                            }
                        })
                )
            }

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

                when (addressBookType) {
                    AddressBookType.ManualNew -> {
                        if (toChain is ChainBitCoin86) {
                            val memoByteLength =
                                memoTxt.text.toString().trim().toByteArray(Charsets.UTF_8).size
                            if (memoByteLength > 80) {
                                requireContext().makeToast(R.string.error_memo_count)
                                return@setOnClickListener
                            }
                        }

                        val memoInput = if (memoLayout.isVisible) {
                            memoTxt.text.toString().trim()
                        } else {
                            ""
                        }

                        val addressBook = AddressBook(
                            nameInput,
                            toChain?.tag ?: "EVM-universal",
                            addressInput,
                            memoInput,
                            Calendar.getInstance().timeInMillis
                        )
                        addressBookViewModel.updateAddressBook(addressBook)
                        dismiss()
                    }

                    AddressBookType.ManualEdit -> {
                        if (toChain is ChainBitCoin86) {
                            val memoByteLength =
                                memoTxt.text.toString().trim().toByteArray(Charsets.UTF_8).size
                            if (memoByteLength > 80) {
                                requireContext().makeToast(R.string.error_memo_count)
                                return@setOnClickListener
                            }
                        }

                        addressBook?.bookName = nameInput
                        addressBook?.memo = memoTxt.text.toString().trim()
                        addressBook?.lastTime = Calendar.getInstance().timeInMillis
                        addressBookViewModel.updateAddressBook(addressBook!!)
                        dismiss()
                    }

                    AddressBookType.AfterTxEdit -> {
                        addressBook?.bookName = nameInput
                        addressBook?.memo = memoTxt.text.toString().trim()
                        addressBook?.lastTime = Calendar.getInstance().timeInMillis
                        addressBookViewModel.updateAddressBook(addressBook!!)
                        dismiss()
                    }

                    AddressBookType.AfterTxNew -> {
                        val addressBook = AddressBook(
                            nameInput,
                            toChain?.tag ?: "",
                            addressInput,
                            memoTxt.text.toString().trim(),
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

        if (toChain == null) {
            return BaseKey.isValidEthAddress(address)

        } else if (toChain is ChainBitCoin86) {
            if (toChain?.isTestnet == true) {
                return try {
                    Address.fromString(TestNet3Params.get(), address)
                    true
                } catch (e: Exception) {
                    false
                }

            } else {
                return try {
                    Address.fromString(MainNetParams.get(), address)
                    true
                } catch (e: Exception) {
                    false
                }
            }

        } else if (toChain is ChainSui || toChain is ChainIota) {
            return BaseUtils.isValidSuiAddress(address)

        } else if (toChain?.isEvmCosmos() == true) {
            return if (address?.startsWith("0x") == true && BaseKey.isValidEthAddress(address)) {
                true
            } else {
                val prefix = address?.substringBefore('1')
                toChain?.accountPrefix == prefix
            }

        } else if (toChain?.supportCosmos() == true) {
            val prefix = address?.substringBefore('1')
            return toChain?.accountPrefix == prefix

        } else {
            return address?.startsWith("0x") == true && BaseKey.isValidEthAddress(address)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            setupRatio(bottomSheetDialog)
        }
        return dialog
    }

    private fun setupRatio(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet = bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet) as View
        val behavior = BottomSheetBehavior.from(bottomSheet)
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = bottomSheetDialogDefaultHeight(windowHeight())
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun bottomSheetDialogDefaultHeight(windowHeight: Int): Int {
        return windowHeight * 18 / 20
    }

    private fun windowHeight(): Int {
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    override fun onStart() {
        super.onStart()

        val bottomSheetDialog = dialog as BottomSheetDialog
        val bottomSheet =
            bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

        bottomSheet?.let { sheet ->
            val behavior = BottomSheetBehavior.from(sheet)
            behavior.isHideable = true

            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {
                        BottomSheetBehavior.STATE_HIDDEN -> dismiss()
                        BottomSheetBehavior.STATE_COLLAPSED -> {
                            behavior.state = BottomSheetBehavior.STATE_EXPANDED
                        }

                        else -> {}
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {}
            })
        }
    }

    private fun handleOneClickWithDelay(bottomSheetDialogFragment: BottomSheetDialogFragment) {
        if (isClickable) {
            isClickable = false

            bottomSheetDialogFragment.show(
                requireActivity().supportFragmentManager, bottomSheetDialogFragment::class.java.name
            )

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 300)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

enum class AddressBookType { ManualNew, ManualEdit, AfterTxNew, AfterTxEdit }