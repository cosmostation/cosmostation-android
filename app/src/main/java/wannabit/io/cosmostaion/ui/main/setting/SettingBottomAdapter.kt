package wannabit.io.cosmostaion.ui.main.setting

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.databinding.ItemBuyCryptoBinding
import wannabit.io.cosmostaion.databinding.ItemCurrencyBinding
import wannabit.io.cosmostaion.databinding.ItemPriceStyleBinding
import wannabit.io.cosmostaion.databinding.ItemSettingBottomBinding
import wannabit.io.cosmostaion.ui.main.SettingType

class SettingBottomAdapter(
    val context: Context,
    private val settingType: SettingType
) : ListAdapter<String, RecyclerView.ViewHolder>(SettingDiffCallback()) {

    private var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (settingType.ordinal) {
            SettingType.LANGUAGE.ordinal, SettingType.AUTO_PASS.ordinal -> {
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

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (settingType.ordinal) {
            SettingType.LANGUAGE.ordinal, SettingType.AUTO_PASS.ordinal -> {
                if (holder is SettingBottomViewHolder) {
                    val stringItem = currentList[position]
                    holder.bind(stringItem, settingType.ordinal)

                    holder.itemView.setOnClickListener {
                        onItemClickListener?.let { it(position) }
                    }
                }
            }

            SettingType.CURRENCY.ordinal -> {
                if (holder is CurrencyViewHolder) {
                    val currency = currentList[position]
                    holder.bind(currency)

                    holder.itemView.setOnClickListener {
                        onItemClickListener?.let { it(position) }
                    }
                }
            }

            SettingType.PRICE_STATUS.ordinal -> {
                if (holder is PriceStyleViewHolder) {
                    val style = currentList[position]
                    holder.bind(style)

                    holder.itemView.setOnClickListener {
                        onItemClickListener?.let { it(position) }
                    }
                }
            }

            SettingType.BUY_CRYPTO.ordinal -> {
                if (holder is BuyCryptoViewHolder) {
                    val buy = currentList[position]
                    holder.bind(buy)

                    holder.itemView.setOnClickListener {
                        onItemClickListener?.let { it(position) }
                    }
                }
            }
        }
    }

    private class SettingDiffCallback : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }
}