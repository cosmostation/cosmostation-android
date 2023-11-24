package wannabit.io.cosmostaion.ui.dialog.tx.address

import android.app.Activity
import android.os.Bundle
import android.os.Handler
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
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.databinding.FragmentAddressBinding
import wannabit.io.cosmostaion.ui.dialog.qr.QrCodeActivity
import wannabit.io.cosmostaion.ui.viewmodel.tx.TxViewModel

class AddressFragment(
    private val selectedChain: CosmosLine,
    private val selectedRecipientChain: CosmosLine?,
    private val existAddress: String,
    private val addressType: AddressType?,
    val listener: AddressListener
) : BottomSheetDialogFragment() {

    private var _binding: FragmentAddressBinding? = null
    private val binding get() = _binding!!

    private val txViewModel: TxViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddressBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        clickAction()
        setUpNameServiceView()
    }

    private fun initView() {
        binding.apply {
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

    private fun clickAction() {
        var isClickable = true
        binding.apply {
            btnSelf.setOnClickListener {
                addressTxt.text = Editable.Factory.getInstance().newEditable(selectedChain.address)
            }

            btnQr.setOnClickListener {
                val integrator = IntentIntegrator.forSupportFragment(this@AddressFragment)
                integrator.setOrientationLocked(true)
                integrator.captureActivity = QrCodeActivity::class.java
                qrCodeResultLauncher.launch(integrator.createScanIntent())
            }

            btnAddressBook.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    AddressBookFragment(selectedChain.address, selectedRecipientChain, object : AddressBookSelectListener {
                        override fun select(address: String) {
                            addressTxt.text = Editable.Factory.getInstance().newEditable(address)
                            addressTxt.textSize = 11f
                            addressTxt.setSelection(addressTxt.text.toString().length)
                        }

                    }).show(requireActivity().supportFragmentManager, AddressBookFragment::class.java.name)

                    Handler().postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            btnConfirm.setOnClickListener {
                addressTxt.text.toString().trim().apply {
                    val address = this
                    if (address.isEmpty()) {
                        requireContext().makeToast(R.string.error_invalid_address)
                        return@setOnClickListener
                    }

                    if (addressType == AddressType.REWARD_ADDRESS) {
                        if (selectedChain.rewardAddress.equals(address, true)) {
                            requireContext().makeToast(R.string.error_same_reward_address)
                            return@setOnClickListener
                        }

                    } else {
                        if (selectedChain.address.equals(address, true)) {
                            requireContext().makeToast(R.string.error_self_sending)
                            return@setOnClickListener
                        }
                    }

                    if (BaseUtils.isValidChainAddress(selectedRecipientChain, addressTxt.text.toString().trim())) {
                        listener.address(addressTxt.text.toString().trim())
                        dismiss()
                    } else {
                        val prefix = selectedRecipientChain?.accountPrefix!!
                        txViewModel.icnsAddress(addressTxt.text.toString().trim(), prefix)
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
                NameServiceFragment(response, object : NameServiceSelectListener {
                    override fun select(address: String) {
                        binding.addressTxt.text = Editable.Factory.getInstance().newEditable(address)
                        binding.addressTxt.textSize = 11f
                    }

                }).show(requireActivity().supportFragmentManager, NameServiceFragment::class.java.name)
            }
        }
    }
}

enum class AddressType { REWARD_ADDRESS, EVM_TRANSFER, DEFAULT_TRANSFER, BEP3_TRANSFER }

interface AddressListener {
    fun address(address: String)
}