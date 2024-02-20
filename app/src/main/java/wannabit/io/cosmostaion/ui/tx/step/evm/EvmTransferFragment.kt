package wannabit.io.cosmostaion.ui.tx.step.evm

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.EVM_BASE_FEE
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.amountHandlerLeft
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.database.model.RefAddress
import wannabit.io.cosmostaion.databinding.FragmentEvmTransferBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.ui.option.tx.address.AddressListener
import wannabit.io.cosmostaion.ui.option.tx.address.EvmAddressFragment
import wannabit.io.cosmostaion.ui.option.tx.general.AmountSelectListener
import wannabit.io.cosmostaion.ui.option.tx.general.EvmInsertAmountFragment
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.tx.TxResultType
import wannabit.io.cosmostaion.ui.tx.step.BaseTxFragment
import java.math.BigDecimal
import java.math.RoundingMode

class EvmTransferFragment : BaseTxFragment() {

    private var _binding: FragmentEvmTransferBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedEvmChain: EthereumLine
    private lateinit var toSendDenom: String
    private var selectedToken: Token? = null

    private var selectedFeeInfo = 1

    private var toSendAmount = ""
    private var decimal = 18
    private var price = BigDecimal.ZERO
    private var existedAddress = ""

    private var availableAmount = BigDecimal.ZERO

