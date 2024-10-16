package wannabit.io.cosmostaion.ui.tx.info.major

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.fetcher.suiValidatorImg
import wannabit.io.cosmostaion.chain.fetcher.suiValidatorName
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.setImageFromSvg
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.ItemSuiStakingInfoBinding
import java.math.BigDecimal
import java.math.RoundingMode

class SuiStakingInfoViewHolder(
    val context: Context, private val binding: ItemSuiStakingInfoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(chain: BaseChain, staked: Pair<String, JsonObject>) {
        binding.apply {
            stakeCoinView.setBackgroundResource(R.drawable.item_bg)

            (chain as ChainSui).suiFetcher()?.let { fetcher ->
                fetcher.suiValidators.firstOrNull { it["suiAddress"].asString == staked.first }
                    ?.let { validator ->
                        monikerImg.setImageFromSvg(
                            validator.suiValidatorImg(), R.drawable.icon_default_vaildator
                        )
                        monikerName.text = validator.suiValidatorName()
                    }

                pendingBadge.visibleOrGone(staked.second["status"].asString == "Pending")
                objectId.text = staked.second["stakedSuiId"].asString

                val principal = try {
                    staked.second["principal"].asLong.toBigDecimal().movePointLeft(9)
                        .setScale(9, RoundingMode.DOWN)
                } catch (e: Exception) {
                    BigDecimal.ZERO
                }

                val estimatedReward = try {
                    staked.second["estimatedReward"].asLong.toBigDecimal().movePointLeft(9)
                        .setScale(9, RoundingMode.DOWN)
                } catch (e: Exception) {
                    BigDecimal.ZERO
                }

                principalTxt.text = formatAmount(principal.toString(), 9)
                earned.text = formatAmount(estimatedReward.toString(), 9)
                totalStaked.text = formatAmount(principal.add(estimatedReward).toString(), 9)
                startEarning.text = "Epoch #" + staked.second["stakeActiveEpoch"].asString
            }
        }
    }
}