package wannabit.io.cosmostaion.ui.main

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.ChainType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.priceChangeStatus
import wannabit.io.cosmostaion.databinding.ItemDashBinding
import java.math.BigDecimal


class DashboardViewHolder(
    val context: Context,
    private val binding: ItemDashBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(line: CosmosLine) {
        binding.apply {
            dashView.setBackgroundResource(R.drawable.item_dash_bg)

            when (line.chainType) {
                ChainType.COSMOS_TYPE -> {
                    chainName.text = line.name.uppercase()
                    chainValue.text = formatAssetValue(line.allAssetValue())

                    if (line.fetched) {
                        BaseData.getAsset(line.apiName, line.stakeDenom)?.let {
                            BaseData.lastUpDown(it.coinGeckoId).let { lastUpDown ->
                                if (BigDecimal.ZERO > lastUpDown) {
                                    chainPrice.setTextColor(ContextCompat.getColorStateList(context, R.color.color_accent_red))
                                } else {
                                    chainPrice.setTextColor(ContextCompat.getColorStateList(context, R.color.color_accent_green))
                                }
                                chainPrice.text = priceChangeStatus(lastUpDown)
                            }
                        }
                    } else {
                        chainPrice.text = priceChangeStatus("0.00".toBigDecimal())
                    }
                }

                // eth, sui
                else -> {}
            }
        }
    }
}









