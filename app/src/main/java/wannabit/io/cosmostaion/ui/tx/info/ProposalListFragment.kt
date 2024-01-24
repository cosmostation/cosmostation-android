package wannabit.io.cosmostaion.ui.tx.info

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.res.CosmosProposal
import wannabit.io.cosmostaion.data.model.res.VoteData
import wannabit.io.cosmostaion.data.repository.chain.ProposalRepositoryImpl
import wannabit.io.cosmostaion.databinding.FragmentProposalListBinding
import wannabit.io.cosmostaion.ui.tx.step.VoteFragment
import wannabit.io.cosmostaion.ui.viewmodel.chain.ProposalViewModel
import wannabit.io.cosmostaion.ui.viewmodel.chain.ProposalViewModelProviderFactory

class ProposalListFragment : Fragment() {

    private var _binding: FragmentProposalListBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: CosmosLine

    private lateinit var proposalViewModel: ProposalViewModel
    private lateinit var proposalListAdapter: ProposalListAdapter

    private var isShowAll = false
    private val votingPeriods: MutableList<CosmosProposal> = mutableListOf()
    private val etcPeriods: MutableList<CosmosProposal> = mutableListOf()
    private var filterVotingPeriods: MutableList<CosmosProposal> = mutableListOf()
    private var filterEtcPeriods: MutableList<CosmosProposal> = mutableListOf()
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initRecyclerView()
        setUpClickAction()
    }

    private fun initViewModel() {
        val proposalRepository = ProposalRepositoryImpl()
        val proposalViewModelProviderFactory = ProposalViewModelProviderFactory(proposalRepository)
        proposalViewModel =
            ViewModelProvider(this, proposalViewModelProviderFactory)[ProposalViewModel::class.java]

        initData()
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

    private fun initRecyclerView() {
        binding.apply {
            btnVote.updateButtonView(false)
            proposalViewModel.proposalResult.observe(viewLifecycleOwner) { proposals ->
                votingPeriods.clear()
                etcPeriods.clear()
                filterVotingPeriods.clear()
                filterEtcPeriods.clear()

                if (proposals?.isNotEmpty() == true) {
                    proposalViewModel.voteStatus(selectedChain.apiName, selectedChain.address)
                    proposals.forEach { proposal ->
                        if (proposal.isVotingPeriod()) {
                            votingPeriods.add(proposal)
                            if (!proposal.isScam()) {
                                filterVotingPeriods.add(proposal)
                            }
                        } else {
                            etcPeriods.add(proposal)
                            if (!proposal.isScam()) {
                                filterEtcPeriods.add(proposal)
                            }
                        }
                    }

                } else {
                    recycler.visibility = View.GONE
                    emptyLayout.visibility = View.VISIBLE
                }
            }

            proposalViewModel.voteStatusResult.observe(viewLifecycleOwner) { voteStatus ->
                Handler(Looper.getMainLooper()).postDelayed({
                    loading.visibility = View.GONE
                    recycler.visibility = View.VISIBLE
                    emptyLayout.visibility = View.GONE
                    voteStatus?.votes?.forEach { vote ->
                        myVotes.add(vote)
                    }
                    updateRecyclerView(filterVotingPeriods, filterEtcPeriods)
                }, 1000)
            }
        }
    }

    private fun updateRecyclerView(
        votingPeriods: MutableList<CosmosProposal>, etcPeriods: MutableList<CosmosProposal>
    ) {
        binding.recycler.apply {
            proposalListAdapter = ProposalListAdapter(
                requireContext(),
                selectedChain,
                votingPeriods,
                etcPeriods,
                myVotes,
                toVoteList,
                listener = proposalCheckAction
            )
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = proposalListAdapter
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
                binding.btnVote.updateButtonView(it.isNotEmpty())
            }
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
                    updateRecyclerView(votingPeriods, etcPeriods)
                } else {
                    btnFilter.setImageResource(R.drawable.icon_filter)
                    updateRecyclerView(filterVotingPeriods, filterEtcPeriods)
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}