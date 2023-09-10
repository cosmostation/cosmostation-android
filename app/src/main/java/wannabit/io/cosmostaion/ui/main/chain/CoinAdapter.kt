package wannabit.io.cosmostaion.ui.main.chain

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.base.v1beta1.CoinProto.Coin
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.databinding.ItemCosmosLineCoinBinding
import wannabit.io.cosmostaion.databinding.ItemCosmosLineTokenBinding

class CoinAdapter(
    val context: Context,
    val line: CosmosLine,
    private val nativeCoins: MutableList<Coin>,
    private val ibcCoins: MutableList<Coin>
) : ListAdapter<Coin, RecyclerView.ViewHolder>(CoinDiffCallback()) {

    companion object {
        const val VIEW_TYPE_STAKE_ITEM = 0
        const val VIEW_TYPE_NATIVE_ITEM = 1
        const val VIEW_TYPE_IBC_ITEM = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_STAKE_ITEM -> {
                val binding = ItemCosmosLineCoinBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                CoinCosmosLineViewHolder(binding)
            }

            VIEW_TYPE_NATIVE_ITEM, VIEW_TYPE_IBC_ITEM -> {
                val binding = ItemCosmosLineTokenBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                CoinViewHolder(parent.context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_STAKE_ITEM -> {
                if (holder is CoinCosmosLineViewHolder) {
                    holder.bind(context, line)
                }
            }

            VIEW_TYPE_NATIVE_ITEM -> {
                if (holder is CoinViewHolder) {
                    val nativeCoins = nativeCoins.filter { it.denom != line.stakeDenom }
                    val nativePosition = position -1
                    holder.bindNativeAsset(line, nativeCoins[nativePosition], nativePosition, nativeCoins.size)
                }
            }

            VIEW_TYPE_IBC_ITEM -> {
                if (holder is CoinViewHolder) {
                    val ibcPosition = position - nativeCoins.size
                    holder.bindNativeAsset(line, ibcCoins[ibcPosition], ibcPosition, ibcCoins.size)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return VIEW_TYPE_STAKE_ITEM
        } else if (position < nativeCoins.size) {
            return VIEW_TYPE_NATIVE_ITEM
        } else if (position < nativeCoins.size + ibcCoins.size) {
            return VIEW_TYPE_IBC_ITEM
        }
        return 0
    }

    override fun getItemCount(): Int {
        return nativeCoins.size + ibcCoins.size
    }

    private class CoinDiffCallback : DiffUtil.ItemCallback<Coin>() {

        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem == newItem
        }
    }
}