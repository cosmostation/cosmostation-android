package wannabit.io.cosmostaion.ui.tx.info

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.data.model.res.CosmosProposal
import wannabit.io.cosmostaion.data.model.res.VoteData
import wannabit.io.cosmostaion.databinding.ItemProposalBinding
import wannabit.io.cosmostaion.databinding.ItemStickyHeaderBinding

class ProposalListAdapter(
    val context: Context,
    val selectedChain: CosmosLine,
    private val votingPeriods: MutableList<CosmosProposal>,
    private val etcPeriods: MutableList<CosmosProposal>,
    private val myVotes: MutableList<VoteData>,
    private val toVoteList: MutableList<String>?,
    var listener: CheckListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_VOTING_HEADER = 0
        const val VIEW_TYPE_VOTING_ITEM = 1
        const val VIEW_TYPE_VOTED_HEADER = 2
        const val VIEW_TYPE_VOTED_ITEM = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_VOTING_HEADER, VIEW_TYPE_VOTED_HEADER -> {
                val binding = ItemStickyHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                ProposalListHeaderViewHolder(binding)
            }

            VIEW_TYPE_VOTING_ITEM, VIEW_TYPE_VOTED_ITEM -> {
                val binding = ItemProposalBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                ProposalListViewHolder(parent.context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ProposalListHeaderViewHolder -> holder.bind(holder.itemViewType)

            is ProposalListViewHolder -> {
                val positionOffset = if (votingPeriods.isNotEmpty()) 1 else 0
                val votingIndex = position - positionOffset
                val etcIndex = position - (votingPeriods.size + positionOffset + 1)

                when {
                    votingPeriods.isNotEmpty() && holder.itemViewType == VIEW_TYPE_VOTING_ITEM ->
                        holder.bind(
                            selectedChain,
                            votingPeriods[votingIndex],
                            myVotes,
                            toVoteList,
                            listener
                        )

                    etcIndex >= 0 && holder.itemViewType == VIEW_TYPE_VOTED_ITEM ->
                        holder.bind(
                            selectedChain,
                            etcPeriods[etcIndex],
                            myVotes,
                            toVoteList,
                            listener
                        )
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val votingOffset = if (votingPeriods.isNotEmpty()) 1 else 0
        return when {
            votingOffset > 0 && position == 0 -> VIEW_TYPE_VOTING_HEADER
            position in 1..votingPeriods.size -> VIEW_TYPE_VOTING_ITEM
            position == votingPeriods.size + votingOffset -> VIEW_TYPE_VOTED_HEADER
            else -> VIEW_TYPE_VOTED_ITEM
        }
    }

    override fun getItemCount(): Int {
        val votingOffset = if (votingPeriods.isNotEmpty()) 1 else 0
        return votingPeriods.size + etcPeriods.size + votingOffset + 1
    }

    inner class ProposalListHeaderViewHolder(
        private val binding: ItemStickyHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewType: Int) {
            binding.apply {
                if (viewType == VIEW_TYPE_VOTING_HEADER) {
                    headerTitle.text = context.getString(R.string.str_voting_period)
                    headerCnt.text = votingPeriods.size.toString()
                } else {
                    headerTitle.text = context.getString(R.string.str_vote_proposals)
                    headerCnt.text = etcPeriods.size.toString()
                }
            }
        }
    }

    interface CheckListener {
        fun proposalCheck(isChecked: Boolean, proposalId: String)
    }
}