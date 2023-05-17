package wannabit.io.cosmostaion.fragment.txs.neutron.defi.swap

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.walletconnect.util.Empty
import dagger.hilt.android.AndroidEntryPoint
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.txs.neutron.defi.NeutronSwapActivity
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.databinding.FragmentNeutronSwapStep0Binding
import wannabit.io.cosmostaion.model.NetworkResult
import wannabit.io.cosmostaion.model.viewModel.neutron.AstroportViewModel
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.makeToast
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

@AndroidEntryPoint
class NeutronSwapStep0Fragment : BaseFragment() {

    private var _binding: FragmentNeutronSwapStep0Binding? = null
    private val binding get() = _binding!!

    private val astroportViewModel: AstroportViewModel by viewModels()

    private var inputCoinDecimal = 0
    private var outputCoinDecimal = 0
    private var availableMaxAmount = BigDecimal.ZERO
    private var beliefPrice = BigDecimal.ZERO

    private var inDecimalChecker = ""
    private var inDecimalSetter = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNeutronSwapStep0Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            onUpdateView()
            onAddAmountWatcher()
            loadDataObserve()
            onClick()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun onUpdateView() {
        binding.apply {
            getSActivity()?.let {
                inputCoinDecimal = WDp.getDenomDecimal(baseDao, baseActivity.mChainConfig, it.inputCoin?.denom)
                outputCoinDecimal = WDp.getDenomDecimal(baseDao, baseActivity.mChainConfig, it.outputCoin?.denom)
                setDpDecimals(inputCoinDecimal)

                availableMaxAmount = pairAvailable()
                if (it.inputCoin?.denom == baseActivity.mChainConfig.mainDenom()) {
                    availableMaxAmount = availableMaxAmount.subtract(WDp.getMainDenomFee(requireContext(), baseDao, baseActivity.mChainConfig))
                }

                it.inputCoin?.denom?.let { denom ->
                    swapAvailable.text = "${WDp.getDpAmount2(availableMaxAmount, inputCoinDecimal, outputCoinDecimal)} ${WDp.getDpSymbol(baseDao, baseActivity.mChainConfig, denom)}"
                    WDp.setDpSymbolImg(baseDao, baseActivity.mChainConfig, denom, swapInputIcon)
                    swapInputSymbol.text = WDp.getDpSymbol(baseDao, baseActivity.mChainConfig, denom)
                }

                it.outputCoin?.denom.let { denom ->
                    WDp.setDpSymbolImg(baseDao, baseActivity.mChainConfig, denom, swapOutputIcon)
                    WDp.setDpSymbol(requireContext(), baseDao, baseActivity.mChainConfig, denom, swapOutputSymbol)
                }
            }
        }
    }

