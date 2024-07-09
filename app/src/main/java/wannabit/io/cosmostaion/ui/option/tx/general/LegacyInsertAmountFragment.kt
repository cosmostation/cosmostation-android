package wannabit.io.cosmostaion.ui.option.tx.general

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
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.handlerRight
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.res.OktToken
import wannabit.io.cosmostaion.databinding.FragmentInsertAmountBinding
import java.math.BigDecimal
import java.math.RoundingMode

class LegacyInsertAmountFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentInsertAmountBinding? = null
    private val binding get() = _binding!!

    private lateinit var fromChain: BaseChain
    private var oktTokenInfo: OktToken? = null
    private var availableAmount = ""
    private var existAmount = ""

    private var assetDecimal: Int = 6

    companion object {
        @JvmStatic
        fun newInstance(
            fromChain: BaseChain,
            oktTokenInfo: OktToken?,
            availableAmount: String,
            existAmount: String,
            listener: AmountSelectListener
        ): LegacyInsertAmountFragment {
            val args = Bundle().apply {
                putParcelable("fromChain", fromChain)
                putParcelable("oktTokenInfo", oktTokenInfo)
                putString("availableAmount", availableAmount)
                putString("existAmount", existAmount)
            }
            val fragment = LegacyInsertAmountFragment()
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

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        if (binding.amountTxt.text?.isNotEmpty() == true) {
            val amount = BigDecimal(binding.amountTxt.text.toString().trim()).setScale(
                assetDecimal, RoundingMode.DOWN
            )
            amountSelectListener?.select(amount.toPlainString())
        }
    }

    private fun initView() {
        binding.apply {
            arguments?.apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    getParcelable(
                        "fromChain", BaseChain::class.java
                    )?.let { fromChain = it }
                    getParcelable("oktTokenInfo", OktToken::class.java)?.let { oktTokenInfo = it }

                } else {
                    (getParcelable("fromChain") as? BaseChain)?.let {
                        fromChain = it
                    }
                    (getParcelable("oktTokenInfo") as? OktToken)?.let {
                        oktTokenInfo = it
                    }
                }
                getString("availableAmount")?.let { availableAmount = it }
                getString("existAmount")?.let { existAmount = it }
            }

            assetDecimal = 18
            availableDenom.text = oktTokenInfo?.original_symbol?.uppercase()
            availableAmount.toBigDecimal().setScale(assetDecimal, RoundingMode.DOWN)
                ?.let { amount ->
                    available.text = formatAmount(amount.toPlainString(), assetDecimal)
                }

            existAmount.let {
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
                    availableAmount.toBigDecimal().setScale(assetDecimal, RoundingMode.DOWN)
                        ?.let { amount ->
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
                        amount.multiply(BigDecimal(0.25)).setScale(assetDecimal, RoundingMode.DOWN)
                    amountTxt.text =
                        Editable.Factory.getInstance().newEditable(quarterAmount.toPlainString())
                    updateAmountView()
                }
            }

            btnHalf.setOnClickListener {
                availableAmount.toBigDecimal().let { amount ->
                    val halfAmount =
                        amount.multiply(BigDecimal(0.5)).setScale(assetDecimal, RoundingMode.DOWN)
                    amountTxt.text =
                        Editable.Factory.getInstance().newEditable(halfAmount.toPlainString())
                    updateAmountView()
                }
            }

            btnMax.setOnClickListener {
                availableAmount.toBigDecimal().let { amount ->
                    val maxAmount = amount.setScale(assetDecimal, RoundingMode.DOWN)
                    amountTxt.text =
                        Editable.Factory.getInstance().newEditable(maxAmount.toPlainString())
                    updateAmountView()
                }
            }

            btnConfirm.setOnClickListener {
                val originalAmount = BigDecimal(amountTxt.text.toString().trim()).setScale(
                    assetDecimal, RoundingMode.DOWN
                )
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