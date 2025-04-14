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
import com.cosmos.base.v1beta1.CoinProto.Coin
import com.cosmos.staking.v1beta1.StakingProto.Validator
import com.cosmos.staking.v1beta1.TxProto.MsgUndelegate
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
import wannabit.io.cosmostaion.common.getdAmount
import wannabit.io.cosmostaion.common.isActiveValidator
import wannabit.io.cosmostaion.common.setMonikerImg
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.res.FeeInfo
import wannabit.io.cosmostaion.databinding.FragmentUnStakingBinding
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
import wannabit.io.cosmostaion.ui.tx.option.validator.ValidatorFragment
import wannabit.io.cosmostaion.ui.tx.option.validator.ValidatorListener
import java.math.BigDecimal
import java.math.RoundingMode

class UnStakingFragment : BaseTxFragment() {

    private var _binding: FragmentUnStakingBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain
    private var validator: Validator? = null
    private var initiaValidator: com.initia.mstaking.v1.StakingProto.Validator? = null
    private var zenrockValidator: com.zrchain.validation.HybridValidationProto.ValidatorHV? = null

    private var feeInfos: MutableList<FeeInfo> = mutableListOf()
    private var selectedFeeInfo = 0
    private var txFee: TxProto.Fee? = null

    private var toCoin: Coin? = null
    private var txMemo = ""

