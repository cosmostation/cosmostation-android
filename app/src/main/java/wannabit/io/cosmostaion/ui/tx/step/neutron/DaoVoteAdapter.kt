package wannabit.io.cosmostaion.ui.tx.step.neutron

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import wannabit.io.cosmostaion.data.model.res.ProposalData
import wannabit.io.cosmostaion.data.model.res.ProposalModule
import wannabit.io.cosmostaion.databinding.ItemDaoMultiVoteBinding
import wannabit.io.cosmostaion.databinding.ItemDaoSingleVoteBinding

class DaoVoteAdapter(
    private var proposalModules: MutableList<ProposalModule?>?,
    private var singleProposals: List<Pair<String?, ProposalData?>>,
    private var multiProposals: List<Pair<String?, ProposalData?>>,
    private var overruleProposals: List<Pair<String?, ProposalData?>>,
    private var listener: ClickListener
) : ListAdapter<Pair<String?, ProposalData?>, ViewHolder>(DaoVoteDiffCallback()) {

    companion object {
        const val VIEW_TYPE_SINGLE = 0
        const val VIEW_TYPE_MULTI = 1
        const val VIEW_TYPE_OVERRULE = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            VIEW_TYPE_SINGLE -> {
                val binding = ItemDaoSingleVoteBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                DaoSingleVoteViewHolder(parent.context, binding)
            }

            VIEW_TYPE_MULTI -> {
                val binding = ItemDaoMultiVoteBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                DaoMultiVoteViewHolder(parent.context, binding)
            }

            VIEW_TYPE_OVERRULE -> {
                val binding = ItemDaoSingleVoteBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                DaoOverruleViewHolder(parent.context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_SINGLE -> {
                if (holder is DaoSingleVoteViewHolder) {
                    val proposal = singleProposals[position]
                    val module = proposalModules?.firstOrNull { it?.address == proposal.first }
                    holder.bind(proposal.second, module, listener)
                }
            }

            VIEW_TYPE_MULTI -> {
                if (holder is DaoMultiVoteViewHolder) {
                    val proposal = multiProposals[position - singleProposals.size]
                    val module = proposalModules?.firstOrNull { it?.address == proposal.first }
                    holder.bind(proposal.second, module, listener)
                }
            }

            VIEW_TYPE_OVERRULE -> {
                if (holder is DaoOverruleViewHolder) {
                    val proposal = overruleProposals[position]
                    val module = proposalModules?.firstOrNull { it?.address == proposal.first }
                    holder.bind(proposal.second, module, listener)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < singleProposals.size) {
            VIEW_TYPE_SINGLE
        } else if (position < (singleProposals.size + multiProposals.size)) {
            VIEW_TYPE_MULTI
        } else {
            VIEW_TYPE_OVERRULE
        }
    }

    private class DaoVoteDiffCallback : DiffUtil.ItemCallback<Pair<String?, ProposalData?>>() {

        override fun areItemsTheSame(
            oldItem: Pair<String?, ProposalData?>, newItem: Pair<String?, ProposalData?>
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Pair<String?, ProposalData?>, newItem: Pair<String?, ProposalData?>
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface ClickListener {
        fun selectOption(position: Int, module: String?, tag: Int)
    }
}