package wannabit.io.cosmostaion.ui.tx.info.major.sui

import android.os.Build
import android.os.Bundle
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
import wannabit.io.cosmostaion.chain.FetchState
import wannabit.io.cosmostaion.chain.fetcher.StakeReward
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.databinding.FragmentSuiActiveBinding

class SuiPendingFragment : Fragment() {

    private var _binding: FragmentSuiActiveBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain

    private lateinit var suiStakingInfoAdapter: SuiStakingInfoAdapter

    private var pendingList: MutableList<StakeReward> = mutableListOf()

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): SuiPendingFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = SuiPendingFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuiActiveBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        refreshData()
        observeViewModels()
    }

    private fun initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("selectedChain", BaseChain::class.java)
                ?.let { selectedChain = it }
        } else {
            (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                selectedChain = it
            }
        }

        updateView()
    }

    private fun updateView() {
        binding.apply {
            lifecycleScope.launch(Dispatchers.IO) {
                refresher.isRefreshing = false
                pendingList.clear()
                (selectedChain as ChainSui).suiFetcher()?.let { fetcher ->
                    pendingList.addAll(fetcher.suiStakedList)
                }

                withContext(Dispatchers.Main) {
                    pendingList.sortByDescending { it.activationEpoch }

                    val pending = pendingList.filter { it.isPending }
                    if (pending.isEmpty()) {
                        emptyLayout.visibility = View.VISIBLE
                        recycler.visibility = View.GONE
                    } else {
                        emptyLayout.visibility = View.GONE
                        recycler.visibility = View.VISIBLE

                        suiStakingInfoAdapter = SuiStakingInfoAdapter(selectedChain)
                        recycler.setHasFixedSize(true)
                        recycler.layoutManager = LinearLayoutManager(requireContext())
                        recycler.adapter = suiStakingInfoAdapter

                        suiStakingInfoAdapter.submitList(pending)
                        if (::suiStakingInfoAdapter.isInitialized) {
                            suiStakingInfoAdapter.setOnItemClickListener { _ ->
                                requireActivity().makeToast(R.string.error_pending)
                                return@setOnItemClickListener
                            }
                        }
                    }
                }
            }
        }
    }

    private fun refreshData() {
        binding.refresher.setOnRefreshListener {
            if (selectedChain.fetchState == FetchState.BUSY) {
                binding.refresher.isRefreshing = false
            } else {
                BaseData.baseAccount?.let { account ->
                    selectedChain.fetchState = FetchState.IDLE
                    ApplicationViewModel.shared.loadSuiData(
                        account.id,
                        selectedChain as ChainSui,
                        isRefresh = true
                    )
                }
            }
        }
    }

    private fun observeViewModels() {
        ApplicationViewModel.shared.notifySuiTxResult.observe(viewLifecycleOwner) {
            updateView()
        }

        ApplicationViewModel.shared.notifyRefreshResult.observe(viewLifecycleOwner) {
            updateView()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}