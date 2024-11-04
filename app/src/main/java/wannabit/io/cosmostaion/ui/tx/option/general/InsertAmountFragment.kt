package wannabit.io.cosmostaion.ui.tx.option.general

import android.os.Build
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
import wannabit.io.cosmostaion.databinding.FragmentInsertAmountBinding
import wannabit.io.cosmostaion.ui.main.chain.cosmos.TxType
import java.math.BigDecimal
import java.math.RoundingMode

interface AmountSelectListener {
    fun select(toAmount: String)
}

class InsertAmountFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentInsertAmountBinding? = null
    private val binding get() = _binding!!

    private lateinit var txType: TxType
    private var availableAmount: String? = ""
    private var toAmount: String? = ""
    private var toAsset: Asset? = null

    private var assetDecimal: Int = 6

    companion object {
        @JvmStatic
        fun newInstance(
            txType: TxType,
            availableAmount: String?,
            toAmount: String?,
            toAsset: Asset?,
            listener: AmountSelectListener
        ): InsertAmountFragment {
            val args = Bundle().apply {
                putSerializable("txType", txType)
                putString("availableAmount", availableAmount)
                putString("toAmount", toAmount)
                putParcelable("toAsset", toAsset)
            }
            val fragment = InsertAmountFragment()
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
                        "txType", TxType::class.java
                    )?.let { txType = it }
                    getParcelable("toAsset", Asset::class.java)?.let { toAsset = it }

                } else {
                    (getSerializable("txType") as? TxType)?.let {
                        txType = it
                    }
                    (getParcelable("toAsset") as? Asset)?.let {
                        toAsset = it
                    }
                }
                getString("availableAmount")?.let { availableAmount = it }
                getString("toAmount")?.let { toAmount = it }
            }

            initTxType()
        }
    }

    private fun initTxType() {
        binding.apply {
            when (txType) {
                TxType.DELEGATE, TxType.SUI_DELEGATE -> {
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
            }
        }
    }

    private fun setAssetAmount() {
        binding.apply {
            toAsset?.let { asset ->
                assetDecimal = asset.decimals ?: 6

                availableAmount?.toBigDecimal()?.movePointLeft(assetDecimal)
                    ?.setScale(assetDecimal, RoundingMode.DOWN)?.let { amount ->
                        available.text = formatAmount(amount.toPlainString(), assetDecimal)
                        availableDenom.text = asset.symbol
                        availableDenom.setTextColor(asset.assetColor())
                    }

                if (toAmount?.isNotEmpty() == true) {
                    val dpToSendAmount = toAmount?.toBigDecimal()?.movePointLeft(assetDecimal)
                        ?.setScale(assetDecimal, RoundingMode.DOWN)?.stripTrailingZeros()
                        ?.toPlainString()
                    amountTxt.text = Editable.Factory.getInstance().newEditable(dpToSendAmount)
                } else {
                    amountTxt.text = Editable.Factory.getInstance().newEditable(toAmount)
                }
            }
        }
    }

    private fun setShareAmount() {
        binding.apply {
            availableAmount?.toBigDecimal()?.movePointLeft(6)?.setScale(6, RoundingMode.DOWN)
                ?.let { amount ->
                    available.text = formatAmount(amount.toPlainString(), 6)
                    availableDenom.text = "Share"
                }

            if (toAmount?.isNotEmpty() == true) {
                val dpToSendAmount =
                    toAmount?.toBigDecimal()?.movePointLeft(6)?.setScale(6, RoundingMode.DOWN)
                        ?.stripTrailingZeros()?.toPlainString()
                amountTxt.text = Editable.Factory.getInstance().newEditable(dpToSendAmount)
            } else {
                amountTxt.text = Editable.Factory.getInstance().newEditable(toAmount)
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
                    availableAmount?.toBigDecimal()?.movePointLeft(assetDecimal)
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
                val quarterAmount = availableAmount?.toBigDecimal()?.multiply(BigDecimal(0.25))
                    ?.movePointLeft(assetDecimal)?.setScale(assetDecimal, RoundingMode.DOWN)
                amountTxt.text =
                    Editable.Factory.getInstance().newEditable(quarterAmount?.toPlainString())
                updateAmountView()
            }

            btnHalf.setOnClickListener {
                val halfAmount = availableAmount?.toBigDecimal()?.multiply(BigDecimal(0.5))
                    ?.movePointLeft(assetDecimal)?.setScale(assetDecimal, RoundingMode.DOWN)
                amountTxt.text =
                    Editable.Factory.getInstance().newEditable(halfAmount?.toPlainString())
                updateAmountView()
            }

            btnMax.setOnClickListener {
                val maxAmount = availableAmount?.toBigDecimal()?.movePointLeft(assetDecimal)
                    ?.setScale(assetDecimal, RoundingMode.DOWN)
                amountTxt.text =
                    Editable.Factory.getInstance().newEditable(maxAmount?.toPlainString())
                updateAmountView()
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