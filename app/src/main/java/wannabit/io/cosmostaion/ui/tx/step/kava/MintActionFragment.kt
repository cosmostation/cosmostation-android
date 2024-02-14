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
import com.kava.cdp.v1beta1.GenesisProto.CollateralParam
import com.kava.cdp.v1beta1.QueryProto.CDPResponse
import com.kava.cdp.v1beta1.TxProto.MsgDeposit
import com.kava.cdp.v1beta1.TxProto.MsgDrawDebt
import com.kava.cdp.v1beta1.TxProto.MsgRepayDebt
import com.kava.cdp.v1beta1.TxProto.MsgWithdraw
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.amountHandlerLeft
import wannabit.io.cosmostaion.common.debtAmount
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.common.liquidationRatioAmount
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.res.Asset
import wannabit.io.cosmostaion.data.model.res.FeeInfo
import wannabit.io.cosmostaion.databinding.FragmentMintActionBinding
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

class MintActionFragment : BaseTxFragment() {

    private var _binding: FragmentMintActionBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: CosmosLine
    private lateinit var mintActionType: MintActionType
    private lateinit var collateralParam: CollateralParam
    private lateinit var myCdp: CDPResponse

    private var feeInfos: MutableList<FeeInfo> = mutableListOf()
    private var selectedFeeInfo = 0
    private var txFee: TxProto.Fee? = null
    private var txMemo = ""

