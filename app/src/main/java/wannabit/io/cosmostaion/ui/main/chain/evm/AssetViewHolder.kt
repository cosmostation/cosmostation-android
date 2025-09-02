package wannabit.io.cosmostaion.ui.main.chain.evm

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
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemEvmAssetBinding
import java.math.BigDecimal
import java.math.RoundingMode

class AssetViewHolder(
    private val binding: ItemEvmAssetBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(evmChain: BaseChain) {
        binding.apply {
            assetView.setBackgroundResource(R.drawable.item_bg)

            BaseData.getAssetWithSymbol(evmChain.apiName, evmChain.getGasAssetSymbol())?.let { asset ->
                assetImg.setTokenImg(asset)
                assetImg.clipToOutline = true
                assetName.text = asset.symbol

                assetPrice.text = formatAssetValue(BaseData.getPrice(asset.coinGeckoId))
                BaseData.lastUpDown(asset.coinGeckoId).let { lastUpDown ->
                    assetPriceChange.priceChangeStatusColor(lastUpDown)
                    assetPriceChange.text = priceChangeStatus(lastUpDown)
                }
            }

            val dpAmount = evmChain.evmRpcFetcher?.evmBalance?.movePointLeft(18)
                ?.setScale(18, RoundingMode.DOWN)
            val value = evmChain.evmRpcFetcher?.allAssetValue(false) ?: BigDecimal.ZERO
            val amount = dpAmount?.setScale(6, RoundingMode.DOWN) ?: BigDecimal.ZERO

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

    fun tokenBind(evmChain: BaseChain, token: Token) {
        binding.apply {
            assetView.setBackgroundResource(R.drawable.item_bg)
            assetImg.setTokenImg(token.image)
            assetImg.clipToOutline = true
            assetName.text = token.symbol

            assetPrice.text = formatAssetValue(BaseData.getPrice(token.coinGeckoId))
            BaseData.lastUpDown(token.coinGeckoId).let { lastUpDown ->
                assetPriceChange.priceChangeStatusColor(lastUpDown)
                assetPriceChange.text = priceChangeStatus(lastUpDown)
            }

            token.amount?.toBigDecimal()?.movePointLeft(token.decimals)
                ?.setScale(6, RoundingMode.DOWN)?.let { amount ->
                    if (Prefs.hideValue) {
                        assetAmount.visibility = View.GONE
                        assetAmountValue.visibility = View.GONE
                        hideValue.visibility = View.VISIBLE
                    } else {
                        assetAmount.visibility = View.VISIBLE
                        assetAmountValue.visibility = View.VISIBLE
                        hideValue.visibility = View.GONE

                        assetAmount.text = formatAmount(amount.toPlainString(), 6)
                        assetAmountValue.text = evmChain.evmRpcFetcher?.tokenValue(token.address)
                            ?.let { formatAssetValue(it) }
                    }
                }
        }
    }
}