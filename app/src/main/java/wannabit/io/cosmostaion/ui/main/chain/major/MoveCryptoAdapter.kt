package wannabit.io.cosmostaion.ui.main.chain.major

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.APTOS_MAIN_DENOM
import wannabit.io.cosmostaion.chain.majorClass.ChainIota
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.majorClass.IOTA_MAIN_DENOM
import wannabit.io.cosmostaion.chain.majorClass.SUI_MAIN_DENOM
import wannabit.io.cosmostaion.data.model.res.Asset
import wannabit.io.cosmostaion.databinding.ItemCosmosTokenBinding
import wannabit.io.cosmostaion.databinding.ItemDefaultAssetViewBinding
import wannabit.io.cosmostaion.databinding.ItemHeaderBinding
import wannabit.io.cosmostaion.databinding.ItemMajorCryptoBinding
import wannabit.io.cosmostaion.ui.main.chain.cosmos.CoinViewHolder
import java.math.BigDecimal

class MoveCryptoAdapter(
    val context: Context,
    val selectedChain: BaseChain,
    private val moveBalances: MutableList<Pair<String?, BigDecimal?>>,
    private val moveNativeBalances: MutableList<Pair<String?, BigDecimal?>>,
    private val aptosNativeBalances: MutableList<Pair<Asset?, BigDecimal?>>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_MAIN_ITEM = 0
        const val VIEW_TYPE_APTOS_MAIN_ITEM = 1
        const val VIEW_TYPE_COIN_HEADER = 2
        const val VIEW_TYPE_COIN_ITEM = 3
        const val VIEW_TYPE_FUNGIBLE_HEADER = 4
        const val VIEW_TYPE_FUNGIBLE_ITEM = 5
    }

    private var onItemClickListener: ((BaseChain, String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_MAIN_ITEM -> {
                val binding = ItemMajorCryptoBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                MoveAssetViewHolder(binding)
            }

            VIEW_TYPE_APTOS_MAIN_ITEM -> {
                val binding = ItemDefaultAssetViewBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                MoveAptosAssetViewHolder(binding)
            }

            VIEW_TYPE_COIN_HEADER, VIEW_TYPE_FUNGIBLE_HEADER -> {
                val binding = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                MajorCryptoHeaderViewHolder(binding)
            }

            VIEW_TYPE_COIN_ITEM, VIEW_TYPE_FUNGIBLE_ITEM -> {
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
            is MoveAssetViewHolder -> {
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

                        is ChainIota -> {
                            holder.iotaBind(selectedChain)
                            holder.itemView.setOnClickListener {
                                onItemClickListener?.let {
                                    it(selectedChain, IOTA_MAIN_DENOM)
                                }
                            }
                        }
                    }
                }
            }

            is MoveAptosAssetViewHolder -> {
                holder.bind(selectedChain)
                holder.itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(selectedChain, APTOS_MAIN_DENOM)
                    }
                }
            }

            is MajorCryptoHeaderViewHolder -> {
                if (holder.itemViewType == VIEW_TYPE_COIN_HEADER || holder.itemViewType == VIEW_TYPE_FUNGIBLE_HEADER) {
                    holder.bind()
                }
            }

            is CoinViewHolder -> {
                if (selectedChain.isMoveChain) {
                    val fungibleBalances =
                        aptosNativeBalances.filter { it.first?.type == "fungible" }
                    val nativeBalances = aptosNativeBalances.filter { it.first?.type == "bridge" }

                    val balance = if (holder.itemViewType == VIEW_TYPE_COIN_ITEM) {
                        nativeBalances[position - 2]

                    } else {
                        val fungiblePosition = if (nativeBalances.isNotEmpty()) {
                            position - nativeBalances.size - 3
                        } else {
                            position - 2
                        }
                        fungibleBalances[fungiblePosition]
                    }

                    holder.aptosBind(selectedChain, balance)
                    holder.itemView.setOnClickListener {
                        onItemClickListener?.let {
                            balance.first?.denom?.let { denom ->
                                it(selectedChain, denom)
                            }
                        }
                    }

                } else {
                    if (holder.itemViewType == VIEW_TYPE_COIN_ITEM) {
                        val balance = if (moveBalances.isNotEmpty()) {
                            moveNativeBalances[position - 2]
                        } else {
                            moveNativeBalances[position - 1]
                        }

                        if (selectedChain is ChainSui) {
                            holder.suiBind(selectedChain, balance)
                        } else {
                            holder.iotaBind(selectedChain, balance)
                        }

                        holder.itemView.setOnClickListener {
                            onItemClickListener?.let {
                                balance.first?.let { denom ->
                                    it(selectedChain, denom)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (selectedChain.isMoveChain) {
            val nativeBalances = aptosNativeBalances.filter { it.first?.type == "bridge" }
            if (nativeBalances.isNotEmpty()) {
                if (position == 0) VIEW_TYPE_APTOS_MAIN_ITEM
                else if (position == 1) VIEW_TYPE_COIN_HEADER
                else if (position < nativeBalances.size + 2) VIEW_TYPE_COIN_ITEM
                else if (position < nativeBalances.size + 3) VIEW_TYPE_FUNGIBLE_HEADER
                else VIEW_TYPE_FUNGIBLE_ITEM

            } else {
                when (position) {
                    0 -> VIEW_TYPE_APTOS_MAIN_ITEM
                    1 -> VIEW_TYPE_FUNGIBLE_HEADER
                    else -> VIEW_TYPE_FUNGIBLE_ITEM
                }
            }

        } else {
            if (moveBalances.isNotEmpty()) {
                if (moveNativeBalances.isNotEmpty()) {
                    when (position) {
                        0 -> VIEW_TYPE_MAIN_ITEM
                        1 -> VIEW_TYPE_COIN_HEADER
                        else -> VIEW_TYPE_COIN_ITEM
                    }
                } else {
                    VIEW_TYPE_MAIN_ITEM
                }

            } else {
                if (moveNativeBalances.isNotEmpty()) {
                    if (position == 0) VIEW_TYPE_COIN_HEADER
                    else VIEW_TYPE_COIN_ITEM
                } else {
                    -1
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (selectedChain.isMoveChain) {
            val hasNative = aptosNativeBalances.any { it.first?.type == "bridge" }
            val hasFungible = aptosNativeBalances.any { it.first?.type == "fungible" }

            if (hasNative && hasFungible) {
                aptosNativeBalances.size + 3
            } else if (hasNative || hasFungible) {
                aptosNativeBalances.size + 2
            } else {
                1
            }

        } else {
            if (moveBalances.isNotEmpty()) {
                if (moveNativeBalances.isNotEmpty()) {
                    moveBalances.size + moveNativeBalances.size + 1
                } else {
                    moveBalances.size
                }

            } else {
                if (moveNativeBalances.isNotEmpty()) {
                    moveNativeBalances.size + 1
                } else {
                    0
                }
            }
        }
    }

    inner class MajorCryptoHeaderViewHolder(
        private val binding: ItemHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.apply {
                if (selectedChain.isMoveChain) {
                    if (itemViewType == VIEW_TYPE_COIN_HEADER) {
                        headerTitle.text = context.getString(R.string.str_coins)
                        headerCnt.text =
                            aptosNativeBalances.filter { it.first?.type == "bridge" }.size.toString()
                    } else {
                        headerTitle.text = context.getString(R.string.str_fungible_coins)
                        headerCnt.text =
                            aptosNativeBalances.filter { it.first?.type == "fungible" }.size.toString()
                    }

                } else {
                    headerTitle.text = context.getString(R.string.str_native_coins)
                    headerCnt.text = moveNativeBalances.size.toString()
                }
            }
        }
    }

    fun setOnItemClickListener(listener: (BaseChain, String) -> Unit) {
        onItemClickListener = listener
    }
}