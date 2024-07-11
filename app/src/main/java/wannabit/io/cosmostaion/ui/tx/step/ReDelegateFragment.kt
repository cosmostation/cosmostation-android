package wannabit.io.cosmostaion.ui.tx.step

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
import com.cosmos.staking.v1beta1.StakingProto.Validator
import com.cosmos.staking.v1beta1.TxProto.MsgBeginRedelegate
import com.cosmos.tx.v1beta1.TxProto
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.amountHandlerLeft
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.formatString
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.common.getdAmount
import wannabit.io.cosmostaion.common.setMonikerImg
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.data.model.res.FeeInfo
import wannabit.io.cosmostaion.databinding.FragmentRedelegateBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.ui.main.chain.cosmos.TxType
import wannabit.io.cosmostaion.ui.option.tx.general.AmountSelectListener
import wannabit.io.cosmostaion.ui.option.tx.general.AssetFragment
import wannabit.io.cosmostaion.ui.option.tx.general.AssetSelectListener
import wannabit.io.cosmostaion.ui.option.tx.general.BaseFeeAssetFragment
import wannabit.io.cosmostaion.ui.option.tx.general.BaseFeeAssetSelectListener
import wannabit.io.cosmostaion.ui.option.tx.general.InsertAmountFragment
import wannabit.io.cosmostaion.ui.option.tx.general.MemoFragment
import wannabit.io.cosmostaion.ui.option.tx.general.MemoListener
import wannabit.io.cosmostaion.ui.option.tx.validator.ValidatorDefaultFragment
import wannabit.io.cosmostaion.ui.option.tx.validator.ValidatorDefaultListener
import wannabit.io.cosmostaion.ui.option.tx.validator.ValidatorFragment
import wannabit.io.cosmostaion.ui.option.tx.validator.ValidatorListener
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import java.math.BigDecimal
import java.math.RoundingMode

class ReDelegateFragment : BaseTxFragment() {

    private var _binding: FragmentRedelegateBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain
    private var fromValidator: Validator? = null
    private var toValidator: Validator? = null

    private var feeInfos: MutableList<FeeInfo> = mutableListOf()
    private var selectedFeeInfo = 0
    private var txFee: TxProto.Fee? = null
    private var txTip: TxProto.Tip? = null

    private var toCoin: CoinProto.Coin? = null
    private var txMemo = ""

