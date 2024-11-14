package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.data.model.res.Coin
import wannabit.io.cosmostaion.data.model.res.CoinType
import wannabit.io.cosmostaion.databinding.ItemCosmosLineCoinBinding
import wannabit.io.cosmostaion.databinding.ItemCosmosLineEtcBinding
import wannabit.io.cosmostaion.databinding.ItemCosmosLineTokenBinding
import wannabit.io.cosmostaion.databinding.ItemCosmosTokenBinding
import wannabit.io.cosmostaion.databinding.ItemHeaderBinding

class CoinAdapter(
    val context: Context, val selectedChain: BaseChain,
    stakeCoins: MutableList<Coin>,
    private val nativeCoins: MutableList<Coin>,
    private val bridgeCoins: MutableList<Coin>,
    private val ibcCoins: MutableList<Coin>,
    private val etcCoins: MutableList<Coin>,
    private val tokenCoins: MutableList<Coin>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onItemClickListener: ((BaseChain, String, Int, CoinType) -> Unit)? = null

    companion object {
        const val VIEW_TYPE_STAKE_ITEM = 0
        const val VIEW_TYPE_NATIVE_HEADER = 1
        const val VIEW_TYPE_NATIVE_ITEM = 2
        const val VIEW_TYPE_IBC_HEADER = 3
        const val VIEW_TYPE_IBC_ITEM = 4
        const val VIEW_TYPE_BRIDGE_HEADER = 5
        const val VIEW_TYPE_BRIDGE_ITEM = 6
        const val VIEW_TYPE_ETC_HEADER = 7
        const val VIEW_TYPE_ETC_ITEM = 8
        const val VIEW_TYPE_TOKEN_HEADER = 9
        const val VIEW_TYPE_TOKEN_ITEM = 10
    }

    private var items: MutableList<ListItem> = mutableListOf()

    init {
        when (selectedChain) {
            is ChainOkt996Keccak -> {
                setOktItems(stakeCoins, etcCoins)
            }

            is ChainOktEvm -> {
                setOktEvmItems(stakeCoins, etcCoins, tokenCoins)
            }

            else -> {
                setItems(stakeCoins, nativeCoins, ibcCoins, bridgeCoins, tokenCoins)
            }
        }
    }

    fun setOktItems(
        stakeCoins: MutableList<Coin>, etcCoins: MutableList<Coin>
    ) {
        val tempList = mutableListOf<ListItem>()
        if (stakeCoins.isNotEmpty()) {
            stakeCoins.forEach { tempList.add(ListItem(ItemType.STAKE_ITEM, it)) }
        }
        if (etcCoins.isNotEmpty()) {
            tempList.add(ListItem(ItemType.ETC_HEADER))
            etcCoins.forEach { tempList.add(ListItem(ItemType.ETC_ITEM, it)) }
        }
        items = tempList
        notifyDataSetChanged()
    }

    fun setOktEvmItems(
        stakeCoins: MutableList<Coin>, etcCoins: MutableList<Coin>, tokenCoins: MutableList<Coin>
    ) {
        val tempList = mutableListOf<ListItem>()
        if (stakeCoins.isNotEmpty()) {
            stakeCoins.forEach { tempList.add(ListItem(ItemType.STAKE_ITEM, it)) }
        }
        if (etcCoins.isNotEmpty()) {
            tempList.add(ListItem(ItemType.ETC_HEADER))
            etcCoins.forEach { tempList.add(ListItem(ItemType.ETC_ITEM, it)) }
        }
        if (tokenCoins.isNotEmpty()) {
            tempList.add(ListItem(ItemType.TOKEN_HEADER))
            tokenCoins.forEach { tempList.add(ListItem(ItemType.TOKEN_ITEM, it)) }
        }
        items = tempList
        notifyDataSetChanged()
    }

    fun setItems(
        stakeCoins: MutableList<Coin>,
        nativeCoins: MutableList<Coin>,
        ibcCoins: MutableList<Coin>,
        bridgeCoins: MutableList<Coin>,
        tokenCoins: MutableList<Coin>
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
        if (bridgeCoins.isNotEmpty()) {
            tempList.add(ListItem(ItemType.BRIDGE_HEADER))
            bridgeCoins.forEach { tempList.add(ListItem(ItemType.BRIDGE_ITEM, it)) }
        }
        if (tokenCoins.isNotEmpty()) {
            tempList.add(ListItem(ItemType.TOKEN_HEADER))
            tokenCoins.forEach { tempList.add(ListItem(ItemType.TOKEN_ITEM, it)) }
        }
        items = tempList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_NATIVE_HEADER, VIEW_TYPE_IBC_HEADER, VIEW_TYPE_BRIDGE_HEADER, VIEW_TYPE_ETC_HEADER, VIEW_TYPE_TOKEN_HEADER -> {
                val binding = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                CryptoHeaderViewHolder(binding)
            }

            VIEW_TYPE_STAKE_ITEM -> {
                val binding = ItemCosmosLineCoinBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                CoinCosmosLineViewHolder(binding)
            }

            VIEW_TYPE_NATIVE_ITEM, VIEW_TYPE_IBC_ITEM, VIEW_TYPE_BRIDGE_ITEM -> {
                val binding = ItemCosmosTokenBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                CoinViewHolder(parent.context, binding)
            }

            VIEW_TYPE_ETC_ITEM -> {
                val binding = ItemCosmosLineEtcBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                CoinEtcViewHolder(parent.context, binding)
            }

            VIEW_TYPE_TOKEN_ITEM -> {
                val binding = ItemCosmosLineTokenBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                TokenViewHolder(parent.context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is CryptoHeaderViewHolder -> {
                holder.bind(selectedChain, position)
            }

            is CoinCosmosLineViewHolder -> {
                val coin = item.coin ?: return
                holder.bind(context, selectedChain)

                holder.itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(selectedChain, selectedChain.stakeDenom, position, coin.type)
                    }
                }

                holder.itemView.setOnLongClickListener { view ->
                    if (selectedChain.isStakeEnabled() && selectedChain.cosmosFetcher?.cosmosRewards?.isNotEmpty() == true) {
                        val scaleX = view.scaleX
                        val scaleY = view.scaleY
                        val customDialog = RewardDialog(
                            context, selectedChain, selectedChain.cosmosFetcher?.cosmosRewards
                        )

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
                            it(selectedChain, selectedChain.stakeDenom, position, coin.type)
                        }
                        true
                    }
                }
            }

            is CoinViewHolder -> {
                val coin = item.coin ?: return
                holder.bind(selectedChain, coin)

                holder.itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(selectedChain, coin.denom, position, coin.type)
                    }
                }
            }

            is CoinEtcViewHolder -> {
                val coin = item.coin ?: return
                holder.bindOktCoin(selectedChain, coin)

                holder.itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(selectedChain, coin.denom, position, coin.type)
                    }
                }
            }

            is TokenViewHolder -> {
                val coin = item.coin ?: return
                holder.bind(selectedChain, coin)

                holder.itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(selectedChain, coin.denom, position, coin.type)
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {
            ItemType.STAKE_ITEM -> VIEW_TYPE_STAKE_ITEM
            ItemType.NATIVE_HEADER -> VIEW_TYPE_NATIVE_HEADER
            ItemType.NATIVE_ITEM -> VIEW_TYPE_NATIVE_ITEM
            ItemType.IBC_HEADER -> VIEW_TYPE_IBC_HEADER
            ItemType.IBC_ITEM -> VIEW_TYPE_IBC_ITEM
            ItemType.BRIDGE_HEADER -> VIEW_TYPE_BRIDGE_HEADER
            ItemType.BRIDGE_ITEM -> VIEW_TYPE_BRIDGE_ITEM
            ItemType.ETC_HEADER -> VIEW_TYPE_ETC_HEADER
            ItemType.ETC_ITEM -> VIEW_TYPE_ETC_ITEM
            ItemType.TOKEN_HEADER -> VIEW_TYPE_TOKEN_HEADER
            ItemType.TOKEN_ITEM -> VIEW_TYPE_TOKEN_ITEM
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class CryptoHeaderViewHolder(
        private val binding: ItemHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(chain: BaseChain, position: Int) {
            binding.apply {
                if (getItemViewType(position) == VIEW_TYPE_NATIVE_HEADER) {
                    headerTitle.text = context.getString(R.string.str_native_coins)
                    headerCnt.text = nativeCoins.size.toString()

                } else if (getItemViewType(position) == VIEW_TYPE_IBC_HEADER) {
                    headerTitle.text = context.getString(R.string.str_ibc_coins)
                    headerCnt.text = ibcCoins.size.toString()

                } else if (getItemViewType(position) == VIEW_TYPE_BRIDGE_HEADER) {
                    headerTitle.text = context.getString(R.string.str_bridge_coins)
                    headerCnt.text = bridgeCoins.size.toString()

                } else if (getItemViewType(position) == VIEW_TYPE_ETC_HEADER) {
                    headerTitle.text = context.getString(R.string.str_native_coins)
                    headerCnt.text = etcCoins.size.toString()

                } else {
                    headerTitle.text = if (selectedChain.isSupportCw20()) {
                        context.getString(R.string.str_contract_tokens)
                    } else {
                        if (selectedChain is ChainOktEvm) {
                            context.getString(R.string.str_kip20_tokens)
                        } else {
                            context.getString(R.string.str_erc20_tokens)
                        }
                    }
                    headerCnt.text = tokenCoins.size.toString()
                }
            }
        }
    }

    fun setOnItemClickListener(listener: (BaseChain, String, Int, CoinType) -> Unit) {
        onItemClickListener = listener
    }
}

enum class ItemType {
    STAKE_ITEM, NATIVE_HEADER, NATIVE_ITEM, IBC_HEADER, IBC_ITEM, BRIDGE_HEADER, BRIDGE_ITEM, ETC_HEADER, ETC_ITEM, TOKEN_HEADER, TOKEN_ITEM
}

data class ListItem(val type: ItemType, val coin: Coin? = null)