package wannabit.io.cosmostaion.ui.main

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.cosmosClass.OKT_GECKO_ID
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.priceChangeStatus
import wannabit.io.cosmostaion.common.priceChangeStatusColor
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemDashBinding


class DashboardViewHolder(
    val context: Context, private val binding: ItemDashBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun evmBind(line: EthereumLine) {
        binding.apply {
            dashView.setBackgroundResource(R.drawable.item_bg)

            chainImg.setImageResource(line.logo)
            chainSwipeImg.setImageResource(line.swipeLogo)
            chainName.text = line.name.uppercase()

            line.stakeDenom?.let { denom ->
                if (line is ChainOktEvm) {
                    chainPrice.text = formatAssetValue(BaseData.getPrice(OKT_GECKO_ID))
                    BaseData.lastUpDown(OKT_GECKO_ID).let { lastUpDown ->
                        chainPriceStatus.priceChangeStatusColor(lastUpDown)
                        chainPriceStatus.text = priceChangeStatus(lastUpDown)
                    }

                } else if (line.supportCosmos) {
                    BaseData.getAsset(line.apiName, denom)?.let { asset ->
                        chainPrice.text = formatAssetValue(BaseData.getPrice(asset.coinGeckoId))
                        BaseData.lastUpDown(asset.coinGeckoId).let { lastUpDown ->
                            chainPriceStatus.priceChangeStatusColor(lastUpDown)
                            chainPriceStatus.text = priceChangeStatus(lastUpDown)
                        }
                    }

                } else {
                    chainPrice.text = formatAssetValue(BaseData.getPrice(line.coinGeckoId))
                    BaseData.lastUpDown(line.coinGeckoId).let { lastUpDown ->
                        chainPriceStatus.priceChangeStatusColor(lastUpDown)
                        chainPriceStatus.text = priceChangeStatus(lastUpDown)
                    }
                }
            }

            listOf(chainBadge, chainTypeBadge, chainSideBadge, chainSideTypeBadge).forEach { it.visibility = View.GONE }
            if (Prefs.style == 0) {
                chainCw20Badge.visibleOrGone(line.supportCw20)
                chainNftBadge.visibleOrGone(line.supportNft)
                chainPrice.visibility = View.GONE
                chainPriceStatus.visibility = View.GONE

            } else {
                chainCw20Badge.visibleOrGone(line.supportCw20)
                chainNftBadge.visibleOrGone(line.supportNft)
                chainPrice.visibility = View.VISIBLE
                chainPriceStatus.visibility = View.VISIBLE
            }

            if (line.fetched) {
                skeletonChainValue.visibility = View.GONE
                if (line !is ChainOktEvm) {
                    if (line.supportCosmos && line.cosmosBalances == null) {
                        respondLayout.visibility = View.VISIBLE
                        chainValue.visibility = View.GONE
                        return
                    }
                }

                if (line.web3j == null) {
                    respondLayout.visibility = View.VISIBLE
                    chainValue.visibility = View.GONE
                    return
                }

                respondLayout.visibility = View.GONE
                chainValue.visibility = View.VISIBLE
                if (Prefs.hideValue) {
                    chainValue.text = "✱✱✱✱"
                    chainValue.textSize = 10f
                } else {
                    chainValue.text = formatAssetValue(line.allValue(false))
                    chainValue.textSize = 14f
                }
            }
        }
    }

    fun bind(line: CosmosLine) {
        binding.apply {
            dashView.setBackgroundResource(R.drawable.item_bg)

            chainImg.setImageResource(line.logo)
            chainSwipeImg.setImageResource(line.swipeLogo)
            chainName.text = line.name.uppercase()

            line.stakeDenom?.let { denom ->
                if (line is ChainOkt996Keccak) {
                    chainPrice.text = formatAssetValue(BaseData.getPrice(OKT_GECKO_ID))
                    BaseData.lastUpDown(OKT_GECKO_ID).let { lastUpDown ->
                        chainPriceStatus.priceChangeStatusColor(lastUpDown)
                        chainPriceStatus.text = priceChangeStatus(lastUpDown)
                    }

                } else {
                    BaseData.getAsset(line.apiName, denom)?.let { asset ->
                        chainPrice.text = formatAssetValue(BaseData.getPrice(asset.coinGeckoId))
                        BaseData.lastUpDown(asset.coinGeckoId).let { lastUpDown ->
                            chainPriceStatus.priceChangeStatusColor(lastUpDown)
                            chainPriceStatus.text = priceChangeStatus(lastUpDown)
                        }
                    }
                }
            }

            if (Prefs.style == 0) {
                chainPrice.visibility = View.GONE
                chainPriceStatus.visibility = View.GONE
                if (!line.isDefault) {
                    chainBadge.setBackgroundResource(R.drawable.round_box_deprecated)
                    chainBadge.setTextColor(
                        ContextCompat.getColor(
                            context, R.color.color_base02
                        )
                    )
                    chainBadge.text = context.getString(R.string.str_legacy)
                    chainBadge.visibility = View.VISIBLE
                    chainSideBadge.visibility = View.GONE

                    when (line.tag) {
                        "okt996_Keccak" -> {
                            chainTypeBadge.visibility = View.VISIBLE
                            chainSideTypeBadge.visibility = View.GONE
                            chainTypeBadge.text = context.getString(R.string.str_ethsecp256k1)
                        }

                        "okt996_Secp" -> {
                            chainTypeBadge.visibility = View.VISIBLE
                            chainSideTypeBadge.visibility = View.GONE
                            chainTypeBadge.text = context.getString(R.string.str_secp256k1)
                        }

                        else -> {
                            chainSideTypeBadge.visibility = View.GONE
                            chainTypeBadge.visibility = View.GONE
                        }
                    }

                } else {
                    listOf(chainBadge, chainTypeBadge, chainSideBadge, chainSideTypeBadge).forEach { it.visibility = View.GONE }
                }
                chainCw20Badge.visibleOrGone(line.supportCw20)
                chainNftBadge.visibleOrGone(line.supportNft)
                chainSideCw20Badge.visibility = View.GONE
                chainSideNftBadge.visibility = View.GONE

            } else {
                chainPrice.visibility = View.VISIBLE
                chainPriceStatus.visibility = View.VISIBLE
                if (!line.isDefault) {
                    chainSideBadge.setBackgroundResource(R.drawable.round_box_deprecated)
                    chainSideBadge.setTextColor(
                        ContextCompat.getColor(
                            context, R.color.color_base02
                        )
                    )
                    chainSideBadge.text = context.getString(R.string.str_legacy)
                    chainSideBadge.visibility = View.VISIBLE
                    chainBadge.visibility = View.GONE

                    when (line.tag) {
                        "okt996_Keccak" -> {
                            chainTypeBadge.visibility = View.GONE
                            chainSideTypeBadge.visibility = View.VISIBLE
                            chainSideTypeBadge.text = context.getString(R.string.str_ethsecp256k1)
                        }

                        "okt996_Secp" -> {
                            chainTypeBadge.visibility = View.GONE
                            chainSideTypeBadge.visibility = View.VISIBLE
                            chainSideTypeBadge.text = context.getString(R.string.str_secp256k1)
                        }

                        else -> {
                            chainSideTypeBadge.visibility = View.GONE
                            chainTypeBadge.visibility = View.GONE
                        }
                    }

                } else {
                    listOf(chainBadge, chainTypeBadge, chainSideBadge, chainSideTypeBadge).forEach { it.visibility = View.GONE }
                }
                chainSideCw20Badge.visibleOrGone(line.supportCw20)
                chainSideNftBadge.visibleOrGone(line.supportNft)
                chainCw20Badge.visibility = View.GONE
                chainNftBadge.visibility = View.GONE
            }

            if (line.fetched) {
                skeletonChainValue.visibility = View.GONE
                if (line !is ChainOkt996Keccak && line !is ChainBinanceBeacon) {
                    if (line.cosmosBalances == null) {
                        respondLayout.visibility = View.VISIBLE
                        chainValue.visibility = View.GONE

                    } else {
                        respondLayout.visibility = View.GONE
                        chainValue.visibility = View.VISIBLE
                    }
                }

                if (Prefs.hideValue) {
                    chainValue.text = "✱✱✱✱"
                    chainValue.textSize = 10f
                } else {
                    chainValue.text = formatAssetValue(line.allValue(false))
                    chainValue.textSize = 14f
                }
            }
        }
    }
}









