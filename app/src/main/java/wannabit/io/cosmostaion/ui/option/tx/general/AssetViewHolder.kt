package wannabit.io.cosmostaion.ui.option.tx.general

import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.data.model.res.FeeData
import wannabit.io.cosmostaion.databinding.ItemAssetBinding

class AssetViewHolder(
    private val binding: ItemAssetBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(line: CosmosLine, feeData: FeeData) {
        binding.apply {
            feeData.denom?.let { denom ->
                BaseData.getAsset(line.apiName, denom)?.let { asset ->
                    tokenImg.setTokenImg(asset)
                    tokenName.text = asset.symbol
                }
            }
        }
    }
}