package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.data.model.req.Cw721Model
import wannabit.io.cosmostaion.data.model.req.Cw721TokenModel
import wannabit.io.cosmostaion.data.model.res.Cw721
import wannabit.io.cosmostaion.databinding.ItemHeaderBinding
import wannabit.io.cosmostaion.databinding.ItemNftBinding

class NftAdapter(private val line: BaseChain) :
    ListAdapter<Cw721Model, RecyclerView.ViewHolder>(Cw72lDiffCallback()) {

    private var onItemClickListener: ((BaseChain, Cw721?, Cw721TokenModel?) -> Unit)? = null

    companion object {
        const val VIEW_TYPE_NFT_HEADER = 0
        const val VIEW_TYPE_NFT_ITEM = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_NFT_HEADER -> {
                val binding = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                NftTitleViewHolder(binding)
            }

            VIEW_TYPE_NFT_ITEM -> {
                val binding = ItemNftBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                NftViewHolder(parent.context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NftTitleViewHolder -> {
                val sectionPosition = getHeaderPosition(position)
                val model = currentList[sectionPosition]
                holder.bind(model)
            }

            is NftViewHolder -> {
                val sectionPosition = getSectionPosition(position)
                val nftPosition = getItemPosition(position, sectionPosition)

                val model = currentList[sectionPosition]
                val nft = model.tokens[nftPosition]
                holder.bind(model.info, nft)

                holder.itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(line, model.info, nft)
                    }
                }
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        var count = 0
        for (cw72lModel in currentList) {
            count++
            count += cw72lModel.tokens.size
        }
        return count
    }

    override fun getItemViewType(position: Int): Int {
        var itemCountSoFar = 0
        for (cw72lModel in currentList) {
            if (position == itemCountSoFar) {
                return VIEW_TYPE_NFT_HEADER
            } else if (position < itemCountSoFar + 1 + cw72lModel.tokens.size) {
                return VIEW_TYPE_NFT_ITEM
            }
            itemCountSoFar += 1 + cw72lModel.tokens.size
        }
        throw IllegalArgumentException("Invalid position")
    }

    private fun getHeaderPosition(adapterPosition: Int): Int {
        var itemCountSoFar = 0
        for (index in currentList.indices) {
            if (adapterPosition == itemCountSoFar) {
                return index
            }
            itemCountSoFar += 1 + currentList[index].tokens.size
        }
        throw IllegalArgumentException("Invalid position")
    }

    private fun getSectionPosition(adapterPosition: Int): Int {
        var itemCountSoFar = 0
        for (index in currentList.indices) {
            if (adapterPosition < itemCountSoFar + 1 + currentList[index].tokens.size) {
                return index
            }
            itemCountSoFar += 1 + currentList[index].tokens.size
        }
        throw IllegalArgumentException("Invalid position")
    }

    private fun getItemPosition(adapterPosition: Int, sectionPosition: Int): Int {
        var itemCountSoFar = 0
        for (index in 0 until sectionPosition) {
            itemCountSoFar += 1 + currentList[index].tokens.size
        }
        return adapterPosition - itemCountSoFar - 1
    }

    inner class NftTitleViewHolder(
        private val binding: ItemHeaderBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Cw721Model) {
            binding.apply {
                headerTitle.text = model.info.name
                headerCnt.text = model.tokens.size.toString()
            }
        }
    }

    private class Cw72lDiffCallback : DiffUtil.ItemCallback<Cw721Model>() {

        override fun areItemsTheSame(oldItem: Cw721Model, newItem: Cw721Model): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Cw721Model, newItem: Cw721Model): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (BaseChain, Cw721?, Cw721TokenModel?) -> Unit) {
        onItemClickListener = listener
    }
}