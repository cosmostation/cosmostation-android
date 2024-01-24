package wannabit.io.cosmostaion.ui.tx.step.kava

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.cosmos.base.abci.v1beta1.AbciProto
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.staking.v1beta1.StakingProto
import com.cosmos.staking.v1beta1.StakingProto.Validator
import com.cosmos.tx.v1beta1.TxProto
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kava.router.v1beta1.TxProto.MsgDelegateMintDeposit
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.amountHandlerLeft
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.formatString
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.common.setMonikerImg
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.res.FeeInfo
import wannabit.io.cosmostaion.databinding.FragmentDepositEarningBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.ui.option.tx.general.AmountSelectListener
import wannabit.io.cosmostaion.ui.option.tx.general.AssetFragment
import wannabit.io.cosmostaion.ui.option.tx.general.AssetSelectListener
import wannabit.io.cosmostaion.ui.option.tx.general.InsertAmountFragment
import wannabit.io.cosmostaion.ui.option.tx.general.MemoFragment
import wannabit.io.cosmostaion.ui.option.tx.general.MemoListener
import wannabit.io.cosmostaion.ui.option.tx.validator.ValidatorDefaultFragment
import wannabit.io.cosmostaion.ui.option.tx.validator.ValidatorDefaultListener
import wannabit.io.cosmostaion.ui.main.chain.TxType
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.tx.step.BaseTxFragment
import java.math.BigDecimal
import java.math.RoundingMode

class DepositEarningFragment : BaseTxFragment() {

    private var _binding: FragmentDepositEarningBinding? = null
    private val binding get() = _binding!!

    private var selectedChain: CosmosLine? = null
    private var toValidator: Validator? = null

    private var feeInfos: MutableList<FeeInfo> = mutableListOf()
    private var selectedFeeInfo = 0
    private var txFee: TxProto.Fee? = null

    private var toCoin: CoinProto.Coin? = null
    private var txMemo = ""

