package wannabit.io.cosmostaion.ui.option.tx.kava

import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.handlerRight
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.databinding.FragmentBep3InsertAmountBinding
import wannabit.io.cosmostaion.ui.option.tx.general.AmountSelectListener
import java.math.BigDecimal
import java.math.RoundingMode

class Bep3InsertAmountFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBep3InsertAmountBinding? = null
    private val binding get() = _binding!!

    private lateinit var fromChain: CosmosLine
    private var toSendDenom = ""
    private var minAvailableAmount = ""
    private var availAmount = ""
    private var toAmount = ""

    private var assetDecimal: Int = 6

    companion object {
        @JvmStatic
        fun newInstance(
            fromChain: CosmosLine,
            toSendDenom: String,
            minAvailableAmount: String?,
            availAmount: String?,
            toAmount: String?,
            listener: AmountSelectListener
        ): Bep3InsertAmountFragment {
            val args = Bundle().apply {
                putParcelable("fromChain", fromChain)
                putString("toSendDenom", toSendDenom)
                putString("minAvailableAmount", minAvailableAmount)
                putString("availAmount", availAmount)
                putString("toAmount", toAmount)
            }
            val fragment = Bep3InsertAmountFragment()
            fragment.arguments = args
            fragment.amountSelectListener = listener
            return fragment
        }
    }

    private var amountSelectListener: AmountSelectListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBep3InsertAmountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        textChange()
        updateView()
        setUpClickAction()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        if (binding.amountTxt.text?.isNotEmpty() == true) {
            val amount =
                BigDecimal(binding.amountTxt.text.toString().trim()).movePointRight(assetDecimal)
                    .setScale(0)
            amountSelectListener?.select(amount.toPlainString())
        }
    }

    private fun initView() {
        binding.apply {
            editLayout.setHint(R.string.str_send_amount)

            arguments?.apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    getSerializable(
                        "fromChain", CosmosLine::class.java
                    )?.let { fromChain = it }

                } else {
                    (getSerializable("fromChain") as? CosmosLine)?.let {
                        fromChain = it
                    }
                }
                getString("toSendDenom")?.let { toSendDenom = it }
                getString("minAvailableAmount")?.let { minAvailableAmount = it }
                getString("availAmount")?.let { availAmount = it }
                getString("toAmount")?.let { toAmount = it }
            }

            if (fromChain is ChainBinanceBeacon) {
                assetDecimal = 8
                fromChain.lcdBeaconTokens.firstOrNull { it.symbol == toSendDenom }
                    ?.let { tokenInfo ->
                        minAvailableAmount.toBigDecimal().movePointLeft(assetDecimal)
                            ?.setScale(assetDecimal, RoundingMode.DOWN)?.let { amount ->
                                minAvailable.text =
                                    formatAmount(amount.toPlainString(), assetDecimal)
                            }

                        availAmount.toBigDecimal().movePointLeft(assetDecimal)
                            ?.setScale(assetDecimal, RoundingMode.DOWN)?.let { amount ->
                                available.text = formatAmount(amount.toPlainString(), assetDecimal)
                                availableDenom.text = tokenInfo.original_symbol.uppercase()
                            }
                    }

            } else {
                BaseData.assets?.firstOrNull { it.denom?.lowercase() == toSendDenom }
                    ?.let { asset ->
                        asset.decimals?.let { decimal ->
                            assetDecimal = decimal

                            minAvailableAmount.toBigDecimal().movePointLeft(decimal)
                                ?.setScale(decimal, RoundingMode.DOWN)?.let { amount ->
                                    minAvailable.text =
                                        formatAmount(amount.toPlainString(), decimal)
                                }

                            availAmount.toBigDecimal().movePointLeft(decimal)
                                ?.setScale(decimal, RoundingMode.DOWN)?.let { amount ->
                                    available.text = formatAmount(amount.toPlainString(), decimal)
                                    availableDenom.text = asset.symbol
                                }
                        }
                    }
            }

            toAmount.let {
                if (it.isNotEmpty()) {
                    val dpToSendAmount = it.toBigDecimal().setScale(assetDecimal, RoundingMode.DOWN)
                        .stripTrailingZeros().toPlainString()
                    amountTxt.text = Editable.Factory.getInstance().newEditable(dpToSendAmount)
                } else {
                    amountTxt.text = Editable.Factory.getInstance().newEditable(it)
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
                    updateView()
                }
            })
        }
    }

    private fun updateView() {
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
                    minAvailableAmount.toBigDecimal().movePointLeft(assetDecimal)
                        ?.setScale(assetDecimal)?.let { minAmount ->
                            availAmount.toBigDecimal().movePointLeft(assetDecimal)
                                ?.setScale(assetDecimal)?.let { amount ->
                                    if (this != BigDecimal.ZERO && amount >= this && this >= minAmount) {
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
    }

    private fun setUpClickAction() {
        binding.apply {
            btnQuarter.setOnClickListener {
                availAmount.toBigDecimal().let { amount ->
                    val quarterAmount =
                        amount.multiply(BigDecimal(0.25)).movePointLeft(assetDecimal)
                            .setScale(assetDecimal, RoundingMode.DOWN)
                    amountTxt.text =
                        Editable.Factory.getInstance().newEditable(quarterAmount.toPlainString())
                    updateView()
                }
            }

            btnHalf.setOnClickListener {
                availAmount.toBigDecimal().let { amount ->
                    val halfAmount = amount.multiply(BigDecimal(0.5)).movePointLeft(assetDecimal)
                        .setScale(assetDecimal, RoundingMode.DOWN)
                    amountTxt.text =
                        Editable.Factory.getInstance().newEditable(halfAmount.toPlainString())
                    updateView()
                }
            }

            btnMax.setOnClickListener {
                availAmount.toBigDecimal().let { amount ->
                    val maxAmount =
                        amount.movePointLeft(assetDecimal).setScale(assetDecimal, RoundingMode.DOWN)
                    amountTxt.text =
                        Editable.Factory.getInstance().newEditable(maxAmount.toPlainString())
                    updateView()
                }
            }

            btnConfirm.setOnClickListener {
                val originalAmount =
                    BigDecimal(amountTxt.text.toString().trim()).movePointRight(assetDecimal)
                        .setScale(0)
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