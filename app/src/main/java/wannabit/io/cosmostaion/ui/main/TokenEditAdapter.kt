package wannabit.io.cosmostaion.ui.main

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.data.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.databinding.ItemTokenEditBinding

class TokenEditAdapter(
    private val walletViewModel: WalletViewModel,
    private val lifecycleOwner: LifecycleOwner,
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

            walletViewModel.editGrc20Balance.observe(lifecycleOwner) { contract ->
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