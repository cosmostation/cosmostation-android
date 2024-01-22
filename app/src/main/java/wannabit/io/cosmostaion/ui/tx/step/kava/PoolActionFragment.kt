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
import com.kava.swap.v1beta1.QueryProto
import com.kava.swap.v1beta1.TxProto.MsgDeposit
import com.kava.swap.v1beta1.TxProto.MsgWithdraw
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
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
import wannabit.io.cosmostaion.databinding.FragmentPoolActionBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.ui.dialog.tx.AmountSelectListener
import wannabit.io.cosmostaion.ui.dialog.tx.AssetFragment
import wannabit.io.cosmostaion.ui.dialog.tx.AssetSelectListener
import wannabit.io.cosmostaion.ui.dialog.tx.InsertAmountFragment
import wannabit.io.cosmostaion.ui.dialog.tx.MemoFragment
import wannabit.io.cosmostaion.ui.dialog.tx.MemoListener
import wannabit.io.cosmostaion.ui.dialog.tx.kava.PoolAmountSelectListener
import wannabit.io.cosmostaion.ui.dialog.tx.kava.PoolInsertAmountFragment
import wannabit.io.cosmostaion.ui.main.chain.TxType
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.tx.step.BaseTxFragment
import java.math.BigDecimal
import java.math.RoundingMode

