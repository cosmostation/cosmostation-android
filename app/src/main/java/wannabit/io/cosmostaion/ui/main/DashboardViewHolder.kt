package wannabit.io.cosmostaion.ui.main

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
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

    fun proBind(chain: BaseChain) {
        binding.apply {
            dashView.setBackgroundResource(R.drawable.item_bg)
            chainImg.setImageResource(chain.logo)
            chainSwipeImg.setImageResource(chain.swipeLogo)
            chainName.text = chain.name.uppercase()

            dashView.heightInDp(114)
            proLayout.visibility = View.VISIBLE
            skeletonAssetCnt.visibility = View.VISIBLE
            assetCnt.visibility = View.VISIBLE

            listOf(
                chainBadge, chainCw20Badge, chainErc20Badge, chainNftBadge, chainDappBadge
            ).forEach { it.visibility = View.GONE }

            chainSideCw20Badge.visibleOrGone(chain.supportCw20)
            chainSideErc20Badge.visibleOrGone(chain.supportEvm)
            chainSideNftBadge.visibleOrGone(chain.supportNft)
            chainSideDappBadge.visibleOrGone(chain.isDefault && chain.isEcosystem())
            chainPrice.visibility = View.VISIBLE
            chainPriceStatus.visibility = View.VISIBLE

            if (chain.isEvmCosmos()) {
                chainAddress.text = chain.address
                chainEvmAddress.text = chain.evmAddress
                chainAddress.visibility = View.INVISIBLE
                chainEvmAddress.visibility = View.VISIBLE

                handler.removeCallbacks(starEvmAddressAnimation)
                handler.postDelayed(starEvmAddressAnimation, 5000)

            } else if (chain.isCosmos()) {
                chainAddress.text = chain.address
                chainAddress.visibility = View.VISIBLE
                chainEvmAddress.visibility = View.GONE

                handler.removeCallbacks(starEvmAddressAnimation)

            } else {
                chainAddress.text = chain.evmAddress
                chainAddress.visibility = View.VISIBLE
                chainEvmAddress.visibility = View.GONE

                handler.removeCallbacks(starEvmAddressAnimation)
            }

            if (chain.isCosmos()) {
                BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
                    chainPrice.text = formatAssetValue(BaseData.getPrice(asset.coinGeckoId))
                    BaseData.lastUpDown(asset.coinGeckoId).let { lastUpDown ->
                        chainPriceStatus.priceChangeStatusColor(lastUpDown)
                        chainPriceStatus.text = priceChangeStatus(lastUpDown)
                    }
                }

            } else {
                chainPrice.text = formatAssetValue(BaseData.getPrice(chain.coinGeckoId))
                BaseData.lastUpDown(chain.coinGeckoId).let { lastUpDown ->
                    chainPriceStatus.priceChangeStatusColor(lastUpDown)
                    chainPriceStatus.text = priceChangeStatus(lastUpDown)
                }
            }

            if (chain.fetched) {
                skeletonChainValue.visibility = View.GONE
                skeletonAssetCnt.visibility = View.GONE

                if (chain.isEvmCosmos()) {
                    if (chain.grpcFetcher?.cosmosBalances == null || chain.web3j == null) {
                        respondLayout.visibility = View.VISIBLE
                        chainValue.visibility = View.GONE
                        assetCnt.visibility = View.GONE
                        return
                    }

                } else if (chain.isCosmos()) {
                    if (chain.grpcFetcher?.cosmosBalances == null) {
                        respondLayout.visibility = View.VISIBLE
                        chainValue.visibility = View.GONE
                        assetCnt.visibility = View.GONE
                        return
                    }

                } else {
                    if (chain.web3j == null) {
                        respondLayout.visibility = View.VISIBLE
                        chainValue.visibility = View.GONE
                        assetCnt.visibility = View.GONE
                        return
                    }
                }
                respondLayout.visibility = View.GONE
                chainValue.visibility = View.VISIBLE

                if (chain.isCosmos()) {
                    val coinCntString = (chain.grpcFetcher?.cosmosBalances?.count {
                        BaseData.getAsset(
                            chain.apiName, it.denom
                        ) != null
                    } ?: 0).toString() + " Coins"

                    if (chain.supportCw20) {
                        val tokenCnt =
                            chain.grpcFetcher?.tokens?.count { BigDecimal.ZERO < it.amount?.toBigDecimal() }
                        if (tokenCnt == 0) {
                            assetCnt.text = coinCntString
                        } else {
                            assetCnt.text = "$tokenCnt Tokens, $coinCntString"
                        }

                    } else if (chain.supportEvm) {
                        val tokenCnt =
                            chain.evmRpcFetcher?.evmTokens?.count { BigDecimal.ZERO < it.amount?.toBigDecimal() }
                        if (tokenCnt == 0) {
                            assetCnt.text = coinCntString
                        } else {
                            assetCnt.text = "$tokenCnt Tokens, $coinCntString"
                        }

                    } else {
                        assetCnt.text = coinCntString
                    }

                } else {
                    val coinCnt =
                        if (BigDecimal.ZERO >= chain.evmRpcFetcher?.evmBalance) "0" else "1" + " Coins"
                    val tokenCnt =
                        chain.evmRpcFetcher?.evmTokens?.count { BigDecimal.ZERO < it.amount?.toBigDecimal() }
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
                    chainValue.text = formatAssetValue(chain.allValue(false))
                    chainValue.textSize = 14f
                }
            }
        }
    }

    fun bind(chain: BaseChain) {
        binding.apply {
            dashView.setBackgroundResource(R.drawable.item_bg)
            chainImg.setImageResource(chain.logo)
            chainSwipeImg.setImageResource(chain.swipeLogo)
            chainName.text = chain.name.uppercase()
            handler.removeCallbacks(starEvmAddressAnimation)

            dashView.heightInDp(68)
            chainAddress.visibility = View.GONE
            chainEvmAddress.visibility = View.GONE
            proLayout.visibility = View.GONE
            skeletonAssetCnt.visibility = View.GONE
            assetCnt.visibility = View.GONE

            chainBadge.visibleOrGone(!chain.isDefault)
            chainCw20Badge.visibleOrGone(chain.supportCw20)
            chainErc20Badge.visibleOrGone(chain.supportEvm)
            chainNftBadge.visibleOrGone(chain.supportNft)
            chainDappBadge.visibleOrGone(chain.isDefault && chain.isEcosystem())

            listOf(
                chainSideBadge, chainSideCw20Badge, chainSideErc20Badge, chainSideNftBadge, chainSideDappBadge
            ).forEach { it.visibility = View.GONE }

            if (chain.fetched) {
                skeletonChainValue.visibility = View.GONE
                skeletonAssetCnt.visibility = View.GONE

                if (chain.isEvmCosmos()) {
                    if (chain.grpcFetcher?.cosmosBalances == null || chain.web3j == null) {
                        respondLayout.visibility = View.VISIBLE
                        chainValue.visibility = View.GONE
                        assetCnt.visibility = View.GONE
                        return
                    }

                } else if (chain.isCosmos()) {
                    if (chain.grpcFetcher?.cosmosBalances == null) {
                        respondLayout.visibility = View.VISIBLE
                        chainValue.visibility = View.GONE
                        assetCnt.visibility = View.GONE
                        return
                    }

                } else {
                    if (chain.web3j == null) {
                        respondLayout.visibility = View.VISIBLE
                        chainValue.visibility = View.GONE
                        assetCnt.visibility = View.GONE
                        return
                    }
                }

                if (Prefs.hideValue) {
                    chainValue.text = "✱✱✱✱"
                    chainValue.textSize = 10f
                } else {
                    chainValue.text = formatAssetValue(chain.allValue(false))
                    chainValue.textSize = 14f
                }
            }
        }
    }
}









