package wannabit.io.cosmostaion.ui.option.tx.address

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.zxing.client.android.Intents
import com.google.zxing.integration.android.IntentIntegrator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.database.model.RefAddress
import wannabit.io.cosmostaion.databinding.FragmentAddressBinding
import wannabit.io.cosmostaion.ui.qr.QrCodeActivity
import wannabit.io.cosmostaion.ui.viewmodel.tx.TxViewModel

class EvmAddressFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAddressBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedEvmChain: EthereumLine
    private var selectedToken: Token? = null
    private var existAddress: String = ""

    private var selectedRefAddress: RefAddress? = null
    private var selectedAddressBook: AddressBook? = null

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(
            selectedEvmChain: EthereumLine, selectedToken: Token?, existAddress: String,listener: AddressListener
        ): EvmAddressFragment {
            val args = Bundle().apply {
                putParcelable("selectedEvmChain", selectedEvmChain)
                putParcelable("selectedToken", selectedToken)
                putString("existAddress", existAddress)
            }
            val fragment = EvmAddressFragment()
            fragment.addressListener = listener
            fragment.arguments = args
            return fragment
        }
    }

    private var addressListener: AddressListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddressBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
    }

    private fun initView() {
        binding.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.apply {
                    getParcelable(
                        "selectedEvmChain", EthereumLine::class.java
                    )?.let { selectedEvmChain = it }

                    getParcelable("selectedToken", Token::class.java)?.let { selectedToken = it }
                }

            } else {
                arguments?.apply {
                    (getParcelable("selectedEvmChain") as? EthereumLine)?.let {
                        selectedEvmChain = it
                    }

                    (getParcelable("selectedToken") as? Token)?.let { selectedToken = it }
                }
            }
            arguments?.getString("existAddress")?.let { existAddress = it }


            title.text = getString(R.string.title_recipient_address)
            btnSelf.visibility = View.GONE

            if (existAddress.isNotEmpty()) {
                addressTxt.text = Editable.Factory.getInstance().newEditable(existAddress)
                addressTxt.textSize = 11f
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnQr.setOnClickListener {
                val integrator = IntentIntegrator.forSupportFragment(this@EvmAddressFragment)
                integrator.setOrientationLocked(true)
                integrator.captureActivity = QrCodeActivity::class.java
                qrCodeResultLauncher.launch(integrator.createScanIntent())
            }

//            btnAddressBook.setOnClickListener {
//                handleOneClickWithDelay(
//                    AddressBookFragment(selectedChain.address,
//                        selectedRecipientChain,
//                        addressType,
//                        object : AddressBookSelectListener {
//                            override fun select(
//                                refAddress: RefAddress?, addressBook: AddressBook?
//                            ) {
//                                refAddress?.let {
//                                    selectedRefAddress = refAddress
//                                    if (addressType == AddressType.EVM_TRANSFER || selectedChain is ChainOkt60) {
//                                        addressTxt.text = Editable.Factory.getInstance()
//                                            .newEditable(ByteUtils.convertBech32ToEvm(it.dpAddress))
//                                    } else {
//                                        addressTxt.text =
//                                            Editable.Factory.getInstance().newEditable(it.dpAddress)
//                                    }
//
//                                } ?: run {
//                                    selectedAddressBook = addressBook
//                                    selectedAddressBook?.let {
//                                        if (addressType == AddressType.EVM_TRANSFER || selectedChain is ChainOkt60) {
//                                            addressTxt.text = Editable.Factory.getInstance()
//                                                .newEditable(ByteUtils.convertBech32ToEvm(it.address))
//                                        } else {
//                                            addressTxt.text = Editable.Factory.getInstance()
//                                                .newEditable(it.address)
//                                        }
//                                    }
//                                    addressTxt.textSize = 11f
//                                    addressTxt.setSelection(addressTxt.text.toString().length)
//                                }
//                            }
//                        })
//                )
//            }

            btnConfirm.setOnClickListener {
                addressTxt.text.toString().trim().apply {
                    val address = this
                    if (address.isEmpty()) {
                        requireContext().makeToast(R.string.error_invalid_address)
                        return@setOnClickListener
                    }

                    if (selectedEvmChain.address.equals(address, true)) {
                        requireContext().makeToast(R.string.error_self_sending)
                        return@setOnClickListener
                    }

                    if (BaseUtils.isValidChainAddress(
                            null, addressTxt.text.toString().trim()
                        )
                    ) {
                        addressListener?.selectAddress(
                            selectedRefAddress,
                            selectedAddressBook,
                            addressTxt.text.toString().trim()
                        )
                        dismiss()

                    } else {
                        requireContext().makeToast(R.string.error_invalid_address)
                        return@setOnClickListener
                    }
                }
            }
        }
    }

    private val qrCodeResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.getStringExtra(Intents.Scan.RESULT)?.trim()?.let { address ->
                    binding.addressTxt.text = Editable.Factory.getInstance().newEditable(address)
                }
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