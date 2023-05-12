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
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.databinding.FragmentNeutronSwapBinding
import wannabit.io.cosmostaion.dialog.SelectChainListDialog
import wannabit.io.cosmostaion.model.factory.neutron.AstroportViewModelProviderFactory
import wannabit.io.cosmostaion.model.repository.neutron.AstroportRepository
import wannabit.io.cosmostaion.model.viewModel.neutron.AstroportViewModel
import wannabit.io.cosmostaion.network.res.neutron.Pair
import wannabit.io.cosmostaion.network.res.neutron.ResPairData
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.makeToast
import java.math.BigDecimal

class NeutronSwapFragment : BaseFragment() {

    private var _binding: FragmentNeutronSwapBinding? = null
    private val binding get() = _binding!!

    private lateinit var astroportViewModel: AstroportViewModel

    private var swapPools: ArrayList<ResPairData> = arrayListOf()
    private var allPairs: ArrayList<Pair> = arrayListOf()
    private var swapAblePairs = arrayListOf<Pair>()
    private var selectedPool: ResPairData? = null
    private var inputCoin: Pair? = null
    private var outputCoin: Pair? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNeutronSwapBinding.inflate(layoutInflater, container, false)
        val astroportViewModelProviderFactory = AstroportViewModelProviderFactory(AstroportRepository())
        astroportViewModel = ViewModelProvider(this, astroportViewModelProviderFactory)[AstroportViewModel::class.java]

