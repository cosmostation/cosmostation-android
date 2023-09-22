package wannabit.io.cosmostaion.ui.main

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.ChainType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.common.priceChangeStatus
import wannabit.io.cosmostaion.common.priceChangeStatusColor
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.ItemDashBinding


class DashboardViewHolder(
    val context: Context,
    private val binding: ItemDashBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(account: BaseAccount, line: CosmosLine) {
        binding.apply {
            dashView.setBackgroundResource(R.drawable.item_bg)

            when (line.chainType) {
                ChainType.COSMOS_TYPE -> {
                    chainImg.setImageResource(line.logo)
                    chainSwipeImg.setImageResource(line.swipeLogo)
                    chainName.text = line.name.uppercase()

                    if (!line.isDefault) {
                        chainPath.text = line.getHDPath(account.lastHDPath)
                    }
                    chainPath.goneOrVisible(line.isDefault)
                    chainLegacy.goneOrVisible(line.isDefault)

                    if (line.fetched) {
                        skeletonChainValue.visibility = View.GONE
                        skeletonChainPrice.visibility = View.GONE
                        chainValue.text = formatAssetValue(line.allAssetValue())

                        var coinGeckoId: String? = ""
                        if (line is ChainBinanceBeacon) {
                            coinGeckoId = ChainBinanceBeacon().BNB_GECKO_ID
                        } else {
                            BaseData.getAsset(line.apiName, line.stakeDenom)?.let { asset ->
                                coinGeckoId = asset.coinGeckoId
                            }
                        }
                        BaseData.lastUpDown(coinGeckoId).let { lastUpDown ->
                            chainPrice.priceChangeStatusColor(lastUpDown)
                            chainPrice.text = priceChangeStatus(lastUpDown)
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









