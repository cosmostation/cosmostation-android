package wannabit.io.cosmostaion.ui.tx.info.major.sui

import android.content.Context
import android.graphics.PorterDuff
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.fetcher.StakeReward
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.common.setImageFromSvg
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.ItemSuiStakingInfoBinding
import java.math.RoundingMode

class SuiStakingInfoViewHolder(
    val context: Context, private val binding: ItemSuiStakingInfoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(chain: BaseChain, staked: StakeReward) {
        binding.apply {
            stakeCoinView.setBackgroundResource(R.drawable.item_bg)
            clickImg.setColorFilter(
                ContextCompat.getColor(context, R.color.color_base03), PorterDuff.Mode.SRC_IN
            )

            (chain as ChainSui).suiFetcher()?.let { fetcher ->
                fetcher.suiValidators.firstOrNull { it.address == staked.validatorAddress }
                    ?.let { validator ->
                        monikerImg.setImageFromSvg(
                            validator.imageUrl, R.drawable.icon_default_vaildator
                        )
                        monikerName.text = validator.name
                    }

                clickImg.goneOrVisible(staked.isPending)
                pendingBadge.visibleOrGone(staked.isPending)
                pendingBadge.setColorFilter(
                    ContextCompat.getColor(context, R.color.color_blue), PorterDuff.Mode.SRC_IN
                )
                objectId.text = staked.objectId

                val principal =
                    staked.principal.toBigDecimal().movePointLeft(9).setScale(9, RoundingMode.DOWN)
                val estimatedReward = staked.estimatedReward.toBigDecimal().movePointLeft(9)
                    .setScale(9, RoundingMode.DOWN)

                principalTxt.text = formatAmount(principal.toString(), 9)
                earned.text = formatAmount(estimatedReward.toString(), 9)
                totalStaked.text = formatAmount(principal.add(estimatedReward).toString(), 9)
                startEarning.text = "Epoch #" + staked.activationEpoch
            }
        }
    }
}