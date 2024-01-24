package wannabit.io.cosmostaion.ui.dialog.tx

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cosmos.distribution.v1beta1.DistributionProto.DelegationDelegatorReward
import com.cosmos.staking.v1beta1.StakingProto.Validator
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.FragmentStakingOptionBinding
import wannabit.io.cosmostaion.ui.tx.info.OptionType
import wannabit.io.cosmostaion.ui.tx.info.UnBondingEntry
import wannabit.io.cosmostaion.ui.tx.step.CancelUnBondingFragment
import wannabit.io.cosmostaion.ui.tx.step.ClaimRewardFragment
import wannabit.io.cosmostaion.ui.tx.step.CompoundingFragment
import wannabit.io.cosmostaion.ui.tx.step.ReDelegateFragment
import wannabit.io.cosmostaion.ui.tx.step.StakingFragment
import wannabit.io.cosmostaion.ui.tx.step.UnStakingFragment

class StakingOptionFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentStakingOptionBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: CosmosLine
    private var validator: Validator? = null
    private var unBondingEntry: UnBondingEntry? = null
    private var optionType: OptionType? = OptionType.STAKE

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(
            selectedChain: CosmosLine,
            validator: Validator?,
            unBondingEntry: UnBondingEntry?,
            optionType: OptionType?
        ): StakingOptionFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putSerializable("validator", validator)
                putParcelable("unBondingEntry", unBondingEntry)
                putSerializable("optionType", optionType)
            }
            val fragment = StakingOptionFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStakingOptionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
    }

    private fun initView() {
        binding.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.apply {
                    getParcelable("selectedChain", CosmosLine::class.java)?.let {
                        selectedChain = it
                    }
                    validator = getSerializable("validator", Validator::class.java)
                    unBondingEntry = getParcelable("unBondingEntry", UnBondingEntry::class.java)
                    optionType = getSerializable("optionType", OptionType::class.java)
                }
            } else {
                (arguments?.getParcelable("selectedChain") as? CosmosLine)?.let {
                    selectedChain = it
                }
                arguments?.apply {
                    (getParcelable("selectedChain") as? CosmosLine)?.let {
                        selectedChain = it
                    }
                    validator = getSerializable("validator") as? Validator
                    unBondingEntry = getParcelable("unBondingEntry") as? UnBondingEntry
                    optionType = getSerializable("optionType") as? OptionType
                }
            }

            val isStakeOption = optionType == OptionType.STAKE
            listOf(
                stakeLayout,
                unstakeLayout,
                switchValidatorLayout,
                claimRewardsLayout,
                compoundingLayout
            ).forEach {
                it.visibleOrGone(isStakeOption)
            }
            unstakeCancelLayout.goneOrVisible(isStakeOption)

            listOf(view0, view1, view2, view3, view4).forEach { it.visibleOrGone(isStakeOption) }
            cancelView.goneOrVisible(isStakeOption)
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            stakeLayout.setOnClickListener {
                setOneClickAction(StakingFragment.newInstance(selectedChain, validator))
            }

            unstakeLayout.setOnClickListener {
                setOneClickAction(UnStakingFragment.newInstance(selectedChain, validator))
            }

            switchValidatorLayout.setOnClickListener {
                setOneClickAction(ReDelegateFragment.newInstance(selectedChain, validator))
            }

            claimRewardsLayout.setOnClickListener {
                val claimableRewards: MutableList<DelegationDelegatorReward?> = mutableListOf()
                selectedChain.claimableRewards()
                    .firstOrNull { it?.validatorAddress == validator?.operatorAddress }
                    ?.let { claimableReward ->
                        claimableRewards.add(claimableReward)
                    } ?: run {
                    requireContext().makeToast(R.string.error_not_reward)
                    return@setOnClickListener
                }
                setOneClickAction(ClaimRewardFragment.newInstance(selectedChain, claimableRewards))
            }

            compoundingLayout.setOnClickListener {
                if (selectedChain.rewardAddress != selectedChain.address) {
                    requireContext().makeToast(R.string.error_reward_address_changed_msg)
                    return@setOnClickListener
                }
                val claimableRewards: MutableList<DelegationDelegatorReward?> = mutableListOf()
                selectedChain.claimableRewards()
                    .firstOrNull { it?.validatorAddress == validator?.operatorAddress }
                    ?.let { claimableReward ->
                        claimableRewards.add(claimableReward)
                    } ?: run {
                    requireContext().makeToast(R.string.error_not_reward)
                    return@setOnClickListener
                }
                setOneClickAction(CompoundingFragment.newInstance(selectedChain, claimableRewards))
            }

            unstakeCancelLayout.setOnClickListener {
                setOneClickAction(
                    CancelUnBondingFragment.newInstance(
                        selectedChain, unBondingEntry
                    )
                )
            }
        }
    }

    private fun setOneClickAction(bottomSheetDialogFragment: BottomSheetDialogFragment) {
        if (isClickable) {
            isClickable = false

            bottomSheetDialogFragment.show(
                requireActivity().supportFragmentManager, bottomSheetDialogFragment::class.java.name
            )

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 1000)
        }
        dismiss()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}