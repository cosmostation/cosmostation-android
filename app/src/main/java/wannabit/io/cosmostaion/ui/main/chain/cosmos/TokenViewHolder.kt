package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.priceChangeStatus
import wannabit.io.cosmostaion.common.priceChangeStatusColor
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.data.model.res.Coin
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemCosmosLineTokenBinding
import java.math.RoundingMode

class TokenViewHolder(
    val context: Context, private val binding: ItemCosmosLineTokenBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(chain: BaseChain, coin: Coin) {
        binding.apply {
            coinView.setBackgroundResource(R.drawable.item_bg)
            chain.cosmosFetcher()?.tokens?.firstOrNull { token -> token.chain == chain.apiName && token.contract == coin.denom }
                ?.let { token ->
                    tokenImg.setTokenImg(token.image)
                    tokenImg.clipToOutline = true
                    tokenName.text = token.symbol

                    tokenPrice.text = formatAssetValue(BaseData.getPrice(token.coinGeckoId))
                    BaseData.lastUpDown(token.coinGeckoId).let { lastUpDown ->
                        tokenPriceChange.priceChangeStatusColor(lastUpDown)
                        tokenPriceChange.text = priceChangeStatus(lastUpDown)
                    }

                    token.amount?.toBigDecimal()?.movePointLeft(token.decimals)
                        ?.setScale(6, RoundingMode.DOWN)?.let { amount ->
                            if (Prefs.hideValue) {
                                coinAmount.visibility = View.GONE
                                coinAmountValue.visibility = View.GONE
                                hideValue.visibility = View.VISIBLE
                            } else {
                                coinAmount.visibility = View.VISIBLE
                                coinAmountValue.visibility = View.VISIBLE
                                hideValue.visibility = View.GONE

                                coinAmount.text = formatAmount(amount.toPlainString(), 6)
                                if (chain.isSupportCw20()) {
                                    chain.cosmosFetcher?.let {
                                        coinAmountValue.text =
                                            formatAssetValue(it.tokenValue(token.contract))
                                    }
                                } else {
                                    chain.evmRpcFetcher?.let {
                                        coinAmountValue.text =
                                            formatAssetValue(it.tokenValue(token.contract))
                                    }
                                }
                            }
                        }

                } ?: run {
                chain.evmRpcFetcher()?.evmTokens?.firstOrNull { token -> token.chain == chain.apiName && token.contract == coin.denom }
                    ?.let { evmToken ->
                        tokenImg.setTokenImg(evmToken.image)
                        tokenImg.clipToOutline = true
                        tokenName.text = evmToken.symbol

                        tokenPrice.text = formatAssetValue(BaseData.getPrice(evmToken.coinGeckoId))
                        BaseData.lastUpDown(evmToken.coinGeckoId).let { lastUpDown ->
                            tokenPriceChange.priceChangeStatusColor(lastUpDown)
                            tokenPriceChange.text = priceChangeStatus(lastUpDown)
                        }

                        evmToken.amount?.toBigDecimal()?.movePointLeft(evmToken.decimals)
                            ?.setScale(6, RoundingMode.DOWN)?.let { amount ->
                                if (Prefs.hideValue) {
                                    coinAmount.visibility = View.GONE
                                    coinAmountValue.visibility = View.GONE
                                    hideValue.visibility = View.VISIBLE
                                } else {
                                    coinAmount.visibility = View.VISIBLE
                                    coinAmountValue.visibility = View.VISIBLE
                                    hideValue.visibility = View.GONE

                                    coinAmount.text = formatAmount(amount.toPlainString(), 6)
                                    if (chain.isSupportCw20()) {
                                        chain.cosmosFetcher?.let {
                                            coinAmountValue.text =
                                                formatAssetValue(it.tokenValue(evmToken.contract))
                                        }
                                    } else {
                                        chain.evmRpcFetcher?.let {
                                            coinAmountValue.text =
                                                formatAssetValue(it.tokenValue(evmToken.contract))
                                        }
                                    }
                                }
                            }
                    }
            }
        }
    }
}