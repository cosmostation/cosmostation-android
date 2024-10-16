package wannabit.io.cosmostaion.ui.main

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.FetchState
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.cosmosClass.OKT_GECKO_ID
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin84
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
            chainName.text = chain.name.uppercase()

            dashView.heightInDp(114)
            proLayout.visibility = View.VISIBLE
            skeletonAssetCnt.visibility = View.VISIBLE
            skeletonChainValue.visibility = View.VISIBLE
            chainPrice.visibility = View.VISIBLE
            chainPriceStatus.visibility = View.VISIBLE
            chainBadge.visibility = View.GONE

            if (chain is ChainBitCoin84) {
                chainSideBadge.visibility = View.VISIBLE
                when (chain.accountKeyType.pubkeyType) {
                    PubKeyType.BTC_NESTED_SEGWIT -> {
                        chainSideBadge.defaultSet()
                        chainSideBadge.text = context.getString(R.string.str_nested_segwit)
                    }

                    PubKeyType.BTC_LEGACY -> {
                        chainSideBadge.defaultSet()
                        chainSideBadge.text = context.getString(R.string.str_legacy)
                    }

                    else -> {
                        chainSideBadge.setBackgroundResource(R.drawable.round_box_bit)
                        chainSideBadge.setTextColor(
                            ContextCompat.getColorStateList(
                                context,
                                R.color.color_base01
                            )
                        )
                        chainSideBadge.text = context.getString(R.string.str_native_segwit)
                    }
                }

            } else {
                chainSideBadge.visibleOrGone(!chain.isDefault)
                chainSideBadge.defaultSet()
                chainBadge.text = context.getString(R.string.str_old)
            }

            if (chain.isEvmCosmos()) {
                chainAddress.text = chain.address
                chainEvmAddress.text = chain.evmAddress
                chainAddress.visibility = View.INVISIBLE
                chainEvmAddress.visibility = View.VISIBLE

                handler.removeCallbacks(starEvmAddressAnimation)
                handler.postDelayed(starEvmAddressAnimation, 5000)

            } else {
                chainAddress.text = if (chain.supportCosmos()) {
                    chain.address
                } else if (chain.supportEvm) {
                    chain.evmAddress
                } else {
                    chain.mainAddress
                }
                chainAddress.visibility = View.VISIBLE
                chainEvmAddress.visibility = View.GONE
                handler.removeCallbacks(starEvmAddressAnimation)
            }

            if (chain.supportCosmos()) {
                if (chain is ChainOktEvm) {
                    chainPrice.text = formatAssetValue(BaseData.getPrice(OKT_GECKO_ID))
                    BaseData.lastUpDown(OKT_GECKO_ID).let { lastUpDown ->
                        chainPriceStatus.priceChangeStatusColor(lastUpDown)
                        chainPriceStatus.text = priceChangeStatus(lastUpDown)
                    }

                } else {
                    BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
                        chainPrice.text = formatAssetValue(BaseData.getPrice(asset.coinGeckoId))
                        BaseData.lastUpDown(asset.coinGeckoId).let { lastUpDown ->
                            chainPriceStatus.priceChangeStatusColor(lastUpDown)
                            chainPriceStatus.text = priceChangeStatus(lastUpDown)
                        }
                    }
                }

            } else {
                chainPrice.text = formatAssetValue(BaseData.getPrice(chain.coinGeckoId))
                BaseData.lastUpDown(chain.coinGeckoId).let { lastUpDown ->
                    chainPriceStatus.priceChangeStatusColor(lastUpDown)
                    chainPriceStatus.text = priceChangeStatus(lastUpDown)
                }
            }

            when (chain.fetchState) {
                FetchState.SUCCESS -> {
                    skeletonChainValue.visibility = View.GONE
                    skeletonAssetCnt.visibility = View.GONE
                    respondLayout.visibility = View.GONE
                    chainValue.visibility = View.VISIBLE
                    assetCnt.visibility = View.VISIBLE

                    if (Prefs.hideValue) {
                        chainValue.text = "✱✱✱✱"
                        chainValue.textSize = 10f
                    } else {
                        chainValue.text = formatAssetValue(chain.allValue(false))
                        chainValue.textSize = 14f
                    }

                    chainValue.text = formatAssetValue(chain.allValue(true), true)
                    val coinCntString = chain.coinCnt.toString() + " Coins"
                    val tokenCnt = chain.tokenCnt
                    assetCnt.text = if (tokenCnt > 0) {
                        "$tokenCnt Tokens, $coinCntString"
                    } else {
                        coinCntString
                    }
                }

                FetchState.FAIL -> {
                    skeletonChainValue.visibility = View.GONE
                    skeletonAssetCnt.visibility = View.GONE
                    respondLayout.visibility = View.VISIBLE
                    chainValue.visibility = View.GONE
                    assetCnt.visibility = View.GONE
                }

                else -> {
                    skeletonChainValue.visibility = View.VISIBLE
                    skeletonAssetCnt.visibility = View.VISIBLE
                    respondLayout.visibility = View.GONE
                    chainValue.visibility = View.GONE
                    assetCnt.visibility = View.GONE
                }
            }
        }
    }

    fun testnetProBind(chain: BaseChain) {
        binding.apply {
            dashView.setBackgroundResource(R.drawable.item_bg)
            chainImg.setImageResource(chain.logo)
            chainName.text = chain.name.uppercase()

            dashView.heightInDp(114)
            proLayout.visibility = View.VISIBLE
            skeletonAssetCnt.visibility = View.VISIBLE
            skeletonChainValue.visibility = View.VISIBLE
            chainBadge.visibility = View.GONE

            if (chain is ChainBitCoin84) {
                chainSideBadge.visibility = View.VISIBLE
                when (chain.accountKeyType.pubkeyType) {
                    PubKeyType.BTC_NESTED_SEGWIT -> {
                        chainSideBadge.defaultSet()
                        chainSideBadge.text = context.getString(R.string.str_nested_segwit)
                    }

                    PubKeyType.BTC_LEGACY -> {
                        chainSideBadge.defaultSet()
                        chainSideBadge.text = context.getString(R.string.str_legacy)
                    }

                    else -> {
                        chainSideBadge.setBackgroundResource(R.drawable.round_box_bit)
                        chainSideBadge.setTextColor(
                            ContextCompat.getColorStateList(
                                context,
                                R.color.color_base01
                            )
                        )
                        chainSideBadge.text = context.getString(R.string.str_native_segwit)
                    }
                }

            } else {
                chainSideBadge.visibleOrGone(!chain.isDefault)
                chainSideBadge.defaultSet()
                chainBadge.text = context.getString(R.string.str_old)
            }

            chainPrice.visibility = View.VISIBLE
            chainPriceStatus.visibility = View.VISIBLE

            if (chain.isEvmCosmos()) {
                chainAddress.text = chain.address
                chainEvmAddress.text = chain.evmAddress
                chainAddress.visibility = View.INVISIBLE
                chainEvmAddress.visibility = View.VISIBLE

                handler.removeCallbacks(starEvmAddressAnimation)
                handler.postDelayed(starEvmAddressAnimation, 5000)

            } else {
                chainAddress.text = if (chain.supportCosmos()) {
                    chain.address
                } else if (chain.supportEvm) {
                    chain.evmAddress
                } else {
                    chain.mainAddress
                }
                chainAddress.visibility = View.VISIBLE
                chainEvmAddress.visibility = View.GONE
                handler.removeCallbacks(starEvmAddressAnimation)
            }

            if (chain.supportCosmos()) {
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

            when (chain.fetchState) {
                FetchState.SUCCESS -> {
                    skeletonChainValue.visibility = View.GONE
                    skeletonAssetCnt.visibility = View.GONE
                    respondLayout.visibility = View.GONE
                    chainValue.visibility = View.VISIBLE
                    assetCnt.visibility = View.VISIBLE

                    if (Prefs.hideValue) {
                        chainValue.text = "✱✱✱✱"
                        chainValue.textSize = 10f
                    } else {
                        chainValue.text = formatAssetValue(chain.allValue(false))
                        chainValue.textSize = 14f
                    }

                    chainValue.text = formatAssetValue(chain.allValue(true), true)
                    val coinCntString = chain.coinCnt.toString() + " Coins"
                    val tokenCnt = chain.tokenCnt
                    assetCnt.text = if (tokenCnt > 0) {
                        "$tokenCnt Tokens, $coinCntString"
                    } else {
                        coinCntString
                    }
                }

                FetchState.FAIL -> {
                    skeletonChainValue.visibility = View.GONE
                    skeletonAssetCnt.visibility = View.GONE
                    respondLayout.visibility = View.VISIBLE
                    chainValue.visibility = View.GONE
                    assetCnt.visibility = View.GONE
                }

                else -> {
                    skeletonChainValue.visibility = View.VISIBLE
                    skeletonAssetCnt.visibility = View.VISIBLE
                    respondLayout.visibility = View.GONE
                    chainValue.visibility = View.GONE
                    assetCnt.visibility = View.GONE
                }
            }
        }
    }

    fun bind(chain: BaseChain) {
        binding.apply {
            dashView.setBackgroundResource(R.drawable.item_bg)
            chainImg.setImageResource(chain.logo)
            chainName.text = chain.name.uppercase()
            handler.removeCallbacks(starEvmAddressAnimation)

            dashView.heightInDp(68)
            chainAddress.visibility = View.GONE
            chainEvmAddress.visibility = View.GONE
            proLayout.visibility = View.GONE
            skeletonAssetCnt.visibility = View.GONE
            assetCnt.visibility = View.GONE
            chainSideBadge.visibility = View.GONE

            if (chain is ChainBitCoin84) {
                chainBadge.visibility = View.VISIBLE
                when (chain.accountKeyType.pubkeyType) {
                    PubKeyType.BTC_NESTED_SEGWIT -> {
                        chainBadge.defaultSet()
                        chainBadge.text = context.getString(R.string.str_nested_segwit)
                    }

                    PubKeyType.BTC_LEGACY -> {
                        chainBadge.defaultSet()
                        chainBadge.text = context.getString(R.string.str_legacy)
                    }

                    else -> {
                        chainBadge.setBackgroundResource(R.drawable.round_box_bit)
                        chainBadge.setTextColor(
                            ContextCompat.getColorStateList(
                                context,
                                R.color.color_base01
                            )
                        )
                        chainBadge.text = context.getString(R.string.str_native_segwit)
                    }
                }

            } else {
                chainBadge.visibleOrGone(!chain.isDefault)
                chainBadge.defaultSet()
                chainBadge.text = context.getString(R.string.str_old)
            }

            when (chain.fetchState) {
                FetchState.SUCCESS -> {
                    skeletonChainValue.visibility = View.GONE
                    respondLayout.visibility = View.GONE
                    chainValue.visibility = View.VISIBLE

                    if (Prefs.hideValue) {
                        chainValue.text = "✱✱✱✱"
                        chainValue.textSize = 10f
                    } else {
                        chainValue.text = formatAssetValue(chain.allValue(false))
                        chainValue.textSize = 14f
                    }
                }

                FetchState.FAIL -> {
                    skeletonChainValue.visibility = View.GONE
                    respondLayout.visibility = View.VISIBLE
                    chainValue.visibility = View.GONE
                }

                else -> {
                    skeletonChainValue.visibility = View.VISIBLE
                    respondLayout.visibility = View.GONE
                    chainValue.visibility = View.GONE
                }
            }
        }
    }

    fun testnetBind(chain: BaseChain) {
        binding.apply {
            dashView.setBackgroundResource(R.drawable.item_bg)
            chainImg.setImageResource(chain.logo)
            chainName.text = chain.name.uppercase()
            handler.removeCallbacks(starEvmAddressAnimation)

            dashView.heightInDp(68)
            chainAddress.visibility = View.GONE
            chainEvmAddress.visibility = View.GONE
            proLayout.visibility = View.GONE
            skeletonAssetCnt.visibility = View.GONE
            assetCnt.visibility = View.GONE
            chainSideBadge.visibility = View.GONE

            if (chain is ChainBitCoin84) {
                chainBadge.visibility = View.VISIBLE
                when (chain.accountKeyType.pubkeyType) {
                    PubKeyType.BTC_NESTED_SEGWIT -> {
                        chainBadge.defaultSet()
                        chainBadge.text = context.getString(R.string.str_nested_segwit)
                    }

                    PubKeyType.BTC_LEGACY -> {
                        chainBadge.defaultSet()
                        chainBadge.text = context.getString(R.string.str_legacy)
                    }

                    else -> {
                        chainBadge.setBackgroundResource(R.drawable.round_box_bit)
                        chainBadge.setTextColor(
                            ContextCompat.getColorStateList(
                                context,
                                R.color.color_base01
                            )
                        )
                        chainBadge.text = context.getString(R.string.str_native_segwit)
                    }
                }

            } else {
                chainBadge.visibleOrGone(!chain.isDefault)
                chainBadge.defaultSet()
                chainBadge.text = context.getString(R.string.str_old)
            }

            when (chain.fetchState) {
                FetchState.SUCCESS -> {
                    skeletonChainValue.visibility = View.GONE
                    respondLayout.visibility = View.GONE
                    chainValue.visibility = View.VISIBLE

                    if (Prefs.hideValue) {
                        chainValue.text = "✱✱✱✱"
                        chainValue.textSize = 10f
                    } else {
                        chainValue.text = formatAssetValue(chain.allValue(false))
                        chainValue.textSize = 14f
                    }
                }

                FetchState.FAIL -> {
                    skeletonChainValue.visibility = View.GONE
                    respondLayout.visibility = View.VISIBLE
                    chainValue.visibility = View.GONE
                }

                else -> {
                    skeletonChainValue.visibility = View.VISIBLE
                    respondLayout.visibility = View.GONE
                    chainValue.visibility = View.GONE
                }
            }
        }
    }
}

fun TextView.defaultSet() {
    setBackgroundResource(R.drawable.round_box_deprecated)
    setTextColor(ContextCompat.getColorStateList(context, R.color.color_base02))
}









