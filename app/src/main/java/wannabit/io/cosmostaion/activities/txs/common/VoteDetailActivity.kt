package wannabit.io.cosmostaion.activities.txs.common

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.common.collect.Sets
import com.google.gson.Gson
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.databinding.ActivityVoteDetailsBinding
import wannabit.io.cosmostaion.databinding.ItemVoteInfoBinding
import wannabit.io.cosmostaion.databinding.ItemVoteMessageBinding
import wannabit.io.cosmostaion.databinding.ItemVoteTallyInfoBinding
import wannabit.io.cosmostaion.model.viewModel.VoteViewModel
import wannabit.io.cosmostaion.network.res.ResProposal
import wannabit.io.cosmostaion.network.res.ResVoteStatus
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.visibleOrGone
import java.math.BigDecimal
import java.util.*
import java.util.regex.Pattern

class VoteDetailActivity : BaseActivity() {

    private lateinit var voteDetailsBinding: ActivityVoteDetailsBinding
    private val voteViewModel: VoteViewModel by viewModels()

    private val mProposalId: String
        get() {
            return intent.getStringExtra("proposalId") ?: ""
        }

    private var mProposal: ResProposal? = null
    private val selectedSet: MutableSet<ResProposal> = Sets.newHashSet()
    private var mMyVoteData: ResVoteStatus.VotesData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        voteDetailsBinding = ActivityVoteDetailsBinding.inflate(layoutInflater)
        setContentView(voteDetailsBinding.root)
        initView()
        onObserve()
        onFetchProposal()

