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
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin84
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.majorClass.SUI_MAIN_DENOM
import wannabit.io.cosmostaion.chain.suiCoinSymbol
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.FragmentCoinBinding
import wannabit.io.cosmostaion.ui.tx.step.CommonTransferFragment
import wannabit.io.cosmostaion.ui.tx.step.SendAssetType
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
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
            (arguments?.getParcelable(
                "selectedChain" + ""
            ) as? BaseChain)?.let {
                selectedChain = it
            }
        }
        binding.dropMoney.visibility = View.GONE
    }

    private fun sortAssets() {
        lifecycleScope.launch(Dispatchers.IO) {
            suiBalances.clear()
            searchSuiBalances.clear()
            suiNativeBalances.clear()
            searchSuiNativeBalances.clear()

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
                    suiNativeBalances.addAll(fetcher.suiBalances.drop(1))
                    searchSuiNativeBalances.addAll(suiNativeBalances)

                    withContext(Dispatchers.Main) {
                        initRecyclerView()
                        initSearchView(fetcher.suiBalances, suiBalances)
                    }
                }

            } else {
                (selectedChain as ChainBitCoin84).btcFetcher()?.let {
                    withContext(Dispatchers.Main) {
                        initRecyclerView()
                        binding.searchBar.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        if (isAdded) {
            majorCryptoAdapter = MajorCryptoAdapter(
                requireContext(), selectedChain, searchSuiBalances, searchSuiNativeBalances
            )
            binding.recycler.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = majorCryptoAdapter
                majorCryptoAdapter.notifyDataSetChanged()

                majorCryptoAdapter.setOnItemClickListener { chain, denom ->
                    val sendAssetType = if (chain is ChainSui) {
                        SendAssetType.SUI_COIN
                    } else {
                        SendAssetType.BIT_COIN
                    }
                    handleOneClickWithDelay(
                        CommonTransferFragment.newInstance(
                            chain, denom, sendAssetType
                        )
                    )
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
            if (!selectedChain.fetched) {
                binding.refresher.isRefreshing = false
            } else {
                BaseData.baseAccount?.let { account ->
                    selectedChain.fetched = false
                    if (selectedChain is ChainSui) {
                        ApplicationViewModel.shared.loadSuiData(account.id, selectedChain, false)
                    } else {
                        ApplicationViewModel.shared.loadBtcData(
                            account.id, selectedChain as ChainBitCoin84, false
                        )
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
            if (selectedChain.tag == tag && selectedChain.fetched) {
                sortAssets()
            }
        }

        ApplicationViewModel.shared.fetchedTokenResult.observe(viewLifecycleOwner) {
            if (selectedChain.tag == it) {
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