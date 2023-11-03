package wannabit.io.cosmostaion.ui.tx.info

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.staking.v1beta1.StakingProto.Validator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.dpTime
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.gapTime
import wannabit.io.cosmostaion.common.setMonikerImg
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.ItemUnstakingInfoBinding

class UnstakingViewHolder(
    val context: Context,
    private val binding: ItemUnstakingInfoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(line: CosmosLine, validator: Validator?, entry: UnBondingEntry,
             cnt: Int, position: Int, listener: StakingInfoAdapter.ClickListener) {
        binding.apply {
            unstakingView.setBackgroundResource(R.drawable.item_bg)
            headerLayout.visibleOrGone(position == 0)
            headerCnt.text = cnt.toString()

            unstakingView.setOnClickListener {
                listener.selectUnStakingCancelAction(validator)
            }

            validator?.let { validator ->
                monikerImg.setMonikerImg(line, validator.operatorAddress)
                moniker.text = validator.description?.moniker
                jailedImg.visibleOrGone(validator.jailed)
            }

            line.stakeDenom?.let { denom ->
                BaseData.getAsset(line.apiName, denom)?.let { asset ->
                    asset.decimals?.let { decimal ->
                        val unBondingAmount = entry.entry?.balance?.toBigDecimal()?.movePointLeft(decimal)
                        unstaked.text = formatAmount(unBondingAmount.toString(), decimal)

                        entry.entry?.completionTime?.let {
                            remainDay.text = gapTime(it.seconds * 1000)
                            unstakingDay.text = dpTime(it.seconds * 1000)
                        }
                    }
                }
            }
        }
    }
}