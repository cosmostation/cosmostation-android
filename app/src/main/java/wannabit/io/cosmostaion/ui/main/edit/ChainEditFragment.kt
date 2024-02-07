package wannabit.io.cosmostaion.ui.main.edit

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.common.updateSelectButtonView
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.FragmentChainEditBinding
import wannabit.io.cosmostaion.ui.tx.step.BaseTxFragment
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel
import java.math.BigDecimal

class ChainEditFragment : BaseTxFragment() {

    private var _binding: FragmentChainEditBinding? = null
    private val binding get() = _binding

    private lateinit var chainEditAdapter: ChainEditAdapter

    private val walletViewModel: WalletViewModel by activityViewModels()

    private var toDisplayChainLines: MutableList<String> = mutableListOf()
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
                        sortCosmosLine()
                        searchChains.addAll(allCosmosLineChains)
                        toDisplayChainLines.addAll(Prefs.getDisplayChains(account))

                        withContext(Dispatchers.Main) {
                            recycler.setHasFixedSize(true)
                            recycler.layoutManager = LinearLayoutManager(requireContext())
                            chainEditAdapter = ChainEditAdapter(
                                account, searchChains, toDisplayChainLines, editClickAction
                            )
                            recycler.adapter = chainEditAdapter

                            btnSelect.updateSelectButtonView(allCosmosLineChains.none { !it.fetched })
                            progress.goneOrVisible(allCosmosLineChains.none { !it.fetched })
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
                    allCosmosLineChains.forEach { line ->
                        if (line.address?.isEmpty() == true) {
                            line.setInfoWithSeed(seed, line.setParentPath, lastHDPath)
                        }
                        if (!line.fetched) {
                            walletViewModel.loadChainData(line, id, true)
                        }
                    }

                } else if (type == BaseAccountType.PRIVATE_KEY) {
                    allCosmosLineChains.forEach { line ->
                        if (line.address?.isEmpty() == true) {
                            line.setInfoWithPrivateKey(privateKey)
                        }
                        if (!line.fetched) {
                            walletViewModel.loadChainData(line, id, true)
                        }
                    }
                }
            }
        }
    }

    private fun setupLoadedView() {
        walletViewModel.editFetchedResult.observe(viewLifecycleOwner) {
            lifecycleScope.launch(Dispatchers.IO) {
                BaseData.baseAccount?.let { account ->
                    val fetchedLine =
                        account.allCosmosLineChains.firstOrNull { line -> line.tag == it }
                    val index = account.allCosmosLineChains.indexOf(fetchedLine)
                    withContext(Dispatchers.Main) {
                        if (::chainEditAdapter.isInitialized) {
                            chainEditAdapter.notifyItemChanged(index)
                        }
                        binding?.btnSelect?.updateSelectButtonView(account.allCosmosLineChains.none { line -> !line.fetched })
                        binding?.progress?.goneOrVisible(account.allCosmosLineChains.none { line -> !line.fetched })
                    }
                }
            }
        }

        walletViewModel.chainDataErrorMessage.observe(viewLifecycleOwner) {
            return@observe
        }
    }

    private val editClickAction = object : ChainEditAdapter.SelectListener {
        override fun select(displayChainLines: MutableList<String>) {
            toDisplayChainLines = displayChainLines
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
                        allCosmosChains = reSortCosmosChains()

                        toDisplayChainLines.clear()
                        toDisplayChainLines.add("cosmos118")

                        allCosmosChains.filter { it.allAssetValue(true) > BigDecimal.ONE && it.tag != "cosmos118" }
                            .forEach { toDisplayChainLines.add(it.tag) }

                        valuableSortCosmosChains()
                        withContext(Dispatchers.Main) {
                            backdropLayout.visibility = View.GONE
                            chainEditAdapter.notifyItemRangeChanged(
                                1, allCosmosChains.size + 1, null
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
                    if (toDisplayChainLines.contains(chain.tag) && !chain.fetched) {
                        return@setOnClickListener
                    }
                }
                ApplicationViewModel.shared.walletEdit(toDisplayChainLines)
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
                    searchChains.clear()
                    BaseData.baseAccount?.let { account ->
                        if (StringUtils.isEmpty(newText)) {
                            searchChains.addAll(account.allCosmosLineChains)
                        } else {
                            newText?.let { searchTxt ->
                                searchChains.addAll(account.allCosmosLineChains.filter { chain ->
                                    chain.name.contains(searchTxt, ignoreCase = true)
                                })
                            }
                        }
                    }
                    if (searchChains.isEmpty()) {
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
        walletViewModel.editFetchedResult.removeObservers(viewLifecycleOwner)
    }
}