package wannabit.io.cosmostaion.ui.tx.genTx

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
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
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.amountHandlerLeft
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.getdAmount
import wannabit.io.cosmostaion.common.handlerRight
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.res.Asset
import wannabit.io.cosmostaion.data.model.res.FeeInfo
import wannabit.io.cosmostaion.databinding.FragmentCoinMintBinding
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

class CoinMintFragment : BaseTxFragment() {

    private var _binding: FragmentCoinMintBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain

    private lateinit var burnAsset: Asset
    private lateinit var mintAsset: Asset
    private var mintRate = BigDecimal.ZERO

    private var feeInfos: MutableList<FeeInfo> = mutableListOf()
    private var selectedFeeInfo = 0
    private var txFee: TxProto.Fee? = null

    private var toBurnAmount = ""
    private var txMemo = ""

    private var availableAmount = BigDecimal.ZERO

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): CoinMintFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = CoinMintFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinMintBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initFee()
        textChange()
        setUpClickAction()
        setUpObserve()
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

            listOf(burnLayout, mintLayout, memoView, feeView).forEach {
                it.setBackgroundResource(
                    R.drawable.cell_bg
                )
            }
            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)

            BaseData.getAsset(selectedChain.apiName, selectedChain.stakeDenom)?.let { asset ->
                burnAsset = asset
                burnCoinImg.setTokenImg(asset)
                burnCoinName.text = asset.symbol
                burnAvailableDenom.text = asset.symbol
            }

            BaseData.getAsset(selectedChain.apiName, "uphoton")?.let { asset ->
                mintAsset = asset
                mintCoinImg.setTokenImg(asset)
                mintCoinName.text = asset.symbol
                val dpMintAmount = selectedChain.cosmosFetcher?.balanceAmount(asset.denom ?: "")
                    ?.movePointLeft(asset.decimals ?: 6)
                    ?.setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                mintAvailable.text = formatAmount(dpMintAmount.toString(), asset.decimals ?: 6)
                mintAvailableDenom.text = asset.symbol
            }

            iconWallet.colorFilter = PorterDuffColorFilter(
                ContextCompat.getColor(
                    requireContext(), R.color.color_base02
                ), PorterDuff.Mode.SRC_IN
            )

            iconWallet2.colorFilter = PorterDuffColorFilter(
                ContextCompat.getColor(
                    requireContext(), R.color.color_base02
                ), PorterDuff.Mode.SRC_IN
            )

            mintImg.colorFilter = PorterDuffColorFilter(
                ContextCompat.getColor(
                    requireContext(), R.color.color_sub_yellow
                ), PorterDuff.Mode.SRC_IN
            )

            txViewModel.mintPhotonRate(selectedChain)
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

    private fun textChange() {
        binding.burnAmountTxt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val userInput = s.toString()
                if (userInput.startsWith(".")) {
                    binding.burnAmountTxt.setText("")
                    binding.burnAmountValue.text = ""
                    return

                } else if (userInput.contains(".")) {
                    val decimalPlaces: Int = userInput.length - userInput.indexOf(".") - 1
                    if (decimalPlaces == burnAsset.decimals) {
                        if (userInput.toBigDecimal()
                                .handlerRight(burnAsset.decimals ?: 6, 0) == BigDecimal.ZERO
                        ) {
                            s?.delete(s.length - 1, s.length)
                        }
                    } else if (decimalPlaces > (burnAsset.decimals ?: 6)) {
                        s?.delete(s.length - 1, s.length)
                    }
                }
                updateAmountView()
            }
        })
    }

    private fun updateAmountView() {
        binding.apply {
            btnMint.updateButtonView(false)
            val burnText = burnAmountTxt.text.toString().trim()

            if (burnText.isEmpty()) {
                mintAmount.text = ""
                mintAmountValue.text = ""
                return
            }

            val dpBurnAmount = burnText.toBigDecimal().movePointRight(burnAsset.decimals ?: 6)
                .setScale(0, RoundingMode.DOWN)
            toBurnAmount = dpBurnAmount.toPlainString()

            if (dpBurnAmount == BigDecimal.ZERO || availableAmount < dpBurnAmount) {
                mintAmount.text = ""
                mintAmountValue.text = ""
                invalidMsg.visibility = View.VISIBLE
                return
            }
            invalidMsg.visibility = View.INVISIBLE

            val burnPrice = BaseData.getPrice(burnAsset.coinGeckoId)
            val burnValue = burnPrice.multiply(burnText.toBigDecimal())
            burnAmountValue.text = formatAssetValue(burnValue)

            val mintAmountTxt = burnText.toBigDecimal().multiply(mintRate)
                .setScale(mintAsset.decimals ?: 6, RoundingMode.DOWN)
            val mintPrice = BaseData.getPrice(mintAsset.coinGeckoId)
            val mintValue = mintPrice.multiply(mintAmountTxt)
            mintAmount.text = formatAmount(mintAmountTxt.toPlainString(), mintAsset.decimals ?: 6)
            mintAmountValue.text = formatAssetValue(mintValue)

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

                    val balanceAmount =
                        selectedChain.cosmosFetcher?.balanceAmount(selectedChain.stakeDenom)

                    txFee?.let {
                        availableAmount = if (it.getAmount(0).denom == selectedChain.stakeDenom) {
                            val feeAmount = it.getAmount(0).amount.toBigDecimal()
                            if (feeAmount > balanceAmount) {
                                BigDecimal.ZERO
                            } else {
                                balanceAmount?.subtract(feeAmount)
                            }
                        } else {
                            balanceAmount
                        }
                    }

                    val dpAvailableAmount = availableAmount.movePointLeft(asset.decimals ?: 6)
                        .setScale(burnAsset.decimals ?: 6, RoundingMode.DOWN)
                    burnAvailable.text =
                        formatAmount(dpAvailableAmount.toPlainString(), asset.decimals ?: 6)
                }
            }
        }
    }

    private fun setUpObserve() {
        txViewModel.conversionRateResult.observe(viewLifecycleOwner) { rate ->
            binding.apply {
                loading.visibility = View.GONE
                txLayout.visibility = View.VISIBLE
                mintRate = rate?.toBigDecimal()
                val dpMintRate = mintRate.setScale(2, RoundingMode.DOWN)
                mintingRateTxt.text = "1 ATONE ≈ " + dpMintRate + " PHOTON"
            }
        }
    }

    @SuppressLint("WrongConstant")
    private fun setUpClickAction() {
        binding.apply {
            btnHalf.setOnClickListener {
                val halfAmount =
                    availableAmount.multiply(BigDecimal(0.5)).movePointLeft(burnAsset.decimals ?: 6)
                        .setScale(burnAsset.decimals ?: 6, RoundingMode.DOWN)
                burnAmountTxt.setText(halfAmount.toPlainString())
                updateAmountView()
                if (halfAmount > BigDecimal.ZERO) {
                    burnAmountTxt.setSelection(halfAmount.toPlainString().length)
                } else {
                    burnAmountTxt.setSelection(halfAmount.toPlainString().length - 1)
                }
            }

            btnMax.setOnClickListener {
                val maxAmount = availableAmount.movePointLeft(burnAsset.decimals ?: 6)
                    .setScale(burnAsset.decimals ?: 6, RoundingMode.DOWN)
                burnAmountTxt.setText(maxAmount.toPlainString())
                updateAmountView()
                if (maxAmount > BigDecimal.ZERO) {
                    burnAmountTxt.setSelection(maxAmount.toPlainString().length)
                } else {
                    burnAmountTxt.setSelection(maxAmount.toPlainString().length - 1)
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
                txFee?.let { fee ->
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

            btnMint.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    coinMintResultLauncher.launch(this)
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

    private val coinMintResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
                txViewModel.broadcast(
                    selectedChain.cosmosFetcher?.getChannel(),
                    onBindMintPhotonMsg(),
                    txFee,
                    txMemo,
                    selectedChain
                )
            }
        }

    private fun txSimulate() {
        binding.apply {
            val burnText = burnAmountTxt.text.toString().trim()
            if (burnAmountTxt.text.isEmpty() || burnText.toBigDecimal() <= BigDecimal.ZERO) {
                return
            }
            if (!selectedChain.isSimulable()) {
                return updateFeeViewWithSimulate(null)
            }
            btnMint.updateButtonView(false)
            txViewModel.simulate(
                selectedChain.cosmosFetcher?.getChannel(),
                onBindMintPhotonMsg(),
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
        binding.btnMint.updateButtonView(isSuccess)
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

    private fun onBindMintPhotonMsg(): MutableList<Any> {
        val burnCoin =
            CoinProto.Coin.newBuilder().setAmount(toBurnAmount).setDenom(selectedChain.stakeDenom)
                .build()
        val msgMint = com.atomone.photon.v1.TxProto.MsgMintPhoton.newBuilder()
            .setToAddress(selectedChain.address).setAmount(burnCoin).build()
        return Signer.mintPhotonMsg(msgMint)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}