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
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.zxing.client.android.Intents
import com.google.zxing.integration.android.IntentIntegrator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.data.repository.tx.TxRepositoryImpl
import wannabit.io.cosmostaion.databinding.FragmentAddressBinding
import wannabit.io.cosmostaion.ui.qr.QrCodeActivity
import wannabit.io.cosmostaion.ui.tx.step.SendAssetType
import wannabit.io.cosmostaion.ui.viewmodel.tx.TxViewModel
import wannabit.io.cosmostaion.ui.viewmodel.tx.TxViewModelProviderFactory

class CommonAddressFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAddressBinding? = null
    private val binding get() = _binding!!

    private lateinit var fromChain: CosmosLine
    private var existAddress = ""
    private lateinit var addressType: AddressType

    private var addressBookMemo = ""

    private lateinit var txViewModel: TxViewModel

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(
            fromChain: CosmosLine,
            existAddress: String,
            addressType: AddressType,
            listener: AddressListener
        ): CommonAddressFragment {
            val args = Bundle().apply {
                putSerializable("fromChain", fromChain)
                putString("existAddress", existAddress)
                putSerializable("addressType", addressType)
            }
            val fragment = CommonAddressFragment()
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
                    getSerializable(
                        "fromChain", CosmosLine::class.java
                    )?.let { fromChain = it }
                    getSerializable(
                        "addressType", AddressType::class.java
                    )?.let { addressType = it }

                } else {
                    (getSerializable("fromChain") as? CosmosLine)?.let {
                        fromChain = it
                    }
                    (getSerializable("addressType") as? AddressType)?.let {
                        addressType = it
                    }
                }
                getString("existAddress")?.let { existAddress = it }
            }

            if (addressType == AddressType.REWARD_ADDRESS) {
                title.text = getString(R.string.title_reward_recipient_address)
                btnSelf.visibility = View.VISIBLE
            } else {
                title.text = getString(R.string.title_recipient_address)
                btnSelf.visibility = View.GONE
            }

            if (existAddress.isNotEmpty()) {
                addressTxt.text = Editable.Factory.getInstance().newEditable(existAddress)
                addressTxt.textSize = 11f
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnSelf.setOnClickListener {
                addressTxt.text = Editable.Factory.getInstance().newEditable(fromChain.address)
            }

            btnQr.setOnClickListener {
                val integrator = IntentIntegrator.forSupportFragment(this@CommonAddressFragment)
                integrator.setOrientationLocked(true)
                integrator.captureActivity = QrCodeActivity::class.java
                qrCodeResultLauncher.launch(integrator.createScanIntent())
            }

            btnAddressBook.setOnClickListener {
                handleOneClickWithDelay(
                    AddressBookFragment.newInstance(fromChain,
                        fromChain,
                        fromChain.address,
                        SendAssetType.ONLY_COSMOS_COIN,
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

                    if (addressType == AddressType.REWARD_ADDRESS) {
                        if (fromChain.rewardAddress.equals(address, true)) {
                            requireContext().makeToast(R.string.error_same_reward_address)
                            return@setOnClickListener
                        }

                    } else {
                        if (fromChain.address.equals(address, true)) {
                            requireContext().makeToast(R.string.error_self_sending)
                            return@setOnClickListener
                        }
                    }

                    if (BaseUtils.isValidBechAddress(
                            fromChain,
                            addressTxt.text.toString().trim()
                        )
                    ) {
                        addressListener?.selectAddress(addressTxt.text.toString().trim(), "")
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

    private fun setUpNameServiceView() {
        txViewModel.nameServices.observe(viewLifecycleOwner) { response ->
            if (response.isEmpty()) {
                requireContext().makeToast(R.string.error_invalid_address)
                return@observe

            } else {
                handleOneClickWithDelay(
                    NameServiceFragment.newInstance(response, object : NameServiceSelectListener {
                        override fun select(address: String) {
                            binding.addressTxt.text =
                                Editable.Factory.getInstance().newEditable(address)
                            binding.addressTxt.textSize = 11f
                        }
                    })
                )
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
}

enum class AddressType { REWARD_ADDRESS, DEFAULT_TRANSFER }

