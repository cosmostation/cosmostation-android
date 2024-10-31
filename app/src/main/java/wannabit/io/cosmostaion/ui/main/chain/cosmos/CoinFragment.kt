package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.FetchState
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCosmos
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.model.res.Coin
import wannabit.io.cosmostaion.data.model.res.CoinType
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.FragmentCoinBinding
import wannabit.io.cosmostaion.ui.main.NoticeInfoFragment
import wannabit.io.cosmostaion.ui.main.NoticeType
import wannabit.io.cosmostaion.ui.main.TokenEditFragment
import wannabit.io.cosmostaion.ui.main.TokenEditListener
import wannabit.io.cosmostaion.ui.main.dapp.DappActivity
import wannabit.io.cosmostaion.ui.tx.genTx.CommonTransferFragment
import wannabit.io.cosmostaion.ui.tx.genTx.LegacyTransferFragment
import wannabit.io.cosmostaion.ui.tx.genTx.SendAssetType
import java.math.BigDecimal

interface CoinFragmentInteraction {
    fun showTokenList()
}

class CoinFragment : Fragment(), CoinFragmentInteraction {

    private var _binding: FragmentCoinBinding? = null
    private val binding get() = _binding!!

    private lateinit var coinAdapter: CoinAdapter

    private lateinit var selectedChain: BaseChain

