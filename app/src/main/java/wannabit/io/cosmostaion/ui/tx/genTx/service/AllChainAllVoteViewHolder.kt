package wannabit.io.cosmostaion.ui.tx.genTx.service

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.gov.v1beta1.GovProto
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.dateToLong
import wannabit.io.cosmostaion.common.gapTime
import wannabit.io.cosmostaion.common.voteDpTime
import wannabit.io.cosmostaion.data.model.res.CosmosProposal
import wannabit.io.cosmostaion.data.model.res.VoteData
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemAllChainVoteBinding

class AllChainAllVoteViewHolder(
    val context: Context,
    private val binding: ItemAllChainVoteBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        voteAllModel: VoteAllModel,
        proposal: CosmosProposal,
        myVotes: MutableList<VoteData>?,
        listener: CheckListener
    ) {
        binding.apply {
            proposalView.setBackgroundResource(R.drawable.cell_bg)
            deleteView.setBackgroundResource(R.drawable.cell_bg)

            voteId.text = "# " + proposal.id
            voteTitle.text = proposal.title
            voteRemainTime.text = try {
                val endTimeToLong = dateToLong(
                    context.getString(R.string.str_tx_time_format), proposal.voting_end_time
                )
                "${voteDpTime(endTimeToLong)} (${gapTime(endTimeToLong)})"

            } catch (e: Exception) {
                "${voteDpTime(proposal.voting_end_time?.toLong() ?: 0L)} (${gapTime(proposal.voting_end_time?.toLong() ?: 0L)})"
            }
            updateView(this, proposal)

            myVotes?.firstOrNull { it.proposal_id == proposal.id }?.let { rawVote ->
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
                voteAllModel.onChainMyVotes?.firstOrNull { it.proposal_id == proposal.id }
                    ?.let { rawVote ->
                        when (rawVote.vote) {
                            "YES" -> statusImg.setImageResource(R.drawable.icon_yes)
                            "NO" -> statusImg.setImageResource(R.drawable.icon_no)
                            "ABSTAIN" -> statusImg.setImageResource(R.drawable.icon_abstain)
                            "VETO" -> statusImg.setImageResource(R.drawable.icon_veto)
                            "WEIGHT" -> statusImg.setImageResource(R.drawable.icon_weight)
                            else -> statusImg.setImageResource(R.drawable.icon_not_voted)
                        }

                    } ?: run {
                    statusImg.setImageResource(R.drawable.icon_not_voted)
                }
            }

            yesView.setOnClickListener {
                updateView(this, proposal)
                listener.proposalAllVoteCheck(voteAllModel, proposal, 1)
            }

            abstainView.setOnClickListener {
                updateView(this, proposal)
                listener.proposalAllVoteCheck(voteAllModel, proposal, 2)
            }

            noView.setOnClickListener {
                updateView(this, proposal)
                listener.proposalAllVoteCheck(voteAllModel, proposal, 3)
            }

            vetoView.setOnClickListener {
                updateView(this, proposal)
                listener.proposalAllVoteCheck(voteAllModel, proposal, 4)
            }

            proposalView.setOnClickListener {
                voteAllModel.basechain?.explorerProposal(proposal.id)?.let { url ->
                    context.startActivity(Intent(Intent.ACTION_VIEW, url))
                    Prefs.foreToBack = false

                } ?: run {
                    return@setOnClickListener
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