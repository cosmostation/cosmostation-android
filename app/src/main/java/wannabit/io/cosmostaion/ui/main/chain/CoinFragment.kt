package wannabit.io.cosmostaion.ui.main.chain

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.data.model.res.Coin
import wannabit.io.cosmostaion.data.model.res.CoinType
import wannabit.io.cosmostaion.databinding.FragmentCoinBinding
import wannabit.io.cosmostaion.ui.tx.TransferFragment

class CoinFragment(position: Int) : Fragment() {

    private var _binding: FragmentCoinBinding? = null
    private val binding get() = _binding!!

    private lateinit var coinAdapter: CoinAdapter
    private lateinit var selectedChain: CosmosLine
    private val selectedPosition = position

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

        initSortAssets()
        initRecyclerView()
    }

    private fun initSortAssets() {
        val baseAccount = BaseData.baseAccount
        baseAccount?.let { account ->
            selectedChain = account.displayCosmosLineChains[selectedPosition]

            selectedChain.stakeDenom?.let { stakeDenom ->
                if (selectedChain is ChainBinanceBeacon) {
                    selectedChain.lcdAccountInfo?.balances?.forEach { balance ->
                        if (balance.symbol == stakeDenom) {
                            stakeCoins.add(Coin(balance.symbol, balance.free, CoinType.STAKE))
                        } else {
                            val totalBalance = balance.free.toBigDecimal().add(balance.frozen.toBigDecimal()).add(balance.locked.toBigDecimal())
                            nativeCoins.add(Coin(balance.symbol, totalBalance.toPlainString(), CoinType.NATIVE))
                        }
                    }
                    if (stakeCoins.firstOrNull { it.denom == stakeDenom } == null) {
                        stakeCoins.add(Coin(stakeDenom, "0", CoinType.STAKE))
                    }
                    nativeCoins.sortWith(compareBy { it.denom })

                } else {
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
        }
    }

    private fun initRecyclerView() {
        coinAdapter = CoinAdapter(requireContext(), selectedChain)
        binding.recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = coinAdapter
            coinAdapter.submitList(stakeCoins + nativeCoins + ibcCoins + bridgeCoins)

            var isClickable = true
            coinAdapter.setOnItemClickListener { line, denom ->
                val bottomSheet = TransferFragment(line, denom)
                if (isClickable) {
                    isClickable = false
                    bottomSheet.show(requireActivity().supportFragmentManager, TransferFragment(line, denom)::class.java.name)

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}