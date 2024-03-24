package wannabit.io.cosmostaion.ui.tx.step

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.cosmos.gov.v1beta1.TxProto.MsgVote
import com.cosmos.tx.v1beta1.ServiceProto.GetTxResponse
import com.cosmos.tx.v1beta1.TxProto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.concurrentForEach
import wannabit.io.cosmostaion.data.model.res.CosmosProposal
import wannabit.io.cosmostaion.data.model.res.VoteData
import wannabit.io.cosmostaion.data.repository.chain.ProposalRepositoryImpl
import wannabit.io.cosmostaion.data.repository.tx.TxRepositoryImpl
import wannabit.io.cosmostaion.databinding.FragmentAllChainVoteBinding
import wannabit.io.cosmostaion.ui.viewmodel.chain.ProposalViewModel
import wannabit.io.cosmostaion.ui.viewmodel.chain.ProposalViewModelProviderFactory
import wannabit.io.cosmostaion.ui.viewmodel.tx.TxViewModel
import wannabit.io.cosmostaion.ui.viewmodel.tx.TxViewModelProviderFactory

class AllChainVoteFragment : BaseTxFragment() {

    private var _binding: FragmentAllChainVoteBinding? = null
    private val binding: FragmentAllChainVoteBinding? get() = _binding

    private lateinit var viewModel: TxViewModel
    private lateinit var proposalViewModel: ProposalViewModel

    private val stakedChains = mutableListOf<BaseChain>()
    private var isShowAll = false
    private var allLiveInfo = mutableListOf<VoteAllModel>()
    private var toDisplayInfos = mutableListOf<VoteAllModel>()

    private lateinit var allChainClaimAdapter: AllChainClaimAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllChainVoteBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initView()
        setUpViewModels()
        setUpClickAction()
    }

    private fun initViewModel() {
        val txRepository = TxRepositoryImpl()
        val txViewModelProviderFactory = TxViewModelProviderFactory(txRepository)
        viewModel = ViewModelProvider(
            this, txViewModelProviderFactory
        )[TxViewModel::class.java]

        val proposalRepository = ProposalRepositoryImpl()
        val proposalViewModelProviderFactory = ProposalViewModelProviderFactory(proposalRepository)
        proposalViewModel = ViewModelProvider(
            this, proposalViewModelProviderFactory
        )[ProposalViewModel::class.java]
    }

    private fun initView() {
        binding?.apply {
            lifecycleScope.launch(Dispatchers.IO) {
                BaseData.baseAccount?.let { account ->
                    if (account.sortedDisplayEvmLines()
                            .none { !it.fetched } && account.sortedDisplayCosmosLines()
                            .none { !it.fetched }
                    ) {
                        account.sortedDisplayEvmLines().filter { it.supportCosmos }
                            .forEach { chain ->
                                val delegated = chain.delegationAmountSum()
                                val voteThreshold = chain.voteThreshold()
                                val txFee = chain.getInitPayableFee(requireContext())
                                if (delegated >= voteThreshold.toBigDecimal() && txFee != null) {
                                    stakedChains.add(chain)
                                }
                            }

                        account.sortedDisplayCosmosLines().filter { it.isDefault }
                            .forEach { chain ->
                                val delegated = chain.delegationAmountSum()
                                val voteThreshold = chain.voteThreshold()
                                val txFee = chain.getInitPayableFee(requireContext())
                                if (delegated >= voteThreshold.toBigDecimal() && txFee != null) {
                                    stakedChains.add(chain)
                                }
                            }
                    }
                }
                allLiveInfo.clear()
                toDisplayInfos.clear()
                stakedChains.asSequence().concurrentForEach { chain ->
//                    proposalViewModel.proposalChainList(chain)
                }
            }
        }
    }

    private fun setUpViewModels() {
        var progress = 0
        val toShowProposals = mutableListOf<CosmosProposal>()
        val myVotes = mutableListOf<VoteData>()

        proposalViewModel.proposalChainResult.observe(viewLifecycleOwner) { response ->
            lifecycleScope.launch(Dispatchers.IO) {
                response.second?.forEach { proposal ->
                    if (proposal.isVotingPeriod() && !proposal.isScam()) {
                        toShowProposals.add(proposal)
                    }

                    if (toShowProposals.isNotEmpty()) {
                        val address = (response.first as CosmosLine).address
                        proposalViewModel.voteChainStatus(response.first, address)
                    }
                }
            }
        }

        proposalViewModel.voteChainStatusResult.observe(viewLifecycleOwner) { response ->
            lifecycleScope.launch(Dispatchers.IO) {
                response.second?.votes?.forEach { vote ->
                    myVotes.add(vote)
                }
                allLiveInfo.add(VoteAllModel(response.first, toShowProposals, myVotes))
                progress += 1

                withContext(Dispatchers.Main) {
                    updateProgress(progress, stakedChains.size)
                }
            }
        }
    }

    private fun updateProgress(progress: Int, allCnt: Int) {
        binding?.checked?.text = "Checked " + progress + "/" + allCnt
    }

    private fun setUpClickAction() {
        binding.apply {

        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

data class VoteAllModel(
    var basechain: BaseChain?,
    var proposals: MutableList<CosmosProposal>,
    var myVotes: MutableList<VoteData>,
    var toVotes: MutableList<MsgVote>? = null,
    var txFee: TxProto.Fee? = null,
    var txResponse: GetTxResponse? = null,
    var isBusy: Boolean = false
) {

    fun onClear() {
        toVotes = emptyList<MsgVote>().toMutableList()
        txFee = null
        txResponse = null
        isBusy = true
    }
}