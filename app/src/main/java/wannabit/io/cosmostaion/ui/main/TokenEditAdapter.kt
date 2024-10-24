package wannabit.io.cosmostaion.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.databinding.ItemTokenEditBinding

class TokenEditAdapter(
    private val displayErc20Tokens: MutableList<String>?
) : ListAdapter<Token, TokenEditViewHolder>(
    TokenEditDiffCallback()
) {

    private var onItemClickListener: ((MutableList<String>) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): TokenEditViewHolder {
        val binding =
            ItemTokenEditBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TokenEditViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TokenEditViewHolder, position: Int
    ) {
        val erc20Token = currentList[position]
        holder.bind(erc20Token)
        updateView(holder, erc20Token, displayErc20Tokens)

        holder.itemView.setOnClickListener {
            if (displayErc20Tokens?.contains(erc20Token.contract) == true) {
                displayErc20Tokens.removeIf { it == erc20Token.contract }
            } else {
                displayErc20Tokens?.add(erc20Token.contract)
            }
            updateView(holder, erc20Token, displayErc20Tokens)
            onItemClickListener?.let {
                displayErc20Tokens?.let(it)
            }
        }
    }

    private fun updateView(
        holder: TokenEditViewHolder, token: Token, displayErc20Tokens: MutableList<String>?
    ) {
        holder.binding.apply {
            if (displayErc20Tokens?.contains(token.contract) == true) {
                editView.setBackgroundResource(R.drawable.item_select_bg)
            } else {
                editView.setBackgroundResource(R.drawable.cell_bg)
            }
        }
    }

    private class TokenEditDiffCallback : DiffUtil.ItemCallback<Token>() {

        override fun areItemsTheSame(oldItem: Token, newItem: Token): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Token, newItem: Token): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (MutableList<String>) -> Unit) {
        onItemClickListener = listener
    }
}