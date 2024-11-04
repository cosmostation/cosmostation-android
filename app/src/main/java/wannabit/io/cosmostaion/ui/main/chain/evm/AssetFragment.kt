package wannabit.io.cosmostaion.ui.main.chain.evm

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
import wannabit.io.cosmostaion.chain.FetchState
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.FragmentAssetBinding
import wannabit.io.cosmostaion.ui.main.TokenEditFragment
import wannabit.io.cosmostaion.ui.main.TokenEditListener
import wannabit.io.cosmostaion.ui.tx.genTx.CommonTransferFragment
import wannabit.io.cosmostaion.ui.tx.genTx.SendAssetType
import java.math.BigDecimal

interface AssetFragmentInteraction {
    fun showTokenList()
}

class AssetFragment : Fragment(), AssetFragmentInteraction {

    private var _binding: FragmentAssetBinding? = null
    private val binding get() = _binding!!

    private lateinit var assetAdapter: AssetAdapter

    private lateinit var selectedEvmChain: BaseChain
    private var ethCoins = mutableListOf<BaseChain>()
    private var searchEthCoins = mutableListOf<BaseChain>()
    private var allErc20Tokens = mutableListOf<Token>()
    private var displayErc20Tokens = mutableListOf<Token>()
    private var searchDisplayErc20Tokens = mutableListOf<Token>()

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedEvmChain: BaseChain): AssetFragment {
            val args = Bundle().apply {
                putParcelable("selectedEvmChain", selectedEvmChain)
            }
            val fragment = AssetFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAssetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        sortAssets()
        refreshData()
        observeViewModels()
        initSearchView()
    }

    private fun initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("selectedEvmChain", BaseChain::class.java)
                ?.let { selectedEvmChain = it }
        } else {
            (arguments?.getParcelable("selectedEvmChain") as? BaseChain)?.let {
                selectedEvmChain = it
            }
        }
    }

    private fun sortAssets() {
        val evmTokens = mutableListOf<Token>()
        evmTokens.clear()
        ethCoins.clear()
        searchEthCoins.clear()
        allErc20Tokens.clear()
        displayErc20Tokens.clear()
        searchDisplayErc20Tokens.clear()

        lifecycleScope.launch(Dispatchers.IO) {
            ethCoins.add(selectedEvmChain)
            searchEthCoins.addAll(ethCoins)
            selectedEvmChain.evmRpcFetcher?.evmTokens?.let { evmTokens.addAll(it) }
            evmTokens.sortBy { it.symbol.lowercase() }

            BaseData.baseAccount?.let { account ->
                Prefs.getDisplayErc20s(account.id, selectedEvmChain.tag)?.let { userCustomTokens ->
                    evmTokens.sortWith { token0, token1 ->
                        val address0 = token0.contract
                        val address1 = token1.contract

                        val containsToken0 = userCustomTokens.contains(address0)
                        val containsToken1 = userCustomTokens.contains(address1)

                        when {
                            containsToken0 && !containsToken1 -> -1
                            !containsToken0 && containsToken1 -> 1
                            else -> {
                                val value0 = selectedEvmChain.evmRpcFetcher?.tokenValue(address0)
                                    ?: BigDecimal.ZERO
                                val value1 = selectedEvmChain.evmRpcFetcher?.tokenValue(address1)
                                    ?: BigDecimal.ZERO
                                value1.compareTo(value0)
                            }
                        }
                    }
                    evmTokens.forEach { token ->
                        if (userCustomTokens.contains(token.contract) && !displayErc20Tokens.contains(
                                token
                            )
                        ) {
                            displayErc20Tokens.add(token)
                        }
                    }

                } ?: run {
                    evmTokens.sortWith { o1, o2 ->
                        when {
                            BigDecimal.ZERO < o1.amount?.toBigDecimal() && BigDecimal.ZERO >= o2.amount?.toBigDecimal() -> -1
                            BigDecimal.ZERO >= o1.amount?.toBigDecimal() && BigDecimal.ZERO < o2.amount?.toBigDecimal() -> 1
                            else -> {
                                val value0 = selectedEvmChain.evmRpcFetcher?.tokenValue(o1.contract)
                                    ?: BigDecimal.ZERO
                                val value1 = selectedEvmChain.evmRpcFetcher?.tokenValue(o2.contract)
                                    ?: BigDecimal.ZERO
                                value1.compareTo(value0)
                            }
                        }
                    }

                    evmTokens.forEach { token ->
                        if (token.amount?.toBigDecimal()!! > BigDecimal.ZERO && !displayErc20Tokens.contains(
                                token
                            )
                        ) {
                            displayErc20Tokens.add(token)
                        }
                    }
                }
            }
            searchDisplayErc20Tokens.addAll(displayErc20Tokens)

            withContext(Dispatchers.Main) {
                allErc20Tokens.addAll(evmTokens)
                initRecyclerView()
            }
        }
    }

    private fun initRecyclerView() {
        assetAdapter = AssetAdapter(selectedEvmChain, searchEthCoins, searchDisplayErc20Tokens)
        binding.recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = assetAdapter
            assetAdapter.notifyDataSetChanged()

            assetAdapter.setOnItemClickListener { evmChain, denom ->
                val sendAssetType = if (denom.isNotEmpty()) {
                    SendAssetType.ONLY_EVM_ERC20
                } else {
                    SendAssetType.ONLY_EVM_COIN
                }
                handleOneClickWithDelay(
                    CommonTransferFragment.newInstance(
                        evmChain, denom, sendAssetType
                    )
                )
            }
        }
        binding.refresher.isRefreshing = false
    }

    private fun refreshData() {
        binding.refresher.setOnRefreshListener {
            if (selectedEvmChain.fetchState == FetchState.BUSY) {
                binding.refresher.isRefreshing = false
            } else {
                BaseData.baseAccount?.let { account ->
                    ApplicationViewModel.shared.loadEvmChainData(
                        selectedEvmChain, account.id, false
                    )
                }
            }
        }
    }

    private fun observeViewModels() {
        ApplicationViewModel.shared.hideValueResult.observe(viewLifecycleOwner) {
            if (::assetAdapter.isInitialized) {
                assetAdapter.notifyDataSetChanged()
            }
        }

        ApplicationViewModel.shared.fetchedResult.observe(viewLifecycleOwner) { tag ->
            if (selectedEvmChain.tag == tag) {
                sortAssets()
            }
        }

        ApplicationViewModel.shared.fetchedTokenResult.observe(viewLifecycleOwner) { tag ->
            if (selectedEvmChain.tag == tag) {
                sortAssets()
            }
        }
    }

    private fun initSearchView() {
        binding.apply {
            searchBar.visibleOrGone(searchEthCoins.size + searchDisplayErc20Tokens.size > 15)
            searchView.setQuery("", false)
            searchView.clearFocus()
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    searchEthCoins.clear()
                    searchDisplayErc20Tokens.clear()

                    if (StringUtils.isEmpty(newText)) {
                        searchEthCoins.addAll(ethCoins)
                        searchDisplayErc20Tokens.addAll(displayErc20Tokens)

                    } else {
                        newText?.let { searchTxt ->
                            searchEthCoins.addAll(ethCoins.filter { coin ->
                                coin.coinSymbol.contains(searchTxt, true)
                            })

                            searchDisplayErc20Tokens.addAll(displayErc20Tokens.filter { token ->
                                token.symbol.contains(searchTxt, true)
                            })
                        }
                    }
                    if (searchEthCoins.isEmpty() && searchDisplayErc20Tokens.isEmpty()) {
                        emptyLayout.visibility = View.VISIBLE
                        recycler.visibility = View.GONE
                    } else {
                        emptyLayout.visibility = View.GONE
                        recycler.visibility = View.VISIBLE
                        assetAdapter.notifyDataSetChanged()
                    }
                    return true
                }
            })
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

    override fun showTokenList() {
        TokenEditFragment.newInstance(selectedEvmChain,
            allErc20Tokens,
            displayErc20Tokens.map { it.contract }.toMutableList(),
            object : TokenEditListener {
                override fun edit(displayErc20Tokens: MutableList<String>) {
                    BaseData.baseAccount?.let { account ->
                        ApplicationViewModel.shared.loadEvmChainData(
                            selectedEvmChain, account.id, false
                        )
                    }
                }
            }).show(
            requireActivity().supportFragmentManager, TokenEditFragment::class.java.name
        )
    }

    override fun onDestroyView() {
        _binding = null
        ApplicationViewModel.shared.fetchedResult.removeObservers(viewLifecycleOwner)
        ApplicationViewModel.shared.fetchedTokenResult.removeObservers(viewLifecycleOwner)
        super.onDestroyView()
    }
}