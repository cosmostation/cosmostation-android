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
import com.kava.hard.v1beta1.HardProto
import com.kava.hard.v1beta1.TxProto.MsgBorrow
import com.kava.hard.v1beta1.TxProto.MsgDeposit
import com.kava.hard.v1beta1.TxProto.MsgRepay
import com.kava.hard.v1beta1.TxProto.MsgWithdraw
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.formatString
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.res.Asset
import wannabit.io.cosmostaion.data.model.res.FeeInfo
import wannabit.io.cosmostaion.databinding.FragmentLendActionBinding
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

class LendActionFragment(
    val selectedChain: CosmosLine,
    private val lendActionType: LendActionType,
    private val lendMyDeposit: MutableList<CoinProto.Coin>,
    private val lendMyBorrows: MutableList<CoinProto.Coin>,
    private val lendMoneyMarket: HardProto.MoneyMarket?,
    private val borrowAbleAmount: BigDecimal
) : BaseTxFragment() {

    private var _binding: FragmentLendActionBinding? = null
    private val binding get() = _binding!!

    private var feeInfos: MutableList<FeeInfo> = mutableListOf()
    private var selectedFeeInfo = 0
    private var txFee: TxProto.Fee? = null
    private var txMemo = ""

    private var msAsset: Asset? = null
    private var availableAmount = BigDecimal.ZERO
    private var toLendAmount = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLendActionBinding.inflate(layoutInflater, container, false)
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
            lendAmountView.setBackgroundResource(R.drawable.cell_bg)
            memoView.setBackgroundResource(R.drawable.cell_bg)
            feeView.setBackgroundResource(R.drawable.cell_bg)

            lendMoneyMarket?.let { market ->
                BaseData.getAsset(selectedChain.apiName, market.denom)?.let { asset ->
                    msAsset = asset
                    tokenImg.setTokenImg(asset)
                    tokenName.text = asset.symbol

                    when (lendActionType) {
                        LendActionType.DEPOSIT -> {
                            lendActionTitle.text = getString(R.string.title_lend_deposit)
                            lendAmountTitle.text = getString(R.string.title_vault_deposit_amount)
                            btnLend.text = getString(R.string.str_deposit)
                            val balanceAmount = selectedChain.balanceAmount(market.denom)
                            if (txFee?.getAmount(0)?.denom == market.denom) {
                                val feeAmount = txFee?.getAmount(0)?.amount?.toBigDecimal()
                                availableAmount = balanceAmount.subtract(feeAmount)
                            }
                            availableAmount = balanceAmount
                        }

                        LendActionType.WITHDRAW -> {
                            lendActionTitle.text = getString(R.string.title_lend_withdraw)
                            lendAmountTitle.text = getString(R.string.title_vault_withdraw_amount)
                            btnLend.text = getString(R.string.str_withdraw)
                            availableAmount = lendMyDeposit.firstOrNull { it.denom == market.denom }?.amount?.toBigDecimal() ?: BigDecimal.ZERO
                        }

                        LendActionType.BORROW -> {
                            lendActionTitle.text = getString(R.string.title_lend_borrow)
                            lendAmountTitle.text = getString(R.string.title_borrow_amount)
                            btnLend.text = getString(R.string.str_borrow)
                            availableAmount = borrowAbleAmount
                        }

                        LendActionType.REPAY -> {
                            lendActionTitle.text = getString(R.string.title_lend_repay)
                            lendAmountTitle.text = getString(R.string.title_repay_amount)
                            btnLend.text = getString(R.string.str_repay)
                            var borrowedAmount = lendMyBorrows.firstOrNull { it.denom == market.denom }?.amount?.toBigDecimal() ?: BigDecimal.ZERO
                            borrowedAmount = borrowedAmount.multiply(BigDecimal("1.1")).setScale(0, RoundingMode.DOWN)

                            var balanceAmount = selectedChain.balanceAmount(market.denom)
                            if (txFee?.getAmount(0)?.denom == market.denom) {
                                val feeAmount = txFee?.getAmount(0)?.amount?.toBigDecimal()
                                balanceAmount = balanceAmount.subtract(feeAmount)
                            }
                            availableAmount = if (balanceAmount > borrowedAmount) {
                                borrowedAmount
                            } else {
                                balanceAmount
                            }
                        }
                    }
                }

            } ?: run {
                return
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

    private fun updateAmountView(toAmount: String) {
        binding.apply {
            tabMsgTxt.visibility = View.GONE
            amountLayout.visibility = View.VISIBLE

            toLendAmount = toAmount
            msAsset?.let { asset ->
                asset.decimals?.let { decimal ->
                    val dpAmount = BigDecimal(toAmount).movePointLeft(decimal)
                        .setScale(decimal, RoundingMode.DOWN)
                    val price = BaseData.getPrice(asset.coinGeckoId)
                    val value = price.multiply(dpAmount)

                    lendAmount.text = formatString(dpAmount.toPlainString(), decimal)
                    lendValue.text = formatAssetValue(value)
                }
            }
            txSimul()
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
            lendAmountView.setOnClickListener {
                if (isClickable) {
                    isClickable = false

                    when (lendActionType) {
                        LendActionType.DEPOSIT -> {
                            InsertAmountFragment(
                                TxType.LEND_DEPOSIT,
                                null,
                                availableAmount,
                                toLendAmount,
                                msAsset,
                                null,
                                object : AmountSelectListener {
                                    override fun select(toAmount: String) {
                                        updateAmountView(toAmount)
                                    }

                                }).show(requireActivity().supportFragmentManager, InsertAmountFragment::class.java.name)
                        }

                        LendActionType.WITHDRAW -> {
                            InsertAmountFragment(
                                TxType.LEND_WITHDRAW,
                                null,
                                availableAmount,
                                toLendAmount,
                                msAsset,
                                null,
                                object : AmountSelectListener {
                                    override fun select(toAmount: String) {
                                        updateAmountView(toAmount)
                                    }

                                }).show(requireActivity().supportFragmentManager, InsertAmountFragment::class.java.name)
                        }

                        LendActionType.BORROW -> {
                            InsertAmountFragment(
                                TxType.LEND_BORROW,
                                null,
                                availableAmount,
                                toLendAmount,
                                msAsset,
                                null,
                                object : AmountSelectListener {
                                    override fun select(toAmount: String) {
                                        updateAmountView(toAmount)
                                    }

                                }).show(requireActivity().supportFragmentManager, InsertAmountFragment::class.java.name)
                        }

                        LendActionType.REPAY -> {
                            InsertAmountFragment(
                                TxType.LEND_REPAY,
                                null,
                                availableAmount,
                                toLendAmount,
                                msAsset,
                                null,
                                object : AmountSelectListener {
                                    override fun select(toAmount: String) {
                                        updateAmountView(toAmount)
                                    }

                                }).show(requireActivity().supportFragmentManager, InsertAmountFragment::class.java.name)
                        }
                    }

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

            btnLend.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    getLendResultLauncher.launch(this)
                    requireActivity().overridePendingTransition(R.anim.anim_slide_in_bottom, R.anim.anim_fade_out)
                }
            }
        }
    }

    private val getLendResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE

                when (lendActionType) {
                    LendActionType.DEPOSIT -> {
                        txViewModel.broadLendDeposit(
                            getChannel(selectedChain),
                            selectedChain.address,
                            onBindDepositMsg(),
                            txFee,
                            txMemo,
                            selectedChain
                        )
                    }

                    LendActionType.WITHDRAW -> {
                        txViewModel.broadLendWithdraw(
                            getChannel(selectedChain),
                            selectedChain.address,
                            onBindWithdrawMsg(),
                            txFee,
                            txMemo,
                            selectedChain
                        )
                    }

                    LendActionType.BORROW -> {
                        txViewModel.broadLendBorrow(
                            getChannel(selectedChain),
                            selectedChain.address,
                            onBindBorrowMsg(),
                            txFee,
                            txMemo,
                            selectedChain
                        )
                    }

                    LendActionType.REPAY -> {
                        txViewModel.broadLendRepay(
                            getChannel(selectedChain),
                            selectedChain.address,
                            onBindRepayMsg(),
                            txFee,
                            txMemo,
                            selectedChain
                        )
                    }
                }
            }
        }

    private fun txSimul() {
        binding.apply {
            if (toLendAmount.isEmpty()) { return }
            if (toLendAmount.toBigDecimal() == BigDecimal.ZERO) { return }
            backdropLayout.visibility = View.VISIBLE

            when (lendActionType) {
                LendActionType.DEPOSIT -> {
                    txViewModel.simulateLendDeposit(
                        getChannel(selectedChain),
                        selectedChain.address,
                        onBindDepositMsg(),
                        txFee,
                        txMemo
                    )
                }

                LendActionType.WITHDRAW -> {
                    txViewModel.simulateLendWithdraw(
                        getChannel(selectedChain),
                        selectedChain.address,
                        onBindWithdrawMsg(),
                        txFee,
                        txMemo
                    )
                }

                LendActionType.BORROW -> {
                    txViewModel.simulateLendBorrow(
                        getChannel(selectedChain),
                        selectedChain.address,
                        onBindBorrowMsg(),
                        txFee,
                        txMemo
                    )
                }

                LendActionType.REPAY -> {
                    txViewModel.simulateLendRepay(
                        getChannel(selectedChain),
                        selectedChain.address,
                        onBindRepayMsg(),
                        txFee,
                        txMemo
                    )
                }
            }
        }
    }

    private fun onBindDepositMsg(): MsgDeposit? {
        val depositCoin =
            CoinProto.Coin.newBuilder().setDenom(lendMoneyMarket?.denom).setAmount(toLendAmount).build()
        return MsgDeposit.newBuilder()
            .setDepositor(selectedChain.address)
            .addAmount(depositCoin)
            .build()
    }

    private fun onBindWithdrawMsg(): MsgWithdraw? {
        val withdrawCoin =
            CoinProto.Coin.newBuilder().setDenom(lendMoneyMarket?.denom).setAmount(toLendAmount).build()
        return MsgWithdraw.newBuilder()
            .setDepositor(selectedChain.address)
            .addAmount(withdrawCoin)
            .build()
    }

    private fun onBindBorrowMsg(): MsgBorrow? {
        val borrowCoin =
            CoinProto.Coin.newBuilder().setDenom(lendMoneyMarket?.denom).setAmount(toLendAmount).build()
        return MsgBorrow.newBuilder()
            .setBorrower(selectedChain.address)
            .addAmount(borrowCoin)
            .build()
    }

    private fun onBindRepayMsg(): MsgRepay? {
        val repayCoin =
            CoinProto.Coin.newBuilder().setDenom(lendMoneyMarket?.denom).setAmount(toLendAmount).build()
        return MsgRepay.newBuilder()
            .setSender(selectedChain.address)
            .setOwner(selectedChain.address)
            .addAmount(repayCoin)
            .build()
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
        binding.btnLend.updateButtonView(isSuccess)
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

enum class LendActionType { DEPOSIT, WITHDRAW, BORROW, REPAY }