    private var hexValue = ""

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedEvmChain: EthereumLine, toSendDenom: String): EvmTransferFragment {
            val args = Bundle().apply {
                putParcelable("selectedEvmChain", selectedEvmChain)
                putString("toSendDenom", toSendDenom)
            }
            val fragment = EvmTransferFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEvmTransferBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setupFeeView()
        setUpClickAction()
        setUpSimulate()
        setUpBroadcast()
    }

    private fun initView() {
        binding.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("selectedEvmChain", EthereumLine::class.java)
                    ?.let { selectedEvmChain = it }
            } else {
                (arguments?.getParcelable("selectedEvmChain") as? EthereumLine)?.let {
                    selectedEvmChain = it
                }
            }
            arguments?.getString("toSendDenom")?.let { toSendDenom = it }

            listOf(sendAssetView, addressView, feeView).forEach {
                it.setBackgroundResource(
                    R.drawable.cell_bg
                )
            }
            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)

            feeTokenImg.setImageResource(selectedEvmChain.coinLogo)
            feeToken.text = selectedEvmChain.coinSymbol

            selectedToken = selectedEvmChain.evmTokens.firstOrNull { it.address == toSendDenom }
            selectedToken?.let { token ->
                tokenImg.setTokenImg(token.assetImg())
                tokenName.text = token.symbol
                availableAmount = token.amount?.toBigDecimal()
                decimal = token.decimals
                price = BaseData.getPrice(token.coinGeckoId)

            } ?: run {
                tokenImg.setImageResource(selectedEvmChain.coinLogo)
                tokenName.text = selectedEvmChain.coinSymbol
                availableAmount = selectedEvmChain.evmBalance.subtract(EVM_BASE_FEE)
                decimal = 18
                price = BaseData.getPrice(selectedEvmChain.coinGeckoId)
            }
        }
    }

    private fun setupFeeView() {
        binding.apply {
            feeSegment.apply {
                setSelectedBackground(
                    ContextCompat.getColor(
                        requireContext(), R.color.color_accent_purple
                    )
                )
                setRipple(
                    ContextCompat.getColor(
                        requireContext(), R.color.color_accent_purple
                    )
                )
            }

            for (i in 0 until 3) {
                val segmentView = ItemSegmentedFeeBinding.inflate(layoutInflater)
                feeSegment.addView(
                    segmentView.root,
                    i,
                    LinearLayout.LayoutParams(0, dpToPx(requireContext(), 32), 1f)
                )
                segmentView.btnTitle.text = when (i) {
                    0 -> getString(R.string.str_tiny)
                    1 -> getString(R.string.str_low)
                    else -> getString(R.string.str_average)
                }
            }
            feeSegment.setPosition(selectedFeeInfo, false)
        }
    }

    private fun updateAmountView(toAmount: String) {
        binding.apply {
            tabMsgTxt.visibility = View.GONE
            amountLayout.visibility = View.VISIBLE
            toSendAmount = toAmount

            val dpAmount = toAmount.toBigDecimal().amountHandlerLeft(decimal)
            val value = price.multiply(dpAmount)

            sendAmount.text = formatAmount(dpAmount.toPlainString(), decimal)
            sendValue.text = formatAssetValue(value)
        }
        txSimulate()
    }

    private fun updateRecipientAddressView(address: String) {
        existedAddress = address
        binding.apply {
            if (address.isEmpty()) {
                recipientAddressMsg.visibility = View.VISIBLE
                recipientAddressMsg.text = getString(R.string.str_tap_for_add_address_msg)
            } else {
                recipientAddressMsg.visibility = View.GONE
                recipientEvmAddress.text = address
            }
        }
        txSimulate()
    }

    private fun setUpClickAction() {
        binding.apply {
            sendAssetView.setOnClickListener {
                handleOneClickWithDelay(
                    EvmInsertAmountFragment.newInstance(selectedEvmChain,
                        selectedToken,
                        availableAmount.toString(),
                        toSendAmount,
                        object : AmountSelectListener {
                            override fun select(toAmount: String) {
                                updateAmountView(toAmount)
                            }
                        })
                )
            }

            addressView.setOnClickListener {
                handleOneClickWithDelay(
                    EvmAddressFragment.newInstance(selectedEvmChain,
                        selectedToken,
                        existedAddress,
                        object : AddressListener {
                            override fun selectAddress(
                                refAddress: RefAddress?,
                                addressBook: AddressBook?,
                                addressTxt: String
                            ) {
                                refAddress?.dpAddress?.let {
                                    updateRecipientAddressView(it)

                                } ?: run {
                                    addressBook?.let {
                                        updateRecipientAddressView(it.address)

                                    } ?: run {
                                        updateRecipientAddressView(addressTxt)
                                    }
                                }
                            }
                        })
                )
            }

            feeSegment.setOnPositionChangedListener { position ->
                selectedFeeInfo = position
                txSimulate()
            }

            btnSend.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    sendResultLauncher.launch(this)
                    requireActivity().overridePendingTransition(
                        R.anim.anim_slide_in_bottom, R.anim.anim_fade_out
                    )
                }
            }
        }
    }

    private fun txSimulate() {
        binding.apply {
            if (toSendAmount.isEmpty() || existedAddress.isEmpty()) {
                return
            }
            if (toSendAmount.toBigDecimal() == BigDecimal.ZERO) {
                return
            }
            btnSend.updateButtonView(false)
            backdropLayout.visibility = View.VISIBLE
            txViewModel.simulateEvmSend(
                binding.recipientEvmAddress.text.toString().trim(),
                toSendAmount,
                selectedToken,
                selectedEvmChain,
                selectedFeeInfo
            )
        }
    }

    private fun setUpSimulate() {
        txViewModel.simulateEvmSend.observe(viewLifecycleOwner) { response ->
            response.first?.let { hexValue ->
                this.hexValue = hexValue
                response.second?.let { evmFeeAmount ->
                    updateFeeViewWithSimulate(evmFeeAmount)
                }
            }
        }

        txViewModel.erc20ErrorMessage.observe(viewLifecycleOwner) { response ->
            isBroadCastTx(false)
            requireContext().showToast(view, response.first, true)
            return@observe
        }
    }

    private fun updateFeeViewWithSimulate(evmFeeAmount: String) {
        binding.apply {
            if (evmFeeAmount.toBigDecimal() > BigDecimal.ZERO) {
                val calFeeAmount =
                    evmFeeAmount.toBigDecimal().movePointLeft(18).setScale(18, RoundingMode.DOWN)
                val value = price.multiply(calFeeAmount).setScale(6, RoundingMode.DOWN)
                feeAmount.text = formatAmount(calFeeAmount.toPlainString(), 18)
                feeValue.text = formatAssetValue(value)

            } else {

            }
        }
        isBroadCastTx(true)
    }

    private fun isBroadCastTx(isSuccess: Boolean) {
        binding.backdropLayout.visibility = View.GONE
        binding.btnSend.updateButtonView(isSuccess)
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

    private val sendResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE

                val web3j = Web3j.build(HttpService(selectedEvmChain.rpcURL))
                txViewModel.broadcastEvmSend(web3j, hexValue)
            }
        }

    private fun setUpBroadcast() {
        txViewModel.broadcastEvmSendTx.observe(viewLifecycleOwner) { txResponse ->
            Intent(requireContext(), TxResultActivity::class.java).apply {
                if (txResponse?.isNotEmpty() == true) {
                    putExtra("isSuccess", true)
                    putExtra("txHash", txResponse)
                } else {
                    putExtra("isSuccess", false)
                    putExtra("errorMsg", txResponse)
                }
                putExtra("selectedChain", selectedEvmChain.tag)
                putExtra("txResultType", TxResultType.EVM.toString())
                startActivity(this)
            }
            dismiss()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}