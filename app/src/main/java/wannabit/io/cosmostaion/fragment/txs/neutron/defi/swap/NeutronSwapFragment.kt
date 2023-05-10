package wannabit.io.cosmostaion.fragment.txs.neutron.defi.swap

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.txs.neutron.defi.NeutronSwapActivity
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.databinding.FragmentNeutronSwapBinding
import wannabit.io.cosmostaion.model.factory.neutron.AstroportViewModelProviderFactory
import wannabit.io.cosmostaion.model.repository.neutron.AstroportRepository
import wannabit.io.cosmostaion.model.type.Coin
import wannabit.io.cosmostaion.model.viewModel.neutron.AstroportViewModel
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.makeToast
import java.math.BigDecimal
import java.math.RoundingMode

class NeutronSwapFragment : BaseFragment() {

    private var _binding: FragmentNeutronSwapBinding? = null
    private val binding get() = _binding!!

    private lateinit var astroportViewModel: AstroportViewModel

    private var mAllDenoms = ArrayList<String>()
    private lateinit var inputCoinDenom: String
    private lateinit var outputCoinDenom: String

    private var mAvailableMaxAmount: BigDecimal = BigDecimal.ZERO
    private var mSwapableDenoms = ArrayList<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNeutronSwapBinding.inflate(layoutInflater, container, false)
        val astroportViewModelProviderFactory = AstroportViewModelProviderFactory(AstroportRepository())
        astroportViewModel = ViewModelProvider(this, astroportViewModelProviderFactory)[AstroportViewModel::class.java]

        inputCoinDenom = baseActivity.mChainConfig.mainDenom()
        outputCoinDenom = "ibc/EFB00E728F98F0C4BBE8CA362123ACAB466EDA2826DC6837E49F4C1902F21BBA"
        astroportViewModel.loadSwapRateData(baseActivity.mChainConfig, Coin(inputCoinDenom, "1000000"), outputCoinDenom, "neutron1vwrktvvxnevy7s5t7v44z72pdxncnq9gdsjwq9607cdd6vl2lfcs33fpah")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnToggle.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.color_neutron)
        loadDataObserve()
        onClick()
    }

    private fun loadDataObserve() {
        astroportViewModel.swapRateData.observe(viewLifecycleOwner) { response ->
            response?.let {
                onUpdateView(it.return_amount)
            }
        }
    }

//    private fun onDataObserve() {
//        neutronViewModel.pair.observe(viewLifecycleOwner) { response ->
//            response?.let { data ->
//                data.pairs.forEach { pair ->
//                    pair.asset_infos.forEach { assetInfo ->
//                        assetInfo.native_token?.let {
//                            if (!mAllDenoms.contains(it.denom)) {
//                                mAllDenoms.add(it.denom)
//                            }
//                        }
//                        assetInfo.token?.let {
//                            mAllDenoms.add(it.contract_addr)
//                        }
//                    }
//                }
//            }
//
//            mInputCoinDenom = baseActivity.mChainConfig.mainDenom()
//            mOutputCoinDenom = "ibc/C4CFF46FD6DE35CA4CF4CE031E643C8FDC9BA4B99AE598E9B0ED98FE3A2319F9"
//
////            if (mInputCoinDenom == null || mOutputCoinDenom == null) {
////                mInputCoinDenom = baseActivity.mChainConfig.mainDenom()
////                mOutputCoinDenom = "ibc/E8AC6B792CDE60AB208CA060CA010A3881F682A7307F624347AB71B6A0B0BF89"
////            }
////
////            if (mInputCoinDenom?.isNotEmpty() == true && mOutputCoinDenom?.isNotEmpty() == true) {
////                onUpdateView()
////            }
//        }
//    }

    private fun onUpdateView(swapAmount: String?) {
        binding.apply {
            WDp.setDpSymbolImg(baseDao, baseActivity.mChainConfig, inputCoinDenom, imgInputCoin)
            WDp.setDpSymbol(requireContext(), baseDao, baseActivity.mChainConfig, inputCoinDenom, txtInputCoin)
            inpusAmount.text = WDp.getDpAmount2(baseDao.getAvailable(inputCoinDenom), 6, 6)

            WDp.setDpSymbolImg(baseDao, baseActivity.mChainConfig, outputCoinDenom, imgOutputCoin)
            WDp.setDpSymbol(requireContext(), baseDao, baseActivity.mChainConfig, outputCoinDenom, txtOutputCoin)

            WDp.setDpSymbol(requireContext(), baseDao, baseActivity.mChainConfig, inputCoinDenom, inputsRateSymbol)
            WDp.setDpSymbol(requireContext(), baseDao, baseActivity.mChainConfig, outputCoinDenom, outputsRateSymbol)
            inputsRate.text = WDp.getDpAmount2(BigDecimal.ONE, 0, 6)

            WDp.setDpSymbol(requireContext(), baseDao, baseActivity.mChainConfig, inputCoinDenom, globalInputsRateSymbol)
            WDp.setDpSymbol(requireContext(), baseDao, baseActivity.mChainConfig, outputCoinDenom, globalOutputsRateSymbol)
            globalInputsRate.text = WDp.getDpAmount2(BigDecimal.ONE, 0, 6)

            outputsRate.text = WDp.getDpAmount2(BigDecimal(swapAmount), 6, 6)
            val priceInput = WDp.price(baseDao, baseDao.getAsset(baseActivity.mChainConfig, inputCoinDenom).coinGeckoId)
            val priceOutput = WDp.price(baseDao, baseDao.getAsset(baseActivity.mChainConfig, outputCoinDenom).coinGeckoId)
            if (priceInput.compareTo(BigDecimal.ZERO) == 0 || priceOutput.compareTo(BigDecimal.ZERO) == 0) {
                globalOutputsRate.text = "??????"
            } else {
                val priceRate = priceInput.divide(priceOutput, 6, RoundingMode.DOWN)
                globalOutputsRate.text = WDp.getDpAmount2(priceRate, 0, 6)
            }
        }
    }

    fun onClick() {
        binding.apply {
            btnToInputCoin.setOnClickListener {

            }

            btnToggle.setOnClickListener {
                val temp = inputCoinDenom
                inputCoinDenom = outputCoinDenom
                outputCoinDenom = temp

                astroportViewModel.loadSwapRateData(baseActivity.mChainConfig, Coin(inputCoinDenom, "1000000"), outputCoinDenom, "neutron1vwrktvvxnevy7s5t7v44z72pdxncnq9gdsjwq9607cdd6vl2lfcs33fpah")
            }

            btnToOutputCoin.setOnClickListener {

            }

            btnStartSwap.setOnClickListener {
                onClickSwap()
            }
        }
    }

    private fun onClickSwap() {
        if (!baseActivity.mAccount.hasPrivateKey && !baseActivity.mAccount.isLedger()) {
            baseActivity.onInsertKeyDialog()
            return
        }
        if (!WDp.isTxFeePayable(requireContext(), baseDao, baseActivity.mChainConfig)) {
            requireContext().makeToast(R.string.error_not_enough_fee)
            return
        }

        val inputBalance = baseDao.getAvailable(inputCoinDenom)
        if (BigDecimal.ZERO >= inputBalance) {
            requireContext().makeToast(R.string.error_not_enough_to_balance)
            return
        }

        Intent(requireContext(), NeutronSwapActivity::class.java).apply {
            putExtra("inputDenom", inputCoinDenom)
            putExtra("outputDenom", outputCoinDenom)
            startActivity(this)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}