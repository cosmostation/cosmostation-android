package wannabit.io.cosmostaion.ui.main.chain.major

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin84
import wannabit.io.cosmostaion.chain.majorClass.ChainNamada
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.majorClass.SUI_MAIN_DENOM
import wannabit.io.cosmostaion.data.model.res.Coin
import wannabit.io.cosmostaion.databinding.ItemCosmosTokenBinding
import wannabit.io.cosmostaion.databinding.ItemHeaderBinding
import wannabit.io.cosmostaion.databinding.ItemMajorCryptoBinding
import wannabit.io.cosmostaion.ui.main.chain.cosmos.CoinViewHolder
import wannabit.io.cosmostaion.ui.main.chain.cosmos.ItemType
import wannabit.io.cosmostaion.ui.main.chain.cosmos.ListItem
import java.math.BigDecimal

class MajorCryptoAdapter(
    val context: Context,
    val selectedChain: BaseChain,
    private val suiBalances: MutableList<Pair<String?, BigDecimal?>>,
    private val suiNativeBalances: MutableList<Pair<String?, BigDecimal?>>,
    stakeCoins: MutableList<Coin>,
    private val nativeCoins: MutableList<Coin>,
    private val ibcCoins: MutableList<Coin>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_MAIN_ITEM = 0
        const val VIEW_TYPE_COIN_HEADER = 1
        const val VIEW_TYPE_COIN_ITEM = 2
        const val VIEW_TYPE_IBC_HEADER = 3
        const val VIEW_TYPE_IBC_ITEM = 4
    }

    private var onItemClickListener: ((BaseChain, String) -> Unit)? = null

    private var items: MutableList<ListItem> = mutableListOf()

    init {
        if (selectedChain is ChainNamada) {
            setItems(stakeCoins, nativeCoins, ibcCoins)
        }
    }

    fun setItems(
        stakeCoins: MutableList<Coin>,
        nativeCoins: MutableList<Coin>,
        ibcCoins: MutableList<Coin>
    ) {
        val tempList = mutableListOf<ListItem>()
        if (stakeCoins.isNotEmpty()) {
            stakeCoins.forEach { tempList.add(ListItem(ItemType.STAKE_ITEM, it)) }
        }
        if (nativeCoins.isNotEmpty()) {
            tempList.add(ListItem(ItemType.NATIVE_HEADER))
            nativeCoins.forEach { tempList.add(ListItem(ItemType.NATIVE_ITEM, it)) }
        }
        if (ibcCoins.isNotEmpty()) {
            tempList.add(ListItem(ItemType.IBC_HEADER))
            ibcCoins.forEach { tempList.add(ListItem(ItemType.IBC_ITEM, it)) }
        }
        items = tempList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_MAIN_ITEM -> {
                val binding = ItemMajorCryptoBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                MajorCryptoViewHolder(binding)
            }

            VIEW_TYPE_COIN_HEADER, VIEW_TYPE_IBC_HEADER -> {
                val binding = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                MajorCryptoHeaderViewHolder(binding)
            }

            VIEW_TYPE_COIN_ITEM, VIEW_TYPE_IBC_ITEM -> {
                val binding = ItemCosmosTokenBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                CoinViewHolder(parent.context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MajorCryptoViewHolder -> {
                if (holder.itemViewType == VIEW_TYPE_MAIN_ITEM) {
                    when (selectedChain) {
                        is ChainSui -> {
                            holder.bind(selectedChain)
                            holder.itemView.setOnClickListener {
                                onItemClickListener?.let {
                                    it(selectedChain, SUI_MAIN_DENOM)
                                }
                            }
                        }

                        is ChainBitCoin84 -> {
                            holder.bitcoinBind(selectedChain)
                            holder.itemView.setOnClickListener {
                                onItemClickListener?.let {
                                    it(selectedChain, "")
                                }
                            }
                        }

                        else -> {
                            val item = items[position]
                            val coin = item.coin ?: return
                            holder.namadaBind(selectedChain as ChainNamada, coin)
                            holder.itemView.setOnClickListener {
                                onItemClickListener?.let {
                                    it(selectedChain, coin.denom)
                                }
                            }
                        }
                    }
                }
            }

            is MajorCryptoHeaderViewHolder -> {
                if (holder.itemViewType == VIEW_TYPE_COIN_HEADER || holder.itemViewType == VIEW_TYPE_IBC_HEADER) {
                    holder.bind(position)
                }
            }

            is CoinViewHolder -> {
                if (holder.itemViewType == VIEW_TYPE_COIN_ITEM) {
                    if (selectedChain is ChainSui) {
                        val balance = if (suiBalances.isNotEmpty()) {
                            suiNativeBalances[position - 2]
                        } else {
                            suiNativeBalances[position - 1]
                        }
                        holder.suiBind(selectedChain, balance)
                        holder.itemView.setOnClickListener {
                            onItemClickListener?.let {
                                balance.first?.let { denom ->
                                    it(selectedChain, denom)
                                }
                            }
                        }

                    } else {
                        val item = items[position]
                        val coin = item.coin ?: return
                        holder.bind(selectedChain, coin)
                    }

                } else {
                    val item = items[position]
                    val coin = item.coin ?: return
                    holder.bind(selectedChain, coin)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (selectedChain is ChainSui) {
            if (suiBalances.isNotEmpty()) {
                return if (suiNativeBalances.isNotEmpty()) {
                    when (position) {
                        0 -> VIEW_TYPE_MAIN_ITEM
                        1 -> VIEW_TYPE_COIN_HEADER
                        else -> VIEW_TYPE_COIN_ITEM
                    }
                } else {
                    VIEW_TYPE_MAIN_ITEM
                }

            } else {
                return if (suiNativeBalances.isNotEmpty()) {
                    if (position == 0) VIEW_TYPE_COIN_HEADER
                    else VIEW_TYPE_COIN_ITEM
                } else {
                    -1
                }
            }

        } else if (selectedChain is ChainBitCoin84) {
            return VIEW_TYPE_MAIN_ITEM

        } else {
            return when (items[position].type) {
                ItemType.STAKE_ITEM -> VIEW_TYPE_MAIN_ITEM
                ItemType.NATIVE_HEADER -> VIEW_TYPE_COIN_HEADER
                ItemType.NATIVE_ITEM -> VIEW_TYPE_COIN_ITEM
                ItemType.IBC_HEADER -> VIEW_TYPE_IBC_HEADER
                else -> VIEW_TYPE_IBC_ITEM
            }
        }
    }

    override fun getItemCount(): Int {
        return if (selectedChain is ChainSui) {
            return if (suiBalances.isNotEmpty()) {
                if (suiNativeBalances.isNotEmpty()) {
                    suiBalances.size + suiNativeBalances.size + 1
                } else {
                    suiBalances.size
                }

            } else {
                if (suiNativeBalances.isNotEmpty()) {
                    suiNativeBalances.size + 1
                } else {
                    0
                }
            }

        } else if (selectedChain is ChainBitCoin84) {
            1
        } else {
            items.size
        }
    }

    inner class MajorCryptoHeaderViewHolder(
        private val binding: ItemHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.apply {
                if (getItemViewType(position) == VIEW_TYPE_COIN_HEADER) {
                    headerTitle.text = context.getString(R.string.str_native_coins)
                    headerCnt.text = when (selectedChain) {
                        is ChainSui -> {
                            suiNativeBalances.size.toString()
                        }

                        is ChainNamada -> {
                            nativeCoins.size.toString()
                        }

                        else -> {
                            "0"
                        }
                    }

                } else {
                    headerTitle.text = context.getString(R.string.str_ibc_coins)
                    headerCnt.text = ibcCoins.size.toString()
                }
            }
        }
    }

    fun setOnItemClickListener(listener: (BaseChain, String) -> Unit) {
        onItemClickListener = listener
    }
}