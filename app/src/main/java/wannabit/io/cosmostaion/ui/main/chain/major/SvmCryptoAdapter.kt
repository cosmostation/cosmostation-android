package wannabit.io.cosmostaion.ui.main.chain.major

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainSolana
import wannabit.io.cosmostaion.databinding.ItemDefaultAssetViewBinding
import wannabit.io.cosmostaion.databinding.ItemEvmAssetBinding
import wannabit.io.cosmostaion.databinding.ItemHeaderBinding

class SvmCryptoAdapter(
    val selectedChain: BaseChain,
    val coins: MutableList<JsonObject>,
    val tokens: MutableList<JsonObject>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_MAIN_ITEM = 0
        const val VIEW_TYPE_TOKEN_HEADER = 1
        const val VIEW_TYPE_TOKEN_ITEM = 2
    }

    private var onItemClickListener: ((BaseChain, String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_TOKEN_HEADER -> {
                val binding = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                SolanaCryptoHeaderViewHolder(binding)
            }

            VIEW_TYPE_MAIN_ITEM -> {
                val binding = ItemDefaultAssetViewBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                SvmAssetViewHolder(binding)
            }

            VIEW_TYPE_TOKEN_ITEM -> {
                val binding = ItemEvmAssetBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                SvmTokenViewHolderr(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SolanaCryptoHeaderViewHolder -> {
                holder.bind()
            }

            is SvmAssetViewHolder -> {
                holder.bind(selectedChain as ChainSolana)
                holder.itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(selectedChain, selectedChain.coinSymbol)
                    }
                }
            }

            is SvmTokenViewHolderr -> {
                val token = tokens[position - 2]
                holder.bind(selectedChain as ChainSolana, token)

                holder.itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(selectedChain, token["mint"].asString)
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (tokens.isNotEmpty()) {
            when (position) {
                0 -> VIEW_TYPE_MAIN_ITEM
                1 -> VIEW_TYPE_TOKEN_HEADER
                else -> VIEW_TYPE_TOKEN_ITEM
            }
        } else {
            VIEW_TYPE_MAIN_ITEM
        }
    }

    override fun getItemCount(): Int {
        return if (tokens.isNotEmpty()) {
            coins.size + tokens.size + 1
        } else {
            coins.size
        }
    }

    inner class SolanaCryptoHeaderViewHolder(
        private val binding: ItemHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.apply {
                headerTitle.text = "Spl tokens"
                headerCnt.text = tokens.size.toString()
            }
        }
    }

    fun setOnItemClickListener(listener: (BaseChain, String) -> Unit) {
        onItemClickListener = listener
    }
}