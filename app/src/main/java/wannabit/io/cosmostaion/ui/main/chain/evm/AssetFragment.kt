package wannabit.io.cosmostaion.ui.main.chain.evm

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.FragmentAssetBinding
import wannabit.io.cosmostaion.ui.main.edit.TokenEditFragment
import wannabit.io.cosmostaion.ui.main.edit.TokenEditListener
import wannabit.io.cosmostaion.ui.tx.step.CommonTransferFragment
import wannabit.io.cosmostaion.ui.tx.step.SendAssetType
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import java.math.BigDecimal

interface AssetFragmentInteraction {
    fun showTokenList()
}

class AssetFragment : Fragment(), AssetFragmentInteraction {

    private var _binding: FragmentAssetBinding? = null
    private val binding get() = _binding!!

    private lateinit var assetAdapter: AssetAdapter

    private lateinit var selectedEvmChain: BaseChain
    private var allErc20Tokens = mutableListOf<Token>()
    private var displayErc20Tokens = mutableListOf<Token>()

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedEvmChain: BaseChain): AssetFragment {
            val args = Bundle().apply {
                putParcelable("selectedEvmChain", selectedEvmChain)
            }
            val fragment = AssetFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAssetBinding.inflate(layoutInflater, container, false)
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
            arguments?.getParcelable("selectedEvmChain", BaseChain::class.java)
                ?.let { selectedEvmChain = it }
        } else {
            (arguments?.getParcelable("selectedEvmChain") as? BaseChain)?.let {
                selectedEvmChain = it
            }
        }
    }

    private fun sortAssets() {
        val evmTokens = mutableListOf<Token>()
        evmTokens.clear()
        allErc20Tokens.clear()
        displayErc20Tokens.clear()

        lifecycleScope.launch(Dispatchers.IO) {
            selectedEvmChain.evmRpcFetcher?.evmTokens?.let { evmTokens.addAll(it) }
            evmTokens.sortBy { it.symbol.lowercase() }

            BaseData.baseAccount?.let { account ->
                Prefs.getDisplayErc20s(account.id, selectedEvmChain.tag)?.let { userCustomTokens ->
                    evmTokens.sortWith { token0, token1 ->
                        val address0 = token0.address
                        val address1 = token1.address

                        val containsToken0 = userCustomTokens.contains(address0)
                        val containsToken1 = userCustomTokens.contains(address1)

                        when {
                            containsToken0 && !containsToken1 -> -1
                            !containsToken0 && containsToken1 -> 1
                            else -> {
                                val value0 = selectedEvmChain.evmRpcFetcher?.tokenValue(address0)
                                    ?: BigDecimal.ZERO
                                val value1 = selectedEvmChain.evmRpcFetcher?.tokenValue(address1)
                                    ?: BigDecimal.ZERO
                                value1.compareTo(value0)
                            }
                        }
                    }
                    evmTokens.forEach { token ->
                        if (userCustomTokens.contains(token.address) && !displayErc20Tokens.contains(
                                token
                            )
                        ) {
                            displayErc20Tokens.add(token)
                        }
                    }

                } ?: run {
                    evmTokens.sortWith { o1, o2 ->
                        val value0 = selectedEvmChain.evmRpcFetcher?.tokenValue(o1.address)
                            ?: BigDecimal.ZERO
                        val value1 = selectedEvmChain.evmRpcFetcher?.tokenValue(o2.address)
                            ?: BigDecimal.ZERO
                        when {
                            value0 > value1 -> -1
                            value0 < value1 -> 1
                            else -> 0
                        }
                    }

                    evmTokens.forEach { token ->
                        if (token.amount?.toBigDecimal()!! > BigDecimal.ZERO && !displayErc20Tokens.contains(
                                token
                            )
                        ) {
                            displayErc20Tokens.add(token)
                        }
                    }
                }
            }

            withContext(Dispatchers.Main) {
                allErc20Tokens.addAll(evmTokens)
                initRecyclerView(displayErc20Tokens)
            }
        }
    }

    private fun initRecyclerView(evmTokens: MutableList<Token>) {
        assetAdapter = AssetAdapter(selectedEvmChain, evmTokens)
        binding.recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = assetAdapter
            assetAdapter.notifyDataSetChanged()

            assetAdapter.setOnItemClickListener { evmChain, denom ->
                val sendAssetType = if (denom.isNotEmpty()) {
                    SendAssetType.ONLY_EVM_ERC20
                } else {
                    SendAssetType.ONLY_EVM_COIN
                }
                handleOneClickWithDelay(
                    CommonTransferFragment.newInstance(
                        evmChain, denom, sendAssetType
                    )
                )
            }
        }
        binding.refresher.isRefreshing = false
    }

    private fun refreshData() {
        binding.refresher.setOnRefreshListener {
            if (!selectedEvmChain.fetched) {
                binding.refresher.isRefreshing = false
            } else {
                BaseData.baseAccount?.let { account ->
                    ApplicationViewModel.shared.loadEvmChainData(
                        selectedEvmChain, account.id, false
                    )
                }
            }
        }
    }

    private fun observeViewModels() {
        ApplicationViewModel.shared.hideValueResult.observe(viewLifecycleOwner) {
            if (::assetAdapter.isInitialized) {
                assetAdapter.notifyDataSetChanged()
            }
        }

        ApplicationViewModel.shared.fetchedResult.observe(viewLifecycleOwner) {
            if (selectedEvmChain.fetched) {
                sortAssets()
            }
        }

        ApplicationViewModel.shared.fetchedTokenResult.observe(viewLifecycleOwner) {
            if (selectedEvmChain.tag == it) {
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

    override fun showTokenList() {
        TokenEditFragment.newInstance(selectedEvmChain,
            allErc20Tokens,
            displayErc20Tokens.map { it.address }.toMutableList(),
            object : TokenEditListener {
                override fun edit(displayErc20Tokens: MutableList<String>) {
                    BaseData.baseAccount?.let { account ->
                        ApplicationViewModel.shared.loadEvmChainData(
                            selectedEvmChain, account.id, false
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
        ApplicationViewModel.shared.fetchedTokenResult.removeObservers(viewLifecycleOwner)
        super.onDestroyView()
    }
}