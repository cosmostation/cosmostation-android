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
import com.kava.hard.v1beta1.HardProto
import com.kava.hard.v1beta1.TxProto.MsgBorrow
import com.kava.hard.v1beta1.TxProto.MsgDeposit
import com.kava.hard.v1beta1.TxProto.MsgRepay
import com.kava.hard.v1beta1.TxProto.MsgWithdraw
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
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
import wannabit.io.cosmostaion.data.model.res.Asset
import wannabit.io.cosmostaion.data.model.res.FeeInfo
import wannabit.io.cosmostaion.databinding.FragmentLendActionBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.ui.main.chain.cosmos.TxType
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.tx.option.general.AmountSelectListener
import wannabit.io.cosmostaion.ui.tx.option.general.AssetFragment
import wannabit.io.cosmostaion.ui.tx.option.general.AssetSelectListener
import wannabit.io.cosmostaion.ui.tx.option.general.InsertAmountFragment
import wannabit.io.cosmostaion.ui.tx.option.general.MemoFragment
import wannabit.io.cosmostaion.ui.tx.option.general.MemoListener
import wannabit.io.cosmostaion.ui.tx.genTx.BaseTxFragment
import java.math.BigDecimal
import java.math.RoundingMode

class LendActionFragment : BaseTxFragment() {

    private var _binding: FragmentLendActionBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain
    private lateinit var lendActionType: LendActionType
    private lateinit var lendMyDeposits: MutableList<CoinProto.Coin>
    private lateinit var lendMyBorrows: MutableList<CoinProto.Coin>
    private lateinit var lendMoneyMarket: HardProto.MoneyMarket
    private lateinit var borrowAbleAmount: BigDecimal

    private var feeInfos: MutableList<FeeInfo> = mutableListOf()
    private var selectedFeeInfo = 0
    private var txFee: TxProto.Fee? = null
    private var txMemo = ""

