package wannabit.io.cosmostaion.ui.main

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.data.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.databinding.ItemTokenEditBinding

class TokenEditAdapter(
    private val walletViewModel: WalletViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private val selectChain: BaseChain,
    private val displayTokens: MutableList<String>?
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
        val token = currentList[position]
        holder.bind(walletViewModel, selectChain, token)
        updateView(holder, token, displayTokens)

        walletViewModel.editErc20Balance.observe(lifecycleOwner) { contract ->
            if (contract == token.contract) {
                Handler(Looper.getMainLooper()).post {
                    notifyItemChanged(position)
                }
            }
        }

        walletViewModel.editCw20Balance.observe(lifecycleOwner) { contract ->
            if (contract == token.contract) {
                Handler(Looper.getMainLooper()).post {
                    notifyItemChanged(position)
                }
            }
        }

        holder.itemView.setOnClickListener {
            if (displayTokens?.contains(token.contract) == true) {
                displayTokens.removeIf { it == token.contract }
            } else {
                displayTokens?.add(token.contract)
            }
            updateView(holder, token, displayTokens)
            onItemClickListener?.let {
                displayTokens?.let(it)
            }
        }
    }

    private fun updateView(
        holder: TokenEditViewHolder, token: Token, displayTokens: MutableList<String>?
    ) {
        holder.binding.apply {
            if (displayTokens?.contains(token.contract) == true) {
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