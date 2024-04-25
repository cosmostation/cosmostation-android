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
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.model.res.BnbHistory
import wannabit.io.cosmostaion.data.model.res.CosmosHistory
import wannabit.io.cosmostaion.data.model.res.TransactionList
import wannabit.io.cosmostaion.data.repository.chain.HistoryRepositoryImpl
import wannabit.io.cosmostaion.databinding.FragmentHistoryBinding
import wannabit.io.cosmostaion.ui.tx.info.SendResultFragment
import wannabit.io.cosmostaion.ui.viewmodel.chain.HistoryViewModel
import wannabit.io.cosmostaion.ui.viewmodel.chain.HistoryViewModelProviderFactory
import java.util.Calendar

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var selectedChain: CosmosLine

    private lateinit var historyAdapter: HistoryAdapter

    private var searchAfter: String = ""
    private var hasMore = false
    private val BATCH_CNT = 30

    private val allHistoryGroup: MutableList<Pair<String, CosmosHistory>> = mutableListOf()
    private val allBnbHistoryGroup: MutableList<Pair<String, BnbHistory>> = mutableListOf()
    private val allOktHistoryGroup: MutableList<Pair<String, TransactionList>> = mutableListOf()

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: CosmosLine): HistoryFragment {
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
            arguments?.getParcelable("selectedChain", CosmosLine::class.java)
                ?.let { selectedChain = it }
        } else {
            (arguments?.getParcelable("selectedChain") as? CosmosLine)?.let {
                selectedChain = it
            }
        }
    }

    private fun refreshData() {
        binding.refresher.setOnRefreshListener {
            allHistoryGroup.clear()
            allBnbHistoryGroup.clear()
            allOktHistoryGroup.clear()
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
                historyAdapter.setOnItemClickListener { line, history, hash ->
                    when (line) {
                        is ChainOkt996Keccak, is ChainOktEvm -> {
                            line.explorerTx(hash)?.let {
                                startActivity(Intent(Intent.ACTION_VIEW, it))
                            } ?: run {
                                return@setOnItemClickListener
                            }
                        }

                        else -> {
                            history?.let {
                                if (it.getMsgCnt() == 1 && it.getMsgType(
                                        requireContext(), line.address
                                    ).equals(getString(R.string.tx_send), true)
                                ) {
                                    if (isClickable) {
                                        isClickable = false

                                        SendResultFragment(line, history).show(
                                            requireActivity().supportFragmentManager,
                                            SendResultFragment::class.java.name
                                        )

                                        Handler(Looper.getMainLooper()).postDelayed({
                                            isClickable = true
                                        }, 300)
                                    }

                                } else {
                                    line.explorerTx(hash)?.let {
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
            is ChainBinanceBeacon -> {
                historyViewModel.bnbHistory(
                    requireContext(), selectedChain.address, threeMonthAgoTime(), currentTime()
                )
            }

            is ChainOkt996Keccak, is ChainOktEvm -> {
                historyViewModel.oktHistory(
                    "ANDROID", ByteUtils.convertBech32ToEvm(selectedChain.address), "50"
                )
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
        })
    }

    private fun checkHistory() {
        historyViewModel.historyResult.observe(viewLifecycleOwner) { response ->
            allHistoryGroup.addAll(response)
            response?.let { historyGroup ->
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

        historyViewModel.bnbHistoryResult.observe(viewLifecycleOwner) { response ->
            allBnbHistoryGroup.addAll(response)
            response?.let {
                historyAdapter.submitList(allBnbHistoryGroup as List<Any>?)
            }

            binding.loading.visibility = View.GONE
            binding.refresher.visibleOrGone(allBnbHistoryGroup.isNotEmpty())
            binding.emptyLayout.visibleOrGone(allBnbHistoryGroup.isEmpty())
            historyAdapter.notifyDataSetChanged()
        }

        historyViewModel.oktHistoryResult.observe(viewLifecycleOwner) { response ->
            allOktHistoryGroup.addAll(response)
            response?.let {
                historyAdapter.submitList(allOktHistoryGroup as List<Any>?)
            }

            binding.loading.visibility = View.GONE
            binding.refresher.visibleOrGone(allOktHistoryGroup.isNotEmpty())
            binding.emptyLayout.visibleOrGone(allOktHistoryGroup.isEmpty())
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

fun currentTime(): String {
    val cTime = Calendar.getInstance()
    return cTime.timeInMillis.toString()
}

fun threeMonthAgoTime(): String {
    val cTime = Calendar.getInstance()
    cTime.add(Calendar.MONTH, -3)
    return cTime.timeInMillis.toString()
}