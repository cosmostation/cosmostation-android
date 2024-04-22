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
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.zxing.client.android.Intents
import com.google.zxing.integration.android.IntentIntegrator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.databinding.FragmentAddressBinding
import wannabit.io.cosmostaion.ui.qr.QrCodeActivity
import wannabit.io.cosmostaion.ui.tx.step.SendAssetType
import wannabit.io.cosmostaion.ui.viewmodel.tx.TxViewModel

interface AddressListener {
    fun selectAddress(address: String, memo: String)
}

class TransferAddressFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAddressBinding? = null
    private val binding get() = _binding!!

    private val txViewModel: TxViewModel by activityViewModels()

    private lateinit var fromChain: BaseChain
    private lateinit var toChain: BaseChain
    private var existAddress = ""
    private lateinit var sendAssetType: SendAssetType

    private var addressBookMemo = ""

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(
            fromChain: BaseChain,
            toChain: BaseChain,
            existAddress: String,
            sendAssetType: SendAssetType,
            listener: AddressListener
        ): TransferAddressFragment {
            val args = Bundle().apply {
                putSerializable("fromChain", fromChain)
                putSerializable("toChain", toChain)
                putString("existAddress", existAddress)
                putSerializable("sendAssetType", sendAssetType)
            }
            val fragment = TransferAddressFragment()
            fragment.arguments = args
            fragment.addressListener = listener
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
        setUpNameServiceView()
    }

    private fun initView() {
        binding.apply {
            arguments?.apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    getSerializable(
                        "fromChain", BaseChain::class.java
                    )?.let { fromChain = it }
                    getSerializable(
                        "toChain", BaseChain::class.java
                    )?.let { toChain = it }
                    getSerializable(
                        "sendAssetType", SendAssetType::class.java
                    )?.let { sendAssetType = it }

                } else {
                    (getSerializable("fromChain") as? BaseChain)?.let {
                        fromChain = it
                    }
                    (getSerializable("toChain") as? BaseChain)?.let {
                        toChain = it
                    }
                    (getSerializable("sendAssetType") as? SendAssetType)?.let {
                        sendAssetType = it
                    }
                }
                getString("existAddress")?.let { existAddress = it }
            }

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
                val integrator = IntentIntegrator.forSupportFragment(this@TransferAddressFragment)
                integrator.setOrientationLocked(true)
                integrator.captureActivity = QrCodeActivity::class.java
                qrCodeResultLauncher.launch(integrator.createScanIntent())
            }

            btnAddressBook.setOnClickListener {
                handleOneClickWithDelay(
                    AddressBookFragment.newInstance(fromChain,
                        toChain,
                        fromChain.address,
                        sendAssetType,
                        object : AddressBookSelectListener {
                            override fun select(address: String, memo: String) {
                                addressTxt.text =
                                    Editable.Factory.getInstance().newEditable(address)
                                existAddress = address
                                addressBookMemo = memo

                                addressListener?.selectAddress(
                                    existAddress, addressBookMemo
                                )
                                dismiss()
                            }
                        })
                )
            }

            btnConfirm.setOnClickListener {
                addressTxt.text.toString().trim().apply {
                    val address = this
                    if (address.isEmpty()) {
                        requireContext().makeToast(R.string.error_invalid_address)
                        return@setOnClickListener
                    }

                    if (fromChain.address.equals(address, true)) {
                        requireContext().makeToast(R.string.error_self_sending)
                        return@setOnClickListener
                    }

                    if (sendAssetType == SendAssetType.COSMOS_EVM_COIN) {
                        if (address.equals(ByteUtils.convertBech32ToEvm(fromChain.address), true)) {
                            requireContext().makeToast(R.string.error_self_sending)
                            return@setOnClickListener
                        }
                    }

                    if (sendAssetType == SendAssetType.ONLY_EVM_COIN || sendAssetType == SendAssetType.ONLY_EVM_ERC20) {
                        if (BaseKey.isValidEthAddress(address)) {
                            addressListener?.selectAddress(
                                address, addressBookMemo
                            )
                            dismiss()
                            return@setOnClickListener

                        } else {
                            requireContext().makeToast(R.string.error_invalid_address)
                            return@setOnClickListener
                        }

                    } else if (sendAssetType == SendAssetType.ONLY_COSMOS_COIN || sendAssetType == SendAssetType.ONLY_COSMOS_CW20) {
                        if (BaseUtils.isValidBechAddress(
                                toChain as CosmosLine, address
                            )
                        ) {
                            addressListener?.selectAddress(
                                address, addressBookMemo
                            )
                            dismiss()
                            return@setOnClickListener
                        }

                        toChain.accountPrefix?.let { prefix ->
                            txViewModel.icnsAddress(
                                toChain as CosmosLine, addressTxt.text.toString().trim(), prefix
                            )
                        }

                    } else if (sendAssetType == SendAssetType.COSMOS_EVM_COIN) {
                        if (BaseKey.isValidEthAddress(address)) {
                            addressListener?.selectAddress(
                                address, addressBookMemo
                            )
                            dismiss()
                            return@setOnClickListener
                        }

                        if (BaseUtils.isValidBechAddress((toChain as CosmosLine), address)) {
                            addressListener?.selectAddress(
                                address, addressBookMemo
                            )
                            dismiss()
                            return@setOnClickListener
                        }

                        toChain.accountPrefix?.let { prefix ->
                            txViewModel.icnsAddress(
                                toChain as CosmosLine, addressTxt.text.toString().trim(), prefix
                            )
                        }
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

    private fun setUpNameServiceView() {
        binding.apply {
            txViewModel.nameServices.observe(viewLifecycleOwner) { response ->
                if (response.isEmpty()) {
                    requireContext().makeToast(R.string.error_invalid_address)
                    return@observe

                } else {
                    handleOneClickWithDelay(
                        NameServiceFragment.newInstance(
                            response,
                            object : NameServiceSelectListener {
                                override fun select(address: String) {
                                    addressTxt.text =
                                        Editable.Factory.getInstance().newEditable(address)
                                    addressTxt.textSize = 11f
                                }
                            })
                    )
                }
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