    private var msAsset: Asset? = null
    private var availableAmount = BigDecimal.ZERO
    private var toLendAmount = ""

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(
            selectedChain: BaseChain,
            lendActionType: LendActionType,
            lendMyDeposits: MutableList<CoinProto.Coin>,
            lendMyBorrows: MutableList<CoinProto.Coin>,
            lendMoneyMarket: HardProto.MoneyMarket?,
            borrowAbleAmount: String
        ): LendActionFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putSerializable("lendActionType", lendActionType)
                putSerializable("lendMyDeposits", lendMyDeposits.toHashSet())
                putSerializable("lendMyBorrows", lendMyBorrows.toHashSet())
                putSerializable("lendMoneyMarket", lendMoneyMarket)
                putString("borrowAbleAmount", borrowAbleAmount)
            }
            val fragment = LendActionFragment()
            fragment.arguments = args
            return fragment
        }
    }

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
        setUpClickAction()
        setUpSimulate()
        setUpBroadcast()
    }

    private fun initView() {
        binding.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.apply {
                    getParcelable("selectedChain", BaseChain::class.java)?.let {
                        selectedChain = it
                    }
                    getSerializable(
                        "lendActionType", LendActionType::class.java
                    )?.let { lendActionType = it }
                    getSerializable(
                        "lendMoneyMarket", HardProto.MoneyMarket::class.java
                    )?.let { lendMoneyMarket = it }
                }

            } else {
                arguments?.apply {
                    (getParcelable("selectedChain") as? BaseChain)?.let {
                        selectedChain = it
                    }
                    (getSerializable("lendActionType") as? LendActionType)?.let {
                        lendActionType = it
                    }
                    (getSerializable("lendMoneyMarket") as? HardProto.MoneyMarket)?.let {
                        lendMoneyMarket = it
                    }
                }
            }
            val serializableDepositList =
                arguments?.getSerializable("lendMyDeposits") as? HashSet<*>
            if (serializableDepositList?.isNotEmpty() == true) {
                lendMyDeposits = serializableDepositList.toList() as MutableList<CoinProto.Coin>
            }

            val serializableBorrowList = arguments?.getSerializable("lendMyBorrows") as? HashSet<*>
            if (serializableBorrowList?.isNotEmpty() == true) {
                lendMyBorrows = serializableBorrowList.toList() as MutableList<CoinProto.Coin>
            }
            arguments?.getString("borrowAbleAmount")?.toBigDecimal()?.let {
                borrowAbleAmount = it
            }

            listOf(
                lendAmountView, memoView, feeView
            ).forEach { it.setBackgroundResource(R.drawable.cell_bg) }
            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)

            lendMoneyMarket.let { market ->
                BaseData.getAsset(selectedChain.apiName, market.denom)?.let { asset ->
                    msAsset = asset
                    tokenImg.setTokenImg(asset)
                    tokenName.text = asset.symbol

                    when (lendActionType) {
                        LendActionType.DEPOSIT -> {
                            lendActionTitle.text = getString(R.string.title_lend_deposit)
                            lendAmountTitle.text = getString(R.string.title_vault_deposit_amount)
                            btnLend.text = getString(R.string.str_deposit)
                            val balanceAmount =
                                selectedChain.cosmosFetcher?.balanceAmount(market.denom)
                            if (txFee?.getAmount(0)?.denom == market.denom) {
                                val feeAmount = txFee?.getAmount(0)?.amount?.toBigDecimal()
                                availableAmount = balanceAmount?.subtract(feeAmount)
                            }
                            availableAmount = balanceAmount
                        }

                        LendActionType.WITHDRAW -> {
                            lendActionTitle.text = getString(R.string.title_lend_withdraw)
                            lendAmountTitle.text = getString(R.string.title_vault_withdraw_amount)
                            btnLend.text = getString(R.string.str_withdraw)
                            availableAmount =
                                lendMyDeposits.firstOrNull { it.denom == market.denom }?.amount?.toBigDecimal()
                                    ?: BigDecimal.ZERO
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
                            var borrowedAmount =
                                lendMyBorrows.firstOrNull { it.denom == market.denom }?.amount?.toBigDecimal()
                                    ?: BigDecimal.ZERO
                            borrowedAmount = borrowedAmount.multiply(BigDecimal("1.1"))
                                .setScale(0, RoundingMode.DOWN)

                            var balanceAmount =
                                selectedChain.cosmosFetcher?.balanceAmount(market.denom)
                            if (txFee?.getAmount(0)?.denom == market.denom) {
                                val feeAmount = txFee?.getAmount(0)?.amount?.toBigDecimal()
                                balanceAmount = balanceAmount?.subtract(feeAmount)
                            }
                            availableAmount = if (borrowedAmount < balanceAmount) {
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

                    lendAmount.text = formatAmount(dpAmount.toPlainString(), decimal)
                    lendValue.text = formatAssetValue(value)
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
            lendAmountView.setOnClickListener {
                when (lendActionType) {
                    LendActionType.DEPOSIT -> {
                        handleOneClickWithDelay(
                            InsertAmountFragment.newInstance(TxType.LEND_DEPOSIT,
                                availableAmount.toString(),
                                toLendAmount,
                                msAsset,
                                object : AmountSelectListener {
                                    override fun select(toAmount: String) {
                                        updateAmountView(toAmount)
                                    }

                                })
                        )
                    }

                    LendActionType.WITHDRAW -> {
                        handleOneClickWithDelay(
                            InsertAmountFragment.newInstance(TxType.LEND_WITHDRAW,
                                availableAmount.toString(),
                                toLendAmount,
                                msAsset,
                                object : AmountSelectListener {
                                    override fun select(toAmount: String) {
                                        updateAmountView(toAmount)
                                    }
                                })
                        )
                    }

                    LendActionType.BORROW -> {
                        handleOneClickWithDelay(
                            InsertAmountFragment.newInstance(TxType.LEND_BORROW,
                                availableAmount.toString(),
                                toLendAmount,
                                msAsset,
                                object : AmountSelectListener {
                                    override fun select(toAmount: String) {
                                        updateAmountView(toAmount)
                                    }
                                })
                        )
                    }

                    LendActionType.REPAY -> {
                        handleOneClickWithDelay(
                            InsertAmountFragment.newInstance(TxType.LEND_REPAY,
                                availableAmount.toString(),
                                toLendAmount,
                                msAsset,
                                object : AmountSelectListener {
                                    override fun select(toAmount: String) {
                                        updateAmountView(toAmount)
                                    }
                                })
                        )
                    }
                }
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

            btnLend.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    getLendResultLauncher.launch(this)
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

    private val getLendResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE

                when (lendActionType) {
                    LendActionType.DEPOSIT -> {
                        txViewModel.broadcast(
                            selectedChain.cosmosFetcher?.getChannel(),
                            onBindDepositMsg(),
                            txFee,
                            txMemo,
                            selectedChain
                        )
                    }

                    LendActionType.WITHDRAW -> {
                        txViewModel.broadcast(
                            selectedChain.cosmosFetcher?.getChannel(),
                            onBindWithdrawMsg(),
                            txFee,
                            txMemo,
                            selectedChain
                        )
                    }

                    LendActionType.BORROW -> {
                        txViewModel.broadcast(
                            selectedChain.cosmosFetcher?.getChannel(),
                            onBindBorrowMsg(),
                            txFee,
                            txMemo,
                            selectedChain
                        )
                    }

                    LendActionType.REPAY -> {
                        txViewModel.broadcast(
                            selectedChain.cosmosFetcher?.getChannel(),
                            onBindRepayMsg(),
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
            if (toLendAmount.isEmpty()) {
                return
            }
            if (toLendAmount.toBigDecimal() == BigDecimal.ZERO) {
                return
            }
            if (!selectedChain.isSimulable()) {
                return updateFeeViewWithSimulate(null)
            }
            btnLend.updateButtonView(false)
            backdropLayout.visibility = View.VISIBLE

            when (lendActionType) {
                LendActionType.DEPOSIT -> {
                    txViewModel.simulate(
                        selectedChain.cosmosFetcher?.getChannel(),
                        onBindDepositMsg(),
                        txFee,
                        txMemo,
                        selectedChain
                    )
                }

                LendActionType.WITHDRAW -> {
                    txViewModel.simulate(
                        selectedChain.cosmosFetcher?.getChannel(),
                        onBindWithdrawMsg(),
                        txFee,
                        txMemo,
                        selectedChain
                    )
                }

                LendActionType.BORROW -> {
                    txViewModel.simulate(
                        selectedChain.cosmosFetcher?.getChannel(),
                        onBindBorrowMsg(),
                        txFee,
                        txMemo,
                        selectedChain
                    )
                }

                LendActionType.REPAY -> {
                    txViewModel.simulate(
                        selectedChain.cosmosFetcher?.getChannel(),
                        onBindRepayMsg(),
                        txFee,
                        txMemo,
                        selectedChain
                    )
                }
            }
        }
    }

    private fun onBindDepositMsg(): MutableList<Any> {
        val depositCoin =
            CoinProto.Coin.newBuilder().setDenom(lendMoneyMarket.denom).setAmount(toLendAmount)
                .build()
        val msgDeposit =
            MsgDeposit.newBuilder().setDepositor(selectedChain.address).addAmount(depositCoin)
                .build()
        return Signer.lendDepositMsg(msgDeposit)
    }

    private fun onBindWithdrawMsg(): MutableList<Any> {
        val withdrawCoin =
            CoinProto.Coin.newBuilder().setDenom(lendMoneyMarket.denom).setAmount(toLendAmount)
                .build()
        val msgWithdraw =
            MsgWithdraw.newBuilder().setDepositor(selectedChain.address).addAmount(withdrawCoin)
                .build()
        return Signer.lendWithdrawMsg(msgWithdraw)
    }

    private fun onBindBorrowMsg(): MutableList<Any> {
        val borrowCoin =
            CoinProto.Coin.newBuilder().setDenom(lendMoneyMarket.denom).setAmount(toLendAmount)
                .build()
        val msgBorrow =
            MsgBorrow.newBuilder().setBorrower(selectedChain.address).addAmount(borrowCoin).build()
        return Signer.lendBorrowMsg(msgBorrow)
    }

    private fun onBindRepayMsg(): MutableList<Any> {
        val repayCoin =
            CoinProto.Coin.newBuilder().setDenom(lendMoneyMarket.denom).setAmount(toLendAmount)
                .build()
        val msgRepay =
            MsgRepay.newBuilder().setSender(selectedChain.address).setOwner(selectedChain.address)
                .addAmount(repayCoin).build()
        return Signer.lendRepayMsg(msgRepay)
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
        binding.btnLend.updateButtonView(isSuccess)
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

enum class LendActionType { DEPOSIT, WITHDRAW, BORROW, REPAY }