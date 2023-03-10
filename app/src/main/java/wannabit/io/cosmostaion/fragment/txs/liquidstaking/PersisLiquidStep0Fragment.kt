package wannabit.io.cosmostaion.fragment.txs.liquidstaking

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.txs.liquidstaking.PersisLiquidActivity
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.databinding.FragmentLiquidStep0Binding
import wannabit.io.cosmostaion.model.type.Coin
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.WLog
import java.math.BigDecimal
import java.math.RoundingMode

class PersisLiquidStep0Fragment() : BaseFragment() {

    private var _binding: FragmentLiquidStep0Binding? = null
    private val binding get() = _binding!!

    private lateinit var persisLUSViewModel: PersisViewModel

    private var mOutputDenom: String? = null
    private var mInputCoinDecimal = 0
    private var mOutputCoinDecimal = 0
    private var mAvailableMaxAmount = BigDecimal.ZERO

    private lateinit var mInDecimalChecker: String
    private lateinit var mInDecimalSetter: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLiquidStep0Binding.inflate(layoutInflater, container, false)
        persisLUSViewModel = PersisViewModel()
        persisLUSViewModel.loadCValue(baseActivity.mBaseChain)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        persisLUSViewModel.cValue.observe(viewLifecycleOwner, Observer { cValue ->
            onUpdateView()
            onAddAmountWatcher(cValue.toString())
            onClick(cValue.toString())
        })
    }

    fun onUpdateView() {
        binding.progress.visibility = View.GONE
        mInputCoinDecimal = WDp.getDenomDecimal(baseDao, baseActivity.mChainConfig, getSActivity()?.mInputDenom)
        setDpDecimals(mInputCoinDecimal)
        mAvailableMaxAmount = baseDao.getAvailable(getSActivity()?.mInputDenom)

        getSActivity()?.mInputDenom?.let {
            WDp.setDpSymbolImg(baseDao, baseActivity.mChainConfig, it, binding.lsInputIcon)
            WDp.setDpSymbol(requireContext(), baseDao, baseActivity.mChainConfig, it, binding.lsInputSymbol)
            WDp.setDpCoin(requireContext(), baseDao, baseActivity.mChainConfig, it, mAvailableMaxAmount.toPlainString(), binding.lsAvailableAmountSymbol, binding.lsAvailableAmount)
        }

        if (getSActivity()?.mTxType == BaseConstant.CONST_PW_TX_PERSIS_LIQUID_STAKING) {
            mOutputDenom = "stk/uatom"
        } else if (getSActivity()?.mTxType == BaseConstant.CONST_PW_TX_PERSIS_LIQUID_REDEEM) {
            mOutputDenom = "ibc/C8A74ABBE2AF892E15680D916A7C22130585CE5704F9B17A10F184A90D53BECA"
        }

        mOutputDenom?.let {
            mOutputCoinDecimal = WDp.getDenomDecimal(baseDao, baseActivity.mChainConfig, it)
            WDp.setDpSymbolImg(baseDao, baseActivity.mChainConfig, it, binding.lsOutputIcon)
            WDp.setDpSymbol(requireContext(), baseDao, baseActivity.mChainConfig, it, binding.lsOutputSymbol)
        }
    }

    private fun onAddAmountWatcher(cValue: String) {
        binding.lsInputAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(et: Editable) {
                onUpdateOutputTextView(cValue)
                val es = et.toString().trim()
                if (TextUtils.isEmpty(es)) {
                    binding.lsInputAmount.background = ContextCompat.getDrawable(requireContext(), R.drawable.edittext_box)
                } else if (es.startsWith(".")) {
                    binding.lsInputAmount.background = ContextCompat.getDrawable(requireContext(), R.drawable.edittext_box)
                    binding.lsInputAmount.setText("")
                } else if (es.endsWith(".")) {
                    binding.lsInputAmount.background = ContextCompat.getDrawable(requireContext(), R.drawable.edittext_box_error)
                    binding.lsInputAmount.visibility = View.VISIBLE
                } else if (binding.lsInputAmount.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                    binding.lsInputAmount.setText("0")
                    binding.lsInputAmount.setSelection(1)
                }
                if (es == mInDecimalChecker) {
                    binding.lsInputAmount.setText(mInDecimalSetter)
                    binding.lsInputAmount.setSelection(mInputCoinDecimal + 1)
                } else {
                    try {
                        val inputAmount = BigDecimal(es)
                        if (BigDecimal.ZERO >= inputAmount) {
                            binding.lsInputAmount.background = ContextCompat.getDrawable(requireContext(), R.drawable.edittext_box_error)
                            return
                        }
                        val checkPosition = inputAmount.movePointRight(mInputCoinDecimal)
                        val checkMax = checkPosition.setScale(0, RoundingMode.DOWN)
                        if (checkPosition.compareTo(checkMax) != 0 || checkPosition != checkMax) {
                            val recover = es.substring(0, es.length - 1)
                            binding.lsInputAmount.setText(recover)
                            binding.lsInputAmount.setSelection(recover.length)
                            return
                        }
                        if (inputAmount > mAvailableMaxAmount.movePointLeft(mInputCoinDecimal).setScale(mInputCoinDecimal, RoundingMode.CEILING)) {
                            binding.lsInputAmount.background = ContextCompat.getDrawable(requireContext(), R.drawable.edittext_box_error)
                        } else {
                            binding.lsInputAmount.background = ContextCompat.getDrawable(requireContext(), R.drawable.edittext_box)
                        }
                        binding.lsInputAmount.setSelection(binding.lsInputAmount.text.length)
                    } catch (_: Exception) { }
                }
            }
        })
    }

    fun onClick(cValue: String) {
        binding.btnCancel.setOnClickListener {
            getSActivity()?.onBeforeStep()
        }

        binding.btnNext.setOnClickListener {
            if (isValidateLSInputAmount()) {
                getSActivity()?.onNextStep()
            } else {
                Toast.makeText(requireContext(), R.string.error_invalid_amounts, Toast.LENGTH_SHORT).show()
            }
        }

        binding.lsInputClear.setOnClickListener {
            binding.lsInputAmount.setText("")
            binding.lsPoolOutput.text = ""
        }

        binding.lsInput14.setOnClickListener { onSetCalAmount("0.25", cValue) }

        binding.lsInputHalf.setOnClickListener { onSetCalAmount("0.5", cValue) }

        binding.lsInput34.setOnClickListener { onSetCalAmount("0.75", cValue) }

        binding.lsInputMax.setOnClickListener { onSetCalAmount("1", cValue) }
    }

    private fun onSetCalAmount(value: String, cValue: String) {
        val cal = mAvailableMaxAmount.movePointLeft(mInputCoinDecimal).multiply(BigDecimal(value)).setScale(mInputCoinDecimal, RoundingMode.DOWN)
        binding.lsInputAmount.setText(cal.toPlainString())
        onUpdateOutputTextView(cValue)
    }

    private fun onUpdateOutputTextView(cValue: String) {
        try {
            val inputAmountTemp = BigDecimal(binding.lsInputAmount.text.toString().trim())
            val rate = BigDecimal(cValue).movePointLeft(18)
            if (inputAmountTemp.compareTo(BigDecimal.ZERO) == 0) {
                binding.lsPoolOutput.text = ""
                return
            }
            val outputAmount: BigDecimal = if (getSActivity()?.mTxType == BaseConstant.CONST_PW_TX_PERSIS_LIQUID_STAKING) {
                inputAmountTemp.multiply(rate).setScale(12, RoundingMode.DOWN)
            } else {
                val redeemFee = inputAmountTemp.multiply(BigDecimal("0.005"))
                inputAmountTemp.divide(rate, 12, RoundingMode.DOWN).subtract(redeemFee)
            }
            binding.lsPoolOutput.text = outputAmount.setScale(mOutputCoinDecimal, RoundingMode.DOWN).toPlainString()
        } catch (_: Exception) { }
    }

    private fun isValidateLSInputAmount(): Boolean {
        try {
            val inputAmountTemp = BigDecimal(binding.lsInputAmount.text.toString().trim())
            val outAmountTemp = BigDecimal(binding.lsPoolOutput.text.toString().trim())

            inputAmountTemp.let { amount ->
                if (amount <= BigDecimal.ZERO) return false
                if (amount > mAvailableMaxAmount.movePointLeft(mInputCoinDecimal).setScale(mInputCoinDecimal, RoundingMode.CEILING)) return false
                getSActivity()?.mSwapInCoin = Coin(getSActivity()?.mInputDenom, amount.movePointRight(mInputCoinDecimal).setScale(0).toPlainString())
            }

            outAmountTemp.let { amount ->
                getSActivity()?.mSwapOutCoin = Coin(mOutputDenom, amount.movePointRight(mOutputCoinDecimal).setScale(0).toPlainString())
            }
            return true

        } catch (_: java.lang.Exception) {
            return false
        }
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

    private fun getSActivity(): PersisLiquidActivity? {
        return baseActivity as? PersisLiquidActivity
    }
}