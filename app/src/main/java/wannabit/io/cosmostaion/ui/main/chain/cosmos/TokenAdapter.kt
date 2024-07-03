package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.databinding.ItemCosmosLineTokenBinding

class TokenAdapter(
    val line: BaseChain
) : ListAdapter<Token, TokenViewHolder>(TokenDiffCallback()) {

    private var onItemClickListener: ((BaseChain, String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TokenViewHolder {
        val binding =
            ItemCosmosLineTokenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TokenViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: TokenViewHolder, position: Int) {
        val token = currentList[position]
        holder.bind(line, token, currentList.size, position)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(line, token.address)
            }
        }
    }

    private class TokenDiffCallback : DiffUtil.ItemCallback<Token>() {

        override fun areItemsTheSame(oldItem: Token, newItem: Token): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Token, newItem: Token): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (BaseChain, String) -> Unit) {
        onItemClickListener = listener
    }
}