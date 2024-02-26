package wannabit.io.cosmostaion.ui.main.edit

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.common.BaseData
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
                        searchEvmChains.addAll(allEvmLineChains)
                        toDisplayEvmChains.addAll(Prefs.getDisplayEvmChains(account))

                        searchChains.addAll(allCosmosLineChains)
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
                                evmClickAction,
                                cosmosClickAction
                            )
                            recycler.adapter = chainEditAdapter
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
                    allEvmLineChains.forEach { line ->
                        if (line.address?.isEmpty() == true) {
                            line.setInfoWithSeed(seed, line.setParentPath, lastHDPath)
                        }
                        if (!line.fetched) {
                            ApplicationViewModel.shared.loadEvmChainData(line, id, true)
                        }
                    }

                    allCosmosLineChains.forEach { line ->
                        if (line.address?.isEmpty() == true) {
                            line.setInfoWithSeed(seed, line.setParentPath, lastHDPath)
                        }
                        if (!line.fetched) {
                            ApplicationViewModel.shared.loadChainData(line, id, true)
                        }
                    }

                } else if (type == BaseAccountType.PRIVATE_KEY) {
                    allEvmLineChains.forEach { line ->
                        if (line.address?.isEmpty() == true) {
                            line.setInfoWithPrivateKey(privateKey)
                        }
                        if (!line.fetched) {
                            ApplicationViewModel.shared.loadEvmChainData(line, id, true)
                        }
                    }

                    allCosmosLineChains.forEach { line ->
                        if (line.address?.isEmpty() == true) {
                            line.setInfoWithPrivateKey(privateKey)
                        }
                        if (!line.fetched) {
                            ApplicationViewModel.shared.loadChainData(line, id, true)
                        }
                    }
                }
            }
        }
    }

    private fun setupLoadedView() {
        ApplicationViewModel.shared.editFetchedResult.observe(viewLifecycleOwner) {
            lifecycleScope.launch(Dispatchers.IO) {
                searchEvmChains.indexOf(searchEvmChains.firstOrNull { line -> line.tag == it })
                    .let { fetchedEvmChainIndex ->
                        withContext(Dispatchers.Main) {
                            if (::chainEditAdapter.isInitialized) {
                                chainEditAdapter.notifyItemChanged(fetchedEvmChainIndex + 1)
                            }
                        }
                    }

                searchChains.indexOf(searchChains.firstOrNull { line -> line.tag == it })
                    .let { fetchedChainIndex ->
                        withContext(Dispatchers.Main) {
                            if (::chainEditAdapter.isInitialized) {
                                chainEditAdapter.notifyItemChanged(fetchedChainIndex + searchEvmChains.size + 2)
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

        ApplicationViewModel.shared.chainDataErrorMessage.observe(viewLifecycleOwner) {
            return@observe
        }
    }

    private val evmClickAction = object : ChainEditAdapter.EvmSelectListener {
        override fun select(displayEvmChains: MutableList<String>) {
            toDisplayEvmChains = displayEvmChains
        }
    }

    private val cosmosClickAction = object : ChainEditAdapter.SelectListener {
        override fun select(displayChains: MutableList<String>) {
            toDisplayChains = displayChains
        }
    }

    private fun reSortEvmChains(): MutableList<EthereumLine> {
        BaseData.baseAccount?.let { account ->
            account.apply {
                allEvmLineChains.sortWith { o1, o2 ->
                    when {
                        o1.tag == "ethereum60" -> -1
                        o2.tag == "ethereum60" -> 1
                        else -> {
                            when {
                                o1.allAssetValue(true) > o2.allAssetValue(true) -> -1
                                o1.allAssetValue(true) < o2.allAssetValue(true) -> 1
                                else -> 0
                            }
                        }
                    }
                }
                return allEvmLineChains
            }
        }
        return mutableListOf()
    }

    private fun valuableSorEvmChains() {
        BaseData.baseAccount?.let { account ->
            allEvmChains.sortWith { o1, o2 ->
                when {
                    o1.tag == "ethereum60" -> -1
                    o2.tag == "ethereum60" -> 1
                    o1.allValue(true) > o2.allValue(true) -> -1
                    o1.allValue(true) < o2.allValue(true) -> 1
                    else -> 0
                }
            }

            allEvmChains.sortWith { o1, o2 ->
                when {
                    o1.tag == "ethereum60" -> -1
                    o2.tag == "ethereum60" -> 1
                    Prefs.getDisplayEvmChains(account)
                        .contains(o1.tag) && !Prefs.getDisplayEvmChains(
                        account
                    ).contains(o2.tag) -> -1

                    Prefs.getDisplayEvmChains(account)
                        .contains(o2.tag) && Prefs.getDisplayEvmChains(
                        account
                    ).contains(o1.tag) -> 1

                    else -> 0
                }
            }
        }
    }

    private fun reSortCosmosChains(): MutableList<CosmosLine> {
        BaseData.baseAccount?.let { account ->
            account.apply {
                allCosmosLineChains.sortWith { o1, o2 ->
                    when {
                        o1.tag == "cosmos118" -> -1
                        o2.tag == "cosmos118" -> 1
                        else -> {
                            when {
                                o1.allAssetValue(true) > o2.allAssetValue(true) -> -1
                                o1.allAssetValue(true) < o2.allAssetValue(true) -> 1
                                else -> 0
                            }
                        }
                    }
                }
                return allCosmosLineChains
            }
        }
        return mutableListOf()
    }

    private fun valuableSortCosmosChains() {
        BaseData.baseAccount?.let { account ->
            allCosmosChains.sortWith { o1, o2 ->
                when {
                    o1.tag == "cosmos118" -> -1
                    o2.tag == "cosmos118" -> 1
                    o1.allValue(true) > o2.allValue(true) -> -1
                    o1.allValue(true) < o2.allValue(true) -> 1
                    else -> 0
                }
            }

            allCosmosChains.sortWith { o1, o2 ->
                when {
                    o1.tag == "cosmos118" -> -1
                    o2.tag == "cosmos118" -> 1
                    Prefs.getDisplayChains(account).contains(o1.tag) && !Prefs.getDisplayChains(
                        account
                    ).contains(o2.tag) -> -1

                    Prefs.getDisplayChains(account).contains(o2.tag) && Prefs.getDisplayChains(
                        account
                    ).contains(o1.tag) -> 1

                    else -> 0
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding?.apply {
            btnSelect.setOnClickListener {
                if (btnSelect.isEnabled) {
                    btnSelect.isEnabled = false
                    backdropLayout.visibility = View.VISIBLE

                    lifecycleScope.launch(Dispatchers.IO) {
                        allEvmChains = reSortEvmChains()
                        allCosmosChains = reSortCosmosChains()

                        toDisplayEvmChains.clear()
                        toDisplayEvmChains.add("ethereum60")

                        toDisplayChains.clear()
                        toDisplayChains.add("cosmos118")

                        allEvmChains.filter { it.allAssetValue(true) > BigDecimal.ONE && it.tag != "ethereum60" }
                            .forEach { toDisplayEvmChains.add(it.tag) }
                        allCosmosChains.filter { it.allAssetValue(true) > BigDecimal.ONE && it.tag != "cosmos118" }
                            .forEach { toDisplayChains.add(it.tag) }

                        valuableSorEvmChains()
                        valuableSortCosmosChains()

                        withContext(Dispatchers.Main) {
                            backdropLayout.visibility = View.GONE
                            chainEditAdapter.notifyItemRangeChanged(
                                1, allEvmChains.size + allCosmosChains.size + 1, null
                            )
                        }
                    }

                    Handler(Looper.getMainLooper()).postDelayed({
                        btnSelect.isEnabled = true
                    }, 1000)
                }
            }

            btnConfirm.setOnClickListener {
                searchChains.forEach { chain ->
                    if (toDisplayChains.contains(chain.tag) && !chain.fetched) {
                        return@setOnClickListener
                    }
                }
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