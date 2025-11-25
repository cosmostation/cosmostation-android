package wannabit.io.cosmostaion.ui.main.chain.major

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.APTOS_MAIN_DENOM
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.priceChangeStatus
import wannabit.io.cosmostaion.common.priceChangeStatusColor
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemDefaultAssetViewBinding
import java.math.BigDecimal
import java.math.RoundingMode

class MoveAptosAssetViewHolder(
    private val binding: ItemDefaultAssetViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(chain: BaseChain) {
        binding.apply {
            assetView.setBackgroundResource(R.drawable.item_bg)

            BaseData.getAsset(chain.apiName, chain.getStakeAssetDenom())?.let { asset ->
                assetImg.setTokenImg(asset)
                assetImg.clipToOutline = true
                assetName.text = asset.symbol

                assetPrice.text = formatAssetValue(BaseData.getPrice(asset.coinGeckoId))
                BaseData.lastUpDown(asset.coinGeckoId).let { lastUpDown ->
                    assetPriceChange.priceChangeStatusColor(lastUpDown)
                    assetPriceChange.text = priceChangeStatus(lastUpDown)
                }

                val dpAmount = chain.aptosFetcher?.aptosBalanceAmount(APTOS_MAIN_DENOM)?.movePointLeft(asset.decimals ?: 8)
                    ?.setScale(asset.decimals ?: 8, RoundingMode.DOWN)
                val value = chain.aptosFetcher?.aptosBalanceValue(APTOS_MAIN_DENOM) ?: BigDecimal.ZERO

                if (Prefs.hideValue) {
                    assetAmount.visibility = View.GONE
                    assetAmountValue.visibility = View.GONE
                    hideValue.visibility = View.VISIBLE
                } else {
                    assetAmount.visibility = View.VISIBLE
                    assetAmountValue.visibility = View.VISIBLE
                    hideValue.visibility = View.GONE

                    assetAmount.text = formatAmount(dpAmount.toString(), 6)
                    assetAmountValue.text = formatAssetValue(value)
                }
            }
        }
    }
}
