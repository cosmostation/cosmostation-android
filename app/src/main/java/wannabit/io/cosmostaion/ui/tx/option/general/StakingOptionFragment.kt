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
import wannabit.io.cosmostaion.chain.cosmosClass.ChainInitia
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.ChainSunrise
import wannabit.io.cosmostaion.chain.cosmosClass.ChainZenrock
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.FragmentStakingOptionBinding
import wannabit.io.cosmostaion.ui.tx.genTx.CancelUnBondingFragment
import wannabit.io.cosmostaion.ui.tx.genTx.ClaimRewardFragment
import wannabit.io.cosmostaion.ui.tx.genTx.CompoundingFragment
import wannabit.io.cosmostaion.ui.tx.genTx.ReDelegateFragment
import wannabit.io.cosmostaion.ui.tx.genTx.StakingFragment
import wannabit.io.cosmostaion.ui.tx.genTx.UnStakingFragment
import wannabit.io.cosmostaion.ui.tx.info.InitiaUnBondingEntry
import wannabit.io.cosmostaion.ui.tx.info.OptionType
import wannabit.io.cosmostaion.ui.tx.info.UnBondingEntry
import wannabit.io.cosmostaion.ui.tx.info.ZenrockUnBondingEntry

class StakingOptionFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentStakingOptionBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain
    private var validator: Validator? = null
    private var unBondingEntry: UnBondingEntry? = null
    private var initiaValidator: com.initia.mstaking.v1.StakingProto.Validator? = null
    private var initiaUnBondingEntry: InitiaUnBondingEntry? = null
    private var zenrockValidator: com.zrchain.validation.HybridValidationProto.ValidatorHV? = null
    private var zenrockUnBondingEntry: ZenrockUnBondingEntry? = null
    private var optionType: OptionType? = OptionType.STAKE

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(
            selectedChain: BaseChain,
            validator: Validator? = null,
            initiaValidator: com.initia.mstaking.v1.StakingProto.Validator? = null,
            zenrockValidator: com.zrchain.validation.HybridValidationProto.ValidatorHV? = null,
            unBondingEntry: UnBondingEntry? = null,
            initiaUnBondingEntry: InitiaUnBondingEntry? = null,
            zenrockUnBondingEntry: ZenrockUnBondingEntry? = null,
            optionType: OptionType?
        ): StakingOptionFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putSerializable("validator", validator)
                putSerializable("initiaValidator", initiaValidator)
                putSerializable("zenrockValidator", zenrockValidator)
                putParcelable("unBondingEntry", unBondingEntry)
                putParcelable("initiaUnBondingEntry", initiaUnBondingEntry)
                putParcelable("zenrockUnBondingEntry", zenrockUnBondingEntry)
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
                    initiaValidator = getSerializable(
                        "initiaValidator", com.initia.mstaking.v1.StakingProto.Validator::class.java
                    )
                    zenrockValidator = getSerializable(
                        "zenrockValidator",
                        com.zrchain.validation.HybridValidationProto.ValidatorHV::class.java
                    )

                    unBondingEntry = getParcelable("unBondingEntry", UnBondingEntry::class.java)
                    initiaUnBondingEntry =
                        getParcelable("initiaUnBondingEntry", InitiaUnBondingEntry::class.java)
                    zenrockUnBondingEntry =
                        getParcelable("zenrockUnBondingEntry", ZenrockUnBondingEntry::class.java)
                    optionType = getSerializable("optionType", OptionType::class.java)
                }
            } else {
                arguments?.apply {
                    (getParcelable("selectedChain") as? BaseChain)?.let {
                        selectedChain = it
                    }
                    validator = getSerializable("validator") as? Validator
                    initiaValidator =
                        getSerializable("initiaValidator") as? com.initia.mstaking.v1.StakingProto.Validator
                    zenrockValidator =
                        getSerializable("zenrockValidator") as? com.zrchain.validation.HybridValidationProto.ValidatorHV

                    unBondingEntry = getParcelable("unBondingEntry") as? UnBondingEntry
                    initiaUnBondingEntry =
                        getParcelable("initiaUnBondingEntry") as? InitiaUnBondingEntry
                    zenrockUnBondingEntry =
                        getParcelable("zenrockUnBondingEntry") as? ZenrockUnBondingEntry
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

            if (selectedChain is ChainNeutron) {
                claimRewardsLayout.visibility = View.GONE
                compoundingLayout.visibility = View.GONE
                view4.visibility = View.GONE
            }

            if (selectedChain is ChainSunrise) {
                compoundingLayout.visibility = View.GONE
                view4.visibility = View.GONE
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            stakeLayout.setOnClickListener {
                when (selectedChain) {
                    is ChainInitia -> {
                        handleOneClickWithDelay(
                            StakingFragment.newInstance(
                                selectedChain, initiaToValidator = initiaValidator
                            )
                        )
                    }

                    is ChainZenrock -> {
                        handleOneClickWithDelay(
                            StakingFragment.newInstance(
                                selectedChain, zenrockToValidator = zenrockValidator
                            )
                        )
                    }

                    else -> {
                        handleOneClickWithDelay(
                            StakingFragment.newInstance(
                                selectedChain, toValidator = validator
                            )
                        )
                    }
                }
            }

            unstakeLayout.setOnClickListener {
                when (selectedChain) {
                    is ChainInitia -> {
                        handleOneClickWithDelay(
                            UnStakingFragment.newInstance(
                                selectedChain, initiaValidator = initiaValidator
                            )
                        )
                    }

                    is ChainZenrock -> {
                        handleOneClickWithDelay(
                            UnStakingFragment.newInstance(
                                selectedChain, zenrockValidator = zenrockValidator
                            )
                        )
                    }

                    else -> {
                        handleOneClickWithDelay(
                            UnStakingFragment.newInstance(
                                selectedChain, validator = validator
                            )
                        )
                    }
                }
            }

            switchValidatorLayout.setOnClickListener {
                when (selectedChain) {
                    is ChainInitia -> {
                        handleOneClickWithDelay(
                            ReDelegateFragment.newInstance(
                                selectedChain, initiaFromValidator = initiaValidator
                            )
                        )
                    }

                    is ChainZenrock -> {
                        handleOneClickWithDelay(
                            ReDelegateFragment.newInstance(
                                selectedChain, zenrockFromValidator = zenrockValidator
                            )
                        )
                    }

                    else -> {
                        handleOneClickWithDelay(
                            ReDelegateFragment.newInstance(
                                selectedChain, fromValidator = validator
                            )
                        )
                    }
                }
            }

            claimRewardsLayout.setOnClickListener {
                val claimableRewards: MutableList<DelegationDelegatorReward?> = mutableListOf()
                selectedChain.cosmosFetcher?.cosmosRewards?.firstOrNull {
                    when (selectedChain) {
                        is ChainInitia -> it.validatorAddress == initiaValidator?.operatorAddress

                        is ChainZenrock -> it.validatorAddress == zenrockValidator?.operatorAddress

                        else -> it.validatorAddress == validator?.operatorAddress
                    }
                }?.let { claimableReward ->
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
                        selectedChain, claimableRewards, false
                    )
                )
            }

            compoundingLayout.setOnClickListener {
                if (selectedChain.cosmosFetcher?.rewardAddress != selectedChain.address) {
                    requireContext().makeToast(R.string.error_reward_address_changed_msg)
                    return@setOnClickListener
                }
                val claimableRewards: MutableList<DelegationDelegatorReward?> = mutableListOf()
                selectedChain.cosmosFetcher?.cosmosRewards?.firstOrNull {
                    when (selectedChain) {
                        is ChainInitia -> it.validatorAddress == initiaValidator?.operatorAddress

                        is ChainZenrock -> it.validatorAddress == zenrockValidator?.operatorAddress

                        else -> it.validatorAddress == validator?.operatorAddress
                    }
                }?.let { claimableReward ->
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
                        selectedChain, claimableRewards, false
                    )
                )
            }

            unstakeCancelLayout.setOnClickListener {
                when (selectedChain) {
                    is ChainInitia -> {
                        handleOneClickWithDelay(
                            CancelUnBondingFragment.newInstance(
                                selectedChain, initiaUnBondingEntry = initiaUnBondingEntry
                            )
                        )
                    }

                    is ChainZenrock -> {
                        handleOneClickWithDelay(
                            CancelUnBondingFragment.newInstance(
                                selectedChain, zenrockUnBondingEntry = zenrockUnBondingEntry
                            )
                        )
                    }

                    else -> {
                        handleOneClickWithDelay(
                            CancelUnBondingFragment.newInstance(
                                selectedChain, unBondingEntry = unBondingEntry
                            )
                        )
                    }
                }
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