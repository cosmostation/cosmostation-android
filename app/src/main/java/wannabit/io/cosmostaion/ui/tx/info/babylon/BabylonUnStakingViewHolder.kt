package wannabit.io.cosmostaion.ui.tx.info.babylon

import android.content.Context
import android.graphics.PorterDuff
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.staking.v1beta1.StakingProto.Validator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.dpTimeNotSecond
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.gapTime
import wannabit.io.cosmostaion.common.isActiveValidator
import wannabit.io.cosmostaion.common.setMonikerImg
import wannabit.io.cosmostaion.databinding.ItemBabylonUnstakingBinding
import wannabit.io.cosmostaion.ui.tx.info.UnBondingEntry

class BabylonUnStakingViewHolder(
    val context: Context, private val binding: ItemBabylonUnstakingBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        chain: BaseChain,
        validator: Validator,
        entry: UnBondingEntry,
        listener: BabylonUnStakingAdapter.ClickListener
    ) {
        binding.apply {
            unstakingView.setBackgroundResource(R.drawable.item_bg)
            clickImg.setColorFilter(
                ContextCompat.getColor(context, R.color.color_base03), PorterDuff.Mode.SRC_IN
            )
//            unstakingView.setOnClickListener {
//                listener.selectUnStakingAction(entry)
//            }

            monikerImg.setMonikerImg(chain, validator.operatorAddress)
            moniker.text = validator.description?.moniker?.trim()
            val statusImage = when {
                validator.jailed -> R.drawable.icon_jailed
                !validator.isActiveValidator(chain) -> R.drawable.icon_inactive
                else -> 0
            }
            jailedImg.visibility = if (statusImage != 0) View.VISIBLE else View.GONE
            jailedImg.setImageResource(statusImage)

            BaseData.getAsset(chain.apiName, chain.getStakeAssetDenom())?.let { asset ->
                val unBondingAmount =
                    entry.entry?.balance?.toBigDecimal()?.movePointLeft(asset.decimals ?: 6)
                unstaked.text = formatAmount(unBondingAmount.toString(), asset.decimals ?: 6)

                entry.entry?.completionTime?.let {
                    try {
                        val stakingUnBondingTime = chain.getChainParam()?.getAsJsonObject("params")
                            ?.getAsJsonObject("staking_params")?.getAsJsonObject("params")
                            ?.get("unbonding_time")?.asString
                        val estimateUnBondingTime =
                            stakingUnBondingTime?.replace(Regex("\\D"), "")?.toLong() ?: 1L

                        val txTime = (it.seconds.minus(1814400L).plus(estimateUnBondingTime)) * 1000
                        remainDay.text = "${"Est."} ${gapTime(txTime)}"
                        unstakingDay.text = "${dpTimeNotSecond(txTime)}"

                    } catch (e: Exception) {

                    }
                }
            }
        }
    }

    fun notBind() {
        binding.apply {
            unstakingView.setBackgroundResource(R.drawable.item_bg)
            moniker.text = "Unknown"
        }
    }
}