package wannabit.io.cosmostaion.ui.main.chain.major

import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.priceChangeStatus
import wannabit.io.cosmostaion.common.priceChangeStatusColor
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.databinding.ItemEvmAssetBinding

class MajorAssetViewHolder(
    private val binding: ItemEvmAssetBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(evmChain: BaseChain) {
        binding.apply {
            assetView.setBackgroundResource(R.drawable.item_bg)

            BaseData.getAsset(evmChain.apiName, evmChain.coinSymbol)?.let { asset ->
                assetImg.setTokenImg(asset)
                assetImg.clipToOutline = true
                assetName.text = asset.symbol

                assetPrice.text = formatAssetValue(BaseData.getPrice(asset.coinGeckoId))
                BaseData.lastUpDown(asset.coinGeckoId).let { lastUpDown ->
                    assetPriceChange.priceChangeStatusColor(lastUpDown)
                    assetPriceChange.text = priceChangeStatus(lastUpDown)
                }
            }
        }
    }
}