package wannabit.io.cosmostaion.ui.main.chain.major

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.majorClass.ChainSolana
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.priceChangeStatus
import wannabit.io.cosmostaion.common.priceChangeStatusColor
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemEvmAssetBinding
import java.math.BigDecimal
import java.math.RoundingMode

class SolanaAssetViewHolder(
    private val binding: ItemEvmAssetBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(chain: ChainSolana) {
        binding.apply {
            assetView.setBackgroundResource(R.drawable.item_bg)

            BaseData.getAsset(chain.apiName, chain.coinSymbol)?.let { asset ->
                assetImg.setTokenImg(asset)
                assetImg.clipToOutline = true
                assetName.text = asset.symbol

                assetPrice.text = formatAssetValue(BaseData.getPrice(asset.coinGeckoId))
                BaseData.lastUpDown(asset.coinGeckoId).let { lastUpDown ->
                    assetPriceChange.priceChangeStatusColor(lastUpDown)
                    assetPriceChange.text = priceChangeStatus(lastUpDown)
                }

                val dpAmount =
                    chain.solanaFetcher?.solanaBalanceAmount()?.movePointLeft(asset.decimals ?: 6)
                        ?.setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                val value = chain.solanaFetcher?.solanaBalanceValue() ?: BigDecimal.ZERO
                val amount = dpAmount?.setScale(6, RoundingMode.DOWN)

                if (Prefs.hideValue) {
                    assetAmount.visibility = View.GONE
                    assetAmountValue.visibility = View.GONE
                    hideValue.visibility = View.VISIBLE
                } else {
                    assetAmount.visibility = View.VISIBLE
                    assetAmountValue.visibility = View.VISIBLE
                    hideValue.visibility = View.GONE

                    assetAmount.text = formatAmount(amount.toString(), 6)
                    assetAmountValue.text = formatAssetValue(value)
                }
            }
        }
    }

    fun tokenBind(chain: ChainSolana, token: JsonObject) {
        binding.apply {
            assetView.setBackgroundResource(R.drawable.item_bg)

            BaseData.getToken(chain, chain.apiName, token["mint"].asString)
                ?.let { splToken ->
                    assetImg.setTokenImg(splToken.image)
                    assetImg.clipToOutline = true
                    assetName.text = splToken.symbol

                    assetPrice.text = formatAssetValue(BaseData.getPrice(splToken.coinGeckoId))
                    BaseData.lastUpDown(splToken.coinGeckoId).let { lastUpDown ->
                        assetPriceChange.priceChangeStatusColor(lastUpDown)
                        assetPriceChange.text = priceChangeStatus(lastUpDown)
                    }

                    val value =
                        chain.solanaFetcher?.splTokenValue(splToken.address) ?: BigDecimal.ZERO
                    val amount =
                        token["tokenAmount"].asJsonObject["uiAmountString"].asString.toBigDecimal()
                            .setScale(6, RoundingMode.DOWN)

                    if (Prefs.hideValue) {
                        assetAmount.visibility = View.GONE
                        assetAmountValue.visibility = View.GONE
                        hideValue.visibility = View.VISIBLE
                    } else {
                        assetAmount.visibility = View.VISIBLE
                        assetAmountValue.visibility = View.VISIBLE
                        hideValue.visibility = View.GONE

                        assetAmount.text = formatAmount(amount.toString(), 6)
                        assetAmountValue.text = formatAssetValue(value)
                    }

                } ?: run {
                assetImg.setImageResource(R.drawable.token_default)
                assetImg.clipToOutline = true
                assetName.text = "UNKNOWN"

                assetPrice.text = formatAssetValue(BaseData.getPrice(""))
                BaseData.lastUpDown("").let { lastUpDown ->
                    assetPriceChange.priceChangeStatusColor(lastUpDown)
                    assetPriceChange.text = priceChangeStatus(lastUpDown)
                }

                val value =
                    chain.solanaFetcher?.splTokenValue("") ?: BigDecimal.ZERO
                val amount =
                    token["tokenAmount"].asJsonObject["uiAmountString"].asString.toBigDecimal()
                        .setScale(6, RoundingMode.DOWN)

                if (Prefs.hideValue) {
                    assetAmount.visibility = View.GONE
                    assetAmountValue.visibility = View.GONE
                    hideValue.visibility = View.VISIBLE
                } else {
                    assetAmount.visibility = View.VISIBLE
                    assetAmountValue.visibility = View.VISIBLE
                    hideValue.visibility = View.GONE

                    assetAmount.text = formatAmount(amount.toString(), 6)
                    assetAmountValue.text = formatAssetValue(value)
                }
            }
        }
    }
}