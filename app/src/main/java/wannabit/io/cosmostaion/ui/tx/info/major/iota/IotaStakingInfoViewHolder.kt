package wannabit.io.cosmostaion.ui.tx.info.major.iota

import android.content.Context
import android.graphics.PorterDuff
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.fetcher.moveValidatorImg
import wannabit.io.cosmostaion.chain.fetcher.moveValidatorName
import wannabit.io.cosmostaion.chain.majorClass.ChainIota
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.common.setImageFromSvg
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.ItemSuiStakingInfoBinding
import java.math.BigDecimal
import java.math.RoundingMode

class IotaStakingInfoViewHolder(
    val context: Context, private val binding: ItemSuiStakingInfoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(chain: BaseChain, staked: Pair<String, JsonObject>) {
        binding.apply {
            stakeCoinView.setBackgroundResource(R.drawable.item_bg)
            clickImg.setColorFilter(
                ContextCompat.getColor(context, R.color.color_base03), PorterDuff.Mode.SRC_IN
            )

            (chain as ChainIota).iotaFetcher?.let { fetcher ->
                fetcher.iotaValidators.firstOrNull { it["iotaAddress"].asString == staked.first }
                    ?.let { validator ->
                        monikerImg.setImageFromSvg(
                            validator.moveValidatorImg(), R.drawable.icon_default_vaildator
                        )
                        monikerName.text = validator.moveValidatorName()
                    }

                clickImg.goneOrVisible(staked.second["status"].asString == "Pending")
                pendingBadge.visibleOrGone(staked.second["status"].asString == "Pending")
                pendingBadge.setColorFilter(
                    ContextCompat.getColor(context, R.color.color_blue), PorterDuff.Mode.SRC_IN
                )
                objectId.text = staked.second["stakedIotaId"].asString

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