    private fun onAddAmountWatcher() {
        binding.apply {
            swapInputAmount.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                var lastTime = 0L
                override fun onTextChanged(et: CharSequence?, start: Int, before: Int, count: Int) {
                    lastTime = Date().time
                    astroportViewModel.resetSwapRateDate()
                    Handler(Looper.getMainLooper()).postDelayed({
                        getSActivity()?.let {
                            if (lastTime + 950 <= Date().time) {
                                var inputAmount = BigDecimal.ZERO
                                if (et?.trim().toString().isNotEmpty()) {
                                    inputAmount = BigDecimal(et?.trim().toString()).movePointRight(inputCoinDecimal)
                                }
                                if (inputAmount > BigDecimal.ZERO && it.outputCoin?.denom?.isNotEmpty() == true && it.inputCoin?.denom?.isNotEmpty() == true) {
                                    astroportViewModel.loadSwapRateData(baseActivity.mChainConfig, it.inputCoin, inputAmount.toPlainString(), it.outputCoin, it.selectedPool?.contract_address!!)
                                }
                            }
                        }
                    }, 1000)
                }

                override fun afterTextChanged(et: Editable?) {
                    val es = et.toString().trim()
                    if (TextUtils.isEmpty(es)) {
                        swapInputAmount.background = ContextCompat.getDrawable(requireContext(), R.drawable.edittext_box)
                    } else if (es.startsWith(".")) {
                        swapInputAmount.background = ContextCompat.getDrawable(requireContext(), R.drawable.edittext_box)
                        swapInputAmount.setText("")
                    } else if (es.endsWith(".")) {
                        swapInputAmount.background = ContextCompat.getDrawable(requireContext(), R.drawable.edittext_box_error)
                        swapInputAmount.visibility = View.VISIBLE
                    } else if (swapInputAmount.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                        swapInputAmount.setText("0")
                        swapInputAmount.setSelection(1)
                    }
                    if (es == inDecimalChecker) {
                        swapInputAmount.setText(inDecimalSetter)
                        swapInputAmount.setSelection(inputCoinDecimal + 1)
                    } else {
                        try {
                            val inputAmount = BigDecimal(es)
                            if (BigDecimal.ZERO >= inputAmount) {
                                swapInputAmount.background = ContextCompat.getDrawable(requireContext(), R.drawable.edittext_box_error)
                                return
                            }
                            val checkPosition = inputAmount.movePointRight(inputCoinDecimal)
                            val checkMax = checkPosition.setScale(0, RoundingMode.DOWN)
                            if (checkPosition.compareTo(checkMax) != 0 || checkPosition != checkMax) {
                                val recover = es.substring(0, es.length - 1)
                                swapInputAmount.setText(recover)
                                swapInputAmount.setSelection(recover.length)
                                return
                            }
                            if (inputAmount > availableMaxAmount.movePointLeft(inputCoinDecimal).setScale(inputCoinDecimal, RoundingMode.CEILING)) {
                                swapInputAmount.background = ContextCompat.getDrawable(requireContext(), R.drawable.edittext_box_error)
                            } else {
                                swapInputAmount.background = ContextCompat.getDrawable(requireContext(), R.drawable.edittext_box)
                            }
                            swapInputAmount.setSelection(swapInputAmount.text.length)
                        } catch (_: Exception) {
                        }
                    }
                }
            })
        }
    }

    private fun loadDataObserve() {
        astroportViewModel.loading.observe(viewLifecycleOwner) {
            if (it && binding.swapInputAmount.text.trim().isNotEmpty()) {
                onUpdateOutputTextView("Calculating...")
            }
        }

        astroportViewModel.swapRateData.observe(viewLifecycleOwner) { response ->
            response?.let {
                when (response) {
                    is NetworkResult.Success -> {
                        response.data?.let { data ->
                            onUpdateOutputTextView(data.return_amount)
                        }
                    }
                    is NetworkResult.Error -> {
                        requireContext().makeToast(response.message ?: "Unknown error message")
                        requireActivity().finish()
                    }
                }
            } ?: run {
                onUpdateOutputTextView(StringUtils.EMPTY)
            }
        }
    }

    fun onClick() {
        binding.apply {
            btnCancel.setOnClickListener { getSActivity()?.onBeforeStep() }

            btnNext.setOnClickListener {
                if (validateAndSetSwapAmount()) {
                    getSActivity()?.onNextStep()
                } else {
                    getSActivity()?.mAmount = null
                    requireContext().makeToast(R.string.error_invalid_amounts)
                }
            }

            btnClear.setOnClickListener {
                swapInputAmount.setText("")
                onUpdateOutputTextView("")
            }

            btn14.setOnClickListener { onSetCalAmount("0.25") }
            btnHalf.setOnClickListener { onSetCalAmount("0.5") }
            btn34.setOnClickListener { onSetCalAmount("0.75") }
            btnMax.setOnClickListener { onSetCalAmount("1") }
        }
    }

    private fun onSetCalAmount(value: String) {
        val cal = availableMaxAmount.movePointLeft(inputCoinDecimal).multiply(BigDecimal(value)).setScale(inputCoinDecimal, RoundingMode.DOWN)
        binding.swapInputAmount.setText(cal.toPlainString())
    }

    private fun onUpdateOutputTextView(outAmount: String) {
        binding.apply {
            try {
                beliefPrice = BigDecimal.ZERO
                val inputAmount = BigDecimal(swapInputAmount.text.toString().trim { it <= ' ' })
                if (inputAmount.compareTo(BigDecimal.ZERO) == 0) {
                    swapOutputAmount.text = String.Empty
                    return
                }

                val dpOutputAmount = BigDecimal(outAmount).movePointLeft(outputCoinDecimal)
                if (dpOutputAmount <= BigDecimal.ZERO) {
                    swapOutputAmount.text = ""
                    swapOutputAmount.background = ContextCompat.getDrawable(requireContext(), R.drawable.edittext_box_error)
                } else {
                    swapOutputAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorBlackDayNight))
                    swapOutputAmount.text = BigDecimal(outAmount).movePointLeft(outputCoinDecimal).toPlainString()
                    beliefPrice = inputAmount.movePointRight(inputCoinDecimal).divide(BigDecimal(outAmount), 18, RoundingMode.UP)
                }

            } catch (_: Exception) {
                swapOutputAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGray1))
                swapOutputAmount.text = outAmount
            }
        }
    }

    private fun validateAndSetSwapAmount(): Boolean {
        if (astroportViewModel.swapRateData.value == null || astroportViewModel.loading.value == true) {
            return false
        }
        if (beliefPrice <= BigDecimal.ZERO) {
            requireContext().makeToast(R.string.error_invalid_amount)
            return false
        }

        return try {
            val inputAmount = BigDecimal(binding.swapInputAmount.text.trim().toString()).movePointRight(inputCoinDecimal)
            val outputAmount = BigDecimal(binding.swapOutputAmount.text.trim().toString()).movePointRight(outputCoinDecimal)
            if (inputAmount > availableMaxAmount) {
                requireContext().makeToast(R.string.error_invalid_amounts)
                return false
            }

            if (inputAmount > BigDecimal(0) && outputAmount > BigDecimal(0)) {
                getSActivity()?.mSelectedPool = getSActivity()?.selectedPool
                getSActivity()?.mInputPair = getSActivity()?.inputCoin
                getSActivity()?.mSwapInAmount = inputAmount.toPlainString()
                getSActivity()?.mSwapOutAmount = outputAmount.toPlainString()
                getSActivity()?.mBeliefPrice = beliefPrice.toPlainString()
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }

    private fun pairAvailable(): BigDecimal {
        getSActivity()?.selectedPool?.let {
            it.pairs.forEach { pair ->
                if (pair.denom == getSActivity()?.inputCoin?.denom) {
                    return if (pair.type == "cw20") {
                        baseDao.getCw20Asset(baseActivity.mChainConfig, getSActivity()?.inputCoin?.denom).getAmount()
                    } else {
                        baseDao.getAvailable(getSActivity()?.inputCoin?.denom)
                    }
                }
            }
        }
        return BigDecimal.ZERO
    }


    private fun setDpDecimals(indecimals: Int) {
        inDecimalChecker = "0."
        inDecimalSetter = "0."
        for (i in 0 until indecimals) {
            inDecimalChecker += "0"
        }
        for (i in 0 until indecimals - 1) {
            inDecimalSetter += "0"
        }
    }

    private fun getSActivity(): NeutronSwapActivity? {
        return baseActivity as NeutronSwapActivity?
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}