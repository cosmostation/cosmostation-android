package wannabit.io.cosmostaion.ui.option.tx.general

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.handlerRight
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.res.Asset
import wannabit.io.cosmostaion.databinding.FragmentInsertAmountBinding
import wannabit.io.cosmostaion.ui.tx.step.SendAssetType
import wannabit.io.cosmostaion.ui.tx.step.TransferStyle
import java.math.BigDecimal
import java.math.RoundingMode

class TransferAmountFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentInsertAmountBinding? = null
    private val binding get() = _binding!!

    private lateinit var fromChain: BaseChain
    private var toSendAsset: Asset? = null
    private var availableAmount = ""
    private var existAmount = ""
    private lateinit var sendAssetType: SendAssetType
    private lateinit var transferType: TransferStyle

    private var assetDecimal: Int = 6

    companion object {
        @JvmStatic
        fun newInstance(
            fromChain: BaseChain,
            toSendAsset: Asset?,
            availableAmount: String,
            existAmount: String,
            sendAssetType: SendAssetType,
            transferType: TransferStyle,
            listener: AmountSelectListener
        ): TransferAmountFragment {
            val args = Bundle().apply {
                putSerializable("fromChain", fromChain)
                putParcelable("toSendAsset", toSendAsset)
                putString("availableAmount", availableAmount)
                putString("existAmount", existAmount)
                putSerializable("sendAssetType", sendAssetType)
                putSerializable("transferType", transferType)
            }
            val fragment = TransferAmountFragment()
            fragment.arguments = args
            fragment.amountSelectListener = listener
            return fragment
        }
    }

    private var amountSelectListener: AmountSelectListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInsertAmountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        textChange()
        updateAmountView()
        setUpClickAction()
    }

    private fun initView() {
        binding.apply {
            arguments?.apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    getSerializable(
                        "fromChain", BaseChain::class.java
                    )?.let { fromChain = it }
                    getParcelable("toSendAsset", Asset::class.java)?.let { toSendAsset = it }
                    getSerializable(
                        "sendAssetType", SendAssetType::class.java
                    )?.let { sendAssetType = it }
                    getSerializable(
                        "transferType", TransferStyle::class.java
                    )?.let { transferType = it }

                } else {
                    (getSerializable("fromChain") as? BaseChain)?.let {
                        fromChain = it
                    }
                    (getParcelable("toSendAsset") as? Asset)?.let {
                        toSendAsset = it
                    }
                    (getSerializable("sendAssetType") as? SendAssetType)?.let {
                        sendAssetType = it
                    }
                    (getSerializable("transferType") as? TransferStyle)?.let {
                        transferType = it
                    }
                }
                getString("availableAmount")?.let { availableAmount = it }
                getString("existAmount")?.let { existAmount = it }

                initData()
            }
        }
    }

    private fun initData() {
        binding.apply {
            when (sendAssetType) {
                SendAssetType.COSMOS_EVM_COIN -> {
                    if (transferType == TransferStyle.WEB3_STYLE) {

                    } else {
                        toSendAsset?.let { asset ->
                            assetDecimal = asset.decimals ?: 6
                            availableAmount.toBigDecimal().movePointLeft(assetDecimal)
                                ?.setScale(assetDecimal, RoundingMode.DOWN)?.let { amount ->
                                    available.text = formatAmount(amount.toPlainString(), assetDecimal)
                                    availableDenom.text = asset.symbol
                                    availableDenom.setTextColor(asset.assetColor())
                                }
                        }
                    }
                }

                SendAssetType.ONLY_COSMOS_COIN -> {
                    toSendAsset?.let { asset ->
                        assetDecimal = asset.decimals ?: 6
                        availableAmount.toBigDecimal().movePointLeft(assetDecimal)
                            ?.setScale(assetDecimal, RoundingMode.DOWN)?.let { amount ->
                                available.text = formatAmount(amount.toPlainString(), assetDecimal)
                                availableDenom.text = asset.symbol
                                availableDenom.setTextColor(asset.assetColor())
                            }
                    }
                }

                else -> { }
            }

            existAmount.let { toAmount ->
                if (toAmount.isNotEmpty()) {
                    val dpToSendAmount = toAmount.toBigDecimal().movePointLeft(assetDecimal)
                        .setScale(assetDecimal, RoundingMode.DOWN).stripTrailingZeros()
                        .toPlainString()
                    amountTxt.text =
                        Editable.Factory.getInstance().newEditable(dpToSendAmount)
                } else {
                    amountTxt.text = Editable.Factory.getInstance().newEditable(toAmount)
                }
            }
        }
    }

    private fun textChange() {
        binding.apply {
            amountTxt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?, start: Int, count: Int, after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    val userInput = s.toString()
                    if (userInput.startsWith(".")) {
                        amountTxt.setText("")
                        return

                    } else if (userInput.contains(".")) {
                        val decimalPlaces: Int = userInput.length - userInput.indexOf(".") - 1
                        if (decimalPlaces == assetDecimal) {
                            if (userInput.toBigDecimal()
                                    .handlerRight(assetDecimal, 0) == BigDecimal.ZERO
                            ) {
                                s?.delete(s.length - 1, s.length)
                            }
                        } else if (decimalPlaces > assetDecimal) {
                            s?.delete(s.length - 1, s.length)
                        }
                    }
                    updateAmountView()
                }
            })
        }
    }

    private fun updateAmountView() {
        binding.apply {
            amountTxt.text.toString().trim().let { text ->
                if (text.isEmpty()) {
                    editLayout.error = null
                    invalidMsg.visibility = View.GONE
                    btnConfirm.updateButtonView(false)
                    return
                }

                if (text.toBigDecimal().handlerRight(assetDecimal, 0) == BigDecimal.ZERO) {
                    editLayout.error = getString(R.string.error_invalid_amount)
                    invalidMsg.visibility = View.VISIBLE
                    btnConfirm.updateButtonView(false)
                    return
                }

                BigDecimal(text).apply {
                    availableAmount.toBigDecimal().movePointLeft(assetDecimal)
                        ?.setScale(assetDecimal, RoundingMode.DOWN)?.let { amount ->
                            if (this != BigDecimal.ZERO && amount >= this) {
                                editLayout.error = null
                                invalidMsg.visibility = View.GONE
                                btnConfirm.isEnabled = true
                                btnConfirm.updateButtonView(true)

                            } else {
                                editLayout.error = getString(R.string.error_invalid_amount)
                                invalidMsg.visibility = View.VISIBLE
                                btnConfirm.isEnabled = false
                                btnConfirm.updateButtonView(false)
                            }
                            amountTxt.setSelection(text.length)
                        }
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnQuarter.setOnClickListener {
                val quarterAmount = availableAmount.toBigDecimal().multiply(BigDecimal(0.25))
                    .movePointLeft(assetDecimal).setScale(assetDecimal, RoundingMode.DOWN)
                amountTxt.text =
                    Editable.Factory.getInstance().newEditable(quarterAmount.toPlainString())
                updateAmountView()
            }

            btnHalf.setOnClickListener {
                val halfAmount = availableAmount.toBigDecimal().multiply(BigDecimal(0.5))
                    .movePointLeft(assetDecimal).setScale(assetDecimal, RoundingMode.DOWN)
                amountTxt.text =
                    Editable.Factory.getInstance().newEditable(halfAmount.toPlainString())
                updateAmountView()
            }

            btnMax.setOnClickListener {
                val maxAmount = availableAmount.toBigDecimal().movePointLeft(assetDecimal)
                    .setScale(assetDecimal, RoundingMode.DOWN)
                amountTxt.text =
                    Editable.Factory.getInstance().newEditable(maxAmount.toPlainString())
                updateAmountView()
            }

            btnConfirm.setOnClickListener {
                val originalAmount =
                    amountTxt.text.toString().trim().toBigDecimal().movePointRight(assetDecimal)
                        .setScale(0, RoundingMode.DOWN)
                amountSelectListener?.select(originalAmount.toPlainString())
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}