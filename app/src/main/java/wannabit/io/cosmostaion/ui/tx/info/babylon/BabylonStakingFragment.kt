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
import com.cosmos.staking.v1beta1.StakingProto
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
import wannabit.io.cosmostaion.ui.tx.option.general.StakingOptionFragment

class BabylonStakingFragment(
    private val selectedChain: BaseChain,
    private val babylonEpochData: BabylonFetcher.BabylonEpochData?,
    private val babylonEpochTxType: MutableList<BabylonFetcher.BabylonEpochTxType>,
    private val babylonViewModel: BabylonViewModel
) : Fragment() {

    private var _binding: FragmentBabylonStakingBinding? = null
    private val binding get() = _binding!!

    private lateinit var babylonStakingAdapter: BabylonStakingAdapter

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
                var delegations = selectedChain.cosmosFetcher?.cosmosDelegations ?: mutableListOf()
                val validators = selectedChain.cosmosFetcher?.cosmosValidators ?: mutableListOf()
                val cosmostationValAddress =
                    validators.firstOrNull { it.description.moniker == "Cosmostation" }?.operatorAddress

                val tempDelegations = delegations.toMutableList()
                tempDelegations.sortWith { o1, o2 ->
                    when {
                        o1.delegation.validatorAddress == cosmostationValAddress -> -1
                        o2.delegation.validatorAddress == cosmostationValAddress -> 1
                        o1.balance.amount.toDouble() > o2.balance.amount.toDouble() -> -1
                        else -> 1
                    }
                }
                delegations = tempDelegations

                withContext(Dispatchers.Main) {
                    refresher.isRefreshing = false
                    if (delegations.isEmpty() && babylonEpochTxType.isEmpty()) {
                        recycler.visibility = View.GONE
                        emptyLayout.visibility = View.VISIBLE

                    } else {
                        recycler.visibility = View.VISIBLE
                        emptyLayout.visibility = View.GONE
                        babylonStakingAdapter = BabylonStakingAdapter(
                            requireContext(),
                            selectedChain,
                            babylonEpochData?.currentEpoch?.currentEpoch,
                            babylonEpochTxType.filter {
                                it.type.contains("MsgWrappedDelegate") || it.type.contains(
                                    "MsgWrappedBeginRedelegate"
                                )
                            }.toMutableList(),
                            validators = validators,
                            delegations = delegations,
                            listener = selectClickAction
                        )
                        recycler.setHasFixedSize(true)
                        recycler.layoutManager = LinearLayoutManager(requireContext())
                        recycler.adapter = babylonStakingAdapter
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

    private val selectClickAction = object : BabylonStakingAdapter.ClickListener {
        override fun selectStakingAction(validator: StakingProto.Validator?) {
            handleOneClickWithDelay(
                StakingOptionFragment.newInstance(
                    selectedChain, validator = validator, optionType = OptionType.STAKE
                )
            )
        }
    }

    private fun observeViewModels() {
        ApplicationViewModel.shared.refreshStakingInfoFetchedResult.observe(viewLifecycleOwner) { tag ->
            if (selectedChain.tag == tag) {
                ApplicationViewModel.shared.notifyRefreshEvent()
                initData()
            }
        }

        ApplicationViewModel.shared.txFetchedResult.observe(viewLifecycleOwner) { tag ->
            if (selectedChain.tag == tag) {
                babylonViewModel.epochData(selectedChain)
                ApplicationViewModel.shared.notifyTxEvent()
                initData()
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