    private val stakeCoins = mutableListOf<Coin>()
    private val searchStakeCoins = mutableListOf<Coin>()
    private val nativeCoins = mutableListOf<Coin>()
    private val searchNativeCoins = mutableListOf<Coin>()
    private val bridgeCoins = mutableListOf<Coin>()
    private val searchBridgeCoins = mutableListOf<Coin>()
    private val ibcCoins = mutableListOf<Coin>()
    private val searchIbcCoins = mutableListOf<Coin>()
    private val etcCoins = mutableListOf<Coin>()
    private val searchEtcCoins = mutableListOf<Coin>()
    private val tokenCoins = mutableListOf<Coin>()
    private var allTokenCoins = mutableListOf<Token>()
    private var displayTokenCoins = mutableListOf<Token>()
    private val searchTokenCoins = mutableListOf<Coin>()

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): CoinFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = CoinFragment()
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

        observeViewModels()
        refreshData()
        initData()
        sunsetPopup()
        initSearchView()
        setUpClickAction()
    }

    private fun initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("selectedChain", BaseChain::class.java)
                ?.let { selectedChain = it }
        } else {
            (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                selectedChain = it
            }
        }
        setUpInitData()
    }

    private fun setUpInitData() {
        stakeCoins.clear()
        searchStakeCoins.clear()
        nativeCoins.clear()
        searchNativeCoins.clear()
        ibcCoins.clear()
        searchIbcCoins.clear()
        bridgeCoins.clear()
        searchBridgeCoins.clear()
        etcCoins.clear()
        searchEtcCoins.clear()

        val tokens = mutableListOf<Token>()
        tokens.clear()
        tokenCoins.clear()
        searchTokenCoins.clear()
        allTokenCoins.clear()
        displayTokenCoins.clear()

        when (selectedChain) {
            is ChainOktEvm -> {
                (selectedChain as ChainOktEvm).oktFetcher?.oktAccountInfo?.get("value")?.asJsonObject?.get(
                    "coins"
                )?.asJsonArray?.forEach { balance ->
                    if (balance.asJsonObject["denom"].asString == selectedChain.stakeDenom) {
                        stakeCoins.add(
                            Coin(
                                balance.asJsonObject["denom"].asString,
                                balance.asJsonObject["amount"].asString,
                                CoinType.STAKE
                            )
                        )
                    } else {
                        etcCoins.add(
                            Coin(
                                balance.asJsonObject["denom"].asString,
                                balance.asJsonObject["amount"].asString,
                                CoinType.ETC
                            )
                        )
                    }
                }
                if (stakeCoins.none { it.denom == selectedChain.stakeDenom }) {
                    stakeCoins.add(Coin(selectedChain.stakeDenom, "0", CoinType.STAKE))
                }
                searchStakeCoins.addAll(stakeCoins)

                etcCoins.sortBy { it.denom }
                searchEtcCoins.addAll(etcCoins)
            }

            else -> {
                selectedChain.cosmosFetcher?.cosmosBalances?.forEach { coin ->
                    val coinType = BaseData.getAsset(selectedChain.apiName, coin.denom)?.type
                    coinType?.let {
                        when (it) {
                            "staking", "native" -> {
                                if (coin.denom == selectedChain.stakeDenom) {
                                    stakeCoins.add(
                                        Coin(
                                            coin.denom, coin.amount, CoinType.STAKE
                                        )
                                    )
                                } else {
                                    nativeCoins.add(
                                        Coin(
                                            coin.denom, coin.amount, CoinType.NATIVE
                                        )
                                    )
                                }
                            }

                            "bep", "bridge" -> {
                                bridgeCoins.add(
                                    Coin(
                                        coin.denom, coin.amount, CoinType.BRIDGE
                                    )
                                )
                            }

                            "ibc" -> {
                                ibcCoins.add(Coin(coin.denom, coin.amount, CoinType.IBC))
                            }
                        }
                    }
                }

                if (stakeCoins.none { it.denom == selectedChain.stakeDenom }) {
                    stakeCoins.add(Coin(selectedChain.stakeDenom, "0", CoinType.STAKE))
                }
                searchStakeCoins.addAll(stakeCoins)

                nativeCoins.sortWith(compareByDescending {
                    selectedChain.cosmosFetcher?.balanceValue(
                        it.denom
                    )
                })
                searchNativeCoins.addAll(nativeCoins)

                ibcCoins.sortWith(compareByDescending {
                    selectedChain.cosmosFetcher?.balanceValue(
                        it.denom
                    )
                })
                searchIbcCoins.addAll(ibcCoins)

                bridgeCoins.sortWith(compareByDescending {
                    selectedChain.cosmosFetcher?.balanceValue(
                        it.denom
                    )
                })
                searchBridgeCoins.addAll(bridgeCoins)
            }
        }

        BaseData.baseAccount?.let { account ->
            if (selectedChain.supportEvm) {
                selectedChain.evmRpcFetcher?.evmTokens?.let { tokens.addAll(it) }
                tokens.sortBy { it.symbol.lowercase() }
                Prefs.getDisplayErc20s(account.id, selectedChain.tag)?.let { userCustomTokens ->
                    tokens.sortWith { o1, o2 ->
                        val address0 = o1.contract
                        val address1 = o2.contract

                        val containsToken0 = userCustomTokens.contains(address0)
                        val containsToken1 = userCustomTokens.contains(address1)

                        when {
                            containsToken0 && !containsToken1 -> -1
                            !containsToken0 && containsToken1 -> 1
                            else -> {
                                val value0 = selectedChain.evmRpcFetcher?.tokenValue(address0)
                                    ?: BigDecimal.ZERO
                                val value1 = selectedChain.evmRpcFetcher?.tokenValue(address1)
                                    ?: BigDecimal.ZERO
                                value1.compareTo(value0)
                            }
                        }
                    }

                    tokens.forEach { token ->
                        if (userCustomTokens.contains(token.contract) && !displayTokenCoins.contains(
                                token
                            )
                        ) {
                            displayTokenCoins.add(token)
                            tokenCoins.add(
                                Coin(
                                    token.contract, token.amount.toString(), CoinType.ERC20
                                )
                            )
                        }
                    }
                    allTokenCoins.addAll(tokens)
                    searchTokenCoins.addAll(tokenCoins)

                } ?: run {
                    tokens.sortWith { o1, o2 ->
                        when {
                            BigDecimal.ZERO < o1.amount?.toBigDecimal() && BigDecimal.ZERO >= o2.amount?.toBigDecimal() -> -1
                            BigDecimal.ZERO >= o1.amount?.toBigDecimal() && BigDecimal.ZERO < o2.amount?.toBigDecimal() -> 1
                            else -> {
                                val value0 = selectedChain.evmRpcFetcher?.tokenValue(o1.contract)
                                    ?: BigDecimal.ZERO
                                val value1 = selectedChain.evmRpcFetcher?.tokenValue(o2.contract)
                                    ?: BigDecimal.ZERO
                                value1.compareTo(value0)
                            }
                        }
                    }

                    tokens.forEach { token ->
                        if (BigDecimal.ZERO < token.amount?.toBigDecimal() && !displayTokenCoins.contains(
                                token
                            ) && token.wallet_preload == true
                        ) {
                            displayTokenCoins.add(token)
                            tokenCoins.add(
                                Coin(
                                    token.contract, token.amount.toString(), CoinType.ERC20
                                )
                            )
                        }
                    }
                    allTokenCoins.addAll(tokens)
                    searchTokenCoins.addAll(tokenCoins)
                }

            } else {
                selectedChain.cosmosFetcher?.tokens?.let { tokens.addAll(it) }
                tokens.sortBy { it.symbol.lowercase() }

                Prefs.getDisplayCw20s(account.id, selectedChain.tag)?.let { userCustomTokens ->
                    tokens.sortWith { o1, o2 ->
                        val address0 = o1.contract
                        val address1 = o2.contract

                        val containsToken0 = userCustomTokens.contains(address0)
                        val containsToken1 = userCustomTokens.contains(address1)

                        when {
                            containsToken0 && !containsToken1 -> -1
                            !containsToken0 && containsToken1 -> 1
                            else -> {
                                val value0 = selectedChain.cosmosFetcher?.tokenValue(address0)
                                    ?: BigDecimal.ZERO
                                val value1 = selectedChain.cosmosFetcher?.tokenValue(address1)
                                    ?: BigDecimal.ZERO
                                value1.compareTo(value0)
                            }
                        }
                    }

                    tokens.forEach { token ->
                        if (userCustomTokens.contains(token.contract) && !displayTokenCoins.contains(
                                token
                            )
                        ) {
                            displayTokenCoins.add(token)
                            tokenCoins.add(
                                Coin(
                                    token.contract, token.amount.toString(), CoinType.CW20
                                )
                            )
                        }
                    }
                    allTokenCoins.addAll(tokens)
                    searchTokenCoins.addAll(tokenCoins)

                } ?: run {
                    tokens.sortWith { o1, o2 ->
                        when {
                            BigDecimal.ZERO < o1.amount?.toBigDecimal() && BigDecimal.ZERO >= o2.amount?.toBigDecimal() -> -1
                            BigDecimal.ZERO >= o1.amount?.toBigDecimal() && BigDecimal.ZERO < o2.amount?.toBigDecimal() -> 1
                            else -> {
                                val value0 = selectedChain.cosmosFetcher?.tokenValue(o1.contract)
                                    ?: BigDecimal.ZERO
                                val value1 = selectedChain.cosmosFetcher?.tokenValue(o2.contract)
                                    ?: BigDecimal.ZERO
                                value1.compareTo(value0)
                            }
                        }
                    }

                    tokens.forEach { token ->
                        if (BigDecimal.ZERO < token.amount?.toBigDecimal() && !displayTokenCoins.contains(
                                token
                            ) && token.wallet_preload == true
                        ) {
                            displayTokenCoins.add(token)
                            tokenCoins.add(
                                Coin(
                                    token.contract, token.amount.toString(), CoinType.CW20
                                )
                            )
                        }
                    }
                    allTokenCoins.addAll(tokens)
                    searchTokenCoins.addAll(tokenCoins)
                }
            }
        }

        initRecyclerView()
        binding.refresher.isRefreshing = false
    }

    private fun initRecyclerView() {
        coinAdapter = CoinAdapter(
            requireContext(),
            selectedChain,
            searchStakeCoins,
            searchNativeCoins,
            searchBridgeCoins,
            searchIbcCoins,
            searchEtcCoins,
            searchTokenCoins
        )
        binding.recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = coinAdapter

            if (::coinAdapter.isInitialized) {
                coinAdapter.setOnItemClickListener { chain, denom, position, type ->
                    val sendAssetType = if (position == 0) {
                        if (chain is ChainOktEvm) {
                            SendAssetType.ONLY_EVM_COIN
                        } else if (chain.supportEvm && chain.supportCosmos()) {
                            SendAssetType.COSMOS_EVM_COIN
                        } else {
                            SendAssetType.ONLY_COSMOS_COIN
                        }

                    } else {
                        when (type) {
                            CoinType.CW20 -> {
                                SendAssetType.ONLY_COSMOS_CW20
                            }

                            CoinType.ERC20 -> {
                                SendAssetType.ONLY_EVM_ERC20
                            }

                            else -> {
                                SendAssetType.ONLY_COSMOS_COIN
                            }
                        }
                    }

                    if (selectedChain.isBankLocked()) {
                        requireActivity().makeToast(R.string.error_tranfer_disabled)
                        return@setOnItemClickListener
                    }

                    if (chain is ChainOkt996Keccak || chain is ChainOktEvm && sendAssetType == SendAssetType.ONLY_COSMOS_COIN) {
                        startLegacyTransfer(chain, denom)
                    } else {
                        startTransfer(chain, denom, sendAssetType)
                    }
                }
            }
        }
    }

    private fun initSearchView() {
        binding.apply {
            searchBar.visibleOrGone(searchStakeCoins.size + searchNativeCoins.size + searchBridgeCoins.size + searchIbcCoins.size + searchEtcCoins.size + searchTokenCoins.size > 15)
            searchView.setQuery("", false)
            searchView.clearFocus()
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    searchStakeCoins.clear()
                    searchNativeCoins.clear()
                    searchBridgeCoins.clear()
                    searchIbcCoins.clear()
                    searchEtcCoins.clear()
                    searchTokenCoins.clear()

                    if (StringUtils.isEmpty(newText)) {
                        searchStakeCoins.addAll(stakeCoins)
                        searchNativeCoins.addAll(nativeCoins)
                        searchBridgeCoins.addAll(bridgeCoins)
                        searchIbcCoins.addAll(ibcCoins)
                        searchEtcCoins.addAll(etcCoins)
                        searchTokenCoins.addAll(tokenCoins)

                    } else {
                        newText?.let { searchTxt ->
                            searchStakeCoins.addAll(stakeCoins.filter { coin ->
                                coin.denom.contains(searchTxt, ignoreCase = true)
                            })

                            searchNativeCoins.addAll(nativeCoins.filter { coin ->
                                BaseData.getAsset(selectedChain.apiName, coin.denom)?.let { asset ->
                                    asset.symbol?.contains(searchTxt, ignoreCase = true)
                                } ?: false
                            })

                            searchBridgeCoins.addAll(bridgeCoins.filter { coin ->
                                BaseData.getAsset(selectedChain.apiName, coin.denom)?.let { asset ->
                                    asset.symbol?.contains(searchTxt, ignoreCase = true)
                                } ?: false
                            })

                            searchIbcCoins.addAll(ibcCoins.filter { coin ->
                                BaseData.getAsset(selectedChain.apiName, coin.denom)?.let { asset ->
                                    asset.symbol?.contains(searchTxt, ignoreCase = true)
                                } ?: false
                            })

                            if (selectedChain is ChainOktEvm) {
                                searchEtcCoins.addAll(etcCoins.filter { coin ->
                                    (selectedChain as ChainOktEvm).oktFetcher()?.oktTokens?.get("data")?.asJsonArray?.firstOrNull { it.asJsonObject["symbol"].asString == coin.denom }
                                        ?.let { token ->
                                            token.asJsonObject["original_symbol"].asString.contains(
                                                searchTxt, true
                                            )
                                        } ?: false
                                })
                            }

                            searchTokenCoins.addAll(tokenCoins.filter { coin ->
                                BaseData.getToken(
                                    selectedChain, selectedChain.apiName, coin.denom
                                )?.symbol?.contains(searchTxt, ignoreCase = true) ?: false
                            })
                        }
                    }

                    if (searchStakeCoins.isEmpty() && searchNativeCoins.isEmpty() && searchBridgeCoins.isEmpty() && searchIbcCoins.isEmpty() && searchEtcCoins.isEmpty() && searchTokenCoins.isEmpty()) {
                        emptyLayout.visibility = View.VISIBLE
                        recycler.visibility = View.GONE
                    } else {
                        emptyLayout.visibility = View.GONE
                        recycler.visibility = View.VISIBLE
                        when (selectedChain) {
                            is ChainOkt996Keccak -> {
                                coinAdapter.setOktItems(searchStakeCoins, searchEtcCoins)
                            }

                            is ChainOktEvm -> {
                                coinAdapter.setOktEvmItems(
                                    searchStakeCoins, searchEtcCoins, searchTokenCoins
                                )
                            }

                            else -> {
                                coinAdapter.setItems(
                                    searchStakeCoins,
                                    searchNativeCoins,
                                    searchIbcCoins,
                                    searchBridgeCoins,
                                    searchTokenCoins
                                )
                            }
                        }
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
                    ApplicationViewModel.shared.loadChainData(selectedChain, account.id, false)
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.dropMoney.apply {
            visibleOrGone(selectedChain is ChainCosmos || selectedChain is ChainNeutron)
            setOnClickListener {
                Intent(requireActivity(), DappActivity::class.java).apply {
                    putExtra("dapp", "https://app.drop.money/dashboard?referral_code=dropmaga")
                    startActivity(this)
                }
            }
        }
    }

    private fun sunsetPopup() {
        val noticeType = when (selectedChain) {
            is ChainOkt996Keccak -> {
                NoticeType.LEGACY_PATH
            }

            else -> {
                return
            }
        }
        Handler(Looper.getMainLooper()).postDelayed({
            if (isAdded && isVisible && isResumed) {
                NoticeInfoFragment.newInstance(selectedChain, noticeType, null).show(
                    requireActivity().supportFragmentManager, NoticeInfoFragment::class.java.name
                )
            }
        }, 800)
    }

    private fun observeViewModels() {
        ApplicationViewModel.shared.hideValueResult.observe(viewLifecycleOwner) {
            coinAdapter.notifyDataSetChanged()
        }

        ApplicationViewModel.shared.fetchedResult.observe(viewLifecycleOwner) { tag ->
            if (selectedChain.tag == tag) {
                if (selectedChain.supportCw20 || selectedChain.supportEvm) {
                    ApplicationViewModel.shared.fetchedTokenResult.observe(viewLifecycleOwner) {
                        initData()
                        binding.loading.visibility = View.GONE
                    }
                } else {
                    initData()
                }
            }
        }
    }

    private fun startTransfer(chain: BaseChain, denom: String, sendAssetType: SendAssetType) {
        handleOneClickWithDelay(
            CommonTransferFragment.newInstance(
                chain, denom, sendAssetType
            )
        )
    }

    private fun startLegacyTransfer(chain: BaseChain, denom: String) {
        handleOneClickWithDelay(LegacyTransferFragment.newInstance(chain, denom))
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
        TokenEditFragment.newInstance(selectedChain,
            allTokenCoins,
            displayTokenCoins.map { it.contract }.toMutableList(),
            object : TokenEditListener {
                override fun edit(displayErc20Tokens: MutableList<String>) {
                    binding.loading.visibility = View.VISIBLE
                    BaseData.baseAccount?.let { account ->
                        ApplicationViewModel.shared.loadChainData(
                            selectedChain, account.id, false
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
        super.onDestroyView()
    }
}