package wannabit.io.cosmostaion.ui.main

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.cosmosClass.OKT_GECKO_ID
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.fadeInAnimation
import wannabit.io.cosmostaion.common.fadeOutAnimation
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.heightInDp
import wannabit.io.cosmostaion.common.priceChangeStatus
import wannabit.io.cosmostaion.common.priceChangeStatusColor
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.BaseAccount
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

    fun proBind(chain: BaseChain, baseAccount: BaseAccount?) {
        binding.apply {
            dashView.setBackgroundResource(R.drawable.item_bg)
            chainImg.setImageResource(chain.logo)
            chainName.text = chain.name.uppercase()

            dashView.heightInDp(114)
            proLayout.visibility = View.VISIBLE
            skeletonAssetCnt.visibility = View.VISIBLE
            skeletonChainValue.visibility = View.VISIBLE
            chainBadge.visibility = View.GONE
            chainSideBadge.visibleOrGone(!chain.isDefault)

            chainPrice.visibility = View.VISIBLE
            chainPriceStatus.visibility = View.VISIBLE

            if (chain.isEvmCosmos()) {
                chainAddress.text = chain.address
                chainEvmAddress.text = chain.evmAddress
                chainAddress.visibility = View.INVISIBLE
                chainEvmAddress.visibility = View.VISIBLE

                handler.removeCallbacks(starEvmAddressAnimation)
                handler.postDelayed(starEvmAddressAnimation, 5000)

            } else if (chain.supportCosmos()) {
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

            if (!chain.fetchedState) {
                skeletonChainValue.visibility = View.VISIBLE
                skeletonAssetCnt.visibility = View.VISIBLE
                respondLayout.visibility = View.GONE
                chainValue.visibility = View.VISIBLE

            } else {
                skeletonChainValue.visibility = View.GONE
                skeletonAssetCnt.visibility = View.GONE
                if (chain.fetched) {
                    if (chain.isEvmCosmos()) {
                        if (chain is ChainOktEvm) {
                            if (chain.oktFetcher?.oktAccountInfo?.isJsonNull == true || chain.web3j == null) {
                                respondLayout.visibility = View.VISIBLE
                                chainValue.visibility = View.GONE
                                assetCnt.visibility = View.GONE
                                return
                            }

                        } else {
                            if (chain.cosmosFetcher?.cosmosBalances == null || chain.web3j == null) {
                                respondLayout.visibility = View.VISIBLE
                                chainValue.visibility = View.GONE
                                assetCnt.visibility = View.GONE
                                return
                            }
                        }

                    } else if (chain.supportCosmos()) {
                        if (chain is ChainOkt996Keccak) {
                            if (chain.oktFetcher?.oktAccountInfo?.isJsonNull == true) {
                                respondLayout.visibility = View.VISIBLE
                                chainValue.visibility = View.GONE
                                assetCnt.visibility = View.GONE
                                return
                            }

                        } else {
                            if (chain.cosmosFetcher?.cosmosBalances == null) {
                                respondLayout.visibility = View.VISIBLE
                                chainValue.visibility = View.GONE
                                assetCnt.visibility = View.GONE
                                return
                            }
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
                    assetCnt.visibility = View.VISIBLE

                    if (chain is ChainOkt996Keccak) {
                        assetCnt.text =
                            chain.oktFetcher?.oktAccountInfo?.get("value")?.asJsonObject?.get("coins")?.asJsonArray?.size()
                                .toString() + " Coins"

                    } else if (chain.supportCosmos()) {
                        val coinCntString = if (chain is ChainOktEvm) {
                            chain.oktFetcher?.oktAccountInfo?.get("value")?.asJsonObject?.get("coins")?.asJsonArray?.size()
                                .toString() + " Coins"
                        } else {
                            (chain.cosmosFetcher?.cosmosBalances?.count {
                                BaseData.getAsset(
                                    chain.apiName, it.denom
                                ) != null
                            } ?: 0).toString() + " Coins"
                        }

                        if (chain.supportCw20) {
                            val tokenCnt =
                                chain.cosmosFetcher?.tokens?.count { BigDecimal.ZERO < it.amount?.toBigDecimal() }
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
                        baseAccount?.let {
                            val coinCnt =
                                if (BigDecimal.ZERO >= chain.evmRpcFetcher?.evmBalance) "0" + " Coins" else "1" + " Coins"
                            val tokenCnt = if (Prefs.getDisplayErc20s(baseAccount.id, chain.tag) != null) {
                                Prefs.getDisplayErc20s(baseAccount.id, chain.tag)?.size
                            } else {
                                chain.evmRpcFetcher?.evmTokens?.count { BigDecimal.ZERO < it.amount?.toBigDecimal() }
                            }
                            if (tokenCnt == 0) {
                                assetCnt.text = coinCnt
                            } else {
                                assetCnt.text = "$tokenCnt Tokens, $coinCnt"
                            }
                        }
                    }

                    if (Prefs.hideValue) {
                        chainValue.text = "✱✱✱✱"
                        chainValue.textSize = 10f
                    } else {
                        chainValue.text = formatAssetValue(chain.allValue(false))
                        chainValue.textSize = 14f
                    }

                } else {
                    skeletonAssetCnt.visibility = View.VISIBLE
                    skeletonChainValue.visibility = View.VISIBLE
                    respondLayout.visibility = View.GONE
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
            assetCnt.visibility = View.VISIBLE
            chainBadge.visibility = View.GONE
            chainSideBadge.visibleOrGone(!chain.isDefault)
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
                chainAddress.text = chain.address
                chainAddress.visibility = View.VISIBLE
                chainEvmAddress.visibility = View.GONE

                handler.removeCallbacks(starEvmAddressAnimation)
            }

            BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
                chainPrice.text = formatAssetValue(BaseData.getPrice(asset.coinGeckoId))
                BaseData.lastUpDown(asset.coinGeckoId).let { lastUpDown ->
                    chainPriceStatus.priceChangeStatusColor(lastUpDown)
                    chainPriceStatus.text = priceChangeStatus(lastUpDown)
                }
            }

            if (!chain.fetchedState) {
                skeletonChainValue.visibility = View.VISIBLE
                skeletonAssetCnt.visibility = View.VISIBLE
                respondLayout.visibility = View.GONE
                chainValue.visibility = View.VISIBLE

            } else {
                skeletonChainValue.visibility = View.GONE
                skeletonAssetCnt.visibility = View.GONE
                if (chain.fetched) {
                    if (chain.isEvmCosmos()) {
                        if (chain.cosmosFetcher?.cosmosBalances == null || chain.web3j == null) {
                            respondLayout.visibility = View.VISIBLE
                            chainValue.visibility = View.GONE
                            assetCnt.visibility = View.GONE
                            return
                        }

                    } else if (chain.cosmosFetcher?.cosmosBalances == null) {
                        respondLayout.visibility = View.VISIBLE
                        chainValue.visibility = View.GONE
                        assetCnt.visibility = View.GONE
                        return
                    }
                    respondLayout.visibility = View.GONE
                    chainValue.visibility = View.VISIBLE

                    val coinCntString = (chain.cosmosFetcher?.cosmosBalances?.count {
                        BaseData.getAsset(
                            chain.apiName, it.denom
                        ) != null
                    } ?: 0).toString() + " Coins"

                    if (chain.supportCw20) {
                        val tokenCnt =
                            chain.cosmosFetcher?.tokens?.count { BigDecimal.ZERO < it.amount?.toBigDecimal() }
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

                    if (Prefs.hideValue) {
                        chainValue.text = "✱✱✱✱"
                        chainValue.textSize = 10f
                    } else {
                        chainValue.text = formatAssetValue(chain.allValue(false))
                        chainValue.textSize = 14f
                    }

                } else {
                    skeletonAssetCnt.visibility = View.VISIBLE
                    skeletonChainValue.visibility = View.VISIBLE
                    respondLayout.visibility = View.GONE
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
            chainBadge.visibleOrGone(!chain.isDefault)
            chainSideBadge.visibility = View.GONE

            if (!chain.fetchedState) {
                skeletonChainValue.visibility = View.VISIBLE
                respondLayout.visibility = View.GONE
                chainValue.visibility = View.GONE

            } else {
                skeletonChainValue.visibility = View.GONE
                if (chain.fetched) {
                    if (chain.isEvmCosmos()) {
                        if (chain is ChainOktEvm) {
                            if (chain.oktFetcher?.oktAccountInfo == null || chain.web3j == null) {
                                respondLayout.visibility = View.VISIBLE
                                chainValue.visibility = View.GONE
                                return
                            }

                        } else {
                            if (chain.cosmosFetcher?.cosmosBalances == null || chain.web3j == null) {
                                respondLayout.visibility = View.VISIBLE
                                chainValue.visibility = View.GONE
                                return
                            }
                        }

                    } else if (chain.supportCosmos()) {
                        if (chain is ChainOkt996Keccak) {
                            if (chain.oktFetcher?.oktAccountInfo == null) {
                                respondLayout.visibility = View.VISIBLE
                                chainValue.visibility = View.GONE
                                return
                            }

                        } else {
                            if (chain.cosmosFetcher?.cosmosBalances == null) {
                                respondLayout.visibility = View.VISIBLE
                                chainValue.visibility = View.GONE
                                return
                            }
                        }

                    } else {
                        if (chain.web3j == null) {
                            respondLayout.visibility = View.VISIBLE
                            chainValue.visibility = View.GONE
                            return
                        }
                    }
                    respondLayout.visibility = View.GONE
                    chainValue.visibility = View.VISIBLE

                    if (Prefs.hideValue) {
                        chainValue.text = "✱✱✱✱"
                        chainValue.textSize = 10f
                    } else {
                        chainValue.text = formatAssetValue(chain.allValue(false))
                        chainValue.textSize = 14f
                    }

                } else {
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
            chainBadge.visibleOrGone(!chain.isDefault)
            chainSideBadge.visibility = View.GONE
            skeletonAssetCnt.visibility = View.GONE

            if (!chain.fetchedState) {
                skeletonChainValue.visibility = View.VISIBLE
                respondLayout.visibility = View.GONE
                chainValue.visibility = View.GONE

            } else {
                skeletonChainValue.visibility = View.GONE
                if (chain.fetched) {
                    if (chain.isEvmCosmos()) {
                        if (chain.cosmosFetcher?.cosmosBalances == null || chain.web3j == null) {
                            respondLayout.visibility = View.VISIBLE
                            chainValue.visibility = View.GONE
                            return
                        }

                    } else {
                        if (chain.cosmosFetcher?.cosmosBalances == null) {
                            respondLayout.visibility = View.VISIBLE
                            chainValue.visibility = View.GONE
                            return
                        }
                    }
                    respondLayout.visibility = View.GONE
                    chainValue.visibility = View.VISIBLE

                    if (Prefs.hideValue) {
                        chainValue.text = "✱✱✱✱"
                        chainValue.textSize = 10f
                    } else {
                        chainValue.text = formatAssetValue(chain.allValue(false))
                        chainValue.textSize = 14f
                    }

                } else {
                    skeletonChainValue.visibility = View.VISIBLE
                    skeletonAssetCnt.visibility = View.VISIBLE
                    respondLayout.visibility = View.GONE
                    chainValue.visibility = View.GONE
                }
            }
        }
    }
}









