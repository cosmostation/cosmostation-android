package wannabit.io.cosmostaion.ui.tx.step.service

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.cosmos.auth.v1beta1.QueryGrpc
import com.cosmos.auth.v1beta1.QueryProto
import com.cosmos.base.abci.v1beta1.AbciProto
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.gov.v1beta1.GovProto
import com.cosmos.gov.v1beta1.TxProto.MsgVote
import com.cosmos.tx.v1beta1.ServiceGrpc
import com.cosmos.tx.v1beta1.ServiceProto
import com.cosmos.tx.v1beta1.ServiceProto.GetTxResponse
import com.cosmos.tx.v1beta1.ServiceProto.SimulateResponse
import com.cosmos.tx.v1beta1.TxProto
import io.grpc.ManagedChannel
import io.grpc.stub.StreamObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.concurrentForEach
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.res.CosmosProposal
import wannabit.io.cosmostaion.data.model.res.VoteData
import wannabit.io.cosmostaion.data.repository.chain.ProposalRepositoryImpl
import wannabit.io.cosmostaion.databinding.FragmentAllChainVoteBinding
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.step.BaseTxFragment
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.chain.ProposalViewModel
import wannabit.io.cosmostaion.ui.viewmodel.chain.ProposalViewModelProviderFactory
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AllChainVoteFragment : BaseTxFragment() {

    private var _binding: FragmentAllChainVoteBinding? = null
    private val binding: FragmentAllChainVoteBinding? get() = _binding

    private lateinit var proposalViewModel: ProposalViewModel

    private val stakedChains = mutableListOf<BaseChain>()
    private var isShowAll = false

    private var allLiveInfo = mutableListOf<VoteAllModel>()
    private var allDisplayLiveInfo = mutableListOf<VoteAllModel>()
    private var toDisplayInfos = mutableListOf<VoteAllModel>()

    private lateinit var allChainNotVoteAdapter: AllChainNotVoteAdapter
    private var allChainNotVoteTouchAdapter: AllChainNotVoteTouchAdapter? = null

    private lateinit var allChainAllVoteAdapter: AllChainAllVoteAdapter
    private var allChainAllVoteTouchAdapter: AllChainAllVoteTouchAdapter? = null

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
                    if (account.sortedDisplayChains().none { !it.fetched }) {
                        account.sortedDisplayChains()
                            .filter { !it.isTestnet && it.isDefault && tag != "finschia438" }
                            .forEach { chain ->
                                val delegated =
                                    chain.grpcFetcher?.delegationAmountSum() ?: BigDecimal.ZERO
                                val voteThreshold = chain.voteThreshold()
                                val txFee = chain.getInitPayableFee(requireContext())
                                if (delegated > voteThreshold && txFee != null) {
                                    stakedChains.add(chain)
                                }
                            }
                    }
                }
                allLiveInfo.clear()
                allDisplayLiveInfo.clear()
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
                    val address = chain.address
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

            withContext(Dispatchers.Main) {
                if (stakedChains.isEmpty()) {
                    updateView()
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
            voteExplain.visibility = View.VISIBLE
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

            allLiveInfo.forEach { voteModel ->
                voteModel.proposals.forEach { it.toVoteOption = null }
            }
            allDisplayLiveInfo.clear()
            toDisplayInfos.clear()

            allDisplayLiveInfo.addAll(allLiveInfo.map { it.deepCopy() })
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

            if (isShowAll) {
                btnFilter.setImageResource(R.drawable.icon_all_vote)
                allVoteTitle.text = getString(R.string.title_not_vote_list)
                recycler.visibility = View.GONE

                if (allDisplayLiveInfo.isEmpty()) {
                    emptyLayout.visibility = View.VISIBLE
                    allRecycler.visibility = View.GONE
                    btnVoteAll.visibility = View.GONE
                } else {
                    allRecycler.visibility = View.VISIBLE
                    btnVoteAll.visibility = View.VISIBLE
                    btnVoteAll.updateButtonView(false)
                }

            } else {
                btnFilter.setImageResource(R.drawable.icon_not_vote)
                allVoteTitle.text = getString(R.string.title_all_vote_list)
                allRecycler.visibility = View.GONE

                if (toDisplayInfos.isEmpty()) {
                    emptyLayout.visibility = View.VISIBLE
                    recycler.visibility = View.GONE
                    btnVoteAll.visibility = View.GONE
                } else {
                    recycler.visibility = View.VISIBLE
                    btnVoteAll.visibility = View.VISIBLE
                    btnVoteAll.updateButtonView(false)
                }
            }

            initRecyclerView()
            allInitRecyclerView()
        }
    }

    private fun initRecyclerView() {
        binding?.recycler?.apply {
            allChainNotVoteAdapter = AllChainNotVoteAdapter(checkProposal)
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = allChainNotVoteAdapter
            allChainNotVoteAdapter.submitList(toDisplayInfos)

            if (allChainNotVoteTouchAdapter == null) {
                allChainNotVoteTouchAdapter =
                    AllChainNotVoteTouchAdapter(toDisplayInfos) { model, proposal ->
                        deleteUpdateView(model, proposal)
                    }.apply {
                        setClamp(resources.displayMetrics.widthPixels.toFloat() / 6)
                    }
            }

            allChainNotVoteTouchAdapter?.let { touchAdapter ->
                val itemTouchHelper = ItemTouchHelper(touchAdapter)
                itemTouchHelper.attachToRecyclerView(this)
            }

            allChainNotVoteAdapter.setOnItemClickListener { model, proposal ->
                allChainNotVoteTouchAdapter?.initializeCurrentPosition(this)
                deleteUpdateView(model, proposal)
            }

            setOnTouchListener { _, _ ->
                allChainNotVoteTouchAdapter?.removePreviousClamp(this)
                false
            }
        }
    }

    private fun allInitRecyclerView() {
        binding?.allRecycler?.apply {
            allChainAllVoteAdapter = AllChainAllVoteAdapter(checkProposal)
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = allChainAllVoteAdapter
            allChainAllVoteAdapter.submitList(allDisplayLiveInfo)

            if (allChainAllVoteTouchAdapter == null) {
                allChainAllVoteTouchAdapter = AllChainAllVoteTouchAdapter(
                    allDisplayLiveInfo
                ) { model, proposal ->
                    deleteUpdateView(model, proposal)
                }.apply {
                    setClamp(resources.displayMetrics.widthPixels.toFloat() / 6)
                }
            }

            allChainAllVoteTouchAdapter?.let { touchAdapter ->
                val itemTouchHelper = ItemTouchHelper(touchAdapter)
                itemTouchHelper.attachToRecyclerView(this)
            }

            allChainAllVoteAdapter.setOnItemClickListener { model, proposal ->
                allChainAllVoteTouchAdapter?.initializeCurrentPosition(this)
                deleteUpdateView(model, proposal)
            }

            setOnTouchListener { _, _ ->
                allChainAllVoteTouchAdapter?.removePreviousClamp(this)
                false
            }
        }
    }

    private val checkProposal = object : CheckListener {
        override fun proposalNotVoteCheck(
            voteModel: VoteAllModel, proposal: CosmosProposal, tag: Int
        ) {
            val voteOption = GovProto.VoteOption.forNumber(tag)
            if (voteOption == proposal.toVoteOption) return
            if (voteModel.isBusy) return
            if (voteModel.txResponse != null) return
            when (tag) {
                1 -> proposal.toVoteOption = GovProto.VoteOption.VOTE_OPTION_YES
                2 -> proposal.toVoteOption = GovProto.VoteOption.VOTE_OPTION_ABSTAIN
                3 -> proposal.toVoteOption = GovProto.VoteOption.VOTE_OPTION_NO
                4 -> proposal.toVoteOption = GovProto.VoteOption.VOTE_OPTION_NO_WITH_VETO
                else -> proposal.toVoteOption = null
            }

            Handler(Looper.getMainLooper()).postDelayed({
                allChainNotVoteAdapter.notifyDataSetChanged()
                txSimulate(voteModel)
            }, 80)
        }

        override fun proposalAllVoteCheck(
            voteModel: VoteAllModel, proposal: CosmosProposal, tag: Int
        ) {
            val voteOption = GovProto.VoteOption.forNumber(tag)
            if (voteOption == proposal.toVoteOption) return
            if (voteModel.isBusy) return
            if (voteModel.txResponse != null) return
            when (tag) {
                1 -> proposal.toVoteOption = GovProto.VoteOption.VOTE_OPTION_YES
                2 -> proposal.toVoteOption = GovProto.VoteOption.VOTE_OPTION_ABSTAIN
                3 -> proposal.toVoteOption = GovProto.VoteOption.VOTE_OPTION_NO
                4 -> proposal.toVoteOption = GovProto.VoteOption.VOTE_OPTION_NO_WITH_VETO
                else -> proposal.toVoteOption = null
            }

            Handler(Looper.getMainLooper()).postDelayed({
                allChainAllVoteAdapter.notifyDataSetChanged()
                txSimulate(voteModel)
            }, 80)
        }
    }

    private fun deleteUpdateView(model: VoteAllModel?, proposal: CosmosProposal?) {
        if (isShowAll) {
            model?.let { voteAllModel ->
                if (voteAllModel.isBusy) return
                if (voteAllModel.txResponse != null) return
                val proposals = allDisplayLiveInfo.firstOrNull { it == voteAllModel }?.proposals
                proposals?.remove(proposal)

                if (proposals?.isEmpty() == true) {
                    allDisplayLiveInfo.remove(voteAllModel)
                } else {
                    txSimulate(voteAllModel)
                }
                allChainAllVoteAdapter.submitList(allDisplayLiveInfo)
                allChainAllVoteAdapter.notifyDataSetChanged()

                binding?.apply {
                    if (allDisplayLiveInfo.isEmpty()) {
                        allRecycler.visibility = View.GONE
                        emptyLayout.visibility = View.VISIBLE
                        btnVoteAll.updateButtonView(false)
                    }
                }
            }

        } else {
            model?.let { voteModel ->
                if (voteModel.isBusy) return
                if (voteModel.txResponse != null) return
                val proposals = toDisplayInfos.firstOrNull { it == voteModel }?.proposals
                proposals?.remove(proposal)

                if (proposals?.isEmpty() == true) {
                    toDisplayInfos.remove(voteModel)
                } else {
                    txSimulate(voteModel)
                }
                allChainNotVoteAdapter.submitList(toDisplayInfos)
                allChainNotVoteAdapter.notifyDataSetChanged()

                binding?.apply {
                    if (toDisplayInfos.isEmpty()) {
                        recycler.visibility = View.GONE
                        emptyLayout.visibility = View.VISIBLE
                        btnVoteAll.updateButtonView(false)
                    }
                }
            }
        }
    }

    private fun txSimulate(voteAllModel: VoteAllModel) {
        if (voteAllModel.proposals.any { it.toVoteOption == null }) {
            return
        }

        lifecycleScope.launch(Dispatchers.IO) {
            voteAllModel.onClear()
            voteAllModel.basechain?.let { chain ->
                val txFee = chain.getInitPayableFee(requireContext())
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
                    if (isShowAll) {
                        if (::allChainAllVoteAdapter.isInitialized) {
                            allChainAllVoteAdapter.notifyDataSetChanged()
                        }
                    } else {
                        if (::allChainNotVoteAdapter.isInitialized) {
                            allChainNotVoteAdapter.notifyDataSetChanged()
                        }
                    }
                }
                simulateVoteTx(chain, toVotes)?.let { response ->
                    withContext(Dispatchers.Main) {
                        updateFeeViewWithSimulate(voteAllModel, toVotes, txFee, response.gasInfo)
                    }
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding?.apply {
            btnFilter.setOnClickListener {
                if (isShowAll) {
                    if (allDisplayLiveInfo.count { it.isBusy } > 0) return@setOnClickListener
                    if (allDisplayLiveInfo.count { it.txResponse != null } > 0) return@setOnClickListener
                } else {
                    if (toDisplayInfos.count { it.isBusy } > 0) return@setOnClickListener
                    if (toDisplayInfos.count { it.txResponse != null } > 0) return@setOnClickListener
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

            btnConfirm.setOnClickListener {
                BaseData.baseAccount?.let { account ->
                    account.sortedDisplayChains().forEach {
                        it.fetched = false
                    }
                    account.sortedDisplayChains().asSequence().concurrentForEach { chain ->
                        ApplicationViewModel.shared.loadChainData(
                            chain, account.id, false
                        )
                    }
                }
                dismiss()
            }
        }
    }

    private fun updateFeeViewWithSimulate(
        voteAllModel: VoteAllModel,
        toVotes: MutableList<MsgVote?>?,
        txFee: TxProto.Fee?,
        gasInfo: AbciProto.GasInfo?
    ) {
        lifecycleScope.launch(Dispatchers.IO) {
            gasInfo?.let { info ->
                voteAllModel.basechain?.let { chain ->
                    val gasLimit =
                        (info.gasUsed.toDouble() * chain.gasMultiply()).toLong().toBigDecimal()
                    chain.getBaseFeeInfo(requireContext()).feeDatas.firstOrNull {
                        it.denom == txFee?.getAmount(
                            0
                        )?.denom
                    }?.let { gasRate ->
                        val feeCoinAmount =
                            gasRate.gasRate?.multiply(gasLimit)?.setScale(0, RoundingMode.UP)
                        val feeCoin =
                            CoinProto.Coin.newBuilder().setDenom(txFee?.getAmount(0)?.denom)
                                .setAmount(feeCoinAmount.toString()).build()

                        voteAllModel.isBusy = false
                        voteAllModel.txFee = TxProto.Fee.newBuilder().setGasLimit(gasLimit.toLong())
                            .addAmount(feeCoin).build()
                        voteAllModel.toVotes = toVotes
                    }
                }
            }

            withContext(Dispatchers.Main) {
                if (isShowAll) {
                    if (::allChainAllVoteAdapter.isInitialized) {
                        allChainAllVoteAdapter.notifyDataSetChanged()
                    }

                    if (allDisplayLiveInfo.none { it.toVotes?.isEmpty() == true || it.txFee == null }) {
                        binding?.btnVoteAll?.updateButtonView(true)
                    }

                } else {
                    if (::allChainNotVoteAdapter.isInitialized) {
                        allChainNotVoteAdapter.notifyDataSetChanged()
                    }

                    if (toDisplayInfos.none { it.toVotes?.isEmpty() == true || it.txFee == null }) {
                        binding?.btnVoteAll?.updateButtonView(true)
                    }
                }
            }
        }
    }

    private val voteAllResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK && isAdded) {
            binding?.apply {
                btnVoteAll.visibility = View.GONE
                btnConfirm.visibility = View.VISIBLE

                if (isShowAll) {
                    for (i in allDisplayLiveInfo.indices) {
                        allDisplayLiveInfo[i].isBusy = true
                    }
                    allChainAllVoteAdapter.notifyDataSetChanged()

                    for (i in 0 until allDisplayLiveInfo.size) {
                        val voteAllModel = allDisplayLiveInfo[i]
                        broadCastVoteTx(voteAllModel) {
                            voteAllModel.basechain?.let { chain ->
                                val channel = getChannel(chain)
                                checkTx(voteAllModel, channel, it?.txResponse)
                            }
                        }
                    }

                } else {
                    for (i in toDisplayInfos.indices) {
                        toDisplayInfos[i].isBusy = true
                    }
                    allChainNotVoteAdapter.notifyDataSetChanged()

                    for (i in 0 until toDisplayInfos.size) {
                        val voteAllModel = toDisplayInfos[i]
                        broadCastVoteTx(voteAllModel) {
                            voteAllModel.basechain?.let { chain ->
                                val channel = getChannel(chain)
                                checkTx(voteAllModel, channel, it?.txResponse)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun simulateVoteTx(
        chain: BaseChain, toVotes: MutableList<MsgVote?>?
    ): SimulateResponse? {
        val channel = getChannel(chain)
        return try {
            loadAuth(channel, chain.address)?.let {
                val simulStub =
                    ServiceGrpc.newBlockingStub(channel).withDeadlineAfter(8L, TimeUnit.SECONDS)
                val simulateTx = Signer.genVoteSimulate(
                    it, toVotes, chain.getInitPayableFee(requireContext()), "", chain
                )
                simulStub.simulate(simulateTx)
            }
        } catch (e: Exception) {
            null
        }
        return null
    }

    private fun broadCastVoteTx(
        voteAllModel: VoteAllModel, onComplete: (ServiceProto.BroadcastTxResponse?) -> Unit
    ) {
        lifecycleScope.launch(Dispatchers.IO) {
            voteAllModel.basechain?.let { chain ->
                val toVotes = voteAllModel.toVotes
                val txFee = voteAllModel.txFee
                val channel = getChannel(chain)

                val txStub =
                    ServiceGrpc.newBlockingStub(channel).withDeadlineAfter(8L, TimeUnit.SECONDS)
                val broadcastTx = Signer.genVoteBroadcast(
                    loadAuth(channel, chain.address), toVotes, txFee, "", chain
                )
                try {
                    onComplete(txStub.broadcastTx(broadcastTx))
                } catch (e: Exception) {
                }
            }
        }
    }

    private fun checkTx(
        voteAllModel: VoteAllModel,
        managedChannel: ManagedChannel,
        txResponse: AbciProto.TxResponse?
    ) {
        lifecycleScope.launch(Dispatchers.IO) {
            loadTx(managedChannel, txResponse?.txhash) { response ->
                voteAllModel.isBusy = false
                voteAllModel.txResponse = response
                lifecycleScope.launch(Dispatchers.Main) {
                    if (isShowAll) {
                        if (::allChainAllVoteAdapter.isInitialized) {
                            allChainAllVoteAdapter.notifyDataSetChanged()
                            if (allDisplayLiveInfo.none { it.txResponse == null }) {
                                binding?.btnConfirm?.updateButtonView(true)
                            }
                        }

                    } else {
                        if (::allChainNotVoteAdapter.isInitialized) {
                            allChainNotVoteAdapter.notifyDataSetChanged()
                            if (toDisplayInfos.none { it.txResponse == null }) {
                                binding?.btnConfirm?.updateButtonView(true)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun loadAuth(
        managedChannel: ManagedChannel, address: String?
    ): QueryProto.QueryAccountResponse? {
        val stub = QueryGrpc.newBlockingStub(managedChannel).withDeadlineAfter(8L, TimeUnit.SECONDS)
        val request = QueryProto.QueryAccountRequest.newBuilder().setAddress(address).build()
        return try {
            stub.account(request)
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun loadTx(
        managedChannel: ManagedChannel, txHash: String?, onComplete: (GetTxResponse?) -> Unit
    ) {
        try {
            val stub = ServiceGrpc.newStub(managedChannel)
            val request = ServiceProto.GetTxRequest.newBuilder().setHash(txHash).build()

            val response = withContext(Dispatchers.IO) {
                suspendCoroutine { continuation ->
                    val observer = object : StreamObserver<GetTxResponse> {
                        override fun onNext(value: GetTxResponse?) {
                            continuation.resume(value)
                        }

                        override fun onError(t: Throwable?) {
                            continuation.resumeWithException(t ?: RuntimeException("Unknown error"))
                        }

                        override fun onCompleted() {}
                    }
                    stub.getTx(request, observer)
                }
            }
            onComplete(response)
        } catch (e: Throwable) {
            delay(3000)
            loadTx(managedChannel, txHash, onComplete)
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

fun VoteAllModel.deepCopy(): VoteAllModel {
    return this.copy(
        proposals = this.proposals.map { proposal ->
            proposal.copy()
        }.toMutableList()
    )
}