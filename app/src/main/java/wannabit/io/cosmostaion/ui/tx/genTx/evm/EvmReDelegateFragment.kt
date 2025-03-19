package wannabit.io.cosmostaion.ui.tx.genTx.evm

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
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.staking.v1beta1.StakingProto.Validator
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.formatString
import wannabit.io.cosmostaion.common.isActiveValidator
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.setMonikerImg
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.databinding.FragmentRedelegateBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.ui.main.chain.cosmos.TxType
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TransferTxResultActivity
import wannabit.io.cosmostaion.ui.tx.genTx.BaseTxFragment
import wannabit.io.cosmostaion.ui.tx.genTx.TransferStyle
import wannabit.io.cosmostaion.ui.tx.option.general.AmountSelectListener
import wannabit.io.cosmostaion.ui.tx.option.general.InsertAmountFragment
import wannabit.io.cosmostaion.ui.tx.option.validator.ValidatorDefaultFragment
import wannabit.io.cosmostaion.ui.tx.option.validator.ValidatorDefaultListener
import wannabit.io.cosmostaion.ui.tx.option.validator.ValidatorFragment
import wannabit.io.cosmostaion.ui.tx.option.validator.ValidatorListener
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

class EvmReDelegateFragment : BaseTxFragment() {

    private var _binding: FragmentRedelegateBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain
    private var fromValidator: Validator? = null
    private var toValidator: Validator? = null

    private var selectedFeePosition = 0

    private val evmGasPrices: List<BigInteger> = listOf(
        BigInteger.valueOf(1500000000),
        BigInteger.valueOf(1500000000),
        BigInteger.valueOf(1500000000)
    )
    private var evmFeeAmount: BigInteger? = null
    private val evmGasLimit = BigInteger.valueOf(21000)
    private var evmHexValue = ""

    private var toCoin: CoinProto.Coin? = null

