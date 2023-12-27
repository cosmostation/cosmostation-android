package wannabit.io.cosmostaion.ui.dialog.tx

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cosmos.distribution.v1beta1.DistributionProto.DelegationDelegatorReward
import com.cosmos.staking.v1beta1.StakingProto
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

class StakingOptionFragment(
    val selectedChain: CosmosLine,
    val validator: StakingProto.Validator?,
    private val unBondingEntry: UnBondingEntry?,
    private val optionType: OptionType?
) : BottomSheetDialogFragment() {

    private var _binding: FragmentStakingOptionBinding? = null
    private val binding get() = _binding!!

    private var isClickable = true

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
            val isStakeOption = optionType == OptionType.STAKE
            stakeLayout.visibleOrGone(isStakeOption)
            unstakeLayout.visibleOrGone(isStakeOption)
            switchValidatorLayout.visibleOrGone(isStakeOption)
            claimRewardsLayout.visibleOrGone(isStakeOption)
            compoundingLayout.visibleOrGone(isStakeOption)
            unstakeCancelLayout.goneOrVisible(isStakeOption)

            listOf(view0, view1, view2, view3, view4).forEach { it.visibleOrGone(isStakeOption) }
            cancelView.goneOrVisible(isStakeOption)
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            stakeLayout.setOnClickListener {
                StakingFragment(selectedChain, validator).show(
                    requireActivity().supportFragmentManager, StakingFragment::class.java.name
                )
                setClickableOnce(isClickable)
            }

            unstakeLayout.setOnClickListener {
                UnStakingFragment(selectedChain, validator).show(
                    requireActivity().supportFragmentManager,
                    UnStakingFragment::class.java.name
                )
                setClickableOnce(isClickable)
            }

            switchValidatorLayout.setOnClickListener {
                ReDelegateFragment(selectedChain, validator).show(
                    requireActivity().supportFragmentManager,
                    ReDelegateFragment::class.java.name
                )
                setClickableOnce(isClickable)
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

                ClaimRewardFragment(selectedChain, claimableRewards).show(
                    requireActivity().supportFragmentManager,
                    ClaimRewardFragment::class.java.name
                )
                setClickableOnce(isClickable)
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
                setClickableOnce(isClickable)

                CompoundingFragment(selectedChain, claimableRewards).show(
                    requireActivity().supportFragmentManager,
                    CompoundingFragment::class.java.name
                )
            }

            unstakeCancelLayout.setOnClickListener {
                CancelUnBondingFragment(selectedChain, unBondingEntry).show(
                    requireActivity().supportFragmentManager,
                    CancelUnBondingFragment::class.java.name
                )
                setClickableOnce(isClickable)
            }
        }
    }

    private fun setClickableOnce(clickable: Boolean) {
        if (clickable) {
            isClickable = false

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