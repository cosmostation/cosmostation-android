package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.common.dpTimeToYear
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.model.res.CosmosHistory
import wannabit.io.cosmostaion.data.repository.chain.HistoryRepositoryImpl
import wannabit.io.cosmostaion.databinding.FragmentHistoryBinding
import wannabit.io.cosmostaion.ui.tx.info.SendResultFragment
import wannabit.io.cosmostaion.data.viewmodel.chain.HistoryViewModel
import wannabit.io.cosmostaion.data.viewmodel.chain.HistoryViewModelProviderFactory

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var selectedChain: BaseChain

    private lateinit var historyAdapter: HistoryAdapter

    private var searchAfter: String = ""
    private var hasMore = false
    private val BATCH_CNT = 30

    private val allHistoryGroup: MutableList<Pair<String, CosmosHistory>> = mutableListOf()
    private val allEthHistoryGroup: MutableList<Pair<String, JsonObject>> = mutableListOf()

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): HistoryFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = HistoryFragment()
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
            arguments?.getParcelable("selectedChain", BaseChain::class.java)
                ?.let { selectedChain = it }
        } else {
            (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                selectedChain = it
            }
        }
    }

    private fun refreshData() {
        binding.refresher.setOnRefreshListener {
            allHistoryGroup.clear()
            allEthHistoryGroup.clear()
            searchAfter = ""
            initData()
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            historyAdapter = HistoryAdapter(requireContext(), selectedChain)
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = historyAdapter

            var isClickable = true
            if (::historyAdapter.isInitialized) {
                historyAdapter.setOnItemClickListener { chain, history, hash ->
                    when (chain) {
                        is ChainOkt996Keccak, is ChainOktEvm -> {
                            chain.explorerTx(hash)?.let {
                                startActivity(Intent(Intent.ACTION_VIEW, it))
                            } ?: run {
                                return@setOnItemClickListener
                            }
                        }

                        else -> {
                            history?.let {
                                if (it.getMsgCnt() == 1 && it.getMsgType(
                                        requireContext(), chain.address
                                    ).equals(getString(R.string.tx_send), true)
                                ) {
                                    if (isClickable) {
                                        isClickable = false

                                        SendResultFragment(chain, history).show(
                                            requireActivity().supportFragmentManager,
                                            SendResultFragment::class.java.name
                                        )

                                        Handler(Looper.getMainLooper()).postDelayed({
                                            isClickable = true
                                        }, 300)
                                    }

                                } else {
                                    chain.explorerTx(hash)?.let {
                                        startActivity(Intent(Intent.ACTION_VIEW, it))
                                    } ?: run {
                                        return@setOnItemClickListener
                                    }
                                }
                            }
                        }
                    }
                }
            }

            initData()
        }
    }

    private fun initData() {
        when (selectedChain) {
            is ChainOkt996Keccak, is ChainOktEvm -> {
                historyViewModel.ethHistory(selectedChain, BATCH_CNT.toString(), searchAfter)
            }

            else -> {
                historyViewModel.history(
                    requireContext(),
                    selectedChain.apiName,
                    selectedChain.address,
                    BATCH_CNT.toString(),
                    searchAfter
                )
            }
        }
        if (binding.refresher.isRefreshing) {
            binding.recycler.suppressLayout(true)
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
                        when (selectedChain) {
                            is ChainOkt996Keccak, is ChainOktEvm -> {
                                val next = searchAfter.toLong() - 1
                                historyViewModel.ethHistory(selectedChain, "20", next.toString())
                            }

                            else -> {
                                historyViewModel.history(
                                    requireContext(),
                                    selectedChain.apiName,
                                    selectedChain.address,
                                    BATCH_CNT.toString(),
                                    searchAfter
                                )
                            }
                        }
                    }
                }
            }
        })
    }

    private fun checkHistory() {
        historyViewModel.historyResult.observe(viewLifecycleOwner) { response ->
            binding.refresher.isRefreshing = false
            binding.recycler.suppressLayout(false)
            response?.let { historyGroup ->
                allHistoryGroup.addAll(historyGroup)
                if (historyGroup.isNotEmpty()) {
                    historyAdapter.submitList(allHistoryGroup as List<Any>?)
                    searchAfter =
                        allHistoryGroup[allHistoryGroup.size - 1].second.searchAfter.toString()
                    hasMore = historyGroup.size >= BATCH_CNT
                } else {
                    searchAfter = ""
                    hasMore = false
                }

                binding.loading.visibility = View.GONE
                binding.refresher.visibleOrGone(historyGroup.isNotEmpty())
                binding.emptyLayout.visibleOrGone(historyGroup.isEmpty())
                historyAdapter.notifyDataSetChanged()
            }
        }

        historyViewModel.ethHistoryResult.observe(viewLifecycleOwner) { response ->
            binding.refresher.isRefreshing = false
            binding.recycler.suppressLayout(false)
            val historyGroup: MutableList<Pair<String, JsonObject>> = mutableListOf()
            response.asJsonObject["txs"].asJsonArray.forEach { history ->
                val headerDate = dpTimeToYear(history.asJsonObject["txTime"].asString.toLong())
                historyGroup.add(Pair(headerDate, history.asJsonObject))
            }
            allEthHistoryGroup.addAll(historyGroup)
            if (historyGroup.isNotEmpty()) {
                historyAdapter.submitList(allEthHistoryGroup as List<Any>?)
                searchAfter = response["search_after"].asString
                hasMore = historyGroup.size >= 20
            } else {
                searchAfter = ""
                hasMore = false
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}