package wannabit.io.cosmostaion.ui.tx.info

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cosmos.staking.v1beta1.StakingProto
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.parcelize.Parcelize
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.databinding.FragmentStakeInfoBinding
import wannabit.io.cosmostaion.ui.option.tx.general.ChangeRewardAddressWarnFragment
import wannabit.io.cosmostaion.ui.option.tx.general.StakingOptionFragment
import wannabit.io.cosmostaion.ui.tx.step.StakingFragment

class StakeInfoFragment(
    private val selectedChain: CosmosLine
) : Fragment() {

    private var _binding: FragmentStakeInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var stakingInfoAdapter: StakingInfoAdapter

    private var isClickable = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStakeInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
    }

    private fun initView() {
        binding.apply {
            CoroutineScope(Dispatchers.IO).launch {
                val rewardAddress = selectedChain.rewardAddress
                var delegations = selectedChain.cosmosDelegations
                val validators = selectedChain.cosmosValidators
                val unBondings = selectedChain.cosmosUnbondings.flatMap { unbonding ->
                    unbonding.entriesList.map { entry ->
                        UnBondingEntry(unbonding.validatorAddress, entry)
                    }
                }.sortedBy { it.entry?.creationHeight }.toMutableList()

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

                val stakingInfoList = delegations + unBondings

                withContext(Dispatchers.Main) {
                    recycler.visibility =
                        if (stakingInfoList.isNotEmpty()) View.VISIBLE else View.GONE
                    emptyStake.visibility =
                        if (stakingInfoList.isEmpty()) View.VISIBLE else View.GONE

                    if (stakingInfoList.isNotEmpty()) {
                        stakingInfoAdapter = StakingInfoAdapter(
                            selectedChain,
                            rewardAddress,
                            validators,
                            delegations,
                            unBondings,
                            selectClickAction
                        )
                        recycler.setHasFixedSize(true)
                        recycler.layoutManager = LinearLayoutManager(requireContext())
                        recycler.adapter = stakingInfoAdapter
                    }
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            btnChangeRewardAddress.setOnClickListener {
                handleOneClickWithDelay(ChangeRewardAddressWarnFragment.newInstance(selectedChain))
            }

            btnStake.setOnClickListener {
                StakingFragment.newInstance(selectedChain, null).show(
                    requireActivity().supportFragmentManager, StakingFragment::class.java.name
                )
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
            }, 1000)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

@Parcelize
data class UnBondingEntry(
    val validatorAddress: String?, val entry: StakingProto.UnbondingDelegationEntry?
) : Parcelable

enum class OptionType { STAKE, UNSTAKE }