package wannabit.io.cosmostaion.ui.tx.option.address

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
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.zxing.client.android.Intents
import com.google.zxing.integration.android.IntentIntegrator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.majorClass.ChainIota
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.data.repository.tx.TxRepositoryImpl
import wannabit.io.cosmostaion.data.viewmodel.tx.TxViewModel
import wannabit.io.cosmostaion.data.viewmodel.tx.TxViewModelProviderFactory
import wannabit.io.cosmostaion.databinding.FragmentAddressBinding
import wannabit.io.cosmostaion.ui.qr.QrCodeActivity
import wannabit.io.cosmostaion.ui.tx.genTx.SendAssetType

interface AddressListener {
    fun selectAddress(address: String, memo: String)
}

class TransferAddressFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAddressBinding? = null
    private val binding get() = _binding!!

    private lateinit var txViewModel: TxViewModel

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
                putParcelable("fromChain", fromChain)
                putParcelable("toChain", toChain)
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

        initViewModel()
        initView()
        setUpClickAction()
        setUpNameServiceView()
    }

    private fun initViewModel() {
        val txRepository = TxRepositoryImpl()
        val txViewModelProviderFactory = TxViewModelProviderFactory(txRepository)
        txViewModel = ViewModelProvider(
            this, txViewModelProviderFactory
        )[TxViewModel::class.java]
    }

    private fun initView() {
        binding.apply {
            arguments?.apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    getParcelable(
                        "fromChain", BaseChain::class.java
                    )?.let { fromChain = it }
                    getParcelable(
                        "toChain", BaseChain::class.java
                    )?.let { toChain = it }
                    getSerializable(
                        "sendAssetType", SendAssetType::class.java
                    )?.let { sendAssetType = it }

                } else {
                    (getParcelable("fromChain") as? BaseChain)?.let {
                        fromChain = it
                    }
                    (getParcelable("toChain") as? BaseChain)?.let {
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
                val address = if (fromChain.mainAddress.isNotEmpty()) {
                    fromChain.mainAddress
                } else if (fromChain.address.isEmpty()) {
                    fromChain.evmAddress
                } else {
                    fromChain.address
                }
                handleOneClickWithDelay(
                    AddressBookFragment.newInstance(
                        fromChain,
                        toChain,
                        address,
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

                    when (sendAssetType) {
                        SendAssetType.ONLY_EVM_COIN, SendAssetType.ONLY_EVM_ERC20 -> {
                            if (address.contains(".eth")) {
                                txViewModel.ensService(address)

                            } else if (BaseKey.isValidEthAddress(address)) {
                                if (fromChain.evmAddress.equals(address, true)) {
                                    requireContext().makeToast(R.string.error_self_sending)
                                    return@setOnClickListener
                                }

                                addressListener?.selectAddress(
                                    address, addressBookMemo
                                )
                                dismiss()
                                return@setOnClickListener

                            } else {
                                requireContext().makeToast(R.string.error_invalid_address)
                                return@setOnClickListener
                            }
                        }

                        SendAssetType.ONLY_COSMOS_COIN, SendAssetType.ONLY_COSMOS_CW20, SendAssetType.ONLY_COSMOS_GRC20 -> {
                            if (BaseUtils.isValidBechAddress(
                                    toChain, address
                                )
                            ) {
                                if (fromChain.address.equals(address, true)) {
                                    requireContext().makeToast(R.string.error_self_sending)
                                    return@setOnClickListener
                                }

                                addressListener?.selectAddress(
                                    address, addressBookMemo
                                )
                                dismiss()
                                return@setOnClickListener
                            }

                            txViewModel.icnsAddress(
                                toChain, addressTxt.text.toString().trim(), toChain.accountPrefix()
                            )
                        }

                        SendAssetType.BIT_COIN -> {
                            if (BaseUtils.isValidBitAddress(
                                    toChain as ChainBitCoin86, address
                                )
                            ) {
                                if (fromChain.mainAddress.equals(address, true)) {
                                    requireContext().makeToast(R.string.error_self_sending)
                                    return@setOnClickListener
                                }

                                addressListener?.selectAddress(
                                    address, addressBookMemo
                                )
                                dismiss()
                                return@setOnClickListener

                            } else {
                                requireContext().makeToast(R.string.error_invalid_address)
                                return@setOnClickListener
                            }
                        }

                        SendAssetType.SUI_COIN, SendAssetType.SUI_NFT -> {
                            if (address.contains(".sui") || address.startsWith("@")) {
                                txViewModel.suiNameService((fromChain as ChainSui).suiFetcher, address)

                            } else if (BaseUtils.isValidSuiAddress(address)) {
                                if (fromChain.mainAddress.equals(address, true)) {
                                    requireContext().makeToast(R.string.error_self_sending)
                                    return@setOnClickListener
                                }

                                addressListener?.selectAddress(
                                    address, addressBookMemo
                                )
                                dismiss()
                                return@setOnClickListener

                            } else {
                                requireContext().makeToast(R.string.error_invalid_address)
                                return@setOnClickListener
                            }
                        }

                        SendAssetType.IOTA_COIN, SendAssetType.IOTA_NFT -> {
                            if (address.contains(".iota") || address.startsWith("@")) {
                                txViewModel.iotaNameService((fromChain as ChainIota).iotaFetcher, address)

                            } else if (BaseUtils.isValidSuiAddress(address)) {
                                if (fromChain.mainAddress.equals(address, true)) {
                                    requireContext().makeToast(R.string.error_self_sending)
                                    return@setOnClickListener
                                }

                                addressListener?.selectAddress(
                                    address, addressBookMemo
                                )
                                dismiss()
                                return@setOnClickListener

                            } else {
                                requireContext().makeToast(R.string.error_invalid_address)
                                return@setOnClickListener
                            }
                        }

                        SendAssetType.SOLANA_COIN, SendAssetType.SOLANA_TOKEN -> {
                            if (BaseKey.isValidSolanaAddress(address)) {
                                if (fromChain.mainAddress.equals(address, true)) {
                                    requireContext().makeToast(R.string.error_self_sending)
                                    return@setOnClickListener
                                }

                                addressListener?.selectAddress(
                                    address, addressBookMemo
                                )
                                dismiss()
                                return@setOnClickListener

                            } else {
                                requireContext().makeToast(R.string.error_invalid_address)
                                return@setOnClickListener
                            }
                        }

                        SendAssetType.APTOS_COIN -> {
                            if (BaseKey.isValidAptosAddress(address)) {
                                if (fromChain.mainAddress.equals(address, true)) {
                                    requireContext().makeToast(R.string.error_self_sending)
                                    return@setOnClickListener
                                }

                                addressListener?.selectAddress(
                                    address, addressBookMemo
                                )
                                dismiss()
                                return@setOnClickListener

                            } else {
                                requireContext().makeToast(R.string.error_invalid_address)
                                return@setOnClickListener
                            }
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
                            response, object : NameServiceSelectListener {
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