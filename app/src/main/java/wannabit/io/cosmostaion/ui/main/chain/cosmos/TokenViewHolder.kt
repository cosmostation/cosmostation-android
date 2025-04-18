package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.testnetClass.ChainGnoTestnet
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.priceChangeStatus
import wannabit.io.cosmostaion.common.priceChangeStatusColor
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.data.model.res.Coin
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemCosmosLineTokenBinding
import java.math.BigDecimal
import java.math.RoundingMode

class TokenViewHolder(
    val context: Context, private val binding: ItemCosmosLineTokenBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(chain: BaseChain, coin: Coin) {
        binding.apply {
            coinView.setBackgroundResource(R.drawable.item_bg)
            chain.cosmosFetcher()?.tokens?.firstOrNull { token -> token.chainName == chain.apiName && token.address == coin.denom }
                ?.let { token ->
                    tokenImg.setTokenImg(token.image)
                    tokenImg.clipToOutline = true
                    tokenName.text = token.symbol

                    tokenPrice.text = formatAssetValue(BaseData.getPrice(token.coinGeckoId))
                    BaseData.lastUpDown(token.coinGeckoId).let { lastUpDown ->
                        tokenPriceChange.priceChangeStatusColor(lastUpDown)
                        tokenPriceChange.text = priceChangeStatus(lastUpDown)
                    }

                    val dpAmount = token.amount?.toBigDecimal()?.movePointLeft(token.decimals)
                        ?.setScale(6, RoundingMode.DOWN) ?: BigDecimal.ZERO
                    if (Prefs.hideValue) {
                        coinAmount.visibility = View.GONE
                        coinAmountValue.visibility = View.GONE
                        hideValue.visibility = View.VISIBLE
                    } else {
                        coinAmount.visibility = View.VISIBLE
                        coinAmountValue.visibility = View.VISIBLE
                        hideValue.visibility = View.GONE

                        coinAmount.text = formatAmount(dpAmount.toPlainString(), 6)
                        if (chain.isSupportCw20()) {
                            chain.cosmosFetcher?.let {
                                coinAmountValue.text =
                                    formatAssetValue(it.tokenValue(token.address))
                            }
                        } else {
                            chain.evmRpcFetcher?.let {
                                coinAmountValue.text =
                                    formatAssetValue(it.tokenValue(token.address))
                            }
                        }
                    }

                } ?: run {
                chain.evmRpcFetcher()?.evmTokens?.firstOrNull { token -> token.chainName == chain.apiName && token.address == coin.denom }
                    ?.let { evmToken ->
                        tokenImg.setTokenImg(evmToken.image)
                        tokenImg.clipToOutline = true
                        tokenName.text = evmToken.symbol

                        tokenPrice.text = formatAssetValue(BaseData.getPrice(evmToken.coinGeckoId))
                        BaseData.lastUpDown(evmToken.coinGeckoId).let { lastUpDown ->
                            tokenPriceChange.priceChangeStatusColor(lastUpDown)
                            tokenPriceChange.text = priceChangeStatus(lastUpDown)
                        }

                        val dpAmount =
                            evmToken.amount?.toBigDecimal()?.movePointLeft(evmToken.decimals)
                                ?.setScale(6, RoundingMode.DOWN) ?: BigDecimal.ZERO

                        if (Prefs.hideValue) {
                            coinAmount.visibility = View.GONE
                            coinAmountValue.visibility = View.GONE
                            hideValue.visibility = View.VISIBLE
                        } else {
                            coinAmount.visibility = View.VISIBLE
                            coinAmountValue.visibility = View.VISIBLE
                            hideValue.visibility = View.GONE

                            coinAmount.text = formatAmount(dpAmount.toPlainString(), 6)
                            if (chain.isSupportCw20()) {
                                chain.cosmosFetcher?.let {
                                    coinAmountValue.text =
                                        formatAssetValue(it.tokenValue(evmToken.address))
                                }
                            } else {
                                chain.evmRpcFetcher?.let {
                                    coinAmountValue.text =
                                        formatAssetValue(it.tokenValue(evmToken.address))
                                }
                            }
                        }

                    } ?: run {
                    (chain as ChainGnoTestnet).gnoRpcFetcher()?.grc20Tokens?.firstOrNull { token -> token.chainName == chain.apiName && token.address == coin.denom }
                        ?.let { grcToken ->
                            tokenImg.setTokenImg(grcToken.image)
                            tokenImg.clipToOutline = true
                            tokenName.text = grcToken.symbol

                            tokenPrice.text =
                                formatAssetValue(BaseData.getPrice(grcToken.coinGeckoId))
                            BaseData.lastUpDown(grcToken.coinGeckoId).let { lastUpDown ->
                                tokenPriceChange.priceChangeStatusColor(lastUpDown)
                                tokenPriceChange.text = priceChangeStatus(lastUpDown)
                            }

                            val dpAmount =
                                grcToken.amount?.toBigDecimal()?.movePointLeft(grcToken.decimals)
                                    ?.setScale(6, RoundingMode.DOWN) ?: BigDecimal.ZERO

                            if (Prefs.hideValue) {
                                coinAmount.visibility = View.GONE
                                coinAmountValue.visibility = View.GONE
                                hideValue.visibility = View.VISIBLE
                            } else {
                                coinAmount.visibility = View.VISIBLE
                                coinAmountValue.visibility = View.VISIBLE
                                hideValue.visibility = View.GONE

                                coinAmount.text = formatAmount(dpAmount.toPlainString(), 6)
                                chain.gnoRpcFetcher?.let {
                                    coinAmountValue.text =
                                        formatAssetValue(it.grc20TokenValue(grcToken.address))
                                }
                            }
                        }
                }
            }
        }
    }
}