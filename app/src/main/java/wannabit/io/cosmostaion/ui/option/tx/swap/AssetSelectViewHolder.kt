package wannabit.io.cosmostaion.ui.option.tx.swap

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.base.v1beta1.CoinProto
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.data.model.res.Asset
import wannabit.io.cosmostaion.databinding.ItemAssetSelectBinding
import java.math.RoundingMode

class AssetSelectViewHolder(
    val context: Context, private val binding: ItemAssetSelectBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(asset: Asset, balances: MutableList<CoinProto.Coin>?) {
        binding.apply {
            tokenImg.setTokenImg(asset)
            tokenName.text = asset.symbol

            balances?.firstOrNull { it.denom == asset.denom }?.let { coin ->
                val dpAmount = coin.amount.toBigDecimal().movePointLeft(asset.decimals ?: 6)
                    .setScale(6, RoundingMode.DOWN)
                tokenBalance.text = formatAmount(dpAmount.toPlainString(), asset.decimals ?: 6)

                val price = BaseData.getPrice(asset.coinGeckoId)
                val value =
                    price.multiply(coin.amount.toBigDecimal()).movePointLeft(asset.decimals ?: 6)
                        .setScale(6, RoundingMode.DOWN)
                tokenValue.text = formatAssetValue(value)
            }
        }
    }
}