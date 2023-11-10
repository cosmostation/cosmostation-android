package wannabit.io.cosmostaion.ui.tx.step.neutron

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.gapTime
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.common.visibleOrInvisible
import wannabit.io.cosmostaion.common.voteDpTime
import wannabit.io.cosmostaion.data.model.res.ProposalData
import wannabit.io.cosmostaion.data.model.res.ProposalModule
import wannabit.io.cosmostaion.databinding.ItemDaoMultiChoiceBinding
import wannabit.io.cosmostaion.databinding.ItemDaoMultiVoteBinding

class DaoMultiVoteViewHolder(
    val context: Context, private val binding: ItemDaoMultiVoteBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        proposalData: ProposalData?,
        proposalModule: ProposalModule?,
        listener: DaoVoteAdapter.ClickListener
    ) {
        binding.apply {
            voteView.setBackgroundResource(R.drawable.cell_bg)

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

                val inflater =
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                optionLayout.removeAllViews()
                proposal.proposal?.choices?.forEach { choice ->
                    val optionViewLayout = ItemDaoMultiChoiceBinding.inflate(inflater)
                    optionLayout.addView(optionViewLayout.root)
                    optionViewLayout.apply {
                        choice?.let { choice ->
                            optionImg.visibleOrInvisible(proposal.myVote == choice.index.toString())
                            if (proposal.myVote == choice.index.toString()) {
                                optionView.setBackgroundResource(R.drawable.button_check_bg)
                            } else {
                                optionView.setBackgroundResource(R.drawable.button_fee_bg)
                            }
                            optionTitle.text = "Option " + choice.index + ". " + choice.option_type
                            optionDescription.text = choice.description

                            optionView.setOnClickListener {
                                proposal.myVote = choice.index.toString()
                                choice.index?.let { index ->
                                    listener.selectOption(
                                        adapterPosition, proposalModule?.name, index
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}