package wannabit.io.cosmostaion.ui.main.chain

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.data.model.res.Coin
import wannabit.io.cosmostaion.data.model.res.CoinType
import wannabit.io.cosmostaion.databinding.ItemCosmosLineCoinBinding
import wannabit.io.cosmostaion.databinding.ItemCosmosLineTokenBinding

class CoinAdapter(
    val context: Context,
    val line: CosmosLine
) : ListAdapter<Coin, RecyclerView.ViewHolder>(CoinDiffCallback()) {

    private var onItemClickListener: ((CosmosLine, String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CoinType.STAKE.ordinal -> {
                val binding = ItemCosmosLineCoinBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                CoinCosmosLineViewHolder(binding)
            }

            CoinType.NATIVE.ordinal, CoinType.IBC.ordinal, CoinType.BRIDGE.ordinal -> {
                val binding = ItemCosmosLineTokenBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                CoinViewHolder(parent.context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val coin = getItem(position)
        when (coin.type) {
            CoinType.STAKE -> {
                if (holder is CoinCosmosLineViewHolder) {
                    holder.bind(context, line)

                    holder.itemView.setOnClickListener {
                        onItemClickListener?.let {
                            line.stakeDenom?.let { stakeDenom ->
                                it(line, stakeDenom)
                            }
                        }
                    }
                }
            }

            CoinType.NATIVE, CoinType.IBC, CoinType.BRIDGE ->  {
                if (holder is CoinViewHolder) {
                    val coinType = coin.type
                    val coinPosition = currentList.filter { it.type == coinType }.indexOf(coin)
                    val coinCount = currentList.count { it.type == coinType }
                    holder.bind(line, coin, coinPosition, coinCount)

                    holder.itemView.setOnClickListener {
                        onItemClickListener?.let {
                            it (line, coin.denom)
                        }
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type.ordinal
    }

    private class CoinDiffCallback : DiffUtil.ItemCallback<Coin>() {

        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.denom == newItem.denom
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (CosmosLine, String) -> Unit) {
        onItemClickListener = listener
    }
}