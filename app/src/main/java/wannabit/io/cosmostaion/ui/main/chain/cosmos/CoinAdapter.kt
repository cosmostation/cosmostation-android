package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.data.model.res.Coin
import wannabit.io.cosmostaion.data.model.res.CoinType
import wannabit.io.cosmostaion.databinding.ItemCosmosLineCoinBinding
import wannabit.io.cosmostaion.databinding.ItemCosmosLineEtcBinding
import wannabit.io.cosmostaion.databinding.ItemCosmosLineTokenBinding

class CoinAdapter(
    val context: Context, val selectedChain: CosmosLine
) : ListAdapter<Coin, RecyclerView.ViewHolder>(CoinDiffCallback()) {

    private var onItemClickListener: ((CosmosLine, String, Int) -> Unit)? = null

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

            CoinType.ETC.ordinal -> {
                val binding = ItemCosmosLineEtcBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                CoinEtcViewHolder(parent.context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val coin = getItem(position)
        when (coin.type) {
            CoinType.STAKE -> {
                if (holder is CoinCosmosLineViewHolder) {
                    holder.bind(context, selectedChain)

                    holder.itemView.setOnClickListener {
                        onItemClickListener?.let {
                            selectedChain.stakeDenom?.let { stakeDenom ->
                                it(selectedChain, stakeDenom, position)
                            }
                        }
                    }

                    holder.itemView.setOnLongClickListener { view ->
                        if (selectedChain.supportStaking && selectedChain.cosmosRewards.isNotEmpty()) {
                            val scaleX = view.scaleX
                            val scaleY = view.scaleY
                            val customDialog =
                                RewardDialog(context, selectedChain, selectedChain.cosmosRewards)

                            if (scaleX == 1.0f && scaleY == 1.0f) {
                                view.animate().scaleX(1.1f).scaleY(1.1f).setDuration(300).start()
                                val handler = Handler()
                                handler.postDelayed({
                                    customDialog.show()
                                }, 200)
                            }

                            customDialog.setOnDismissListener {
                                view.animate().scaleX(1.0f).scaleY(1.0f).setDuration(300).start()
                            }
                            true

                        } else {
                            onItemClickListener?.let {
                                selectedChain.stakeDenom?.let { stakeDenom ->
                                    it(selectedChain, stakeDenom, position)
                                }
                            }
                            true
                        }
                    }
                }
            }

            CoinType.NATIVE, CoinType.IBC, CoinType.BRIDGE -> {
                if (holder is CoinViewHolder) {
                    val coinType = coin.type
                    val coinPosition = currentList.filter { it.type == coinType }.indexOf(coin)
                    val coinCount = currentList.count { it.type == coinType }
                    holder.bind(selectedChain, coin, coinPosition, coinCount)

                    holder.itemView.setOnClickListener {
                        onItemClickListener?.let {
                            it(selectedChain, coin.denom, position)
                        }
                    }
                }
            }

            CoinType.ETC -> {
                if (holder is CoinEtcViewHolder) {
                    val coinType = coin.type
                    val coinPosition = currentList.filter { it.type == coinType }.indexOf(coin)
                    val coinCount = currentList.count { it.type == coinType }

                    holder.itemView.setOnClickListener {
                        onItemClickListener?.let {
                            it(selectedChain, coin.denom, position)
                        }
                    }
                    if (selectedChain is ChainBinanceBeacon) {
                        holder.bindBeaconCoin(selectedChain, coin, coinPosition, coinCount)
                    } else if (selectedChain is ChainOkt996Keccak || selectedChain is ChainOktEvm) {
                        holder.bindOktCoin(selectedChain, coin, coinPosition, coinCount)
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

    fun setOnItemClickListener(listener: (CosmosLine, String, Int) -> Unit) {
        onItemClickListener = listener
    }
}