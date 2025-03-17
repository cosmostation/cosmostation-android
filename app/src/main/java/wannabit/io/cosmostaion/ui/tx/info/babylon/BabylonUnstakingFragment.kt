package wannabit.io.cosmostaion.ui.tx.info.babylon

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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.FetchState
import wannabit.io.cosmostaion.chain.fetcher.BabylonFetcher
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.data.viewmodel.chain.BabylonViewModel
import wannabit.io.cosmostaion.databinding.FragmentBabylonStakingBinding
import wannabit.io.cosmostaion.ui.tx.info.OptionType
import wannabit.io.cosmostaion.ui.tx.info.UnBondingEntry
import wannabit.io.cosmostaion.ui.tx.option.general.StakingOptionFragment

class BabylonUnstakingFragment(
    private val selectedChain: BaseChain,
    private val babylonEpochData: BabylonFetcher.BabylonEpochData?,
    private val babylonEpochTxType: MutableList<BabylonFetcher.BabylonEpochTxType>,
    private val babylonViewModel: BabylonViewModel
) : Fragment() {

    private var _binding: FragmentBabylonStakingBinding? = null
    private val binding get() = _binding!!

    private lateinit var babylonUnStakingAdapter: BabylonUnStakingAdapter

    private var isClickable = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBabylonStakingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        refreshData()
        observeViewModels()
    }

    private fun initData() {
        binding.apply {
            lifecycleScope.launch(Dispatchers.IO) {
                val validators = selectedChain.cosmosFetcher?.cosmosValidators ?: mutableListOf()
                val unBondings =
                    selectedChain.cosmosFetcher?.cosmosUnbondings?.flatMap { unBonding ->
                        unBonding.entriesList.map { entry ->
                            UnBondingEntry(unBonding.validatorAddress, entry)
                        }
                    }?.sortedBy { it.entry?.creationHeight }?.toMutableList() ?: mutableListOf()

                val filterUnStakingTx = babylonEpochTxType.filter {
                    it.type.contains("MsgWrappedUndelegate") || it.type.contains(
                        "MsgWrappedCancelUnbondingDelegation"
                    )
                }.toMutableList()
                val filteredUnStakings = unBondings.filterNot { unBonding ->
                    filterUnStakingTx.any { cancel -> cancel.createHeight == unBonding.entry?.creationHeight }
                }.toMutableList()

                withContext(Dispatchers.Main) {
                    refresher.isRefreshing = false
                    if (unBondings.isEmpty() && babylonEpochTxType.isEmpty()) {
                        recycler.visibility = View.GONE
                        emptyLayout.visibility = View.VISIBLE

                    } else {
                        recycler.visibility = View.VISIBLE
                        emptyLayout.visibility = View.GONE
                        babylonUnStakingAdapter = BabylonUnStakingAdapter(
                            requireContext(),
                            selectedChain,
                            babylonEpochData?.currentEpoch?.currentEpoch,
                            filterUnStakingTx,
                            validators = validators,
                            unBondings = filteredUnStakings,
                            listener = selectClickAction
                        )
                        recycler.setHasFixedSize(true)
                        recycler.layoutManager = LinearLayoutManager(requireContext())
                        recycler.adapter = babylonUnStakingAdapter
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
                    ApplicationViewModel.shared.loadChainData(
                        selectedChain, account.id, isRefresh = true
                    )
                    babylonViewModel.epochData(selectedChain)
                }
            }
        }
    }

    private val selectClickAction = object : BabylonUnStakingAdapter.ClickListener {
        override fun selectUnStakingAction(unBondingEntry: UnBondingEntry?) {
            handleOneClickWithDelay(
                StakingOptionFragment.newInstance(
                    selectedChain, unBondingEntry = unBondingEntry, optionType = OptionType.UNSTAKE
                )
            )
        }
    }

    private fun observeViewModels() {
        ApplicationViewModel.shared.notifyTxResult.observe(viewLifecycleOwner) {
            babylonViewModel.epochData(selectedChain)
            initData()
        }

        ApplicationViewModel.shared.notifyRefreshResult.observe(viewLifecycleOwner) {
            initData()
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