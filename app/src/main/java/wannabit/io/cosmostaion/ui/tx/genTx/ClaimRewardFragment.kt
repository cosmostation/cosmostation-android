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
import com.cosmos.distribution.v1beta1.DistributionProto.DelegationDelegatorReward
import com.cosmos.tx.v1beta1.TxProto
import com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.google.protobuf.Any
import com.google.protobuf.ByteString
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBabylon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.ChainZenrock
import wannabit.io.cosmostaion.chain.testnetClass.ChainInitiaTestnet
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.amountHandlerLeft
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.getdAmount
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.req.ClaimReq
import wannabit.io.cosmostaion.data.model.req.ClaimRewards
import wannabit.io.cosmostaion.data.model.res.FeeInfo
import wannabit.io.cosmostaion.databinding.FragmentClaimRewardBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.sign.Signer
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.tx.option.general.AssetFragment
import wannabit.io.cosmostaion.ui.tx.option.general.AssetSelectListener
import wannabit.io.cosmostaion.ui.tx.option.general.BaseFeeAssetFragment
import wannabit.io.cosmostaion.ui.tx.option.general.BaseFeeAssetSelectListener
import wannabit.io.cosmostaion.ui.tx.option.general.MemoFragment
import wannabit.io.cosmostaion.ui.tx.option.general.MemoListener
import java.math.BigDecimal
import java.math.RoundingMode

class ClaimRewardFragment : BaseTxFragment() {

    private var _binding: FragmentClaimRewardBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain
    private lateinit var claimableRewards: MutableList<DelegationDelegatorReward?>
    private var isClaim: Boolean = false

