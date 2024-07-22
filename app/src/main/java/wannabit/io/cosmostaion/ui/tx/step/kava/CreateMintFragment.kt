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
import com.cosmos.tx.v1beta1.TxProto
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kava.cdp.v1beta1.GenesisProto
import com.kava.cdp.v1beta1.TxProto.MsgCreateCDP
import com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.expectUSDXLTV
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.amountHandlerLeft
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.res.Asset
import wannabit.io.cosmostaion.data.model.res.FeeInfo
import wannabit.io.cosmostaion.databinding.FragmentCreateMintBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.ui.main.chain.cosmos.TxType
import wannabit.io.cosmostaion.ui.option.tx.general.AmountSelectListener
import wannabit.io.cosmostaion.ui.option.tx.general.AssetFragment
import wannabit.io.cosmostaion.ui.option.tx.general.AssetSelectListener
import wannabit.io.cosmostaion.ui.option.tx.general.InsertAmountFragment
import wannabit.io.cosmostaion.ui.option.tx.general.MemoFragment
import wannabit.io.cosmostaion.ui.option.tx.general.MemoListener
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.tx.step.BaseTxFragment
import java.math.BigDecimal
import java.math.RoundingMode

class CreateMintFragment : BaseTxFragment() {

    private var _binding: FragmentCreateMintBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain
    private lateinit var collateralParam: GenesisProto.CollateralParam
    private lateinit var priceFeed: QueryPricesResponse

    private var feeInfos: MutableList<FeeInfo> = mutableListOf()
    private var selectedFeeInfo = 0
    private var txFee: TxProto.Fee? = null
    private var txTip: TxProto.Tip? = null
    private var txMemo = ""