    private var availableAmount = BigDecimal.ZERO

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(
            selectedChain: BaseChain, fromValidator: Validator?
        ): EvmReDelegateFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putSerializable("fromValidator", fromValidator)
            }
            val fragment = EvmReDelegateFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRedelegateBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initFee()
        setUpClickAction()
        setUpSimulate()
        setUpBroadcast()
    }

    private fun initView() {
        binding.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("selectedChain", BaseChain::class.java)
                    ?.let { selectedChain = it }
                fromValidator = arguments?.getSerializable("fromValidator", Validator::class.java)
            } else {
                (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                    selectedChain = it
                }
                fromValidator = arguments?.getSerializable("fromValidator") as? Validator?
            }

            listOf(fromValidatorView, toValidatorView, amountView, memoView, feeView).forEach {
                it.setBackgroundResource(
                    R.drawable.cell_bg
                )
            }
            memoView.visibility = View.GONE
            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)

            if (fromValidator != null) {
                selectedChain.cosmosFetcher?.cosmosValidators?.firstOrNull {
                    it.operatorAddress == selectedChain.cosmosFetcher?.cosmosDelegations?.get(
                        0
                    )?.delegation?.validatorAddress
                }
            }

            val cosmostation =
                selectedChain.cosmosFetcher?.cosmosValidators?.firstOrNull { it.description.moniker == "Cosmostation" }
            toValidator = if (fromValidator?.operatorAddress == cosmostation?.operatorAddress) {
                selectedChain.cosmosFetcher?.cosmosValidators?.firstOrNull { it.operatorAddress != cosmostation?.operatorAddress }
            } else {
                selectedChain.cosmosFetcher?.cosmosValidators?.firstOrNull { it.operatorAddress != fromValidator?.operatorAddress }
            }
            updateFromValidatorView()
            updateToValidatorView()
        }
    }

    private fun initFee() {
        binding.apply {
            feeSegment.setSelectedBackground(
                ContextCompat.getColor(
                    requireContext(), R.color.color_accent_purple
                )
            )
            feeSegment.setRipple(
                ContextCompat.getColor(
                    requireContext(), R.color.color_accent_purple
                )
            )
            val evmGasTitle = listOf(
                getString(R.string.str_low),
                getString(R.string.str_average),
                getString(R.string.str_high)
            )
            for (i in evmGasTitle.indices) {
                val segmentView = ItemSegmentedFeeBinding.inflate(layoutInflater)
                feeSegment.addView(
                    segmentView.root,
                    i,
                    LinearLayout.LayoutParams(0, dpToPx(requireContext(), 32), 1f)
                )
                segmentView.btnTitle.text = evmGasTitle[i]
            }
            feeSegment.setPosition(1, false)
            selectedFeePosition = 1
            feeTokenImg.setImageResource(selectedChain.coinLogo)
            feeToken.text = selectedChain.coinSymbol

            val feePrice = BaseData.getPrice(selectedChain.coinGeckoId)
            val totalGasPrice = evmGasPrices[selectedFeePosition]
            val amount = totalGasPrice.multiply(evmGasLimit).toBigDecimal()
            val dpAmount = amount.movePointLeft(18).setScale(18, RoundingMode.DOWN)
            val value = feePrice.multiply(dpAmount)
            feeAmount.text = formatAmount(dpAmount.toPlainString(), 18)
            feeValue.text = formatAssetValue(value)
        }
    }

    private fun updateFromValidatorView() {
        binding.apply {
            fromValidator?.let { fromValidator ->
                fromMonikerImg.setMonikerImg(selectedChain, fromValidator.operatorAddress)
                fromMonikerName.text = fromValidator.description?.moniker?.trim()
                val statusImage = when {
                    fromValidator.jailed -> R.drawable.icon_jailed
                    !fromValidator.isActiveValidator(selectedChain) -> R.drawable.icon_inactive
                    else -> 0
                }
                fromJailedImg.visibility = if (statusImage != 0) View.VISIBLE else View.GONE
                fromJailedImg.setImageResource(statusImage)
            }

            BaseData.getAsset(selectedChain.apiName, selectedChain.stakeDenom)?.let { asset ->
                asset.decimals?.let { decimal ->
                    val staked =
                        selectedChain.cosmosFetcher?.cosmosDelegations?.firstOrNull { it.delegation.validatorAddress == fromValidator?.operatorAddress }?.balance?.amount
                    availableAmount = staked?.toBigDecimal()
                    staked?.toBigDecimal()?.movePointLeft(decimal)?.let {
                        stakedAmount.text = formatAmount(it.toPlainString(), decimal)
                    }
                }
            }
        }
        txSimulate()
    }

    private fun updateToValidatorView() {
        binding.apply {
            toValidator?.let { toValidator ->
                toMonikerImg.setMonikerImg(selectedChain, toValidator.operatorAddress)
                toMonikerName.text = toValidator.description?.moniker?.trim()
                val statusImage = when {
                    toValidator.jailed -> R.drawable.icon_jailed
                    !toValidator.isActiveValidator(selectedChain) -> R.drawable.icon_inactive
                    else -> 0
                }
                toJailedImg.visibility = if (statusImage != 0) View.VISIBLE else View.GONE
                toJailedImg.setImageResource(statusImage)

                toValidator.commission.commissionRates.rate.toBigDecimal().movePointLeft(16)
                    .setScale(2, RoundingMode.DOWN).let {
                        commission.text = formatString("$it%", 3)

                        txSimulate()
                    }
            }
        }
    }

    private fun updateAmountView(toAmount: String) {
        binding.apply {
            toCoin =
                CoinProto.Coin.newBuilder().setAmount(toAmount).setDenom(selectedChain.stakeDenom)
                    .build()

            BaseData.getAsset(selectedChain.apiName, selectedChain.stakeDenom)?.let { asset ->
                asset.decimals?.let { decimal ->
                    val dpAmount = BigDecimal(toAmount).movePointLeft(decimal)
                        .setScale(decimal, RoundingMode.DOWN)
                    redelegateAmountMsg.visibility = View.GONE
                    redelegateAmount.text = formatAmount(dpAmount.toPlainString(), decimal)
                    redelegateAmount.setTextColor(
                        ContextCompat.getColor(
                            requireContext(), R.color.color_base01
                        )
                    )
                    redelegateDenom.visibility = View.VISIBLE
                    redelegateDenom.text = asset.symbol
                    redelegateDenom.setTextColor(asset.assetColor())
                }
            }
            txSimulate()
        }
    }

    private fun updateFeeView() {
        binding.apply {
            val feePrice = BaseData.getPrice(selectedChain.coinGeckoId)
            if (evmFeeAmount == null) {
                evmFeeAmount = evmGasPrices[selectedFeePosition].multiply(evmGasLimit)
            }
            val dpAmount =
                evmFeeAmount?.toBigDecimal()?.movePointLeft(18)?.setScale(18, RoundingMode.DOWN)
            val value = feePrice.multiply(dpAmount)
            dpAmount?.let { amount ->
                feeAmount.text = formatAmount(amount.toPlainString(), 18)
                feeValue.text = formatAssetValue(value)
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            fromValidatorView.setOnClickListener {
                handleOneClickWithDelay(
                    ValidatorFragment(selectedChain, object : ValidatorListener {
                        override fun select(validatorAddress: String) {
                            if (fromValidator?.operatorAddress != validatorAddress) {
                                fromValidator =
                                    selectedChain.cosmosFetcher?.cosmosValidators?.firstOrNull { it.operatorAddress == validatorAddress }
                                updateFeeView()
                                updateFromValidatorView()
                            }

                            if (fromValidator?.operatorAddress == toValidator?.operatorAddress) {
                                toValidator =
                                    selectedChain.cosmosFetcher?.cosmosValidators?.firstOrNull { it.operatorAddress != toValidator?.operatorAddress }
                                updateToValidatorView()
                            }
                        }
                    })
                )
            }

            toValidatorView.setOnClickListener {
                handleOneClickWithDelay(
                    ValidatorDefaultFragment(
                        selectedChain,
                        fromValidator,
                        listener = object : ValidatorDefaultListener {
                            override fun select(validatorAddress: String) {
                                toValidator =
                                    selectedChain.cosmosFetcher?.cosmosValidators?.firstOrNull { it.operatorAddress == validatorAddress }
                                updateToValidatorView()
                            }
                        })
                )
            }

            amountView.setOnClickListener {
                handleOneClickWithDelay(
                    InsertAmountFragment.newInstance(TxType.RE_DELEGATE,
                        availableAmount.toString(),
                        toCoin?.amount,
                        selectedChain.stakeDenom?.let {
                            BaseData.getAsset(
                                selectedChain.apiName, it
                            )
                        },
                        object : AmountSelectListener {
                            override fun select(toAmount: String) {
                                updateAmountView(toAmount)
                            }
                        })
                )
            }

            feeSegment.setOnPositionChangedListener { position ->
                selectedFeePosition = position
                updateFeeView()
                txSimulate()
            }

            btnRedelegate.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    relegateResultLauncher.launch(this)
                    requireActivity().overridePendingTransition(
                        R.anim.anim_slide_in_bottom, R.anim.anim_fade_out
                    )
                }
            }
        }
    }

    private val relegateResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
