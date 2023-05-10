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
import androidx.lifecycle.ViewModelProvider
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.txs.neutron.defi.NeutronSwapActivity
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.databinding.FragmentNeutronSwapStep0Binding
import wannabit.io.cosmostaion.model.factory.neutron.AstroportViewModelProviderFactory
import wannabit.io.cosmostaion.model.repository.neutron.AstroportRepository
import wannabit.io.cosmostaion.model.type.Coin
import wannabit.io.cosmostaion.model.viewModel.neutron.AstroportViewModel
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.WLog
import wannabit.io.cosmostaion.utils.makeToast
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

class NeutronSwapStep0Fragment : BaseFragment() {

    private var _binding: FragmentNeutronSwapStep0Binding? = null
    private val binding get() = _binding!!

    private lateinit var astroportViewModel: AstroportViewModel

    private var inputCoinDecimal = 0
    private var outputCoinDecimal = 0
    private var availableMaxAmount = BigDecimal.ZERO

    private var inDecimalChecker = ""
    private var inDecimalSetter = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNeutronSwapStep0Binding.inflate(layoutInflater, container, false)
        val astroportViewModelProviderFactory = AstroportViewModelProviderFactory(AstroportRepository())
        astroportViewModel = ViewModelProvider(this, astroportViewModelProviderFactory)[AstroportViewModel::class.java]
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
            inputCoinDecimal = WDp.getDenomDecimal(baseDao, baseActivity.mChainConfig, getSActivity()?.inputDenom)
            outputCoinDecimal = WDp.getDenomDecimal(baseDao, baseActivity.mChainConfig, getSActivity()?.outputDenom)
            setDpDecimals(inputCoinDecimal)

            availableMaxAmount = baseDao.getAvailable(getSActivity()?.inputDenom)
            if (getSActivity()?.inputDenom == baseActivity.mChainConfig.mainDenom()) {
                availableMaxAmount = availableMaxAmount.subtract(WDp.getMainDenomFee(requireContext(), baseDao, baseActivity.mChainConfig))
            }

            getSActivity()?.inputDenom?.let {
                swapAvailable.text = "${WDp.getDpAmount2(availableMaxAmount, inputCoinDecimal, outputCoinDecimal)} ${WDp.getDpSymbol(baseDao, baseActivity.mChainConfig, it)}"
                WDp.setDpSymbolImg(baseDao, baseActivity.mChainConfig, it, swapInputIcon)
                swapInputSymbol.text = WDp.getDpSymbol(baseDao, baseActivity.mChainConfig, it)
            }

            getSActivity()?.outputDenom?.let {
                WDp.setDpSymbolImg(baseDao, baseActivity.mChainConfig, it, swapOutputIcon)
                WDp.setDpSymbol(requireContext(), baseDao, baseActivity.mChainConfig, it, swapOutputSymbol)
            }
        }
    }

    private fun onAddAmountWatcher() {
        binding.apply {
            swapInputAmount.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                var lasttime = 0L
                override fun onTextChanged(et: CharSequence?, start: Int, before: Int, count: Int) {
                    lasttime = Date().time
                    var inputAmount = BigDecimal.ZERO
                    if (et?.trim().toString().isNotEmpty()) {
                        inputAmount = BigDecimal(et?.trim().toString()).movePointRight(inputCoinDecimal)
                    }

                    Handler(Looper.getMainLooper()).postDelayed({
                        getSActivity()?.let {
                            if (lasttime + 400 <= Date().time) {
                                astroportViewModel.loadSwapRateData(baseActivity.mChainConfig, Coin(it.inputDenom, inputAmount.toPlainString()), it.outputDenom!!, "neutron1vwrktvvxnevy7s5t7v44z72pdxncnq9gdsjwq9607cdd6vl2lfcs33fpah")
                            }
                        }
                    }, 500)
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
                        } catch (_: Exception) { }
                    }
                }
            })
        }
    }

    private fun loadDataObserve() {
        binding.apply {
            astroportViewModel.swapRateData.observe(viewLifecycleOwner) { response ->
                response?.let {
                    onUpdateOutputTextView(it.return_amount)
                }
            }
        }
    }

    fun onClick() {
        binding.apply {
            btnCancel.setOnClickListener { getSActivity()?.onBeforeStep() }

            btnNext.setOnClickListener {
                if (isValidateSwapInputAmount()) {
                    getSActivity()?.onNextStep()
                } else {
                    requireContext().makeToast(R.string.error_invalid_amounts)
                }
            }

            btnClear.setOnClickListener {
                swapInputAmount.setText("")
                swapOutputAmount.text = ""
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
                val inputAmount = BigDecimal(swapInputAmount.text.toString().trim { it <= ' ' })
                if (inputAmount.compareTo(BigDecimal.ZERO) == 0) {
                    swapOutputAmount.text = ""
                    return
                }
                swapOutputAmount.text = WDp.getDpAmount2(BigDecimal(outAmount), outputCoinDecimal, outputCoinDecimal)
            } catch (_: Exception) { }
        }
    }

    private fun isValidateSwapInputAmount(): Boolean {
        return try {
            binding.apply {
                val inputAmount = BigDecimal(swapInputAmount.text.trim().toString()).movePointRight(inputCoinDecimal)
                val outputAmount = BigDecimal(swapOutputAmount.text.trim().toString()).movePointRight(outputCoinDecimal)

                getSActivity()?.mAmount = Coin(getSActivity()?.inputDenom, inputAmount.toPlainString())
                getSActivity()?.mSwapOutCoin = Coin(getSActivity()?.outputDenom, outputAmount.toPlainString())
                getSActivity()?.mContractAddress = "neutron1vwrktvvxnevy7s5t7v44z72pdxncnq9gdsjwq9607cdd6vl2lfcs33fpah"
            }
            true
        } catch (e: java.lang.Exception) {
            getSActivity()?.mAmount = null
            getSActivity()?.mSwapOutCoin = null
            false
        }
    }

    private fun setDpDecimals(indecimals: Int) {
        inDecimalChecker = "0."
        inDecimalSetter = "0."
        for (i in 0 until indecimals) { inDecimalChecker += "0" }
        for (i in 0 until indecimals - 1) { inDecimalSetter += "0" }
    }

    private fun getSActivity(): NeutronSwapActivity? {
        return baseActivity as NeutronSwapActivity?
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}