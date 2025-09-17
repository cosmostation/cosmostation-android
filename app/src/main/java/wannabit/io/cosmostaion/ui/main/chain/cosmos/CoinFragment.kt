package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Parcelable
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
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCelestia
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCosmos
import wannabit.io.cosmostaion.chain.cosmosClass.ChainDydx
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.chain.testnetClass.ChainGnoTestnet
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.model.res.Coin
import wannabit.io.cosmostaion.data.model.res.CoinType
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.DialogBabylonInfoBinding
import wannabit.io.cosmostaion.databinding.DialogDropInfoBinding
import wannabit.io.cosmostaion.databinding.DialogDydxInfoBinding
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

    private val cw20TokenCoins = mutableListOf<Coin>()
    private val allCw20TokenCoins = mutableListOf<Token>()
    private var displayCw20TokenCoins = mutableListOf<Token>()
    private val searchCw20TokenCoins = mutableListOf<Coin>()

    private val erc20TokenCoins = mutableListOf<Coin>()
    private val allErc20TokenCoins = mutableListOf<Token>()
    private var displayErc20TokenCoins = mutableListOf<Token>()
    private val searchErc20TokenCoins = mutableListOf<Coin>()

    private val grc20TokenCoins = mutableListOf<Coin>()
    private val allGrc20TokenCoins = mutableListOf<Token>()
    private var displayGrc20TokenCoins = mutableListOf<Token>()
    private val searchGrc20TokenCoins = mutableListOf<Coin>()

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

        val cw20Tokens = mutableListOf<Token>()
        cw20Tokens.clear()
        cw20TokenCoins.clear()
        allCw20TokenCoins.clear()
        searchCw20TokenCoins.clear()
        displayCw20TokenCoins.clear()

        val erc20Tokens = mutableListOf<Token>()
        erc20Tokens.clear()
        erc20TokenCoins.clear()
        allErc20TokenCoins.clear()
        searchErc20TokenCoins.clear()
        displayErc20TokenCoins.clear()

        val grc20Tokens = mutableListOf<Token>()
        grc20Tokens.clear()
        grc20TokenCoins.clear()
        allGrc20TokenCoins.clear()
        searchGrc20TokenCoins.clear()
        displayGrc20TokenCoins.clear()

        when (selectedChain) {
            is ChainOktEvm -> {
                (selectedChain as ChainOktEvm).oktFetcher?.oktAccountInfo?.get("value")?.asJsonObject?.get(
                    "coins"
                )?.asJsonArray?.forEach { balance ->
                    if (balance.asJsonObject["denom"].asString == selectedChain.getStakeAssetDenom()) {
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
                if (stakeCoins.none { it.denom == selectedChain.getStakeAssetDenom() }) {
                    stakeCoins.add(Coin(selectedChain.getStakeAssetDenom(), "0", CoinType.STAKE))
                }
                searchStakeCoins.addAll(stakeCoins)

                etcCoins.sortBy { it.denom }
                searchEtcCoins.addAll(etcCoins)
            }

            else -> {
                if (selectedChain is ChainGnoTestnet) {
                    selectedChain.gnoRpcFetcher?.gnoBalances?.forEach { coin ->
                        val coinType = BaseData.getAsset(selectedChain.apiName, coin.denom)?.type
                        coinType?.let {
                            when (it) {
                                "staking", "native" -> {
                                    if (coin.denom == selectedChain.getStakeAssetDenom()) {
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

                } else {
                    selectedChain.cosmosFetcher?.cosmosBalances?.forEach { coin ->
                        val coinType = BaseData.getAsset(selectedChain.apiName, coin.denom)?.type
                        coinType?.let {
                            when (it) {
                                "staking", "native" -> {
                                    if (coin.denom == selectedChain.getStakeAssetDenom()) {
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
                }

                if (stakeCoins.none { it.denom == selectedChain.getStakeAssetDenom() }) {
                    stakeCoins.add(Coin(selectedChain.getStakeAssetDenom(), "0", CoinType.STAKE))
                }
                searchStakeCoins.addAll(stakeCoins)

                val mainCoin = (selectedChain.getChainListParam()?.get("main_asset_denom")?.asString
                    ?: "").uppercase()
                if (mainCoin.isNotEmpty()) {
                    if (nativeCoins.isEmpty()) {
                        nativeCoins.add(
                            Coin(
                                mainCoin, "0", CoinType.NATIVE
                            )
                        )

                    } else {
                        if (nativeCoins.none { coin -> coin.denom.uppercase() == mainCoin }) {
                            nativeCoins.add(
                                Coin(
                                    mainCoin, "0", CoinType.NATIVE
                                )
                            )
                        }
                    }
                }

                if (selectedChain is ChainGnoTestnet) {
                    nativeCoins.sortWith(compareByDescending {
                        selectedChain.gnoRpcFetcher?.balanceValue(
                            it.denom
                        )
                    })

                    ibcCoins.sortWith(compareByDescending {
                        selectedChain.gnoRpcFetcher?.balanceValue(
                            it.denom
                        )
                    })

                    bridgeCoins.sortWith(compareByDescending {
                        selectedChain.gnoRpcFetcher?.balanceValue(
                            it.denom
                        )
                    })

                } else {
                    nativeCoins.sortWith(compareByDescending {
                        selectedChain.cosmosFetcher?.balanceValue(
                            it.denom
                        )
                    })

                    ibcCoins.sortWith(compareByDescending {
                        selectedChain.cosmosFetcher?.balanceValue(
                            it.denom
                        )
                    })

                    bridgeCoins.sortWith(compareByDescending {
                        selectedChain.cosmosFetcher?.balanceValue(
                            it.denom
                        )
                    })
                }
                searchNativeCoins.addAll(nativeCoins)
                searchIbcCoins.addAll(ibcCoins)
                searchBridgeCoins.addAll(bridgeCoins)
            }
        }

        BaseData.baseAccount?.let { account ->
            if (selectedChain.evmRpcFetcher?.evmTokens?.isNotEmpty() == true) {
                selectedChain.evmRpcFetcher?.evmTokens?.let { erc20Tokens.addAll(it) }
                erc20Tokens.sortBy { it.symbol.lowercase() }
                Prefs.getDisplayErc20s(account.id, selectedChain.tag)?.let { userCustomTokens ->
                    erc20Tokens.sortWith { o1, o2 ->
                        val address0 = o1.address
                        val address1 = o2.address

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

                    erc20Tokens.forEach { token ->
                        if (userCustomTokens.contains(token.address) && !displayErc20TokenCoins.contains(
                                token
                            )
                        ) {
                            displayErc20TokenCoins.add(token)
                            erc20TokenCoins.add(
                                Coin(
                                    token.address, token.amount.toString(), CoinType.ERC20
                                )
                            )
                        }
                    }
                    allErc20TokenCoins.addAll(erc20Tokens)
                    searchErc20TokenCoins.addAll(erc20TokenCoins)

                } ?: run {
                    erc20Tokens.sortWith { o1, o2 ->
                        when {
                            o1.wallet_preload == true && o2.wallet_preload == false -> -1
                            o1.wallet_preload == false && o2.wallet_preload == true -> 1
                            BigDecimal.ZERO < o1.amount?.toBigDecimal() && BigDecimal.ZERO >= o2.amount?.toBigDecimal() -> -1
                            BigDecimal.ZERO >= o1.amount?.toBigDecimal() && BigDecimal.ZERO < o2.amount?.toBigDecimal() -> 1
                            else -> {
                                val value0 = selectedChain.evmRpcFetcher?.tokenValue(o1.address)
                                    ?: BigDecimal.ZERO
                                val value1 = selectedChain.evmRpcFetcher?.tokenValue(o2.address)
                                    ?: BigDecimal.ZERO
                                value1.compareTo(value0)
                            }
                        }
                    }

                    erc20Tokens.forEach { token ->
                        if (!displayErc20TokenCoins.contains(token) && token.wallet_preload == true) {
                            displayErc20TokenCoins.add(token)
                            erc20TokenCoins.add(
                                Coin(
                                    token.address, token.amount.toString(), CoinType.ERC20
                                )
                            )
                        }
                    }
                    allErc20TokenCoins.addAll(erc20Tokens)
                    searchErc20TokenCoins.addAll(erc20TokenCoins)
                }
            }

            if (selectedChain.cosmosFetcher?.tokens?.isNotEmpty() == true) {
                selectedChain.cosmosFetcher?.tokens?.let { cw20Tokens.addAll(it) }
                cw20Tokens.sortBy { it.symbol.lowercase() }

                Prefs.getDisplayCw20s(account.id, selectedChain.tag)?.let { userCustomTokens ->
                    cw20Tokens.sortWith { o1, o2 ->
                        val address0 = o1.address
                        val address1 = o2.address

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

                    cw20Tokens.forEach { token ->
                        if (userCustomTokens.contains(token.address) && !displayCw20TokenCoins.contains(
                                token
                            )
                        ) {
                            displayCw20TokenCoins.add(token)
                            cw20TokenCoins.add(
                                Coin(
                                    token.address, token.amount.toString(), CoinType.CW20
                                )
                            )
                        }
                    }
                    allCw20TokenCoins.addAll(cw20Tokens)
                    searchCw20TokenCoins.addAll(cw20TokenCoins)

                } ?: run {
                    cw20Tokens.sortWith { o1, o2 ->
                        when {
                            BigDecimal.ZERO < o1.amount?.toBigDecimal() && BigDecimal.ZERO >= o2.amount?.toBigDecimal() -> -1
                            BigDecimal.ZERO >= o1.amount?.toBigDecimal() && BigDecimal.ZERO < o2.amount?.toBigDecimal() -> 1
                            else -> {
                                val value0 = selectedChain.cosmosFetcher?.tokenValue(o1.address)
                                    ?: BigDecimal.ZERO
                                val value1 = selectedChain.cosmosFetcher?.tokenValue(o2.address)
                                    ?: BigDecimal.ZERO
                                value1.compareTo(value0)
                            }
                        }
                    }

                    cw20Tokens.forEach { token ->
                        if (!displayCw20TokenCoins.contains(token) && BigDecimal.ZERO < token.amount?.toBigDecimal()) {
                            displayCw20TokenCoins.add(token)
                            cw20TokenCoins.add(
                                Coin(
                                    token.address, token.amount.toString(), CoinType.CW20
                                )
                            )
                        }
                    }
                    allCw20TokenCoins.addAll(cw20Tokens)
                    searchCw20TokenCoins.addAll(cw20TokenCoins)
                }
            }

            if (selectedChain.gnoRpcFetcher?.grc20Tokens?.isNotEmpty() == true) {
                selectedChain.gnoRpcFetcher?.grc20Tokens?.let { grc20Tokens.addAll(it) }
                grc20Tokens.sortBy { it.symbol.lowercase() }

                Prefs.getDisplayGrc20s(account.id, selectedChain.tag)?.let { userCustomTokens ->
                    grc20Tokens.sortWith { o1, o2 ->
                        val address0 = o1.address
                        val address1 = o2.address

                        val containsToken0 = userCustomTokens.contains(address0)
                        val containsToken1 = userCustomTokens.contains(address1)

                        when {
                            containsToken0 && !containsToken1 -> -1
                            !containsToken0 && containsToken1 -> 1
                            else -> {
                                val value0 = selectedChain.gnoRpcFetcher?.grc20TokenValue(address0)
                                    ?: BigDecimal.ZERO
                                val value1 = selectedChain.gnoRpcFetcher?.grc20TokenValue(address1)
                                    ?: BigDecimal.ZERO
                                value1.compareTo(value0)
                            }
                        }
                    }

                    grc20Tokens.forEach { token ->
                        if (userCustomTokens.contains(token.address) && !displayGrc20TokenCoins.contains(
                                token
                            )
                        ) {
                            displayGrc20TokenCoins.add(token)
                            grc20TokenCoins.add(
                                Coin(
                                    token.address, token.amount.toString(), CoinType.GRC20
                                )
                            )
                        }
                    }
                    allGrc20TokenCoins.addAll(grc20Tokens)
                    searchGrc20TokenCoins.addAll(grc20TokenCoins)

                } ?: run {
                    grc20Tokens.sortWith { o1, o2 ->
                        when {
                            o1.wallet_preload == true && o2.wallet_preload == false -> -1
                            o1.wallet_preload == false && o2.wallet_preload == true -> 1
                            BigDecimal.ZERO < o1.amount?.toBigDecimal() && BigDecimal.ZERO >= o2.amount?.toBigDecimal() -> -1
                            BigDecimal.ZERO >= o1.amount?.toBigDecimal() && BigDecimal.ZERO < o2.amount?.toBigDecimal() -> 1
                            else -> {
                                val value0 =
                                    selectedChain.gnoRpcFetcher?.grc20TokenValue(o1.address)
                                        ?: BigDecimal.ZERO
                                val value1 =
                                    selectedChain.gnoRpcFetcher?.grc20TokenValue(o2.address)
                                        ?: BigDecimal.ZERO
                                value1.compareTo(value0)
                            }
                        }
                    }

                    grc20Tokens.forEach { token ->
                        if (!displayGrc20TokenCoins.contains(token) && token.wallet_preload == true) {
                            displayGrc20TokenCoins.add(token)
                            grc20TokenCoins.add(
                                Coin(
                                    token.address, token.amount.toString(), CoinType.GRC20
                                )
                            )
                        }
                    }
                    allGrc20TokenCoins.addAll(grc20Tokens)
                    searchGrc20TokenCoins.addAll(grc20TokenCoins)
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
            searchCw20TokenCoins,
            searchErc20TokenCoins,
            searchGrc20TokenCoins,
            selectClickAction
        )
        binding.recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = coinAdapter

            if (::coinAdapter.isInitialized) {
                coinAdapter.setOnItemClickListener { chain, denom, position, type ->
                    if (!selectedChain.isSendEnabled()) {
                        requireActivity().makeToast(R.string.error_tranfer_disabled)
                        return@setOnItemClickListener
                    }

                    if (position == 0) {
                        if (chain.isEvmCosmos() && chain !is ChainOktEvm) {
                            handleOneClickWithDelay(
                                SelectSendTypeFragment.newInstance(
                                    selectedChain,
                                    denom
                                )
                            )

                        } else {
                            val sendAssetType = if (chain is ChainOktEvm) {
                                SendAssetType.ONLY_EVM_COIN
                            } else {
                                SendAssetType.ONLY_COSMOS_COIN
                            }

                            if (chain is ChainOkt996Keccak) {
                                startLegacyTransfer(chain, denom)
                            } else {
                                startTransfer(chain, denom, sendAssetType)
                            }
                        }

                    } else {
                        val sendAssetType = when (type) {
                            CoinType.CW20 -> {
                                SendAssetType.ONLY_COSMOS_CW20
                            }

                            CoinType.ERC20 -> {
                                SendAssetType.ONLY_EVM_ERC20
                            }

                            CoinType.GRC20 -> {
                                SendAssetType.ONLY_COSMOS_GRC20
                            }

                            else -> {
                                SendAssetType.ONLY_COSMOS_COIN
                            }
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
    }

    private fun initSearchView() {
        binding.apply {
            searchBar.visibleOrGone(searchStakeCoins.size + searchNativeCoins.size + searchBridgeCoins.size + searchIbcCoins.size + searchEtcCoins.size + searchCw20TokenCoins.size + searchErc20TokenCoins.size + searchGrc20TokenCoins.size > 15)
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
                    searchCw20TokenCoins.clear()
                    searchErc20TokenCoins.clear()
                    searchGrc20TokenCoins.clear()

                    if (StringUtils.isEmpty(newText)) {
                        searchStakeCoins.addAll(stakeCoins)
                        searchNativeCoins.addAll(nativeCoins)
                        searchBridgeCoins.addAll(bridgeCoins)
                        searchIbcCoins.addAll(ibcCoins)
                        searchEtcCoins.addAll(etcCoins)
                        searchCw20TokenCoins.addAll(cw20TokenCoins)
                        searchErc20TokenCoins.addAll(erc20TokenCoins)
                        searchGrc20TokenCoins.addAll(grc20TokenCoins)

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
                                    BaseData.getAsset(selectedChain.apiName, coin.denom)
                                        ?.let { asset ->
                                            asset.symbol?.contains(searchTxt, ignoreCase = true)
                                        } ?: false
                                })
                            }

                            searchCw20TokenCoins.addAll(cw20TokenCoins.filter { coin ->
                                BaseData.getToken(
                                    selectedChain, selectedChain.apiName, coin.denom
                                )?.symbol?.contains(searchTxt, ignoreCase = true) ?: false
                            })

                            searchErc20TokenCoins.addAll(erc20TokenCoins.filter { coin ->
                                BaseData.getToken(
                                    selectedChain, selectedChain.apiName, coin.denom
                                )?.symbol?.contains(searchTxt, ignoreCase = true) ?: false
                            })

                            searchGrc20TokenCoins.addAll(grc20TokenCoins.filter { coin ->
                                BaseData.getToken(
                                    selectedChain, selectedChain.apiName, coin.denom
                                )?.symbol?.contains(searchTxt, ignoreCase = true) ?: false
                            })
                        }
                    }

                    if (searchStakeCoins.isEmpty() && searchNativeCoins.isEmpty() && searchBridgeCoins.isEmpty() && searchIbcCoins.isEmpty() && searchEtcCoins.isEmpty() && searchErc20TokenCoins.isEmpty() && searchGrc20TokenCoins.isEmpty()) {
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
                                    searchStakeCoins,
                                    searchEtcCoins,
                                    searchCw20TokenCoins,
                                    searchErc20TokenCoins
                                )
                            }

                            else -> {
                                coinAdapter.setItems(
                                    searchStakeCoins,
                                    searchNativeCoins,
                                    searchIbcCoins,
                                    searchBridgeCoins,
                                    searchCw20TokenCoins,
                                    searchErc20TokenCoins,
                                    searchGrc20TokenCoins
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
                    ApplicationViewModel.shared.loadChainData(selectedChain, account.id)
                }
            }
        }
    }

    private val selectClickAction = object : CoinAdapter.ClickListener {
        override fun btcStatus() {
            handleOneClickWithDelay(BabylonBtcStatusFragment.newInstance(selectedChain))
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            dropMoney.visibleOrGone(selectedChain is ChainCosmos || selectedChain is ChainNeutron || selectedChain is ChainCelestia)
            dropMoney.setOnClickListener {
                val savedTime = Prefs.getDappInfoHideTime(0)
                val currentTime = System.currentTimeMillis()
                if (currentTime >= savedTime) {
                    showDropInfo()
                } else {
                    Intent(requireActivity(), DappActivity::class.java).apply {
                        putExtra("dapp", "https://app.drop.money/dashboard?referral_code=dropmaga")
                        startActivity(this)
                    }
                }
            }

            dydxTrade.visibleOrGone(selectedChain is ChainDydx)
            dydxTrade.setOnClickListener {
                val savedTime = Prefs.getDappInfoHideTime(1)
                val currentTime = System.currentTimeMillis()
                if (currentTime >= savedTime) {
                    showDydxInfo()
                } else {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=trade.opsdao.dydxchain")
                        )
                    )
                }
            }

            babylonStaking.visibleOrGone(selectedChain.isSupportStaking())
            babylonStaking.setOnClickListener {
                if (selectedChain.btcStakingDapp().isNotEmpty()) {
                    val savedTime = Prefs.getDappInfoHideTime(2)
                    val currentTime = System.currentTimeMillis()
                    if (currentTime >= savedTime) {
                        showBabylonInfo()
                    } else {
                        Intent(requireActivity(), DappActivity::class.java).apply {
                            putExtra("dapp", selectedChain.btcStakingDapp())
                            putExtra("selectedBitChain", selectedChain as Parcelable)
                            startActivity(this)
                        }
                    }
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
                initData()
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
        val allTokens = if (selectedChain.isSupportCw20() && selectedChain.isSupportErc20()) {
            (allCw20TokenCoins + allErc20TokenCoins).toMutableList()
        } else if (selectedChain.isSupportCw20()) {
            allCw20TokenCoins
        } else if (selectedChain.isSupportGrc20()) {
            allGrc20TokenCoins
        } else {
            allErc20TokenCoins
        }
        val displayTokens = if (selectedChain.isSupportCw20() && selectedChain.isSupportErc20()) {
            (displayCw20TokenCoins.map { it.address } + displayErc20TokenCoins.map { it.address }).toMutableList()
        } else if (selectedChain.isSupportCw20()) {
            displayCw20TokenCoins.map { it.address }
        } else if (selectedChain.isSupportGrc20()) {
            displayGrc20TokenCoins.map { it.address }
        } else {
            displayErc20TokenCoins.map { it.address }
        }

        TokenEditFragment.newInstance(
            selectedChain,
            allTokens,
            displayTokens.toMutableList(),
            object : TokenEditListener {
                override fun edit() {
                    binding.loading.visibility = View.VISIBLE
                    BaseData.baseAccount?.let { account ->
                        ApplicationViewModel.shared.loadChainData(selectedChain, account.id)
                    }
                }
            }).show(
            requireActivity().supportFragmentManager, TokenEditFragment::class.java.name
        )
    }

    private fun showDropInfo() {
        val inflater =
            requireActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DialogDropInfoBinding.inflate(inflater)
        val alertDialog = AlertDialog.Builder(requireContext(), R.style.AppTheme_AlertDialogTheme)
            .setView(binding.root)

        val dialog = alertDialog.create()
        dialog.setCancelable(false)
        dialog.show()

        binding.apply {
            dappView.setBackgroundResource(R.drawable.dialog_transparent_bg)
            btnDapp.setBackgroundResource(R.drawable.button_liquid_bg)

            var isDropPinned = false
            btnCheck.setOnClickListener {
                isDropPinned = !isDropPinned
                if (isDropPinned) {
                    checkImg.setImageResource(R.drawable.icon_checkbox_on)
                } else {
                    checkImg.setImageResource(R.drawable.icon_checkbox_off)
                }
            }

            btnClose.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            btnClose.setOnClickListener {
                if (isDropPinned) {
                    Prefs.setDappInfoHideTime(0)
                }
                dialog.dismiss()
            }

            btnDapp.setOnClickListener {
                dialog.dismiss()
                Intent(requireActivity(), DappActivity::class.java).apply {
                    putExtra("dapp", "https://app.drop.money/dashboard?referral_code=dropmaga")
                    startActivity(this)
                }
            }
        }
    }

    private fun showDydxInfo() {
        val inflater =
            requireActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DialogDydxInfoBinding.inflate(inflater)
        val alertDialog = AlertDialog.Builder(requireContext(), R.style.AppTheme_AlertDialogTheme)
            .setView(binding.root)

        val dialog = alertDialog.create()
        dialog.setCancelable(false)
        dialog.show()

        binding.apply {
            dappView.setBackgroundResource(R.drawable.dialog_transparent_bg)
            btnDapp.setBackgroundResource(R.drawable.button_download_bg)

            var isDydxPinned = false
            btnCheck.setOnClickListener {
                isDydxPinned = !isDydxPinned
                if (isDydxPinned) {
                    checkImg.setImageResource(R.drawable.icon_checkbox_on)
                } else {
                    checkImg.setImageResource(R.drawable.icon_checkbox_off)
                }
            }

            btnClose.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            btnClose.setOnClickListener {
                if (isDydxPinned) {
                    Prefs.setDappInfoHideTime(1)
                }
                dialog.dismiss()
            }

            btnDapp.setOnClickListener {
                dialog.dismiss()
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW, Uri.parse("market://details?id=trade.opsdao.dydxchain")
                    )
                )
            }
        }
    }

    private fun showBabylonInfo() {
        val inflater =
            requireActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DialogBabylonInfoBinding.inflate(inflater)
        val alertDialog = AlertDialog.Builder(requireContext(), R.style.AppTheme_AlertDialogTheme)
            .setView(binding.root)

        val dialog = alertDialog.create()
        dialog.setCancelable(false)
        dialog.show()

        binding.apply {
            dappView.setBackgroundResource(R.drawable.dialog_transparent_bg)
            btnDapp.setBackgroundResource(R.drawable.button_babylon_bg)

            var isBabylonPinned = false
            btnCheck.setOnClickListener {
                isBabylonPinned = !isBabylonPinned
                if (isBabylonPinned) {
                    checkImg.setImageResource(R.drawable.icon_checkbox_on)
                } else {
                    checkImg.setImageResource(R.drawable.icon_checkbox_off)
                }
            }

            btnClose.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            btnClose.setOnClickListener {
                if (isBabylonPinned) {
                    Prefs.setDappInfoHideTime(2)
                }
                dialog.dismiss()
            }

            btnDapp.setOnClickListener {
                dialog.dismiss()
                Intent(requireActivity(), DappActivity::class.java).apply {
                    putExtra("dapp", selectedChain.btcStakingDapp())
                    putExtra("selectedBitChain", selectedChain as Parcelable)
                    startActivity(this)
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        ApplicationViewModel.shared.fetchedResult.removeObservers(viewLifecycleOwner)
        super.onDestroyView()
    }
}