package wannabit.io.cosmostaion.ui.tx.step

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cosmos.base.abci.v1beta1.AbciProto
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.gov.v1beta1.GovProto
import com.cosmos.gov.v1beta1.TxProto.MsgVote
import com.cosmos.tx.v1beta1.ServiceProto.GetTxResponse
import com.cosmos.tx.v1beta1.TxProto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.concurrentForEach
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.res.CosmosProposal
import wannabit.io.cosmostaion.data.model.res.VoteData
import wannabit.io.cosmostaion.data.repository.chain.ProposalRepositoryImpl
import wannabit.io.cosmostaion.data.repository.tx.TxRepositoryImpl
import wannabit.io.cosmostaion.databinding.FragmentAllChainVoteBinding
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.viewmodel.chain.ProposalViewModel
import wannabit.io.cosmostaion.ui.viewmodel.chain.ProposalViewModelProviderFactory
import wannabit.io.cosmostaion.ui.viewmodel.tx.TxViewModel
import wannabit.io.cosmostaion.ui.viewmodel.tx.TxViewModelProviderFactory
import java.math.RoundingMode

class AllChainVoteFragment : BaseTxFragment() {

    private var _binding: FragmentAllChainVoteBinding? = null
    private val binding: FragmentAllChainVoteBinding? get() = _binding

    private lateinit var viewModel: TxViewModel
    private lateinit var proposalViewModel: ProposalViewModel

    private val stakedChains = mutableListOf<BaseChain>()
    private var isShowAll = false
    private var allLiveInfo = mutableListOf<VoteAllModel>()
    private var toDisplayInfos = mutableListOf<VoteAllModel>()

    private lateinit var allChainVoteAdapter: AllChainVoteAdapter

