package wannabit.io.cosmostaion.ui.tx.genTx.neutron

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import wannabit.io.cosmostaion.data.model.res.ProposalData
import wannabit.io.cosmostaion.databinding.ItemDaoMultiVoteBinding
import wannabit.io.cosmostaion.databinding.ItemDaoSingleVoteBinding

class DaoVoteAdapter(
    private var singleProposals: MutableList<ProposalData>?,
    private var multipleProposals: MutableList<ProposalData>?,
    private var overruleProposals: MutableList<ProposalData>?,
    private var listener: ClickListener
) : ListAdapter<ProposalData, ViewHolder>(DaoVoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (singleProposals?.isNotEmpty() == true) {
            val binding =
                ItemDaoSingleVoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return DaoSingleVoteViewHolder(parent.context, binding)

        } else if (multipleProposals?.isNotEmpty() == true) {
            val binding = ItemDaoMultiVoteBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return DaoMultiVoteViewHolder(parent.context, binding)

        } else if (overruleProposals?.isNotEmpty() == true) {
            val binding =
                ItemDaoSingleVoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return DaoOverruleVoteViewHolder(parent.context, binding)

        } else {
            return throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (singleProposals?.isNotEmpty() == true) {
            if (holder is DaoSingleVoteViewHolder) {
                val proposal = singleProposals?.get(position)
                holder.bind(proposal, listener)
            }

        } else if (multipleProposals?.isNotEmpty() == true) {
            if (holder is DaoMultiVoteViewHolder) {
                val proposal = multipleProposals?.get(position)
                holder.bind(proposal, listener)
            }

        } else if (overruleProposals?.isNotEmpty() == true) {
            if (holder is DaoOverruleVoteViewHolder) {
                val proposal = overruleProposals?.get(position)
                holder.bind(proposal, listener)
            }
        }
    }

    override fun getItemCount(): Int {
        if (singleProposals?.isNotEmpty() == true) {
            singleProposals?.let { return it.size }

        } else if (multipleProposals?.isNotEmpty() == true) {
            multipleProposals?.let { return it.size }

        } else if (overruleProposals?.isNotEmpty() == true) {
            overruleProposals?.let { return it.size }
        }
        return 0
    }

    private class DaoVoteDiffCallback : DiffUtil.ItemCallback<ProposalData>() {

        override fun areItemsTheSame(
            oldItem: ProposalData, newItem: ProposalData
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ProposalData, newItem: ProposalData
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface ClickListener {
        fun selectOption(position: Int, module: String, tag: Int)
    }
}