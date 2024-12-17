package wannabit.io.cosmostaion.ui.main.chain.major

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin84
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.repository.chain.HistoryRepositoryImpl
import wannabit.io.cosmostaion.databinding.FragmentHistoryBinding
import wannabit.io.cosmostaion.ui.main.chain.cosmos.HistoryAdapter
import wannabit.io.cosmostaion.data.viewmodel.chain.HistoryViewModel
import wannabit.io.cosmostaion.data.viewmodel.chain.HistoryViewModelProviderFactory

class MajorHistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var selectedChain: BaseChain

    private lateinit var historyAdapter: HistoryAdapter

    private val suiHistoryGroup: MutableList<Pair<String, JsonObject>> = mutableListOf()
    private val bitHistoryGroup: MutableList<Pair<String, JsonObject>> = mutableListOf()

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): MajorHistoryFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = MajorHistoryFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        initViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkHistory()
        initRecyclerView()
        refreshData()
    }

    private fun initViewModel() {
        val historyRepository = HistoryRepositoryImpl()
        val historyViewModelProviderFactory = HistoryViewModelProviderFactory(historyRepository)
        historyViewModel =
            ViewModelProvider(this, historyViewModelProviderFactory)[HistoryViewModel::class.java]

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("selectedChain", BaseChain::class.java)
                ?.let { selectedChain = it }
        } else {
            (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                selectedChain = it
            }
        }
        if (selectedChain is ChainBitCoin84) {
            historyViewModel.bitHistory(selectedChain as ChainBitCoin84)
        } else if (selectedChain is ChainSui) {
            historyViewModel.suiHistory(selectedChain as ChainSui)
        } else {

        }
    }

    private fun refreshData() {
        binding.refresher.setOnRefreshListener {
            if (selectedChain is ChainBitCoin84) {
                bitHistoryGroup.clear()
                historyViewModel.bitHistory(selectedChain as ChainBitCoin84)
            } else if (selectedChain is ChainSui) {
                suiHistoryGroup.clear()
                historyViewModel.suiHistory(selectedChain as ChainSui)
            } else {

            }
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            historyAdapter = HistoryAdapter(requireContext(), selectedChain)
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = historyAdapter

            if (::historyAdapter.isInitialized) {
                historyAdapter.setOnItemClickListener { chain, _, hash ->
                    chain.explorerTx(hash)?.let {
                        startActivity(Intent(Intent.ACTION_VIEW, it))
                    } ?: run {
                        return@setOnItemClickListener
                    }
                }
            }
        }
    }

    private fun checkHistory() {
        historyViewModel.suiHistoryResult.observe(viewLifecycleOwner) { response ->
            suiHistoryGroup.clear()
            binding.refresher.isRefreshing = false
            response?.let { historyGroup ->
                suiHistoryGroup.addAll(historyGroup)
                if (historyGroup.isNotEmpty()) {
                    historyAdapter.submitList(suiHistoryGroup as List<Any>?)
                }

                binding.loading.visibility = View.GONE
                binding.refresher.visibleOrGone(historyGroup.isNotEmpty())
                binding.emptyLayout.visibleOrGone(historyGroup.isEmpty())
                historyAdapter.notifyDataSetChanged()
            }
        }

        historyViewModel.btcHistoryResult.observe(viewLifecycleOwner) { response ->
            bitHistoryGroup.clear()
            binding.refresher.isRefreshing = false
            response?.let { historyGroup ->
                bitHistoryGroup.addAll(historyGroup)
                if (historyGroup.isNotEmpty()) {
                    historyAdapter.submitList(bitHistoryGroup as List<Any>?)
                }

                binding.loading.visibility = View.GONE
                binding.refresher.visibleOrGone(historyGroup.isNotEmpty())
                binding.emptyLayout.visibleOrGone(historyGroup.isEmpty())
                historyAdapter.notifyDataSetChanged()
            }
        }

        historyViewModel.errorMessage.observe(viewLifecycleOwner) {
            binding.loading.visibility = View.GONE
            binding.refresher.visibility = View.GONE
            binding.emptyLayout.visibility = View.VISIBLE
            return@observe
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}