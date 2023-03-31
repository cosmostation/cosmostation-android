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
import wannabit.io.cosmostaion.model.VoteViewModel
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
            voteDetailsBinding.loadingLayer.visibility = View.GONE
            voteDetailsBinding.btnVote.visibility = View.VISIBLE
            voteDetailsBinding.recycler.visibility = View.VISIBLE
            voteDetailsBinding.recycler.adapter?.notifyDataSetChanged()
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
        private val TYPE_VOTE_INFO = 0
        private val TYPE_VOTE_TALLY = 1
        private val TYPE_VOTE_MESSAGE = 2

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return when (viewType) {
                TYPE_VOTE_INFO -> VoteInfoHolder(ItemVoteInfoBinding.inflate(layoutInflater, parent, false))
                TYPE_VOTE_TALLY -> VoteTallyHolder(ItemVoteTallyInfoBinding.inflate(layoutInflater, parent, false))
                else -> VoteMessageHolder(ItemVoteMessageBinding.inflate(layoutInflater, parent, false))
            }
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            when (position) {
                0 -> (viewHolder as VoteInfoHolder).bind()
                1 -> (viewHolder as VoteTallyHolder).bind()
                else -> (viewHolder as VoteMessageHolder).bind(position - 2)
            }
        }

        override fun getItemCount(): Int {
            return 2 + (mProposal?.messages?.size ?: 0)
        }

        override fun getItemViewType(position: Int): Int {
            return when (position) {
                0 -> TYPE_VOTE_INFO
                1 -> TYPE_VOTE_TALLY
                else -> TYPE_VOTE_MESSAGE
            }
        }

        inner class VoteInfoHolder(val voteInfoBinding: ItemVoteInfoBinding) : ViewHolder(voteInfoBinding.root) {
            fun bind() {
                voteViewModel.proposal.value?.let { proposal ->
                    voteInfoBinding.voteId.text = "# ${proposal.id}"
                    voteInfoBinding.voteTitle.text = proposal.title
                    voteInfoBinding.voteRemainTime.text = getGapTime(WDp.convertDateToLong(resources.getString(R.string.str_vote_time_format), proposal.voting_end_time))
                    voteInfoBinding.voteExpeditedImg.visibleOrGone(proposal.is_expedited)
                }
            }
        }

        inner class VoteTallyHolder(val voteTallyInfoBinding: ItemVoteTallyInfoBinding) : ViewHolder(voteTallyInfoBinding.root) {
            fun bind() {
                voteViewModel.proposal.value?.let { proposal ->
                    if (proposal.getVoteStatus(baseDao, mChainConfig, proposal)) {
                        voteTallyInfoBinding.currentStatus.text = " PASS"
                        voteTallyInfoBinding.currentStatus.setTextColor(ContextCompat.getColor(this@VoteDetailActivity, R.color.colorVoteYes))
                    } else {
                        voteTallyInfoBinding.currentStatus.text = " REJECT"
                        voteTallyInfoBinding.currentStatus.setTextColor(ContextCompat.getColor(this@VoteDetailActivity, R.color.colorVoteNo))
                    }
                    voteTallyInfoBinding.currentStatusMessage.text = proposal.getVoteStatus(this@VoteDetailActivity, baseDao, mChainConfig, proposal)
                    mMyVoteData?.voteDetails?.let {
                        if (it.isNotEmpty()) {
                            if (it.count() > 1) {
                                voteTallyInfoBinding.myVote.text = "WEIGHT"
                                voteTallyInfoBinding.myVote.setTextColor(ContextCompat.getColor(this@VoteDetailActivity, R.color.colorVoteWeight))
                                voteTallyInfoBinding.myVoteImg.setImageResource(R.drawable.icon_my_vote_weight)
                            } else {
                                val voteOption = it[0].option
                                voteTallyInfoBinding.myVoteImg.setImageResource(voteOptionImage(voteOption))
                                voteTallyInfoBinding.myVote.text = voteOptionConvert(voteOption)
                                voteTallyInfoBinding.myVote.setTextColor(voteOptionColor(voteOption))
                            }
                        }
                    } ?: run {
                        voteTallyInfoBinding.myVote.text = resources.getString(R.string.str_vote_not).uppercase()
                        voteTallyInfoBinding.myVote.setTextColor(ContextCompat.getColor(this@VoteDetailActivity, R.color.colorVoteAbstain))
                        voteTallyInfoBinding.myVoteImg.setImageResource(R.drawable.icon_not_voted)
                    }
                    voteTallyInfoBinding.quorum.text = WDp.getPercentDp(baseDao.mParam.getQuorum(mChainConfig, proposal).movePointRight(2))
                    voteTallyInfoBinding.currentTurnout.text = WDp.getDpString(WDp.getTurnout(baseDao, proposal).setScale(2).toPlainString() + "%", 3)

                    voteTallyInfoBinding.voteYesProgress.progress = WDp.getYesPer(proposal).toInt()
                    voteTallyInfoBinding.voteNoProgress.progress = WDp.getNoPer(proposal).toInt()
                    voteTallyInfoBinding.voteVetoProgress.progress = WDp.getVetoPer(proposal).toInt()
                    voteTallyInfoBinding.voteAbstainProgress.progress = WDp.getAbstainPer(proposal).toInt()

                    voteTallyInfoBinding.voteYesPercent.text = WDp.getDpString(WDp.getYesPer(proposal).toPlainString() + "%", 3)
                    voteTallyInfoBinding.voteNoPercent.text = WDp.getDpString(WDp.getNoPer(proposal).toPlainString() + "%", 3)
                    voteTallyInfoBinding.voteVetoPercent.text = WDp.getDpString(WDp.getVetoPer(proposal).toPlainString() + "%", 3)
                    voteTallyInfoBinding.voteAbstainPercent.text = WDp.getDpString(WDp.getAbstainPer(proposal).toPlainString() + "%", 3)
                }
            }
        }

        inner class VoteMessageHolder(val voteMessageBinding: ItemVoteMessageBinding) : ViewHolder(voteMessageBinding.root) {
            fun bind(position: Int) {
                voteViewModel.proposal.value?.let { proposal ->
                    voteMessageBinding.messageType.text = (position + 1).toString() + ". " + proposal.getProposalType(position).substringAfterLast(".")
                    voteMessageBinding.messageType.setTextColor(ContextCompat.getColor(this@VoteDetailActivity, mChainConfig.chainColor()))
                    voteMessageBinding.messageTitle.text = proposal.getProposalTitle(position)
                    voteMessageBinding.messageDescription.text = proposal.getProposalDescription(position)
                    if (proposal.getProposalAmount(position) != null) {
                        WDp.setDpCoin(this@VoteDetailActivity, baseDao, mChainConfig, proposal.getProposalAmount(position), voteMessageBinding.requestDenom, voteMessageBinding.requestAmount)
                    } else {
                        voteMessageBinding.amountLayout.visibility = View.GONE
                    }

                    voteMessageBinding.messageBtnExpend.setOnClickListener {
                        if (voteMessageBinding.messageDescription.maxLines == 500) {
                            voteMessageBinding.messageDescription.maxLines = 0
                            voteMessageBinding.messageBtnExpend.setImageResource(R.drawable.arrow_down_gr)
                            voteMessageBinding.view2.visibility = View.GONE
                        } else {
                            voteMessageBinding.messageDescription.maxLines = 500
                            voteMessageBinding.messageBtnExpend.setImageResource(R.drawable.arrow_up_gr)
                            voteMessageBinding.view2.visibility = View.VISIBLE
                        }
                        voteDetailsBinding.recycler.adapter?.notifyDataSetChanged()
                    }
                    onSpamLink(proposal, position, voteMessageBinding)
                }
            }
        }
    }

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
