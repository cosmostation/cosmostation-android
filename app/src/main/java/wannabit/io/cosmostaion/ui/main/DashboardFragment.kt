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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.FetchState
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.majorClass.ChainIota
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.testnetClass.ChainGnoTestnet
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.concurrentForEach
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.toMoveAnimation
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.data.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.FragmentDashboardBinding
import wannabit.io.cosmostaion.ui.main.chain.cosmos.CosmosActivity
import wannabit.io.cosmostaion.ui.main.chain.evm.EvmActivity
import wannabit.io.cosmostaion.ui.main.chain.major.MajorActivity
import wannabit.io.cosmostaion.ui.main.setting.wallet.chain.ChainEndpointFragment
import wannabit.io.cosmostaion.ui.main.setting.wallet.chain.EndPointType
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
            val searchTxt = binding?.searchView?.query
            mainnetChains =
                account.sortedDisplayChains().filter { chain -> !chain.isTestnet }.toMutableList()
            searchMainnetChains.addAll(if (searchTxt.isNullOrEmpty()) {
                mainnetChains
            } else {
                mainnetChains.filter { chain ->
                    chain.getChainName()?.contains(searchTxt.toString(), ignoreCase = true) == true
                }
            })

            testnetChains =
                account.sortedDisplayChains().filter { chain -> chain.isTestnet }.toMutableList()
            searchTestnetChains.addAll(if (searchTxt.isNullOrEmpty()) {
                testnetChains
            } else {
                testnetChains.filter { chain ->
                    chain.getChainName()?.contains(searchTxt.toString(), ignoreCase = true) == true
                }
            })
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
            if (chain.fetchState == FetchState.IDLE || chain.fetchState == FetchState.BUSY) return
            if (chain.fetchState == FetchState.SUCCESS) {
                if (chain is ChainSui || chain is ChainIota || chain is ChainBitCoin86) {
                    Intent(requireContext(), MajorActivity::class.java).apply {
                        putExtra("selectedChain", chain as Parcelable)
                        startActivity(this)
                    }

                } else if (chain.supportCosmos() || chain.isEvmCosmos()) {
                    Intent(requireContext(), CosmosActivity::class.java).apply {
                        putExtra("selectedChain", chain as Parcelable)
                        startActivity(this)
                    }

                } else {
                    Intent(requireContext(), EvmActivity::class.java).apply {
                        putExtra("selectedChain", chain as Parcelable)
                        startActivity(this)
                    }
                }
                requireActivity().toMoveAnimation()

            } else {
                nodeDownPopup(chain)
                return
            }
        }
    }

    private fun initDisplayData(baseAccount: BaseAccount?) {
        baseAccount?.let { account ->
            account.apply {
                lifecycleScope.launch(Dispatchers.IO) {
                    if (type == BaseAccountType.MNEMONIC) {
                        sortedDisplayChains().asSequence().concurrentForEach { chain ->
                            val safeContext = context ?: return@concurrentForEach
                            if (chain.publicKey == null) {
                                chain.setInfoWithSeed(
                                    safeContext, seed, chain.setParentPath, lastHDPath
                                )
                            }
                            if (Prefs.style == 1) {
                                if (!chain.supportCosmos() && chain.evmAddress.isNotEmpty()) {
                                    withContext(Dispatchers.Main) {
                                        updateRowData(chain.tag)
                                    }
                                } else {
                                    if (chain.address.isNotEmpty() || chain.mainAddress.isNotEmpty()) {
                                        updateRowData(chain.tag)
                                    }
                                }
                            }

                            if (chain.fetchState == FetchState.IDLE || chain.fetchState == FetchState.BUSY) {
                                ApplicationViewModel.shared.loadChainData(chain, id)
                            }
                        }

                    } else if (type == BaseAccountType.PRIVATE_KEY) {
                        sortedDisplayChains().asSequence().concurrentForEach { chain ->
                            val safeContext = context ?: return@concurrentForEach
                            if (chain.publicKey == null) {
                                chain.setInfoWithPrivateKey(safeContext, privateKey)
                            }
                            if (Prefs.style == 1) {
                                if (!chain.supportCosmos() && chain.evmAddress.isNotEmpty()) {
                                    withContext(Dispatchers.Main) {
                                        updateRowData(chain.tag)
                                    }
                                } else {
                                    if (chain.address.isNotEmpty() || chain.mainAddress.isNotEmpty()) {
                                        updateRowData(chain.tag)
                                    }
                                }
                            }

                            if (chain.fetchState == FetchState.IDLE || chain.fetchState == FetchState.BUSY) {
                                ApplicationViewModel.shared.loadChainData(chain, id)
                            }
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
        lifecycleScope.launch(Dispatchers.IO) {
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
            BaseData.baseAccount?.let { account ->
                var totalSum = BigDecimal.ZERO
                val dpTags = Prefs.getDisplayChains(account)
                val dpChains = account.allChains.filter { chain ->
                    dpTags.contains(chain.tag)
                }

                dpChains.forEach { chain ->
                    totalSum = totalSum.add(chain.allValue(false) ?: BigDecimal.ZERO)
                }
                totalChainValue = totalSum

                withContext(Dispatchers.Main) {
                    if (isAdded) {
                        binding?.totalValue?.text =
                            if (Prefs.hideValue) "✱✱✱✱✱" else formatAssetValue(totalChainValue)
                        binding?.totalValue?.textSize = if (Prefs.hideValue) 18f else 24f
                    }
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
                    if (account.sortedDisplayChains().any { it.fetchState == FetchState.BUSY }) {
                        refresher.isRefreshing = false

                    } else {
                        ApplicationViewModel.shared.fetchedResult.removeObservers(viewLifecycleOwner)
                        walletViewModel.price(BaseData.currencyName().lowercase())

                        lifecycleScope.launch(Dispatchers.IO) {
                            account.sortedDisplayChains().forEach { chain ->
                                chain.fetchState = FetchState.IDLE
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
                                chain.getMainAssetSymbol().contains(
                                    searchTxt, ignoreCase = true
                                ) || chain.getChainName()?.contains(searchTxt, ignoreCase = true) == true
                            })

                            searchTestnetChains.addAll(testnetChains.filter { chain ->
                                chain.getMainAssetSymbol().contains(
                                    searchTxt, ignoreCase = true
                                ) || chain.getChainName()?.contains(searchTxt, ignoreCase = true) == true
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

    private fun nodeDownPopup(chain: BaseChain) {
        NoticeInfoFragment.newInstance(chain,
            NoticeType.NODE_DOWN_GUIDE,
            object : NodeDownSelectListener {
                override fun select(tag: String?) {
                    baseAccount?.let { account ->
                        ApplicationViewModel.shared.loadChainData(chain, account.id)
                        updateRowData(tag.toString())
                    }
                }

                override fun changeEndpoint(tag: String?) {
                    if (chain is ChainOktEvm || chain is ChainBitCoin86) return

                    val endPointType = if (chain is ChainSui || chain is ChainGnoTestnet) {
                        EndPointType.END_POINT_SUI
                    } else if (chain.isEvmCosmos() || chain.supportCosmos()) {
                        EndPointType.END_POINT_COSMOS
                    } else {
                        EndPointType.END_POINT_EVM
                    }

                    ChainEndpointFragment.newInstance(chain, endPointType).show(
                        parentFragmentManager, ChainEndpointFragment::class.java.name
                    )

                    parentFragmentManager.setFragmentResultListener(
                        "endpoint", this@DashboardFragment
                    ) { _, _ ->
                        baseAccount?.let { account ->
                            ApplicationViewModel.shared.loadChainData(chain, account.id)
                            updateRowData(tag.toString())
                        }
                    }
                }

            }).show(
            requireActivity().supportFragmentManager, NoticeInfoFragment::class.java.name
        )
    }

    private fun observeViewModels() {
        walletViewModel.updatePriceResult.observe(viewLifecycleOwner) {
            updateTotalValue()
            dashAdapter.notifyDataSetChanged()
        }

        ApplicationViewModel.shared.updatePriceResult.observe(viewLifecycleOwner) {
            updateTotalValue()
            dashAdapter.notifyDataSetChanged()
        }

        ApplicationViewModel.shared.displayLegacyResult.observe(viewLifecycleOwner) {
            ApplicationViewModel.shared.fetchedResult.removeObservers(viewLifecycleOwner)
            walletViewModel.price(BaseData.currencyName().lowercase())
            lifecycleScope.launch(Dispatchers.IO) {
                baseAccount?.initAccount()
                baseAccount?.sortedDisplayChains()?.forEach { chain ->
                    chain.fetchState = FetchState.IDLE
                }
                withContext(Dispatchers.Main) {
                    initData(baseAccount)
                    updateViewWithLoadedData(baseAccount)
                }
            }
        }

        ApplicationViewModel.shared.displayTestnetResult.observe(viewLifecycleOwner) {
            ApplicationViewModel.shared.fetchedResult.removeObservers(viewLifecycleOwner)
            walletViewModel.price(BaseData.currencyName().lowercase())
            lifecycleScope.launch(Dispatchers.IO) {
                baseAccount?.initAccount()
                baseAccount?.sortedDisplayChains()?.forEach { chain ->
                    chain.fetchState = FetchState.IDLE
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
                    account.sortLine(Prefs.chainFilter)
                    initDisplayData(account)

                    delay(100)
                    withContext(Dispatchers.Main) {
                        initData(baseAccount)
                        initRecyclerView()
                        updateTotalValue()
                        updateSearchView()
                    }
                }
            }
        }

        ApplicationViewModel.shared.serviceTxResult.observe(viewLifecycleOwner) {
            lifecycleScope.launch(Dispatchers.IO) {
                baseAccount?.sortedDisplayChains()?.forEach { chain ->
                    chain.fetchState = FetchState.IDLE
                }
                withContext(Dispatchers.Main) {
                    initData(baseAccount)
                    updateViewWithLoadedData(baseAccount)
                }
            }
        }

        ApplicationViewModel.shared.chainFilterResult.observe(viewLifecycleOwner) {
            lifecycleScope.launch(Dispatchers.IO) {
                baseAccount?.let { account ->
                    account.sortLine(Prefs.chainFilter)
                    initData(account)

                    withContext(Dispatchers.Main) {
                        dashAdapter.notifyDataSetChanged()
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