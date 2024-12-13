package wannabit.io.cosmostaion.ui.tx.genTx

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.gov.v1beta1.GovProto.VoteOption
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.cosmosClass.ChainAtomone
import wannabit.io.cosmostaion.common.dateToLong
import wannabit.io.cosmostaion.common.gapTime
import wannabit.io.cosmostaion.common.voteDpTime
import wannabit.io.cosmostaion.data.model.res.CosmosProposal
import wannabit.io.cosmostaion.databinding.ItemAtomoneVoteBinding

class AtomoneVoteViewHolder(
    val context: Context, private val binding: ItemAtomoneVoteBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(chain: ChainAtomone, proposal: CosmosProposal, listener: VoteAdapter.ClickListener) {
        binding.apply {
            voteView.setBackgroundResource(R.drawable.cell_bg)
            updateView(proposal)

            voteId.text = "# " + proposal.id + "."
            voteTitle.text = proposal.title
            val dateTime = if (chain.isSupportMintscan()) {
                dateToLong(context.getString(R.string.str_tx_time_format), proposal.voting_end_time)
            } else {
                proposal.voting_end_time?.toLong() ?: 0L
            }
            voteRemainTime.text = voteDpTime(dateTime) + " (" + gapTime(dateTime) + ")"

            yesView.setOnClickListener {
                updateView(proposal)
                listener.selectOption(adapterPosition, 0)
            }

            noView.setOnClickListener {
                updateView(proposal)
                listener.selectOption(adapterPosition, 1)
            }

            abstainView.setOnClickListener {
                updateView(proposal)
                listener.selectOption(adapterPosition, 3)
            }
        }
    }

    private fun updateView(proposal: CosmosProposal) {
        binding.apply {
            listOf(yesView, noView, abstainView).forEach {
                it.setBackgroundResource(
                    R.drawable.button_fee_bg
                )
            }

            listOf(yesImg, noImg, abstainImg).forEach {
                it.visibility = View.GONE
            }

            when (proposal.toVoteOption) {
                VoteOption.VOTE_OPTION_YES -> {
                    yesImg.visibility = View.VISIBLE
                    yesView.setBackgroundResource(R.drawable.button_check_bg)

                }

                VoteOption.VOTE_OPTION_NO -> {
                    noImg.visibility = View.VISIBLE
                    noView.setBackgroundResource(R.drawable.button_check_bg)

                }

                VoteOption.VOTE_OPTION_ABSTAIN -> {
                    abstainImg.visibility = View.VISIBLE
                    abstainView.setBackgroundResource(R.drawable.button_check_bg)
                }

                else -> null
            }
        }
    }
}