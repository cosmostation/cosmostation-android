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
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.data.model.res.Coin
import wannabit.io.cosmostaion.data.model.res.CoinType
import wannabit.io.cosmostaion.databinding.FragmentCoinBinding
import wannabit.io.cosmostaion.ui.tx.step.CommonTransferFragment
import wannabit.io.cosmostaion.ui.tx.step.LegacyTransferFragment
import wannabit.io.cosmostaion.ui.tx.step.SendAssetType
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel

class CoinFragment : Fragment() {

    private var _binding: FragmentCoinBinding? = null
    private val binding get() = _binding!!

    private lateinit var coinAdapter: CoinAdapter

    private lateinit var selectedChain: BaseChain

    private val stakeCoins = mutableListOf<Coin>()
    private val nativeCoins = mutableListOf<Coin>()
    private val bridgeCoins = mutableListOf<Coin>()
    private val ibcCoins = mutableListOf<Coin>()

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
        initRecyclerView()
        initData()
        sunsetPopup()
    }

    private fun initRecyclerView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("selectedChain", BaseChain::class.java)
                ?.let { selectedChain = it }
        } else {
            (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                selectedChain = it
            }
        }

        coinAdapter = CoinAdapter(requireContext(), selectedChain)
        binding.recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = coinAdapter

            coinAdapter.setOnItemClickListener { chain, denom, position ->
                val sendAssetType = if (position == 0) {
                    if (chain.supportEvm && chain.isCosmos()) {
                        SendAssetType.COSMOS_EVM_COIN
                    } else {
                        SendAssetType.ONLY_COSMOS_COIN
                    }
                } else {
                    SendAssetType.ONLY_COSMOS_COIN
                }


//                val sendAssetType = if (position == 0) {
//                    if (line is EthereumLine) {
//                        if (line is ChainBeraEvm) {
//                            requireActivity().makeToast(R.string.error_tranfer_disabled_bgt)
//                            return@setOnItemClickListener
//                        } else if (line is ChainOktEvm) {
//                            SendAssetType.ONLY_EVM_COIN
//                        } else if (line.supportCosmos) {
//                            SendAssetType.COSMOS_EVM_COIN
//                        } else {
//                            SendAssetType.ONLY_EVM_COIN
//                        }
//
//                    } else {
//                        SendAssetType.ONLY_COSMOS_COIN
//                    }
//
//                } else {
//                    if (line is ChainBeraEvm) {
//                        SendAssetType.ONLY_EVM_COIN
//                    } else {
//                        SendAssetType.ONLY_COSMOS_COIN
//                    }
//                }
//
                if (selectedChain.isBankLocked()) {
                    requireActivity().makeToast(R.string.error_tranfer_disabled)
                    return@setOnItemClickListener
                }
//
//                if (line is ChainOkt996Keccak || line is ChainOktEvm && position != 0) {
//                    startLegacyTransfer(line, denom)
//
//                } else if (line.tag.startsWith("kava")) {
//                    startTransfer(line, denom, sendAssetType)
//
//                } else {
                startTransfer(chain, denom, sendAssetType)
//                }
            }
        }
    }

    private fun initData() {
        stakeCoins.clear()
        nativeCoins.clear()
        ibcCoins.clear()
        bridgeCoins.clear()

        when (selectedChain) {
//                is ChainOkt996Keccak -> {
//                    (selectedChain as ChainOkt996Keccak).oktLcdAccountInfo?.value?.coins?.forEach { balance ->
//                        if (balance.denom == stakeDenom) {
//                            stakeCoins.add(Coin(balance.denom, balance.amount, CoinType.STAKE))
//                        } else {
//                            nativeCoins.add(Coin(balance.denom, balance.amount, CoinType.ETC))
//                        }
//                    }
//                    if (stakeCoins.none { it.denom == stakeDenom }) {
//                        stakeCoins.add(Coin(stakeDenom, "0", CoinType.STAKE))
//                    }
//                    nativeCoins.sortBy { it.denom }
//                }
//
//                is ChainOktEvm -> {
//                    (selectedChain as ChainOktEvm).oktLcdAccountInfo?.value?.coins?.forEach { balance ->
//                        if (balance.denom == stakeDenom) {
//                            stakeCoins.add(Coin(balance.denom, balance.amount, CoinType.STAKE))
//                        } else {
//                            nativeCoins.add(Coin(balance.denom, balance.amount, CoinType.ETC))
//                        }
//                    }
//                    if (stakeCoins.none { it.denom == stakeDenom }) {
//                        stakeCoins.add(Coin(stakeDenom, "0", CoinType.STAKE))
//                    }
//                    nativeCoins.sortBy { it.denom }
//                }

            else -> {
                selectedChain.grpcFetcher?.cosmosBalances?.forEach { coin ->
                    val coinType = BaseData.getAsset(selectedChain.apiName, coin.denom)?.type
                    coinType?.let {
                        when (it) {
                            "staking" -> stakeCoins.add(
                                Coin(
                                    coin.denom, coin.amount, CoinType.STAKE
                                )
                            )

                            "native" -> {
                                nativeCoins.add(
                                    Coin(
                                        coin.denom, coin.amount, CoinType.NATIVE
                                    )
                                )
                            }

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
                    stakeCoins.add(Coin(selectedChain.stakeDenom, "0", CoinType.STAKE))
                }
//                    if (selectedChain is ChainBeraEvm) {
//                        nativeCoins.add(
//                            Coin(
//                                "abera",
//                                (selectedChain as ChainBeraEvm).evmBalance.toString(),
//                                CoinType.NATIVE
//                            )
//                        )
//                    }

                nativeCoins.sortWith(compareByDescending {
                    selectedChain.grpcFetcher?.balanceValue(
                        it.denom
                    )
                })
                ibcCoins.sortWith(compareByDescending {
                    selectedChain.grpcFetcher?.balanceValue(
                        it.denom
                    )
                })
                bridgeCoins.sortWith(compareByDescending {
                    selectedChain.grpcFetcher?.balanceValue(
                        it.denom
                    )
                })
            }
        }
        coinAdapter.submitList(stakeCoins + nativeCoins + ibcCoins + bridgeCoins)
        coinAdapter.notifyDataSetChanged()
        binding.refresher.isRefreshing = false
    }

    private fun refreshData() {
        binding.refresher.setOnRefreshListener {
            if (!selectedChain.fetched) {
                binding.refresher.isRefreshing = false
            } else {
                BaseData.baseAccount?.let { account ->
                    ApplicationViewModel.shared.loadChainData(selectedChain, account.id, false)
                }
            }
        }
    }

    private fun sunsetPopup() {
//        val noticeType = when (selectedChain) {
//            is ChainCantoEvm, is ChainRegen, is ChainLikeCoin, is ChainIxo -> {
//                NoticeType.CHAIN_DELIST
//            }
//
//            is ChainOkt996Keccak -> {
//                NoticeType.LEGACY_PATH
//            }
//
//            else -> {
//                return
//            }
//        }
//        Handler(Looper.getMainLooper()).postDelayed({
//            if (isAdded && isVisible && isResumed) {
//                NoticeInfoFragment.newInstance(selectedChain, noticeType).show(
//                    requireActivity().supportFragmentManager,
//                    NoticeInfoFragment::class.java.name
//                )
//            }
//        }, 800)
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

    private fun startLegacyTransfer(line: CosmosLine, denom: String) {
        handleOneClickWithDelay(LegacyTransferFragment.newInstance(line, denom))
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

    override fun onDestroyView() {
        _binding = null
        ApplicationViewModel.shared.fetchedResult.removeObservers(viewLifecycleOwner)
        super.onDestroyView()
    }
}