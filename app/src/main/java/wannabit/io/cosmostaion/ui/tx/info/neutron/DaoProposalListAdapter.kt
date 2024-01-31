package wannabit.io.cosmostaion.ui.tx.info.neutron

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.NEUTRON_MULTI_MODULE
import wannabit.io.cosmostaion.chain.cosmosClass.NEUTRON_SINGLE_MODULE
import wannabit.io.cosmostaion.data.model.res.ProposalData
import wannabit.io.cosmostaion.data.model.res.ResDaoVoteStatus
import wannabit.io.cosmostaion.databinding.ItemDaoProposalBinding
import wannabit.io.cosmostaion.databinding.ItemStickyHeaderBinding

class DaoProposalListAdapter(
    val selectedChain: ChainNeutron,
    private val type: Int,
    private val periodProposals: MutableList<ProposalData?> = mutableListOf(),
    private val etcProposals: MutableList<ProposalData?> = mutableListOf(),
    private val neutronMyVotes: MutableList<ResDaoVoteStatus>?,
    var listener: CheckListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_PERIOD_HEADER = 0
        const val VIEW_TYPE_PERIOD_ITEM = 1
        const val VIEW_TYPE_ETC_HEADER = 2
        const val VIEW_TYPE_ETC_ITEM = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_PERIOD_HEADER, VIEW_TYPE_ETC_HEADER -> {
                val binding = ItemStickyHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                DaoHeaderViewHolder(parent.context, binding)
            }

            VIEW_TYPE_PERIOD_ITEM, VIEW_TYPE_ETC_ITEM -> {
                val binding = ItemDaoProposalBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                DaoViewHolder(parent.context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DaoHeaderViewHolder -> {
                holder.bind(holder.itemViewType)
            }

            is DaoViewHolder -> {
                val positionOffset = if (periodProposals.isNotEmpty()) 1 else 0
                val periodIndex = position - positionOffset
                val etcIndex = position - (periodProposals.size + positionOffset + 1)

                when {
                    periodProposals.isNotEmpty() && holder.itemViewType == VIEW_TYPE_PERIOD_ITEM -> holder.bind(
                        selectedChain,
                        type,
                        periodProposals[periodIndex],
                        neutronMyVotes,
                        listener
                    )

                    etcIndex >= 0 && holder.itemViewType == VIEW_TYPE_ETC_ITEM -> holder.bind(
                        selectedChain, type, etcProposals[etcIndex], neutronMyVotes, listener
                    )
                }
            }
        }
    }

    override fun getItemCount(): Int {
        val votingOffset = if (periodProposals.isNotEmpty()) 1 else 0
        return periodProposals.size + etcProposals.size + votingOffset + 1
    }

    override fun getItemViewType(position: Int): Int {
        val votingOffset = if (periodProposals.isNotEmpty()) 1 else 0
        return when {
            votingOffset > 0 && position == 0 -> VIEW_TYPE_PERIOD_HEADER
            position in 1..periodProposals.size -> VIEW_TYPE_PERIOD_ITEM
            position == periodProposals.size + votingOffset -> VIEW_TYPE_ETC_HEADER
            else -> VIEW_TYPE_ETC_ITEM
        }
    }

    inner class DaoHeaderViewHolder(
        val context: Context, private val binding: ItemStickyHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewType: Int) {
            binding.apply {
                if (viewType == VIEW_TYPE_PERIOD_HEADER) {
                    headerTitle.text = context.getString(R.string.str_voting_period)
                    headerCnt.text = periodProposals.size.toString()

                } else {
                    headerTitle.text = context.getString(R.string.str_vote_proposals)
                    headerCnt.text = etcProposals.size.toString()
                }
            }
        }
    }

    interface CheckListener {
        fun daoProposalCheck(
            isChecked: Boolean, proposalId: String?
        )
    }
}