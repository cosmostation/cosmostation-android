package wannabit.io.cosmostaion.ui.tx.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.res.ProposalData
import wannabit.io.cosmostaion.data.model.res.ProposalModule
import wannabit.io.cosmostaion.data.repository.chain.ProposalRepositoryImpl
import wannabit.io.cosmostaion.databinding.FragmentDaoListBinding
import wannabit.io.cosmostaion.ui.tx.step.neutron.DaoVoteFragment
import wannabit.io.cosmostaion.ui.viewmodel.chain.ProposalViewModel
import wannabit.io.cosmostaion.ui.viewmodel.chain.ProposalViewModelProviderFactory

class DaoListFragment(private val selectedChain: ChainNeutron) : Fragment() {

    private var _binding: FragmentDaoListBinding? = null
    private val binding get() = _binding!!

    private lateinit var proposalViewModel: ProposalViewModel
    private lateinit var daoListAdapter: DaoListAdapter

    private var daoProposals: List<Pair<String?, ProposalData?>> = mutableListOf()
    private var modules: MutableList<ProposalModule?>? = mutableListOf()
    private var toVoteSingle: MutableList<String?> = mutableListOf()
    private var toVoteMulti: MutableList<String?> = mutableListOf()

    private var singleProposals: MutableList<Pair<String?, ProposalData?>> = mutableListOf()
    private var multiProposals: MutableList<Pair<String?, ProposalData?>> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDaoListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initRecyclerView()
        modules = selectedChain.daoList?.get(0)?.proposal_modules
        val contAddressList: MutableList<String?> = mutableListOf()
        modules?.forEach { proposalModule ->
            contAddressList.add(proposalModule?.address)
        }
        proposalViewModel.daoProposalList(getChannel(), contAddressList)
        clickAction()
    }

    private fun initViewModel() {
        val proposalRepository = ProposalRepositoryImpl()
        val proposalViewModelProviderFactory = ProposalViewModelProviderFactory(proposalRepository)
        proposalViewModel = ViewModelProvider(this, proposalViewModelProviderFactory)[ProposalViewModel::class.java]
    }

    private fun initRecyclerView() {
        binding.apply {
            btnVote.updateButtonView(false)

            proposalViewModel.daoProposalResult.observe(viewLifecycleOwner) { proposals ->
                proposals?.let { proposalPairs ->
                    daoProposals = proposalPairs
                    proposalViewModel.daoMyVoteStatus(selectedChain.apiName, selectedChain.address)
                }
            }

            proposalViewModel.voteDaoStatusResult.observe(viewLifecycleOwner) { voteStatus ->
                voteStatus?.let {
                    loading.visibility = View.GONE
                    recycler.visibility = View.VISIBLE

                    daoListAdapter =
                        DaoListAdapter(requireContext(), selectedChain, modules, daoProposals, it, daoProposalCheckAction)
                    recycler.setHasFixedSize(true)
                    recycler.layoutManager = LinearLayoutManager(requireContext())
                    recycler.adapter = daoListAdapter
                }
            }
        }
    }

    private val daoProposalCheckAction = object : DaoListAdapter.CheckListener {
        override fun daoProposalCheck(isChecked: Boolean, contAddress: String?, module: String?, proposalId: String?) {
            if (module == "Single Module") {
                if (isChecked && !toVoteSingle.contains(proposalId)) {
                    toVoteSingle.add(proposalId)
                } else if (!isChecked && toVoteSingle.contains(proposalId)) {
                    toVoteSingle.indexOf(proposalId).let { index ->
                        if (index != -1) {
                            toVoteSingle.removeAt(index)
                        }
                    }
                }
                singleProposals = daoProposals.filter { toVoteSingle.contains(it.second?.id) && contAddress == it.first }.toMutableList()

            } else {
                if (isChecked && !toVoteMulti.contains(proposalId)) {
                    toVoteMulti.add(proposalId)
                } else if (!isChecked && toVoteMulti.contains(proposalId)) {
                    toVoteMulti.indexOf(proposalId).let { index ->
                        if (index != -1) {
                            toVoteMulti.removeAt(index)
                        }
                    }
                }
                multiProposals = daoProposals.filter { toVoteMulti.contains(it.second?.id) && contAddress == it.first }.toMutableList()
            }
            binding.btnVote.updateButtonView(toVoteSingle.isNotEmpty() || toVoteMulti.isNotEmpty())
        }
    }

    private fun clickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            btnVote.setOnClickListener {
                if (!selectedChain.isTxFeePayable(requireContext())) {
                    requireContext().makeToast(R.string.error_not_enough_fee)
                    return@setOnClickListener
                }
                singleProposals.forEach { it.second?.myVote = null }
                multiProposals.forEach { it.second?.myVote = null }
                val bottomSheet = DaoVoteFragment(selectedChain, singleProposals, multiProposals)
                bottomSheet.show(requireActivity().supportFragmentManager, DaoVoteFragment::class.java.name)
            }
        }
    }

    private fun getChannel(): ManagedChannel {
        return ManagedChannelBuilder.forAddress(selectedChain.grpcHost, selectedChain.grpcPort).useTransportSecurity().build()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}