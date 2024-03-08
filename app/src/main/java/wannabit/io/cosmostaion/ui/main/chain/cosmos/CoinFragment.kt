package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainGovgen
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.data.model.res.Coin
import wannabit.io.cosmostaion.data.model.res.CoinType
import wannabit.io.cosmostaion.databinding.FragmentCoinBinding
import wannabit.io.cosmostaion.ui.option.tx.kava.BridgeClickListener
import wannabit.io.cosmostaion.ui.option.tx.kava.BridgeOptionFragment
import wannabit.io.cosmostaion.ui.tx.step.CommonTransferFragment
import wannabit.io.cosmostaion.ui.tx.step.LegacyTransferFragment
import wannabit.io.cosmostaion.ui.tx.step.SendAssetType
import wannabit.io.cosmostaion.ui.tx.step.kava.Bep3Fragment
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel

class CoinFragment : Fragment() {

    private var _binding: FragmentCoinBinding? = null
    private val binding get() = _binding!!

    private lateinit var coinAdapter: CoinAdapter

    private lateinit var selectedChain: CosmosLine

    private val stakeCoins = mutableListOf<Coin>()
    private val nativeCoins = mutableListOf<Coin>()
    private val bridgeCoins = mutableListOf<Coin>()
    private val ibcCoins = mutableListOf<Coin>()

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: CosmosLine): CoinFragment {
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
        initRecyclerView()
        initData()
    }

    private fun initRecyclerView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("selectedChain", CosmosLine::class.java)
                ?.let { selectedChain = it }
        } else {
            (arguments?.getParcelable("selectedChain") as? CosmosLine)?.let {
                selectedChain = it
            }
        }

        coinAdapter = CoinAdapter(requireContext(), selectedChain)
        binding.recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = coinAdapter

            coinAdapter.setOnItemClickListener { line, denom, position ->
                val sendAssetType = if (position == 0) {
                    if (line is EthereumLine) {
                        if (line is ChainOktEvm) {
                            SendAssetType.ONLY_EVM_COIN
                        } else if (line.supportCosmos) {
                            SendAssetType.COSMOS_EVM_COIN
                        } else {
                            SendAssetType.ONLY_EVM_COIN
                        }

                    } else {
                        SendAssetType.ONLY_COSMOS_COIN
                    }

                } else {
                    SendAssetType.ONLY_COSMOS_COIN
                }

                if (line is ChainGovgen) {
                    requireActivity().makeToast(R.string.error_tranfer_disabled)
                    return@setOnItemClickListener
                }

                if (line is ChainBinanceBeacon) {
                    if (BaseUtils.isHtlcSwappableCoin(line, denom)) {
                        selectBridgeOption(line, denom, sendAssetType)
                    } else {
                        startLegacyTransfer(line, denom)
                    }

                } else if (line is ChainOkt996Keccak || line is ChainOktEvm && position != 0) {
                    startLegacyTransfer(line, denom)

                } else if (line.tag.startsWith("kava")) {
                    if (BaseUtils.isHtlcSwappableCoin(line, denom)) {
                        selectBridgeOption(line, denom, sendAssetType)
                    } else {
                        startTransfer(line, denom, sendAssetType)
                    }

                } else {
                    startTransfer(line, denom, sendAssetType)
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
                            val totalBalance =
                                balance.free.toBigDecimal().add(balance.frozen.toBigDecimal())
                                    .add(balance.locked.toBigDecimal())
                            nativeCoins.add(
                                Coin(
                                    balance.symbol, totalBalance.toPlainString(), CoinType.ETC
                                )
                            )
                        }
                    }
                    if (stakeCoins.none { it.denom == stakeDenom }) {
                        stakeCoins.add(Coin(stakeDenom, "0", CoinType.STAKE))
                    }
                    nativeCoins.sortBy { it.denom }
                }

                is ChainOkt996Keccak -> {
                    (selectedChain as ChainOkt996Keccak).oktLcdAccountInfo?.value?.coins?.forEach { balance ->
                        if (balance.denom == stakeDenom) {
                            stakeCoins.add(Coin(balance.denom, balance.amount, CoinType.STAKE))
                        } else {
                            nativeCoins.add(Coin(balance.denom, balance.amount, CoinType.ETC))
                        }
                    }
                    if (stakeCoins.none { it.denom == stakeDenom }) {
                        stakeCoins.add(Coin(stakeDenom, "0", CoinType.STAKE))
                    }
                    nativeCoins.sortBy { it.denom }
                }

                is ChainOktEvm -> {
                    (selectedChain as ChainOktEvm).oktLcdAccountInfo?.value?.coins?.forEach { balance ->
                        if (balance.denom == stakeDenom) {
                            stakeCoins.add(Coin(balance.denom, balance.amount, CoinType.STAKE))
                        } else {
                            nativeCoins.add(Coin(balance.denom, balance.amount, CoinType.ETC))
                        }
                    }
                    if (stakeCoins.none { it.denom == stakeDenom }) {
                        stakeCoins.add(Coin(stakeDenom, "0", CoinType.STAKE))
                    }
                    nativeCoins.sortBy { it.denom }
                }

                else -> {
                    selectedChain.cosmosBalances?.forEach { coin ->
                        val coinType = BaseData.getAsset(selectedChain.apiName, coin.denom)?.type
                        coinType?.let {
                            when (it) {
                                "staking" -> stakeCoins.add(
                                    Coin(
                                        coin.denom, coin.amount, CoinType.STAKE
                                    )
                                )

                                "native" -> nativeCoins.add(
                                    Coin(
                                        coin.denom, coin.amount, CoinType.NATIVE
                                    )
                                )

                                "bep", "bridge" -> bridgeCoins.add(
                                    Coin(
                                        coin.denom, coin.amount, CoinType.BRIDGE
                                    )
                                )

                                "ibc" -> ibcCoins.add(Coin(coin.denom, coin.amount, CoinType.IBC))
                            }
                        }
                    }

                    if (stakeCoins.none { it.denom == selectedChain.stakeDenom }) {
                        stakeCoins.add(Coin(stakeDenom, "0", CoinType.STAKE))
                    }

                    nativeCoins.sortWith(compareByDescending { selectedChain.balanceValue(it.denom) })
                    ibcCoins.sortWith(compareByDescending { selectedChain.balanceValue(it.denom) })
                    bridgeCoins.sortWith(compareByDescending { selectedChain.balanceValue(it.denom) })
                }
            }
            coinAdapter.submitList(stakeCoins + nativeCoins + ibcCoins + bridgeCoins)
            coinAdapter.notifyDataSetChanged()
        }
        binding.refresher.isRefreshing = false
    }

    private fun refreshData() {
        binding.refresher.setOnRefreshListener {
            if (!selectedChain.fetched) {
                binding.refresher.isRefreshing = false
            } else {
                BaseData.baseAccount?.let { account ->
                    if (selectedChain is EthereumLine) {
                        ApplicationViewModel.shared.loadEvmChainData(selectedChain as EthereumLine, account.id, false)
                    } else {
                        ApplicationViewModel.shared.loadChainData(selectedChain, account.id, false)
                    }
                }
            }
        }
    }

    private fun observeViewModels() {
        ApplicationViewModel.shared.hideValueResult.observe(viewLifecycleOwner) {
            coinAdapter.notifyDataSetChanged()
        }

        ApplicationViewModel.shared.fetchedRefreshResult.observe(viewLifecycleOwner) {
            if (selectedChain.fetched) {
                initData()
            }
        }

        ApplicationViewModel.shared.fetchedSendResult.observe(viewLifecycleOwner) {
            coinAdapter.notifyDataSetChanged()
        }
    }

    private fun startTransfer(line: CosmosLine, denom: String, sendAssetType: SendAssetType) {
        handleOneClickWithDelay(
            CommonTransferFragment.newInstance(
                line, denom, sendAssetType
            )
        )
    }

    private fun startBep3Transfer(line: CosmosLine, denom: String) {
        handleOneClickWithDelay(Bep3Fragment.newInstance(line, denom))
    }

    private fun startLegacyTransfer(line: CosmosLine, denom: String) {
        handleOneClickWithDelay(LegacyTransferFragment.newInstance(line, denom))
    }

    private fun selectBridgeOption(line: CosmosLine, denom: String, sendAssetType: SendAssetType) {
        handleOneClickWithDelay(
            BridgeOptionFragment.newInstance(line, denom, sendAssetType, bridgeClickAction)
        )
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

    private val bridgeClickAction = object : BridgeClickListener {
        override fun bep3Transfer(line: CosmosLine, denom: String) {
            startBep3Transfer(line, denom)
        }

        override fun simpleTransfer(line: CosmosLine, denom: String, sendAssetType: SendAssetType) {
            if (line is ChainBinanceBeacon) {
                startLegacyTransfer(line, denom)
            } else {
                startTransfer(line, denom, sendAssetType)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}