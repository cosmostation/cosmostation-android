package wannabit.io.cosmostaion.ui.tx.genTx.neutron

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.gapTime
import wannabit.io.cosmostaion.common.voteDpTime
import wannabit.io.cosmostaion.data.model.res.ProposalData
import wannabit.io.cosmostaion.databinding.ItemDaoSingleVoteBinding

class DaoOverruleVoteViewHolder(
    val context: Context, private val binding: ItemDaoSingleVoteBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        proposalData: ProposalData?, listener: DaoVoteAdapter.ClickListener
    ) {
        binding.apply {
            voteView.setBackgroundResource(R.drawable.cell_bg)
            updateView(proposalData)

            proposalData?.let { proposal ->
                voteId.text = "# " + proposal.id + "."
                voteTitle.text = proposal.proposal?.title
                proposal.proposal?.expiration?.at_time?.toLong()?.let { expiration ->
                    voteRemainTime.text =
                        voteDpTime(expiration.div(1000000)) + " (" + gapTime(expiration.div(1000000)) + ")"
                    voteRemainTime.setTextColor(
                        ContextCompat.getColorStateList(
                            context, R.color.color_base02
                        )
                    )
                }
            }

            yesView.setOnClickListener {
                listener.selectOption(adapterPosition, "Overrule", 0)
                updateView(proposalData)
            }

            noView.setOnClickListener {
                listener.selectOption(adapterPosition, "Overrule", 1)
                updateView(proposalData)
            }

            abstainView.setOnClickListener {
                listener.selectOption(adapterPosition, "Overrule", 2)
                updateView(proposalData)
            }
        }
    }

    private fun updateView(proposalData: ProposalData?) {
        binding.apply {
            yesView.setBackgroundResource(R.drawable.button_fee_bg)
            noView.setBackgroundResource(R.drawable.button_fee_bg)
            abstainView.setBackgroundResource(R.drawable.button_fee_bg)

            yesImg.visibility = View.GONE
            noImg.visibility = View.GONE
            abstainImg.visibility = View.GONE

            when (proposalData?.myVote) {
                "yes" -> {
                    yesImg.visibility = View.VISIBLE
                    yesView.setBackgroundResource(R.drawable.button_check_bg)
                }

                "no" -> {
                    noImg.visibility = View.VISIBLE
                    noView.setBackgroundResource(R.drawable.button_check_bg)
                }

                "abstain" -> {
                    abstainImg.visibility = View.VISIBLE
                    abstainView.setBackgroundResource(R.drawable.button_check_bg)
                }
            }
        }
    }
}