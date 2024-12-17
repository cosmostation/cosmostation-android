package wannabit.io.cosmostaion.ui.tx.info

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.res.CosmosProposal
import wannabit.io.cosmostaion.data.viewmodel.chain.ProposalViewModel
import wannabit.io.cosmostaion.databinding.FragmentOnChainProposalListBinding
import wannabit.io.cosmostaion.ui.tx.genTx.VoteFragment

class OnChainProposalListFragment : Fragment() {

    private var _binding: FragmentOnChainProposalListBinding? = null
    private val binding: FragmentOnChainProposalListBinding? get() = _binding

    private lateinit var selectedChain: BaseChain

    private val proposalViewModel: ProposalViewModel by activityViewModels()
    private lateinit var onChainProposalListAdapter: OnChainProposalListAdapter

    private var votingPeriods: MutableList<CosmosProposal> = mutableListOf()
    private var etcProposals: MutableList<CosmosProposal> = mutableListOf()
    private var toVoteList: MutableList<String>? = mutableListOf()

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): OnChainProposalListFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = OnChainProposalListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnChainProposalListBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        setUpProposalsData()
        setUpClickAction()
    }

    private fun initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("selectedChain", BaseChain::class.java)
                ?.let { selectedChain = it }

        } else {
            (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                selectedChain = it
            }
        }

        proposalViewModel.onChainProposalList(selectedChain)
    }

    private fun setUpProposalsData() {
        binding?.apply {
            proposalViewModel.proposalResult.observe(viewLifecycleOwner) { response ->
                votingPeriods.clear()
                etcProposals.clear()
                loading.visibility = View.GONE

                if (response?.isNotEmpty() == true) {
                    recycler.visibility = View.VISIBLE
                    emptyLayout.visibility = View.GONE
                    response.forEach { proposal ->
                        if (proposal.isVotingPeriod()) {
                            votingPeriods.add(proposal)
                        } else {
                            etcProposals.add(proposal)
                        }
                    }
                    initRecyclerView()

                } else {
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
        binding?.recycler?.apply {
            onChainProposalListAdapter = OnChainProposalListAdapter(
                requireContext(),
                selectedChain,
                votingPeriods,
                etcProposals,
                toVoteList,
                listener = proposalCheckAction
            )
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = onChainProposalListAdapter
        }
    }

    private val proposalCheckAction = object : OnChainProposalListAdapter.CheckListener {
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

            btnVote.setOnClickListener {
                val delegated = selectedChain.cosmosFetcher?.delegationAmountSum()
                val votingThreshold = selectedChain.votingThreshold()
                if (votingThreshold != null) {
                    if (votingThreshold > delegated) {
                        requireActivity().makeToast(R.string.error_no_bonding_no_vote)
                        return@setOnClickListener
                    }
                }

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