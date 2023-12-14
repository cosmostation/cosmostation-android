package wannabit.io.cosmostaion.ui.main.chain

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.FragmentTokenBinding
import wannabit.io.cosmostaion.ui.tx.step.EvmTransferFragment
import wannabit.io.cosmostaion.ui.tx.step.TransferFragment
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import java.math.BigDecimal

class TokenFragment(position: Int) : Fragment() {

    private var _binding: FragmentTokenBinding? = null
    private val binding get() = _binding!!

    private lateinit var tokenAdapter: TokenAdapter
    private lateinit var selectedChain: CosmosLine
    private val selectedPosition = position

    private var baseAccount: BaseAccount? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTokenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpHideValue()
        refreshData()
        initRecyclerView()
        initData()
        clickAction()
    }

    private fun refreshData() {
        binding.refresher.setOnRefreshListener {
            initData()
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            baseAccount = BaseData.baseAccount
            baseAccount?.let { baseAccount ->
                selectedChain = baseAccount.displayCosmosLineChains[selectedPosition]
                tokenAdapter = TokenAdapter(requireContext(), selectedChain)
                recycler.setHasFixedSize(true)
                recycler.layoutManager = LinearLayoutManager(requireContext())
                recycler.adapter = tokenAdapter
            }
        }
    }

    private fun initData() {
        CoroutineScope(Dispatchers.IO).launch {
            baseAccount?.let { baseAccount ->
                if (selectedChain.supportCw20) {
                    selectedChain.loadAllCw20Balance(baseAccount.id)
                } else if (selectedChain.supportErc20) {
                    selectedChain.loadAllErc20Balance(baseAccount.id)
                }

                withContext(Dispatchers.Main) {
                    if (isAdded) {
                        val cw20tokens = mutableListOf<Token>()
                        selectedChain.cw20tokens.forEach { token ->
                            if (token.amount?.toBigDecimal() != BigDecimal.ZERO) {
                                cw20tokens.add(token)
                            }
                        }

                        cw20tokens.sortWith { o1, o2 ->
                            val value0 = selectedChain.tokenValue(o1.address)
                            val value1 = selectedChain.tokenValue(o2.address)
                            when {
                                value0 > value1 -> -1
                                value0 < value1 -> 1
                                else -> 0
                            }
                        }

                        val erc20tokens = mutableListOf<Token>()
                        selectedChain.erc20tokens.forEach { token ->
                            if (token.amount?.toBigDecimal() != BigDecimal.ZERO) {
                                erc20tokens.add(token)
                            }
                        }

                        erc20tokens.sortWith { o1, o2 ->
                            val value0 = selectedChain.tokenValue(o1.address)
                            val value1 = selectedChain.tokenValue(o2.address)
                            when {
                                value0 > value1 -> -1
                                value0 < value1 -> 1
                                else -> 0
                            }
                        }

                        if (selectedChain.supportCw20) {
                            binding.recycler.visibleOrGone(selectedChain.cw20tokens.isNotEmpty())
                            binding.emptyLayout.visibleOrGone(selectedChain.cw20tokens.isEmpty())
                            tokenAdapter.submitList(cw20tokens)

                        } else if (selectedChain.supportErc20) {
                            binding.recycler.visibleOrGone(selectedChain.erc20tokens.isNotEmpty())
                            binding.emptyLayout.visibleOrGone(selectedChain.erc20tokens.isEmpty())
                            tokenAdapter.submitList(erc20tokens)
                        }
                        tokenAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
        binding.refresher.isRefreshing = false
    }

    private fun clickAction() {
        var isClickable = true
        tokenAdapter.setOnItemClickListener { line, denom ->
            if (!selectedChain.isTxFeePayable(requireContext())) {
                requireContext().makeToast(R.string.error_not_enough_fee)
                return@setOnItemClickListener
            }

            if (selectedChain.supportCw20) {
                val bottomSheet = TransferFragment(line, denom)
                if (isClickable) {
                    isClickable = false
                    bottomSheet.show(requireActivity().supportFragmentManager, TransferFragment::class.java.name)

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }

            } else if (selectedChain.supportErc20) {
                val bottomSheet = EvmTransferFragment(line, denom)
                if (isClickable) {
                    isClickable = false
                    bottomSheet.show(requireActivity().supportFragmentManager, EvmTransferFragment::class.java.name)

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }
        }
    }

    private fun setUpHideValue() {
        ApplicationViewModel.shared.hideValueResult.observe(viewLifecycleOwner) {
            tokenAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}