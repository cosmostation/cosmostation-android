package wannabit.io.cosmostaion.ui.main.chain.major

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.FetchState
import wannabit.io.cosmostaion.chain.fetcher.suiCoinSymbol
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin84
import wannabit.io.cosmostaion.chain.majorClass.ChainNamada
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.majorClass.NAMADA_MAIN_DENOM
import wannabit.io.cosmostaion.chain.majorClass.SUI_MAIN_DENOM
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.model.res.Coin
import wannabit.io.cosmostaion.data.model.res.CoinType
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.databinding.FragmentCoinBinding
import wannabit.io.cosmostaion.ui.tx.genTx.CommonTransferFragment
import wannabit.io.cosmostaion.ui.tx.genTx.SendAssetType
import java.math.BigDecimal

class MajorCryptoFragment : Fragment() {

    private var _binding: FragmentCoinBinding? = null
    private val binding get() = _binding!!

    private lateinit var majorCryptoAdapter: MajorCryptoAdapter

    private lateinit var selectedChain: BaseChain

    private var suiBalances: MutableList<Pair<String?, BigDecimal?>> = mutableListOf()
    private var searchSuiBalances: MutableList<Pair<String?, BigDecimal?>> = mutableListOf()
    private var suiNativeBalances: MutableList<Pair<String?, BigDecimal?>> = mutableListOf()
    private var searchSuiNativeBalances: MutableList<Pair<String?, BigDecimal?>> = mutableListOf()
    private val stakeCoins = mutableListOf<Coin>()
    private val searchStakeCoins = mutableListOf<Coin>()
    private val nativeCoins = mutableListOf<Coin>()
    private val searchNativeCoins = mutableListOf<Coin>()
    private val ibcCoins = mutableListOf<Coin>()
    private val searchIbcCoins = mutableListOf<Coin>()

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): MajorCryptoFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = MajorCryptoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        sortAssets()
        refreshData()
        observeViewModels()
    }

    private fun initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("selectedChain", BaseChain::class.java)
                ?.let { selectedChain = it }
        } else {
            (arguments?.getParcelable("selectedChain") as? BaseChain)?.let { selectedChain = it }
        }
        binding.apply {
            dropMoney.visibility = View.GONE
            dydxTrade.visibility = View.GONE
        }
    }

    private fun sortAssets() {
        lifecycleScope.launch(Dispatchers.IO) {
            suiBalances.clear()
            searchSuiBalances.clear()
            suiNativeBalances.clear()
            searchSuiNativeBalances.clear()
            stakeCoins.clear()
            searchStakeCoins.clear()
            nativeCoins.clear()
            searchNativeCoins.clear()
            ibcCoins.clear()
            searchIbcCoins.clear()

            if (selectedChain is ChainSui) {
                (selectedChain as ChainSui).suiFetcher()?.let { fetcher ->
                    val tempSuiBalances: MutableList<Pair<String?, BigDecimal?>> = mutableListOf()
                    tempSuiBalances.addAll(fetcher.suiBalances)

                    if (tempSuiBalances.none { it.first == SUI_MAIN_DENOM }) {
                        tempSuiBalances.add(Pair(SUI_MAIN_DENOM, BigDecimal.ZERO))
                    }
                    synchronized(tempSuiBalances) {
                        tempSuiBalances.sortWith { o1, o2 ->
                            when {
                                o1.first == SUI_MAIN_DENOM -> -1
                                o2.first == SUI_MAIN_DENOM -> 1
                                else -> {
                                    val value0 = fetcher.suiBalanceValue(o1.first ?: "")
                                    val value1 = fetcher.suiBalanceValue(o2.first ?: "")
                                    value1.compareTo(value0)
                                }
                            }
                        }
                    }
                    tempSuiBalances.firstOrNull()?.let { suiBalances.add(it) }
                    searchSuiBalances.addAll(suiBalances)
                    suiNativeBalances.addAll(tempSuiBalances.drop(1))
                    searchSuiNativeBalances.addAll(suiNativeBalances)

                    withContext(Dispatchers.Main) {
                        initRecyclerView()
                        initSearchView(fetcher.suiBalances, suiBalances)
                    }
                }

            } else if (selectedChain is ChainBitCoin84) {
                (selectedChain as ChainBitCoin84).btcFetcher()?.let {
                    withContext(Dispatchers.Main) {
                        initRecyclerView()
                        binding.searchBar.visibility = View.GONE
                    }
                }

            } else {
                (selectedChain as ChainNamada).namadaFetcher()?.let { fetcher ->
                    fetcher.namadaBalances?.forEach { coin ->
                        BaseData.getAsset(selectedChain.apiName, coin["tokenAddress"].asString)?.type?.let { coinType ->
                            when (coinType) {
                                "staking, native" -> {
                                    if (coin["tokenAddress"].asString == NAMADA_MAIN_DENOM) {
                                        stakeCoins.add(
                                            Coin(
                                                coin["tokenAddress"].asString, coin["balance"].asString, CoinType.STAKE
                                            )
                                        )
                                    } else {
                                        nativeCoins.add(
                                            Coin(
                                                coin["tokenAddress"].asString, coin["balance"].asString, CoinType.NATIVE
                                            )
                                        )
                                    }
                                }

                                "ibc" -> {
                                    ibcCoins.add(Coin(coin["tokenAddress"].asString, coin["balance"].asString, CoinType.IBC))
                                }
                            }
                        }
                    }
                    if (stakeCoins.none { it.denom == NAMADA_MAIN_DENOM }) {
                        stakeCoins.add(Coin(NAMADA_MAIN_DENOM, "0", CoinType.STAKE))
                    }
                    searchStakeCoins.addAll(stakeCoins)

                    nativeCoins.sortWith(compareByDescending {
                        fetcher.namadaBalanceValue(it.denom)
                    })
                    searchNativeCoins.addAll(nativeCoins)
                    ibcCoins.sortWith(compareByDescending {
                        fetcher.namadaBalanceValue(it.denom)
                    })
                    searchIbcCoins.addAll(ibcCoins)

                    withContext(Dispatchers.Main) {
                        initRecyclerView()
                        initSearchView(mutableListOf(), mutableListOf())
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        if (isAdded) {
            majorCryptoAdapter = MajorCryptoAdapter(
                requireContext(), selectedChain, searchSuiBalances, searchSuiNativeBalances, searchStakeCoins, searchNativeCoins, searchIbcCoins
            )
            binding.recycler.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = majorCryptoAdapter
                majorCryptoAdapter.notifyDataSetChanged()

                majorCryptoAdapter.setOnItemClickListener { chain, denom ->
                    val sendAssetType = if (chain is ChainSui) {
                        SendAssetType.SUI_COIN
                    } else if (chain is ChainBitCoin84) {
                        SendAssetType.BIT_COIN
                    } else {
                        SendAssetType.NAMADA_COIN
                    }

                    if (chain is ChainBitCoin84) {
                        chain.btcFetcher()?.let { fetcher ->
                            lifecycleScope.launch(Dispatchers.IO) {
                                val btcFee = fetcher.initFee()
                                withContext(Dispatchers.Main) {
                                    if (btcFee == null) return@withContext
                                    if (fetcher.btcBalances > btcFee) {
                                        handleOneClickWithDelay(
                                            CommonTransferFragment.newInstance(
                                                chain, denom, sendAssetType
                                            )
                                        )

                                    } else {
                                        if (fetcher.btcPendingInput > BigDecimal.ZERO) {
                                            requireContext().makeToast(R.string.error_pending_balance)
                                        } else {
                                            requireContext().makeToast(R.string.error_not_enough_fee)
                                        }
                                        return@withContext
                                    }
                                }
                            }
                        }

                    } else {
                        handleOneClickWithDelay(
                            CommonTransferFragment.newInstance(
                                chain, denom, sendAssetType
                            )
                        )
                    }
                }
            }
            binding.refresher.isRefreshing = false
        }
    }

    private fun initSearchView(
        suiBalances: MutableList<Pair<String?, BigDecimal?>>,
        tempSuiBalances: MutableList<Pair<String?, BigDecimal?>>
    ) {
        binding.apply {
            searchBar.visibleOrGone(suiBalances.size > 15)
            searchView.setQuery("", false)
            searchView.clearFocus()
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (selectedChain is ChainSui) {
                        searchSuiBalances.clear()
                        searchSuiNativeBalances.clear()

                        if (StringUtils.isEmpty(newText)) {
                            searchSuiBalances.addAll(tempSuiBalances)
                            searchSuiNativeBalances.addAll(suiNativeBalances)

                        } else {
                            newText?.let { searchTxt ->
                                searchSuiBalances.addAll(tempSuiBalances.filter { balance ->
                                    balance.first?.let { denom ->
                                        BaseData.getAsset(selectedChain.apiName, denom)
                                            ?.let { asset ->
                                                asset.symbol?.contains(searchTxt, ignoreCase = true)
                                            }
                                    } ?: false
                                })

                                searchSuiNativeBalances.addAll(suiNativeBalances.filter { balance ->
                                    balance.first?.let { denom ->
                                        val asset = BaseData.getAsset(selectedChain.apiName, denom)
                                        val metaData =
                                            (selectedChain as ChainSui).suiFetcher?.suiCoinMeta?.get(
                                                denom
                                            )

                                        if (asset != null) {
                                            asset.symbol?.contains(searchTxt, ignoreCase = true)
                                        } else if (metaData != null) {
                                            metaData["symbol"].asString.contains(
                                                searchTxt, ignoreCase = true
                                            )
                                        } else {
                                            balance.first.suiCoinSymbol()
                                                ?.contains(searchTxt, ignoreCase = true)
                                        }
                                    } ?: false
                                })
                            }
                        }
                    }
                    if (searchSuiBalances.isEmpty() && searchSuiNativeBalances.isEmpty()) {
                        emptyLayout.visibility = View.VISIBLE
                        recycler.visibility = View.GONE
                    } else {
                        emptyLayout.visibility = View.GONE
                        recycler.visibility = View.VISIBLE
                        majorCryptoAdapter.notifyDataSetChanged()
                    }
                    return true
                }
            })
        }
    }

    private fun refreshData() {
        binding.refresher.setOnRefreshListener {
            if (selectedChain.fetchState == FetchState.BUSY) {
                binding.refresher.isRefreshing = false
            } else {
                BaseData.baseAccount?.let { account ->
                    selectedChain.fetchState = FetchState.IDLE
                    when (selectedChain) {
                        is ChainSui -> {
                            ApplicationViewModel.shared.loadSuiData(account.id, selectedChain, false)
                        }

                        is ChainBitCoin84 -> {
                            ApplicationViewModel.shared.loadBtcData(
                                account.id, selectedChain as ChainBitCoin84, false
                            )
                        }

                        else -> {
                            ApplicationViewModel.shared.loadNamadaData(
                                account.id, selectedChain as ChainNamada, false
                            )
                        }
                    }
                }
            }
        }
    }

    private fun observeViewModels() {
        ApplicationViewModel.shared.hideValueResult.observe(viewLifecycleOwner) {
            if (::majorCryptoAdapter.isInitialized) {
                majorCryptoAdapter.notifyDataSetChanged()
            }
        }

        ApplicationViewModel.shared.fetchedResult.observe(viewLifecycleOwner) { tag ->
            ApplicationViewModel.shared.notifyTxEvent()
            if (selectedChain.tag == tag) {
                sortAssets()
            }
        }
    }

    private fun handleOneClickWithDelay(bottomSheetDialogFragment: BottomSheetDialogFragment) {
        if (isClickable) {
            isClickable = false

            bottomSheetDialogFragment.show(
                requireActivity().supportFragmentManager, bottomSheetDialogFragment::class.java.name
            )

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 300)
        }
    }

    override fun onDestroyView() {
        _binding = null
        ApplicationViewModel.shared.fetchedResult.removeObservers(viewLifecycleOwner)
        ApplicationViewModel.shared.fetchedTokenResult.removeObservers(viewLifecycleOwner)
        super.onDestroyView()
    }
}