    private var collateralAsset: Asset? = null
    private var principalAsset: Asset? = null
    private var collateralAvailableAmount = BigDecimal.ZERO
    private var principalAvailableAmount = BigDecimal.ZERO
    private var toCollateralAmount = ""
    private var toPrincipalAmount = ""

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(
            selectedChain: CosmosLine,
            mintActionType: MintActionType,
            collateralParam: CollateralParam?,
            myCdp: CDPResponse?
        ): MintActionFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putSerializable("mintActionType", mintActionType)
                putSerializable("collateralParam", collateralParam)
                putSerializable("myCdp", myCdp)
            }
            val fragment = MintActionFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMintActionBinding.inflate(layoutInflater, container, false)
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
                arguments?.apply {
                    getParcelable("selectedChain", CosmosLine::class.java)?.let {
                        selectedChain = it
                    }
                    getSerializable(
                        "mintActionType", MintActionType::class.java
                    )?.let { mintActionType = it }
                    getSerializable(
                        "collateralParam", CollateralParam::class.java
                    )?.let { collateralParam = it }
                    getSerializable(
                        "myCdp", CDPResponse::class.java
                    )?.let { myCdp = it }

                }

            } else {
                arguments?.apply {
                    (getParcelable("selectedChain") as? CosmosLine)?.let {
                        selectedChain = it
                    }
                    (getSerializable("mintActionType") as? MintActionType)?.let {
                        mintActionType = it
                    }
                    (getSerializable("collateralParam") as? CollateralParam)?.let {
                        collateralParam = it
                    }
                    (getSerializable("priceFeed") as? CDPResponse)?.let {
                        myCdp = it
                    }
                }
            }

            listOf(
                mintAmountView, memoView, feeView
            ).forEach { it.setBackgroundResource(R.drawable.cell_bg) }
            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)

            collateralParam.let { collateralParam ->
                collateralAsset = BaseData.getAsset(selectedChain.apiName, collateralParam.denom)
                principalAsset = BaseData.getAsset(selectedChain.apiName, "usdx")

                when (mintActionType) {
                    MintActionType.DEPOSIT -> {
                        mintAmountTitle.text = getString(R.string.title_vault_deposit_amount)
                        btnMint.text = getString(R.string.str_deposit)
                        collateralAsset?.let { asset ->
                            mintActionTitle.text = getString(R.string.title_deposit_collateral)
                            tokenImg.setTokenImg(asset)
                            tokenName.text = asset.symbol
                            val balanceAmount = selectedChain.balanceAmount(collateralParam.denom)
                            if (txFee?.getAmount(0)?.denom == collateralParam.denom) {
                                val feeAmount = txFee?.getAmount(0)?.amount?.toBigDecimal()
                                collateralAvailableAmount = balanceAmount.subtract(feeAmount)
                            }
                            collateralAvailableAmount = balanceAmount
                        }
                    }

                    MintActionType.WITHDRAW -> {
                        mintAmountTitle.text = getString(R.string.title_vault_withdraw_amount)
                        btnMint.text = getString(R.string.str_withdraw)
                        collateralAsset?.let { asset ->
                            mintActionTitle.text = getString(R.string.title_withdraw_collateral)
                            tokenImg.setTokenImg(asset)
                            tokenName.text = asset.symbol
                            collateralAvailableAmount = myCdp.collateral?.amount?.toBigDecimal()
                        }
                    }

                    MintActionType.BORROW -> {
                        mintAmountTitle.text = getString(R.string.title_borrow_amount)
                        btnMint.text = getString(R.string.str_borrow)
                        principalAsset?.let { asset ->
                            mintActionTitle.text = getString(R.string.title_borrow_usdx)
                            tokenImg.setTokenImg(asset)
                            tokenName.text = asset.symbol
                            val padding = BigDecimal("0.95")
                            val collateralValue = myCdp.collateralValue?.amount
                            val ltvAmount = collateralValue?.toBigDecimal()?.divide(
                                collateralParam.liquidationRatioAmount(), 0, RoundingMode.DOWN
                            )?.multiply(padding)?.setScale(0, RoundingMode.DOWN)
                            val currentBorrowed = myCdp.debtAmount()
                            ltvAmount?.let {
                                if (it.subtract(currentBorrowed) > BigDecimal.ZERO) {
                                    principalAvailableAmount = it.subtract(currentBorrowed)
                                }
                            }
                        }
                    }

                    MintActionType.REPAY -> {
                        mintAmountTitle.text = getString(R.string.title_repay_amount)
                        btnMint.text = getString(R.string.str_repay)
                        principalAsset?.let { asset ->
                            mintActionTitle.text = getString(R.string.title_repay_usdx)
                            tokenImg.setTokenImg(asset)
                            tokenName.text = asset.symbol
                            principalAvailableAmount = selectedChain.balanceAmount("usdx")
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

            when (mintActionType) {
                MintActionType.DEPOSIT, MintActionType.WITHDRAW -> {
                    toCollateralAmount = toAmount
                    collateralAsset?.let { asset ->
                        asset.decimals?.let { decimal ->
                            val dpAmount = BigDecimal(toAmount).movePointLeft(decimal)
                                .setScale(decimal, RoundingMode.DOWN)
                            val price = BaseData.getPrice(asset.coinGeckoId)
                            val value = price.multiply(dpAmount)

                            mintAmount.text = formatAmount(dpAmount.toPlainString(), decimal)
                            mintValue.text = formatAssetValue(value)
                        }
                    }
                }

                else -> {
                    toPrincipalAmount = toAmount
                    principalAsset?.let { asset ->
                        asset.decimals?.let { decimal ->
                            val dpAmount = BigDecimal(toAmount).movePointLeft(decimal)
                                .setScale(decimal, RoundingMode.DOWN)
                            val price = BaseData.getPrice(asset.coinGeckoId)
                            val value = price.multiply(dpAmount)

                            mintAmount.text = formatAmount(dpAmount.toPlainString(), decimal)
                            mintValue.text = formatAssetValue(value)
                        }
                    }
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

    private fun setUpClickAction() {
        binding.apply {
            mintAmountView.setOnClickListener {
                when (mintActionType) {
                    MintActionType.DEPOSIT -> {
                        handleOneClickWithDelay(
                            InsertAmountFragment(
                                TxType.MINT_DEPOSIT,
                                null,
                                collateralAvailableAmount,
                                toCollateralAmount,
                                collateralAsset,
                                null,
                                object : AmountSelectListener {
                                    override fun select(toAmount: String) {
                                        updateAmountView(toAmount)
                                    }
                                })
                        )
                    }

                    MintActionType.WITHDRAW -> {
                        handleOneClickWithDelay(
                            InsertAmountFragment(
                                TxType.MINT_WITHDRAW,
                                null,
                                collateralAvailableAmount,
                                toCollateralAmount,
                                collateralAsset,
                                null,
                                object : AmountSelectListener {
                                    override fun select(toAmount: String) {
                                        updateAmountView(toAmount)
                                    }
                                })
                        )
                    }

                    MintActionType.BORROW -> {
                        handleOneClickWithDelay(
                            InsertAmountFragment(
                                TxType.MINT_BORROW,
                                null,
                                principalAvailableAmount,
                                toPrincipalAmount,
                                principalAsset,
                                null,
                                object : AmountSelectListener {
                                    override fun select(toAmount: String) {
                                        updateAmountView(toAmount)
                                    }
                                })
                        )
                    }

                    MintActionType.REPAY -> {
                        handleOneClickWithDelay(
                            InsertAmountFragment(
                                TxType.MINT_REPAY,
                                null,
                                principalAvailableAmount,
                                toPrincipalAmount,
                                principalAsset,
                                null,
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
                    MemoFragment(txMemo, object : MemoListener {
                        override fun memo(memo: String) {
                            updateMemoView(memo)
                        }

                    })
                )
            }

            feeTokenLayout.setOnClickListener {
                handleOneClickWithDelay(
                    AssetFragment(selectedChain,
                        feeInfos[selectedFeeInfo].feeDatas,
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

            btnMint.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    getMintResultLauncher.launch(this)
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

    private val getMintResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE

                when (mintActionType) {
                    MintActionType.DEPOSIT -> {
                        txViewModel.broadMintDeposit(
                            getChannel(selectedChain),
                            selectedChain.address,
                            onBindDepositMsg(),
                            txFee,
                            txMemo,
                            selectedChain
                        )
                    }

                    MintActionType.WITHDRAW -> {
                        txViewModel.broadMintWithdraw(
                            getChannel(selectedChain),
                            selectedChain.address,
                            onBindWithdrawMsg(),
                            txFee,
                            txMemo,
                            selectedChain
                        )
                    }

                    MintActionType.BORROW -> {
                        txViewModel.broadMintBorrow(
                            getChannel(selectedChain),
                            selectedChain.address,
                            onBindBorrowMsg(),
                            txFee,
                            txMemo,
                            selectedChain
                        )
                    }

                    MintActionType.REPAY -> {
                        txViewModel.broadMintRepay(
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

    private fun txSimulate() {
        binding.apply {
            if (!selectedChain.isGasSimulable()) {
                return updateFeeViewWithSimulate(null)
            }

            when (mintActionType) {
                MintActionType.DEPOSIT -> {
                    if (toCollateralAmount.isEmpty()) {
                        return
                    }
                    if (toCollateralAmount.toBigDecimal() == BigDecimal.ZERO) {
                        return
                    }

                    btnMint.updateButtonView(false)
                    backdropLayout.visibility = View.VISIBLE
                    txViewModel.simulateMintDeposit(
                        getChannel(selectedChain),
                        selectedChain.address,
                        onBindDepositMsg(),
                        txFee,
                        txMemo
                    )
                }

                MintActionType.WITHDRAW -> {
                    if (toCollateralAmount.isEmpty()) {
                        return
                    }
                    if (toCollateralAmount.toBigDecimal() == BigDecimal.ZERO) {
                        return
                    }

                    backdropLayout.visibility = View.VISIBLE
                    txViewModel.simulateMintWithdraw(
                        getChannel(selectedChain),
                        selectedChain.address,
                        onBindWithdrawMsg(),
                        txFee,
                        txMemo
                    )
                }

                MintActionType.BORROW -> {
                    if (toPrincipalAmount.isEmpty()) {
                        return
                    }
                    if (toPrincipalAmount.toBigDecimal() == BigDecimal.ZERO) {
                        return
                    }

                    backdropLayout.visibility = View.VISIBLE
                    txViewModel.simulateMintBorrow(
                        getChannel(selectedChain),
                        selectedChain.address,
                        onBindBorrowMsg(),
                        txFee,
                        txMemo
                    )
                }

                MintActionType.REPAY -> {
                    if (toPrincipalAmount.isEmpty()) {
                        return
                    }
                    if (toPrincipalAmount.toBigDecimal() == BigDecimal.ZERO) {
                        return
                    }

                    backdropLayout.visibility = View.VISIBLE
                    txViewModel.simulateMintRepay(
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
        val collateralCoin = CoinProto.Coin.newBuilder().setDenom(collateralParam.denom)
            .setAmount(toCollateralAmount).build()
        return MsgDeposit.newBuilder().setOwner(selectedChain.address)
            .setDepositor(selectedChain.address).setCollateral(collateralCoin)
            .setCollateralType(collateralParam.type).build()
    }

    private fun onBindWithdrawMsg(): MsgWithdraw? {
        val collateralCoin = CoinProto.Coin.newBuilder().setDenom(collateralParam.denom)
            .setAmount(toCollateralAmount).build()
        return MsgWithdraw.newBuilder().setOwner(selectedChain.address)
            .setDepositor(selectedChain.address).setCollateral(collateralCoin)
            .setCollateralType(collateralParam.type).build()
    }

    private fun onBindBorrowMsg(): MsgDrawDebt? {
        val principalCoin =
            CoinProto.Coin.newBuilder().setDenom("usdx").setAmount(toPrincipalAmount).build()
        return MsgDrawDebt.newBuilder().setSender(selectedChain.address).setPrincipal(principalCoin)
            .setCollateralType(collateralParam.type).build()
    }

    private fun onBindRepayMsg(): MsgRepayDebt? {
        val paymentCoin =
            CoinProto.Coin.newBuilder().setDenom("usdx").setAmount(toPrincipalAmount).build()
        return MsgRepayDebt.newBuilder().setSender(selectedChain.address).setPayment(paymentCoin)
            .setCollateralType(collateralParam.type).build()
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
        binding.btnMint.updateButtonView(isSuccess)
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

enum class MintActionType { DEPOSIT, WITHDRAW, BORROW, REPAY }