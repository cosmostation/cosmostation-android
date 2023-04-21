package wannabit.io.cosmostaion.fragment.txs.neutron

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.databinding.FragmentNeutronSwapBinding
import wannabit.io.cosmostaion.model.viewModel.NeutronViewModel
import wannabit.io.cosmostaion.utils.WDp
import java.math.BigDecimal

class NeutronSwapFragment : BaseFragment() {

    private var _binding: FragmentNeutronSwapBinding? = null
    private val binding get() = _binding!!

    private val neutronViewModel: NeutronViewModel by activityViewModels()

    private var mInputCoinDenom: String? = ""
    private var mOutputCoinDenom: String? = ""
    private var mAvailableMaxAmount: BigDecimal = BigDecimal.ZERO

    private var mAllDenoms = ArrayList<String>()
    private var mSwapableDenoms = ArrayList<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNeutronSwapBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnToggle.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.color_neutron)
        onDataObserve()
        onClick()
        onClickSwap()
    }

    private fun onDataObserve() {
        neutronViewModel.pair.observe(viewLifecycleOwner) { response ->
            response?.let { data ->
                data.pairs.forEach { pair ->
                    pair.asset_infos.forEach { assetInfo ->
                        assetInfo.native_token?.let {
                            if (!mAllDenoms.contains(it.denom)) {
                                mAllDenoms.add(it.denom)
                            }
                        }
                        assetInfo.token?.let {
                            mAllDenoms.add(it.contract_addr)
                        }
                    }
                }
            }

            mInputCoinDenom = baseActivity.mChainConfig.mainDenom()
            mOutputCoinDenom = "ibc/C4CFF46FD6DE35CA4CF4CE031E643C8FDC9BA4B99AE598E9B0ED98FE3A2319F9"

//            if (mInputCoinDenom == null || mOutputCoinDenom == null) {
//                mInputCoinDenom = baseActivity.mChainConfig.mainDenom()
//                mOutputCoinDenom = "ibc/E8AC6B792CDE60AB208CA060CA010A3881F682A7307F624347AB71B6A0B0BF89"
//            }
//
//            if (mInputCoinDenom?.isNotEmpty() == true && mOutputCoinDenom?.isNotEmpty() == true) {
//                onUpdateView()
//            }
        }
    }

    private fun onUpdateView() {
        WDp.setDpSymbolImg(baseDao, baseActivity.mChainConfig, mInputCoinDenom, binding.imgInputCoin)
        WDp.setDpSymbol(requireContext(), baseDao, baseActivity.mChainConfig, mInputCoinDenom, binding.txtInputCoin)
        WDp.setDpSymbolImg(baseDao, baseActivity.mChainConfig, mOutputCoinDenom, binding.imgOutputCoin)
        WDp.setDpSymbol(requireContext(), baseDao, baseActivity.mChainConfig, mOutputCoinDenom, binding.txtOutputCoin)
    }

    fun onClick() {
        binding.btnToInputCoin.setOnClickListener {

        }

        binding.btnToggle.setOnClickListener {
            val temp = mInputCoinDenom
            mInputCoinDenom = mOutputCoinDenom
            mOutputCoinDenom = temp
            onUpdateView()
        }

        binding.btnToOutputCoin.setOnClickListener {

        }
    }

    fun onClickSwap() {
        binding.btnStartSwap.setOnClickListener {

        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}