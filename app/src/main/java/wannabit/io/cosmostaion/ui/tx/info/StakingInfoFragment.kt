package wannabit.io.cosmostaion.ui.tx.info

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
import com.cosmos.staking.v1beta1.StakingProto
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.FetchState
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.databinding.FragmentStakingInfoBinding
import wannabit.io.cosmostaion.ui.tx.option.general.StakingOptionFragment

class StakingInfoFragment : Fragment() {

    private var _binding: FragmentStakingInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain

    private lateinit var stakingInfoAdapter: StakingInfoAdapter

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): StakingInfoFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = StakingInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStakingInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        setUpStakeInfo()
        refreshData()
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
                    if (delegations.isNotEmpty()) {
                        recycler.visibility = View.VISIBLE
                        emptyLayout.visibility = View.GONE
                        stakingInfoAdapter = StakingInfoAdapter(
                            selectedChain,
                            validators,
                            delegations,
                            mutableListOf(),
                            OptionType.STAKE,
                            selectClickAction
                        )
                        recycler.setHasFixedSize(true)
                        recycler.layoutManager = LinearLayoutManager(requireContext())
                        recycler.adapter = stakingInfoAdapter

                    } else {
                        recycler.visibility = View.GONE
                        emptyLayout.visibility = View.VISIBLE
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
                    ApplicationViewModel.shared.loadChainData(selectedChain, account.id, false)
                }
            }
        }
    }

    private val selectClickAction = object : StakingInfoAdapter.ClickListener {
        override fun selectStakingAction(validator: StakingProto.Validator?) {
            handleOneClickWithDelay(
                StakingOptionFragment.newInstance(
                    selectedChain, validator, null, OptionType.STAKE
                )
            )
        }

        override fun selectUnStakingCancelAction(unBondingEntry: UnBondingEntry?) {
            handleOneClickWithDelay(
                StakingOptionFragment.newInstance(
                    selectedChain, null, unBondingEntry, OptionType.UNSTAKE
                )
            )
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

    private fun setUpStakeInfo() {
        ApplicationViewModel.shared.txFetchedResult.observe(viewLifecycleOwner) {
            ApplicationViewModel.shared.notifyTxEvent()
            initData()
        }

        ApplicationViewModel.shared.refreshStakingInfoFetchedResult.observe(viewLifecycleOwner) { tag ->
            if (selectedChain.tag == tag) {
                initData()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}