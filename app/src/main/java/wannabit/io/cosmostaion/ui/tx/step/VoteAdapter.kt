package wannabit.io.cosmostaion.ui.tx.step

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.data.model.res.CosmosProposal
import wannabit.io.cosmostaion.databinding.ItemVoteBinding

class VoteAdapter(
    private var listener: ClickListener
): ListAdapter<CosmosProposal, VoteViewHolder>(VoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoteViewHolder {
        val binding = ItemVoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VoteViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: VoteViewHolder, position: Int) {
        val proposal = currentList[position]
        holder.bind(proposal, listener)
    }

    private class VoteDiffCallback : DiffUtil.ItemCallback<CosmosProposal>() {

        override fun areItemsTheSame(oldItem: CosmosProposal, newItem: CosmosProposal): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CosmosProposal, newItem: CosmosProposal): Boolean {
            return oldItem == newItem
        }
    }

    interface ClickListener {
        fun selectOption(position: Int, tag: Int)
    }
}