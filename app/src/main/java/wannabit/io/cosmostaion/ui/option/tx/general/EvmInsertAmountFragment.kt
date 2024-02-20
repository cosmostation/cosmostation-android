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
import wannabit.io.cosmostaion.chain.EVM_BASE_FEE
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.handlerRight
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.databinding.FragmentInsertAmountBinding
import java.math.BigDecimal
import java.math.RoundingMode

class EvmInsertAmountFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentInsertAmountBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedEvmChain: EthereumLine
    private var selectedToken: Token? = null
    private lateinit var availableAmount: String
    private var toAmount: String = ""

    private var assetDecimal: Int = 18

    companion object {
        @JvmStatic
        fun newInstance(
            selectedEvmChain: EthereumLine,
            selectedToken: Token?,
            availableAmount: String,
            toAmount: String,
            listener: AmountSelectListener
        ): EvmInsertAmountFragment {
            val args = Bundle().apply {
                putParcelable("selectedEvmChain", selectedEvmChain)
                putParcelable("selectedToken", selectedToken)
                putString("availableAmount", availableAmount)
                putString("toAmount", toAmount)
            }
            val fragment = EvmInsertAmountFragment()
            fragment.amountSelectListener = listener
            fragment.arguments = args
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
        setUpClickAction()
    }

    private fun initView() {
        binding.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.apply {
                    getParcelable(
                        "selectedEvmChain", EthereumLine::class.java
                    )?.let { selectedEvmChain = it }

                    getParcelable("selectedToken", Token::class.java)?.let { selectedToken = it }
                }

            } else {
                arguments?.apply {
                    (getParcelable("selectedEvmChain") as? EthereumLine)?.let {
                        selectedEvmChain = it
                    }

                    (getParcelable("selectedToken") as? Token)?.let { selectedToken = it }
                }
            }
            arguments?.getString("availableAmount")?.let { availableAmount = it }
            arguments?.getString("toAmount")?.let { toAmount = it }

            selectedToken?.let { token ->
                assetDecimal = token.decimals
                availableDenom.text = token.symbol
                availableAmount = token.amount.toString()

            } ?: run {
                availableDenom.text = selectedEvmChain.coinSymbol
                availableAmount = selectedEvmChain.evmBalance.subtract(EVM_BASE_FEE).toString()
            }
            available.text = formatAmount(
                availableAmount.toBigDecimal().movePointLeft(assetDecimal)
                    .setScale(assetDecimal, RoundingMode.DOWN).toPlainString(), assetDecimal
            )

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
                ) {
                }

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
                availableAmount.toBigDecimal().let { amount ->
                    val quarterAmount =
                        amount.multiply(BigDecimal(0.25)).movePointLeft(assetDecimal)
                            .setScale(assetDecimal, RoundingMode.DOWN)
                    amountTxt.text =
                        Editable.Factory.getInstance().newEditable(quarterAmount.toPlainString())
                    updateAmountView()
                }
            }

            btnHalf.setOnClickListener {
                availableAmount.toBigDecimal().let { amount ->
                    val halfAmount = amount.multiply(BigDecimal(0.5)).movePointLeft(assetDecimal)
                        .setScale(assetDecimal, RoundingMode.DOWN)
                    amountTxt.text =
                        Editable.Factory.getInstance().newEditable(halfAmount.toPlainString())
                    updateAmountView()
                }
            }

            btnMax.setOnClickListener {
                availableAmount.toBigDecimal().let { amount ->
                    val maxAmount =
                        amount.movePointLeft(assetDecimal).setScale(assetDecimal, RoundingMode.DOWN)
                    amountTxt.text =
                        Editable.Factory.getInstance().newEditable(maxAmount.toPlainString())
                    updateAmountView()
                }
            }

            btnConfirm.setOnClickListener {
                val originalAmount =
                    BigDecimal(amountTxt.text.toString().trim()).movePointRight(assetDecimal)
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