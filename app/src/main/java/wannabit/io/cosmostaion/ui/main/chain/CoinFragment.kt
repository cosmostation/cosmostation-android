package wannabit.io.cosmostaion.ui.main.chain

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainKava459
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt60
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.data.model.res.Coin
import wannabit.io.cosmostaion.data.model.res.CoinType
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.FragmentCoinBinding
import wannabit.io.cosmostaion.ui.dialog.tx.BridgeOptionFragment
import wannabit.io.cosmostaion.ui.tx.step.LegacyTransferFragment
import wannabit.io.cosmostaion.ui.tx.step.TransferFragment
import wannabit.io.cosmostaion.ui.tx.step.kava.Bep3Fragment

class CoinFragment(position: Int) : Fragment() {

    private var _binding: FragmentCoinBinding? = null
    private val binding get() = _binding!!

    private lateinit var coinAdapter: CoinAdapter
    private lateinit var selectedChain: CosmosLine
    private val selectedPosition = position

    private var baseAccount: BaseAccount? = null

    private val stakeCoins = mutableListOf<Coin>()
    private val nativeCoins = mutableListOf<Coin>()
    private val bridgeCoins = mutableListOf<Coin>()
    private val ibcCoins = mutableListOf<Coin>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refreshData()
        initRecyclerView()
        initData()
    }

    private fun refreshData() {
        binding.refresher.setOnRefreshListener {
            if (!selectedChain.fetched) {
                binding.refresher.isRefreshing = false
            } else {
                baseAccount?.let { baseAccount ->
                    selectedChain.loadData(baseAccount.id)

                    selectedChain.setLoadDataCallBack(object : CosmosLine.LoadDataCallback {
                        override fun onDataLoaded(isLoaded: Boolean) {
                            lifecycleScope.launch(Dispatchers.Main) {
                                if (selectedChain.fetched) {
                                    initData()
                                }
                            }
                        }
                    })
                }
            }
        }
    }

    private fun initRecyclerView() {
        baseAccount = BaseData.baseAccount
        baseAccount?.let { baseAccount ->
            selectedChain = baseAccount.displayCosmosLineChains[selectedPosition]

            coinAdapter = CoinAdapter(requireContext(), selectedChain)
            binding.recycler.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = coinAdapter

                coinAdapter.setOnItemClickListener { line, denom ->
                    if (!selectedChain.isTxFeePayable(requireContext())) {
                        requireContext().makeToast(R.string.error_not_enough_fee)
                        return@setOnItemClickListener
                    }

                    if (line is ChainBinanceBeacon) {
                        if (BaseUtils.isHtlcSwappableCoin(line, denom)) {
                            selectBridgeOption(line, denom)
                        } else {
                            startLegacyTransfer(line, denom)
                        }

                    } else if (line is ChainKava459) {
                        if (BaseUtils.isHtlcSwappableCoin(line, denom)) {
                            selectBridgeOption(line, denom)
                        } else {
                            startTransfer(line, denom)
                        }

                    } else {
                        startTransfer(line, denom)
                    }
                }
            }
        }
    }

    private fun initData() {
        stakeCoins.clear()
        nativeCoins.clear()
        ibcCoins.clear()
        bridgeCoins.clear()

        selectedChain.stakeDenom?.let { stakeDenom ->
            when (selectedChain) {
                is ChainBinanceBeacon -> {
                    selectedChain.lcdAccountInfo?.balances?.forEach { balance ->
                        if (balance.symbol == stakeDenom) {
                            stakeCoins.add(Coin(balance.symbol, balance.free, CoinType.STAKE))
                        } else {
                            val totalBalance = balance.free.toBigDecimal().add(balance.frozen.toBigDecimal()).add(balance.locked.toBigDecimal())
                            nativeCoins.add(Coin(balance.symbol, totalBalance.toPlainString(), CoinType.ETC))
                        }
                    }
                    if (stakeCoins.firstOrNull { it.denom == stakeDenom } == null) {
                        stakeCoins.add(Coin(stakeDenom, "0", CoinType.STAKE))
                    }
                    nativeCoins.sortWith(compareBy { it.denom })
                }

                is ChainOkt60 -> {
                    (selectedChain as ChainOkt60).oktLcdAccountInfo?.value?.coins?.forEach { balance ->
                        if (balance.denom == stakeDenom) {
                            stakeCoins.add(Coin(balance.denom, balance.amount, CoinType.STAKE))
                        } else {
                            nativeCoins.add(Coin(balance.denom, balance.amount, CoinType.ETC))
                        }
                    }
                    if (stakeCoins.firstOrNull { it.denom == stakeDenom } == null) {
                        stakeCoins.add(Coin(stakeDenom, "0", CoinType.STAKE))
                    }
                    nativeCoins.sortWith(compareBy { it.denom })
                }

                else -> {
                    selectedChain.cosmosBalances.forEach { coin ->
                        val coinType = BaseData.getAsset(selectedChain.apiName, coin.denom)?.type
                        if (coinType != null) {
                            when (coinType) {
                                "staking" -> stakeCoins.add(Coin(coin.denom, coin.amount, CoinType.STAKE))
                                "native" -> nativeCoins.add(Coin(coin.denom, coin.amount, CoinType.NATIVE))
                                "bep", "bridge" -> bridgeCoins.add(Coin(coin.denom, coin.amount, CoinType.BRIDGE))
                                "ibc" -> ibcCoins.add(Coin(coin.denom, coin.amount, CoinType.IBC))
                            }
                        }
                    }

                    if (stakeCoins.firstOrNull { it.denom == selectedChain.stakeDenom } == null) {
                        stakeCoins.add(Coin(stakeDenom, "0", CoinType.STAKE))
                    }
                    nativeCoins.sortWith { o1, o2 ->
                        when {
                            o1.denom == selectedChain.stakeDenom -> -1
                            o2.denom == selectedChain.stakeDenom -> 1
                            else -> {
                                val value0 = selectedChain.balanceValue(o1.denom)
                                val value1 = selectedChain.balanceValue(o2.denom)
                                when {
                                    value0 > value1 -> -1
                                    value0 < value1 -> 1
                                    else -> 0
                                }
                            }
                        }
                    }

                    ibcCoins.sortWith { o1, o2 ->
                        val value0 = selectedChain.balanceValue(o1.denom)
                        val value1 = selectedChain.balanceValue(o2.denom)
                        when {
                            value0 > value1 -> -1
                            value0 < value1 -> 1
                            else -> 0
                        }
                    }

                    bridgeCoins.sortWith { o1, o2 ->
                        val value0 = selectedChain.balanceValue(o1.denom)
                        val value1 = selectedChain.balanceValue(o2.denom)
                        when {
                            value0 > value1 -> -1
                            value0 < value1 -> 1
                            else -> 0
                        }
                    }
                }
            }
            coinAdapter.submitList(stakeCoins + nativeCoins + ibcCoins + bridgeCoins)
            coinAdapter.notifyDataSetChanged()
        }
        binding.refresher.isRefreshing = false
    }

    private fun startTransfer(line: CosmosLine, denom: String) {
        var isClickable = true
        val bottomSheet = TransferFragment(line, denom)
        if (isClickable) {
            isClickable = false
            bottomSheet.show(requireActivity().supportFragmentManager, TransferFragment::class.java.name)

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 1000)
        }
    }

    private fun startBep3Transfer(line: CosmosLine, denom: String) {
        var isClickable = true
        val bottomSheet = Bep3Fragment(line, denom)
        if (isClickable) {
            isClickable = false
            bottomSheet.show(requireActivity().supportFragmentManager, Bep3Fragment::class.java.name)

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 1000)
        }
    }

    private fun startLegacyTransfer(line: CosmosLine, denom: String) {
        var isClickable = true
        val bottomSheet = LegacyTransferFragment(line, denom)
        if (isClickable) {
            isClickable = false
            bottomSheet.show(requireActivity().supportFragmentManager, LegacyTransferFragment::class.java.name)

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 1000)
        }
    }

    private fun selectBridgeOption(line: CosmosLine, denom: String) {
        var isClickable = true
        val bottomSheet = BridgeOptionFragment(line, denom, bridgeClickAction)
        if (isClickable) {
            isClickable = false
            bottomSheet.show(requireActivity().supportFragmentManager, BridgeOptionFragment::class.java.name)

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 1000)
        }
    }

    private val bridgeClickAction = object : BridgeClickListener {
        override fun bep3Transfer(line: CosmosLine, denom: String) {
            startBep3Transfer(line, denom)
        }

        override fun simpleTransfer(line: CosmosLine, denom: String) {
            if (line is ChainBinanceBeacon) {
                startLegacyTransfer(line, denom)
            } else {
                startTransfer(line, denom)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

interface BridgeClickListener {
    fun bep3Transfer(line: CosmosLine, denom: String)
    fun simpleTransfer(line: CosmosLine, denom: String)
}