package wannabit.io.cosmostaion.ui.tx.info.neutron

import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.databinding.FragmentNeutronStakeInfoBinding
import wannabit.io.cosmostaion.ui.tx.genTx.ClaimRewardFragment
import wannabit.io.cosmostaion.ui.tx.genTx.StakingFragment
import wannabit.io.cosmostaion.ui.tx.genTx.neutron.ContractCompoundingFragment
import wannabit.io.cosmostaion.ui.tx.info.StakingInfoFragment
import wannabit.io.cosmostaion.ui.tx.info.UnBondingEntry
import wannabit.io.cosmostaion.ui.tx.info.UnStakingInfoFragment
import java.math.BigDecimal
import java.math.RoundingMode

class NeutronStakeInfoFragment : Fragment() {

    private var _binding: FragmentNeutronStakeInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain

    private lateinit var neutronStakingPagerAdapter: NeutronStakingPagerAdapter

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): NeutronStakeInfoFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = NeutronStakeInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNeutronStakeInfoBinding.inflate(layoutInflater, container, false)
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
                arguments?.getParcelable("selectedChain", BaseChain::class.java)
                    ?.let { selectedChain = it }

            } else {
                (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                    selectedChain = it
                }
            }

            BaseData.getAsset(selectedChain.apiName, selectedChain.getStakeAssetDenom())
                ?.let { asset ->
                    titleManageStake.text = getString(R.string.title_manage_stake, asset.symbol)
                    stakeCoinImg.setTokenImg(asset)

                    rewardAmount.text = formatAmount(
                        (selectedChain as ChainNeutron).neutronFetcher()?.neutronRewards?.movePointLeft(
                            asset.decimals ?: 6
                        )?.setScale(6, RoundingMode.DOWN).toString(), asset.decimals ?: 6
                    )
                    rewardDenom.text = asset.symbol
                    txView.setBackgroundResource(R.drawable.item_bg)

                    claimImg.setColorFilter(
                        ContextCompat.getColor(requireContext(), R.color.color_base02),
                        PorterDuff.Mode.SRC_IN
                    )

                    compoundImg.setColorFilter(
                        ContextCompat.getColor(requireContext(), R.color.color_base02),
                        PorterDuff.Mode.SRC_IN
                    )
                }

            neutronStakingPagerAdapter = NeutronStakingPagerAdapter(
                this@NeutronStakeInfoFragment, selectedChain
            )
            viewPager.adapter = neutronStakingPagerAdapter
            viewPager.offscreenPageLimit = 1
            viewPager.isUserInputEnabled = false
            tabLayout.bringToFront()

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> "Staking"
                    else -> "Unstaking"
                }
            }.attach()

            lifecycleScope.launch(Dispatchers.IO) {
                val delegations = selectedChain.cosmosFetcher?.cosmosDelegations ?: mutableListOf()
                val unBondings =
                    selectedChain.cosmosFetcher?.cosmosUnbondings?.flatMap { unBonding ->
                        unBonding.entriesList.map { entry ->
                            UnBondingEntry(unBonding.validatorAddress, entry)
                        }
                    }?.sortedBy { it.entry?.creationHeight }?.toMutableList() ?: mutableListOf()

                withContext(Dispatchers.Main) {
                    if (delegations.isNotEmpty() || unBondings.isNotEmpty()) {
                        emptyStake.visibility = View.GONE
                        stakingDataView.visibility = View.VISIBLE

                    } else {
                        emptyStake.visibility = View.VISIBLE
                        stakingDataView.visibility = View.GONE
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

            btnClaimAll.setOnClickListener {
                if (((selectedChain as ChainNeutron).neutronFetcher()?.neutronRewards
                        ?: BigDecimal.ZERO) <= BigDecimal.ZERO
                ) {
                    requireContext().makeToast(R.string.error_not_reward)
                    return@setOnClickListener
                }

                if (!selectedChain.isTxFeePayable(requireContext())) {
                    requireContext().showToast(
                        view, R.string.error_not_enough_fee, false
                    )
                    return@setOnClickListener
                }

                handleOneClickWithDelay(
                    ClaimRewardFragment.newInstance(
                        selectedChain, selectedChain.cosmosFetcher?.claimableRewards(), false
                    )
                )
            }

            btnCompoundingAll.setOnClickListener {
                if (((selectedChain as ChainNeutron).neutronFetcher()?.neutronRewards
                        ?: BigDecimal.ZERO) <= BigDecimal.ZERO
                ) {
                    requireContext().makeToast(R.string.error_not_reward)
                    return@setOnClickListener
                }

                if (!selectedChain.isTxFeePayable(requireContext())) {
                    requireContext().showToast(
                        view, R.string.error_not_enough_fee, false
                    )
                    return@setOnClickListener
                }

                handleOneClickWithDelay(
                    ContractCompoundingFragment.newInstance(selectedChain)
                )
            }

            btnStake.setOnClickListener {
                StakingFragment.newInstance(selectedChain).show(
                    requireActivity().supportFragmentManager, StakingFragment::class.java.name
                )
            }
        }
    }

    private fun handleOneClickWithDelay(bottomSheetDialogFragment: BottomSheetDialogFragment) {
        if (isClickable) {
            isClickable = false

            bottomSheetDialogFragment.show(
                parentFragmentManager, bottomSheetDialogFragment::class.java.name
            )

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 300)
        }
    }

    class NeutronStakingPagerAdapter(
        fragment: Fragment, selectedChain: BaseChain
    ) : FragmentStateAdapter(fragment) {
        private val fragments = mutableListOf<Fragment>()

        init {
            fragments.add(StakingInfoFragment.newInstance(selectedChain))
            fragments.add(UnStakingInfoFragment.newInstance(selectedChain))
        }

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}