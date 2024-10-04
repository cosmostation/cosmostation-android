package wannabit.io.cosmostaion.ui.tx.info

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cosmos.staking.v1beta1.StakingProto
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.parcelize.Parcelize
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.databinding.FragmentStakeInfoBinding
import wannabit.io.cosmostaion.ui.tx.genTx.StakingFragment

class StakeInfoFragment : Fragment() {

    private var _binding: FragmentStakeInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain

    private lateinit var stakingPagerAdapter: StakingPagerAdapter

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): StakeInfoFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = StakeInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("selectedChain", BaseChain::class.java)
                    ?.let { selectedChain = it }

            } else {
                (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                    selectedChain = it
                }
            }

            stakingPagerAdapter = StakingPagerAdapter(
                requireActivity(), selectedChain
            )
            viewPager.adapter = stakingPagerAdapter
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

            btnStake.setOnClickListener {
//                if (selectedChain is ChainBeraEvm) {
//                    EvmStakingFragment.newInstance(selectedChain as EthereumLine, null).show(
//                        requireActivity().supportFragmentManager,
//                        EvmStakingFragment::class.java.name
//                    )
//                } else {
                StakingFragment.newInstance(selectedChain, null).show(
                    requireActivity().supportFragmentManager, StakingFragment::class.java.name
                )
//                }
            }
        }
    }

    class StakingPagerAdapter(
        fragmentActivity: FragmentActivity, selectedChain: BaseChain
    ) : FragmentStateAdapter(fragmentActivity) {
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

@Parcelize
data class UnBondingEntry(
    val validatorAddress: String?, val entry: StakingProto.UnbondingDelegationEntry?
) : Parcelable

enum class OptionType { STAKE, UNSTAKE }