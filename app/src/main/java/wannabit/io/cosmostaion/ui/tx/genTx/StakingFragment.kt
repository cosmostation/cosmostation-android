package wannabit.io.cosmostaion.ui.tx.genTx

import android.annotation.SuppressLint
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
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.staking.v1beta1.StakingProto.Validator
import com.cosmos.staking.v1beta1.TxProto.MsgDelegate
import com.cosmos.tx.v1beta1.TxProto
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.protobuf.Any
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBabylon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainZenrock
import wannabit.io.cosmostaion.chain.testnetClass.ChainInitiaTestnet
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.amountHandlerLeft
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.formatString
import wannabit.io.cosmostaion.common.getdAmount
import wannabit.io.cosmostaion.common.isActiveValidator
import wannabit.io.cosmostaion.common.setMonikerImg
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.res.FeeInfo
import wannabit.io.cosmostaion.databinding.FragmentStakingBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.sign.Signer
import wannabit.io.cosmostaion.ui.main.chain.cosmos.TxType
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.tx.option.general.AmountSelectListener
import wannabit.io.cosmostaion.ui.tx.option.general.AssetFragment
import wannabit.io.cosmostaion.ui.tx.option.general.AssetSelectListener
import wannabit.io.cosmostaion.ui.tx.option.general.BaseFeeAssetFragment
import wannabit.io.cosmostaion.ui.tx.option.general.BaseFeeAssetSelectListener
import wannabit.io.cosmostaion.ui.tx.option.general.InsertAmountFragment
import wannabit.io.cosmostaion.ui.tx.option.general.MemoFragment
import wannabit.io.cosmostaion.ui.tx.option.general.MemoListener
import wannabit.io.cosmostaion.ui.tx.option.validator.ValidatorDefaultFragment
import wannabit.io.cosmostaion.ui.tx.option.validator.ValidatorDefaultListener
import java.math.BigDecimal
import java.math.RoundingMode

class StakingFragment : BaseTxFragment() {

