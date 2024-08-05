package wannabit.io.cosmostaion.ui.main.setting

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.databinding.ItemBuyCryptoBinding
import wannabit.io.cosmostaion.databinding.ItemCurrencyBinding
import wannabit.io.cosmostaion.databinding.ItemEndpointBinding
import wannabit.io.cosmostaion.databinding.ItemHeaderViewBinding
import wannabit.io.cosmostaion.databinding.ItemPriceStyleBinding
import wannabit.io.cosmostaion.databinding.ItemSettingBottomBinding
import wannabit.io.cosmostaion.ui.main.SettingType
import wannabit.io.cosmostaion.ui.main.setting.general.CurrencyViewHolder
import wannabit.io.cosmostaion.ui.main.setting.general.PriceStyleViewHolder
import wannabit.io.cosmostaion.ui.main.setting.wallet.chain.EndPointViewHolder

class SettingBottomAdapter(
    val context: Context,
    private val fromChain: BaseChain?,
    private val grpcEndpoints: MutableList<Any>?,
    private val lcdEndpoints: MutableList<Any>,
    private val settingType: SettingType,
    val listener: EndpointListener?
) : ListAdapter<Any, RecyclerView.ViewHolder>(SettingDiffCallback()) {

    companion object {
        const val VIEW_TYPE_GRPC_HEADER = 0
        const val VIEW_TYPE_GRPC_ITEM = 1
        const val VIEW_TYPE_LCD_HEADER = 2
        const val VIEW_TYPE_LCD_ITEM = 3
    }

    private var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_GRPC_HEADER, VIEW_TYPE_LCD_HEADER -> {
                val binding = ItemHeaderViewBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                EndpointHeaderViewHolder(binding)
            }

            VIEW_TYPE_GRPC_ITEM, VIEW_TYPE_LCD_ITEM -> {
                when (settingType.ordinal) {
                    SettingType.LANGUAGE.ordinal -> {
                        val binding = ItemSettingBottomBinding.inflate(
                            LayoutInflater.from(parent.context), parent, false
                        )
                        SettingBottomViewHolder(parent.context, binding)
                    }

                    SettingType.CURRENCY.ordinal -> {
                        val binding = ItemCurrencyBinding.inflate(
                            LayoutInflater.from(parent.context), parent, false
                        )
                        CurrencyViewHolder(parent.context, binding)
                    }

                    SettingType.PRICE_STATUS.ordinal -> {
                        val binding = ItemPriceStyleBinding.inflate(
                            LayoutInflater.from(parent.context), parent, false
                        )
                        PriceStyleViewHolder(parent.context, binding)
                    }

                    SettingType.BUY_CRYPTO.ordinal -> {
                        val binding = ItemBuyCryptoBinding.inflate(
                            LayoutInflater.from(parent.context), parent, false
                        )
                        BuyCryptoViewHolder(parent.context, binding)
                    }

                    SettingType.END_POINT_EVM.ordinal, SettingType.END_POINT_COSMOS.ordinal -> {
                        val binding = ItemEndpointBinding.inflate(
                            LayoutInflater.from(parent.context), parent, false
                        )
                        EndPointViewHolder(binding)
                    }

                    else -> throw IllegalArgumentException("Invalid view type")
                }
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_GRPC_HEADER, VIEW_TYPE_LCD_HEADER -> {
                if (holder is EndpointHeaderViewHolder) {
                    holder.bind(position)
                }
            }

            VIEW_TYPE_GRPC_ITEM, VIEW_TYPE_LCD_ITEM -> {
                when (settingType.ordinal) {
                    SettingType.LANGUAGE.ordinal -> {
                        if (holder is SettingBottomViewHolder) {
                            val stringItem = currentList[position] as String
                            holder.bind(stringItem)

                            holder.itemView.setOnClickListener {
                                onItemClickListener?.let { it(position) }
                            }
                        }
                    }

                    SettingType.CURRENCY.ordinal -> {
                        if (holder is CurrencyViewHolder) {
                            val currency = currentList[position] as String
                            holder.bind(currency)

                            holder.itemView.setOnClickListener {
                                onItemClickListener?.let { it(position) }
                            }
                        }
                    }

                    SettingType.PRICE_STATUS.ordinal -> {
                        if (holder is PriceStyleViewHolder) {
                            val style = currentList[position] as String
                            holder.bind(style)

                            holder.itemView.setOnClickListener {
                                onItemClickListener?.let { it(position) }
                            }
                        }
                    }

                    SettingType.BUY_CRYPTO.ordinal -> {
                        if (holder is BuyCryptoViewHolder) {
                            val buy = currentList[position] as String
                            holder.bind(buy)

                            holder.itemView.setOnClickListener {
                                onItemClickListener?.let { it(position) }
                            }
                        }
                    }

                    SettingType.END_POINT_EVM.ordinal -> {
                        val endPoint = currentList[position] as JsonObject
                        if (holder is EndPointViewHolder) {
                            holder.evmBind(fromChain, endPoint, listener)
                        }
                    }

                    SettingType.END_POINT_COSMOS.ordinal -> {
                        if (holder.itemViewType == VIEW_TYPE_GRPC_ITEM) {
                            val grpcEndpoint = grpcEndpoints?.get(position - 1) as JsonObject
                            if (holder is EndPointViewHolder) {
                                holder.bind(fromChain, grpcEndpoint, listener)
                            }
                        } else {
                            val lcdEndpoint =
                                lcdEndpoints[position - grpcEndpoints!!.size - 2] as JsonObject
                            if (holder is EndPointViewHolder) {
                                holder.lcdBind(fromChain, lcdEndpoint, listener)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (settingType.ordinal == SettingType.END_POINT_COSMOS.ordinal) {
            if (position == 0) VIEW_TYPE_GRPC_HEADER
            else if (position < grpcEndpoints!!.size + 1) VIEW_TYPE_GRPC_ITEM
            else if (position < grpcEndpoints.size + 2) VIEW_TYPE_LCD_HEADER
            else VIEW_TYPE_LCD_ITEM
        } else {
            VIEW_TYPE_GRPC_ITEM
        }
    }

    override fun getItemCount(): Int {
        return if (lcdEndpoints.isNotEmpty()) {
            currentList.size + 2
        } else {
            if (grpcEndpoints?.isNotEmpty() == true) {
                currentList.size + 1
            } else {
                currentList.size
            }
        }
    }

    private class SettingDiffCallback : DiffUtil.ItemCallback<Any>() {

        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }
    }

    inner class EndpointHeaderViewHolder(
        private val binding: ItemHeaderViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewType: Int) {
            binding.apply {
                if (viewType == VIEW_TYPE_GRPC_HEADER) {
                    headerTitle.text = "GRPC"
                    headerCnt.text = grpcEndpoints!!.size.toString()
                } else {
                    headerTitle.text = "Rest"
                    headerCnt.text = lcdEndpoints.size.toString()
                }
            }
        }
    }

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    interface EndpointListener {
        fun select(endpoint: String, gapTime: Double?)

        fun rpcSelect(endpoint: String, gapTime: Double?)

        fun lcdSelect(endpoint: String, gapTime: Double?)
    }
}