class PoolActionFragment(
    val selectedChain: CosmosLine,
    private val poolActionType: PoolActionType,
    private val swapPool: QueryProto.PoolResponse,
    private val deposit: QueryProto.DepositResponse?
) : BaseTxFragment() {

    private var _binding: FragmentPoolActionBinding? = null
    private val binding get() = _binding!!

    private var feeInfos: MutableList<FeeInfo> = mutableListOf()
    private var selectedFeeInfo = 0
    private var txFee: TxProto.Fee? = null
    private var txMemo = ""

    private var pool1Asset: Asset? = null
    private var pool2Asset: Asset? = null
    private var swapRate = BigDecimal.ZERO
    private var coin1AvailableAmount = BigDecimal.ZERO
    private var coin2AvailableAmount = BigDecimal.ZERO
    private var coin1ToAmount = ""
    private var coin2ToAmount = ""
    private var toWithdrawAmount = ""

    private var isClickable = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPoolActionBinding.inflate(layoutInflater, container, false)
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
            listOf(
                poolView, shareAmountView, memoView, feeView
            ).forEach { it.setBackgroundResource(R.drawable.cell_bg) }
            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)

            if (poolActionType == PoolActionType.DEPOSIT) {
                poolActionTitle.text = getString(R.string.title_pool_deposit)
                poolView.visibility = View.VISIBLE
                buttonLayout.visibility = View.VISIBLE
                shareAmountView.visibility = View.GONE
                btnPool.text = getString(R.string.str_deposit)

                BaseData.getAsset(selectedChain.apiName, swapPool.getCoins(0).denom)
                    ?.let { asset1 ->
                        BaseData.getAsset(selectedChain.apiName, swapPool.getCoins(1).denom)
                            ?.let { asset2 ->
                                pool1Asset = asset1
                                pool2Asset = asset2

                                pool1TokenImg.setTokenImg(asset1)
                                pool1TokenName.text = asset1.symbol
                                pool1Amount.text = formatAmount(
                                    BigDecimal.ZERO.toPlainString(), asset1.decimals ?: 6
                                )
                                pool1Value.text = formatAssetValue(BigDecimal.ZERO)

                                pool2TokenImg.setTokenImg(asset2)
                                pool2TokenName.text = asset2.symbol
                                pool2Amount.text = formatAmount(
                                    BigDecimal.ZERO.toPlainString(), asset2.decimals ?: 6
                                )
                                pool2Value.text = formatAssetValue(BigDecimal.ZERO)

                                val poolCoin1Amount = swapPool.getCoins(0).amount
                                val poolCoin2Amount = swapPool.getCoins(1).amount
                                var availableCoin1Amount =
                                    selectedChain.balanceAmount(swapPool.getCoins(0).denom)
                                if (txFee?.getAmount(0)?.denom == swapPool.getCoins(0).denom) {
                                    val feeAmount = txFee?.getAmount(0)?.amount?.toBigDecimal()
                                    availableCoin1Amount = availableCoin1Amount.subtract(feeAmount)
                                }
                                val availableCoin2Amount =
                                    selectedChain.balanceAmount(swapPool.getCoins(1).denom)

                                swapRate = poolCoin1Amount.toBigDecimal().divide(
                                    poolCoin2Amount.toBigDecimal(), 24, RoundingMode.DOWN
                                )
                                val availableRate = availableCoin1Amount.divide(
                                    availableCoin2Amount, 24, RoundingMode.DOWN
                                )
                                if (swapRate > availableRate) {
                                    coin1AvailableAmount = availableCoin1Amount
                                    coin2AvailableAmount = availableCoin1Amount.divide(
                                        swapRate, 0, RoundingMode.DOWN
                                    )
                                } else {
                                    coin2AvailableAmount = availableCoin2Amount
                                    coin1AvailableAmount = availableCoin2Amount.multiply(swapRate)
                                        .setScale(0, RoundingMode.DOWN)
                                }
                            }
                    }

            } else {
                poolActionTitle.text = getString(R.string.title_pool_withdraw)
                poolView.visibility = View.GONE
                buttonLayout.visibility = View.GONE
                btnPool.text = getString(R.string.str_withdraw)
                shareAmountView.visibility = View.VISIBLE
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

    private fun updateDepositAmountView(amount1: String, amount2: String) {
        binding.apply {
            coin1ToAmount = amount1
            pool1Asset?.decimals?.let { decimal ->
                val dpAmount = coin1ToAmount.toBigDecimal().movePointLeft(decimal)
                    .setScale(decimal, RoundingMode.DOWN)
                val coin1Price = BaseData.getPrice(pool1Asset?.coinGeckoId)
                val coin1Value = coin1Price.multiply(dpAmount)

                pool1Amount.text = formatAmount(dpAmount.toPlainString(), decimal)
                pool1Value.text = formatAssetValue(coin1Value)
            }

            coin2ToAmount = amount2
            pool2Asset?.decimals?.let { decimal ->
                val dpAmount = coin2ToAmount.toBigDecimal().movePointLeft(decimal)
                    .setScale(decimal, RoundingMode.DOWN)
                val coin2Price = BaseData.getPrice(pool2Asset?.coinGeckoId)
                val coin2Value = coin2Price.multiply(dpAmount)

                pool2Amount.text = formatAmount(dpAmount.toPlainString(), decimal)
                pool2Value.text = formatAssetValue(coin2Value)
            }
        }
        txSimulate()
    }

    private fun updateWithdrawAmountView(toAmount: String) {
        binding.apply {
            tabMsgTxt.visibility = View.GONE
            amountLayout.visibility = View.VISIBLE

            toWithdrawAmount = toAmount
            val dpAmount = toAmount.toBigDecimal().movePointLeft(6).setScale(6, RoundingMode.DOWN)
            shareAmount.text = formatAmount(dpAmount.toPlainString(), 6)
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
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            poolView.setOnClickListener {
                PoolInsertAmountFragment(pool1Asset,
                    pool2Asset,
                    coin1AvailableAmount,
                    coin2AvailableAmount,
                    swapRate,
                    coin1ToAmount,
                    coin2ToAmount,
                    object : PoolAmountSelectListener {
                        override fun select(coin1ToAmount: String, coin2ToAmount: String) {
                            updateDepositAmountView(coin1ToAmount, coin2ToAmount)
                        }

                    }).show(
                    requireActivity().supportFragmentManager,
                    PoolInsertAmountFragment::class.java.name
                )
                setClickableOnce(isClickable)
            }

            shareAmountView.setOnClickListener {
                InsertAmountFragment(TxType.POOL_WITHDRAW,
                    null,
                    deposit?.sharesOwned?.toBigDecimal(),
                    toWithdrawAmount,
                    null,
                    null,
                    object : AmountSelectListener {
                        override fun select(toAmount: String) {
                            updateWithdrawAmountView(toAmount)
                        }

                    }).show(
                    requireActivity().supportFragmentManager, InsertAmountFragment::class.java.name
                )
                setClickableOnce(isClickable)
            }

            btnQuarter.setOnClickListener {
                val coin1QuarterAmount =
                    coin1AvailableAmount.multiply(BigDecimal("0.25")).setScale(0, RoundingMode.DOWN)
                val coin2QuarterAmount =
                    coin2AvailableAmount.multiply(BigDecimal("0.25")).setScale(0, RoundingMode.DOWN)
                updateDepositAmountView(
                    coin1QuarterAmount.toPlainString(), coin2QuarterAmount.toPlainString()
                )
            }

            btnHalf.setOnClickListener {
                val coin1HalfAmount =
                    coin1AvailableAmount.multiply(BigDecimal("0.5")).setScale(0, RoundingMode.DOWN)
                val coin2HalfAmount =
                    coin2AvailableAmount.multiply(BigDecimal("0.5")).setScale(0, RoundingMode.DOWN)
                updateDepositAmountView(
                    coin1HalfAmount.toPlainString(), coin2HalfAmount.toPlainString()
                )
            }

            btnMax.setOnClickListener {
                updateDepositAmountView(
                    coin1AvailableAmount.toPlainString(), coin2AvailableAmount.toPlainString()
                )
            }

            memoView.setOnClickListener {
                MemoFragment(txMemo, object : MemoListener {
                    override fun memo(memo: String) {
                        updateMemoView(memo)
                    }

                }).show(
                    requireActivity().supportFragmentManager, MemoFragment::class.java.name
                )
                setClickableOnce(isClickable)
            }

            feeTokenLayout.setOnClickListener {
                AssetFragment(selectedChain,
                    feeInfos[selectedFeeInfo].feeDatas,
                    object : AssetSelectListener {
                        override fun select(denom: String) {
                            selectedChain.getDefaultFeeCoins(requireContext())
                                .firstOrNull { it.denom == denom }?.let { feeCoin ->
                                    val updateFeeCoin = CoinProto.Coin.newBuilder().setDenom(denom)
                                        .setAmount(feeCoin.amount).build()

                                    val updateTxFee = TxProto.Fee.newBuilder()
                                        .setGasLimit(BaseConstant.BASE_GAS_AMOUNT.toLong())
                                        .addAmount(updateFeeCoin).build()

                                    txFee = updateTxFee
                                    updateFeeView()
                                    txSimulate()
                                }
                        }

                    }).show(
                    requireActivity().supportFragmentManager, AssetFragment::class.java.name
                )
                setClickableOnce(isClickable)
            }

            feeSegment.setOnPositionChangedListener { position ->
                selectedFeeInfo = position
                txFee = selectedChain.getBaseFee(
                    requireContext(), selectedFeeInfo, txFee?.getAmount(0)?.denom
                )
                updateFeeView()
                txSimulate()
            }

            btnPool.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    getPoolResultLauncher.launch(this)
                    requireActivity().overridePendingTransition(
                        R.anim.anim_slide_in_bottom, R.anim.anim_fade_out
                    )
                }
            }
        }
    }

    private fun setClickableOnce(clickable: Boolean) {
        if (clickable) {
            isClickable = false

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 1000)
        }
    }

    private val getPoolResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE

                when (poolActionType) {
                    PoolActionType.DEPOSIT -> {
                        txViewModel.broadPoolDeposit(
                            getChannel(selectedChain),
                            selectedChain.address,
                            onBindDepositMsg(),
                            txFee,
                            txMemo,
                            selectedChain
                        )
                    }

                    PoolActionType.WITHDRAW -> {
                        txViewModel.broadPoolWithdraw(
                            getChannel(selectedChain),
                            selectedChain.address,
                            onBindWithdrawMsg(),
                            txFee,
                            txMemo,
                            selectedChain
                        )
                    }
                }
            }
        }

    private fun txSimulate() {
        binding.apply {
            when (poolActionType) {
                PoolActionType.DEPOSIT -> {
                    if (coin1ToAmount.isEmpty() || coin2ToAmount.isEmpty()) {
                        return
                    }
                    if (coin1ToAmount.toBigDecimal() == BigDecimal.ZERO || coin2ToAmount.toBigDecimal() == BigDecimal.ZERO) {
                        return
                    }

                    btnPool.updateButtonView(false)
                    backdropLayout.visibility = View.VISIBLE
                    txViewModel.simulatePoolDeposit(
                        getChannel(selectedChain),
                        selectedChain.address,
                        onBindDepositMsg(),
                        txFee,
                        txMemo
                    )
                }

                PoolActionType.WITHDRAW -> {
                    if (toWithdrawAmount.isEmpty()) {
                        return
                    }
                    if (toWithdrawAmount.toBigDecimal() == BigDecimal.ZERO) {
                        return
                    }

                    backdropLayout.visibility = View.VISIBLE
                    txViewModel.simulatePoolWithdraw(
                        getChannel(selectedChain),
                        selectedChain.address,
                        onBindWithdrawMsg(),
                        txFee,
                        txMemo
                    )
                }
            }
        }
    }

    private fun onBindDepositMsg(): MsgDeposit? {
        val slippage = "30000000000000000"
        val deadLine = (System.currentTimeMillis() / 1000) + 300
        val depositCoin1 = CoinProto.Coin.newBuilder().setDenom(swapPool.getCoins(0).denom)
            .setAmount(coin1ToAmount).build()
        val depositCoin2 = CoinProto.Coin.newBuilder().setDenom(swapPool.getCoins(1).denom)
            .setAmount(coin2ToAmount).build()
        return MsgDeposit.newBuilder().setDepositor(selectedChain.address).setTokenA(depositCoin1)
            .setTokenB(depositCoin2).setSlippage(slippage).setDeadline(deadLine).build()
    }

    private fun onBindWithdrawMsg(): MsgWithdraw? {
        val totalShare = swapPool.totalShares.toBigDecimal()
        val padding = BigDecimal("0.97")
        val poolCoin1Amount =
            swapPool.getCoins(0).amount.toBigDecimal().multiply(toWithdrawAmount.toBigDecimal())
                .divide(totalShare, 0, RoundingMode.DOWN).multiply(padding)
                .setScale(0, RoundingMode.DOWN)
        val poolCoin2Amount =
            swapPool.getCoins(1).amount.toBigDecimal().multiply(toWithdrawAmount.toBigDecimal())
                .divide(totalShare, 0, RoundingMode.DOWN).multiply(padding)
                .setScale(0, RoundingMode.DOWN)
        val deadLine = (System.currentTimeMillis() / 1000) + 300
        return MsgWithdraw.newBuilder().setFrom(selectedChain.address).setShares(toWithdrawAmount)
            .setMinTokenA(
                CoinProto.Coin.newBuilder().setDenom(swapPool.getCoins(0).denom)
                    .setAmount(poolCoin1Amount.toPlainString())
            ).setMinTokenB(
                CoinProto.Coin.newBuilder().setDenom(swapPool.getCoins(1).denom)
                    .setAmount(poolCoin2Amount.toPlainString())
            ).setDeadline(deadLine).build()
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
        txFee?.let { fee ->
            feeInfos[selectedFeeInfo].feeDatas.firstOrNull { it.denom == fee.getAmount(0).denom }
                ?.let { gasRate ->
                    val gasLimit =
                        (gasInfo.gasUsed.toDouble() * selectedChain.gasMultiply()).toLong()
                            .toBigDecimal()
                    val feeCoinAmount =
                        gasRate.gasRate?.multiply(gasLimit)?.setScale(0, RoundingMode.UP)

                    val feeCoin = CoinProto.Coin.newBuilder().setDenom(fee.getAmount(0).denom)
                        .setAmount(feeCoinAmount.toString()).build()
                    txFee =
                        TxProto.Fee.newBuilder().setGasLimit(gasLimit.toLong()).addAmount(feeCoin)
                            .build()
                }
        }
        updateFeeView()
    }

    private fun isBroadCastTx(isSuccess: Boolean) {
        binding.backdropLayout.visibility = View.GONE
        binding.btnPool.updateButtonView(isSuccess)
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

enum class PoolActionType { DEPOSIT, WITHDRAW }