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
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
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

    private var toDisplayEvmChains = mutableListOf<EthereumLine>()
    private var searchEvmChains = mutableListOf<EthereumLine>()
    private var toDisplayCosmosChains = mutableListOf<CosmosLine>()
    private var searchCosmosChains = mutableListOf<CosmosLine>()

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

    private fun initData(baseAccount: BaseAccount?) {
        searchEvmChains.clear()
        searchCosmosChains.clear()

        baseAccount?.let { account ->
            toDisplayEvmChains = account.sortedDisplayEvmLines()
            searchEvmChains.addAll(toDisplayEvmChains)

            toDisplayCosmosChains = account.sortedDisplayCosmosLines()
            searchCosmosChains.addAll(toDisplayCosmosChains)
        }
    }

    override fun onResume() {
        super.onResume()
        updateView()
        isScrollView()
    }

    private fun isScrollView() {
        binding?.apply {
            recycler.post {
                val layoutManager = recycler.layoutManager as LinearLayoutManager
                val firstCompletelyVisibleItemPosition =
                    layoutManager.findFirstCompletelyVisibleItemPosition()
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                val isScrolledDown =
                    firstCompletelyVisibleItemPosition > 0 || firstVisibleItemPosition > 0

                if (recycler.adapter?.itemCount!! > 17) {
                    if (isScrolledDown || searchView.query.isNotEmpty()) {
                        searchBar.visibility = View.VISIBLE
                    } else {
                        searchBar.visibility = View.GONE
                    }
                } else {
                    searchBar.visibility = View.GONE
                }
            }
        }
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

            recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager

                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    baseAccount?.let {
                        if (dy > 0 && firstVisibleItemPosition > 1 && it.sortedDisplayEvmLines().size + it.sortedDisplayCosmosLines().size > 15) {
                            searchBar.visibility = View.VISIBLE
                        } else {
                            searchBar.visibility = View.GONE
                        }
                    }
                }
            })
        }
    }

    private fun updateViewWithLoadedData(baseAccount: BaseAccount?) {
        initDisplayData(baseAccount)
        initRecyclerView()
        setupLoadedData()
    }

    private fun initRecyclerView() {
        dashAdapter = DashboardAdapter(
            requireContext(), searchEvmChains, searchCosmosChains, listener = nodeDownCheckAction
        )

        binding?.recycler?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = dashAdapter
            itemAnimator = null
        }
    }

    private val nodeDownCheckAction = object : DashboardAdapter.NodeDownListener {
        override fun nodeDown(line: CosmosLine) {
            if (!line.fetched) {
                return
            }
            if (line is EthereumLine) {
                if (line !is ChainOktEvm) {
                    if (line.supportCosmos && line.cosmosBalances == null) {
                        nodeDownPopup()
                        return
                    }
                }

                if (line.web3j == null) {
                    nodeDownPopup()
                    return
                }

                if (line.supportCosmos) {
                    Intent(requireContext(), CosmosActivity::class.java).apply {
                        putExtra("selectedChain", line as Parcelable)
                        startActivity(this)
                    }
                    requireActivity().toMoveAnimation()
                } else {
                    Intent(requireContext(), EvmActivity::class.java).apply {
                        putExtra("selectedChain", line as Parcelable)
                        startActivity(this)
                    }
                    requireActivity().toMoveAnimation()
                }

            } else {
                if (line !is ChainOkt996Keccak && line !is ChainBinanceBeacon) {
                    if (line.cosmosBalances == null) {
                        nodeDownPopup()
                        return
                    }
                }
                Intent(requireContext(), CosmosActivity::class.java).apply {
                    putExtra("selectedChain", line as Parcelable)
                    startActivity(this)
                }
                requireActivity().toMoveAnimation()
            }
        }
    }

    private fun initDisplayData(baseAccount: BaseAccount?) {
        baseAccount?.let { account ->
            account.apply {
                lifecycleScope.launch(Dispatchers.IO) {
                    if (type == BaseAccountType.MNEMONIC) {
                        sortedDisplayEvmLines().asSequence().concurrentForEach { line ->
                            if (line.address?.isEmpty() == true) {
                                line.setInfoWithSeed(seed, line.setParentPath, lastHDPath)
                            }
                            if (!line.fetched) {
                                ApplicationViewModel.shared.loadEvmChainData(line, id, false)
                            }
                        }

                        sortedDisplayCosmosLines().asSequence().concurrentForEach { line ->
                            if (line.address?.isEmpty() == true) {
                                line.setInfoWithSeed(seed, line.setParentPath, lastHDPath)
                            }
                            if (!line.fetched) {
                                ApplicationViewModel.shared.loadChainData(line, id, false)
                            }
                        }
                        if (isNew) {
                            PushManager.syncAddresses(Prefs.fcmToken)
                        }

                    } else if (type == BaseAccountType.PRIVATE_KEY) {
                        sortedDisplayEvmLines().asSequence().concurrentForEach { line ->
                            if (line.address?.isEmpty() == true) {
                                line.setInfoWithPrivateKey(privateKey)
                            }
                            if (!line.fetched) {
                                ApplicationViewModel.shared.loadEvmChainData(line, id, false)
                            }
                        }

                        sortedDisplayCosmosLines().asSequence().concurrentForEach { line ->
                            if (line.address?.isEmpty() == true) {
                                line.setInfoWithPrivateKey(privateKey)

                            }
                            if (!line.fetched) {
                                ApplicationViewModel.shared.loadChainData(line, id, false)
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

    private fun updateView() {
        dashAdapter.notifyDataSetChanged()
        updateHideValue()
        updateTotalValue()
    }

    private fun updateRowData(tag: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val searchEvmResult = searchEvmChains.filter { it.tag == tag }
            val evmIterator = searchEvmResult.iterator()
            while (evmIterator.hasNext()) {
                val chain = evmIterator.next()
                val index = searchEvmChains.indexOf(chain)
                if (::dashAdapter.isInitialized) {
                    withContext(Dispatchers.Main) {
                        dashAdapter.notifyItemChanged(index + 1)
                    }
                }
            }

            val searchResult = searchCosmosChains.filter { it.tag == tag }
            val iterator = searchResult.iterator()
            while (iterator.hasNext()) {
                val chain = iterator.next()
                val index = searchCosmosChains.indexOf(chain)
                val position = if (searchEvmChains.size > 0) {
                    searchEvmChains.size + 2
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

            toDisplayEvmChains.forEach { chain ->
                totalSum = totalSum.add(chain.allValue(false))
            }

            toDisplayCosmosChains.forEach { chain ->
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
                    if (account.sortedDisplayEvmLines()
                            .any { !it.fetched } || account.sortedDisplayCosmosLines()
                            .any { !it.fetched }
                    ) {
                        refresher.isRefreshing = false

                    } else {
                        ApplicationViewModel.shared.fetchedResult.removeObservers(viewLifecycleOwner)
                        walletViewModel.price(BaseData.currencyName().lowercase())

                        lifecycleScope.launch(Dispatchers.IO) {
                            account.sortedDisplayEvmLines().forEach { line ->
                                line.fetched = false
                            }
                            account.sortedDisplayCosmosLines().forEach { line ->
                                line.fetched = false
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
                    searchEvmChains.clear()
                    searchCosmosChains.clear()

                    if (StringUtils.isEmpty(newText)) {
                        searchEvmChains.addAll(toDisplayEvmChains)
                        searchCosmosChains.addAll(toDisplayCosmosChains)

                    } else {
                        newText?.let { searchTxt ->
                            searchEvmChains.addAll(toDisplayEvmChains.filter { chain ->
                                chain.name.contains(searchTxt, ignoreCase = true)
                            })

                            searchCosmosChains.addAll(toDisplayCosmosChains.filter { chain ->
                                chain.name.contains(searchTxt, ignoreCase = true)
                            })
                        }
                    }
                    if (searchEvmChains.isEmpty() && searchCosmosChains.isEmpty()) {
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
                baseAccount?.sortedDisplayCosmosLines()?.forEach { line ->
                    line.fetched = false
                }
                withContext(Dispatchers.Main) {
                    initData(baseAccount)
                    updateViewWithLoadedData(baseAccount)
                }
            }
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
                    isNew = response.first
                }
            }
        }

        ApplicationViewModel.shared.walletEditResult.observe(viewLifecycleOwner) { response ->
            lifecycleScope.launch(Dispatchers.IO) {
                baseAccount?.let { account ->
                    if (Prefs.getDisplayEvmChains(account) == response.first && Prefs.getDisplayChains(
                            account
                        ) == response.second
                    ) {
                        return@launch
                    }
                    Prefs.setDisplayEvmChains(account, response.first)
                    Prefs.setDisplayChains(account, response.second)
                    account.sortLine()
                    initDisplayData(account)

                    delay(100)
                    withContext(Dispatchers.Main) {
                        initData(baseAccount)
                        initRecyclerView()
                        updateTotalValue()
                        PushManager.syncAddresses(Prefs.fcmToken)
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