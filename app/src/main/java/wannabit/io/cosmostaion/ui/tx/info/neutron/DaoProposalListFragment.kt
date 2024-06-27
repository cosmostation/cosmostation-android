package wannabit.io.cosmostaion.ui.tx.info.neutron

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayout
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.data.model.res.ResDaoVoteStatus
import wannabit.io.cosmostaion.databinding.FragmentDaoProposalListBinding
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.chain.ProposalViewModel

class DaoProposalListFragment : Fragment() {

    private var _binding: FragmentDaoProposalListBinding? = null
    private val binding get() = _binding!!

//    private lateinit var selectedChain: ChainNeutron

//    private lateinit var daoProposalPagerAdapter: DaoProposalPagerAdapter

    private val proposalViewModel: ProposalViewModel by activityViewModels()

    private var neutronMyVotes: MutableList<ResDaoVoteStatus>? = mutableListOf()

    private var isShowAll = false

    companion object {
//        @JvmStatic
//        fun newInstance(selectedChain: ChainNeutron): DaoProposalListFragment {
//            val args = Bundle().apply {
//                putParcelable("selectedChain", selectedChain)
//            }
//            val fragment = DaoProposalListFragment()
//            fragment.arguments = args
//            return fragment
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDaoProposalListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initTab()
        setUpMyVoteStatus()
        setUpClickAction()
    }

    private fun initData() {
        binding.apply {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                arguments?.apply {
//                    getParcelable("selectedChain", ChainNeutron::class.java)?.let {
//                        selectedChain = it
//                    }
//                }
//
//            } else {
//                arguments?.apply {
//                    (getParcelable("selectedChain") as? ChainNeutron)?.let {
//                        selectedChain = it
//                    }
//                }
//            }
//            proposalViewModel.daoMyVoteStatus(selectedChain.apiName, selectedChain.address)
        }
    }

    private fun initTab() {
        binding.apply {
//            daoProposalPagerAdapter = DaoProposalPagerAdapter(
//                requireActivity(), selectedChain, neutronMyVotes
//            )
//            viewPager.adapter = daoProposalPagerAdapter
//            viewPager.offscreenPageLimit = 2
//            viewPager.isUserInputEnabled = false
//            tabLayout.bringToFront()
//
//            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//                tab.text = when (position) {
//                    0 -> "Single"
//                    1 -> "Multiple"
//                    else -> "Overrule"
//                }
//            }.attach()

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val position = tab?.position ?: 0
                    viewPager.setCurrentItem(position, false)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }

    private fun setUpMyVoteStatus() {
        proposalViewModel.voteDaoStatusResult.observe(viewLifecycleOwner) { voteStatus ->
            voteStatus?.let { neutronMyVotes = voteStatus }
            initTab()
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            btnFilter.setOnClickListener {
                isShowAll = !isShowAll
                if (isShowAll) {
                    btnFilter.setImageResource(R.drawable.icon_not_filter)
                    requireActivity().makeToast(R.string.str_show_all_proposals_msg)
                } else {
                    btnFilter.setImageResource(R.drawable.icon_filter)
                    requireActivity().makeToast(R.string.str_hide_scam_proposals)
                }
                ApplicationViewModel.shared.updateFilterData(isShowAll)
            }
        }
    }

//    class DaoProposalPagerAdapter(
//        fragmentActivity: FragmentActivity,
//        selectedChain: ChainNeutron,
//        neutronMyVotes: MutableList<ResDaoVoteStatus>?
//    ) : FragmentStateAdapter(fragmentActivity) {
//        private val fragments = mutableListOf<Fragment>()
//
//        init {
//            fragments.add(DaoSingleFragment.newInstance(selectedChain, neutronMyVotes))
//            fragments.add(DaoMultipleFragment.newInstance(selectedChain, neutronMyVotes))
//            fragments.add(DaoOverruleFragment.newInstance(selectedChain, neutronMyVotes))
//        }
//
//        override fun getItemCount(): Int {
//            return fragments.size
//        }
//
//        override fun createFragment(position: Int): Fragment {
//            return fragments[position]
//        }
//
//        override fun getItemId(position: Int): Long {
//            return position.toLong()
//        }
//    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}