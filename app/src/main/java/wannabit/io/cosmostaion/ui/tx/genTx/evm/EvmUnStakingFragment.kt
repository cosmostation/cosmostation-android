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
import wannabit.io.cosmostaion.common.isActiveValidator
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.setMonikerImg
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.databinding.FragmentUnStakingBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.ui.main.chain.cosmos.TxType
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TransferTxResultActivity
import wannabit.io.cosmostaion.ui.tx.option.general.AmountSelectListener
import wannabit.io.cosmostaion.ui.tx.option.general.InsertAmountFragment
import wannabit.io.cosmostaion.ui.tx.option.validator.ValidatorFragment
import wannabit.io.cosmostaion.ui.tx.option.validator.ValidatorListener
import wannabit.io.cosmostaion.ui.tx.genTx.BaseTxFragment
import wannabit.io.cosmostaion.ui.tx.genTx.TransferStyle
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

class EvmUnStakingFragment : BaseTxFragment() {

    private var _binding: FragmentUnStakingBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain
    private var validator: StakingProto.Validator? = null

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
            selectedChain: BaseChain,
            validator: StakingProto.Validator?
        ): EvmUnStakingFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putSerializable("validator", validator)
            }
            val fragment = EvmUnStakingFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUnStakingBinding.inflate(layoutInflater, container, false)
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
                validator =
                    arguments?.getSerializable("validator", StakingProto.Validator::class.java)
            } else {
                (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                    selectedChain = it
                }
                validator = arguments?.getSerializable("validator") as? StakingProto.Validator?
            }

            listOf(validatorView, amountView, memoView, feeView).forEach {
                it.setBackgroundResource(
                    R.drawable.cell_bg
                )
            }
            memoView.visibility = View.GONE
            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)

            if (validator != null) {
                selectedChain.cosmosFetcher?.cosmosValidators?.firstOrNull {
                    it.operatorAddress == selectedChain.cosmosFetcher?.cosmosDelegations?.get(
                        0
                    )?.delegation?.validatorAddress
                }
            }
            selectedChain.cosmosFetcher?.cosmosDelegations?.firstOrNull { it.delegation.validatorAddress == validator?.operatorAddress }
                ?.let {
                    availableAmount = it.balance.amount.toBigDecimal()
                }
            updateValidatorView()
        }
    }

    private fun initFee() {
        binding.apply {
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
//                feeTokenImg.setImageResource(selectedChain.coinLogo)
                feeToken.text = selectedChain.coinSymbol

//                val feePrice = BaseData.getPrice(selectedChain.coinGeckoId)
//                val totalGasPrice = evmGasPrices[selectedFeePosition]
//                val amount = totalGasPrice.multiply(evmGasLimit).toBigDecimal()
//                val dpAmount = amount.movePointLeft(18).setScale(18, RoundingMode.DOWN)
//                val value = feePrice.multiply(dpAmount)
//                feeAmount.text = formatAmount(dpAmount.toPlainString(), 18)
//                feeValue.text = formatAssetValue(value)
            }
        }
    }

    private fun updateValidatorView() {
        binding.apply {
            validator?.let { validator ->
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
            BaseData.getAsset(selectedChain.apiName, selectedChain.stakeDenom)?.let { asset ->
                asset.decimals?.let { decimal ->
                    val staked =
                        selectedChain.cosmosFetcher?.cosmosDelegations?.firstOrNull { it.delegation.validatorAddress == validator?.operatorAddress }?.balance?.amount
                    staked?.toBigDecimal()?.movePointLeft(decimal)?.let {
                        stakedAmount.text = formatAmount(it.toPlainString(), decimal)
                    }
                }
            }
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
                    undelegateAmountMsg.visibility = View.GONE
                    undelegateAmount.text = formatAmount(dpAmount.toPlainString(), decimal)
                    undelegateAmount.setTextColor(
                        ContextCompat.getColor(
                            requireContext(), R.color.color_base01
                        )
                    )
                    undelegateDenom.visibility = View.VISIBLE
                    undelegateDenom.text = asset.symbol
                    undelegateDenom.setTextColor(asset.assetColor())
                }
            }
            txSimulate()
        }
    }

    private fun updateFeeView() {
        binding.apply {
//            val feePrice = BaseData.getPrice(selectedChain.coinGeckoId)
//            if (evmFeeAmount == null) {
//                evmFeeAmount = evmGasPrices[selectedFeePosition].multiply(evmGasLimit)
//            }
//            val dpAmount =
//                evmFeeAmount?.toBigDecimal()?.movePointLeft(18)?.setScale(18, RoundingMode.DOWN)
//            val value = feePrice.multiply(dpAmount)
//            dpAmount?.let { amount ->
//                feeAmount.text = formatAmount(amount.toPlainString(), 18)
//                feeValue.text = formatAssetValue(value)
//            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            validatorView.setOnClickListener {
                handleOneClickWithDelay(
                    ValidatorFragment(selectedChain, object : ValidatorListener {
                        override fun select(validatorAddress: String) {
                            if (validator?.operatorAddress != validatorAddress) {
                                validator =
                                    selectedChain.cosmosFetcher?.cosmosValidators?.firstOrNull { it.operatorAddress == validatorAddress }
                                updateValidatorView()
                            }
                        }
                    })
                )
            }

            amountView.setOnClickListener {
                handleOneClickWithDelay(
                    InsertAmountFragment.newInstance(
                        selectedChain,
                        TxType.UN_DELEGATE,
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

            btnUnstake.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    unDelegateResultLauncher.launch(this)
                    requireActivity().overridePendingTransition(
                        R.anim.anim_slide_in_bottom, R.anim.anim_fade_out
                    )
                }
            }
        }
    }

    private val unDelegateResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
//                val web3j = Web3j.build(HttpService(selectedChain.getEvmRpc()))
//                txViewModel.broadcastEvmUnDelegate(web3j, evmHexValue)
            }
        }

    private fun txSimulate() {
        binding.apply {
            if (toCoin == null) {
                return
            }
            btnUnstake.updateButtonView(false)
            backdropLayout.visibility = View.VISIBLE
            txViewModel.simulateEvmUnDelegate(
                ByteUtils.convertBech32ToEvm(validator?.operatorAddress),
                toCoin?.amount,
                selectedChain,
                selectedFeePosition
            )
        }
    }

    private fun setUpSimulate() {
        txViewModel.simulateEvmUnDelegate.observe(viewLifecycleOwner) { response ->
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
        txViewModel.broadcastEvmUnDelegateTx.observe(viewLifecycleOwner) { txResponse ->
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
        binding.btnUnstake.updateButtonView(isSuccess)
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
        txViewModel.broadcastEvmUnDelegateTx.removeObservers(viewLifecycleOwner)
    }
}