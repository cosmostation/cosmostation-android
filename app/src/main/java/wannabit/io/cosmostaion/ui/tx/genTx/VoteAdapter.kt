package wannabit.io.cosmostaion.ui.tx.genTx

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainAtomone
import wannabit.io.cosmostaion.data.model.res.CosmosProposal
import wannabit.io.cosmostaion.databinding.ItemAtomoneVoteBinding
import wannabit.io.cosmostaion.databinding.ItemVoteBinding

class VoteAdapter(
    private var selectChain: BaseChain,
    private var listener: ClickListener
) : ListAdapter<CosmosProposal, RecyclerView.ViewHolder>(VoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (selectChain is ChainAtomone) {
            val binding =
                ItemAtomoneVoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return AtomoneVoteViewHolder(parent.context, binding)

        } else {
            val binding =
                ItemVoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return VoteViewHolder(parent.context, binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val proposal = currentList[position]
        when (holder) {
            is VoteViewHolder -> {
                holder.bind(selectChain, proposal, listener)
            }

            is AtomoneVoteViewHolder -> {
                holder.bind(selectChain as ChainAtomone, proposal, listener)
            }
        }
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