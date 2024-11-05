package wannabit.io.cosmostaion.ui.tx.option.general

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
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.testnetClass.ChainInitiaTestnet
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.FragmentStakingOptionBinding
import wannabit.io.cosmostaion.ui.tx.info.OptionType
import wannabit.io.cosmostaion.ui.tx.info.UnBondingEntry
import wannabit.io.cosmostaion.ui.tx.genTx.CancelUnBondingFragment
import wannabit.io.cosmostaion.ui.tx.genTx.ClaimRewardFragment
import wannabit.io.cosmostaion.ui.tx.genTx.CompoundingFragment
import wannabit.io.cosmostaion.ui.tx.genTx.ReDelegateFragment
import wannabit.io.cosmostaion.ui.tx.genTx.StakingFragment
import wannabit.io.cosmostaion.ui.tx.genTx.UnStakingFragment
import wannabit.io.cosmostaion.ui.tx.info.InitiaUnBondingEntry

class StakingOptionFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentStakingOptionBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain
    private var validator: Validator? = null
    private var unBondingEntry: UnBondingEntry? = null
    private var initiaValidator: com.initia.mstaking.v1.StakingProto.Validator? = null
    private var initiaUnBondingEntry: InitiaUnBondingEntry? = null
    private var optionType: OptionType? = OptionType.STAKE

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(
            selectedChain: BaseChain,
            validator: Validator?,
            initiaValidator: com.initia.mstaking.v1.StakingProto.Validator?,
            unBondingEntry: UnBondingEntry?,
            initiaUnBondingEntry: InitiaUnBondingEntry?,
            optionType: OptionType?
        ): StakingOptionFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putSerializable("validator", validator)
                putSerializable("initiaValidator", initiaValidator)
                putParcelable("unBondingEntry", unBondingEntry)
                putParcelable("initiaUnBondingEntry", initiaUnBondingEntry)
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
                    getParcelable("selectedChain", BaseChain::class.java)?.let {
                        selectedChain = it
                    }
                    validator = getSerializable("validator", Validator::class.java)
                    initiaValidator = getSerializable("initiaValidator", com.initia.mstaking.v1.StakingProto.Validator::class.java)
                    unBondingEntry = getParcelable("unBondingEntry", UnBondingEntry::class.java)
                    initiaUnBondingEntry = getParcelable("initiaUnBondingEntry", InitiaUnBondingEntry::class.java)
                    optionType = getSerializable("optionType", OptionType::class.java)
                }
            } else {
                arguments?.apply {
                    (getParcelable("selectedChain") as? BaseChain)?.let {
                        selectedChain = it
                    }
                    validator = getSerializable("validator") as? Validator
                    initiaValidator = getSerializable("initiaValidator") as? com.initia.mstaking.v1.StakingProto.Validator
                    unBondingEntry = getParcelable("unBondingEntry") as? UnBondingEntry
                    initiaUnBondingEntry = getParcelable("initiaUnBondingEntry") as? InitiaUnBondingEntry
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
//                if (selectedChain is ChainBeraEvm) {
//                    handleOneClickWithDelay(
//                        EvmStakingFragment.newInstance(
//                            selectedChain as EthereumLine,
//                            validator
//                        )
//                    )
//                } else {
                if (selectedChain is ChainInitiaTestnet) {
                    handleOneClickWithDelay(StakingFragment.newInstance(selectedChain, null, initiaValidator))
                } else {
                    handleOneClickWithDelay(StakingFragment.newInstance(selectedChain, validator, null))
                }
//                }
            }

            unstakeLayout.setOnClickListener {
//                if (selectedChain is ChainBeraEvm) {
//                    handleOneClickWithDelay(
//                        EvmUnStakingFragment.newInstance(
//                            selectedChain as EthereumLine,
//                            validator
//                        )
//                    )
//                } else {
                handleOneClickWithDelay(UnStakingFragment.newInstance(selectedChain, validator))
//                }
            }

            switchValidatorLayout.setOnClickListener {
//                if (selectedChain is ChainBeraEvm) {
//                    handleOneClickWithDelay(
//                        EvmReDelegateFragment.newInstance(
//                            selectedChain as EthereumLine,
//                            validator
//                        )
//                    )
//                } else {
                handleOneClickWithDelay(
                    ReDelegateFragment.newInstance(
                        selectedChain, validator
                    )
                )
//                }
            }

            claimRewardsLayout.setOnClickListener {
                val claimableRewards: MutableList<DelegationDelegatorReward?> = mutableListOf()
                selectedChain.cosmosFetcher?.cosmosRewards?.firstOrNull { it.validatorAddress == validator?.operatorAddress }
                    ?.let { claimableReward ->
                        if (claimableReward.rewardCount > 0) {
                            claimableRewards.add(claimableReward)
                        } else {
                            requireContext().makeToast(R.string.error_not_reward)
                            return@setOnClickListener
                        }

                    } ?: run {
                    requireContext().makeToast(R.string.error_not_reward)
                    return@setOnClickListener
                }

                handleOneClickWithDelay(
                    ClaimRewardFragment.newInstance(
                        selectedChain, claimableRewards
                    )
                )
            }

            compoundingLayout.setOnClickListener {
                if (selectedChain.cosmosFetcher?.rewardAddress != selectedChain.address) {
                    requireContext().makeToast(R.string.error_reward_address_changed_msg)
                    return@setOnClickListener
                }
                val claimableRewards: MutableList<DelegationDelegatorReward?> = mutableListOf()
                selectedChain.cosmosFetcher?.claimableRewards()
                    ?.firstOrNull { it?.validatorAddress == validator?.operatorAddress }
                    ?.let { claimableReward ->
                        if (claimableReward.rewardCount > 0) {
                            claimableRewards.add(claimableReward)
                        } else {
                            requireContext().makeToast(R.string.error_not_reward)
                            return@setOnClickListener
                        }
                    } ?: run {
                    requireContext().makeToast(R.string.error_not_reward)
                    return@setOnClickListener
                }
                handleOneClickWithDelay(
                    CompoundingFragment.newInstance(
                        selectedChain, claimableRewards
                    )
                )
            }

            unstakeCancelLayout.setOnClickListener {
//                if (selectedChain is ChainBeraEvm) {
//                    handleOneClickWithDelay(
//                        EvmCancelUnStakingFragment.newInstance(
//                            selectedChain as EthereumLine,
//                            unBondingEntry
//                        )
//                    )
//                } else {
                handleOneClickWithDelay(
                    CancelUnBondingFragment.newInstance(
                        selectedChain, unBondingEntry
                    )
                )
//                }
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
        dismiss()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}