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
import wannabit.io.cosmostaion.ui.tx.step.ClaimRewardFragment
import wannabit.io.cosmostaion.ui.tx.step.CompoundingFragment
import wannabit.io.cosmostaion.ui.tx.step.ReDelegateFragment
import wannabit.io.cosmostaion.ui.tx.step.UnStakingFragment

class StakingOptionFragment(
    val selectedChain: CosmosLine,
    val validator: StakingProto.Validator?,
    private val optionType: OptionType?
) : BottomSheetDialogFragment() {

    private var _binding: FragmentStakingOptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStakingOptionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        clickAction()
    }

    private fun initView() {
        binding.apply {
            unstakeLayout.visibleOrGone(optionType == OptionType.STAKE)
            switchValidatorLayout.visibleOrGone(optionType == OptionType.STAKE)
            claimRewardsLayout.visibleOrGone(optionType == OptionType.STAKE)
            compoundingLayout.visibleOrGone(optionType == OptionType.STAKE)
            unstakeCancelLayout.goneOrVisible(optionType == OptionType.STAKE)

            view1.visibleOrGone(optionType == OptionType.STAKE)
            view2.visibleOrGone(optionType == OptionType.STAKE)
            view3.visibleOrGone(optionType == OptionType.STAKE)
            view4.visibleOrGone(optionType == OptionType.STAKE)
            cancelView.goneOrVisible(optionType == OptionType.STAKE)
        }
    }

    private fun clickAction() {
        var isClickable = true
        binding.apply {
            unstakeLayout.setOnClickListener {
                if (!selectedChain.isTxFeePayable(requireContext())) {
                    requireContext().makeToast(R.string.error_not_enough_fee)
                    return@setOnClickListener
                }

                val bottomSheet = UnStakingFragment(selectedChain, validator)
                if (isClickable) {
                    isClickable = false
                    bottomSheet.show(requireActivity().supportFragmentManager, StakingOptionFragment::class.java.name)

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
                dismiss()
            }

            switchValidatorLayout.setOnClickListener {
                if (!selectedChain.isTxFeePayable(requireContext())) {
                    requireContext().makeToast(R.string.error_not_enough_fee)
                    return@setOnClickListener
                }

                val bottomSheet = ReDelegateFragment(selectedChain, validator)
                if (isClickable) {
                    isClickable = false
                    bottomSheet.show(requireActivity().supportFragmentManager, ReDelegateFragment::class.java.name)

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
                dismiss()
            }

            claimRewardsLayout.setOnClickListener {
                if (!selectedChain.isTxFeePayable(requireContext())) {
                    requireContext().makeToast(R.string.error_not_enough_fee)
                    return@setOnClickListener
                }
                val claimableRewards: MutableList<DelegationDelegatorReward?> = mutableListOf()
                selectedChain.claimableRewards().firstOrNull { it?.validatorAddress == validator?.operatorAddress }?.let { claimableReward ->
                    claimableRewards.add(claimableReward)
                } ?: run {
                    requireContext().makeToast(R.string.error_not_reward)
                    return@setOnClickListener
                }

                val bottomSheet = ClaimRewardFragment(selectedChain, claimableRewards)
                if (isClickable) {
                    isClickable = false
                    bottomSheet.show(requireActivity().supportFragmentManager, ClaimRewardFragment::class.java.name)

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
                dismiss()
            }

            compoundingLayout.setOnClickListener {
                if (!selectedChain.isTxFeePayable(requireContext())) {
                    requireContext().makeToast(R.string.error_not_enough_fee)
                    return@setOnClickListener
                }
                if (selectedChain.rewardAddress != selectedChain.address) {
                    requireContext().makeToast(R.string.error_reward_address_changed_msg)
                    return@setOnClickListener
                }
                val claimableRewards: MutableList<DelegationDelegatorReward?> = mutableListOf()
                selectedChain.claimableRewards().firstOrNull { it?.validatorAddress == validator?.operatorAddress }?.let { claimableReward ->
                    claimableRewards.add(claimableReward)
                } ?: run {
                    requireContext().makeToast(R.string.error_not_reward)
                    return@setOnClickListener
                }

                val bottomSheet = CompoundingFragment(selectedChain, claimableRewards)
                if (isClickable) {
                    isClickable = false
                    bottomSheet.show(requireActivity().supportFragmentManager, CompoundingFragment::class.java.name)

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
                dismiss()
            }

            unstakeCancelLayout.setOnClickListener {
                if (!selectedChain.isTxFeePayable(requireContext())) {
                    requireContext().makeToast(R.string.error_not_enough_fee)
                    return@setOnClickListener
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}