package wannabit.io.cosmostaion.ui.tx.step

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.gov.v1beta1.GovProto
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.amountHandlerLeft
import wannabit.io.cosmostaion.common.dateToLong
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.gapTime
import wannabit.io.cosmostaion.common.voteDpTime
import wannabit.io.cosmostaion.data.model.res.CosmosProposal
import wannabit.io.cosmostaion.databinding.ItemAllChainVoteBinding
import wannabit.io.cosmostaion.databinding.ItemAllChainVoteLayoutBinding

class AllChainVoteViewHolder(
    val context: Context,
    private val binding: ItemAllChainVoteLayoutBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(voteAllModel: VoteAllModel, listener: AllChainVoteAdapter.CheckListener) {
        binding.apply {
            voteAllModel.basechain?.let {
                chainImg.setImageResource(it.logo)
                chainName.text = it.name
                voteCnt.text = voteAllModel.proposals.size.toString()

                if (voteAllModel.isBusy) {
                    progress.visibility = View.VISIBLE
                    feeLayout.visibility = View.GONE

                } else {
                    progress.visibility = View.GONE
                    if (voteAllModel.txResponse != null) {
                        stateImg.visibility = View.VISIBLE
                        feeLayout.visibility = View.GONE

                    } else {
                        stateImg.visibility = View.GONE
                        voteAllModel.txFee?.let { fee ->
                            BaseData.getAsset((it as CosmosLine).apiName, fee.getAmount(0).denom)
                                ?.let { asset ->
                                    val amount = fee.getAmount(0).amount.toBigDecimal()
                                        .amountHandlerLeft(asset.decimals ?: 6)
                                    feeLayout.visibility = View.VISIBLE
                                    feeAmount.text =
                                        formatAmount(amount.toPlainString(), asset.decimals ?: 6)
                                    feeDenom.text = asset.symbol
                                    feeDenom.setTextColor(asset.assetColor())
                                }
                        }
                    }
                }
            }

            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            voteListView.removeAllViews()
            voteAllModel.proposals.forEach { proposal ->
                val voteViewLayout = ItemAllChainVoteBinding.inflate(inflater)
                voteListView.addView(voteViewLayout.root)
                voteViewLayout.apply {
                    proposalView.setBackgroundResource(R.drawable.cell_bg)
                    updateView(this, proposal)

                    voteAllModel.myVotes.firstOrNull { it.proposal_id == proposal.id }
                        ?.let { rawVote ->
                            if (rawVote.votes.size > 1) {
                                statusImg.setImageResource(R.drawable.icon_weight)
                                return

                            } else {
                                val myVote = rawVote.votes[0]
                                when {
                                    myVote.option?.contains("OPTION_YES") == true -> statusImg.setImageResource(
                                        R.drawable.icon_yes
                                    )

                                    myVote.option?.contains("OPTION_NO_WITH_VETO") == true -> statusImg.setImageResource(
                                        R.drawable.icon_veto
                                    )

                                    myVote.option?.contains("OPTION_NO") == true -> statusImg.setImageResource(
                                        R.drawable.icon_no
                                    )

                                    myVote.option?.contains("OPTION_ABSTAIN") == true -> statusImg.setImageResource(
                                        R.drawable.icon_abstain
                                    )
                                }
                            }

                        } ?: run {
                        statusImg.setImageResource(R.drawable.icon_not_voted)
                    }

                    voteId.text = proposal.id
                    voteTitle.text = proposal.title
                    val endTimeToLong = dateToLong(
                        context.getString(R.string.str_tx_time_format), proposal.voting_end_time
                    )
                    voteRemainTime.text = "${voteDpTime(endTimeToLong)} (${gapTime(endTimeToLong)})"

                    yesView.setOnClickListener {
                        updateView(this, proposal)
                        listener.proposalCheck(voteAllModel, proposal, 1)
                    }

                    noView.setOnClickListener {
                        updateView(this, proposal)
                        listener.proposalCheck(voteAllModel, proposal, 3)
                    }

                    vetoView.setOnClickListener {
                        updateView(this, proposal)
                        listener.proposalCheck(voteAllModel, proposal, 4)
                    }

                    abstainView.setOnClickListener {
                        updateView(this, proposal)
                        listener.proposalCheck(voteAllModel, proposal, 2)
                    }
                }
            }
        }
    }

    private fun updateView(binding: ItemAllChainVoteBinding, proposal: CosmosProposal) {
        binding.apply {
            listOf(yesView, noView, vetoView, abstainView).forEach {
                it.setBackgroundResource(
                    R.drawable.button_fee_bg
                )
            }

            when (proposal.toVoteOption) {
                GovProto.VoteOption.VOTE_OPTION_YES -> {
                    yesView.setBackgroundResource(R.drawable.button_check_bg)
                }

                GovProto.VoteOption.VOTE_OPTION_NO -> {
                    noView.setBackgroundResource(R.drawable.button_check_bg)
                }

                GovProto.VoteOption.VOTE_OPTION_NO_WITH_VETO -> {
                    vetoView.setBackgroundResource(R.drawable.button_check_bg)
                }

                GovProto.VoteOption.VOTE_OPTION_ABSTAIN -> {
                    abstainView.setBackgroundResource(R.drawable.button_check_bg)
                }

                else -> null
            }
        }
    }
}