package wannabit.io.cosmostaion.ui.tx.info

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.res.CosmosProposal
import wannabit.io.cosmostaion.data.model.res.VoteData
import wannabit.io.cosmostaion.databinding.FragmentProposalListBinding
import wannabit.io.cosmostaion.ui.tx.step.VoteFragment
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.chain.ProposalViewModel

class ProposalListFragment : Fragment() {

    private var _binding: FragmentProposalListBinding? = null
    private val binding: FragmentProposalListBinding? get() = _binding

    private lateinit var selectedChain: CosmosLine

    private val proposalViewModel: ProposalViewModel by activityViewModels()
    private lateinit var proposalListAdapter: ProposalListAdapter

    private var isShowAll = false

    private val proposals: MutableList<CosmosProposal> = mutableListOf()
    private var filteredProposals: MutableList<CosmosProposal> = mutableListOf()
    private var votingPeriods: MutableList<CosmosProposal> = mutableListOf()
    private var myVotes: MutableList<VoteData> = mutableListOf()
    private var toVoteList: MutableList<String>? = mutableListOf()

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: CosmosLine): ProposalListFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = ProposalListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProposalListBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        setUpProposalsData()
        setUpClickAction()
        setUpVoteInfo()
    }

    private fun initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("selectedChain", CosmosLine::class.java)
                ?.let { selectedChain = it }

        } else {
            (arguments?.getParcelable("selectedChain") as? CosmosLine)?.let {
                selectedChain = it
            }
        }

        proposalViewModel.proposalList(selectedChain.apiName)
    }

    private fun setUpProposalsData() {
        binding?.apply {
            proposalViewModel.proposalResult.observe(viewLifecycleOwner) { response ->
                proposals.clear()
                filteredProposals.clear()
                votingPeriods.clear()

                if (response?.isNotEmpty() == true) {
                    proposalViewModel.voteStatus(selectedChain.apiName, selectedChain.address)
                    proposals.addAll(response)
                    filteredProposals = proposals.filter { !it.isScam() }.toMutableList()
                    votingPeriods = proposals.filter { it.isVotingPeriod() }.toMutableList()
                    initRecyclerView()

                } else {
                    loading.visibility = View.GONE
                    recycler.visibility = View.GONE
                    emptyLayout.visibility = View.VISIBLE
                }
            }

            proposalViewModel.errorMessage.observe(viewLifecycleOwner) {
                loading.visibility = View.GONE
                recycler.visibility = View.GONE
                emptyLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun initRecyclerView() {
        binding?.apply {
            proposalViewModel.voteStatusResult.observe(viewLifecycleOwner) { voteStatus ->
                if (filteredProposals.isEmpty()) {
                    loading.visibility = View.GONE
                    recycler.visibility = View.GONE
                    emptyLayout.visibility = View.VISIBLE

                } else {
                    if (myVotes.isEmpty()) {
                        loading.visibility = View.GONE
                        recycler.visibility = View.VISIBLE
                        voteStatus?.votes?.forEach { vote ->
                            myVotes.add(vote)
                        }

                        proposalListAdapter = ProposalListAdapter(
                            requireContext(),
                            selectedChain,
                            myVotes,
                            toVoteList,
                            listener = proposalCheckAction
                        )
                        recycler.setHasFixedSize(true)
                        recycler.layoutManager = LinearLayoutManager(requireContext())
                        recycler.adapter = proposalListAdapter
                        proposalListAdapter.submitList(filteredProposals)
                        proposalListAdapter.filterProposals()

                    } else {
                        myVotes.clear()
                        voteStatus?.votes?.forEach { vote ->
                            myVotes.add(vote)
                        }
                        if (isShowAll) {
                            btnFilter.setImageResource(R.drawable.icon_not_filter)
                            proposalListAdapter.submitList(proposals) {
                                proposalListAdapter.filterProposals()
                                proposalListAdapter.notifyDataSetChanged()
                            }
                        } else {
                            btnFilter.setImageResource(R.drawable.icon_filter)
                            proposalListAdapter.submitList(filteredProposals) {
                                proposalListAdapter.filterProposals()
                                proposalListAdapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            }
        }

    }

    private val proposalCheckAction = object : ProposalListAdapter.CheckListener {
        override fun proposalCheck(isChecked: Boolean, proposalId: String) {
            if (isChecked && toVoteList?.contains(proposalId) == false) {
                toVoteList?.add(proposalId)

            } else if (!isChecked && toVoteList?.contains(proposalId) == true) {
                toVoteList?.indexOf(proposalId)?.let { index ->
                    if (index != -1) {
                        toVoteList?.removeAt(index)
                    }
                }
            }
            toVoteList?.let {
                binding?.btnVote?.updateButtonView(it.isNotEmpty())
            }
        }
    }

    private fun setUpClickAction() {
        binding?.apply {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            btnFilter.setOnClickListener {
                isShowAll = !isShowAll
                if (proposals.isNotEmpty()) {
                    emptyLayout.visibility = View.GONE
                    recycler.visibility = View.VISIBLE

                    if (isShowAll) {
                        btnFilter.setImageResource(R.drawable.icon_not_filter)
                        requireActivity().makeToast(R.string.str_show_all_proposals_msg)
                        proposalListAdapter.submitList(proposals) {
                            proposalListAdapter.filterProposals()
                            proposalListAdapter.notifyDataSetChanged()
                        }

                    } else {
                        btnFilter.setImageResource(R.drawable.icon_filter)
                        requireActivity().makeToast(R.string.str_hide_scam_proposals)
                        proposalListAdapter.submitList(filteredProposals) {
                            proposalListAdapter.filterProposals()
                            proposalListAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }

            btnVote.setOnClickListener {
                val toVoteProposals: MutableList<CosmosProposal> = mutableListOf()
                votingPeriods.forEach { proposal ->
                    proposal.toVoteOption = null
                    if (toVoteList?.contains(proposal.id) == true) {
                        toVoteProposals.add(proposal)
                    }
                }
                VoteFragment.newInstance(selectedChain, toVoteProposals).show(
                    requireActivity().supportFragmentManager, VoteFragment::class.java.name
                )
            }
        }
    }

    private fun setUpVoteInfo() {
        ApplicationViewModel.shared.fetchedVoteResult.observe(viewLifecycleOwner) {
            proposalViewModel.voteStatus(selectedChain.apiName, selectedChain.address)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}