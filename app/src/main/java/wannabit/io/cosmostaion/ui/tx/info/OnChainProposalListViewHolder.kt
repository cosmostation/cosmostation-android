package wannabit.io.cosmostaion.ui.tx.info

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.gapTime
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.common.voteDpTime
import wannabit.io.cosmostaion.data.model.res.CosmosProposal
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemOnChainProposalBinding

class OnChainProposalListViewHolder(
    val context: Context,
    private val binding: ItemOnChainProposalBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        selectedChain: BaseChain,
        proposal: CosmosProposal,
        toVote: MutableList<String>?,
        checkListener: OnChainProposalListAdapter.CheckListener
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
                voteRemainTime.text = "${voteDpTime(proposal.voting_end_time?.toLong() ?: 0L)} (${gapTime(proposal.voting_end_time?.toLong() ?: 0L)})"
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

            selectSwitch.setOnCheckedChangeListener(null)
            selectSwitch.setOnCheckedChangeListener { _, isChecked ->
                val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                vibrator.vibrate(VibrationEffect.createOneShot(100, 50))

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