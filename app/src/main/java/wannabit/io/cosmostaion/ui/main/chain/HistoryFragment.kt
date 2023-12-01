package wannabit.io.cosmostaion.ui.main.chain

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.model.res.BnbHistory
import wannabit.io.cosmostaion.data.model.res.CosmosHistory
import wannabit.io.cosmostaion.data.repository.chain.HistoryRepositoryImpl
import wannabit.io.cosmostaion.databinding.FragmentHistoryBinding
import wannabit.io.cosmostaion.ui.viewmodel.chain.HistoryViewModel
import wannabit.io.cosmostaion.ui.viewmodel.chain.HistoryViewModelProviderFactory
import java.util.Calendar

class HistoryFragment(position: Int) : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var selectedChain: CosmosLine
    private val selectedPosition = position

    private lateinit var historyAdapter: HistoryAdapter

    private var searchId: Int? = 0
    private var hasMore = false
    private val BATCH_CNT = 30

    private val allHistoryGroup: MutableList<Pair<String, CosmosHistory>> = mutableListOf()
    private val allBnbHistoryGroup: MutableList<Pair<String, BnbHistory>> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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
        historyViewModel = ViewModelProvider(this, historyViewModelProviderFactory)[HistoryViewModel::class.java]
    }

    private fun refreshData() {
        binding.refresher.setOnRefreshListener {
            allHistoryGroup.clear()
            allBnbHistoryGroup.clear()
            searchId = 0
            initData()
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            BaseData.baseAccount?.let { baseAccount ->
                selectedChain = baseAccount.displayCosmosLineChains[selectedPosition]
                historyAdapter = HistoryAdapter(requireContext(), selectedChain)
                recycler.setHasFixedSize(true)
                recycler.layoutManager = LinearLayoutManager(requireContext())
                recycler.adapter = historyAdapter

                initData()
            }
        }
    }

    private fun initData() {
        if (selectedChain is ChainBinanceBeacon) {
            historyViewModel.bnbHistory(requireContext(), selectedChain.address, threeMonthAgoTime(), currentTime())
        } else {
            historyViewModel.history(requireContext(), selectedChain.apiName, selectedChain.address, BATCH_CNT.toString(), searchId)
        }
        binding.refresher.isRefreshing = false
    }

    private fun scrollView() {
        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (historyAdapter.itemCount == 0) {
                    return
                }
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = historyAdapter.itemCount - 1

                if (lastVisibleItemPosition == itemTotalCount) {
                    if (hasMore) {
                        hasMore = false
                        historyViewModel.history(requireContext(), selectedChain.apiName, selectedChain.address, BATCH_CNT.toString(), searchId)
                    }
                }
            }
        })
    }

    private fun checkHistory() {
        historyViewModel.historyResult.observe(viewLifecycleOwner) { response ->
            allHistoryGroup.addAll(response)
            response?.let { historyGroup ->
                if (historyGroup.isNotEmpty()) {
                    historyAdapter.submitList(allHistoryGroup as List<Any>?)
                    searchId = allHistoryGroup[allHistoryGroup.size - 1].second.header?.id
                    hasMore = historyGroup.size >= BATCH_CNT
                } else {
                    searchId = 0
                    hasMore = false
                }

                binding.recycler.visibleOrGone(historyGroup.isNotEmpty())
                binding.emptyLayout.visibleOrGone(historyGroup.isEmpty())
                historyAdapter.notifyDataSetChanged()
            }
        }

        historyViewModel.bnbHistoryResult.observe(viewLifecycleOwner) { response ->
            allBnbHistoryGroup.addAll(response)
            response?.let {
                historyAdapter.submitList(allBnbHistoryGroup as List<Any>?)
            }

            binding.recycler.visibleOrGone(allBnbHistoryGroup.isNotEmpty())
            binding.emptyLayout.visibleOrGone(allBnbHistoryGroup.isEmpty())
            historyAdapter.notifyDataSetChanged()
        }

        historyViewModel.errorMessage.observe(viewLifecycleOwner) { errorMsg ->
            binding.recycler.visibility = View.GONE
            binding.emptyLayout.visibility = View.VISIBLE
            Log.e("error : ", errorMsg)
            return@observe
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

fun currentTime(): String {
    val cTime = Calendar.getInstance()
    return cTime.timeInMillis.toString()
}

fun threeMonthAgoTime(): String {
    val cTime = Calendar.getInstance()
    cTime.add(Calendar.MONTH, -3)
    return cTime.timeInMillis.toString()
}