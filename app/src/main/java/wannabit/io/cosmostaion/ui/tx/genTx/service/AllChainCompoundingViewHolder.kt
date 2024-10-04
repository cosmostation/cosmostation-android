package wannabit.io.cosmostaion.ui.tx.genTx.service

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.amountHandlerLeft
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.databinding.ItemAllChainCompoundingBinding
import java.math.BigDecimal
import java.math.RoundingMode

class AllChainCompoundingViewHolder(
    val context: Context,
    private val binding: ItemAllChainCompoundingBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(compoundAbleReward: ClaimAllModel) {
        binding.apply {
            rewardView.setBackgroundResource(R.drawable.cell_bg)
            deleteView.setBackgroundResource(R.drawable.cell_bg)

            compoundAbleReward.apply {
                chainImg.setImageResource(baseChain.logo)
                chainName.text = baseChain.name.uppercase()

                if (!baseChain.isDefault) {
                    chainBadge.visibility = View.VISIBLE
                    chainBadge.text = context.getString(R.string.str_old)
                    chainBadge.setBackgroundResource(R.drawable.round_box_deprecated)
                    chainBadge.setTextColor(
                        ContextCompat.getColor(
                            context, R.color.color_base02
                        )
                    )

                } else {
                    chainBadge.visibility = View.GONE
                }

                var mainRewardAmount = BigDecimal.ZERO
                val mainRewardDenom = baseChain.stakeDenom
                rewards.forEach { reward ->
                    reward?.rewardList?.firstOrNull { it.denom == mainRewardDenom }
                        ?.let { rewardCoin ->
                            val amount = rewardCoin.amount.toBigDecimal().movePointLeft(18)
                                .setScale(0, RoundingMode.DOWN)
                            mainRewardAmount = mainRewardAmount.add(amount)
                        }
                }

                BaseData.getAsset(baseChain.apiName, mainRewardDenom)?.let { asset ->
                    val price = BaseData.getPrice(asset.coinGeckoId, false)
                    val value =
                        price.multiply(mainRewardAmount).movePointLeft(asset.decimals ?: 6)
                            .setScale(6, RoundingMode.DOWN)
                    rewardAmount.text = formatAmount(
                        mainRewardAmount.movePointLeft(asset.decimals ?: 6).toString(),
                        asset.decimals ?: 6
                    )
                    rewardDenom.text = asset.symbol
                    rewardDenom.setTextColor(asset.assetColor())
                    rewardValue.text = formatAssetValue(value)
                }

                val txFee = fee ?: baseChain.getInitPayableFee(context)
                txFee?.let { fee ->
                    BaseData.getAsset(baseChain.apiName, fee.getAmount(0).denom)?.let { asset ->
                        val amount = fee.getAmount(0).amount.toBigDecimal()
                            .amountHandlerLeft(asset.decimals ?: 6)
                        val price = BaseData.getPrice(asset.coinGeckoId)
                        val value = price.multiply(amount)

                        feeAmount.text = formatAmount(amount.toPlainString(), asset.decimals ?: 6)
                        feeDenom.text = asset.symbol
                        feeDenom.setTextColor(asset.assetColor())
                        feeValue.text = formatAssetValue(value)

                        progress.visibility = View.GONE
                        simulateImg.visibility = View.VISIBLE
                    }
                }

                if (compoundAbleReward.isBusy && compoundAbleReward.txResponse != null) {
                    progress.visibility = View.GONE
                    simulateImg.visibility = View.VISIBLE
                    simulateImg.setImageResource(R.drawable.icon_complete)

                } else if (compoundAbleReward.isBusy && compoundAbleReward.txResponse == null) {
                    progress.visibility = View.VISIBLE
                    simulateImg.visibility = View.GONE

                } else {
                    return
                }
            }
        }
    }
}