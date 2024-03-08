package wannabit.io.cosmostaion.ui.tx.step

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.cosmosClass.ChainDydx
import wannabit.io.cosmostaion.chain.cosmosClass.DYDX_USDC_DENOM
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.amountHandlerLeft
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.databinding.ItemAllChainClaimBinding
import java.math.BigDecimal
import java.math.RoundingMode

class AllChainClaimViewHolder(
    val context: Context,
    private val binding: ItemAllChainClaimBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(valueAbleReward: ValueAbleReward) {
        binding.apply {
            rewardView.setBackgroundResource(R.drawable.cell_bg)

            valueAbleReward.apply {
                chainImg.setImageResource(cosmosLine.logo)
                chainName.text = cosmosLine.name.uppercase()

                if (!cosmosLine.isDefault) {
                    chainBadge.visibility = View.VISIBLE
                    chainBadge.text = context.getString(R.string.str_legacy)
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
                val mainRewardDenom = if (cosmosLine is ChainDydx) {
                    DYDX_USDC_DENOM
                } else {
                    cosmosLine.stakeDenom
                }

                rewards.forEach { reward ->
                    reward?.rewardList?.firstOrNull { it.denom == mainRewardDenom }
                        ?.let { rewardCoin ->
                            val amount = rewardCoin.amount.toBigDecimal().movePointLeft(18)
                                .setScale(0, RoundingMode.DOWN)
                            mainRewardAmount = mainRewardAmount.add(amount)
                        }
                }

                mainRewardDenom?.let { denom ->
                    BaseData.getAsset(cosmosLine.apiName, denom)?.let { asset ->
                        rewardAmount.text = formatAmount(
                            mainRewardAmount.movePointLeft(asset.decimals ?: 6).toString(),
                            asset.decimals ?: 6
                        )
                        rewardDenom.text = asset.symbol
                        rewardDenom.setTextColor(asset.assetColor())
                    }
                }

                val rewardDenoms: MutableList<String> = mutableListOf()
                val rewardsValue =
                    rewards.asSequence().flatMap { it?.rewardList.orEmpty().asSequence() }
                        .mapNotNull { decCoin ->
                            BaseData.getAsset(cosmosLine.apiName, decCoin.denom)?.let { asset ->
                                val price = BaseData.getPrice(asset.coinGeckoId, false)
                                val amount = decCoin.amount.toBigDecimal().movePointLeft(18)
                                    .setScale(0, RoundingMode.DOWN)
                                if (amount > BigDecimal.ZERO) {
                                    rewardDenoms.takeIf { decCoin.denom !in it }?.add(decCoin.denom)
                                }

                                price.multiply(amount).movePointLeft(asset.decimals ?: 6)
                                    .setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                                    .takeIf { amount > BigDecimal.ZERO }
                            }
                        }.fold(BigDecimal.ZERO, BigDecimal::add)

                rewardValue.text = formatAssetValue(rewardsValue)

                if (rewardDenoms.size > 1) {
                    rewardCnt.text = "(+" + (rewardDenoms.size - 1) + ")"
                } else {
                    rewardCnt.text = ""
                }

                valueAbleReward.fee?.let { txFee ->
                    BaseData.getAsset(cosmosLine.apiName, txFee.getAmount(0).denom)?.let { asset ->
                        val amount = txFee.getAmount(0).amount.toBigDecimal()
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

                if (valueAbleReward.isSuccess && valueAbleReward.txResponse != null) {
                    progress.visibility = View.GONE
                    simulateImg.visibility = View.VISIBLE
                    simulateImg.setImageResource(R.drawable.icon_complete)

                } else if (valueAbleReward.isSuccess && valueAbleReward.txResponse == null) {
                    progress.visibility = View.VISIBLE
                    simulateImg.visibility = View.GONE

                } else {
                    return
                }
            }
        }
    }
}