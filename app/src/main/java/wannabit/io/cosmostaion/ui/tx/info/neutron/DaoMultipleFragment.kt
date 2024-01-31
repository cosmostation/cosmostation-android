package wannabit.io.cosmostaion.ui.tx.info.neutron

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.NEUTRON_MULTI_MODULE
import wannabit.io.cosmostaion.chain.cosmosClass.NEUTRON_SINGLE_MODULE
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.res.ProposalData
import wannabit.io.cosmostaion.data.model.res.ResDaoVoteStatus
import wannabit.io.cosmostaion.databinding.FragmentCoinBinding
import wannabit.io.cosmostaion.databinding.FragmentDaoBinding
import wannabit.io.cosmostaion.ui.tx.step.neutron.DaoVoteFragment
import wannabit.io.cosmostaion.ui.viewmodel.chain.ProposalViewModel

class DaoMultipleFragment : Fragment() {

    private var _binding: FragmentDaoBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: ChainNeutron
    private var neutronMyVotes: MutableList<ResDaoVoteStatus>? = mutableListOf()

    private val proposalViewModel: ProposalViewModel by activityViewModels()

    private lateinit var daoProposalListAdapter: DaoProposalListAdapter

    private var votingPeriods: MutableList<ProposalData?> = mutableListOf()
    private var etcPeriods: MutableList<ProposalData?> = mutableListOf()
    private var filterVotingPeriods: MutableList<ProposalData?> = mutableListOf()
    private var filterEtcPeriods: MutableList<ProposalData?> = mutableListOf()

    private var toVoteMultiple: MutableList<String?> = mutableListOf()

    companion object {
        @JvmStatic
        fun newInstance(
            selectedChain: CosmosLine, neutronMyVotes: MutableList<ResDaoVoteStatus>?
        ): DaoMultipleFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putParcelableArrayList("neutronMyVotes", neutronMyVotes?.let { ArrayList(it) })
            }
            val fragment = DaoMultipleFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDaoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        setUpClickAction()
        updateFilterList()
    }

    private fun initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("selectedChain", ChainNeutron::class.java)
                ?.let { selectedChain = it }
        } else {
            (arguments?.getParcelable("selectedChain") as? ChainNeutron)?.let {
                selectedChain = it
            }
        }
        neutronMyVotes = arguments?.getParcelableArrayList("neutronMyVotes")

        selectedChain.param?.params?.chainlistParams?.daos?.get(0)?.proposal_modules?.get(1)?.address?.let { contAddress ->
            proposalViewModel.daoProposals(
                getChannel(selectedChain), contAddress, NEUTRON_MULTI_MODULE
            )
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.apply {
            proposalViewModel.daoMultipleProposalsResult.observe(viewLifecycleOwner) { response ->
                lifecycleScope.launch(Dispatchers.IO) {
                    response?.let { proposalData ->
                        votingPeriods.clear()
                        etcPeriods.clear()
                        filterVotingPeriods.clear()
                        filterEtcPeriods.clear()

                        votingPeriods =
                            proposalData.filter { "open" == it?.proposal?.status }.toMutableList()
                        etcPeriods =
                            proposalData.filter { "open" != it?.proposal?.status }.toMutableList()

                        filterVotingPeriods = votingPeriods.filter {
                            val title = it?.proposal?.title?.lowercase()
                            title?.contains("airdrop") == false && !title.containsEmoji()
                        }.toMutableList()
                        filterEtcPeriods = etcPeriods.filter {
                            val title = it?.proposal?.title?.lowercase()
                            title?.contains("airdrop") == false && !title.containsEmoji()
                        }.toMutableList()
                    }

                    withContext(Dispatchers.Main) {
                        updateView()
                    }
                }
            }
        }
    }

    private fun updateView() {
        binding.apply {
            loading.visibility = View.GONE
            if (filterVotingPeriods.isEmpty() && filterEtcPeriods.isEmpty()) {
                emptyLayout.visibility = View.VISIBLE
            } else {
                recycler.visibility = View.VISIBLE
                updateRecyclerView(filterVotingPeriods, filterEtcPeriods)
            }
            btnVote.updateButtonView(false)
        }
    }

    private fun updateRecyclerView(
        votingPeriods: MutableList<ProposalData?>, etcPeriods: MutableList<ProposalData?>
    ) {
        binding.recycler.apply {
            daoProposalListAdapter = DaoProposalListAdapter(
                selectedChain,
                NEUTRON_MULTI_MODULE,
                votingPeriods,
                etcPeriods,
                neutronMyVotes,
                daoProposalCheckAction
            )
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = daoProposalListAdapter
        }
    }

    private fun updateFilterList() {
        proposalViewModel.filterData.observe(viewLifecycleOwner) { isShowAll ->
            if (isShowAll) {
                updateRecyclerView(votingPeriods, etcPeriods)
            } else {
                updateRecyclerView(filterVotingPeriods, filterEtcPeriods)
            }
        }
    }

    private val daoProposalCheckAction = object : DaoProposalListAdapter.CheckListener {
        override fun daoProposalCheck(isChecked: Boolean, proposalId: String?) {
            if (isChecked && !toVoteMultiple.contains(proposalId)) {
                toVoteMultiple.add(proposalId)

            } else if (!isChecked && toVoteMultiple.contains(proposalId)) {
                toVoteMultiple.indexOf(proposalId).let { index ->
                    if (index != -1) {
                        toVoteMultiple.removeAt(index)
                    }
                }
            }
            binding.btnVote.updateButtonView(toVoteMultiple.isNotEmpty())
        }
    }

    private fun setUpClickAction() {
        binding.btnVote.setOnClickListener {
            val toMultipleProposals =
                votingPeriods.filter { toVoteMultiple.contains(it?.id) }.toMutableList()
            toMultipleProposals.forEach { it?.myVote = null }

            DaoVoteFragment.newInstance(
                selectedChain, mutableListOf(), toMultipleProposals, mutableListOf()
            ).show(
                requireActivity().supportFragmentManager, DaoVoteFragment::class.java.name
            )
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}