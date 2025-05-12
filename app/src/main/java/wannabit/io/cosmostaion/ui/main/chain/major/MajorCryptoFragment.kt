package wannabit.io.cosmostaion.ui.main.chain.major

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Paint
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
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.FetchState
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.fetcher.suiCoinSymbol
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.majorClass.ChainIota
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.majorClass.IOTA_MAIN_DENOM
import wannabit.io.cosmostaion.chain.majorClass.SUI_MAIN_DENOM
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.DialogBabylonInfoBinding
import wannabit.io.cosmostaion.databinding.FragmentCoinBinding
import wannabit.io.cosmostaion.ui.main.dapp.DappActivity
import wannabit.io.cosmostaion.ui.tx.genTx.CommonTransferFragment
import wannabit.io.cosmostaion.ui.tx.genTx.SendAssetType
import java.math.BigDecimal

class MajorCryptoFragment : Fragment() {

    private var _binding: FragmentCoinBinding? = null
    private val binding get() = _binding!!

    private lateinit var majorCryptoAdapter: MajorCryptoAdapter

    private lateinit var selectedChain: BaseChain

    private var moveBalances: MutableList<Pair<String?, BigDecimal?>> = mutableListOf()
    private var searchMoveBalances: MutableList<Pair<String?, BigDecimal?>> = mutableListOf()
    private var moveNativeBalances: MutableList<Pair<String?, BigDecimal?>> = mutableListOf()
    private var searchMoveNativeBalances: MutableList<Pair<String?, BigDecimal?>> = mutableListOf()

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): MajorCryptoFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = MajorCryptoFragment()
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

        initData()
        sortAssets()
        refreshData()
        observeViewModels()
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
        binding.apply {
            dropMoney.visibility = View.GONE
            dydxTrade.visibility = View.GONE
            babylonStaking.visibleOrGone(selectedChain.isSupportStaking() && (selectedChain.accountKeyType.pubkeyType == PubKeyType.BTC_NATIVE_SEGWIT || selectedChain.accountKeyType.pubkeyType == PubKeyType.BTC_TAPROOT))

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

    private fun sortAssets() {
        lifecycleScope.launch(Dispatchers.IO) {
            moveBalances.clear()
            searchMoveBalances.clear()
            moveNativeBalances.clear()
            searchMoveNativeBalances.clear()

            when (selectedChain) {
                is ChainSui -> {
                    (selectedChain as ChainSui).suiFetcher()?.let { fetcher ->
                        val tempSuiBalances: MutableList<Pair<String?, BigDecimal?>> =
                            mutableListOf()
                        tempSuiBalances.addAll(fetcher.suiBalances)

                        if (tempSuiBalances.none { it.first == SUI_MAIN_DENOM }) {
                            tempSuiBalances.add(Pair(SUI_MAIN_DENOM, BigDecimal.ZERO))
                        }
                        synchronized(tempSuiBalances) {
                            tempSuiBalances.sortWith { o1, o2 ->
                                when {
                                    o1.first == SUI_MAIN_DENOM -> -1
                                    o2.first == SUI_MAIN_DENOM -> 1
                                    else -> {
                                        val value0 = fetcher.suiBalanceValue(o1.first ?: "")
                                        val value1 = fetcher.suiBalanceValue(o2.first ?: "")
                                        value1.compareTo(value0)
                                    }
                                }
                            }
                        }

                        tempSuiBalances.firstOrNull()?.let { moveBalances.add(it) }
                        searchMoveBalances.addAll(moveBalances)
                        moveNativeBalances.addAll(tempSuiBalances.drop(1))
                        searchMoveNativeBalances.addAll(moveNativeBalances)

                        withContext(Dispatchers.Main) {
                            initRecyclerView()
                            initSearchView(fetcher.suiBalances, moveBalances)
                        }
                    }
                }

                is ChainIota -> {
                    (selectedChain as ChainIota).iotaFetcher()?.let { fetcher ->
                        val tempIotaBalances: MutableList<Pair<String?, BigDecimal?>> =
                            mutableListOf()
                        tempIotaBalances.addAll(fetcher.iotaBalances)

                        if (tempIotaBalances.none { it.first == IOTA_MAIN_DENOM }) {
                            tempIotaBalances.add(Pair(IOTA_MAIN_DENOM, BigDecimal.ZERO))
                        }
                        synchronized(tempIotaBalances) {
                            tempIotaBalances.sortWith { o1, o2 ->
                                when {
                                    o1.first == IOTA_MAIN_DENOM -> -1
                                    o2.first == IOTA_MAIN_DENOM -> 1
                                    else -> {
                                        val value0 = fetcher.iotaBalanceValue(o1.first ?: "")
                                        val value1 = fetcher.iotaBalanceValue(o2.first ?: "")
                                        value1.compareTo(value0)
                                    }
                                }
                            }
                        }

                        tempIotaBalances.firstOrNull()?.let { moveBalances.add(it) }
                        searchMoveBalances.addAll(moveBalances)
                        moveNativeBalances.addAll(tempIotaBalances.drop(1))
                        searchMoveNativeBalances.addAll(moveNativeBalances)

                        withContext(Dispatchers.Main) {
                            initRecyclerView()
                            initSearchView(fetcher.iotaBalances, moveBalances)
                        }
                    }
                }

                else -> {
                    (selectedChain as ChainBitCoin86).btcFetcher()?.let {
                        withContext(Dispatchers.Main) {
                            initRecyclerView()
                            binding.searchBar.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        if (isAdded) {
            majorCryptoAdapter = MajorCryptoAdapter(
                requireContext(), selectedChain, searchMoveBalances, searchMoveNativeBalances
            )
            binding.recycler.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = majorCryptoAdapter
                majorCryptoAdapter.notifyDataSetChanged()

                majorCryptoAdapter.setOnItemClickListener { chain, denom ->
                    val sendAssetType = when (chain) {
                        is ChainSui -> SendAssetType.SUI_COIN
                        is ChainIota -> SendAssetType.IOTA_COIN
                        else -> SendAssetType.BIT_COIN
                    }

                    if (chain is ChainBitCoin86) {
                        chain.btcFetcher()?.let { fetcher ->
                            lifecycleScope.launch(Dispatchers.IO) {
                                val btcFee = fetcher.initFee()
                                withContext(Dispatchers.Main) {
                                    if (btcFee == null) return@withContext
                                    if (fetcher.btcBalances > btcFee) {
                                        handleOneClickWithDelay(
                                            CommonTransferFragment.newInstance(
                                                chain, denom, sendAssetType
                                            )
                                        )

                                    } else {
                                        if (fetcher.btcPendingInput > BigDecimal.ZERO) {
                                            requireContext().makeToast(R.string.error_pending_balance)
                                        } else {
                                            requireContext().makeToast(R.string.error_not_enough_fee)
                                        }
                                        return@withContext
                                    }
                                }
                            }
                        }

                    } else {
                        handleOneClickWithDelay(
                            CommonTransferFragment.newInstance(
                                chain, denom, sendAssetType
                            )
                        )
                    }
                }
            }
            binding.refresher.isRefreshing = false
        }
    }

    private fun initSearchView(
        suiBalances: MutableList<Pair<String?, BigDecimal?>>,
        tempSuiBalances: MutableList<Pair<String?, BigDecimal?>>
    ) {
        binding.apply {
            searchBar.visibleOrGone(suiBalances.size > 15)
            searchView.setQuery("", false)
            searchView.clearFocus()
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (selectedChain is ChainSui || selectedChain is ChainIota) {
                        searchMoveBalances.clear()
                        searchMoveNativeBalances.clear()

                        if (StringUtils.isEmpty(newText)) {
                            searchMoveBalances.addAll(tempSuiBalances)
                            searchMoveNativeBalances.addAll(moveNativeBalances)

                        } else {
                            newText?.let { searchTxt ->
                                searchMoveBalances.addAll(tempSuiBalances.filter { balance ->
                                    balance.first?.let { denom ->
                                        BaseData.getAsset(selectedChain.apiName, denom)
                                            ?.let { asset ->
                                                asset.symbol?.contains(searchTxt, ignoreCase = true)
                                            }
                                    } ?: false
                                })

                                searchMoveNativeBalances.addAll(moveNativeBalances.filter { balance ->
                                    balance.first?.let { denom ->
                                        val asset = BaseData.getAsset(selectedChain.apiName, denom)
                                        val metaData =
                                            (selectedChain as ChainSui).suiFetcher?.suiCoinMeta?.get(
                                                denom
                                            )

                                        if (asset != null) {
                                            asset.symbol?.contains(searchTxt, ignoreCase = true)
                                        } else if (metaData != null) {
                                            metaData["symbol"].asString.contains(
                                                searchTxt, ignoreCase = true
                                            )
                                        } else {
                                            balance.first.suiCoinSymbol()
                                                ?.contains(searchTxt, ignoreCase = true)
                                        }
                                    } ?: false
                                })
                            }
                        }
                    }

                    if (searchMoveBalances.isEmpty() && searchMoveNativeBalances.isEmpty()) {
                        emptyLayout.visibility = View.VISIBLE
                        recycler.visibility = View.GONE
                    } else {
                        emptyLayout.visibility = View.GONE
                        recycler.visibility = View.VISIBLE
                        majorCryptoAdapter.notifyDataSetChanged()
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
                    selectedChain.fetchState = FetchState.IDLE
                    when (selectedChain) {
                        is ChainSui -> {
                            ApplicationViewModel.shared.loadSuiData(
                                account.id, selectedChain
                            )
                        }

                        is ChainIota -> {
                            ApplicationViewModel.shared.loadIotaData(
                                account.id, selectedChain
                            )
                        }

                        else -> {
                            ApplicationViewModel.shared.loadBtcData(
                                account.id, selectedChain as ChainBitCoin86
                            )
                        }
                    }
                }
            }
        }
    }

    private fun observeViewModels() {
        ApplicationViewModel.shared.hideValueResult.observe(viewLifecycleOwner) {
            if (::majorCryptoAdapter.isInitialized) {
                majorCryptoAdapter.notifyDataSetChanged()
            }
        }

        ApplicationViewModel.shared.fetchedResult.observe(viewLifecycleOwner) { tag ->
            ApplicationViewModel.shared.notifyTxEvent()
            if (selectedChain.tag == tag) {
                sortAssets()
            }
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
        ApplicationViewModel.shared.fetchedTokenResult.removeObservers(viewLifecycleOwner)
        super.onDestroyView()
    }
}