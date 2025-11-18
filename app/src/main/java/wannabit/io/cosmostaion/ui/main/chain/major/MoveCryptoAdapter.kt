package wannabit.io.cosmostaion.ui.main.chain.major

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainIota
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.majorClass.IOTA_MAIN_DENOM
import wannabit.io.cosmostaion.chain.majorClass.SUI_MAIN_DENOM
import wannabit.io.cosmostaion.databinding.ItemCosmosTokenBinding
import wannabit.io.cosmostaion.databinding.ItemHeaderBinding
import wannabit.io.cosmostaion.databinding.ItemMajorCryptoBinding
import wannabit.io.cosmostaion.ui.main.chain.cosmos.CoinViewHolder
import java.math.BigDecimal

class MoveCryptoAdapter(
    val context: Context,
    val selectedChain: BaseChain,
    private val moveBalances: MutableList<Pair<String?, BigDecimal?>>,
    private val moveNativeBalances: MutableList<Pair<String?, BigDecimal?>>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_MAIN_ITEM = 0
        const val VIEW_TYPE_APTOS_MAIN_ITEM = 1
        const val VIEW_TYPE_COIN_HEADER = 2
        const val VIEW_TYPE_COIN_ITEM = 3
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

            VIEW_TYPE_COIN_HEADER -> {
                val binding = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                MajorCryptoHeaderViewHolder(binding)
            }

            VIEW_TYPE_COIN_ITEM -> {
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

                        else -> {

                        }
                    }
                }
            }

            is MajorCryptoHeaderViewHolder -> {
                if (holder.itemViewType == VIEW_TYPE_COIN_HEADER) {
                    holder.bind()
                }
            }

            is CoinViewHolder -> {
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

    override fun getItemViewType(position: Int): Int {
        if (selectedChain is ChainSui || selectedChain is ChainIota) {
            if (moveBalances.isNotEmpty()) {
                return if (moveNativeBalances.isNotEmpty()) {
                    when (position) {
                        0 -> VIEW_TYPE_MAIN_ITEM
                        1 -> VIEW_TYPE_COIN_HEADER
                        else -> VIEW_TYPE_COIN_ITEM
                    }
                } else {
                    VIEW_TYPE_MAIN_ITEM
                }

            } else {
                return if (moveNativeBalances.isNotEmpty()) {
                    if (position == 0) VIEW_TYPE_COIN_HEADER
                    else VIEW_TYPE_COIN_ITEM
                } else {
                    -1
                }
            }

        } else {
            return VIEW_TYPE_MAIN_ITEM
        }
    }

    override fun getItemCount(): Int {
        return if (selectedChain is ChainSui || selectedChain is ChainIota) {
            return if (moveBalances.isNotEmpty()) {
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

        } else {
            1
        }
    }

    inner class MajorCryptoHeaderViewHolder(
        private val binding: ItemHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.apply {
                headerTitle.text = context.getString(R.string.str_native_coins)
                headerCnt.text = if (selectedChain is ChainSui || selectedChain is ChainIota) {
                    moveNativeBalances.size.toString()
                } else {
                    "0"
                }
            }
        }
    }

    fun setOnItemClickListener(listener: (BaseChain, String) -> Unit) {
        onItemClickListener = listener
    }
}