package wannabit.io.cosmostaion.ui.main.chain.evm

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.dpTimeToYear
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.repository.chain.HistoryRepositoryImpl
import wannabit.io.cosmostaion.data.viewmodel.chain.HistoryViewModel
import wannabit.io.cosmostaion.data.viewmodel.chain.HistoryViewModelProviderFactory
import wannabit.io.cosmostaion.databinding.FragmentHistoryBinding
import wannabit.io.cosmostaion.ui.main.chain.cosmos.HistoryAdapter

class EvmHistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var selectedEvmChain: BaseChain

    private lateinit var historyAdapter: HistoryAdapter

    private val ethHistoryGroup: MutableList<Pair<String, JsonObject>> = mutableListOf()

    private var searchAfter: String = ""
    private var hasMore = false
    private val BATCH_CNT = 20

    companion object {
        @JvmStatic
        fun newInstance(selectedEvmChain: BaseChain): EvmHistoryFragment {
            val args = Bundle().apply {
                putParcelable("selectedEvmChain", selectedEvmChain)
            }
            val fragment = EvmHistoryFragment()
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
        scrollView()
        refreshData()
    }

    private fun initViewModel() {
        val historyRepository = HistoryRepositoryImpl()
        val historyViewModelProviderFactory = HistoryViewModelProviderFactory(historyRepository)
        historyViewModel =
            ViewModelProvider(this, historyViewModelProviderFactory)[HistoryViewModel::class.java]

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("selectedEvmChain", BaseChain::class.java)
                ?.let { selectedEvmChain = it }
        } else {
            (arguments?.getParcelable("selectedEvmChain") as? BaseChain)?.let {
                selectedEvmChain = it
            }
        }
        historyViewModel.ethHistory(selectedEvmChain, BATCH_CNT.toString(), searchAfter)
    }

    private fun refreshData() {
        binding.refresher.setOnRefreshListener {
            ethHistoryGroup.clear()
            searchAfter = ""
            historyViewModel.ethHistory(selectedEvmChain, BATCH_CNT.toString(), searchAfter)
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            historyAdapter = HistoryAdapter(requireContext(), selectedEvmChain)
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
        historyViewModel.ethHistoryResult.observe(viewLifecycleOwner) { response ->
            binding.refresher.isRefreshing = false
            binding.recycler.suppressLayout(false)
            val historyGroup: MutableList<Pair<String, JsonObject>> = mutableListOf()
            response.asJsonObject["txs"].asJsonArray.forEach { history ->
                val headerDate = dpTimeToYear(history.asJsonObject["txTime"].asString.toLong())
                historyGroup.add(Pair(headerDate, history.asJsonObject))
            }
            ethHistoryGroup.addAll(historyGroup)
            historyAdapter.submitList(ethHistoryGroup as List<Any>?)
            if (historyGroup.size < 20) {
                hasMore = false
            } else {
                searchAfter = response["search_after"].asString
                if (searchAfter.isNotEmpty()) {
                    hasMore = true
                }
            }

            binding.loading.visibility = View.GONE
            binding.refresher.visibleOrGone(historyGroup.isNotEmpty())
            binding.emptyLayout.visibleOrGone(historyGroup.isEmpty())
            historyAdapter.notifyDataSetChanged()
        }

        historyViewModel.errorMessage.observe(viewLifecycleOwner) {
            binding.loading.visibility = View.GONE
            binding.refresher.visibility = View.GONE
            binding.emptyLayout.visibility = View.VISIBLE
            return@observe
        }
    }

    private fun scrollView() {
        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (binding.refresher.isRefreshing) {
                    return
                }
                if (historyAdapter.itemCount == 0) {
                    return
                }
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = historyAdapter.itemCount - 1

                if (lastVisibleItemPosition == itemTotalCount) {
                    if (hasMore) {
                        hasMore = false
                        val next = searchAfter.toLong() - 1
                        historyViewModel.ethHistory(
                            selectedEvmChain, BATCH_CNT.toString(), next.toString()
                        )
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}