package wannabit.io.cosmostaion.ui.main

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.concurrentForEach
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.toMoveAnimation
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.FragmentDashboardBinding
import wannabit.io.cosmostaion.ui.main.chain.cosmos.CosmosActivity
import wannabit.io.cosmostaion.ui.main.chain.evm.EvmActivity
import wannabit.io.cosmostaion.ui.main.setting.general.PushManager
import wannabit.io.cosmostaion.ui.option.notice.NoticeInfoFragment
import wannabit.io.cosmostaion.ui.option.notice.NoticeType
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel
import java.math.BigDecimal


class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding: FragmentDashboardBinding? get() = _binding

    private lateinit var dashAdapter: DashboardAdapter

    private val walletViewModel: WalletViewModel by activityViewModels()

    private var baseAccount: BaseAccount? = null

    private var mainnetChains = mutableListOf<BaseChain>()
    private var searchMainnetChains = mutableListOf<BaseChain>()
    private var testnetChains = mutableListOf<BaseChain>()
    private var searchTestnetChains = mutableListOf<BaseChain>()

    private var totalChainValue: BigDecimal = BigDecimal.ZERO

    private var isNew: Boolean = false

    companion object {
        @JvmStatic
        fun newInstance(baseAccount: BaseAccount?): DashboardFragment {
            val args = Bundle().apply {
                putParcelable("baseAccount", baseAccount)
            }
            val fragment = DashboardFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModels()
        initView()
        setupHideButton()
        refreshData()
        initSearchView()
    }

    override fun onResume() {
        super.onResume()
        updateView()
    }

    private fun initView() {
        binding?.apply {
            baseAccount = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("baseAccount", BaseAccount::class.java)
            } else {
                arguments?.getParcelable("baseAccount") as? BaseAccount
            }
            initData(baseAccount)
            updateViewWithLoadedData(baseAccount)

            updateHideValue()
            btnHide.setColorFilter(
                ContextCompat.getColor(requireContext(), R.color.color_base03),
                PorterDuff.Mode.SRC_IN
            )
        }
    }

    private fun initData(baseAccount: BaseAccount?) {
        searchMainnetChains.clear()
        searchTestnetChains.clear()

        baseAccount?.let { account ->
            mainnetChains =
                account.sortedDisplayChains().filter { chain -> !chain.isTestnet }.toMutableList()
            searchMainnetChains.addAll(mainnetChains)

            testnetChains =
                account.sortedDisplayChains().filter { chain -> chain.isTestnet }.toMutableList()
            searchTestnetChains.addAll(testnetChains)
        }
    }

    private fun updateViewWithLoadedData(baseAccount: BaseAccount?) {
        initDisplayData(baseAccount)
        initRecyclerView()
        setupLoadedData()

        binding?.searchView?.setQuery("", false)
        binding?.searchView?.clearFocus()
    }

    private fun initRecyclerView() {
        dashAdapter = DashboardAdapter(
            requireContext(),
            searchMainnetChains,
            searchTestnetChains,
            listener = nodeDownCheckAction
        )

        binding?.recycler?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = dashAdapter
            itemAnimator = null
        }
    }

    private val nodeDownCheckAction = object : DashboardAdapter.NodeDownListener {
        override fun nodeDown(chain: BaseChain) {
            if (!chain.fetched) return
//            if (line !is ChainOkt996Keccak) {
//                if (line.cosmosBalances == null) {
//                    nodeDownPopup()
//                    return
//                }
//            }
//            Intent(requireContext(), CosmosActivity::class.java).apply {
//                putExtra("selectedChain", line as Parcelable)
//                startActivity(this)
//            }
//            requireActivity().toMoveAnimation()
            if (chain.isCosmos() && chain.supportEvm) {
                if (chain.grpcFetcher?.cosmosBalances == null) {
                    nodeDownPopup()
                    return
                }

                if (chain.web3j == null) {
                    nodeDownPopup()
                    return
                }
                Intent(requireContext(), CosmosActivity::class.java).apply {
                    putExtra("selectedChain", chain as Parcelable)
                    startActivity(this)
                }
                requireActivity().toMoveAnimation()

            } else {
                chain.grpcFetcher?.let {
                    if (chain.grpcFetcher?.cosmosBalances == null) {
                        nodeDownPopup()
                        return
                    }
                    Intent(requireContext(), CosmosActivity::class.java).apply {
                        putExtra("selectedChain", chain as Parcelable)
                        startActivity(this)
                    }
                    requireActivity().toMoveAnimation()
                }

                chain.evmRpcFetcher?.let {
                    if (chain.web3j == null) {
                        nodeDownPopup()
                        return
                    }
                    Intent(requireContext(), EvmActivity::class.java).apply {
                        putExtra("selectedChain", chain as Parcelable)
                        startActivity(this)
                    }
                    requireActivity().toMoveAnimation()
                }
            }
        }
    }

    private fun initDisplayData(baseAccount: BaseAccount?) {
        baseAccount?.let { account ->
            account.apply {
                lifecycleScope.launch(Dispatchers.IO) {
                    if (type == BaseAccountType.MNEMONIC) {
                        sortedDisplayChains().asSequence().concurrentForEach { chain ->
                            if (chain.address.isEmpty()) {
                                chain.setInfoWithSeed(seed, chain.setParentPath, lastHDPath)
                            }
                            if (!chain.fetched) {
                                ApplicationViewModel.shared.loadChainData(chain, id, false)
                            }
                        }
                        if (isNew) {
                            PushManager.syncAddresses(Prefs.fcmToken)
                        }

                    } else if (type == BaseAccountType.PRIVATE_KEY) {
                        sortedDisplayChains().asSequence().concurrentForEach { chain ->
                            if (chain.address.isEmpty()) {
                                chain.setInfoWithPrivateKey(privateKey)
                            }
                            if (!chain.fetched) {
                                ApplicationViewModel.shared.loadChainData(chain, id, false)
                            }
                        }
                        if (isNew) {
                            PushManager.syncAddresses(Prefs.fcmToken)
                        }
                    }
                }
            }
        }
    }

    private fun setupLoadedData() {
        ApplicationViewModel.shared.fetchedResult.observe(viewLifecycleOwner) { tag ->
            updateRowData(tag)
        }

        ApplicationViewModel.shared.fetchedTokenResult.observe(viewLifecycleOwner) { tag ->
            updateRowData(tag)
        }
    }

    private fun updateSearchView() {
        binding?.apply {
            if (mainnetChains.size + testnetChains.size > 15) {
                searchBar.visibility = View.VISIBLE
            } else {
                searchBar.visibility = View.GONE
            }
        }
    }

    private fun updateView() {
        dashAdapter.notifyDataSetChanged()
        updateHideValue()
        updateTotalValue()
        updateSearchView()
    }

    private fun updateRowData(tag: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val searchMainnetResult = searchMainnetChains.filter { it.tag == tag }
            val mainIterator = searchMainnetResult.iterator()
            while (mainIterator.hasNext()) {
                val chain = mainIterator.next()
                val index = searchMainnetChains.indexOf(chain)
                if (::dashAdapter.isInitialized) {
                    withContext(Dispatchers.Main) {
                        dashAdapter.notifyItemChanged(index + 1)
                    }
                }
            }

            val searchResult = searchTestnetChains.filter { it.tag == tag }
            val iterator = searchResult.iterator()
            while (iterator.hasNext()) {
                val chain = iterator.next()
                val index = searchTestnetChains.indexOf(chain)
                val position = if (searchMainnetChains.size > 0) {
                    searchMainnetChains.size + 2
                } else {
                    1
                }
                if (::dashAdapter.isInitialized) {
                    withContext(Dispatchers.Main) {
                        dashAdapter.notifyItemChanged(index + position)
                    }
                }
            }
        }
        updateTotalValue()
    }

    private fun updateTotalValue() {
        lifecycleScope.launch(Dispatchers.IO) {
            var totalSum = BigDecimal.ZERO

            mainnetChains.forEach { chain ->
                totalSum = totalSum.add(chain.allValue(false))
            }

            testnetChains.forEach { chain ->
                totalSum = totalSum.add(chain.allValue(false))
            }

            withContext(Dispatchers.Main) {
                if (isAdded) {
                    totalChainValue = totalSum

                    val totalValueTxt = binding?.totalValue
                    totalValueTxt?.text =
                        if (Prefs.hideValue) "✱✱✱✱✱" else formatAssetValue(totalSum)
                    totalValueTxt?.textSize = if (Prefs.hideValue) 18f else 24f
                }
            }
        }
    }

    private fun updateHideValue() {
        binding?.apply {
            if (Prefs.hideValue) {
                totalValue.text = "✱✱✱✱✱"
                totalValue.textSize = 18f
                btnHide.setImageResource(R.drawable.icon_hide)

            } else {
                totalValue.text = formatAssetValue(totalChainValue)
                totalValue.textSize = 24f
                btnHide.setImageResource(R.drawable.icon_not_hide)
            }
        }
    }

    private fun setupHideButton() {
        binding?.btnHide?.setOnClickListener {
            Prefs.hideValue = !Prefs.hideValue
            ApplicationViewModel.shared.hideValue()
        }
    }

    private fun refreshData() {
        binding?.apply {
            refresher.setOnRefreshListener {
                baseAccount?.let { account ->
                    if (account.sortedDisplayChains().any { !it.fetched }) {
                        refresher.isRefreshing = false

                    } else {
                        ApplicationViewModel.shared.fetchedResult.removeObservers(viewLifecycleOwner)
                        walletViewModel.price(BaseData.currencyName().lowercase())

                        lifecycleScope.launch(Dispatchers.IO) {
                            account.sortedDisplayChains().forEach { chain ->
                                chain.fetched = false
                            }
                            withContext(Dispatchers.Main) {
                                updateViewWithLoadedData(account)
                                refresher.isRefreshing = false
                            }
                        }
                    }
                }
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

                    if (StringUtils.isEmpty(newText)) {
                        searchMainnetChains.addAll(mainnetChains)
                        searchTestnetChains.addAll(testnetChains)

                    } else {
                        newText?.let { searchTxt ->
                            searchMainnetChains.addAll(mainnetChains.filter { chain ->
                                chain.name.contains(searchTxt, ignoreCase = true)
                            })

                            searchTestnetChains.addAll(testnetChains.filter { chain ->
                                chain.name.contains(searchTxt, ignoreCase = true)
                            })
                        }
                    }
                    if (searchMainnetChains.isEmpty() && searchTestnetChains.isEmpty()) {
                        emptyLayout.visibility = View.VISIBLE
                        recycler.visibility = View.GONE
                    } else {
                        emptyLayout.visibility = View.GONE
                        recycler.visibility = View.VISIBLE
                        dashAdapter.notifyDataSetChanged()
                    }
                    return true
                }
            })
        }
    }

    private fun nodeDownPopup() {
        NoticeInfoFragment.newInstance(null, NoticeType.NODE_DOWN_GUIDE).show(
            requireActivity().supportFragmentManager, NoticeInfoFragment::class.java.name
        )
    }

    private fun observeViewModels() {
        walletViewModel.updatePriceResult.observe(viewLifecycleOwner) {
            updateTotalValue()
            dashAdapter.notifyDataSetChanged()
        }

        ApplicationViewModel.shared.displayLegacyResult.observe(viewLifecycleOwner) {
            ApplicationViewModel.shared.fetchedResult.removeObservers(viewLifecycleOwner)
            walletViewModel.price(BaseData.currencyName().lowercase())
            lifecycleScope.launch(Dispatchers.IO) {
                baseAccount?.initAccount()
                baseAccount?.sortedDisplayChains()?.forEach { chain ->
                    chain.fetched = false
                }
                withContext(Dispatchers.Main) {
                    initData(baseAccount)
                    updateViewWithLoadedData(baseAccount)
                }
            }
        }

        ApplicationViewModel.shared.styleOptionResult.observe(viewLifecycleOwner) { isChanged ->
            if (isChanged) dashAdapter.notifyDataSetChanged()
        }

        ApplicationViewModel.shared.hideValueResult.observe(viewLifecycleOwner) {
            updateHideValue()
            dashAdapter.notifyDataSetChanged()
        }

        ApplicationViewModel.shared.currentAccountResult.observe(viewLifecycleOwner) { response ->
            baseAccount = response.second
            lifecycleScope.launch(Dispatchers.IO) {
                baseAccount?.initAccount()
                withContext(Dispatchers.Main) {
                    initData(baseAccount)
                    val totalValueTxt = binding?.totalValue
                    totalValueTxt?.text =
                        if (Prefs.hideValue) "✱✱✱✱✱" else formatAssetValue(BigDecimal.ZERO)
                    totalValueTxt?.textSize = if (Prefs.hideValue) 18f else 24f

                    updateViewWithLoadedData(baseAccount)
                    updateSearchView()
                    isNew = response.first
                }
            }
        }

        ApplicationViewModel.shared.walletEditResult.observe(viewLifecycleOwner) { response ->
            lifecycleScope.launch(Dispatchers.IO) {
                baseAccount?.let { account ->
                    if (Prefs.getDisplayChains(account) == response) {
                        return@launch
                    }
                    Prefs.setDisplayChains(account, response)
                    account.sortLine()
                    initDisplayData(account)

                    delay(100)
                    withContext(Dispatchers.Main) {
                        initData(baseAccount)
                        initRecyclerView()
                        updateTotalValue()
                        updateSearchView()
//                        PushManager.syncAddresses(Prefs.fcmToken)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        ApplicationViewModel.shared.fetchedResult.removeObservers(viewLifecycleOwner)
        ApplicationViewModel.shared.fetchedTokenResult.removeObservers(viewLifecycleOwner)
        super.onDestroyView()
    }
}