package wannabit.io.cosmostaion.fragment.txs.neutron.defi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.txs.neutron.defi.NeutronDefiActivity
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.databinding.FragmentNeutronSwapBinding
import wannabit.io.cosmostaion.model.factory.neutron.AstroportViewModelProviderFactory
import wannabit.io.cosmostaion.model.repository.neutron.AstroportRepository
import wannabit.io.cosmostaion.model.viewModel.neutron.AstroportViewModel
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.WLog
import java.math.BigDecimal

class NeutronSwapFragment : BaseFragment() {

    private var _binding: FragmentNeutronSwapBinding? = null
    private val binding get() = _binding!!

    private lateinit var astroportViewModel: AstroportViewModel

    private var mAllDenoms = ArrayList<String>()
    private var mInputCoinDenom: String? = ""
    private var mOutputCoinDenom: String? = ""

    private var mAvailableMaxAmount: BigDecimal = BigDecimal.ZERO
    private var mSwapableDenoms = ArrayList<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNeutronSwapBinding.inflate(layoutInflater, container, false)
        val astroportViewModelProviderFactory = AstroportViewModelProviderFactory(AstroportRepository())
        astroportViewModel = ViewModelProvider(this, astroportViewModelProviderFactory)[AstroportViewModel::class.java]

        getSActivity()?.let {
            astroportViewModel.loadSwapListData(it.mChainConfig, "neutron1jj0scx400pswhpjes589aujlqagxgcztw04srynmhf0f6zplzn2qqmhwj7")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnToggle.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.color_neutron)
        loadDataObserve()
        onClick()
        onClickSwap()
    }

    private fun loadDataObserve() {
        astroportViewModel.swapListData.observe(viewLifecycleOwner) { response ->
            response?.let { resPairData ->
                resPairData.pairs.forEach { pair ->
                    pair.asset_infos.forEach { assetInfo ->
                        assetInfo.native_token?.let {
                            if (!mAllDenoms.contains(it.denom)) {
                                mAllDenoms.add(it.denom)
                            }
                        }
                        assetInfo.token?.let {
//                            val asset = baseDao.getCw20Asset(getSActivity()?.mChainConfig, it.contract_addr)
//                            WLog.w("test1234 : " + )
                        }
                    }
                    WLog.w("test1234 : " + mAllDenoms.size)
                }
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

    private fun onUpdateView() {
        WDp.setDpSymbolImg(baseDao, baseActivity.mChainConfig, mInputCoinDenom, binding.imgInputCoin)
        WDp.setDpSymbol(requireContext(), baseDao, baseActivity.mChainConfig, mInputCoinDenom, binding.txtInputCoin)
        WDp.setDpSymbolImg(baseDao, baseActivity.mChainConfig, mOutputCoinDenom, binding.imgOutputCoin)
        WDp.setDpSymbol(requireContext(), baseDao, baseActivity.mChainConfig, mOutputCoinDenom, binding.txtOutputCoin)
    }

    fun onClick() {
        binding.apply {
            btnToInputCoin.setOnClickListener {

            }

            btnToggle.setOnClickListener {
                val temp = mInputCoinDenom
                mInputCoinDenom = mOutputCoinDenom
                mOutputCoinDenom = temp
                onUpdateView()
            }

            btnToOutputCoin.setOnClickListener {

            }

            btnStartSwap.setOnClickListener {
                onClickSwap()
            }
        }
    }

    private fun onClickSwap() {

    }

    private fun getSActivity(): NeutronDefiActivity? {
        return baseActivity as? NeutronDefiActivity
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}