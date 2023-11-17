package wannabit.io.cosmostaion.ui.dialog.tx.kava

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.handlerRight
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.res.Asset
import wannabit.io.cosmostaion.databinding.FragmentPoolInsertAmountBinding
import java.math.BigDecimal
import java.math.RoundingMode

class PoolInsertAmountFragment(
    private val pool1Asset: Asset?,
    private val pool2Asset: Asset?,
    private val coin1AvailableAmount: BigDecimal,
    private val coin2AvailableAmount: BigDecimal,
    private val swapRate: BigDecimal,
    private val coin1ToAmount: String?,
    private val coin2ToAmount: String?,
    val listener: PoolAmountSelectListener
) : BottomSheetDialogFragment() {

    private var _binding: FragmentPoolInsertAmountBinding? = null
    private val binding get() = _binding!!

    private var pool1AssetDecimal: Int = 6
    private var pool2AssetDecimal: Int = 6

    private var isFocused: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPoolInsertAmountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        textChange()
        clickAction()
    }

    private fun initView() {
        binding.apply {
            pool1Asset?.let { asset ->
                asset.decimals?.let { decimal ->
                    pool1AssetDecimal = decimal
                    coin1AvailableAmount.movePointLeft(pool1AssetDecimal)?.setScale(pool1AssetDecimal)?.let { amount ->
                        pool1Available.text = formatAmount(amount.toPlainString(), decimal)
                        pool1AvailableDenom.text = asset.symbol
                    }

                    coin1ToAmount?.let {
                        if (it.isNotEmpty()) {
                            val dpToSendAmount = it.toBigDecimal().movePointLeft(decimal).setScale(decimal).stripTrailingZeros().toPlainString()
                            pool1AmountTxt.text = Editable.Factory.getInstance().newEditable(dpToSendAmount)
                        } else {
                            pool1AmountTxt.text = Editable.Factory.getInstance().newEditable(it)
                        }
                    }
                }
            }

            pool2Asset?.let { asset ->
                asset.decimals?.let { decimal ->
                    pool2AssetDecimal = decimal
                    coin2AvailableAmount.movePointLeft(pool2AssetDecimal)?.setScale(pool2AssetDecimal)?.let { amount ->
                        pool2Available.text = formatAmount(amount.toPlainString(), decimal)
                        pool2AvailableDenom.text = asset.symbol
                    }

                    coin2ToAmount?.let {
                        if (it.isNotEmpty()) {
                            val dpToSendAmount = it.toBigDecimal().movePointLeft(decimal).setScale(decimal).stripTrailingZeros().toPlainString()
                            pool2AmountTxt.text = Editable.Factory.getInstance().newEditable(dpToSendAmount)
                        } else {
                            pool2AmountTxt.text = Editable.Factory.getInstance().newEditable(it)
                        }
                    }
                }
            }

            pool1AmountTxt.setOnFocusChangeListener { _: View?, focused: Boolean -> isFocused = focused }
            pool2AmountTxt.setOnFocusChangeListener { _: View?, focused: Boolean -> isFocused = !focused }
        }
    }

    private fun textChange() {
        binding.apply {
            pool1AmountTxt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    val userInput = s.toString()
                    if (userInput.startsWith(".")) {
                        pool1AmountTxt.setText("")
                        return

                    } else if (userInput.contains(".")) {
                        val decimalPlaces: Int = userInput.length - userInput.indexOf(".") - 1
                        if (decimalPlaces == pool1AssetDecimal) {
                            if (userInput.toBigDecimal().handlerRight(pool1AssetDecimal, 0) == BigDecimal.ZERO) {
                                s?.delete(s.length - 1, s.length)
                            }
                        } else if (decimalPlaces > pool1AssetDecimal) {
                            s?.delete(s.length - 1, s.length)
                        }
                    }
                    updatePool0View()
                }
            })

            pool2AmountTxt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    val userInput = s.toString()
                    if (userInput.startsWith(".")) {
                        pool2AmountTxt.setText("")
                        return

                    } else if (userInput.contains(".")) {
                        val decimalPlaces: Int = userInput.length - userInput.indexOf(".") - 1
                        if (decimalPlaces == pool2AssetDecimal) {
                            if (userInput.toBigDecimal().handlerRight(pool2AssetDecimal, 0) == BigDecimal.ZERO) {
                                s?.delete(s.length - 1, s.length)
                            }
                        } else if (decimalPlaces > pool2AssetDecimal) {
                            s?.delete(s.length - 1, s.length)
                        }
                    }
                    updatePool1View()
                }
            })
        }
    }

    private fun updatePool0View() {
        binding.apply {
            if (isFocused) {
                pool1AmountTxt.text.toString().trim().let { text ->
                    if (text.isEmpty()) {
                        pool1EditLayout.error = null
                        pool1InvalidMsg.visibility = View.GONE

                        pool2AmountTxt.setText("")
                        pool2EditLayout.error = null
                        pool2InvalidMsg.visibility = View.GONE
                        btnConfirm.updateButtonView(false)
                        return
                    }

                    if (text.toBigDecimal().handlerRight(pool1AssetDecimal, 0) == BigDecimal.ZERO) {
                        pool1EditLayout.error = getString(R.string.error_invalid_amount)
                        pool1InvalidMsg.visibility = View.VISIBLE
                        pool2AmountTxt.setText("")
                        btnConfirm.updateButtonView(false)
                        return
                    }

                    BigDecimal(text).apply {
                        coin1AvailableAmount.movePointLeft(pool1AssetDecimal)?.setScale(pool1AssetDecimal)?.let { amount ->
                            if (this != BigDecimal.ZERO && amount >= this) {
                                pool1EditLayout.error = null
                                pool1InvalidMsg.visibility = View.GONE

                            } else {
                                pool1EditLayout.error = getString(R.string.error_invalid_amount)
                                pool1InvalidMsg.visibility = View.VISIBLE
                            }
                            pool1AmountTxt.setSelection(text.length)
                        }

                        val outputAmount = this.movePointRight(pool1AssetDecimal).divide(swapRate, 0, RoundingMode.DOWN)
                        pool2AmountTxt.text = Editable.Factory.getInstance().newEditable(outputAmount.movePointLeft(pool2AssetDecimal).toPlainString())
                        outputAmount?.let { amount ->
                            if (amount != BigDecimal.ZERO && amount <= coin2AvailableAmount) {
                                pool2EditLayout.error = null
                                pool2InvalidMsg.visibility = View.GONE
                                btnConfirm.updateButtonView(true)

                            } else {
                                pool2EditLayout.error = getString(R.string.error_invalid_amount)
                                pool2InvalidMsg.visibility = View.VISIBLE
                                btnConfirm.updateButtonView(false)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun updatePool1View() {
        binding.apply {
            if (!isFocused) {
                pool2AmountTxt.text.toString().trim().let { text ->
                    if (text.isEmpty()) {
                        pool2EditLayout.error = null
                        pool2InvalidMsg.visibility = View.GONE

                        pool1AmountTxt.setText("")
                        pool1EditLayout.error = null
                        pool1InvalidMsg.visibility = View.GONE
                        btnConfirm.updateButtonView(false)
                        return
                    }

                    if (text.toBigDecimal().handlerRight(pool2AssetDecimal, 0) == BigDecimal.ZERO) {
                        pool2EditLayout.error = getString(R.string.error_invalid_amount)
                        pool2InvalidMsg.visibility = View.VISIBLE
                        pool1AmountTxt.setText("")
                        btnConfirm.updateButtonView(false)
                        return
                    }

                    BigDecimal(text).apply {
                        coin2AvailableAmount.movePointLeft(pool2AssetDecimal)?.setScale(pool2AssetDecimal)?.let { amount ->
                            if (this != BigDecimal.ZERO && amount >= this) {
                                pool2EditLayout.error = null
                                pool2InvalidMsg.visibility = View.GONE

                            } else {
                                pool2EditLayout.error = getString(R.string.error_invalid_amount)
                                pool2InvalidMsg.visibility = View.VISIBLE
                            }
                            pool2AmountTxt.setSelection(text.length)
                        }

                        val outputAmount = this.movePointRight(pool2AssetDecimal).multiply(swapRate).setScale(0, RoundingMode.DOWN)
                        pool1AmountTxt.text = Editable.Factory.getInstance().newEditable(outputAmount.movePointLeft(pool1AssetDecimal).toPlainString())
                        outputAmount?.let { amount ->
                            if (amount != BigDecimal.ZERO && amount <= coin1AvailableAmount) {
                                pool1EditLayout.error = null
                                pool1InvalidMsg.visibility = View.GONE
                                btnConfirm.updateButtonView(true)

                            } else {
                                pool1EditLayout.error = getString(R.string.error_invalid_amount)
                                pool1InvalidMsg.visibility = View.VISIBLE
                                btnConfirm.updateButtonView(true)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun clickAction() {
        binding.apply {
            btnConfirm.setOnClickListener {
                val toPool1Amount = pool1AmountTxt.text.toString().trim().toBigDecimal().movePointRight(pool1AssetDecimal).setScale(0).toPlainString()
                val toPool2Amount = pool2AmountTxt.text.toString().trim().toBigDecimal().movePointRight(pool2AssetDecimal).setScale(0).toPlainString()
                listener.select(toPool1Amount, toPool2Amount)
                dismiss()
            }

            pool1EditLayout.setEndIconOnClickListener {
                pool2AmountTxt.setText("")
            }

            pool2EditLayout.setEndIconOnClickListener {
                pool1AmountTxt.setText("")
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

interface PoolAmountSelectListener {
    fun select(coin1ToAmount: String, coin2ToAmount: String)
}