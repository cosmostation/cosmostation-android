package wannabit.io.cosmostaion.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.data.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.databinding.ItemTokenEditBinding

class TokenEditAdapter(
    private val walletViewModel: WalletViewModel,
    private val selectChain: BaseChain,
    private val tokens: MutableList<Token>?,
    private val displayTokens: MutableList<String>?
) : RecyclerView.Adapter<TokenEditViewHolder>() {

    private var onItemClickListener: ((MutableList<String>) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): TokenEditViewHolder {
        val binding =
            ItemTokenEditBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TokenEditViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tokens?.size ?: 0
    }

    override fun onBindViewHolder(holder: TokenEditViewHolder, position: Int) {
        tokens?.get(position)?.let { token ->
            holder.bind(walletViewModel, selectChain, token)
            updateView(holder, token, displayTokens)

            holder.itemView.setOnClickListener {
                if (displayTokens?.contains(token.address) == true) {
                    displayTokens.removeIf { it == token.address }
                } else {
                    displayTokens?.add(token.address)
                }
                updateView(holder, token, displayTokens)
                onItemClickListener?.let {
                    displayTokens?.let(it)
                }
            }
        }
    }

    private fun updateView(
        holder: TokenEditViewHolder, token: Token, displayTokens: MutableList<String>?
    ) {
        holder.binding.apply {
            if (displayTokens?.contains(token.address) == true) {
                editView.setBackgroundResource(R.drawable.item_select_bg)
            } else {
                editView.setBackgroundResource(R.drawable.cell_bg)
            }
        }
    }

    fun updateTokens(newTokens: MutableList<Token>?) {
        tokens?.clear()
        if (newTokens != null) {
            tokens?.addAll(newTokens)
        }
        notifyDataSetChanged()
    }


    fun setOnItemClickListener(listener: (MutableList<String>) -> Unit) {
        onItemClickListener = listener
    }
}