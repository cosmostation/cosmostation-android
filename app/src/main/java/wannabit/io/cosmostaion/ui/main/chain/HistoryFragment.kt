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
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.model.CosmosHistory
import wannabit.io.cosmostaion.data.repository.chain.HistoryRepositoryImpl
import wannabit.io.cosmostaion.databinding.FragmentHistoryBinding
import wannabit.io.cosmostaion.ui.viewmodel.chain.HistoryViewModel
import wannabit.io.cosmostaion.ui.viewmodel.chain.HistoryViewModelProviderFactory

class HistoryFragment(position: Int) : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var historyViewModel: HistoryViewModel

    private lateinit var historyAdapter: HistoryAdapter
    private val selectedPosition = position

    private var historyId: Int = 0
    private var hasMore = false
    private val BATCH_CNT = 50

    private val allHistoryGroup: MutableList<Pair<String, CosmosHistory>> = mutableListOf()

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
    }

    private fun initViewModel() {
        val historyRepository = HistoryRepositoryImpl()
        val historyViewModelProviderFactory = HistoryViewModelProviderFactory(historyRepository)
        historyViewModel = ViewModelProvider(this, historyViewModelProviderFactory)[HistoryViewModel::class.java]
    }

    private fun initRecyclerView() {
        val baseAccount = BaseData.baseAccount
        baseAccount?.let { account ->
            val selectedChain = account.allCosmosLineChains[selectedPosition]
            historyViewModel.history(requireContext(), selectedChain.apiName, selectedChain.address, BATCH_CNT.toString(), historyId)

            historyAdapter = HistoryAdapter(requireContext(), selectedChain)
            binding.recycler.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = historyAdapter

                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        if (adapter != null && adapter!!.itemCount == 0) {
                            return
                        }

                        val lastVisibleItemPosition =
                            (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                        val itemTotalCount = adapter!!.itemCount - 1

                        if (lastVisibleItemPosition == itemTotalCount) {
                            if (hasMore) {
                                hasMore = false
                                historyViewModel.history(requireContext(), selectedChain.apiName, selectedChain.address, "50", historyId)
                            }
                        }
                    }
                })
            }
        }
    }

    private fun checkHistory() {
        historyViewModel.historyResult.observe(viewLifecycleOwner) { response ->
            allHistoryGroup.addAll(response)
            response?.let { historyGroup ->
                if (historyGroup.isNotEmpty()) {
                    historyAdapter.submitList(allHistoryGroup)
                    historyId = historyGroup.lastOrNull()?.second?.header?.id ?:0
                    hasMore = historyGroup.size >= BATCH_CNT
                } else {
                    historyId = 0
                    hasMore = false
                }

                binding.recycler.visibleOrGone(historyGroup.isNotEmpty())
                binding.emptyLayout.visibleOrGone(historyGroup.isEmpty())
                historyAdapter.notifyDataSetChanged()
            }
        }

        historyViewModel.errorMessage.observe(viewLifecycleOwner) { errorMsg ->
            requireActivity().makeToast(errorMsg)
            return@observe
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        historyViewModel.clearDisposables()
    }
}