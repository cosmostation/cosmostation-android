package wannabit.io.cosmostaion.ui.main.chain.cosmos

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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.databinding.FragmentTokenBinding
import wannabit.io.cosmostaion.ui.tx.step.CommonTransferFragment
import wannabit.io.cosmostaion.ui.tx.step.SendAssetType
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import java.math.BigDecimal

class TokenFragment : Fragment() {

    private var _binding: FragmentTokenBinding? = null
    private val binding get() = _binding!!

    private lateinit var tokenAdapter: TokenAdapter
    private lateinit var selectedChain: BaseChain

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): TokenFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = TokenFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTokenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpHideValue()
        setUpRefreshData()
        initData()
        setUpInitData()
        refreshData()
    }

    private fun initData() {
        binding.apply {
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
    }

    private fun initRecyclerView(tokens: MutableList<Token>) {
        binding.recycler.apply {
            tokenAdapter = TokenAdapter(selectedChain)
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = tokenAdapter
            tokenAdapter.submitList(tokens)

            var isClickable = true
            if (::tokenAdapter.isInitialized) {
                tokenAdapter.setOnItemClickListener { line, denom ->
                    if (isClickable) {
                        isClickable = false

                        val sendAssetType = if (selectedChain.supportCw20) {
                            SendAssetType.ONLY_COSMOS_CW20
                        } else {
                            SendAssetType.ONLY_EVM_ERC20
                        }

                        if (selectedChain.isBankLocked()) {
                            requireActivity().makeToast(R.string.error_tranfer_disabled)
                            return@setOnItemClickListener
                        }

                        CommonTransferFragment.newInstance(line, denom, sendAssetType).show(
                            requireActivity().supportFragmentManager,
                            CommonTransferFragment::class.java.name
                        )

                        Handler(Looper.getMainLooper()).postDelayed({
                            isClickable = true
                        }, 300)
                    }
                }
            }
        }
    }

    private fun setUpInitData() {
        binding.apply {
            val tokens = mutableListOf<Token>()
            if (isAdded) {
                lifecycleScope.launch(Dispatchers.IO) {
                    if (selectedChain.supportEvm) {
                        selectedChain.evmRpcFetcher?.let { evmRpc ->
                            evmRpc.evmTokens.forEach { token ->
                                if (token.amount?.toBigDecimal() != BigDecimal.ZERO) {
                                    tokens.add(token)
                                }
                            }

                            tokens.sortWith { o1, o2 ->
                                val value0 = evmRpc.tokenValue(o1.address)
                                val value1 = evmRpc.tokenValue(o2.address)
                                when {
                                    value0 > value1 -> -1
                                    value0 < value1 -> 1
                                    else -> 0
                                }
                            }
                        }

                    } else {
                        selectedChain.grpcFetcher?.let { grpc ->
                            grpc.tokens.forEach { token ->
                                if (token.amount?.toBigDecimal() != BigDecimal.ZERO) {
                                    tokens.add(token)
                                }
                            }

                            tokens.sortWith { o1, o2 ->
                                val value0 = grpc.tokenValue(o1.address)
                                val value1 = grpc.tokenValue(o2.address)
                                when {
                                    value0 > value1 -> -1
                                    value0 < value1 -> 1
                                    else -> 0
                                }
                            }
                        }
                    }

                    withContext(Dispatchers.Main) {
                        refresher.isRefreshing = false
                        loading.visibility = View.GONE
                        refresher.visibleOrGone(tokens.isNotEmpty())
                        emptyLayout.visibleOrGone(tokens.isEmpty())
                        initRecyclerView(tokens)
                    }
                }
            }
        }
    }

    private fun refreshData() {
        binding.refresher.setOnRefreshListener {
            BaseData.baseAccount?.let { account ->
                ApplicationViewModel.shared.loadChainData(selectedChain, account.id, false)
            }
        }
    }

    private fun setUpHideValue() {
        ApplicationViewModel.shared.hideValueResult.observe(viewLifecycleOwner) {
            if (::tokenAdapter.isInitialized) {
                tokenAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun setUpRefreshData() {
        ApplicationViewModel.shared.fetchedTokenResult.observe(viewLifecycleOwner) { tag ->
            if (selectedChain.tag == tag) {
                setUpInitData()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}