    private var availableAmount = BigDecimal.ZERO

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(
            selectedChain: CosmosLine?, toValidator: Validator?
        ): DepositEarningFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putSerializable("toValidator", toValidator)
            }
            val fragment = DepositEarningFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDepositEarningBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initFee()
        updateFeeView()
        setUpClickAction()
        setUpSimulate()
        setUpBroadcast()
    }

    private fun initView() {
        binding.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                selectedChain = arguments?.getParcelable("selectedChain", CosmosLine::class.java)
                toValidator = arguments?.getSerializable("toValidator", Validator::class.java)
            } else {
                selectedChain = arguments?.getParcelable("selectedChain") as? CosmosLine
                toValidator = arguments?.getSerializable("toValidator") as? Validator
            }

            listOf(validatorView, amountView, memoView, feeView).forEach {
                it.setBackgroundResource(
                    R.drawable.cell_bg
                )
            }
            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)

            if (toValidator == null) {
                selectedChain?.let { chain ->
                    chain.cosmosValidators.firstOrNull { it.description.moniker == "Cosmostation" }
                        ?.let { validator ->
                            toValidator = validator
                        } ?: run {
                        toValidator = chain.cosmosValidators[0]
                    }
                }
            }
            updateValidatorView()
        }
    }

    private fun initFee() {
        binding.apply {
            selectedChain?.let { chain ->
                feeInfos = chain.getFeeInfos(requireContext())
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

                for (i in feeInfos.indices) {
                    val segmentView = ItemSegmentedFeeBinding.inflate(layoutInflater)
                    feeSegment.addView(
                        segmentView.root,
                        i,
                        LinearLayout.LayoutParams(0, dpToPx(requireContext(), 32), 1f)
                    )
                    segmentView.btnTitle.text = feeInfos[i].title
                }
                feeSegment.setPosition(chain.getFeeBasePosition(), false)
                selectedFeeInfo = chain.getFeeBasePosition()
                txFee = chain.getInitFee(requireContext())
            }
        }
    }

    private fun updateValidatorView() {
        binding.apply {
            toValidator?.let { validator ->
                selectedChain?.let { chain ->
                    monikerImg.setMonikerImg(chain, validator.operatorAddress)
                    monikerName.text = validator.description?.moniker

                    val statusImage = when {
                        validator.jailed -> R.drawable.icon_jailed
                        validator.status != StakingProto.BondStatus.BOND_STATUS_BONDED -> R.drawable.icon_inactive
                        else -> 0
                    }
                    jailedImg.visibility = if (statusImage != 0) View.VISIBLE else View.GONE
                    jailedImg.setImageResource(statusImage)
                }

                val commissionRate = toValidator?.commission?.commissionRates?.rate?.toBigDecimal()
                    ?.movePointLeft(16)?.setScale(2, RoundingMode.DOWN)
                commissionPercent.text = formatString("$commissionRate%", 3)
                if (commissionRate.toString() == "0.00") {
                    commissionPercent.setTextColor(
                        ContextCompat.getColorStateList(
                            requireContext(), R.color.color_accent_green
                        )
                    )
                } else {
                    commissionPercent.setTextColor(
                        ContextCompat.getColorStateList(
                            requireContext(), R.color.color_base01
                        )
                    )
                }
            }
        }
        txSimulate()
    }

    private fun updateAmountView(toAmount: String) {
        binding.apply {
            selectedChain?.let { chain ->
                toCoin = CoinProto.Coin.newBuilder().setAmount(toAmount).setDenom(chain.stakeDenom)
                    .build()

                chain.stakeDenom?.let { denom ->
                    BaseData.getAsset(chain.apiName, denom)?.let { asset ->
                        asset.decimals?.let { decimal ->
                            val dpAmount = BigDecimal(toAmount).movePointLeft(decimal)
                                .setScale(decimal, RoundingMode.DOWN)
                            addAmountMsg.visibility = View.GONE
                            addAmount.text = formatAmount(dpAmount.toPlainString(), decimal)
                            addAmount.setTextColor(
                                ContextCompat.getColor(
                                    requireContext(), R.color.color_base01
                                )
                            )
                            addDenom.visibility = View.VISIBLE
                            addDenom.text = asset.symbol
                        }
                    }
                }
                txSimulate()
            }
        }
    }

    private fun updateMemoView(memo: String) {
        binding.apply {
            txMemo = memo
            if (txMemo.isEmpty()) {
                tabMemoMsg.text = getString(R.string.str_tap_for_add_memo_msg)
                tabMemoMsg.setTextColor(
                    ContextCompat.getColorStateList(
                        requireContext(), R.color.color_base03
                    )
                )
            } else {
                tabMemoMsg.text = txMemo
                tabMemoMsg.setTextColor(
                    ContextCompat.getColorStateList(
                        requireContext(), R.color.color_base01
                    )
                )
            }
        }
        txSimulate()
    }

    private fun updateFeeView() {
        binding.apply {
            selectedChain?.let { chain ->
                txFee?.getAmount(0)?.let { fee ->
                    BaseData.getAsset(chain.apiName, fee.denom)?.let { asset ->
                        feeTokenImg.setTokenImg(asset)
                        feeToken.text = asset.symbol

                        val amount =
                            fee.amount.toBigDecimal().amountHandlerLeft(asset.decimals ?: 6)
                        val price = BaseData.getPrice(asset.coinGeckoId)
                        val value = price.multiply(amount)

                        feeAmount.text = formatAmount(amount.toPlainString(), asset.decimals ?: 6)
                        feeValue.text = formatAssetValue(value)
                    }

                    chain.stakeDenom?.let { denom ->
                        val balanceAmount = chain.balanceAmount(denom)
                        val vestingAmount = chain.vestingAmount(denom)

                        txFee?.let {
                            availableAmount = if (it.getAmount(0).denom == denom) {
                                val feeAmount = it.getAmount(0).amount.toBigDecimal()
                                if (feeAmount > balanceAmount) {
                                    BigDecimal.ZERO
                                } else {
                                    balanceAmount.add(vestingAmount).subtract(feeAmount)
                                }
                            } else {
                                balanceAmount.add(vestingAmount)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            selectedChain?.let { chain ->
                validatorView.setOnClickListener {
                    handleOneClickWithDelay(
                        ValidatorDefaultFragment(chain, null, object : ValidatorDefaultListener {
                            override fun select(validatorAddress: String) {
                                toValidator =
                                    chain.cosmosValidators.firstOrNull { it.operatorAddress == validatorAddress }
                                updateValidatorView()
                            }
                        })
                    )
                }

                amountView.setOnClickListener {
                    handleOneClickWithDelay(
                        InsertAmountFragment(TxType.EARN_DEPOSIT,
                            null,
                            availableAmount,
                            toCoin?.amount,
                            chain.stakeDenom?.let { denom ->
                                BaseData.getAsset(
                                    chain.apiName, denom
                                )
                            },
                            null,
                            object : AmountSelectListener {
                                override fun select(toAmount: String) {
                                    updateAmountView(toAmount)
                                }
                            })
                    )
                }

                memoView.setOnClickListener {
                    handleOneClickWithDelay(MemoFragment(txMemo, object : MemoListener {
                        override fun memo(memo: String) {
                            updateMemoView(memo)
                        }
                    }))
                }

                feeTokenLayout.setOnClickListener {
                    handleOneClickWithDelay(
                        AssetFragment(chain,
                            feeInfos[selectedFeeInfo].feeDatas,
                            object : AssetSelectListener {
                                override fun select(denom: String) {
                                    chain.getDefaultFeeCoins(requireContext())
                                        .firstOrNull { it.denom == denom }?.let { feeCoin ->
                                            val updateFeeCoin =
                                                CoinProto.Coin.newBuilder().setDenom(denom)
                                                    .setAmount(feeCoin.amount).build()

                                            val updateTxFee = TxProto.Fee.newBuilder()
                                                .setGasLimit(BaseConstant.BASE_GAS_AMOUNT.toLong())
                                                .addAmount(updateFeeCoin).build()

                                            txFee = updateTxFee
                                            updateFeeView()
                                            txSimulate()
                                        }
                                }
                            })
                    )
                }

                feeSegment.setOnPositionChangedListener { position ->
                    selectedFeeInfo = position
                    txFee = chain.getBaseFee(
                        requireContext(), selectedFeeInfo, txFee?.getAmount(0)?.denom
                    )
                    updateFeeView()
                    txSimulate()
                }

                btnDepositLiquidity.setOnClickListener {
                    Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                        depositLiquidityResultLauncher.launch(this)
                        requireActivity().overridePendingTransition(
                            R.anim.anim_slide_in_bottom, R.anim.anim_fade_out
                        )
                    }
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
            }, 1000)
        }
    }

    private val depositLiquidityResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
                selectedChain?.let { chain ->
                    txViewModel.broadEarnDeposit(
                        getChannel(chain), chain.address, onBindEarnDeposit(), txFee, txMemo, chain
                    )
                }
            }
        }

    private fun txSimulate() {
        binding.apply {
            selectedChain?.let { chain ->
                if (toCoin == null) {
                    return
                }
                btnDepositLiquidity.updateButtonView(false)
                backdropLayout.visibility = View.VISIBLE
                txViewModel.simulateEarnDeposit(
                    getChannel(chain), chain.address, onBindEarnDeposit(), txFee, txMemo
                )
            }
        }
    }

    private fun setUpSimulate() {
        txViewModel.simulate.observe(viewLifecycleOwner) { gasInfo ->
            isBroadCastTx(true)
            updateFeeViewWithSimulate(gasInfo)
        }

        txViewModel.errorMessage.observe(viewLifecycleOwner) { response ->
            isBroadCastTx(false)
            requireContext().showToast(view, response, true)
            return@observe
        }
    }

    private fun updateFeeViewWithSimulate(gasInfo: AbciProto.GasInfo) {
        selectedChain?.let { chain ->
            txFee?.let { fee ->
                feeInfos[selectedFeeInfo].feeDatas.firstOrNull { it.denom == fee.getAmount(0).denom }
                    ?.let { gasRate ->
                        val gasLimit = (gasInfo.gasUsed.toDouble() * chain.gasMultiply()).toLong()
                            .toBigDecimal()
                        val feeCoinAmount =
                            gasRate.gasRate?.multiply(gasLimit)?.setScale(0, RoundingMode.UP)

                        val feeCoin = CoinProto.Coin.newBuilder().setDenom(fee.getAmount(0).denom)
                            .setAmount(feeCoinAmount.toString()).build()
                        txFee = TxProto.Fee.newBuilder().setGasLimit(gasLimit.toLong())
                            .addAmount(feeCoin).build()
                    }
            }
        }
        updateFeeView()
    }

    private fun isBroadCastTx(isSuccess: Boolean) {
        binding.backdropLayout.visibility = View.GONE
        binding.btnDepositLiquidity.updateButtonView(isSuccess)
    }

    private fun setUpBroadcast() {
        txViewModel.broadcastTx.observe(viewLifecycleOwner) { txResponse ->
            Intent(requireContext(), TxResultActivity::class.java).apply {
                if (txResponse.code > 0) {
                    putExtra("isSuccess", false)
                } else {
                    putExtra("isSuccess", true)
                }
                putExtra("errorMsg", txResponse.rawLog)
                putExtra("selectedChain", selectedChain?.tag)
                val hash = txResponse.txhash
                if (!TextUtils.isEmpty(hash)) putExtra("txHash", hash)
                startActivity(this)
            }
        }
    }

    private fun onBindEarnDeposit(): MsgDelegateMintDeposit? {
        return MsgDelegateMintDeposit.newBuilder().setDepositor(selectedChain?.address)
            .setValidator(toValidator?.operatorAddress).setAmount(toCoin).build()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}