package wannabit.io.cosmostaion.ui.tx.info

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.dateToLong
import wannabit.io.cosmostaion.common.gapTime
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.common.voteDpTime
import wannabit.io.cosmostaion.data.model.res.CosmosProposal
import wannabit.io.cosmostaion.data.model.res.VoteData
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemProposalBinding

class ProposalListViewHolder(
    val context: Context,
    private val binding: ItemProposalBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        selectedChain: CosmosLine,
        proposal: CosmosProposal,
        myVotes: MutableList<VoteData>,
        toVote: MutableList<String>?,
        checkListener: ProposalListAdapter.CheckListener
    ) {
        binding.apply {
            proposalView.setBackgroundResource(R.drawable.item_bg)
            voteId.text = "# ${proposal.id}."
            voteTitle.text = proposal.title

            if (proposal.isVotingPeriod()) {
                selectSwitch.isChecked = toVote?.contains(proposal.id) == true
                voteStatusImg.visibility = View.GONE
                voteRemainTime.visibility = View.VISIBLE
                switchView.visibility = View.VISIBLE
                val endTimeToLong = dateToLong(
                    context.getString(R.string.str_tx_time_format), proposal.voting_end_time
                )
                voteRemainTime.text = "${voteDpTime(endTimeToLong)} (${gapTime(endTimeToLong)})"
                selectSwitch.thumbDrawable =
                    ContextCompat.getDrawable(context, R.drawable.switch_thumb_off)
            } else {
                selectSwitch.visibility = View.GONE
                voteStatusImg.visibility = View.VISIBLE
                switchView.visibility = View.GONE
                voteStatusImg.setImageDrawable(proposal.proposalStatusImg(context))
                voteRemainTime.text = proposal.proposalStatusTxt().uppercase()
                voteRemainTime.setTextColor(
                    ContextCompat.getColorStateList(
                        context, R.color.color_base01
                    )
                )
            }
            expeditedImg.visibleOrGone(proposal.is_expedited)

            myVotes.firstOrNull { it.proposal_id == proposal.id }?.let { rawVote ->
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

                        myVote.option?.contains("OPTION_NO") == true -> statusImg.setImageResource(R.drawable.icon_no)
                        myVote.option?.contains("OPTION_ABSTAIN") == true -> statusImg.setImageResource(
                            R.drawable.icon_abstain
                        )
                    }
                }
            } ?: run {
                statusImg.setImageResource(R.drawable.icon_not_voted)
            }

            selectSwitch.setOnCheckedChangeListener(null)
            selectSwitch.setOnCheckedChangeListener { _, isChecked ->
                val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(100, 50))
                } else {
                    vibrator.vibrate(100)
                }

                proposal.id?.let { checkListener.proposalCheck(isChecked, it) }
                val thumbDrawable: Drawable? = if (isChecked) {
                    ContextCompat.getDrawable(context, R.drawable.switch_thumb_on)
                } else {
                    ContextCompat.getDrawable(context, R.drawable.switch_thumb_off)
                }
                selectSwitch.thumbDrawable = thumbDrawable
            }

            proposalView.setOnClickListener {
                selectedChain.explorerProposal(proposal.id)?.let { url ->
                    context.startActivity(Intent(Intent.ACTION_VIEW, url))
                    Prefs.foreToBack = false

                } ?: run {
                    return@setOnClickListener
                }
            }
        }
    }
}