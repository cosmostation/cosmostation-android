package wannabit.io.cosmostaion.ui.main.chain

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cosmos.base.v1beta1.CoinProto.Coin
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.databinding.FragmentCoinBinding

class CoinFragment(position: Int) : Fragment() {

    private var _binding: FragmentCoinBinding? = null
    private val binding get() = _binding!!

    private lateinit var coinAdapter: CoinAdapter
    private val selectedPosition = position

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

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val baseAccount = BaseData.baseAccount
        baseAccount?.let { account ->
            val selectedChain = account.allCosmosLineChains[selectedPosition]
            selectedChain.cosmosBalances.forEach { coin ->
                val coinType = BaseData.getAsset(selectedChain.apiName, coin.denom)?.type
                if (coinType != null) {
                    when (coinType) {
                        "staking", "native" -> {
                            nativeCoins.add(coin)
                        }
                        "bep", "bridge" -> {
                            bridgeCoins.add(coin)
                        }
                        "ibc" -> {
                            ibcCoins.add(coin)
                        }
                    }
                }
            }

            if (nativeCoins.firstOrNull { it.denom == selectedChain.stakeDenom } == null) {
                nativeCoins.add(Coin.newBuilder().setDenom(selectedChain.stakeDenom).setAmount("0").build())
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

            coinAdapter = CoinAdapter(requireContext(), selectedChain, nativeCoins, ibcCoins)
            binding.recycler.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = coinAdapter
                coinAdapter.submitList(nativeCoins + ibcCoins)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}