        onClick()
    }

    private fun initView() {
        setSupportActionBar(voteDetailsBinding.toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        voteDetailsBinding.recycler.layoutManager = LinearLayoutManager(this)
        voteDetailsBinding.recycler.adapter = VoteDetailAdapter()

        mAccount = baseDao.onSelectAccount(baseDao.lastUser)
        mBaseChain = BaseChain.getChain(mAccount.baseChain)
        mChainConfig = ChainFactory.getChain(mBaseChain)

    }

    private fun onFetchProposal() {
        voteViewModel.loadProposal(this, mChainConfig, mProposalId)
        voteViewModel.loadMyVoteProposal(this, mChainConfig, mAccount)
    }

    private fun onObserve() {
        voteViewModel.proposal.observe(this) {
            mProposal = it
            selectedSet.add(it)
            onUpdateView()
        }

        voteViewModel.myVote.observe(this) { myVote ->
            myVote.votes.forEach {
                if (it.id.toString() == mProposalId) {
                    mMyVoteData = it
                    return@forEach
                }
            }
            onUpdateView()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onUpdateView() {
        if (voteViewModel.myVote.value != null && voteViewModel.proposal.value != null) {
            voteDetailsBinding.apply {
                loadingLayer.visibility = View.GONE
                btnVote.visibility = View.VISIBLE
                recycler.visibility = View.VISIBLE
                recycler.adapter?.notifyDataSetChanged()
            }
        }
    }

    private fun onClick() {
        voteDetailsBinding.btnExplorer.setOnClickListener {
            onExplorerLink()
        }
        voteDetailsBinding.btnVote.setOnClickListener {
            if (!mAccount.hasPrivateKey && !mAccount.isLedger) {
                onInsertKeyDialog()
                return@setOnClickListener
            }
            if (!WDp.isTxFeePayable(this, baseDao, mChainConfig)) {
                Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (BigDecimal.ZERO >= baseDao.delegationSum) {
                Toast.makeText(baseContext, getString(R.string.error_no_bonding_no_vote), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Intent(this, VoteActivity::class.java).apply {
                putExtra("proposal", Gson().toJson(selectedSet))
                startActivity(this)
            }
        }
    }

    private inner class VoteDetailAdapter : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return when (viewType) {
                voteViewType.TYPE_VOTE_INFO.ordinal -> VoteInfoHolder(ItemVoteInfoBinding.inflate(layoutInflater, parent, false))
                voteViewType.TYPE_VOTE_TALLY.ordinal -> VoteTallyHolder(ItemVoteTallyInfoBinding.inflate(layoutInflater, parent, false))
                else -> VoteMessageHolder(ItemVoteMessageBinding.inflate(layoutInflater, parent, false))
            }
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            when (position) {
                voteViewType.TYPE_VOTE_INFO.ordinal -> (viewHolder as VoteInfoHolder).bind()
                voteViewType.TYPE_VOTE_TALLY.ordinal -> (viewHolder as VoteTallyHolder).bind()
                else -> (viewHolder as VoteMessageHolder).bind(position - 2)
            }
        }

        override fun getItemCount(): Int {
            return 2 + (mProposal?.messages?.size ?: 0)
        }

        override fun getItemViewType(position: Int): Int {
            return when (position) {
                0 -> voteViewType.TYPE_VOTE_INFO.ordinal
                1 -> voteViewType.TYPE_VOTE_TALLY.ordinal
                else -> voteViewType.TYPE_VOTE_MESSAGE.ordinal
            }
        }

        inner class VoteInfoHolder(val voteInfoBinding: ItemVoteInfoBinding) : ViewHolder(voteInfoBinding.root) {
            fun bind() {
                voteInfoBinding.apply {
                    voteViewModel.proposal.value?.let { proposal ->
                        voteId.text = "# ${proposal.id}"
                        voteTitle.text = proposal.title
                        voteRemainTime.text = getGapTime(WDp.convertDateToLong(resources.getString(R.string.str_vote_time_format), proposal.voting_end_time))
                        voteExpeditedImg.visibleOrGone(proposal.is_expedited)
                    }
                }
            }
        }

        inner class VoteTallyHolder(val voteTallyInfoBinding: ItemVoteTallyInfoBinding) : ViewHolder(voteTallyInfoBinding.root) {
            fun bind() {
                voteTallyInfoBinding.apply {
                    voteViewModel.proposal.value?.let { proposal ->
                        if (proposal.getVoteStatus(baseDao, mChainConfig, proposal)) {
                            currentStatus.text = " PASS"
                            currentStatus.setTextColor(ContextCompat.getColor(this@VoteDetailActivity, R.color.colorVoteYes))
                        } else {
                            currentStatus.text = " REJECT"
                            currentStatus.setTextColor(ContextCompat.getColor(this@VoteDetailActivity, R.color.colorVoteNo))
                        }
                        currentStatusMessage.text = proposal.getVoteStatus(this@VoteDetailActivity, baseDao, mChainConfig, proposal)
                        mMyVoteData?.voteDetails?.let {
                            if (it.isNotEmpty()) {
                                if (it.count() > 1) {
                                    myVote.text = "WEIGHT"
                                    myVote.setTextColor(ContextCompat.getColor(this@VoteDetailActivity, R.color.colorVoteWeight))
                                    myVoteImg.setImageResource(R.drawable.icon_my_vote_weight)
                                } else {
                                    val voteOption = it[0].option
                                    myVoteImg.setImageResource(voteOptionImage(voteOption))
                                    myVote.text = voteOptionConvert(voteOption)
                                    myVote.setTextColor(voteOptionColor(voteOption))
                                }
                            }
                        } ?: run {
                            myVote.text = resources.getString(R.string.str_vote_not).uppercase()
                            myVote.setTextColor(ContextCompat.getColor(this@VoteDetailActivity, R.color.colorVoteAbstain))
                            myVoteImg.setImageResource(R.drawable.icon_not_voted)
                        }
                        quorum.text = WDp.getPercentDp(baseDao.mParam.getQuorum(mChainConfig, proposal).movePointRight(2))
                        currentTurnout.text = WDp.getDpString(WDp.getTurnout(baseDao, proposal).setScale(2).toPlainString() + "%", 3)

                        voteYesProgress.progress = WDp.getYesPer(proposal).toInt()
                        voteNoProgress.progress = WDp.getNoPer(proposal).toInt()
                        voteVetoProgress.progress = WDp.getVetoPer(proposal).toInt()
                        voteAbstainProgress.progress = WDp.getAbstainPer(proposal).toInt()

                        voteYesPercent.text = WDp.getDpString(WDp.getYesPer(proposal).toPlainString() + "%", 3)
                        voteNoPercent.text = WDp.getDpString(WDp.getNoPer(proposal).toPlainString() + "%", 3)
                        voteVetoPercent.text = WDp.getDpString(WDp.getVetoPer(proposal).toPlainString() + "%", 3)
                        voteAbstainPercent.text = WDp.getDpString(WDp.getAbstainPer(proposal).toPlainString() + "%", 3)
                    }
                }
            }
        }

        inner class VoteMessageHolder(val voteMessageBinding: ItemVoteMessageBinding) : ViewHolder(voteMessageBinding.root) {
            fun bind(position: Int) {
                voteMessageBinding.apply {
                    voteViewModel.proposal.value?.let { proposal ->
                        messageType.text = (position + 1).toString() + ". " + proposal.getProposalType(position).substringAfterLast(".")
                        messageType.setTextColor(ContextCompat.getColor(this@VoteDetailActivity, mChainConfig.chainColor()))
                        messageTitle.text = proposal.getProposalTitle(position)
                        messageDescription.text = proposal.getProposalDescription(position)
                        if (proposal.getProposalAmount(position) != null) {
                            WDp.setDpCoin(this@VoteDetailActivity, baseDao, mChainConfig, proposal.getProposalAmount(position), requestDenom, requestAmount)
                        } else {
                            amountLayout.visibility = View.GONE
                        }

                        messageBtnExpend.setOnClickListener {
                            if (messageDescription.maxLines == 500) {
                                messageDescription.maxLines = 0
                                messageBtnExpend.setImageResource(R.drawable.arrow_down_gr)
                                view2.visibility = View.GONE
                            } else {
                                messageDescription.maxLines = 500
                                messageBtnExpend.setImageResource(R.drawable.arrow_up_gr)
                                view2.visibility = View.VISIBLE
                            }
                            voteDetailsBinding.recycler.adapter?.notifyDataSetChanged()
                        }
                        onSpamLink(proposal, position, this)
                    }
                }
            }
        }
    }

    enum class voteViewType() { TYPE_VOTE_INFO, TYPE_VOTE_TALLY, TYPE_VOTE_MESSAGE }

    private fun onExplorerLink() {
        val url = mChainConfig.explorerUrl() + "proposals/" + mProposalId
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun onSpamLink(proposal: ResProposal, position: Int, binding: ItemVoteMessageBinding) {
        val URL_PATTERN = Pattern.compile("(([A-Za-z]{3,9}:(?://)?)(?:[-;:&=+$,\\w]+@)?[A-Za-z0-9.-]+|(?:www\\.|[-;:&=+$,\\w]+@)[A-Za-z0-9.-]+)((?:/[+~%/.\\w-]*)?\\??(?:[-+=&;%@.\\w]*)#?(?:[.!/\\\\\\w]*))?", Pattern.CASE_INSENSITIVE)
        proposal.getProposalDescription(position).let {
            if (it != null) {
                val m = URL_PATTERN.matcher(it)
                val sb = SpannableStringBuilder(it)
                while (m.find()) {
                    val clickableSpan: ClickableSpan = object : ClickableSpan() {
                        override fun onClick(widget: View) {
                            onExplorerLink()
                        }
                    }
                    sb.setSpan(clickableSpan, m.start(), m.end(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
                }
                binding.messageDescription.text = sb
                binding.messageDescription.movementMethod = LinkMovementMethod.getInstance()
            }
        }
    }

    private fun getGapTime(finishTime: Long): String {
        var result = "??"
        try {
            val now = Calendar.getInstance().timeInMillis
            val left = finishTime - now

            result = when {
                left >= BaseConstant.CONSTANT_D -> "D-" + left / BaseConstant.CONSTANT_D
                left >= BaseConstant.CONSTANT_H -> "H-" + left / BaseConstant.CONSTANT_H
                else -> "Soon"
            }
        } catch (_: Exception) { }
        return result
    }

    private fun voteOptionConvert(option: String): String {
        return when (option) {
            "VOTE_OPTION_YES" -> resources.getString(R.string.str_vote_yes).uppercase()
            "VOTE_OPTION_NO" -> resources.getString(R.string.str_vote_no).uppercase()
            "VOTE_OPTION_NO_WITH_VETO" -> resources.getString(R.string.str_vote_veto).uppercase()
            else -> resources.getString(R.string.str_vote_abstain).uppercase()
        }
    }

    private fun voteOptionColor(option: String): Int {
        return when (option) {
            "VOTE_OPTION_YES" -> ContextCompat.getColor(this@VoteDetailActivity, R.color.colorVoteYes)
            "VOTE_OPTION_NO" -> ContextCompat.getColor(this@VoteDetailActivity, R.color.colorVoteNo)
            "VOTE_OPTION_NO_WITH_VETO" -> ContextCompat.getColor(this@VoteDetailActivity, R.color.colorVoteVeto)
            else -> ContextCompat.getColor(this@VoteDetailActivity, R.color.colorVoteAbstain)
        }
    }

    private fun voteOptionImage(option: String): Int {
        return when (option) {
            "VOTE_OPTION_YES" -> R.drawable.icon_my_vote_yes
            "VOTE_OPTION_NO" -> R.drawable.icon_my_vote_no
            "VOTE_OPTION_NO_WITH_VETO" -> R.drawable.icon_my_vote_veto
            else -> R.drawable.icon_my_vote_abstain
        }
    }
}