    private var availableAmount = BigDecimal.ZERO

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(
            selectedChain: BaseChain,
            validator: Validator? = null,
            initiaValidator: com.initia.mstaking.v1.StakingProto.Validator? = null,
            zenrockValidator: com.zrchain.validation.HybridValidationProto.ValidatorHV? = null
        ): UnStakingFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putSerializable("validator", validator)
                putSerializable("initiaValidator", initiaValidator)
                putSerializable("zenrockValidator", zenrockValidator)
            }
            val fragment = UnStakingFragment()
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
                validator = arguments?.getSerializable("validator", Validator::class.java)
                initiaValidator = arguments?.getSerializable(
                    "initiaValidator", com.initia.mstaking.v1.StakingProto.Validator::class.java
                )
                zenrockValidator = arguments?.getSerializable(
                    "zenrockValidator",
                    com.zrchain.validation.HybridValidationProto.ValidatorHV::class.java
                )

            } else {
                (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                    selectedChain = it
                }
                validator = arguments?.getSerializable("validator") as? Validator?
                initiaValidator =
                    arguments?.getSerializable("initiaValidator") as? com.initia.mstaking.v1.StakingProto.Validator?
                zenrockValidator =
                    arguments?.getSerializable("zenrockValidator") as? com.zrchain.validation.HybridValidationProto.ValidatorHV?
            }

            BaseData.getAsset(selectedChain.apiName, selectedChain.stakeDenom)?.let { asset ->
                titleUnstakeImg.setTokenImg(asset)
                titleUnstake.text = getString(R.string.title_unstaking, asset.symbol)
            }

            listOf(validatorView, amountView, memoView, feeView).forEach {
                it.setBackgroundResource(
                    R.drawable.cell_bg
                )
            }
            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)

            when (selectedChain) {
                is ChainInitiaTestnet -> {
                    if (initiaValidator != null) {
                        (selectedChain as ChainInitiaTestnet).initiaFetcher()?.initiaValidators?.firstOrNull {
                            it.operatorAddress == (selectedChain as ChainInitiaTestnet).initiaFetcher()?.initiaDelegations?.get(
                                0
                            )?.delegation?.validatorAddress
                        }
                    }
                }

                is ChainZenrock -> {
                    if (zenrockValidator != null) {
                        (selectedChain as ChainZenrock).zenrockFetcher()?.zenrockValidators?.firstOrNull {
                            it.operatorAddress == (selectedChain as ChainZenrock).zenrockFetcher()?.zenrockDelegations?.get(
                                0
                            )?.delegation?.validatorAddress
                        }
                    }
                }

                else -> {
                    if (validator != null) {
                        selectedChain.cosmosFetcher?.cosmosValidators?.firstOrNull {
                            it.operatorAddress == selectedChain.cosmosFetcher?.cosmosDelegations?.get(
                                0
                            )?.delegation?.validatorAddress
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
                    Coin.newBuilder().setDenom(feeDenom).setAmount(feeAmount.toString()).build()
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
            BaseData.getAsset(selectedChain.apiName, selectedChain.stakeDenom)?.let { asset ->
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

                    val staked =
                        selectedChain.cosmosFetcher?.cosmosDelegations?.firstOrNull { it.delegation.validatorAddress == validator.operatorAddress }?.balance?.amount
                    staked?.toBigDecimal()?.movePointLeft(asset.decimals ?: 6)?.let {
                        stakedAmount.text = formatAmount(it.toPlainString(), asset.decimals ?: 6)
                    }
                }

                initiaValidator?.let { validator ->
                    monikerImg.setMonikerImg(selectedChain, validator.operatorAddress)
                    monikerName.text = validator.description?.moniker?.trim()

                    val statusImage = when {
                        validator.jailed -> R.drawable.icon_jailed
                        !validator.isActiveValidator(selectedChain as ChainInitiaTestnet) -> R.drawable.icon_inactive
                        else -> 0
                    }
                    jailedImg.visibility = if (statusImage != 0) View.VISIBLE else View.GONE
                    jailedImg.setImageResource(statusImage)

                    val staked =
                        (selectedChain as ChainInitiaTestnet).initiaFetcher()?.initiaDelegations?.firstOrNull { it.delegation.validatorAddress == validator.operatorAddress }?.balanceList?.firstOrNull { it.denom == selectedChain.stakeDenom }?.amount
                    staked?.toBigDecimal()?.movePointLeft(asset.decimals ?: 6)?.let {
                        stakedAmount.text = formatAmount(it.toPlainString(), asset.decimals ?: 6)
                    }
                }

                zenrockValidator?.let { validator ->
                    monikerImg.setMonikerImg(selectedChain, validator.operatorAddress)
                    monikerName.text = validator.description?.moniker?.trim()

                    val statusImage = when {
                        validator.jailed -> R.drawable.icon_jailed
                        !validator.isActiveValidator(selectedChain as ChainZenrock) -> R.drawable.icon_inactive
                        else -> 0
                    }
                    jailedImg.visibility = if (statusImage != 0) View.VISIBLE else View.GONE
                    jailedImg.setImageResource(statusImage)

                    val staked =
                        (selectedChain as ChainZenrock).zenrockFetcher()?.zenrockDelegations?.firstOrNull { it.delegation.validatorAddress == validator.operatorAddress }?.balance?.amount
                    staked?.toBigDecimal()?.movePointLeft(asset.decimals ?: 6)?.let {
                        stakedAmount.text = formatAmount(it.toPlainString(), asset.decimals ?: 6)
                    }
                }
            }
        }
        txSimulate()
    }

    private fun updateAmountView(toAmount: String) {
        binding.apply {
            toCoin =
                Coin.newBuilder().setAmount(toAmount).setDenom(selectedChain.stakeDenom).build()

            BaseData.getAsset(selectedChain.apiName, selectedChain.stakeDenom)?.let { asset ->
                val price = BaseData.getPrice(asset.coinGeckoId)
                val dpAmount = BigDecimal(toAmount).movePointLeft(asset.decimals ?: 6)
                    .setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                val value = price.multiply(dpAmount)

                undelegateAmountMsg.visibility = View.GONE
                undelegateAmount.text = formatAmount(dpAmount.toPlainString(), asset.decimals ?: 6)
                undelegateAmount.setTextColor(
                    ContextCompat.getColor(
                        requireContext(), R.color.color_base01
                    )
                )
                undelegateDenom.visibility = View.VISIBLE
                undelegateDenom.text = asset.symbol
                undelegateDenom.setTextColor(asset.assetColor())
                undelegateValue.text = formatAssetValue(value)
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

                availableAmount = when (selectedChain) {
                    is ChainInitiaTestnet -> {
                        (selectedChain as ChainInitiaTestnet).initiaFetcher()?.initiaDelegations?.firstOrNull { it.delegation.validatorAddress == initiaValidator?.operatorAddress }
                            ?.let {
                                it.balanceList.firstOrNull { balance -> balance.denom == selectedChain.stakeDenom }?.amount?.toBigDecimal()
                            }
                    }

                    is ChainZenrock -> {
                        (selectedChain as ChainZenrock).zenrockFetcher()?.zenrockDelegations?.firstOrNull { it.delegation.validatorAddress == zenrockValidator?.operatorAddress }?.balance?.amount?.toBigDecimal()
                    }

                    else -> {
                        selectedChain.cosmosFetcher?.cosmosDelegations?.firstOrNull { it.delegation.validatorAddress == validator?.operatorAddress }?.balance?.amount?.toBigDecimal()
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
                    ValidatorFragment(selectedChain, object : ValidatorListener {
                        override fun select(validatorAddress: String) {
                            when (selectedChain) {
                                is ChainInitiaTestnet -> {
                                    if (initiaValidator?.operatorAddress != validatorAddress) {
                                        initiaValidator =
                                            (selectedChain as ChainInitiaTestnet).initiaFetcher()?.initiaValidators?.firstOrNull { it.operatorAddress == validatorAddress }
                                    }
                                }

                                is ChainZenrock -> {
                                    if (zenrockValidator?.operatorAddress != validatorAddress) {
                                        zenrockValidator =
                                            (selectedChain as ChainZenrock).zenrockFetcher()?.zenrockValidators?.firstOrNull { it.operatorAddress == validatorAddress }
                                    }
                                }

                                else -> {
                                    if (validator?.operatorAddress != validatorAddress) {
                                        validator =
                                            selectedChain.cosmosFetcher?.cosmosValidators?.firstOrNull { it.operatorAddress == validatorAddress }
                                    }
                                }
                            }
                            updateFeeView()
                            updateValidatorView()
                        }
                    })
                )
            }

            amountView.setOnClickListener {
                handleOneClickWithDelay(
                    InsertAmountFragment.newInstance(selectedChain, TxType.UN_DELEGATE,
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
                                                    Coin.newBuilder().setDenom(denom)
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
                                                    Coin.newBuilder().setDenom(denom).setAmount(
                                                        feeCoin.gasRate?.multiply(
                                                            gasAmount
                                                        )?.setScale(0, RoundingMode.UP).toString()
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
                        Coin.newBuilder().setDenom(feeDenom).setAmount(feeAmount.toString()).build()
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

            btnUnstake.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    unDelegateResultLauncher.launch(this)
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

    private val unDelegateResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
                txViewModel.broadcast(
                    selectedChain.cosmosFetcher?.getChannel(),
                    onBindUnDelegateMsg(),
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
            backdropLayout.visibility = View.VISIBLE
            btnUnstake.updateButtonView(false)
            txViewModel.simulate(
                selectedChain.cosmosFetcher?.getChannel(),
                onBindUnDelegateMsg(),
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
                        val feeCoin = Coin.newBuilder().setDenom(fee.getAmount(0).denom)
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
                    val feeCoin = Coin.newBuilder().setDenom(fee.getAmount(0).denom)
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
        binding.btnUnstake.updateButtonView(isSuccess)
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

    private fun onBindUnDelegateMsg(): MutableList<Any> {
        return when (selectedChain) {
            is ChainInitiaTestnet -> {
                val msgUnDelegate = com.initia.mstaking.v1.TxProto.MsgUndelegate.newBuilder()
                    .setDelegatorAddress(selectedChain.address)
                    .setValidatorAddress(initiaValidator?.operatorAddress).addAmount(toCoin).build()
                Signer.initiaUnDelegateMsg(msgUnDelegate)
            }

            is ChainZenrock -> {
                val msgUnDelegate = com.zrchain.validation.TxProto.MsgUndelegate.newBuilder()
                    .setDelegatorAddress(selectedChain.address)
                    .setValidatorAddress(zenrockValidator?.operatorAddress).setAmount(toCoin).build()
                Signer.zenrockUnDelegateMsg(msgUnDelegate)
            }

            else -> {
                val msgUnDelegate =
                    MsgUndelegate.newBuilder().setDelegatorAddress(selectedChain.address)
                        .setValidatorAddress(validator?.operatorAddress).setAmount(toCoin).build()

                if (selectedChain is ChainBabylon) {
                    val wrappedMsgUnDelegate = com.babylon.epoching.v1.TxProto.MsgWrappedUndelegate.newBuilder().setMsg(msgUnDelegate).build()
                    Signer.babylonUnDelegateMsg(wrappedMsgUnDelegate)
                } else {
                    Signer.unDelegateMsg(msgUnDelegate)
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}