    private var collateralAsset: Asset? = null
    private var principalAsset: Asset? = null
    private var collateralAvailableAmount = BigDecimal.ZERO
    private var toCollateralAmount = ""
    private var toPrincipalAmount = ""

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(
            selectedChain: BaseChain,
            collateralParam: GenesisProto.CollateralParam?,
            priceFeed: QueryPricesResponse?
        ): CreateMintFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putSerializable("collateralParam", collateralParam)
                putSerializable("priceFeed", priceFeed)
            }
            val fragment = CreateMintFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateMintBinding.inflate(layoutInflater, container, false)
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
                arguments?.getSerializable(
                    "collateralParam", GenesisProto.CollateralParam::class.java
                )?.let { collateralParam = it }
                arguments?.getSerializable(
                    "priceFeed", QueryPricesResponse::class.java
                )?.let { priceFeed = it }

            } else {
                (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                    selectedChain = it
                }
                (arguments?.getSerializable("collateralParam") as? GenesisProto.CollateralParam)?.let {
                    collateralParam = it
                }
                (arguments?.getSerializable("priceFeed") as? QueryPricesResponse)?.let {
                    priceFeed = it
                }
            }

            listOf(
                collateralAmountView, principalAmountView, memoView, feeView
            ).forEach { it.setBackgroundResource(R.drawable.cell_bg) }
            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)

            collateralAsset = BaseData.getAsset(selectedChain.apiName, collateralParam.denom)
            collateralAsset?.let { asset ->
                tokenImg.setTokenImg(asset)
                tokenName.text = asset.symbol?.uppercase()
            }

            principalAsset = BaseData.getAsset(selectedChain.apiName, "usdx")
            principalAsset?.let { asset ->
                principalTokenImg.setTokenImg(asset)
                principalTokenName.text = asset.symbol?.uppercase()
            }

            val balanceAmount = selectedChain.cosmosFetcher?.balanceAmount(collateralParam.denom)
            if (txFee?.getAmount(0)?.denom == collateralParam.denom) {
                val feeAmount = txFee?.getAmount(0)?.amount?.toBigDecimal()
                collateralAvailableAmount = balanceAmount?.subtract(feeAmount)
            }
            collateralAvailableAmount = balanceAmount
        }
    }

    private fun updateCollateralAmountView(toAmount: String) {
        binding.apply {
            tabMsgTxt.visibility = View.GONE
            amountLayout.visibility = View.VISIBLE

            toCollateralAmount = toAmount
            collateralAsset?.let { asset ->
                val dpAmount = BigDecimal(toAmount).movePointLeft(asset.decimals ?: 6)
                    .setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                mintAmount.text = formatAmount(dpAmount.toPlainString(), asset.decimals ?: 6)
                principalAmountView.visibility = View.VISIBLE
            }
            txSimulate()
        }
    }

    private fun updatePrincipalAmountView(toAmount: String) {
        binding.apply {
            principalTabMsgTxt.visibility = View.GONE
            principalAmountLayout.visibility = View.VISIBLE

            toPrincipalAmount = toAmount
            principalAsset?.let { asset ->
                val dpAmount = BigDecimal(toAmount).movePointLeft(asset.decimals ?: 6)
                    .setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                principalMintAmount.text =
                    formatAmount(dpAmount.toPlainString(), asset.decimals ?: 6)
                principalAmountView.visibility = View.VISIBLE
            }
            txSimulate()
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

    private fun setUpClickAction() {
        binding.apply {
            collateralAmountView.setOnClickListener {
                handleOneClickWithDelay(
                    InsertAmountFragment.newInstance(TxType.MINT_CREATE_COLLATERAL,
                        collateralAvailableAmount.toString(),
                        toCollateralAmount,
                        collateralAsset,
                        object : AmountSelectListener {
                            override fun select(toAmount: String) {
                                updateCollateralAmountView(toAmount)
                            }
                        })
                )
            }

            principalAmountView.setOnClickListener {
                handleOneClickWithDelay(
                    InsertAmountFragment.newInstance(TxType.MINT_CREATE_PRINCIPAL,
                        collateralParam.expectUSDXLTV(
                            toCollateralAmount.toBigDecimal(), priceFeed
                        ).toString(),
                        toPrincipalAmount,
                        principalAsset,
                        object : AmountSelectListener {
                            override fun select(toAmount: String) {
                                updatePrincipalAmountView(toAmount)
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

            btnCreateMint.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    createMintResultLauncher.launch(this)
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

    private val createMintResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
                txViewModel.broadMintCreate(
                    getChannel(selectedChain),
                    selectedChain.address,
                    onBindCreateMint(),
                    txFee,
                    txTip,
                    txMemo,
                    selectedChain
                )
            }
        }

    private fun txSimulate() {
        binding.apply {
            if (toCollateralAmount.isEmpty() || toPrincipalAmount.isEmpty()) {
                return
            }
            if (toCollateralAmount.toBigDecimal() == BigDecimal.ZERO || toPrincipalAmount.toBigDecimal() == BigDecimal.ZERO) {
                return
            }
            if (!selectedChain.isGasSimulable()) {
                return updateFeeViewWithSimulate(null)
            }

            btnCreateMint.updateButtonView(false)
            backdropLayout.visibility = View.VISIBLE
            txViewModel.simulateMintCreate(
                getChannel(selectedChain),
                selectedChain.address,
                onBindCreateMint(),
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
            val selectedFeeData =
                feeInfos[selectedFeeInfo].feeDatas.firstOrNull { it.denom == fee.getAmount(0).denom }
            val gasRate = selectedFeeData?.gasRate

            gasInfo?.let { info ->
                val gasLimit =
                    (info.gasUsed.toDouble() * selectedChain.gasMultiply()).toLong().toBigDecimal()
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
        binding.btnCreateMint.updateButtonView(isSuccess)
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

    private fun onBindCreateMint(): MsgCreateCDP? {
        val collateralCoin = CoinProto.Coin.newBuilder().setDenom(collateralParam.denom)
            .setAmount(toCollateralAmount).build()
        val principalCoin =
            CoinProto.Coin.newBuilder().setDenom("usdx").setAmount(toPrincipalAmount).build()
        return MsgCreateCDP.newBuilder().setSender(selectedChain.address)
            .setCollateral(collateralCoin).setPrincipal(principalCoin)
            .setCollateralType(collateralParam.type).build()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}