package wannabit.io.cosmostaion.ui.tx.info.neutron

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.NEUTRON_SINGLE_MODULE
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.res.ProposalData
import wannabit.io.cosmostaion.data.model.res.ResDaoVoteStatus
import wannabit.io.cosmostaion.data.repository.chain.ProposalRepositoryImpl
import wannabit.io.cosmostaion.databinding.FragmentDaoBinding
import wannabit.io.cosmostaion.ui.tx.genTx.neutron.DaoVoteFragment
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.data.viewmodel.chain.ProposalViewModel
import wannabit.io.cosmostaion.data.viewmodel.chain.ProposalViewModelProviderFactory

class DaoSingleFragment : Fragment() {

    private var _binding: FragmentDaoBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: ChainNeutron
    private var neutronMyVotes: MutableList<ResDaoVoteStatus>? = mutableListOf()

    private lateinit var proposalViewModel: ProposalViewModel

    private lateinit var daoProposalListAdapter: DaoProposalListAdapter

    private val proposals: MutableList<ProposalData?> = mutableListOf()
    private var filteredProposals: MutableList<ProposalData?> = mutableListOf()
    private var votingPeriods: MutableList<ProposalData?> = mutableListOf()

    private var toVoteSingle: MutableList<String?> = mutableListOf()

    companion object {
        @JvmStatic
        fun newInstance(
            selectedChain: ChainNeutron, neutronMyVotes: MutableList<ResDaoVoteStatus>?
        ): DaoSingleFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putParcelableArrayList("neutronMyVotes", neutronMyVotes?.let { ArrayList(it) })
            }
            val fragment = DaoSingleFragment()
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

        initViewModel()
        initData()
        setUpClickAction()
        updateFilterList()
    }

    private fun initViewModel() {
        val proposalRepository = ProposalRepositoryImpl()
        val proposalViewModelProviderFactory = ProposalViewModelProviderFactory(proposalRepository)
        proposalViewModel =
            ViewModelProvider(this, proposalViewModelProviderFactory)[ProposalViewModel::class.java]
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

        selectedChain.getChainListParam()?.getAsJsonArray("daos")
            ?.get(0)?.asJsonObject?.getAsJsonArray("proposal_modules")
            ?.get(0)?.asJsonObject?.get("address")?.asString?.let { contAddress ->
                proposalViewModel.daoProposals(
                    selectedChain, contAddress, NEUTRON_SINGLE_MODULE
                )
            }
        setUpProposalData()
    }

    private fun setUpProposalData() {
        binding.apply {
            proposalViewModel.daoSingleProposalsResult.observe(viewLifecycleOwner) { response ->
                lifecycleScope.launch(Dispatchers.IO) {
                    response?.let { proposalData ->
                        proposals.clear()
                        filteredProposals.clear()
                        votingPeriods.clear()

                        proposals.addAll(response)
                        filteredProposals = proposals.filter {
                            val title = it?.proposal?.title?.lowercase()
                            title?.contains("airdrop") == false && !title.containsEmoji()
                        }.toMutableList()
                        votingPeriods =
                            proposalData.filter { "open" == it?.proposal?.status }.toMutableList()
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
            if (filteredProposals.isEmpty()) {
                emptyLayout.visibility = View.VISIBLE
            } else {
                recycler.visibility = View.VISIBLE
                initRecyclerView()
            }
            btnVote.updateButtonView(false)
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            daoProposalListAdapter = DaoProposalListAdapter(
                selectedChain,
                NEUTRON_SINGLE_MODULE,
                neutronMyVotes,
                listener = daoProposalCheckAction
            )
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = daoProposalListAdapter
            daoProposalListAdapter.submitList(filteredProposals)
            daoProposalListAdapter.filterProposals()
        }
    }

    private fun updateFilterList() {
        ApplicationViewModel.shared.filterDataResult.observe(viewLifecycleOwner) { isShowAll ->
            if (proposals.isNotEmpty()) {
                binding.emptyLayout.visibility = View.GONE
                binding.recycler.visibility = View.VISIBLE

                if (isShowAll) {
                    daoProposalListAdapter.submitList(proposals) {
                        daoProposalListAdapter.filterProposals()
                        daoProposalListAdapter.notifyDataSetChanged()
                    }
                } else {
                    daoProposalListAdapter.submitList(filteredProposals) {
                        daoProposalListAdapter.filterProposals()
                        daoProposalListAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    private val daoProposalCheckAction = object : DaoProposalListAdapter.CheckListener {
        override fun daoProposalCheck(isChecked: Boolean, proposalId: String?) {
            if (isChecked && !toVoteSingle.contains(proposalId)) {
                toVoteSingle.add(proposalId)

            } else if (!isChecked && toVoteSingle.contains(proposalId)) {
                toVoteSingle.indexOf(proposalId).let { index ->
                    if (index != -1) {
                        toVoteSingle.removeAt(index)
                    }
                }
            }
            binding.btnVote.updateButtonView(toVoteSingle.isNotEmpty())
        }
    }

    private fun setUpClickAction() {
        binding.btnVote.setOnClickListener {
            val toSingleProposals =
                votingPeriods.filter { toVoteSingle.contains(it?.id) }.toMutableList()
            toSingleProposals.forEach { it?.myVote = null }

            DaoVoteFragment.newInstance(
                selectedChain, toSingleProposals, mutableListOf(), mutableListOf()
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

fun String.containsEmoji(): Boolean {
    for (char in this) {
        if (Character.isSurrogate(char)) {
            return true
        } else if (Character.isHighSurrogate(char)) {
            val nextIndex = this.indexOfFirst { Character.isLowSurrogate(it) }
            if (nextIndex != -1) {
                return true
            }
        }
    }
    return false
}