//                val web3j = Web3j.build(HttpService(selectedChain.getEvmRpc()))
//                txViewModel.broadcastEvmReDelegate(web3j, evmHexValue)
            }
        }

    private fun txSimulate() {
        binding.apply {
            if (toCoin == null) {
                return
            }
            btnRedelegate.updateButtonView(false)
            backdropLayout.visibility = View.VISIBLE
            txViewModel.simulateEvmReDelegate(
                ByteUtils.convertBech32ToEvm(fromValidator?.operatorAddress),
                ByteUtils.convertBech32ToEvm(toValidator?.operatorAddress),
                toCoin?.amount,
                selectedChain,
                selectedFeePosition
            )
        }
    }

    private fun setUpSimulate() {
        txViewModel.simulateEvmReDelegate.observe(viewLifecycleOwner) { response ->
            response.first?.let { hexValue ->
                evmHexValue = hexValue
                response.second?.let { evmFeeAmount ->
                    this.evmFeeAmount = evmFeeAmount.toBigInteger()
                    updateFeeView()
                    isBroadCastTx(true)
                }
            }
        }

        txViewModel.erc20ErrorMessage.observe(viewLifecycleOwner) {
            isBroadCastTx(false)
            requireContext().makeToast(R.string.error_evm_simul)
            return@observe
        }
    }

    private fun setUpBroadcast() {
        txViewModel.broadcastEvmReDelegateTx.observe(viewLifecycleOwner) { txResponse ->
            Intent(requireContext(), TransferTxResultActivity::class.java).apply {
                if (txResponse?.isNotEmpty() == true) {
                    putExtra("isSuccess", true)
                    putExtra("txHash", txResponse)
                } else {
                    putExtra("isSuccess", false)
                    putExtra("errorMsg", txResponse)
                }
                putExtra("fromChainTag", selectedChain.tag)
                putExtra("toChainTag", selectedChain.tag)
                putExtra("transferStyle", TransferStyle.WEB3_STYLE.ordinal)
                startActivity(this)
            }
            dismiss()
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

    private fun isBroadCastTx(isSuccess: Boolean) {
        binding.backdropLayout.visibility = View.GONE
        binding.btnRedelegate.updateButtonView(isSuccess)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
        txViewModel.broadcastEvmReDelegateTx.removeObservers(viewLifecycleOwner)
    }
}