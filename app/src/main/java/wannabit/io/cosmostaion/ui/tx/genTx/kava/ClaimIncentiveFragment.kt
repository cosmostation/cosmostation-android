package wannabit.io.cosmostaion.ui.tx.genTx.kava

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
import com.cosmos.tx.v1beta1.TxProto
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.protobuf.Any
import com.kava.incentive.v1beta1.QueryProto
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.fetcher.allIncentiveCoins
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.amountHandlerLeft
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.sign.Signer
import wannabit.io.cosmostaion.data.model.res.FeeInfo
import wannabit.io.cosmostaion.databinding.FragmentClaimIncentiveBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.tx.option.general.AssetFragment
import wannabit.io.cosmostaion.ui.tx.option.general.AssetSelectListener
import wannabit.io.cosmostaion.ui.tx.option.general.MemoFragment
import wannabit.io.cosmostaion.ui.tx.option.general.MemoListener
import wannabit.io.cosmostaion.ui.tx.genTx.BaseTxFragment
import java.math.RoundingMode

class ClaimIncentiveFragment : BaseTxFragment() {

    private var _binding: FragmentClaimIncentiveBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain
    private lateinit var incentive: QueryProto.QueryRewardsResponse

    private var feeInfos: MutableList<FeeInfo> = mutableListOf()
    private var selectedFeeInfo = 0
    private var txFee: TxProto.Fee? = null
    private var txMemo = ""

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(
            selectedChain: BaseChain, incentive: QueryProto.QueryRewardsResponse
        ): ClaimIncentiveFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putSerializable("incentive", incentive)
            }
            val fragment = ClaimIncentiveFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClaimIncentiveBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initFee()
        updateFeeView()
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
                arguments?.getSerializable("incentive", QueryProto.QueryRewardsResponse::class.java)
                    ?.let { incentive = it }

            } else {
                (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                    selectedChain = it
                }
                (arguments?.getSerializable("incentive") as? QueryProto.QueryRewardsResponse)?.let {
                    incentive = it
                }
            }

            listOf(
                incentiveView, memoView, feeView
            ).forEach { it.setBackgroundResource(R.drawable.cell_bg) }
            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)

            val allIncentives = incentive.allIncentiveCoins()
            allIncentives.firstOrNull { it.denom == "ukava" }?.let { kavaIncentive ->
                BaseData.getAsset(selectedChain.apiName, kavaIncentive.denom)?.let { asset ->
                    kavaLayout.visibility = View.VISIBLE
                    kavaAmount.text = formatAmount(
                        kavaIncentive.amount.toBigDecimal().movePointLeft(asset.decimals ?: 6)
                            .toPlainString(), asset.decimals ?: 6
                    )
                    kavaDenom.text = asset.symbol
                    kavaDenom.setTextColor(asset.assetColor())
                }
            }

            allIncentives.firstOrNull { it.denom == "hard" }?.let { hardIncentive ->
                BaseData.getAsset(selectedChain.apiName, hardIncentive.denom)?.let { asset ->
                    hardLayout.visibility = View.VISIBLE
                    hardAmount.text = formatAmount(
                        hardIncentive.amount.toBigDecimal().movePointLeft(asset.decimals ?: 6)
                            .toPlainString(), asset.decimals ?: 6
                    )
                    hardDenom.text = asset.symbol
                    hardDenom.setTextColor(asset.assetColor())
                }
            }

            allIncentives.firstOrNull { it.denom == "usdx" }?.let { usdxIncentive ->
                BaseData.getAsset(selectedChain.apiName, usdxIncentive.denom)?.let { asset ->
                    usdxLayout.visibility = View.VISIBLE
                    usdxAmount.text = formatAmount(
                        usdxIncentive.amount.toBigDecimal().movePointLeft(asset.decimals ?: 6)
                            .toPlainString(), asset.decimals ?: 6
                    )
                    usdxDenom.text = asset.symbol
                    usdxDenom.setTextColor(asset.assetColor())
                }
            }

            allIncentives.firstOrNull { it.denom == "swp" }?.let { swpIncentive ->
                BaseData.getAsset(selectedChain.apiName, swpIncentive.denom)?.let { asset ->
                    swpLayout.visibility = View.VISIBLE
                    swpAmount.text = formatAmount(
                        swpIncentive.amount.toBigDecimal().movePointLeft(asset.decimals ?: 6)
                            .toPlainString(), asset.decimals ?: 6
                    )
                    swpDenom.text = asset.symbol
                    swpDenom.setTextColor(asset.assetColor())
                }
            }
        }
    }

    private fun initFee() {
        binding.apply {
            feeInfos = selectedChain.getFeeInfos(requireContext())
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
            feeSegment.setPosition(selectedChain.getFeeBasePosition(), false)
            selectedFeeInfo = selectedChain.getFeeBasePosition()
            txFee = selectedChain.getInitFee(requireContext())
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

            feeSegment.setOnPositionChangedListener { position ->
                selectedFeeInfo = position
                txFee = selectedChain.getBaseFee(
                    requireContext(), selectedFeeInfo, txFee?.getAmount(0)?.denom
                )
                updateFeeView()
                txSimulate()
            }

            btnClaimIncentive.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    getRewardResultLauncher.launch(this)
                    if (Build.VERSION.SDK_INT >= 34) {
                        requireActivity().overrideActivityTransition(
                            Activity.OVERRIDE_TRANSITION_OPEN,
                            R.anim.anim_slide_in_bottom,
                            R.anim.anim_fade_out
                        )
                    } else {
                        requireActivity().overridePendingTransition(
                            R.anim.anim_slide_in_bottom,
                            R.anim.anim_fade_out
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

    private val getRewardResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
                txViewModel.broadcast(
                    selectedChain.cosmosFetcher?.getChannel(),
                    onBindClaimIncentiveMsg(),
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
            backdropLayout.visibility = View.VISIBLE
            btnClaimIncentive.updateButtonView(false)
            txViewModel.simulate(
                selectedChain.cosmosFetcher?.getChannel(),
                onBindClaimIncentiveMsg(),
                txFee,
                txMemo,
                selectedChain
            )
        }
    }

    private fun onBindClaimIncentiveMsg(): MutableList<Any> {
        return Signer.claimIncentiveMsg(selectedChain, incentive)
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
            val selectedFeeData =
                feeInfos[selectedFeeInfo].feeDatas.firstOrNull { it.denom == fee.getAmount(0).denom }
            val gasRate = selectedFeeData?.gasRate

            gasUsed?.toLong()?.let { gas ->
                val gasLimit =
                    (gas.toDouble() * selectedChain.simulatedGasMultiply()).toLong().toBigDecimal()
                val feeCoinAmount = gasRate?.multiply(gasLimit)?.setScale(0, RoundingMode.UP)

                val feeCoin = CoinProto.Coin.newBuilder().setDenom(fee.getAmount(0).denom)
                    .setAmount(feeCoinAmount.toString()).build()

                txFee = TxProto.Fee.newBuilder().setGasLimit(gasLimit.toLong()).addAmount(feeCoin)
                    .build()
            }
        }
        updateFeeView()
        isBroadCastTx(true)
    }

    private fun isBroadCastTx(isSuccess: Boolean) {
        binding.backdropLayout.visibility = View.GONE
        binding.btnClaimIncentive.updateButtonView(isSuccess)
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}