    private var _binding: FragmentStakingBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain
    private var toValidator: Validator? = null
    private var initiaToValidator: com.initia.mstaking.v1.StakingProto.Validator? = null
    private var zenrockToValidator: com.zrchain.validation.HybridValidationProto.ValidatorHV? = null

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
            selectedChain: BaseChain,
            toValidator: Validator? = null,
            initiaToValidator: com.initia.mstaking.v1.StakingProto.Validator? = null,
            zenrockToValidator: com.zrchain.validation.HybridValidationProto.ValidatorHV? = null
        ): StakingFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putSerializable("toValidator", toValidator)
                putSerializable("initiaToValidator", initiaToValidator)
                putSerializable("zenrockToValidator", zenrockToValidator)
            }
            val fragment = StakingFragment()
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
                toValidator = arguments?.getSerializable("toValidator", Validator::class.java)
                initiaToValidator = arguments?.getSerializable(
                    "initiaToValidator", com.initia.mstaking.v1.StakingProto.Validator::class.java
                )
                zenrockToValidator = arguments?.getSerializable(
                    "zenrockToValidator",
                    com.zrchain.validation.HybridValidationProto.ValidatorHV::class.java
                )

            } else {
                (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                    selectedChain = it
                }
                toValidator = arguments?.getSerializable("toValidator") as? Validator?
                initiaToValidator =
                    arguments?.getSerializable("initiaToValidator") as? com.initia.mstaking.v1.StakingProto.Validator?
                zenrockToValidator =
                    arguments?.getSerializable("zenrockToValidator") as? com.zrchain.validation.HybridValidationProto.ValidatorHV?
            }

            BaseData.getAsset(selectedChain.apiName, selectedChain.stakeDenom)?.let { asset ->
                titleStakeImg.setTokenImg(asset)
                titleStake.text = getString(R.string.title_staking, asset.symbol)
            }

            listOf(validatorView, amountView, memoView, feeView).forEach {
                it.setBackgroundResource(
                    R.drawable.cell_bg
                )
            }
            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)

            when (selectedChain) {
                is ChainInitiaTestnet -> {
                    if (initiaToValidator == null) {
                        (selectedChain as ChainInitiaTestnet).initiaFetcher()?.initiaValidators?.firstOrNull { it.description.moniker == "Cosmostation" }
                            ?.let { validator ->
                                initiaToValidator = validator
                            } ?: run {
                            initiaToValidator =
                                (selectedChain as ChainInitiaTestnet).initiaFetcher()?.initiaValidators?.get(
                                    0
                                )
                        }
                    }
                }

                is ChainZenrock -> {
                    if (zenrockToValidator == null) {
                        (selectedChain as ChainZenrock).zenrockFetcher()?.zenrockValidators?.firstOrNull { it.description.moniker == "Cosmostation" }
                            ?.let { validator ->
                                zenrockToValidator = validator
                            } ?: run {
                            zenrockToValidator =
                                (selectedChain as ChainZenrock).zenrockFetcher()?.zenrockValidators?.get(
                                    0
                                )
                        }
                    }
                }

                else -> {
                    if (toValidator == null) {
                        selectedChain.cosmosFetcher?.cosmosValidators?.firstOrNull { it.description.moniker == "Cosmostation" }
                            ?.let { validator ->
                                toValidator = validator
                            } ?: run {
                            toValidator = selectedChain.cosmosFetcher?.cosmosValidators?.get(0)
                        }
                    }
                }
            }
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

            if (selectedChain.cosmosFetcher?.cosmosBaseFees?.isNotEmpty() == true) {
                val tipTitle = listOf(
                    "Default", "Fast", "Faster", "Instant"
                )
                for (i in tipTitle.indices) {
                    val segmentView = ItemSegmentedFeeBinding.inflate(layoutInflater)
                    feeSegment.addView(
                        segmentView.root,
                        i,
                        LinearLayout.LayoutParams(0, dpToPx(requireContext(), 32), 1f)
                    )
                    segmentView.btnTitle.text = tipTitle[i]
                }
                feeSegment.setPosition(selectedFeeInfo, false)
                val baseFee = selectedChain.cosmosFetcher?.cosmosBaseFees?.get(0)
                val gasAmount = selectedChain.getInitGasLimit().toBigDecimal()
                val feeDenom = baseFee?.denom
                val feeAmount =
                    baseFee?.getdAmount()?.multiply(gasAmount)?.setScale(0, RoundingMode.DOWN)
                txFee = TxProto.Fee.newBuilder().setGasLimit(gasAmount.toLong()).addAmount(
                    CoinProto.Coin.newBuilder().setDenom(feeDenom).setAmount(feeAmount.toString())
                        .build()
                ).build()

            } else {
                feeInfos = selectedChain.getFeeInfos(requireContext())
                for (i in feeInfos.indices) {
                    val segmentView = ItemSegmentedFeeBinding.inflate(layoutInflater)
                    feeSegment.addView(
                        segmentView.root,
                        i,
                        LinearLayout.LayoutParams(0, dpToPx(requireContext(), 32), 1f)
                    )
                    segmentView.btnTitle.text = feeInfos[i].title
                }
                feeSegment.setPosition(selectedChain.getFeeBasePosition(), false)
                selectedFeeInfo = selectedChain.getFeeBasePosition()
                txFee = selectedChain.getInitFee(requireContext())
            }
        }
        updateFeeView()
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

                val commissionRate =
                    validator.commission.commissionRates.rate.toBigDecimal().movePointLeft(16)
                        .setScale(2, RoundingMode.DOWN)
                commissionPercent.text = formatString("$commissionRate%", 3)
            }

            initiaToValidator?.let { validator ->
                monikerImg.setMonikerImg(selectedChain, validator.operatorAddress)
                monikerName.text = validator.description?.moniker?.trim()

                val statusImage = when {
                    validator.jailed -> R.drawable.icon_jailed
                    !validator.isActiveValidator(selectedChain as ChainInitiaTestnet) -> R.drawable.icon_inactive
                    else -> 0
                }
                jailedImg.visibility = if (statusImage != 0) View.VISIBLE else View.GONE
                jailedImg.setImageResource(statusImage)

                val commissionRate =
                    validator.commission.commissionRates.rate.toBigDecimal().movePointLeft(16)
                        .setScale(2, RoundingMode.DOWN)
                commissionPercent.text = formatString("$commissionRate%", 3)
            }

            zenrockToValidator?.let { validator ->
                monikerImg.setMonikerImg(selectedChain, validator.operatorAddress)
                monikerName.text = validator.description?.moniker?.trim()

                val statusImage = when {
                    validator.jailed -> R.drawable.icon_jailed
                    !validator.isActiveValidator(selectedChain as ChainZenrock) -> R.drawable.icon_inactive
                    else -> 0
                }
                jailedImg.visibility = if (statusImage != 0) View.VISIBLE else View.GONE
                jailedImg.setImageResource(statusImage)

                val commissionRate =
                    validator.commission.commissionRates.rate.toBigDecimal().movePointLeft(16)
                        .setScale(2, RoundingMode.DOWN)
                commissionPercent.text = formatString("$commissionRate%", 3)
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
                val price = BaseData.getPrice(asset.coinGeckoId)
                val dpAmount = BigDecimal(toAmount).movePointLeft(asset.decimals ?: 6)
                    .setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                val value = price.multiply(dpAmount)

                delegateAmountMsg.visibility = View.GONE
                delegateAmount.text = formatAmount(dpAmount.toPlainString(), asset.decimals ?: 6)
                delegateAmount.setTextColor(
                    ContextCompat.getColor(
                        requireContext(), R.color.color_base01
                    )
                )
                delegateDenom.visibility = View.VISIBLE
                delegateDenom.text = asset.symbol
                delegateDenom.setTextColor(asset.assetColor())
                delegateValue.text = formatAssetValue(value)
            }
            txSimulate()
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
            txFee?.getAmount(0)?.let { fee ->
                BaseData.getAsset(selectedChain.apiName, fee.denom)?.let { asset ->
                    feeTokenImg.setTokenImg(asset)
                    feeToken.text = asset.symbol

                    val amount = fee.amount.toBigDecimal().amountHandlerLeft(asset.decimals ?: 6)
                    val price = BaseData.getPrice(asset.coinGeckoId)
                    val value = price.multiply(amount)

                    feeAmount.text = formatAmount(amount.toPlainString(), asset.decimals ?: 6)
                    feeValue.text = formatAssetValue(value)
                }

                val balanceAmount =
                    selectedChain.cosmosFetcher?.balanceAmount(selectedChain.stakeDenom)
                val vestingAmount =
                    selectedChain.cosmosFetcher?.vestingAmount(selectedChain.stakeDenom)

                txFee?.let {
                    availableAmount = if (it.getAmount(0).denom == selectedChain.stakeDenom) {
                        val feeAmount = it.getAmount(0).amount.toBigDecimal()
                        if (feeAmount > balanceAmount) {
                            BigDecimal.ZERO
                        } else {
                            balanceAmount?.add(vestingAmount)?.subtract(feeAmount)
                        }
                    } else {
                        balanceAmount?.add(vestingAmount)
                    }
                }
            }
        }
    }

    @SuppressLint("WrongConstant")
    private fun setUpClickAction() {
        binding.apply {
            validatorView.setOnClickListener {
                handleOneClickWithDelay(
                    ValidatorDefaultFragment(selectedChain,
                        listener = object : ValidatorDefaultListener {
                            override fun select(validatorAddress: String) {
                                when (selectedChain) {
                                    is ChainInitiaTestnet -> initiaToValidator =
                                        (selectedChain as ChainInitiaTestnet).initiaFetcher()?.initiaValidators?.firstOrNull { it.operatorAddress == validatorAddress }

                                    is ChainZenrock -> zenrockToValidator =
                                        (selectedChain as ChainZenrock).zenrockFetcher()?.zenrockValidators?.firstOrNull { it.operatorAddress == validatorAddress }

                                    else -> toValidator =
                                        selectedChain.cosmosFetcher?.cosmosValidators?.firstOrNull { it.operatorAddress == validatorAddress }
                                }
                                updateValidatorView()
                            }
                        })
                )
            }

            amountView.setOnClickListener {
                handleOneClickWithDelay(
                    InsertAmountFragment.newInstance(selectedChain, TxType.DELEGATE,
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

            memoView.setOnClickListener {
                handleOneClickWithDelay(
                    MemoFragment.newInstance(selectedChain, txMemo, object : MemoListener {
                        override fun memo(memo: String) {
                            updateMemoView(memo)
                        }
                    })
                )
            }

            feeTokenLayout.setOnClickListener {
                txFee?.let { fee ->
                    if (selectedChain.cosmosFetcher?.cosmosBaseFees?.isNotEmpty() == true) {
                        handleOneClickWithDelay(
                            BaseFeeAssetFragment(selectedChain,
                                selectedChain.cosmosFetcher?.cosmosBaseFees,
                                object : BaseFeeAssetSelectListener {
                                    override fun select(denom: String) {
                                        selectedChain.cosmosFetcher?.cosmosBaseFees?.firstOrNull { it.denom == denom }
                                            ?.let { baseFee ->
                                                val feeAmount = baseFee.getdAmount()
                                                    .multiply(fee.gasLimit.toBigDecimal())
                                                    ?.setScale(0, RoundingMode.DOWN)
                                                val updateFeeCoin =
                                                    CoinProto.Coin.newBuilder().setDenom(denom)
                                                        .setAmount(feeAmount.toString()).build()
                                                txFee = TxProto.Fee.newBuilder()
                                                    .setGasLimit(fee.gasLimit)
                                                    .addAmount(updateFeeCoin).build()
                                                txFee = Signer.setFee(selectedFeeInfo, txFee)

                                                updateFeeView()
                                                txSimulate()
                                            }
                                    }
                                })
                        )

                    } else {
                        handleOneClickWithDelay(
                            AssetFragment.newInstance(selectedChain,
                                feeInfos[selectedFeeInfo].feeDatas.toMutableList(),
                                object : AssetSelectListener {
                                    override fun select(denom: String) {
                                        feeInfos[selectedFeeInfo].feeDatas.firstOrNull { it.denom == denom }
                                            ?.let { feeCoin ->
                                                val gasAmount =
                                                    selectedChain.getInitGasLimit().toBigDecimal()
                                                val updateFeeCoin =
                                                    CoinProto.Coin.newBuilder().setDenom(denom)
                                                        .setAmount(
                                                            feeCoin.gasRate?.multiply(
                                                                gasAmount
                                                            )?.setScale(0, RoundingMode.UP)
                                                                .toString()
                                                        ).build()

                                                txFee = TxProto.Fee.newBuilder().setGasLimit(
                                                    selectedChain.getInitGasLimit()
                                                ).addAmount(updateFeeCoin).build()

                                                updateFeeView()
                                                txSimulate()
                                            }
                                    }
                                })
                        )
                    }
                }
            }

            feeSegment.setOnPositionChangedListener { position ->
                selectedFeeInfo = position
                txFee = if (selectedChain.cosmosFetcher?.cosmosBaseFees?.isNotEmpty() == true) {
                    val baseFee = selectedChain.cosmosFetcher?.cosmosBaseFees?.firstOrNull {
                        it.denom == txFee?.getAmount(0)?.denom
                    }
                    val gasAmount = txFee?.gasLimit?.toBigDecimal()
                    val feeDenom = baseFee?.denom
                    val feeAmount =
                        baseFee?.getdAmount()?.multiply(gasAmount)?.setScale(0, RoundingMode.DOWN)
                    txFee = TxProto.Fee.newBuilder().setGasLimit(gasAmount!!.toLong()).addAmount(
                        CoinProto.Coin.newBuilder().setDenom(feeDenom)
                            .setAmount(feeAmount.toString()).build()
                    ).build()
                    Signer.setFee(selectedFeeInfo, txFee)

                } else {
                    selectedChain.getBaseFee(
                        requireContext(), selectedFeeInfo, txFee?.getAmount(0)?.denom
                    )
                }
                updateFeeView()
                txSimulate()
            }

            btnStake.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    delegateResultLauncher.launch(this)
                    if (Build.VERSION.SDK_INT >= 34) {
                        requireActivity().overrideActivityTransition(
                            Activity.OVERRIDE_TRANSITION_OPEN,
                            R.anim.anim_slide_in_bottom,
                            R.anim.anim_fade_out
                        )
                    } else {
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
            }, 300)
        }
    }

    private val delegateResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
                txViewModel.broadcast(
                    selectedChain.cosmosFetcher?.getChannel(),
                    onBindDelegateMsg(),
                    txFee,
                    txMemo,
                    selectedChain
                )
            }
        }

    private fun txSimulate() {
        binding.apply {
            if (toCoin == null) {
                return
            }
            if (!selectedChain.isSimulable()) {
                return updateFeeViewWithSimulate(null)
            }
            btnStake.updateButtonView(false)
            backdropLayout.visibility = View.VISIBLE
            txViewModel.simulate(
                selectedChain.cosmosFetcher?.getChannel(),
                onBindDelegateMsg(),
                txFee,
                txMemo,
                selectedChain
            )
        }
    }

    private fun setUpSimulate() {
        txViewModel.simulate.observe(viewLifecycleOwner) { gasUsed ->
            updateFeeViewWithSimulate(gasUsed)
        }

        txViewModel.errorMessage.observe(viewLifecycleOwner) { response ->
            isBroadCastTx(false)
            requireContext().showToast(view, response, true)
            return@observe
        }
    }

    private fun updateFeeViewWithSimulate(gasUsed: String?) {
        txFee?.let { fee ->
            gasUsed?.toLong()?.let { gas ->
                val gasLimit =
                    (gas.toDouble() * selectedChain.simulatedGasMultiply()).toLong().toBigDecimal()
                if (selectedChain.cosmosFetcher?.cosmosBaseFees?.isNotEmpty() == true) {
                    selectedChain.cosmosFetcher?.cosmosBaseFees?.firstOrNull {
                        it.denom == fee.getAmount(
                            0
                        ).denom
                    }?.let { baseFee ->
                        val feeCoinAmount =
                            baseFee.getdAmount().multiply(gasLimit).setScale(0, RoundingMode.UP)
                        val feeCoin = CoinProto.Coin.newBuilder().setDenom(fee.getAmount(0).denom)
                            .setAmount(feeCoinAmount.toString()).build()
                        txFee = TxProto.Fee.newBuilder().setGasLimit(gasLimit.toLong())
                            .addAmount(feeCoin).build()
                        txFee = Signer.setFee(selectedFeeInfo, txFee)
                    }

                } else {
                    val selectedFeeData =
                        feeInfos[selectedFeeInfo].feeDatas.firstOrNull { it.denom == fee.getAmount(0).denom }
                    val gasRate = selectedFeeData?.gasRate

                    val feeCoinAmount = gasRate?.multiply(gasLimit)?.setScale(0, RoundingMode.UP)
                    val feeCoin = CoinProto.Coin.newBuilder().setDenom(fee.getAmount(0).denom)
                        .setAmount(feeCoinAmount.toString()).build()
                    txFee =
                        TxProto.Fee.newBuilder().setGasLimit(gasLimit.toLong()).addAmount(feeCoin)
                            .build()
                }
            }
        }
        updateFeeView()
        isBroadCastTx(true)
    }

    private fun isBroadCastTx(isSuccess: Boolean) {
        binding.backdropLayout.visibility = View.GONE
        binding.btnStake.updateButtonView(isSuccess)
    }

    private fun setUpBroadcast() {
        txViewModel.broadcast.observe(viewLifecycleOwner) { response ->
            Intent(requireContext(), TxResultActivity::class.java).apply {
                response?.let { txResponse ->
                    if (txResponse.code > 0) {
                        putExtra("isSuccess", false)
                    } else {
                        putExtra("isSuccess", true)
                    }
                    putExtra("errorMsg", txResponse.rawLog)
                    putExtra("selectedChain", selectedChain.tag)
                    val hash = txResponse.txhash
                    if (!TextUtils.isEmpty(hash)) putExtra("txHash", hash)
                    startActivity(this)
                }
            }
            dismiss()
        }
    }

    private fun onBindDelegateMsg(): MutableList<Any> {
        return when (selectedChain) {
            is ChainInitiaTestnet -> {
                val msgDelegate = com.initia.mstaking.v1.TxProto.MsgDelegate.newBuilder()
                    .setDelegatorAddress(selectedChain.address)
                    .setValidatorAddress(initiaToValidator?.operatorAddress).addAmount(toCoin)
                    .build()
                Signer.initiaDelegateMsg(msgDelegate)
            }

            is ChainZenrock -> {
                val msgDelegate = com.zrchain.validation.TxProto.MsgDelegate.newBuilder()
                    .setDelegatorAddress(selectedChain.address)
                    .setValidatorAddress(zenrockToValidator?.operatorAddress).setAmount(toCoin)
                    .build()
                Signer.zenrockDelegateMsg(msgDelegate)
            }

            else -> {
                val msgDelegate =
                    MsgDelegate.newBuilder().setDelegatorAddress(selectedChain.address)
                        .setValidatorAddress(toValidator?.operatorAddress).setAmount(toCoin).build()

                if (selectedChain is ChainBabylon) {
                    val wrappedMsgDelegate = com.babylon.epoching.v1.TxProto.MsgWrappedDelegate.newBuilder().setMsg(msgDelegate).build()
                    Signer.babylonDelegateMsg(wrappedMsgDelegate)
                } else {
                    Signer.delegateMsg(msgDelegate)
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
        txViewModel.broadcastTx.removeObservers(viewLifecycleOwner)
    }
}