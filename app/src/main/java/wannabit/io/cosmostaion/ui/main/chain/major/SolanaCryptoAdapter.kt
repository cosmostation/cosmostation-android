package wannabit.io.cosmostaion.ui.main.chain.major

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainSolana
import wannabit.io.cosmostaion.databinding.ItemEvmAssetBinding
import wannabit.io.cosmostaion.databinding.ItemHeaderBinding

class SolanaCryptoAdapter(
    val context: Context,
    val selectedChain: BaseChain,
    val coins: MutableList<JsonObject>,
    val tokens: MutableList<Pair<String, JsonObject>>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_MAIN_HEADER = 0
        const val VIEW_TYPE_MAIN_ITEM = 1
        const val VIEW_TYPE_TOKEN_HEADER = 2
        const val VIEW_TYPE_TOKEN_ITEM = 3
    }

    private var onItemClickListener: ((BaseChain, String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_MAIN_HEADER, VIEW_TYPE_TOKEN_HEADER -> {
                val binding = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                SolanaCryptoHeaderViewHolder(binding)
            }

            VIEW_TYPE_MAIN_ITEM, VIEW_TYPE_TOKEN_ITEM -> {
                val binding = ItemEvmAssetBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                SolanaAssetViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SolanaCryptoHeaderViewHolder -> {
                holder.bind(position)
            }

            is SolanaAssetViewHolder -> {
                if (holder.itemViewType == VIEW_TYPE_MAIN_ITEM) {
                    holder.bind(selectedChain as ChainSolana)
                    holder.itemView.setOnClickListener {
                        onItemClickListener?.let {
                            it(selectedChain, selectedChain.coinSymbol)
                        }
                    }

                } else {
                    val token = tokens[position - 3]
                    holder.tokenBind(selectedChain as ChainSolana, token)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (tokens.isNotEmpty()) {
            when (position) {
                0 -> VIEW_TYPE_MAIN_HEADER
                1 -> VIEW_TYPE_MAIN_ITEM
                2 -> VIEW_TYPE_TOKEN_HEADER
                else -> VIEW_TYPE_TOKEN_ITEM
            }
        } else {
            when (position) {
                0 -> VIEW_TYPE_MAIN_HEADER
                else -> VIEW_TYPE_MAIN_ITEM
            }
        }
    }

    override fun getItemCount(): Int {
        return if (tokens.isNotEmpty()) {
            coins.size + tokens.size + 2
        } else {
            coins.size + 1
        }
    }

    inner class SolanaCryptoHeaderViewHolder(
        private val binding: ItemHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.apply {
                if (getItemViewType(position) == VIEW_TYPE_MAIN_HEADER) {
                    headerTitle.text = "Coin"
                    headerCnt.text = "1"

                } else {
                    headerTitle.text = "Spl tokens"
                    headerCnt.text = tokens.size.toString()
                }
            }
        }
    }

    fun setOnItemClickListener(listener: (BaseChain, String) -> Unit) {
        onItemClickListener = listener
    }
}