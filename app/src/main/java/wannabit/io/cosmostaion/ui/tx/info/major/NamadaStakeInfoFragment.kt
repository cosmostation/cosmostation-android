package wannabit.io.cosmostaion.ui.tx.info.major

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.databinding.FragmentStakeInfoBinding
import wannabit.io.cosmostaion.ui.tx.genTx.StakingFragment

class NamadaStakeInfoFragment : Fragment() {

    private var _binding: FragmentStakeInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain

    private lateinit var namadaStakingPagerAdapter: NamadaStakingPagerAdapter

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): NamadaStakeInfoFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = NamadaStakeInfoFragment()
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

            namadaStakingPagerAdapter = NamadaStakingPagerAdapter(
                this@NamadaStakeInfoFragment, selectedChain
            )
            viewPager.adapter = namadaStakingPagerAdapter
            viewPager.offscreenPageLimit = 1
            viewPager.isUserInputEnabled = false
            tabLayout.bringToFront()

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> "Staking"
                    else -> "Unstaking"
                }
            }.attach()
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            btnStake.setOnClickListener {
                StakingFragment.newInstance(selectedChain, null, null).show(
                    requireActivity().supportFragmentManager, StakingFragment::class.java.name
                )
            }
        }
    }

    class NamadaStakingPagerAdapter(
        fragment: Fragment, selectedChain: BaseChain
    ) : FragmentStateAdapter(fragment) {
        private val fragments = mutableListOf<Fragment>()

        init {
            fragments.add(NamadaBondingInfoFragment.newInstance(selectedChain))
//            fragments.add(UnStakingInfoFragment.newInstance(selectedChain))
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