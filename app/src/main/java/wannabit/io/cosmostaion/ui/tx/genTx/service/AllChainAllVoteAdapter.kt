package wannabit.io.cosmostaion.ui.tx.genTx.service

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.amountHandlerLeft
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.setChainLogo
import wannabit.io.cosmostaion.data.model.res.CosmosProposal
import wannabit.io.cosmostaion.databinding.ItemAllChainVoteBinding
import wannabit.io.cosmostaion.databinding.ItemAllChainVoteLayoutBinding

class AllChainAllVoteAdapter(
    var listener: CheckListener
) : ListAdapter<VoteAllModel, RecyclerView.ViewHolder>(AllChainVoteDiffCallback()) {

    private var onItemClickListener: ((VoteAllModel, CosmosProposal) -> Unit)? = null

    companion object {
        const val VIEW_TYPE_VOTING_HEADER = 0
        const val VIEW_TYPE_VOTING_ITEM = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_VOTING_HEADER -> {
                val binding = ItemAllChainVoteLayoutBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                AllChainVoteTitleViewHolder(binding)
            }

            VIEW_TYPE_VOTING_ITEM -> {
                val binding = ItemAllChainVoteBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                AllChainAllVoteViewHolder(parent.context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AllChainVoteTitleViewHolder -> {
                val sectionPosition = getHeaderPosition(position)
                val model = currentList[sectionPosition]
                holder.bind(model)
            }

            is AllChainAllVoteViewHolder -> {
                holder.setIsRecyclable(false)
                val sectionPosition = getSectionPosition(position)
                val votePosition = getItemPosition(position, sectionPosition)

                val model = currentList[sectionPosition]
                val proposal = model.proposals[votePosition]
                holder.bind(model, proposal, model.myVotes, listener)

                holder.itemView.findViewById<View>(R.id.delete_layout).setOnClickListener {
                    onItemClickListener?.let {
                        it(model, proposal)
                    }
                }
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        var count = 0
        for (voteAllModel in currentList) {
            count++
            count += voteAllModel.proposals.size
        }
        return count
    }

    override fun getItemViewType(position: Int): Int {
        var itemCountSoFar = 0
        for (voteAllModel in currentList) {
            if (position == itemCountSoFar) {
                return VIEW_TYPE_VOTING_HEADER
            } else if (position < itemCountSoFar + 1 + voteAllModel.proposals.size) {
                return VIEW_TYPE_VOTING_ITEM
            }
            itemCountSoFar += 1 + voteAllModel.proposals.size
        }
        throw IllegalArgumentException("Invalid position")
    }

    private fun getHeaderPosition(adapterPosition: Int): Int {
        var itemCountSoFar = 0
        for (index in currentList.indices) {
            if (adapterPosition == itemCountSoFar) {
                return index
            }
            itemCountSoFar += 1 + currentList[index].proposals.size
        }
        throw IllegalArgumentException("Invalid position")
    }

    private fun getSectionPosition(adapterPosition: Int): Int {
        var itemCountSoFar = 0
        for (index in currentList.indices) {
            if (adapterPosition < itemCountSoFar + 1 + currentList[index].proposals.size) {
                return index
            }
            itemCountSoFar += 1 + currentList[index].proposals.size
        }
        throw IllegalArgumentException("Invalid position")
    }

    private fun getItemPosition(adapterPosition: Int, sectionPosition: Int): Int {
        var itemCountSoFar = 0
        for (index in 0 until sectionPosition) {
            itemCountSoFar += 1 + currentList[index].proposals.size
        }
        return adapterPosition - itemCountSoFar - 1
    }

    inner class AllChainVoteTitleViewHolder(
        private val binding: ItemAllChainVoteLayoutBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(voteAllModel: VoteAllModel) {
            binding.apply {
                binding.apply {
                    voteAllModel.basechain?.let { chain ->
                        chainImg.setChainLogo(chain)
                        chainName.text = chain.getChainName()
                        voteCnt.text = voteAllModel.proposals.size.toString()

                        if (voteAllModel.isBusy) {
                            progress.visibility = View.VISIBLE
                            feeLayout.visibility = View.GONE

                        } else {
                            progress.visibility = View.GONE
                            if (voteAllModel.txResponse != null) {
                                stateImg.visibility = View.VISIBLE
                                feeLayout.visibility = View.GONE

                            } else {
                                stateImg.visibility = View.GONE
                                voteAllModel.txFee?.let { fee ->
                                    BaseData.getAsset(
                                        chain.apiName, fee.getAmount(0).denom
                                    )?.let { asset ->
                                        val amount = fee.getAmount(0).amount.toBigDecimal()
                                            .amountHandlerLeft(asset.decimals ?: 6)
                                        feeLayout.visibility = View.VISIBLE
                                        feeAmount.text = formatAmount(
                                            amount.toPlainString(), asset.decimals ?: 6
                                        )
                                        feeDenom.text = asset.symbol
                                        feeDenom.setTextColor(asset.assetColor())
                                    }

                                } ?: run {
                                    feeLayout.visibility = View.GONE
                                }
                            }
                        }
                    }
                }
            }
        }
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

    fun setOnItemClickListener(listener: (VoteAllModel, CosmosProposal) -> Unit) {
        onItemClickListener = listener
    }
}