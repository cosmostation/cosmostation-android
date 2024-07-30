package wannabit.io.cosmostaion.ui.option.tx.swap

import android.content.Context
import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.base.v1beta1.CoinProto
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.ItemAssetSelectBinding
import wannabit.io.cosmostaion.ui.tx.step.TargetAsset
import wannabit.io.cosmostaion.ui.tx.step.TargetAssetType
import java.math.BigDecimal
import java.math.RoundingMode

class AssetSelectViewHolder(
    val context: Context, private val binding: ItemAssetSelectBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        chain: BaseChain?,
        asset: TargetAsset,
        balances: MutableList<CoinProto.Coin>?
    ) {
        binding.apply {
            tokenImg.setTokenImg(asset.image)
            tokenName.text = asset.symbol

            ibcBadge.visibleOrGone(asset.type == TargetAssetType.IBC)

            when (asset.type) {
                TargetAssetType.ERC20 -> {
                    tokenDescription.ellipsize = TextUtils.TruncateAt.MIDDLE

                }
                TargetAssetType.CW20 -> {
                    tokenDescription.ellipsize = TextUtils.TruncateAt.MIDDLE

                }
                TargetAssetType.IBC -> {
                    tokenDescription.text = asset.denom
                    tokenDescription.ellipsize = TextUtils.TruncateAt.MIDDLE
                }
                else -> {
                    asset.description?.let { description ->
                        tokenDescription.text = description
                        tokenDescription.ellipsize = TextUtils.TruncateAt.END
                    } ?: run {
                        tokenDescription.text = asset.denom
                        tokenDescription.ellipsize = TextUtils.TruncateAt.MIDDLE
                    }
                }
            }

            chain?.let { chain ->
                BaseData.getAsset(chain.apiName, asset.denom)?.let { asset ->
                    val amount = balances?.firstOrNull { it.denom == asset.denom }?.amount?.toBigDecimal()
                        ?: run {
                        BigDecimal.ZERO
                    }

                    val dpAmount =
                        amount.movePointLeft(asset.decimals ?: 6).setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                    tokenBalance.text = formatAmount(dpAmount.toPlainString(), asset.decimals ?: 6)

                    val price = BaseData.getPrice(asset.coinGeckoId)
                    val value = price.multiply(amount).movePointLeft(asset.decimals ?: 6)
                        .setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                    tokenValue.text = formatAssetValue(value)
                }
            }
        }
    }
}