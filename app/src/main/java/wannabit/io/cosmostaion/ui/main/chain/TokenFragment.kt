package wannabit.io.cosmostaion.ui.main.chain

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tendermint.version.TypesProto.App
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.databinding.FragmentTokenBinding
import wannabit.io.cosmostaion.ui.tx.step.EvmTransferFragment
import wannabit.io.cosmostaion.ui.tx.step.TransferFragment
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel
import java.math.BigDecimal

class TokenFragment(position: Int) : Fragment() {

    private var _binding: FragmentTokenBinding? = null
    private val binding get() = _binding!!

    private lateinit var tokenAdapter: TokenAdapter
    private lateinit var selectedChain: CosmosLine
    private val selectedPosition = position

    private val walletViewModel: WalletViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTokenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpHideValue()
        initRecyclerView()
        setUpInitData()
        refreshData()
        setUpClickAction()
    }

    private fun initRecyclerView() {
        binding.recycler.apply {
            BaseData.baseAccount?.let { account ->
                selectedChain = account.sortedDisplayCosmosLines()[selectedPosition]
                tokenAdapter = TokenAdapter(requireContext(), selectedChain)
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = tokenAdapter

                walletViewModel.loadAllTokenBalance(selectedChain, account.id)
            }
        }
    }

    private fun setUpInitData() {
        binding.apply {
            walletViewModel.fetchedTokenResult.observe(viewLifecycleOwner) {
                if (isAdded) {
                    val tokens = mutableListOf<Token>()
                    selectedChain.tokens.forEach { token ->
                        if (token.amount?.toBigDecimal() != BigDecimal.ZERO) {
                            tokens.add(token)
                        }
                    }

                    tokens.sortWith { o1, o2 ->
                        val value0 = selectedChain.tokenValue(o1.address)
                        val value1 = selectedChain.tokenValue(o2.address)
                        when {
                            value0 > value1 -> -1
                            value0 < value1 -> 1
                            else -> 0
                        }
                    }

                    recycler.visibleOrGone(selectedChain.tokens.isNotEmpty())
                    emptyLayout.visibleOrGone(selectedChain.tokens.isEmpty())
                    tokenAdapter.submitList(tokens)
                    tokenAdapter.notifyDataSetChanged()

                    ApplicationViewModel.shared.fetchedToken()
                }
            }
            refresher.isRefreshing = false
        }
    }

    private fun refreshData() {
        binding.refresher.setOnRefreshListener {
            setUpInitData()
        }
    }

    private fun setUpClickAction() {
        var isClickable = true
        tokenAdapter.setOnItemClickListener { line, denom ->
            if (isClickable) {
                isClickable = false

                Handler(Looper.getMainLooper()).postDelayed({
                    isClickable = true
                }, 1000)

                if (selectedChain.supportCw20) {
                    TransferFragment(line, denom).show(
                        requireActivity().supportFragmentManager, TransferFragment::class.java.name
                    )
                } else {
                    EvmTransferFragment(line, denom).show(
                        requireActivity().supportFragmentManager, EvmTransferFragment::class.java.name
                    )
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