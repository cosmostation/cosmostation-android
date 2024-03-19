package wannabit.io.cosmostaion.ui.main.setting

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.databinding.ItemBuyCryptoBinding
import wannabit.io.cosmostaion.databinding.ItemCurrencyBinding
import wannabit.io.cosmostaion.databinding.ItemEndpointBinding
import wannabit.io.cosmostaion.databinding.ItemPriceStyleBinding
import wannabit.io.cosmostaion.databinding.ItemSettingBottomBinding
import wannabit.io.cosmostaion.ui.main.SettingType
import wannabit.io.cosmostaion.ui.main.setting.general.CurrencyViewHolder
import wannabit.io.cosmostaion.ui.main.setting.general.PriceStyleViewHolder
import wannabit.io.cosmostaion.ui.main.setting.wallet.chain.EndPointViewHolder

class SettingBottomAdapter(
    val context: Context,
    private val fromChain: CosmosLine?,
    private val settingType: SettingType,
    val listener: EndpointListener?
) : ListAdapter<Any, RecyclerView.ViewHolder>(SettingDiffCallback()) {

    private var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (settingType.ordinal) {
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

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
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
                    holder.evmBind(fromChain as EthereumLine, endPoint, listener)
                }
            }

            SettingType.END_POINT_COSMOS.ordinal -> {
                val endPoint = currentList[position] as JsonObject
                if (holder is EndPointViewHolder) {
                    holder.bind(fromChain, endPoint, listener)
                }
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

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    interface EndpointListener {
        fun select(endpoint: String, gapTime: Double?)

        fun rpcSelect(endpoint: String, gapTime: Double?)
    }
}