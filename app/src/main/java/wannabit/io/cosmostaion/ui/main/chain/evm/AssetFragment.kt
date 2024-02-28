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
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.databinding.FragmentAssetBinding
import wannabit.io.cosmostaion.ui.tx.step.CommonTransferFragment
import wannabit.io.cosmostaion.ui.tx.step.SendAssetType
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import java.math.BigDecimal

class AssetFragment : Fragment() {

    private var _binding: FragmentAssetBinding? = null
    private val binding get() = _binding!!

    private lateinit var assetAdapter: AssetAdapter

    private lateinit var selectedEvmChain: EthereumLine

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedEvmChain: EthereumLine): AssetFragment {
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
        refreshData()
        observeViewModels()
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

    private fun initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("selectedEvmChain", EthereumLine::class.java)
                ?.let { selectedEvmChain = it }
        } else {
            (arguments?.getParcelable("selectedEvmChain") as? EthereumLine)?.let {
                selectedEvmChain = it
            }
        }

        val evmTokens = mutableListOf<Token>()
        evmTokens.clear()

        lifecycleScope.launch(Dispatchers.IO) {
            selectedEvmChain.evmTokens.forEach { token ->
                if (token.amount?.toBigDecimal()!! > BigDecimal.ZERO) {
                    evmTokens.add(token)
                }
            }
            evmTokens.sortWith { o1, o2 ->
                val value0 = selectedEvmChain.tokenValue(o1.address)
                val value1 = selectedEvmChain.tokenValue(o2.address)
                when {
                    value0 > value1 -> -1
                    value0 < value0 -> 1
                    else -> 0
                }
            }
            withContext(Dispatchers.Main) {
                initRecyclerView(evmTokens)
            }
        }
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

        ApplicationViewModel.shared.fetchedRefreshResult.observe(viewLifecycleOwner) {
            if (selectedEvmChain.fetched) {
                initData()
            }
        }

        ApplicationViewModel.shared.fetchedSendResult.observe(viewLifecycleOwner) {
            if (::assetAdapter.isInitialized) {
                assetAdapter.notifyDataSetChanged()
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}