package wannabit.io.cosmostaion.ui.tx.step

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.cosmos.base.abci.v1beta1.AbciProto
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.distribution.v1beta1.DistributionProto.DelegationDelegatorReward
import com.cosmos.tx.v1beta1.TxProto
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.formatString
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.res.FeeInfo
import wannabit.io.cosmostaion.databinding.FragmentClaimRewardBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.ui.dialog.tx.AssetFragment
import wannabit.io.cosmostaion.ui.dialog.tx.AssetSelectListener
import wannabit.io.cosmostaion.ui.dialog.tx.ChainFragment
import wannabit.io.cosmostaion.ui.dialog.tx.MemoFragment
import wannabit.io.cosmostaion.ui.dialog.tx.MemoListener
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.viewmodel.tx.TxViewModel
import java.math.BigDecimal
import java.math.RoundingMode

class ClaimRewardFragment(
    val selectedChain: CosmosLine,
    private val claimableRewards: MutableList<DelegationDelegatorReward?>
) : BottomSheetDialogFragment() {

    private var _binding: FragmentClaimRewardBinding? = null
    private val binding get() = _binding!!

    private var feeInfos: MutableList<FeeInfo> = mutableListOf()
    private var selectedFeeInfo = 0
    private var txFee: TxProto.Fee? = null
    private var txMemo = ""

    private val txViewModel: TxViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClaimRewardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            setupRatio(bottomSheetDialog)
        }
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initFee()
        updateFeeView()
        txSimul()
        clickAction()
        setUpSimulate()
        setUpBroadcast()
    }

    private fun initView() {
        binding.apply {
            rewardView.setBackgroundResource(R.drawable.cell_bg)
            memoView.setBackgroundResource(R.drawable.cell_bg)
            feeView.setBackgroundResource(R.drawable.cell_bg)

            val cosmostationValAddress = selectedChain.cosmosValidators.firstOrNull { it.description.moniker == "Cosmostation" }?.operatorAddress
            if (claimableRewards.any { it?.validatorAddress == cosmostationValAddress }) {
                validatorName.text = "Cosmostation"
            } else {
                validatorName.text = selectedChain.cosmosValidators.firstOrNull { it.operatorAddress == claimableRewards[0]?.validatorAddress }?.description?.moniker
            }
            if (claimableRewards.size > 1) {
                validatorCnt.text = "+ " + (claimableRewards.size - 1)
            } else {
                validatorCnt.visibility = View.GONE
            }

            selectedChain.stakeDenom?.let { denom ->
                BaseData.getAsset(selectedChain.apiName, denom)?.let { asset ->
                    asset.decimals?.let { decimal ->
                        var rewardAmount = BigDecimal.ZERO
                        claimableRewards.forEach { reward ->
                            val rawAmount = BigDecimal(reward?.rewardList?.firstOrNull { it.denom == denom }?.amount ?: "0")
                            rewardAmount = rewardAmount.add(rawAmount.movePointLeft(18).movePointLeft(decimal).setScale(decimal, RoundingMode.DOWN))
                        }
                        rewardsAmount.text = formatString(rewardAmount.toPlainString(), decimal)
                        rewardsDenom.text = asset.symbol

                        val anotherRewardDenoms = mutableListOf<String>()
                        claimableRewards.forEach { reward ->
                            reward?.rewardList?.filter { it.denom != denom }?.forEach { anotherRewards ->
                                val anotherAmount = anotherRewards.amount.toBigDecimal().movePointLeft(18).setScale(0, RoundingMode.DOWN)
                                if (anotherAmount != BigDecimal.ZERO) {
                                    if (!anotherRewardDenoms.contains(anotherRewards.denom)) {
                                        anotherRewardDenoms.add(anotherRewards.denom)
                                    }
                                }
                            }
                        }
                        if (anotherRewardDenoms.size > 0) {
                            rewardCnt.text = "+ " + anotherRewardDenoms.size
                        } else {
                            rewardCnt.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun initFee() {
        binding.apply {
            feeInfos = selectedChain.getFeeInfos(requireContext())
            feeSegment.setSelectedBackground(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_accent_purple
                )
            )
            feeSegment.setRipple(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_accent_purple
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
            selectedFeeInfo = selectedChain.getFeeBasePosition()
            txFee = selectedChain.getInitFee(requireContext())
        }
    }

    private fun updateMemoView(memo: String) {
        binding.apply {
            txMemo = memo
            if (txMemo.isEmpty()) {
                tabMemoMsg.text = getString(R.string.str_tap_for_add_memo_msg)
                tabMemoMsg.setTextColor(ContextCompat.getColorStateList(requireContext(), R.color.color_base03))
            } else {
                tabMemoMsg.text = txMemo
                tabMemoMsg.setTextColor(ContextCompat.getColorStateList(requireContext(), R.color.color_base01))
            }
        }
        txSimul()
    }

    private fun updateFeeView() {
        binding.apply {
            txFee?.getAmount(0)?.let { fee ->
                BaseData.getAsset(selectedChain.apiName, fee.denom)?.let { asset ->
                    feeTokenImg.setTokenImg(asset)
                    feeToken.text = asset.symbol

                    val amount = fee.amount.toBigDecimal()
                    val price = BaseData.getPrice(asset.coinGeckoId)

                    asset.decimals?.let { decimal ->
                        val dpAmount = amount.movePointLeft(decimal).setScale(decimal, RoundingMode.DOWN)
                        feeAmount.text = formatString(dpAmount.toPlainString(), decimal)
                        feeDenom.text = asset.symbol
                        val value = price.multiply(amount).movePointLeft(decimal).setScale(decimal, RoundingMode.DOWN)
                        feeValue.text = formatAssetValue(value)
                    }
                }
            }
        }
    }

    private fun clickAction() {
        var isClickable = true
        binding.apply {
            memoView.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    MemoFragment(txMemo, object : MemoListener {
                        override fun memo(memo: String) {
                            updateMemoView(memo)
                        }

                    }).show(
                        requireActivity().supportFragmentManager, MemoFragment::class.java.name
                    )

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            feeTokenLayout.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    AssetFragment(selectedChain, feeInfos[selectedFeeInfo].feeDatas, object : AssetSelectListener {
                        override fun select(denom: String) {
                            var tempCoin: CoinProto.Coin? = null
                            selectedChain.getDefaultFeeCoins(requireContext()).forEach { feeCoin ->
                                if (feeCoin.denom == denom) {
                                    tempCoin = CoinProto.Coin.newBuilder().setDenom(denom).setAmount(feeCoin.amount).build()
                                }
                            }
                            val tempTxFee = TxProto.Fee.newBuilder()
                                .setGasLimit(BaseConstant.BASE_GAS_AMOUNT.toLong())
                                .addAmount(tempCoin).build()
                            txFee = tempTxFee
                            updateFeeView()
                        }

                    }).show(
                        requireActivity().supportFragmentManager, ChainFragment::class.java.name
                    )

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            feeSegment.setOnPositionChangedListener { position ->
                selectedFeeInfo = position
                txFee = selectedChain.getBaseFee(requireContext(), selectedFeeInfo, txFee?.getAmount(0)?.denom)
                updateFeeView()
                txSimul()
            }

            btnGetReward.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    getRewardResultLauncher.launch(this)
                    requireActivity().overridePendingTransition(R.anim.anim_slide_in_bottom, R.anim.anim_fade_out)
                }
            }
        }
    }

    private val getRewardResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
                txViewModel.broadGetRewards(getChannel(), selectedChain.address, claimableRewards, txFee, txMemo, selectedChain)
            }
        }

    private fun txSimul() {
        binding.apply {
            backdropLayout.visibility = View.VISIBLE
            txViewModel.simulateGetRewards(getChannel(), selectedChain.address, claimableRewards, txFee, txMemo)
        }
    }

    private fun setUpSimulate() {
        txViewModel.simulate.observe(viewLifecycleOwner) { gasInfo ->
            isBroadCastTx(true)
            updateFeeViewWithSimul(gasInfo)
        }

        txViewModel.errorMessage.observe(viewLifecycleOwner) { response ->
            isBroadCastTx(false)
            requireContext().makeToast(response)
            return@observe
        }
    }

    private fun updateFeeViewWithSimul(gasInfo: AbciProto.GasInfo) {
        txFee?.let { fee ->
            feeInfos[selectedFeeInfo].feeDatas.firstOrNull { it.denom == fee.getAmount(0).denom }?.let { gasRate ->
                val gasLimit = (gasInfo.gasUsed.toDouble() * selectedChain.gasMultiply()).toLong().toBigDecimal()
                val feeCoinAmount = gasRate.gasRate?.multiply(gasLimit)?.setScale(0, RoundingMode.UP)

                val feeCoin =  CoinProto.Coin.newBuilder().setDenom(fee.getAmount(0).denom).setAmount(feeCoinAmount.toString()).build()
                txFee = TxProto.Fee.newBuilder().setGasLimit(gasLimit.toLong()).addAmount(feeCoin).build()
            }
        }
        updateFeeView()
    }

    private fun isBroadCastTx(isSuccess: Boolean) {
        binding.backdropLayout.visibility = View.GONE
        binding.btnGetReward.updateButtonView(isSuccess)
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
        }
    }

    private fun getChannel(): ManagedChannel {
        return ManagedChannelBuilder.forAddress(selectedChain.grpcHost, selectedChain.grpcPort).useTransportSecurity().build()
    }

    private fun setupRatio(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet = bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet) as View
        val behavior = BottomSheetBehavior.from(bottomSheet)
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = getBottomSheetDialogDefaultHeight()
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun getBottomSheetDialogDefaultHeight(): Int {
        return getWindowHeight() * 19 / 20
    }

    private fun getWindowHeight(): Int {
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    override fun onStart() {
        super.onStart()

        val bottomSheetDialog = dialog as BottomSheetDialog
        val bottomSheet =
            bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

        bottomSheet?.let { sheet ->
            val behavior = BottomSheetBehavior.from(sheet)
            behavior.isHideable = true

            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {
                        BottomSheetBehavior.STATE_HIDDEN -> dismiss()
                        BottomSheetBehavior.STATE_COLLAPSED -> {
                            behavior.state = BottomSheetBehavior.STATE_EXPANDED
                        }

                        else -> {}
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {}
            })
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}