    private var availableAmount = BigDecimal.ZERO

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain, fromValidator: Validator?): ReDelegateFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putSerializable("fromValidator", fromValidator)
            }
            val fragment = ReDelegateFragment()
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
            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)

            if (fromValidator != null) {
                selectedChain.grpcFetcher?.cosmosValidators?.firstOrNull {
                    it.operatorAddress == selectedChain.grpcFetcher?.cosmosDelegations?.get(
                        0
                    )?.delegation?.validatorAddress
                }
            }

            val cosmostation =
                selectedChain.grpcFetcher?.cosmosValidators?.firstOrNull { it.description.moniker == "Cosmostation" }
            toValidator = if (fromValidator?.operatorAddress == cosmostation?.operatorAddress) {
                selectedChain.grpcFetcher?.cosmosValidators?.firstOrNull { it.operatorAddress != cosmostation?.operatorAddress }
            } else {
                selectedChain.grpcFetcher?.cosmosValidators?.firstOrNull { it.operatorAddress != fromValidator?.operatorAddress }
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

            if (selectedChain.grpcFetcher?.cosmosBaseFees?.isNotEmpty() == true) {
                val tipTitle = listOf(
                    "No Tip", "20% Tip", "50% Tip", "100% Tip"
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
                val baseFee = selectedChain.grpcFetcher?.cosmosBaseFees?.get(0)
                val gasAmount = selectedChain.getFeeBaseGasAmount().toBigDecimal()
                val feeDenom = baseFee?.denom
                val feeAmount =
                    baseFee?.getdAmount()?.multiply(gasAmount)?.setScale(0, RoundingMode.DOWN)
                txFee = TxProto.Fee.newBuilder().setGasLimit(gasAmount.toLong()).addAmount(
                    CoinProto.Coin.newBuilder().setDenom(feeDenom).setAmount(feeAmount.toString())
                        .build()
                ).build()
                txTip = TxProto.Tip.newBuilder().setTipper(selectedChain.address).addAmount(
                    CoinProto.Coin.newBuilder().setDenom(feeDenom).setAmount("0").build()
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

    private fun updateFromValidatorView() {
        binding.apply {
            fromValidator?.let { fromValidator ->
                fromMonikerImg.setMonikerImg(selectedChain, fromValidator.operatorAddress)
                fromMonikerName.text = fromValidator.description?.moniker
                fromJailedImg.visibleOrGone(fromValidator.jailed)
            }
            BaseData.getAsset(selectedChain.apiName, selectedChain.stakeDenom)?.let { asset ->
                asset.decimals?.let { decimal ->
                    val staked =
                        selectedChain.grpcFetcher?.cosmosDelegations?.firstOrNull { it.delegation.validatorAddress == fromValidator?.operatorAddress }?.balance?.amount
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
                toMonikerName.text = toValidator.description?.moniker
                toJailedImg.visibleOrGone(toValidator.jailed)

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

                    var totalFeeAmount = fee.amount.toBigDecimal()
                    if (txTip?.amountList?.isNotEmpty() == true) {
                        totalFeeAmount =
                            totalFeeAmount.add(txTip?.getAmount(0)?.amount?.toBigDecimal())
                    }
                    val amount = totalFeeAmount.amountHandlerLeft(asset.decimals ?: 6)
                    val price = BaseData.getPrice(asset.coinGeckoId)
                    val value = price.multiply(amount)

                    feeAmount.text = formatAmount(amount.toPlainString(), asset.decimals ?: 6)
                    feeValue.text = formatAssetValue(value)
                }

                selectedChain.grpcFetcher?.cosmosDelegations?.firstOrNull { it.delegation.validatorAddress == fromValidator?.operatorAddress }
                    ?.let {
                        availableAmount = it.balance.amount.toBigDecimal()
                    }
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
                                    selectedChain.grpcFetcher?.cosmosValidators?.firstOrNull { it.operatorAddress == validatorAddress }
                                updateFeeView()
                                updateFromValidatorView()
                            }

                            if (fromValidator?.operatorAddress == toValidator?.operatorAddress) {
                                toValidator =
                                    selectedChain.grpcFetcher?.cosmosValidators?.firstOrNull { it.operatorAddress != toValidator?.operatorAddress }
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
                        object : ValidatorDefaultListener {
                            override fun select(validatorAddress: String) {
                                toValidator =
                                    selectedChain.grpcFetcher?.cosmosValidators?.firstOrNull { it.operatorAddress == validatorAddress }
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
                    MemoFragment.newInstance(txMemo, object : MemoListener {
                        override fun memo(memo: String) {
                            updateMemoView(memo)
                        }

                    })
                )
            }

            feeTokenLayout.setOnClickListener {
                if (selectedChain.grpcFetcher?.cosmosBaseFees?.isNotEmpty() == true) {
                    handleOneClickWithDelay(
                        BaseFeeAssetFragment.newInstance(selectedChain,
                            selectedChain.grpcFetcher?.cosmosBaseFees,
                            object : BaseFeeAssetSelectListener {
                                override fun select(denom: String) {
                                    txFee?.let { fee ->
                                        txFee = TxProto.Fee.newBuilder().setGasLimit(fee.gasLimit)
                                            .addAmount(
                                                CoinProto.Coin.newBuilder()
                                                    .setDenom(fee.getAmount(0).denom)
                                                    .setAmount(fee.getAmount(0).amount)
                                            ).build()
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
                                    selectedChain.getDefaultFeeCoins(requireContext())
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
            }

            feeSegment.setOnPositionChangedListener { position ->
                selectedFeeInfo = position
                if (selectedChain.grpcFetcher?.cosmosBaseFees?.isNotEmpty() == true) {
                    txTip = Signer.setTip(selectedFeeInfo, txFee, txTip)

                } else {
                    txFee = selectedChain.getBaseFee(
                        requireContext(), selectedFeeInfo, txFee?.getAmount(0)?.denom
                    )
                }
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

    private val relegateResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
                txViewModel.broadReDelegate(
                    getChannel(selectedChain),
                    selectedChain.address,
                    onBindReDelegate(),
                    txFee,
                    txTip,
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
            if (!selectedChain.isGasSimulable()) {
                return updateFeeViewWithSimulate(null)
            }
            btnRedelegate.updateButtonView(false)
            backdropLayout.visibility = View.VISIBLE
            txViewModel.simulateReDelegate(
                getChannel(selectedChain),
                selectedChain.address,
                onBindReDelegate(),
                txFee,
                txTip,
                txMemo,
                selectedChain
            )
        }
    }

    private fun setUpSimulate() {
        txViewModel.simulate.observe(viewLifecycleOwner) { gasInfo ->
            updateFeeViewWithSimulate(gasInfo)
        }

        txViewModel.errorMessage.observe(viewLifecycleOwner) { response ->
            isBroadCastTx(false)
            requireContext().showToast(view, response, true)
            return@observe
        }
    }

    private fun updateFeeViewWithSimulate(gasInfo: AbciProto.GasInfo?) {
        txFee?.let { fee ->
            gasInfo?.let { info ->
                val gasLimit =
                    (info.gasUsed.toDouble() * selectedChain.gasMultiply()).toLong().toBigDecimal()
                if (selectedChain.grpcFetcher?.cosmosBaseFees?.isNotEmpty() == true) {
                    selectedChain.grpcFetcher?.cosmosBaseFees?.firstOrNull {
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
                        txTip = Signer.setTip(selectedFeeInfo, txFee, txTip)
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
        binding.btnRedelegate.updateButtonView(isSuccess)
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
                putExtra("selectedChain", selectedChain.tag)
                val hash = txResponse.txhash
                if (!TextUtils.isEmpty(hash)) putExtra("txHash", hash)
                startActivity(this)
            }
            dismiss()
        }
    }

    private fun onBindReDelegate(): MsgBeginRedelegate {
        return MsgBeginRedelegate.newBuilder().setDelegatorAddress(selectedChain.address)
            .setValidatorSrcAddress(fromValidator?.operatorAddress)
            .setValidatorDstAddress(toValidator?.operatorAddress).setAmount(toCoin).build();
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}