package wannabit.io.cosmostaion.ui.main.chain.cosmos

import androidx.recyclerview.widget.RecyclerView
import com.cosmos.base.v1beta1.CoinProto.Coin
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.databinding.ItemRewardBinding
import java.math.BigDecimal
import java.math.RoundingMode

class RewardViewHolder(
    private val binding: ItemRewardBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(line: CosmosLine, rewardCoin: Coin) {
        binding.apply {
            rewardView.setBackgroundResource(R.drawable.item_bg)

            BaseData.getAsset(line.apiName, rewardCoin.denom)?.let { asset ->
                tokenImg.setTokenImg(asset)
                tokenName.text = asset.symbol

                val amount = rewardCoin.amount.toBigDecimal().movePointLeft(asset.decimals ?: 6)
                    .setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                val price = BaseData.getPrice(asset.coinGeckoId)
                val value = price.multiply(amount)

                coinAmount.text = formatAmount(amount.toPlainString(), asset.decimals ?: 6)
                coinAmountValue.text = formatAssetValue(value)

            } ?: run {
                tokenImg.setImageResource(R.drawable.token_default)
                tokenName.text = rewardCoin.denom

                val amount =
                    rewardCoin.amount.toBigDecimal().movePointLeft(6).setScale(6, RoundingMode.DOWN)
                coinAmount.text = formatAmount(amount.toPlainString(), 6)
                coinAmountValue.text = formatAssetValue(BigDecimal.ZERO)
            }
        }
    }
}