    private var feeInfos: MutableList<FeeInfo> = mutableListOf()
    private var selectedFeeInfo = 0
    private var txFee: TxProto.Fee? = null
    private var txMemo = ""

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(
            selectedChain: BaseChain,
            claimableRewards: MutableList<DelegationDelegatorReward?>?,
            isClaim: Boolean
        ): ClaimRewardFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putSerializable("claimableRewards", claimableRewards?.toHashSet())
                putBoolean("isClaim", isClaim)
            }
            val fragment = ClaimRewardFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClaimRewardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initFee()
        txSimulate()
        setUpClickAction()
        setUpSimulate()
        setUpBroadcast()
    }

    private fun initView() {
        binding.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("selectedChain", BaseChain::class.java)
                    ?.let { selectedChain = it }
            } else {
                (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                    selectedChain = it
                }
            }
            if (selectedChain !is ChainNeutron) {
                val serializableList = arguments?.getSerializable("claimableRewards") as? HashSet<*>
                claimableRewards =
                    serializableList?.toList() as MutableList<DelegationDelegatorReward?>
            }
            isClaim = arguments?.getBoolean("isClaim") ?: false

            listOf(rewardView, memoView, feeView, babylonRewardView).forEach {
                it.setBackgroundResource(
                    R.drawable.cell_bg
                )
            }
            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)

            if (isClaim) {
                (selectedChain as ChainBabylon).apply {
                    rewardView.visibility = View.GONE
                    babylonRewardView.visibility = View.VISIBLE

                    BaseData.getAsset(apiName, stakeDenom)?.let { asset ->
                        var rewardAmount = BigDecimal.ZERO
                        claimableRewards.forEach { reward ->
                            val rawAmount = BigDecimal(
                                reward?.rewardList?.firstOrNull { it.denom == stakeDenom }?.amount
                                    ?: "0"
                            )
                            rewardAmount = rewardAmount.add(
                                rawAmount.movePointLeft(18).movePointLeft(asset.decimals ?: 6)
                                    .setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                            )
                        }
                        babylonRewardsAmount.text =
                            formatAmount(rewardAmount.toPlainString(), asset.decimals ?: 6)
                        babylonRewardsDenom.text = asset.symbol

                        val anotherRewardDenoms = mutableListOf<String>()
                        claimableRewards.forEach { reward ->
                            reward?.rewardList?.filter { it.denom != stakeDenom }
                                ?.forEach { anotherRewards ->
                                    val anotherAmount =
                                        anotherRewards.amount.toBigDecimal().movePointLeft(18)
                                            .setScale(0, RoundingMode.DOWN)
                                    if (anotherAmount != BigDecimal.ZERO) {
                                        if (!anotherRewardDenoms.contains(anotherRewards.denom)) {
                                            anotherRewardDenoms.add(anotherRewards.denom)
                                        }
                                    }
                                }
                        }
                        if (anotherRewardDenoms.size > 0) {
                            babylonRewardsCnt.text = "+ " + anotherRewardDenoms.size
                            titleClaimRewardImg.visibility = View.GONE
                            titleClaimReward.text = "Claim Rewards"
                        } else {
                            babylonRewardsCnt.visibility = View.GONE
                            titleClaimRewardImg.visibility = View.VISIBLE
                            titleClaimRewardImg.setTokenImg(asset)
                            titleClaimReward.text =
                                getString(R.string.title_rewards_claim, asset.symbol)
                        }

                        var btcRewardAmount = BigDecimal.ZERO
                        babylonFetcher?.btcRewards?.forEach { reward ->
                            if (reward.denom == stakeDenom) {
                                val rawAmount =
                                    reward.amount.toBigDecimal().movePointLeft(asset.decimals ?: 6)
                                        .setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                                btcRewardAmount = btcRewardAmount.add(rawAmount)
                            }
                        }
                        btcRewardsAmount.text =
                            formatAmount(btcRewardAmount.toPlainString(), asset.decimals ?: 6)
                        btcRewardsDenom.text = asset.symbol

                        if (((selectedChain as ChainBabylon).babylonFetcher?.btcRewards?.size
                                ?: 0) > 1
                        ) {
                            btcRewardsCnt.text =
                                "+ " + ((selectedChain as ChainBabylon).babylonFetcher?.btcRewards?.size
                                    ?: (0 - 1))
                        } else {
                            btcRewardsCnt.visibility = View.GONE
                        }

                        val totalRewards = rewardAmount.add(btcRewardAmount)
                        babylonTotalReward.text =
                            formatAmount(totalRewards.toPlainString(), asset.decimals ?: 6)
                        babylonTotalRewardDenom.text = asset.symbol
                    }
                }

            } else {
                rewardView.visibility = View.VISIBLE
                babylonRewardView.visibility = View.GONE

                if (selectedChain is ChainNeutron) {
                    val delegations =
                        selectedChain.cosmosFetcher?.cosmosDelegations ?: mutableListOf()
                    val validators =
                        selectedChain.cosmosFetcher?.cosmosValidators ?: mutableListOf()
                    val cosmostationValAddress =
                        validators.firstOrNull { it.description.moniker == "Cosmostation" }?.operatorAddress

                    if (delegations.any { it.delegation.validatorAddress == cosmostationValAddress }) {
                        validatorName.text = "Cosmostation"
                    } else {
                        val maxAmount =
                            delegations.maxOfOrNull { delegation -> delegation.balance.amount.toBigDecimal() }
                        val maxValidator =
                            delegations.firstOrNull { it.balance.amount.toBigDecimal() == maxAmount }
                        validatorName.text =
                            validators.firstOrNull { it.operatorAddress == maxValidator?.delegation?.validatorAddress }?.description?.moniker?.trim()
                    }

                    if (delegations.size > 1) {
                        validatorCnt.text = "+ " + (delegations.size - 1)
                    } else {
                        validatorCnt.visibility = View.GONE
                    }

                    BaseData.getAsset(selectedChain.apiName, selectedChain.stakeDenom)
                        ?.let { asset ->
                            val rewardAmount =
                                (selectedChain as ChainNeutron).neutronFetcher()?.neutronRewards?.movePointLeft(
                                    asset.decimals ?: 6
                                )?.setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                                    ?: BigDecimal.ZERO

                            rewardsAmount.text =
                                formatAmount(rewardAmount.toPlainString(), asset.decimals ?: 6)
                            rewardsDenom.text = asset.symbol
                            rewardsDenom.setTextColor(asset.assetColor())

                            rewardCnt.visibility = View.GONE
                            titleClaimRewardImg.visibility = View.VISIBLE
                            titleClaimRewardImg.setTokenImg(asset)
                            titleClaimReward.text =
                                getString(R.string.title_rewards_claim, asset.symbol)
                        }

                } else {
                    val cosmostationValAddress = when (selectedChain) {
                        is ChainInitiaTestnet -> {
                            (selectedChain as ChainInitiaTestnet).initiaFetcher()?.initiaValidators?.firstOrNull { it.description.moniker == "Cosmostation" }?.operatorAddress
                        }

                        is ChainZenrock -> {
                            (selectedChain as ChainZenrock).zenrockFetcher()?.zenrockValidators?.firstOrNull { it.description.moniker == "Cosmostation" }?.operatorAddress
                        }

                        else -> {
                            selectedChain.cosmosFetcher?.cosmosValidators?.firstOrNull { it.description.moniker == "Cosmostation" }?.operatorAddress
                        }
                    }

                    if (claimableRewards.any { it?.validatorAddress == cosmostationValAddress }) {
                        validatorName.text = "Cosmostation"
                    } else {
                        validatorName.text = when (selectedChain) {
                            is ChainInitiaTestnet -> {
                                (selectedChain as ChainInitiaTestnet).initiaFetcher()?.initiaValidators?.firstOrNull { it.operatorAddress == claimableRewards[0]?.validatorAddress }?.description?.moniker?.trim()
                            }

                            is ChainZenrock -> {
                                (selectedChain as ChainZenrock).zenrockFetcher()?.zenrockValidators?.firstOrNull { it.operatorAddress == claimableRewards[0]?.validatorAddress }?.description?.moniker?.trim()
                            }

                            else -> {
                                selectedChain.cosmosFetcher?.cosmosValidators?.firstOrNull { it.operatorAddress == claimableRewards[0]?.validatorAddress }?.description?.moniker?.trim()
                            }
                        }
                    }

                    if (claimableRewards.size > 1) {
                        validatorCnt.text = "+ " + (claimableRewards.size - 1)
                    } else {
                        validatorCnt.visibility = View.GONE
                    }

                    BaseData.getAsset(selectedChain.apiName, selectedChain.stakeDenom)
                        ?.let { asset ->
                            var rewardAmount = BigDecimal.ZERO

                            claimableRewards.forEach { reward ->
                                val rawAmount = BigDecimal(
                                    reward?.rewardList?.firstOrNull { it.denom == selectedChain.stakeDenom }?.amount
                                        ?: "0"
                                )
                                rewardAmount = rewardAmount.add(
                                    rawAmount.movePointLeft(18).movePointLeft(asset.decimals ?: 6)
                                        .setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                                )
                            }

                            rewardsAmount.text =
                                formatAmount(rewardAmount.toPlainString(), asset.decimals ?: 6)
                            rewardsDenom.text = asset.symbol
                            rewardsDenom.setTextColor(asset.assetColor())

                            val anotherRewardDenoms = mutableListOf<String>()
                            claimableRewards.forEach { reward ->
                                reward?.rewardList?.filter { it.denom != selectedChain.stakeDenom }
                                    ?.forEach { anotherRewards ->
                                        val anotherAmount =
                                            anotherRewards.amount.toBigDecimal().movePointLeft(18)
                                                .setScale(0, RoundingMode.DOWN)
                                        if (anotherAmount != BigDecimal.ZERO) {
                                            if (!anotherRewardDenoms.contains(anotherRewards.denom)) {
                                                anotherRewardDenoms.add(anotherRewards.denom)
                                            }
                                        }
                                    }
                            }
                            if (anotherRewardDenoms.size > 0) {
                                rewardCnt.text = "+ " + anotherRewardDenoms.size
                                titleClaimRewardImg.visibility = View.GONE
                                titleClaimReward.text = "Claim Rewards"

                            } else {
                                rewardCnt.visibility = View.GONE
                                titleClaimRewardImg.visibility = View.VISIBLE
                                titleClaimRewardImg.setTokenImg(asset)
                                titleClaimReward.text =
                                    getString(R.string.title_rewards_claim, asset.symbol)
                            }
                        }
                }
            }
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
            }
        }
    }

    @SuppressLint("WrongConstant")
    private fun setUpClickAction() {
        binding.apply {
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
                    if (feeInfos.isEmpty()) {
                        activity?.makeToast(R.string.str_unknown_error)
                        return@setOnClickListener
                    }

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

            btnGetReward.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    claimRewardResultLauncher.launch(this)
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

    private val claimRewardResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE

                val claimRewardsMsg = if (selectedChain is ChainNeutron) {
                    onBindWasmClaimRewardsMsg()
                } else {
                    Signer.claimStakingRewardMsg(selectedChain, claimableRewards, isClaim)
                }
                txViewModel.broadcast(
                    selectedChain.cosmosFetcher?.getChannel(),
                    claimRewardsMsg,
                    txFee,
                    txMemo,
                    selectedChain
                )
            }
        }

    private fun txSimulate() {
        binding.apply {
            if (!selectedChain.isSimulable()) {
                return updateFeeViewWithSimulate(null)
            }
            btnGetReward.updateButtonView(false)
            backdropLayout.visibility = View.VISIBLE

            val claimRewardsMsg = if (selectedChain is ChainNeutron) {
                onBindWasmClaimRewardsMsg()
            } else {
                Signer.claimStakingRewardMsg(selectedChain, claimableRewards, isClaim)
            }
            txViewModel.simulate(
                selectedChain.cosmosFetcher?.getChannel(),
                claimRewardsMsg,
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
        binding.btnGetReward.updateButtonView(isSuccess)
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

    private fun onBindWasmClaimRewardsMsg(): MutableList<Any> {
        val contractAddress =
            selectedChain.getChainListParam()?.getAsJsonObject("reward")?.get("address")?.asString
                ?: ""
        val wasmMsgs = mutableListOf<MsgExecuteContract?>()
        val jsonData = Gson().toJson(ClaimReq(ClaimRewards()))
        val msg = ByteString.copyFromUtf8(jsonData)
        wasmMsgs.add(
            MsgExecuteContract.newBuilder().setSender(selectedChain.address)
                .setContract(contractAddress).setMsg(msg).build()
        )
        return Signer.wasmMsg(wasmMsgs)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}