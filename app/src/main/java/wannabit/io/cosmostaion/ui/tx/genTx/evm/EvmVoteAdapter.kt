package wannabit.io.cosmostaion.ui.tx.genTx.evm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.data.model.res.CosmosProposal
import wannabit.io.cosmostaion.databinding.ItemVoteBinding

class EvmVoteAdapter(
    private var listener: ClickListener
) : ListAdapter<CosmosProposal, EvmVoteViewHolder>(VoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvmVoteViewHolder {
        val binding = ItemVoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EvmVoteViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: EvmVoteViewHolder, position: Int) {
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