package wannabit.io.cosmostaion.ui.option.tx.swap

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.base.v1beta1.CoinProto
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.data.model.res.Asset
import wannabit.io.cosmostaion.databinding.ItemAssetSelectBinding
import java.math.BigDecimal
import java.math.RoundingMode

class AssetSelectViewHolder(
    val context: Context, private val binding: ItemAssetSelectBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(selectedChain: CosmosLine?, asset: Asset, balances: MutableList<CoinProto.Coin>?) {
        binding.apply {
            tokenImg.setTokenImg(asset)
            tokenName.text = asset.symbol

            selectedChain?.let { chain ->
                BaseData.getAsset(chain.apiName, asset.denom ?: "")?.let { asset ->
                    val amount = balances?.firstOrNull { it.denom == asset.denom }?.let { coin ->
                        coin.amount.toBigDecimal()
                    } ?: run {
                        BigDecimal.ZERO
                    }

                    val dpAmount =
                        amount.movePointLeft(asset.decimals ?: 6).setScale(6, RoundingMode.DOWN)
                    tokenBalance.text = formatAmount(dpAmount.toPlainString(), asset.decimals ?: 6)

                    val price = BaseData.getPrice(asset.coinGeckoId)
                    val value = price.multiply(amount).movePointLeft(asset.decimals ?: 6)
                        .setScale(6, RoundingMode.DOWN)
                    tokenValue.text = formatAssetValue(value)
                }
            }
        }
    }
}