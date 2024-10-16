package wannabit.io.cosmostaion.ui.main

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
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.FetchState
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.concurrentForEach
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.common.updateSelectButtonView
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.FragmentChainEditBinding
import wannabit.io.cosmostaion.ui.tx.genTx.BaseTxFragment
import java.math.BigDecimal

class ChainEditFragment : BaseTxFragment() {

    private var _binding: FragmentChainEditBinding? = null
    private val binding get() = _binding

    private lateinit var chainEditAdapter: ChainEditAdapter

    private var mainnetChains = mutableListOf<BaseChain>()
    private var searchMainnetChains = mutableListOf<BaseChain>()
    private var testnetChains = mutableListOf<BaseChain>()
    private var searchTestnetChains = mutableListOf<BaseChain>()
    private var toDisplayChains: MutableList<String> = mutableListOf()

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
                        mainnetChains.addAll(account.allChains.filter { !it.isTestnet })
                        searchMainnetChains.addAll(mainnetChains)

                        testnetChains.addAll(account.allChains.filter { it.isTestnet })
                        searchTestnetChains.addAll(testnetChains)

                        toDisplayChains.addAll(Prefs.getDisplayChains(account))
                        initAllData(account)

                        withContext(Dispatchers.Main) {
                            recycler.setHasFixedSize(true)
                            recycler.layoutManager = LinearLayoutManager(requireContext())
                            chainEditAdapter = ChainEditAdapter(
                                searchMainnetChains,
                                searchTestnetChains,
                                toDisplayChains,
                                selectClickAction
                            )
                            recycler.adapter = chainEditAdapter

                            btnSelect.updateSelectButtonView(allChains.none { it.fetchState == FetchState.BUSY })
                            progress.goneOrVisible(allChains.none { it.fetchState == FetchState.BUSY })
                        }
                    }
                }
            }
        }
    }

    private fun initAllData(account: BaseAccount) {
        lifecycleScope.launch(Dispatchers.IO) {
            account.apply {
                if (type == BaseAccountType.MNEMONIC) {
                    allChains.asSequence().concurrentForEach { chain ->
                        if (chain.publicKey == null) {
                            chain.setInfoWithSeed(seed, chain.setParentPath, lastHDPath)
                        }
                        if (chain.fetchState == FetchState.IDLE || chain.fetchState == FetchState.FAIL) {
                            ApplicationViewModel.shared.loadChainData(chain, id, true)
                        }
                    }

                } else if (type == BaseAccountType.PRIVATE_KEY) {
                    allChains.asSequence().concurrentForEach { chain ->
                        if (chain.publicKey == null) {
                            chain.setInfoWithPrivateKey(privateKey)
                        }
                        if (chain.fetchState == FetchState.IDLE || chain.fetchState == FetchState.FAIL) {
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
            val searchMainnetResult = searchMainnetChains.filter { it.tag == tag }
            val evmIterator = searchMainnetResult.iterator()
            while (evmIterator.hasNext()) {
                val chain = evmIterator.next()
                val index = searchMainnetChains.indexOf(chain)
                if (::chainEditAdapter.isInitialized) {
                    withContext(Dispatchers.Main) {
                        chainEditAdapter.notifyItemChanged(index + 1)
                    }
                }
            }

            val searchTestnetResult = searchTestnetChains.filter { it.tag == tag }
            val iterator = searchTestnetResult.iterator()
            while (iterator.hasNext()) {
                val chain = iterator.next()
                val index = searchTestnetChains.indexOf(chain)
                if (::chainEditAdapter.isInitialized) {
                    withContext(Dispatchers.Main) {
                        chainEditAdapter.notifyItemChanged(index + (searchMainnetChains.size + 2))
                    }
                }
            }

            withContext(Dispatchers.Main) {
                if (searchMainnetChains.none { it.fetchState == FetchState.BUSY } && searchTestnetChains.none { it.fetchState == FetchState.BUSY }) {
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
        override fun select(displayChains: MutableList<String>) {
            toDisplayChains = displayChains
        }
    }

    private fun setUpClickAction() {
        binding?.apply {
            btnSelect.setOnClickListener {
                toDisplayChains.clear()
                if (btnSelect.isEnabled) btnSelect.isEnabled = false
                backdropLayout.visibility = View.VISIBLE

                lifecycleScope.launch(Dispatchers.IO) {
                    BaseData.baseAccount?.let { account ->
                        account.reSortChains()
                        mainnetChains = account.allChains.filter { !it.isTestnet }.toMutableList()
                        toDisplayChains.add("cosmos118")
                        for (chain in mainnetChains) {
                            if (chain.allValue(true) > BigDecimal.ONE && chain.tag != "cosmos118") {
                                toDisplayChains.add(chain.tag)
                            }
                        }
                    }

                    withContext(Dispatchers.Main) {
                        btnSelect.isEnabled = true
                        backdropLayout.visibility = View.GONE
                        chainEditAdapter.notifyItemRangeChanged(
                            1, mainnetChains.size + testnetChains.size + 1, null
                        )
                    }
                }
            }

            btnConfirm.setOnClickListener {
                ApplicationViewModel.shared.walletEdit(toDisplayChains)
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
                    searchMainnetChains.clear()
                    searchTestnetChains.clear()
                    BaseData.baseAccount?.let { account ->
                        if (StringUtils.isEmpty(newText)) {
                            searchMainnetChains.addAll(account.allChains.filter { !it.isTestnet })
                            searchTestnetChains.addAll(account.allChains.filter { it.isTestnet })

                        } else {
                            newText?.let { searchTxt ->
                                searchMainnetChains.addAll(account.allChains.filter { chain ->
                                    chain.name.contains(
                                        searchTxt,
                                        ignoreCase = true
                                    ) && !chain.isTestnet
                                })

                                searchTestnetChains.addAll(account.allChains.filter { chain ->
                                    chain.name.contains(
                                        searchTxt,
                                        ignoreCase = true
                                    ) && chain.isTestnet
                                })
                            }
                        }
                    }
                    if (searchMainnetChains.isEmpty() && searchTestnetChains.isEmpty()) {
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