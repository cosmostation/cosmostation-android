package wannabit.io.cosmostaion.ui.main.chain.evm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.databinding.ItemEvmAssetBinding
import wannabit.io.cosmostaion.databinding.ItemHeaderBinding

class AssetAdapter(
    private val evmChain: BaseChain, private val evmTokens: MutableList<Token>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_COIN_HEADER = 0
        const val VIEW_TYPE_COIN_ITEM = 1
        const val VIEW_TYPE_TOKEN_HEADER = 2
        const val VIEW_TYPE_TOKEN_ITEM = 3
    }

    private var onItemClickListener: ((BaseChain, String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_COIN_HEADER, VIEW_TYPE_TOKEN_HEADER -> {
                val binding = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                EvmAssetHeaderViewHolder(binding)
            }

            VIEW_TYPE_COIN_ITEM, VIEW_TYPE_TOKEN_ITEM -> {
                val binding =
                    ItemEvmAssetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                AssetViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EvmAssetHeaderViewHolder -> {
                holder.bind(position)
            }

            is AssetViewHolder -> {
                if (holder.itemViewType == VIEW_TYPE_COIN_ITEM) {
                    holder.bind(evmChain)

                    holder.itemView.setOnClickListener {
                        onItemClickListener?.let {
                            it(evmChain, "")
                        }
                    }

                } else {
                    val token = evmTokens[position - 3]
                    holder.tokenBind(evmChain, token)

                    holder.itemView.setOnClickListener {
                        onItemClickListener?.let {
                            it(evmChain, token.address)
                        }
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> VIEW_TYPE_COIN_HEADER
            1 -> VIEW_TYPE_COIN_ITEM
            2 -> VIEW_TYPE_TOKEN_HEADER
            else -> VIEW_TYPE_TOKEN_ITEM
        }
    }

    override fun getItemCount(): Int {
        return if (evmTokens.isNotEmpty()) {
            evmTokens.size + 3
        } else {
            2
        }
    }

    inner class EvmAssetHeaderViewHolder(
        private val binding: ItemHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.apply {
                if (getItemViewType(position) == VIEW_TYPE_COIN_HEADER) {
                    headerTitle.text = "Coin"
                    headerCnt.text = "1"

                } else {
                    headerTitle.text = "Erc20 tokens"
                    headerCnt.text = evmTokens.size.toString()
                }
            }
        }
    }

    fun setOnItemClickListener(listener: (BaseChain, String) -> Unit) {
        onItemClickListener = listener
    }
}