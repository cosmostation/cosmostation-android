package wannabit.io.cosmostaion.ui.main

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.cosmosClass.OKT_GECKO_ID
import wannabit.io.cosmostaion.chain.evmClass.ChainBeraEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.fadeInAnimation
import wannabit.io.cosmostaion.common.fadeOutAnimation
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.heightInDp
import wannabit.io.cosmostaion.common.priceChangeStatus
import wannabit.io.cosmostaion.common.priceChangeStatusColor
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemDashBinding
import java.math.BigDecimal


class DashboardViewHolder(
    val context: Context, private val binding: ItemDashBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val handler = Handler(Looper.getMainLooper())
    private val starEvmAddressAnimation = object : Runnable {
        override fun run() {
            binding.apply {
                if (chainAddress.visibility == View.VISIBLE) {
                    fadeOutAnimation(chainAddress)
                    fadeInAnimation(chainEvmAddress)
                } else {
                    fadeOutAnimation(chainEvmAddress)
                    fadeInAnimation(chainAddress)
                }
            }
            handler.postDelayed(this, 5000)
        }
    }

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

            listOf(
                chainBadge,
                chainTypeBadge,
                chainSideBadge,
                chainSideTypeBadge
            ).forEach { it.visibility = View.GONE }

            if (Prefs.style == 0) {
                dashView.heightInDp(68)
                chainAddress.visibility = View.GONE
                chainEvmAddress.visibility = View.GONE
                proLayout.visibility = View.GONE
                skeletonAssetCnt.visibility = View.GONE
                assetCnt.visibility = View.GONE

                chainCw20Badge.visibleOrGone(line.supportCw20)
                chainNftBadge.visibleOrGone(line.supportNft)
                chainPrice.visibility = View.GONE
                chainPriceStatus.visibility = View.GONE

                handler.removeCallbacks(starEvmAddressAnimation)

            } else {
                dashView.heightInDp(114)
                proLayout.visibility = View.VISIBLE
                skeletonAssetCnt.visibility = View.VISIBLE
                assetCnt.visibility = View.VISIBLE

                chainCw20Badge.visibleOrGone(line.supportCw20)
                chainNftBadge.visibleOrGone(line.supportNft)
                chainPrice.visibility = View.VISIBLE
                chainPriceStatus.visibility = View.VISIBLE

                if (line.supportCosmos) {
                    chainAddress.text = line.address
                    chainEvmAddress.text = ByteUtils.convertBech32ToEvm(line.address)
                    chainAddress.visibility = View.INVISIBLE
                    chainEvmAddress.visibility = View.VISIBLE

                    handler.removeCallbacks(starEvmAddressAnimation)
                    handler.postDelayed(starEvmAddressAnimation, 5000)

                } else {
                    chainAddress.visibility = View.VISIBLE
                    chainEvmAddress.visibility = View.GONE
                    chainAddress.text = line.address
                }
            }

            if (line.fetched) {
                skeletonChainValue.visibility = View.GONE
                skeletonAssetCnt.visibility = View.GONE
                if (line !is ChainOktEvm) {
                    if (line.supportCosmos && line.cosmosBalances == null) {
                        respondLayout.visibility = View.VISIBLE
                        chainValue.visibility = View.GONE
                        assetCnt.visibility = View.GONE
                        return
                    }
                }

                if (line.web3j == null) {
                    respondLayout.visibility = View.VISIBLE
                    chainValue.visibility = View.GONE
                    assetCnt.visibility = View.GONE
                    return
                }
                respondLayout.visibility = View.GONE
                chainValue.visibility = View.VISIBLE

                if (line.supportCosmos) {
                    val coinCntString = if (line is ChainOktEvm) {
                        line.oktLcdAccountInfo?.value?.coins?.size.toString() + " Coins"
                    } else if (line is ChainBeraEvm) {
                        if (line.evmBalance > BigDecimal.ZERO) {
                            (line.cosmosBalances?.count {
                                BaseData.getAsset(
                                    line.apiName,
                                    it.denom
                                ) != null
                            }?.plus(1) ?: 0).toString() + " Coins"
                        } else {
                            (line.cosmosBalances?.count {
                                BaseData.getAsset(
                                    line.apiName,
                                    it.denom
                                ) != null
                            } ?: 0).toString() + " Coins"
                        }
                    } else {
                        (line.cosmosBalances?.count {
                            BaseData.getAsset(
                                line.apiName,
                                it.denom
                            ) != null
                        } ?: 0).toString() + " Coins"
                    }

                    val tokenCnt =
                        line.evmTokens.count { it.amount?.toBigDecimal()!! > BigDecimal.ZERO }
                    if (tokenCnt == 0) {
                        assetCnt.text = coinCntString
                    } else {
                        assetCnt.text = "$tokenCnt Tokens, $coinCntString"
                    }

                } else {
                    val coinCnt = if (line.evmBalance <= BigDecimal.ZERO) "0" else "1" + " Coins"
                    val tokenCnt =
                        line.evmTokens.count { it.amount?.toBigDecimal()!! > BigDecimal.ZERO }
                    if (tokenCnt == 0) {
                        assetCnt.text = coinCnt
                    } else {
                        assetCnt.text = "$tokenCnt Tokens, $coinCnt"
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
                dashView.heightInDp(68)
                chainAddress.visibility = View.GONE
                chainEvmAddress.visibility = View.GONE
                proLayout.visibility = View.GONE
                skeletonAssetCnt.visibility = View.GONE
                assetCnt.visibility = View.GONE

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
                    listOf(
                        chainBadge,
                        chainTypeBadge,
                        chainSideBadge,
                        chainSideTypeBadge
                    ).forEach { it.visibility = View.GONE }
                }
                chainCw20Badge.visibleOrGone(line.supportCw20)
                chainNftBadge.visibleOrGone(line.supportNft)
                chainSideCw20Badge.visibility = View.GONE
                chainSideNftBadge.visibility = View.GONE

            } else {
                dashView.heightInDp(114)
                chainAddress.visibility = View.VISIBLE
                proLayout.visibility = View.VISIBLE
                skeletonAssetCnt.visibility = View.VISIBLE
                assetCnt.visibility = View.VISIBLE
                chainAddress.text = line.address

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
                    listOf(
                        chainBadge,
                        chainTypeBadge,
                        chainSideBadge,
                        chainSideTypeBadge
                    ).forEach { it.visibility = View.GONE }
                }
                chainSideCw20Badge.visibleOrGone(line.supportCw20)
                chainSideNftBadge.visibleOrGone(line.supportNft)
                chainCw20Badge.visibility = View.GONE
                chainNftBadge.visibility = View.GONE
            }

            if (line.fetched) {
                skeletonChainValue.visibility = View.GONE
                skeletonAssetCnt.visibility = View.GONE

                if (line !is ChainOkt996Keccak) {
                    if (line.cosmosBalances == null) {
                        respondLayout.visibility = View.VISIBLE
                        chainValue.visibility = View.GONE
                        assetCnt.visibility = View.GONE

                    } else {
                        val coinCntString = (line.cosmosBalances?.count {
                            BaseData.getAsset(
                                line.apiName,
                                it.denom
                            ) != null
                        } ?: 0).toString() + " Coins"
                        if (line.supportCw20 || line.supportErc20) {
                            val tokenCnt =
                                line.tokens.count { it.amount?.toBigDecimal()!! > BigDecimal.ZERO }
                            if (tokenCnt == 0) {
                                assetCnt.text = coinCntString
                            } else {
                                assetCnt.text = "$tokenCnt Tokens, $coinCntString"
                            }
                        } else {
                            assetCnt.text = coinCntString
                        }
                        respondLayout.visibility = View.GONE
                        chainValue.visibility = View.VISIBLE
                    }

                } else {
                    val coinCntString =
                        line.oktLcdAccountInfo?.value?.coins?.size.toString() + " Coins"
                    val tokenCnt =
                        line.tokens.count { it.amount?.toBigDecimal()!! > BigDecimal.ZERO }
                    if (tokenCnt == 0) {
                        assetCnt.text = coinCntString
                    } else {
                        assetCnt.text = "$tokenCnt Tokens, $coinCntString"
                    }
                    respondLayout.visibility = View.GONE
                    chainValue.visibility = View.VISIBLE
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









