package wannabit.io.cosmostaion.ui.tx.step

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.data.model.res.CosmosProposal
import wannabit.io.cosmostaion.databinding.ItemAllChainVoteLayoutBinding

class AllChainVoteAdapter(var listener: CheckListener) :
    ListAdapter<VoteAllModel, AllChainVoteViewHolder>(AllChainVoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllChainVoteViewHolder {
        val binding = ItemAllChainVoteLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AllChainVoteViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: AllChainVoteViewHolder, position: Int) {
        val model = currentList[position]
        holder.bind(model, listener)
    }

    private class AllChainVoteDiffCallback : DiffUtil.ItemCallback<VoteAllModel>() {

        override fun areItemsTheSame(
            oldItem: VoteAllModel, newItem: VoteAllModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: VoteAllModel, newItem: VoteAllModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface CheckListener {
        fun proposalCheck(voteModel: VoteAllModel, proposal: CosmosProposal, tag: Int)
    }
}