    private var txFee: TxProto.Fee? = null

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
                fetchProposalInfos(stakedChains)
            }
        }
    }

    private fun fetchProposalInfos(stakedChains: MutableList<BaseChain>) {
        lifecycleScope.launch(Dispatchers.IO) {
            var progress = 0
            stakedChains.asSequence().concurrentForEach { chain ->
                val toShowProposals = mutableListOf<CosmosProposal>()
                RetrofitInstance.mintscanApi.cosmosProposal(chain.apiName).body()
                    ?.let { proposals ->
                        proposals.forEach { proposal ->
                            if (proposal.isVotingPeriod() && !proposal.isScam()) {
                                toShowProposals.add(proposal)
                            }
                        }
                    }

                if (toShowProposals.isNotEmpty()) {
                    val address = (chain as CosmosLine).address
                    val myVotes = mutableListOf<VoteData>()
                    RetrofitInstance.mintscanApi.voteStatus(chain.apiName, address).body()
                        ?.let { votes ->
                            votes.votes.forEach { vote ->
                                myVotes.add(vote)
                            }
                        }
                    allLiveInfo.add(VoteAllModel(chain, toShowProposals, myVotes))
                }
                progress += 1
                withContext(Dispatchers.Main) {
                    updateProgress(progress, stakedChains.size)

                    if (progress == stakedChains.size) {
                        updateView()
                    }
                }
            }
        }
    }

    private fun updateProgress(progress: Int, allCnt: Int) {
        binding?.checked?.text = "Checked " + progress + "/" + allCnt
    }

    private fun updateView() {
        binding?.apply {
            loading.visibility = View.GONE
            checked.visibility = View.GONE
            btnLayout.visibility = View.VISIBLE
            titleLayout.visibility = View.VISIBLE
            emptyLayout.visibility = View.GONE

            allLiveInfo.sortWith { o1, o2 ->
                when {
                    o1.basechain?.tag == "cosmos118" -> -1
                    o2.basechain?.tag == "cosmos118" -> 1
                    o1.basechain?.tag == "govgen118" -> -1
                    o2.basechain?.tag == "govgen118" -> 1
                    else -> 0
                }
            }

            toDisplayInfos.clear()
            if (isShowAll) {
                btnFilter.setImageResource(R.drawable.icon_all_vote)
                allVoteTitle.text = getString(R.string.title_all_vote_list)

                toDisplayInfos = allLiveInfo.map { it }.toMutableList()

            } else {
                btnFilter.setImageResource(R.drawable.icon_not_vote)
                allVoteTitle.text = getString(R.string.title_not_vote_list)

                allLiveInfo.forEach { info ->
                    val filteredProposal = mutableListOf<CosmosProposal>()
                    val proposals = info.proposals
                    val myVotes = info.myVotes
                    proposals.forEach { proposal ->
                        if (myVotes.none { it.proposal_id == proposal.id }) {
                            filteredProposal.add(proposal)
                        }
                    }
                    if (filteredProposal.isNotEmpty()) {
                        toDisplayInfos.add(VoteAllModel(info.basechain, filteredProposal, myVotes))
                    }
                }

                if (toDisplayInfos.isEmpty()) {
                    emptyLayout.visibility = View.VISIBLE
                } else {
                    btnVoteAll.visibility = View.VISIBLE
                }
                recycler.visibility = View.VISIBLE
            }
            initRecyclerView()
        }
    }

    private fun initRecyclerView() {
        binding?.recycler?.apply {
            allChainVoteAdapter = AllChainVoteAdapter(checkProposal)
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = allChainVoteAdapter
            allChainVoteAdapter.submitList(toDisplayInfos)
        }
    }

    private val checkProposal = object : AllChainVoteAdapter.CheckListener {
        override fun proposalCheck(voteModel: VoteAllModel, proposal: CosmosProposal, tag: Int) {
            val voteOption = GovProto.VoteOption.forNumber(tag)
            if (voteOption == proposal.toVoteOption) {
                return
            }
            if (voteModel.isBusy) {
                return
            }
            if (voteModel.txResponse != null) {
                return
            }
            when (tag) {
                1 -> {
                    proposal.toVoteOption = GovProto.VoteOption.VOTE_OPTION_YES
                }

                2 -> {
                    proposal.toVoteOption = GovProto.VoteOption.VOTE_OPTION_ABSTAIN
                }

                3 -> {
                    proposal.toVoteOption = GovProto.VoteOption.VOTE_OPTION_NO
                }

                4 -> {
                    proposal.toVoteOption = GovProto.VoteOption.VOTE_OPTION_NO_WITH_VETO
                }

                else -> {
                    proposal.toVoteOption = null
                }
            }
            Handler(Looper.getMainLooper()).postDelayed({
                requireActivity().runOnUiThread {
                    allChainVoteAdapter.notifyDataSetChanged()
                    txSimulate(voteModel)
                }
            }, 80)
        }
    }

    private fun txSimulate(voteAllModel: VoteAllModel) {
        if (voteAllModel.proposals.any { it.toVoteOption == null }) {
            return
        }

        lifecycleScope.launch(Dispatchers.IO) {
            voteAllModel.onClear()
            val chain = voteAllModel.basechain as CosmosLine
            txFee = chain.getInitPayableFee(requireContext())
            val toVotes = mutableListOf<MsgVote?>()
            voteAllModel.proposals.forEach { proposal ->
                val voteMsg = proposal.id?.let { id ->
                    MsgVote.newBuilder().setVoter(chain.address).setProposalId(id.toLong())
                        .setOption(proposal.toVoteOption).build()
                }
                toVotes.add(voteMsg)
            }

            voteAllModel.isBusy = true
            withContext(Dispatchers.Main) {
                val index = toDisplayInfos.indexOf(voteAllModel)
                if (::allChainVoteAdapter.isInitialized) {
                    allChainVoteAdapter.notifyItemChanged(index)
                }
                viewModel.simulateVote(
                    getChannel(chain), chain.address, toVotes, txFee, "", chain
                )
                setUpSimulate(voteAllModel, toVotes)
            }
        }
    }

    private fun setUpClickAction() {
        binding?.apply {
            btnFilter.setOnClickListener {
                if (toDisplayInfos.count { it.isBusy } > 0) {
                    return@setOnClickListener
                }
                if (toDisplayInfos.count { it.txResponse != null } > 0) {
                    return@setOnClickListener
                }
                isShowAll = !isShowAll
                if (isShowAll) {
                    requireActivity().makeToast(R.string.str_show_all_proposals_msg)
                } else {
                    requireActivity().makeToast(R.string.str_hide_voted_proposals_msg)
                }
                updateView()
            }

            btnVoteAll.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    voteAllResultLauncher.launch(this)
                    requireActivity().overridePendingTransition(
                        R.anim.anim_slide_in_bottom, R.anim.anim_fade_out
                    )
                }
            }
        }
    }

    private fun setUpSimulate(voteAllModel: VoteAllModel, toVotes: MutableList<MsgVote?>) {
        viewModel.simulate.observe(viewLifecycleOwner) { gasInfo ->
            updateFeeViewWithSimulate(voteAllModel, toVotes, gasInfo)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { response ->
            binding?.btnVoteAll?.updateButtonView(false)
            requireContext().showToast(view, response, true)
            return@observe
        }
    }

    private fun updateFeeViewWithSimulate(
        voteAllModel: VoteAllModel, toVotes: MutableList<MsgVote?>, gasInfo: AbciProto.GasInfo?
    ) {
        lifecycleScope.launch(Dispatchers.IO) {
            gasInfo?.let { info ->
                val gasLimit =
                    (info.gasUsed.toDouble() * (voteAllModel.basechain as CosmosLine).gasMultiply()).toLong()
                        .toBigDecimal()
                (voteAllModel.basechain as CosmosLine).getBaseFeeInfo(requireContext()).feeDatas.firstOrNull {
                    it.denom == txFee?.getAmount(
                        0
                    )?.denom
                }?.let { gasRate ->
                    val feeCoinAmount =
                        gasRate.gasRate?.multiply(gasLimit)?.setScale(0, RoundingMode.UP)
                    val feeCoin = CoinProto.Coin.newBuilder().setDenom(txFee?.getAmount(0)?.denom)
                        .setAmount(feeCoinAmount.toString()).build()

                    voteAllModel.isBusy = false
                    voteAllModel.txFee =
                        TxProto.Fee.newBuilder().setGasLimit(gasLimit.toLong()).addAmount(feeCoin)
                            .build()
                    voteAllModel.toVotes = toVotes
                }
            }
            withContext(Dispatchers.Main) {
                val index = toDisplayInfos.indexOf(voteAllModel)
                Log.e("Test1234 : ", index.toString())
                if (::allChainVoteAdapter.isInitialized) {
                    allChainVoteAdapter.notifyItemChanged(index)
                }

                if (toDisplayInfos.none { it.toVotes?.isEmpty() == true || it.txFee == null }) {
                    binding?.btnVoteAll?.updateButtonView(true)
                }
            }
        }
    }

    private val voteAllResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK && isAdded) {
//            for (i in 0 until valueAbleRewards.size) {
//                valueAbleRewards[i].isSuccess = true
//            }
//            allChainClaimAdapter.notifyDataSetChanged()
//            binding?.btnClaimAll?.visibility = View.GONE
//            binding?.btnConfirm?.visibility = View.VISIBLE
//
//            for (i in 0 until valueAbleRewards.size) {
//                val valueAbleReward = valueAbleRewards[i]
//                broadCastClaimTx(valueAbleReward) {
//                    val channel = getChannel(valueAbleReward.cosmosLine)
//                    checkTx(i, channel, it?.txResponse)
//                }
//            }
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
    var toVotes: MutableList<MsgVote?>? = null,
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