package wannabit.io.cosmostaion.ui.tx.info.major.iota

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.FetchState
import wannabit.io.cosmostaion.chain.majorClass.ChainIota
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.databinding.FragmentSuiActiveBinding

class IotaActiveFragment : Fragment() {

    private var _binding: FragmentSuiActiveBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain

    private lateinit var iotaStakingInfoAdapter: IotaStakingInfoAdapter

    private var activeList: MutableList<Pair<String, JsonObject>> = mutableListOf()

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): IotaActiveFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = IotaActiveFragment()
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
                activeList.clear()
                (selectedChain as ChainIota).iotaFetcher?.let { fetcher ->
                    fetcher.iotaStakedList.forEach { iotaStaked ->
                        iotaStaked["stakes"].asJsonArray.forEach { stakes ->
                            activeList.add(
                                Pair(
                                    iotaStaked["validatorAddress"].asString, stakes.asJsonObject
                                )
                            )
                        }
                    }
                }

                withContext(Dispatchers.Main) {
                    activeList.sortByDescending {
                        it.second["stakeRequestEpoch"].asLong
                    }

                    val active = activeList.filter { it.second["status"].asString != "Pending" }
                    if (active.isEmpty()) {
                        emptyLayout.visibility = View.VISIBLE
                        recycler.visibility = View.GONE
                    } else {
                        emptyLayout.visibility = View.GONE
                        recycler.visibility = View.VISIBLE

                        iotaStakingInfoAdapter = IotaStakingInfoAdapter(selectedChain)
                        recycler.setHasFixedSize(true)
                        recycler.layoutManager = LinearLayoutManager(requireContext())
                        recycler.adapter = iotaStakingInfoAdapter

                        iotaStakingInfoAdapter.submitList(active)
                        if (::iotaStakingInfoAdapter.isInitialized) {
                            iotaStakingInfoAdapter.setOnItemClickListener { staked ->
//                                handleOneClickWithDelay(
//                                    SuiUnStakingFragment(
//                                        selectedChain, staked
//                                    )
//                                )
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
                    ApplicationViewModel.shared.loadIotaData(
                        account.id, selectedChain, isRefresh = true
                    )
                }
            }
        }
    }

    private fun observeViewModels() {
        ApplicationViewModel.shared.refreshStakingInfoFetchedResult.observe(viewLifecycleOwner) { tag ->
            if (selectedChain.tag == tag) {
                ApplicationViewModel.shared.notifyRefreshEvent()
                updateView()
            }
        }

        ApplicationViewModel.shared.txFetchedResult.observe(viewLifecycleOwner) { tag ->
            if (selectedChain.tag == tag) {
                ApplicationViewModel.shared.notifySuiTxEvent()
                updateView()
            }
        }
    }

    private fun handleOneClickWithDelay(bottomSheetDialogFragment: BottomSheetDialogFragment) {
        if (isClickable) {
            isClickable = false

            bottomSheetDialogFragment.show(
                requireActivity().supportFragmentManager, bottomSheetDialogFragment::class.java.name
            )

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 300)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}