        baseActivity.onShowWaitDialog()
        loadDataObserve()
        astroportViewModel.loadSwapPairData(baseActivity.mChainConfig, BaseConstant.NEUTRON_TESTNET_ROUTER_ADDRESS)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnToggle.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.color_neutron)
        onClick()
    }

    private fun loadDataObserve() {
        astroportViewModel.swapPairData.observe(viewLifecycleOwner) { response ->
            response?.let { pairDataList ->
                swapPools = pairDataList.filter { item -> BigDecimal(item.total_share) != BigDecimal.ZERO } as ArrayList<ResPairData>

                swapPools.forEach { pool ->
                    pool.pairs.forEach { pair ->
                        if (allPairs.firstOrNull { item -> item.type == pair.type && item.address == pair.address && item.denom == pair.denom } == null) {
                            allPairs.add(pair)
                        }
                    }
                }
                selectedPool = swapPools[0]
                selectedPool?.let {
                    inputCoin = it.pairs[0]
                    outputCoin = it.pairs[1]
                }
                onUpdateView()
            }
        }
    }

    private fun onUpdateView() {
        binding.apply {
            baseActivity.onHideWaitDialog()
            WDp.setDpSymbolImg(baseDao, baseActivity.mChainConfig, inputCoin?.denom, imgInputCoin)
            WDp.setDpSymbol(requireContext(), baseDao, baseActivity.mChainConfig, inputCoin?.denom, txtInputCoin)
            inpusAmount.text = WDp.getDpAmount2(pairAvailable(), 6, 6)

            WDp.setDpSymbolImg(baseDao, baseActivity.mChainConfig, outputCoin?.denom, imgOutputCoin)
            WDp.setDpSymbol(requireContext(), baseDao, baseActivity.mChainConfig,  outputCoin?.denom, txtOutputCoin)
        }
    }

    fun onClick() {
        binding.apply {
            btnToInputCoin.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable(SelectChainListDialog.SELECT_PAIR_LIST_BUNDLE_KEY, allPairs)
                bundle.putInt(SelectChainListDialog.SELECT_CHAIN_LIST_BUNDLE_KEY, SelectChainListDialog.SELECT_INPUT_PAIR_CHAIN)
                val dialog = SelectChainListDialog.newInstance(bundle)
                dialog.show(parentFragmentManager, SelectChainListDialog::class.java.name)

                parentFragmentManager.setFragmentResultListener(SelectChainListDialog.SELECT_CHAIN_LIST_BUNDLE_KEY, viewLifecycleOwner) { _, bundle ->
                    val position = bundle.getInt(BaseConstant.POSITION)
                    inputCoin = allPairs[position]
                    inputCoin?.let {
                        swapPools.forEach { pool ->
                            pool.pairs.firstOrNull { item -> item.type == it.type && item.address == it.address && item.denom == it.denom }?.let {
                                selectedPool = pool
                            }
                        }
                        selectedPool?.let { pool ->
                            outputCoin = pool.pairs.firstOrNull { item -> item.type != it.type || item.address != it.address || item.denom != it.denom }
                        }
                    }
                    onUpdateView()
                }
            }

            btnToggle.setOnClickListener {
                val temp = inputCoin
                inputCoin = outputCoin
                outputCoin = temp
                onUpdateView()
            }

            btnToOutputCoin.setOnClickListener {
                val swapAblePools: ArrayList<ResPairData> = arrayListOf()
                swapAblePairs.clear()

                swapPools.forEach { pool ->
                    pool.pairs.firstOrNull { item -> item.type == inputCoin?.type && item.address == inputCoin?.address && item.denom == inputCoin?.denom }?.let {
                        swapAblePools.add(pool)
                    }
                }
                swapAblePools.forEach { pool ->
                    pool.pairs.filter { item -> item.type != inputCoin?.type || item.address != inputCoin?.address || item.denom != inputCoin?.denom }.forEach { pair ->
                        swapAblePairs.add(pair)
                    }
                }

                val bundle = Bundle()
                bundle.putSerializable(SelectChainListDialog.SELECT_PAIR_LIST_BUNDLE_KEY, swapAblePairs)
                bundle.putInt(SelectChainListDialog.SELECT_CHAIN_LIST_BUNDLE_KEY, SelectChainListDialog.SELECT_OUTPUT_PAIR_CHAIN)
                val dialog = SelectChainListDialog.newInstance(bundle)
                dialog.show(parentFragmentManager, SelectChainListDialog::class.java.name)

                parentFragmentManager.setFragmentResultListener(SelectChainListDialog.SELECT_CHAIN_LIST_BUNDLE_KEY, viewLifecycleOwner) { _, bundle ->
                    val position = bundle.getInt(BaseConstant.POSITION)
                    outputCoin = swapAblePairs[position]
                    swapPools.forEach { pool ->
                        pool.pairs.firstOrNull { item -> item.type == inputCoin?.type && item.address == inputCoin?.address && item.denom == inputCoin?.denom }?.let {
                            pool.pairs.firstOrNull {item -> item.type == outputCoin?.type && item.address == outputCoin?.address && item.denom == outputCoin?.denom }?.let {
                                selectedPool = pool
                            }
                        }
                    }
                    onUpdateView()
                }
            }

            btnStartSwap.setOnClickListener {
                onClickSwap()
            }
        }
    }

    private fun onClickSwap() {
        if (!baseActivity.mAccount.hasPrivateKey && !baseActivity.mAccount.isLedger) {
            baseActivity.onInsertKeyDialog()
            return
        }
        if (!WDp.isTxFeePayable(requireContext(), baseDao, baseActivity.mChainConfig)) {
            requireContext().makeToast(R.string.error_not_enough_fee)
            return
        }

        if (BigDecimal.ZERO >= pairAvailable()) {
            requireContext().makeToast(R.string.error_not_enough_to_balance)
            return
        }

        Intent(requireContext(), NeutronSwapActivity::class.java).apply {
            putExtra("selectedPool", selectedPool)
            putExtra("inputCoin", inputCoin)
            putExtra("outputCoin", outputCoin)
            startActivity(this)
        }
    }

    private fun pairAvailable(): BigDecimal {
        selectedPool?.let {
            it.pairs.forEach { pair ->
                if (pair.denom == inputCoin?.denom) {
                    return if (pair.type == "cw20") {
                        baseDao.getCw20Asset(baseActivity.mChainConfig, inputCoin?.denom).getAmount()
                    } else {
                        baseDao.getAvailable(inputCoin?.denom)
                    }
                }
            }
        }
        return BigDecimal.ZERO
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}