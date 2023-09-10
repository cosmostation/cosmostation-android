package wannabit.io.cosmostaion.ui.main.chain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import wannabit.io.cosmostaion.common.BaseData
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
    private val historyId: Int = 0
    private val hasMore = false
    private val BATCH_CNT = 50

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
            historyViewModel.history(selectedChain.apiName, selectedChain.address, BATCH_CNT.toString(), historyId)

            historyAdapter = HistoryAdapter(requireContext(), selectedChain)
            binding.recycler.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = historyAdapter
            }
        }
    }

    private fun loadDataObserve() {
        historyViewModel.historyResult.observe(viewLifecycleOwner) {

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