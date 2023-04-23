package wannabit.io.cosmostaion.fragment.txs.neutron

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.txs.neutron.VaultActivity
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.databinding.FragmentVaultStep0Binding
import wannabit.io.cosmostaion.dialog.CommonAlertDialog
import wannabit.io.cosmostaion.model.type.Coin
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.makeToast
import java.math.BigDecimal
import java.math.RoundingMode

class VaultStep0Fragment : BaseFragment() {

    private var _binding: FragmentVaultStep0Binding? = null
    private val binding get() = _binding!!

    private var mInputCoinDecimal = 0
    private var mMaxAvailable = BigDecimal.ZERO

    private lateinit var mInDecimalChecker: String
    private lateinit var mInDecimalSetter: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentVaultStep0Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onUpdateView()
        onClick()
    }

    private fun onAddAmountWatcher() {
        binding.apply {
            etAmountCoin.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

                override fun afterTextChanged(et: Editable?) {
                    val es = et.toString().trim { it <= ' ' }
                    if (TextUtils.isEmpty(es)) {
                        etAmountCoin.background = ContextCompat.getDrawable(requireContext(), R.drawable.edittext_box)
                    } else if (es.startsWith(".")) {
                        etAmountCoin.background = ContextCompat.getDrawable(requireContext(), R.drawable.edittext_box)
                        etAmountCoin.setText("")
                    } else if (es.endsWith(".")) {
                        etAmountCoin.background = ContextCompat.getDrawable(requireContext(), R.drawable.edittext_box_error)
                        etAmountCoin.visibility = View.VISIBLE
                    } else if (etAmountCoin.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                        etAmountCoin.setText("0")
                        etAmountCoin.setSelection(1)
                    }

                    if (es == mInDecimalChecker) {
                        etAmountCoin.setText(mInDecimalSetter)
                        etAmountCoin.setSelection(mInputCoinDecimal + 1)
                    } else {
                        try {
                            val inputAmount = BigDecimal(es)
                            if (BigDecimal.ZERO >= inputAmount) {
                                etAmountCoin.background = ContextCompat.getDrawable(requireContext(), R.drawable.edittext_box_error)
                                return
                            }
                            val checkPosition = inputAmount.movePointRight(mInputCoinDecimal)
                            val checkMax = checkPosition.setScale(0, RoundingMode.DOWN)
                            if (checkPosition.compareTo(checkMax) != 0 || checkPosition != checkMax) {
                                val recover = es.substring(0, es.length - 1)
                                etAmountCoin.setText(recover)
                                etAmountCoin.setSelection(recover.length)
                                return
                            }
                            if (inputAmount > mMaxAvailable.movePointLeft(mInputCoinDecimal).setScale(mInputCoinDecimal, RoundingMode.CEILING)) {
                                etAmountCoin.background = ContextCompat.getDrawable(requireContext(), R.drawable.edittext_box_error)
                            } else {
                                etAmountCoin.background = ContextCompat.getDrawable(requireContext(), R.drawable.edittext_box)
                            }
                        } catch (_: Exception) { }
                    }
                }
            })
        }
    }

    fun onUpdateView() {
        if (!isAdded || getSActivity() == null || getSActivity()?.mAccount == null) getSActivity()?.onBackPressed()
        onAddAmountWatcher()

        binding.apply {
            getSActivity()?.mChainConfig?.let {
                mInputCoinDecimal = WDp.getDenomDecimal(baseDao, it, it.mainDenom())
                setDpDecimals(mInputCoinDecimal)
                WDp.setDpSymbol(requireContext(), baseDao, it, it.mainDenom(), tvSymbolCoin)

                mMaxAvailable = if (getSActivity()?.mTxType == BaseConstant.CONST_PW_TX_VAULT_DEPOSIT) {
                    baseDao.getAvailable(it.mainDenom()).subtract(WDp.getMainDenomFee(requireContext(), baseDao, it))
                } else {
                    BigDecimal(getSActivity()?.mDepositAmount)
                }
                tvMaxCoin.text = WDp.getDpAmount2(mMaxAvailable, mInputCoinDecimal, mInputCoinDecimal)
            }
        }
    }

    fun onClick() {
        binding.apply {
            btnCancel.setOnClickListener {
                getSActivity()?.onBeforeStep()
            }

            btnNext.setOnClickListener {
                if (isValidateVaultAmount()) {
                    getSActivity()?.onNextStep()
                } else {
                    requireContext().makeToast(R.string.error_invalid_amounts)
                }
            }

            clearAll.setOnClickListener {
                etAmountCoin.setText("")
            }

            btnAdd01.setOnClickListener { onSetCalAmount("0.1") }
            btnAdd1.setOnClickListener { onSetCalAmount("1") }
            btnAdd10.setOnClickListener { onSetCalAmount("10") }
            btnAdd100.setOnClickListener { onSetCalAmount("100") }

            btnAddHalf.setOnClickListener {
                val half = mMaxAvailable.movePointLeft(mInputCoinDecimal).divide(BigDecimal("2"), mInputCoinDecimal, RoundingMode.DOWN)
                etAmountCoin.setText(half.toPlainString())
            }

            btnAddAll.setOnClickListener {
                val max = mMaxAvailable.movePointLeft(mInputCoinDecimal).setScale(mInputCoinDecimal, RoundingMode.DOWN)
                etAmountCoin.setText(max.toPlainString())
                onShowEmptyBalanceWarnDialog()
            }
        }
    }

    private fun onSetCalAmount(value: String) {
        binding.apply {
            var existed = BigDecimal.ZERO
            val es: String = etAmountCoin.text.toString().trim { it <= ' ' }
            if (es.isNotEmpty()) existed = BigDecimal(es)

            etAmountCoin.setText(existed.add(BigDecimal(value)).toPlainString())
        }
    }

    private fun onShowEmptyBalanceWarnDialog() {
        CommonAlertDialog.showSingleButton(getSActivity(), getString(R.string.str_empty_warnning_title), getString(R.string.str_empty_warnning_msg), getString(R.string.str_close), null)
    }

    private fun isValidateVaultAmount(): Boolean {
        binding.apply {
            getSActivity()?.mChainConfig?.let { chainConfig ->
                return try {
                    val amountTemp = BigDecimal(etAmountCoin.text.toString().trim { it <= ' ' })
                    if (amountTemp <= BigDecimal.ZERO || amountTemp > mMaxAvailable.movePointLeft(mInputCoinDecimal).setScale(mInputCoinDecimal, RoundingMode.CEILING)) return false
                    val coin = Coin(chainConfig.mainDenom(), amountTemp.movePointRight(mInputCoinDecimal).setScale(0).toPlainString())
                    getSActivity()?.mAmount = coin
                    true
                } catch (e: Exception) {
                    getSActivity()?.mAmount = null
                    false
                }
            }
        }
        return false
    }

    private fun setDpDecimals(indecimals: Int) {
        mInDecimalChecker = "0."
        mInDecimalSetter = "0."
        for (i in 0 until indecimals) {
            mInDecimalChecker += "0"
        }
        for (i in 0 until indecimals - 1) {
            mInDecimalSetter += "0"
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun getSActivity(): VaultActivity? {
        return baseActivity as? VaultActivity
    }
}