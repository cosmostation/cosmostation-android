package wannabit.io.cosmostaion.ui.option.tx.general

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.handlerRight
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.res.Asset
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.databinding.FragmentInsertAmountBinding
import wannabit.io.cosmostaion.ui.main.chain.TxType
import wannabit.io.cosmostaion.ui.tx.step.TransferAssetType
import java.math.BigDecimal
import java.math.RoundingMode


class InsertAmountFragment(
    private val txType: TxType?,
    private val transferAssetType: TransferAssetType?,
    private val availAmount: BigDecimal?,
    private val toAmount: String?,
    private val selectedAsset: Asset?,
    private val selectedToken: Token?,
    val listener: AmountSelectListener
) : BottomSheetDialogFragment() {

    private var _binding: FragmentInsertAmountBinding? = null
    private val binding get() = _binding!!

    private var assetDecimal: Int = 6

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
            when (txType) {
                TxType.TRANSFER -> {
                    if (transferAssetType == TransferAssetType.COIN_TRANSFER) {
                        setAssetAmount()
                    } else {
                        setTokenAmount()
                    }
                    editLayout.setHint(R.string.str_send_amount)
                }

                TxType.DELEGATE -> {
                    editLayout.setHint(R.string.title_delegate_amount)
                    setAssetAmount()
                }

                TxType.UN_DELEGATE -> {
                    editLayout.setHint(R.string.title_undelegate_amount)
                    setAssetAmount()
                }

                TxType.RE_DELEGATE -> {
                    editLayout.setHint(R.string.title_redelegate_amount)
                    setAssetAmount()
                }

                TxType.VAULT_DEPOSIT, TxType.MINT_DEPOSIT, TxType.LEND_DEPOSIT -> {
                    editLayout.setHint(R.string.title_vault_deposit_amount)
                    setAssetAmount()
                }

                TxType.VAULT_WITHDRAW, TxType.MINT_WITHDRAW, TxType.LEND_WITHDRAW -> {
                    editLayout.setHint(R.string.title_vault_withdraw_amount)
                    setAssetAmount()
                }

                TxType.MINT_BORROW, TxType.LEND_BORROW -> {
                    editLayout.setHint(R.string.title_borrow_amount)
                    setAssetAmount()
                }

                TxType.MINT_REPAY, TxType.LEND_REPAY -> {
                    editLayout.setHint(R.string.title_repay_amount)
                    setAssetAmount()
                }

                TxType.MINT_CREATE_COLLATERAL -> {
                    editLayout.setHint(R.string.title_collateral_amount)
                    setAssetAmount()
                }

                TxType.MINT_CREATE_PRINCIPAL -> {
                    editLayout.setHint(R.string.title_principal_amount)
                    setAssetAmount()
                }

                TxType.POOL_WITHDRAW -> {
                    editLayout.setHint(R.string.title_vault_withdraw_amount)
                    setShareAmount()
                }

                TxType.EARN_DEPOSIT -> {
                    editLayout.setHint(R.string.title_add_amount)
                    setAssetAmount()
                }

                TxType.EARN_WITHDRAW -> {
                    editLayout.setHint(R.string.title_remove_amount)
                    setAssetAmount()
                }

                else -> {}
            }
        }
    }

    private fun setAssetAmount() {
        binding.apply {
            selectedAsset?.let { asset ->
                assetDecimal = asset.decimals ?: 6

                availAmount?.movePointLeft(assetDecimal)?.setScale(assetDecimal, RoundingMode.DOWN)
                    ?.let { amount ->
                        available.text = formatAmount(amount.toPlainString(), assetDecimal)
                        availableDenom.text = asset.symbol
                        availableDenom.setTextColor(asset.assetColor())
                    }

                toAmount?.let {
                    if (it.isNotEmpty()) {
                        val dpToSendAmount = it.toBigDecimal().movePointLeft(assetDecimal)
                            .setScale(assetDecimal, RoundingMode.DOWN).stripTrailingZeros()
                            .toPlainString()
                        amountTxt.text = Editable.Factory.getInstance().newEditable(dpToSendAmount)
                    } else {
                        amountTxt.text = Editable.Factory.getInstance().newEditable(it)
                    }
                }
            }
        }
    }

    private fun setTokenAmount() {
        binding.apply {
            selectedToken?.let { token ->
                assetDecimal = token.decimals

                availAmount?.movePointLeft(token.decimals)
                    ?.setScale(token.decimals, RoundingMode.DOWN)?.let { amount ->
                        available.text = formatAmount(amount.toPlainString(), token.decimals)
                        availableDenom.text = token.symbol
                    }

                toAmount?.let {
                    if (it.isNotEmpty()) {
                        val dpToSendAmount = it.toBigDecimal().movePointLeft(token.decimals)
                            .setScale(token.decimals, RoundingMode.DOWN).stripTrailingZeros()
                            .toPlainString()
                        amountTxt.text = Editable.Factory.getInstance().newEditable(dpToSendAmount)
                    } else {
                        amountTxt.text = Editable.Factory.getInstance().newEditable(it)
                    }
                }
            }
        }
    }

    private fun setShareAmount() {
        binding.apply {
            availAmount?.movePointLeft(6)?.setScale(6, RoundingMode.DOWN)?.let { amount ->
                available.text = formatAmount(amount.toPlainString(), 6)
                availableDenom.text = "Share"
            }

            toAmount?.let {
                if (it.isNotEmpty()) {
                    val dpToSendAmount =
                        it.toBigDecimal().movePointLeft(6).setScale(6, RoundingMode.DOWN)
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
                    availAmount?.movePointLeft(assetDecimal)
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
                availAmount?.let { amount ->
                    val quarterAmount =
                        amount.multiply(BigDecimal(0.25)).movePointLeft(assetDecimal)
                            .setScale(assetDecimal, RoundingMode.DOWN)
                    amountTxt.text =
                        Editable.Factory.getInstance().newEditable(quarterAmount.toPlainString())
                    updateAmountView()
                }
            }

            btnHalf.setOnClickListener {
                availAmount?.let { amount ->
                    val halfAmount = amount.multiply(BigDecimal(0.5)).movePointLeft(assetDecimal)
                        .setScale(assetDecimal, RoundingMode.DOWN)
                    amountTxt.text =
                        Editable.Factory.getInstance().newEditable(halfAmount.toPlainString())
                    updateAmountView()
                }
            }

            btnMax.setOnClickListener {
                availAmount?.let { amount ->
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
                listener.select(originalAmount.toPlainString())
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

interface AmountSelectListener {
    fun select(toAmount: String)
}