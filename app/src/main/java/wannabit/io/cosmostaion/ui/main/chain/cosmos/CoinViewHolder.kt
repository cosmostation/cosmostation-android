package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Context
import android.graphics.BitmapFactory
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.bouncycastle.util.encoders.Base64
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.fetcher.assetImg
import wannabit.io.cosmostaion.chain.fetcher.suiCoinSymbol
import wannabit.io.cosmostaion.chain.majorClass.ChainNamada
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.priceChangeStatus
import wannabit.io.cosmostaion.common.priceChangeStatusColor
import wannabit.io.cosmostaion.common.setImageFromSvg
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.data.model.res.Coin
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemCosmosTokenBinding
import java.math.BigDecimal
import java.math.RoundingMode

class CoinViewHolder(
    val context: Context, private val binding: ItemCosmosTokenBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(chain: BaseChain, coin: Coin) {
        binding.apply {
            coinView.setBackgroundResource(R.drawable.item_bg)

            BaseData.getAsset(chain.apiName, coin.denom)?.let { asset ->
                tokenImg.setTokenImg(asset)
                tokenImg.clipToOutline = true
                tokenName.text = asset.symbol

                tokenPrice.text = formatAssetValue(BaseData.getPrice(asset.coinGeckoId))
                BaseData.lastUpDown(asset.coinGeckoId).let { lastUpDown ->
                    tokenPriceChange.priceChangeStatusColor(lastUpDown)
                    tokenPriceChange.text = priceChangeStatus(lastUpDown)
                }

                val amount = coin.amount.toBigDecimal().movePointLeft(asset.decimals ?: 6)
                    .setScale(6, RoundingMode.DOWN)
                if (Prefs.hideValue) {
                    coinAmount.visibility = View.GONE
                    coinAmountValue.visibility = View.GONE
                    hideValue.visibility = View.VISIBLE
                } else {
                    coinAmount.visibility = View.VISIBLE
                    coinAmountValue.visibility = View.VISIBLE
                    hideValue.visibility = View.GONE

                    coinAmount.text = formatAmount(amount.toPlainString(), 6)
                    coinAmountValue.text = if (chain is ChainNamada) {
                        chain.namadaFetcher?.denomValue(coin.denom)?.let { formatAssetValue(it) }
                    } else {
                        chain.cosmosFetcher?.denomValue(coin.denom)?.let {
                            formatAssetValue(it) }
                    }
                }
            }
        }
    }

    fun suiBind(chain: BaseChain, balance: Pair<String?, BigDecimal?>) {
        binding.apply {
            coinView.setBackgroundResource(R.drawable.item_bg)

            (chain as ChainSui).suiFetcher()?.let { fetcher ->
                balance.first?.let { denom ->
                    val asset = BaseData.getAsset(chain.apiName, denom)
                    val metaData = fetcher.suiCoinMeta[denom]

                    if (asset != null) {
                        tokenImg.setTokenImg(asset)
                        tokenImg.clipToOutline = true
                        tokenName.text = asset.symbol

                        val amount = balance.second?.movePointLeft(asset.decimals ?: 6)
                            ?.setScale(6, RoundingMode.DOWN)
                        coinAmount.text = formatAmount(amount.toString(), 6)

                    } else if (metaData != null) {
                        if (metaData.assetImg().contains("base64")) {
                            val base64String = metaData.assetImg().substringAfter("base64,")
                            val decodedString = Base64.decode(base64String)
                            val base64ToBitmap =
                                BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                            tokenImg.setImageBitmap(base64ToBitmap)
                        } else {
                            tokenImg.setImageFromSvg(metaData.assetImg(), R.drawable.token_default)
                        }

                        tokenName.text = metaData["symbol"].asString
                        val dpAmount = balance.second?.movePointLeft(metaData["decimals"].asInt)
                            ?.setScale(18, RoundingMode.DOWN)
                        coinAmount.text = formatAmount(dpAmount.toString(), 6)

                    } else {
                        tokenName.text = balance.first.suiCoinSymbol()
                        val dpAmount =
                            balance.second?.movePointLeft(9)?.setScale(18, RoundingMode.DOWN)
                        coinAmount.text = formatAmount(dpAmount.toString(), 6)
                    }

                    coinAmountValue.text = formatAssetValue(fetcher.suiBalanceValue(denom))
                    tokenPrice.text = formatAssetValue(BaseData.getPrice(asset?.coinGeckoId))
                    BaseData.lastUpDown(asset?.coinGeckoId).let { lastUpDown ->
                        tokenPriceChange.priceChangeStatusColor(lastUpDown)
                        tokenPriceChange.text = priceChangeStatus(lastUpDown)
                    }
                }
            }

            if (Prefs.hideValue) {
                coinAmount.visibility = View.GONE
                coinAmountValue.visibility = View.GONE
                hideValue.visibility = View.VISIBLE
            } else {
                coinAmount.visibility = View.VISIBLE
                coinAmountValue.visibility = View.VISIBLE
                hideValue.visibility = View.GONE
            }
        }
    }
}