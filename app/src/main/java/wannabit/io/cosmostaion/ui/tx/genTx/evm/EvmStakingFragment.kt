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
import com.cosmos.staking.v1beta1.StakingProto
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
import wannabit.io.cosmostaion.databinding.FragmentStakingBinding
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
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

class EvmStakingFragment : BaseTxFragment() {

    private var _binding: FragmentStakingBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain
    private var toValidator: StakingProto.Validator? = null

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
            selectedChain: BaseChain, toValidator: StakingProto.Validator?
        ): EvmStakingFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putSerializable("toValidator", toValidator)
            }
            val fragment = EvmStakingFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStakingBinding.inflate(layoutInflater, container, false)
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
                toValidator =
                    arguments?.getSerializable("toValidator", StakingProto.Validator::class.java)
            } else {
                (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                    selectedChain = it
                }
                toValidator = arguments?.getSerializable("toValidator") as? StakingProto.Validator?
            }

            listOf(validatorView, amountView, memoView, feeView).forEach {
                it.setBackgroundResource(
                    R.drawable.cell_bg
                )
            }
            memoView.visibility = View.GONE
            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)

            if (toValidator == null) {
                selectedChain.cosmosFetcher?.cosmosValidators?.firstOrNull { it.description.moniker == "Cosmostation" }
                    ?.let { validator ->
                        toValidator = validator
                    } ?: run {
                    toValidator = selectedChain.cosmosFetcher?.cosmosValidators?.get(0)
                }
            }
            availableAmount = selectedChain.cosmosFetcher?.balanceAmount(selectedChain.stakeDenom)
            updateValidatorView()
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

    private fun updateValidatorView() {
        binding.apply {
            toValidator?.let { validator ->
                monikerImg.setMonikerImg(selectedChain, validator.operatorAddress)
                monikerName.text = validator.description?.moniker?.trim()

                val statusImage = when {
                    validator.jailed -> R.drawable.icon_jailed
                    !validator.isActiveValidator(selectedChain) -> R.drawable.icon_inactive
                    else -> 0
                }
                jailedImg.visibility = if (statusImage != 0) View.VISIBLE else View.GONE
                jailedImg.setImageResource(statusImage)
            }

            val commissionRate =
                toValidator?.commission?.commissionRates?.rate?.toBigDecimal()?.movePointLeft(16)
                    ?.setScale(2, RoundingMode.DOWN)
            commissionPercent.text = formatString("$commissionRate%", 3)
        }
        txSimulate()
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
                    delegateAmountMsg.visibility = View.GONE
                    delegateAmount.text = formatAmount(dpAmount.toPlainString(), decimal)
                    delegateAmount.setTextColor(
                        ContextCompat.getColor(
                            requireContext(), R.color.color_base01
                        )
                    )
                    delegateDenom.visibility = View.VISIBLE
                    delegateDenom.text = asset.symbol
                    delegateDenom.setTextColor(asset.assetColor())
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
            validatorView.setOnClickListener {
                handleOneClickWithDelay(
                    ValidatorDefaultFragment(selectedChain,
                        listener = object : ValidatorDefaultListener {
                            override fun select(validatorAddress: String) {
                                toValidator =
                                    selectedChain.cosmosFetcher?.cosmosValidators?.firstOrNull { it.operatorAddress == validatorAddress }
                                updateValidatorView()
                            }
                        })
                )
            }

            amountView.setOnClickListener {
                handleOneClickWithDelay(
                    InsertAmountFragment.newInstance(TxType.DELEGATE,
                        availableAmount.toString(),
                        toCoin?.amount,
                        BaseData.getAsset(
                            selectedChain.apiName, selectedChain.stakeDenom
                        ),
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

            btnStake.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    delegateResultLauncher.launch(this)
                    requireActivity().overridePendingTransition(
                        R.anim.anim_slide_in_bottom, R.anim.anim_fade_out
                    )
                }
            }
        }
    }

    private fun txSimulate() {
        binding.apply {
            if (toCoin == null) {
                return
            }
            btnStake.updateButtonView(false)
            backdropLayout.visibility = View.VISIBLE
            txViewModel.simulateEvmDelegate(
                ByteUtils.convertBech32ToEvm(toValidator?.operatorAddress),
                toCoin?.amount,
                selectedChain,
                selectedFeePosition
            )
        }
    }

    private fun setUpSimulate() {
        txViewModel.simulateEvmDelegate.observe(viewLifecycleOwner) { response ->
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

    private val delegateResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
//                val web3j = Web3j.build(HttpService(selectedChain.getEvmRpc()))
//                txViewModel.broadcastEvmDelegate(web3j, evmHexValue)
            }
        }

    private fun setUpBroadcast() {
        txViewModel.broadcastEvmDelegateTx.observe(viewLifecycleOwner) { txResponse ->
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

    private fun isBroadCastTx(isSuccess: Boolean) {
        binding.backdropLayout.visibility = View.GONE
        binding.btnStake.updateButtonView(isSuccess)
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
        txViewModel.broadcastEvmDelegateTx.removeObservers(viewLifecycleOwner)
    }
}