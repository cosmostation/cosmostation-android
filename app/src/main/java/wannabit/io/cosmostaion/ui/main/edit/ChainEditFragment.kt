package wannabit.io.cosmostaion.ui.main.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.concurrentForEach
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.common.updateSelectButtonView
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.FragmentChainEditBinding
import wannabit.io.cosmostaion.ui.tx.step.BaseTxFragment
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import java.math.BigDecimal

class ChainEditFragment : BaseTxFragment() {

    private var _binding: FragmentChainEditBinding? = null
    private val binding get() = _binding

    private lateinit var chainEditAdapter: ChainEditAdapter

    private var toDisplayEvmChains: MutableList<String> = mutableListOf()
    private var allEvmChains: MutableList<EthereumLine> = mutableListOf()
    private var searchEvmChains: MutableList<EthereumLine> = mutableListOf()

    private var toDisplayChains: MutableList<String> = mutableListOf()
    private var allCosmosChains: MutableList<CosmosLine> = mutableListOf()
    private var searchChains: MutableList<CosmosLine> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChainEditBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLoadData()
        setupLoadedView()
        initSearchView()
        setUpClickAction()
    }

    private fun initLoadData() {
        binding?.apply {
            BaseData.baseAccount?.let { account ->
                account.apply {
                    lifecycleScope.launch(Dispatchers.IO) {
                        sortLine()
                        allEvmChains.addAll(allEvmLineChains)
                        searchEvmChains.addAll(allEvmChains)
                        toDisplayEvmChains.addAll(Prefs.getDisplayEvmChains(account))

                        allCosmosChains.addAll(allCosmosLineChains)
                        searchChains.addAll(allCosmosChains)
                        toDisplayChains.addAll(Prefs.getDisplayChains(account))

                        withContext(Dispatchers.Main) {
                            recycler.setHasFixedSize(true)
                            recycler.layoutManager = LinearLayoutManager(requireContext())
                            chainEditAdapter = ChainEditAdapter(
                                account,
                                searchEvmChains,
                                toDisplayEvmChains,
                                searchChains,
                                toDisplayChains,
                                selectClickAction
                            )
                            recycler.adapter = chainEditAdapter

                            btnSelect.updateSelectButtonView(allEvmChains.none { !it.fetched } && allCosmosChains.none { !it.fetched })
                            progress.goneOrVisible(allEvmChains.none { !it.fetched } && allCosmosChains.none { !it.fetched })
                        }
                        initAllData(account)
                    }
                }
            }
        }
    }

    private fun initAllData(account: BaseAccount) {
        lifecycleScope.launch(Dispatchers.IO) {
            account.apply {
                if (type == BaseAccountType.MNEMONIC) {
                    allEvmChains.asSequence().concurrentForEach { chain ->
                        if (chain.address?.isEmpty() == true) {
                            chain.setInfoWithSeed(seed, chain.setParentPath, lastHDPath)
                        }
                        if (!chain.fetched) {
                            ApplicationViewModel.shared.loadEvmChainData(chain, id, true)
                        }
                    }

                    allCosmosChains.asSequence().concurrentForEach { chain ->
                        if (chain.address?.isEmpty() == true) {
                            chain.setInfoWithSeed(seed, chain.setParentPath, lastHDPath)
                        }
                        if (!chain.fetched) {
                            ApplicationViewModel.shared.loadChainData(chain, id, true)
                        }
                    }

                } else if (type == BaseAccountType.PRIVATE_KEY) {
                    allEvmChains.asSequence().concurrentForEach { chain ->
                        if (chain.address?.isEmpty() == true) {
                            chain.setInfoWithPrivateKey(privateKey)
                        }
                        if (!chain.fetched) {
                            ApplicationViewModel.shared.loadEvmChainData(chain, id, true)
                        }
                    }

                    allCosmosChains.asSequence().concurrentForEach { chain ->
                        if (chain.address?.isEmpty() == true) {
                            chain.setInfoWithPrivateKey(privateKey)
                        }
                        if (!chain.fetched) {
                            ApplicationViewModel.shared.loadChainData(chain, id, true)
                        }
                    }
                }
            }
        }
    }

    private fun setupLoadedView() {
        ApplicationViewModel.shared.editFetchedResult.observe(viewLifecycleOwner) { tag ->
            updateRowData(tag)
        }

        ApplicationViewModel.shared.editFetchedTokenResult.observe(viewLifecycleOwner) { tag ->
            updateRowData(tag)
        }
    }

    private fun updateRowData(tag: String) {
        CoroutineScope(Dispatchers.IO).launch {
            for (i in 0 until searchEvmChains.size) {
                if (searchEvmChains[i].tag == tag) {
                    withContext(Dispatchers.Main) {
                        if (::chainEditAdapter.isInitialized) {
                            chainEditAdapter.notifyItemChanged(i + 1)
                        }
                    }
                }
            }

            for (i in 0 until searchChains.size) {
                if (searchChains[i].tag == tag) {
                    withContext(Dispatchers.Main) {
                        if (::chainEditAdapter.isInitialized) {
                            chainEditAdapter.notifyItemChanged(i + (searchEvmChains.size + 2))
                        }
                    }
                }
            }

            withContext(Dispatchers.Main) {
                if (searchEvmChains.none { !it.fetched } && searchChains.none { !it.fetched }) {
                    binding?.apply {
                        btnSelect.updateSelectButtonView(true)
                        progress.cancelAnimation()
                        progress.visibility = View.GONE
                    }
                }
            }
        }
    }

    private val selectClickAction = object : ChainEditAdapter.SelectListener {
        override fun evmSelect(displayEvmChains: MutableList<String>) {
            toDisplayEvmChains = displayEvmChains
        }

        override fun select(displayChains: MutableList<String>) {
            toDisplayChains = displayChains
        }
    }

    private fun setUpClickAction() {
        binding?.apply {
            btnSelect.setOnClickListener {
                backdropLayout.visibility = View.VISIBLE
                toDisplayEvmChains.clear()
                toDisplayChains.clear()
                if (btnSelect.isEnabled) {
                    btnSelect.isEnabled = false

                    lifecycleScope.launch(Dispatchers.IO) {
                        BaseData.baseAccount?.let { account ->
                            account.reSortEvmChains()
                            allEvmChains = account.allEvmLineChains

                            for (chain in allEvmChains) {
                                if (chain.allAssetValue(true) > BigDecimal.ONE) {
                                    toDisplayEvmChains.add(chain.tag)
                                }
                            }

                            account.reSortCosmosChains()
                            allCosmosChains = account.allCosmosLineChains
                            toDisplayChains.add("cosmos118")

                            for (chain in allCosmosChains) {
                                if (chain.allAssetValue(true) > BigDecimal.ONE && chain.tag != "cosmos118") {
                                    toDisplayChains.add(chain.tag)
                                }
                            }
                        }

                        withContext(Dispatchers.Main) {
                            btnSelect.isEnabled = true
                            backdropLayout.visibility = View.GONE
                            chainEditAdapter.notifyItemRangeChanged(
                                1, allEvmChains.size + allCosmosChains.size + 1, null
                            )
                        }
                    }
                }
            }

            btnConfirm.setOnClickListener {
                ApplicationViewModel.shared.walletEdit(toDisplayEvmChains, toDisplayChains)
                dismiss()
            }
        }
    }

    private fun initSearchView() {
        binding?.apply {
            searchView.setQuery("", false)
            searchView.clearFocus()
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    searchEvmChains.clear()
                    searchChains.clear()
                    BaseData.baseAccount?.let { account ->
                        if (StringUtils.isEmpty(newText)) {
                            searchEvmChains.addAll(account.allEvmLineChains)
                            searchChains.addAll(account.allCosmosLineChains)

                        } else {
                            newText?.let { searchTxt ->
                                searchEvmChains.addAll(account.allEvmLineChains.filter { chain ->
                                    chain.name.contains(searchTxt, ignoreCase = true)
                                })

                                searchChains.addAll(account.allCosmosLineChains.filter { chain ->
                                    chain.name.contains(searchTxt, ignoreCase = true)
                                })
                            }
                        }
                    }
                    if (searchEvmChains.isEmpty() && searchChains.isEmpty()) {
                        emptyLayout.visibility = View.VISIBLE
                        recycler.visibility = View.GONE
                    } else {
                        emptyLayout.visibility = View.GONE
                        recycler.visibility = View.VISIBLE
                        chainEditAdapter.notifyDataSetChanged()
                    }
                    return true
                }
            })
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
        ApplicationViewModel.shared.editFetchedResult.removeObservers(viewLifecycleOwner)
    }
}