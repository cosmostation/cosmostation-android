package wannabit.io.cosmostaion.ui.tx.option.swap

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.base.v1beta1.CoinProto
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.ItemAssetSelectBinding
import wannabit.io.cosmostaion.ui.tx.genTx.TargetAsset
import wannabit.io.cosmostaion.ui.tx.genTx.TargetAssetType
import java.math.BigDecimal
import java.math.RoundingMode

class AssetSelectViewHolder(
    val context: Context, private val binding: ItemAssetSelectBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        chain: BaseChain?,
        asset: TargetAsset,
        toAsset: TargetAsset?,
        balances: MutableList<CoinProto.Coin>?
    ) {
        binding.apply {
            if (asset == toAsset) {
                tokenView.visibility = View.VISIBLE
                tokenViewLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        context, R.color.color_base08
                    )
                )

            } else {
                tokenView.visibility = View.GONE
                tokenViewLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        context, R.color.color_transparent
                    )
                )
            }

            tokenImg.setTokenImg(asset.image)
            tokenName.text = asset.symbol

            ibcBadge.visibleOrGone(asset.type == TargetAssetType.IBC)
            tokenDescription.text = asset.description

            chain?.let { chain ->
                BaseData.getAsset(chain.apiName, asset.denom)?.let { asset ->
                    val amount =
                        balances?.firstOrNull { it.denom == asset.denom }?.amount?.toBigDecimal()
                            ?: run {
                                BigDecimal.ZERO
                            }
                    val dpAmount = amount.movePointLeft(asset.decimals ?: 6)
                        .setScale(asset.decimals ?: 6, RoundingMode.DOWN)

                    val price = BaseData.getPrice(asset.coinGeckoId)
                    val value =
                        dpAmount.multiply(price).setScale(asset.decimals ?: 6, RoundingMode.DOWN)

                    tokenBalance.text = formatAmount(dpAmount.toPlainString(), asset.decimals ?: 6)
                    tokenValue.text = formatAssetValue(value)
                }

            } ?: run {
                tokenBalance.text = ""
                tokenValue.text = ""
            }
        }
    }
}