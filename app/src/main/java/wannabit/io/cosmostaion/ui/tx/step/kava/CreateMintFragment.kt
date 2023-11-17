package wannabit.io.cosmostaion.ui.tx.step.kava

import android.app.Activity
import android.content.Intent
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
import com.kava.cdp.v1beta1.GenesisProto
import com.kava.cdp.v1beta1.TxProto.MsgCreateCDP
import com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.expectUSDXLTV
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
import wannabit.io.cosmostaion.ui.dialog.tx.AmountSelectListener
import wannabit.io.cosmostaion.ui.dialog.tx.AssetFragment
import wannabit.io.cosmostaion.ui.dialog.tx.AssetSelectListener
import wannabit.io.cosmostaion.ui.dialog.tx.ChainFragment
import wannabit.io.cosmostaion.ui.dialog.tx.InsertAmountFragment
import wannabit.io.cosmostaion.ui.dialog.tx.MemoFragment
import wannabit.io.cosmostaion.ui.dialog.tx.MemoListener
import wannabit.io.cosmostaion.ui.main.chain.TxType
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.tx.step.BaseTxFragment
import java.math.BigDecimal
import java.math.RoundingMode

class CreateMintFragment(
    val selectedChain: CosmosLine,
    private val collateralParam: GenesisProto.CollateralParam?,
    private val priceFeed: QueryPricesResponse?,
): BaseTxFragment() {

    private var _binding: FragmentCreateMintBinding? = null
    private val binding get() = _binding!!

    private var feeInfos: MutableList<FeeInfo> = mutableListOf()
    private var selectedFeeInfo = 0
    private var txFee: TxProto.Fee? = null
    private var txMemo = ""

    private var collateralAsset: Asset? = null
    private var principalAsset: Asset? = null
    private var collateralAvailableAmount = BigDecimal.ZERO
    private var toCollateralAmount = ""
    private var toPrincipalAmount = ""

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
        txSimul()
        clickAction()
        setUpSimulate()
        setUpBroadcast()
    }

    private fun initView() {
        binding.apply {
            collateralAmountView.setBackgroundResource(R.drawable.cell_bg)
            principalAmountView.setBackgroundResource(R.drawable.cell_bg)
            memoView.setBackgroundResource(R.drawable.cell_bg)
            feeView.setBackgroundResource(R.drawable.cell_bg)

            collateralParam?.let { collateralParam ->
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

                val balanceAmount = selectedChain.balanceAmount(collateralParam.denom)
                if (txFee?.getAmount(0)?.denom == collateralParam.denom) {
                    val feeAmount = txFee?.getAmount(0)?.amount?.toBigDecimal()
                    collateralAvailableAmount = balanceAmount.subtract(feeAmount)
                }
                collateralAvailableAmount = balanceAmount
            }
        }
    }

    private fun updateCollateralAmountView(toAmount: String) {
        binding.apply {
            tabMsgTxt.visibility = View.GONE
            amountLayout.visibility = View.VISIBLE

            toCollateralAmount = toAmount
            collateralAsset?.let { asset ->
                asset.decimals?.let { decimal ->
                    val dpAmount = BigDecimal(toAmount).movePointLeft(decimal)
                        .setScale(decimal, RoundingMode.DOWN)
                    mintAmount.text = formatAmount(dpAmount.toPlainString(), decimal)
                    principalAmountView.visibility = View.VISIBLE
                }
            }
            txSimul()
        }
    }

    private fun updatePrincipalAmountView(toAmount: String) {
        binding.apply {
            principalTabMsgTxt.visibility = View.GONE
            principalAmountLayout.visibility = View.VISIBLE

            toPrincipalAmount = toAmount
            principalAsset?.let { asset ->
                asset.decimals?.let { decimal ->
                    val dpAmount = BigDecimal(toAmount).movePointLeft(decimal)
                        .setScale(decimal, RoundingMode.DOWN)
                    principalMintAmount.text = formatAmount(dpAmount.toPlainString(), decimal)
                    principalAmountView.visibility = View.VISIBLE
                }
            }
            txSimul()
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
                        feeAmount.text = formatAmount(dpAmount.toPlainString(), decimal)
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
            collateralAmountView.setOnClickListener {
                if (isClickable) {
                    isClickable = false

                    InsertAmountFragment(
                        TxType.MINT_CREATE_COLLATERAL,
                        null,
                        collateralAvailableAmount,
                        toCollateralAmount,
                        collateralAsset,
                        null,
                        object : AmountSelectListener {
                            override fun select(toAmount: String) {
                                updateCollateralAmountView(toAmount)
                            }

                        }).show(requireActivity().supportFragmentManager, InsertAmountFragment::class.java.name)

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            principalAmountView.setOnClickListener {
                if (isClickable) {
                    isClickable = false

                    InsertAmountFragment(
                        TxType.MINT_CREATE_PRINCIPAL,
                        null,
                        collateralParam?.expectUSDXLTV(toCollateralAmount.toBigDecimal(), priceFeed),
                        toPrincipalAmount,
                        principalAsset,
                        null,
                        object : AmountSelectListener {
                            override fun select(toAmount: String) {
                                updatePrincipalAmountView(toAmount)
                            }

                        }).show(requireActivity().supportFragmentManager, InsertAmountFragment::class.java.name)

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

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

            btnCreateMint.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    createMintResultLauncher.launch(this)
                    requireActivity().overridePendingTransition(R.anim.anim_slide_in_bottom, R.anim.anim_fade_out)
                }
            }
        }
    }

    private val createMintResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
                txViewModel.broadMintCreate(getChannel(selectedChain), selectedChain.address, onBindCreateMint(), txFee, txMemo, selectedChain)
            }
        }

    private fun txSimul() {
        binding.apply {
            if (toCollateralAmount.isEmpty() || toPrincipalAmount.isEmpty()) { return }
            if (toCollateralAmount.toBigDecimal() == BigDecimal.ZERO || toPrincipalAmount.toBigDecimal() == BigDecimal.ZERO) { return }

            backdropLayout.visibility = View.VISIBLE
            txViewModel.simulateMintCreate(getChannel(selectedChain), selectedChain.address, onBindCreateMint(), txFee, txMemo)
        }
    }

    private fun setUpSimulate() {
        txViewModel.simulate.observe(viewLifecycleOwner) { gasInfo ->
            isBroadCastTx(true)
            updateFeeViewWithSimul(gasInfo)
        }

        txViewModel.errorMessage.observe(viewLifecycleOwner) { response ->
            isBroadCastTx(false)
            requireContext().showToast(view, response, true)
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
        }
    }

    private fun onBindCreateMint(): MsgCreateCDP? {
        val collateralCoin =
            CoinProto.Coin.newBuilder().setDenom(collateralParam?.denom).setAmount(toCollateralAmount).build()
        val principalCoin =
            CoinProto.Coin.newBuilder().setDenom("usdx").setAmount(toPrincipalAmount).build()
        return MsgCreateCDP.newBuilder()
            .setSender(selectedChain.address)
            .setCollateral(collateralCoin)
            .setPrincipal(principalCoin)
            .setCollateralType(collateralParam?.type)
            .build()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}