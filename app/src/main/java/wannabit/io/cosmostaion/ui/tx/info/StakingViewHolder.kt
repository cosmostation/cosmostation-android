package wannabit.io.cosmostaion.ui.tx.info

import androidx.recyclerview.widget.RecyclerView
import com.cosmos.staking.v1beta1.StakingProto
import com.cosmos.staking.v1beta1.StakingProto.Validator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatString
import wannabit.io.cosmostaion.common.setMonikerImg
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.ItemStakingInfoBinding
import java.math.BigDecimal
import java.math.RoundingMode

class StakingViewHolder(
    private val binding: ItemStakingInfoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(line: CosmosLine, validator: Validator?, delegation: StakingProto.DelegationResponse,
             cnt: Int, position: Int, listener: StakingInfoAdapter.ClickListener
    ) {
        binding.apply {
            delegationView.setBackgroundResource(R.drawable.item_bg)
            headerLayout.visibleOrGone(position == 0)
            headerCnt.text = cnt.toString()

            delegationView.setOnClickListener {
                listener.selectStakingAction(validator)
            }

            validator?.let { validator ->
                monikerImg.setMonikerImg(line, validator.operatorAddress)
                moniker.text = validator.description?.moniker
                jailedImg.visibleOrGone(validator.jailed)
            }

            line.stakeDenom?.let { denom ->
                BaseData.getAsset(line.apiName, denom)?.let { asset ->
                    asset.decimals?.let { decimal ->
                        val vpAmount = validator?.tokens?.toBigDecimal()?.movePointLeft(decimal)
                        votingPower.text = formatAmount(vpAmount.toString(), 0)

                        val commissionRate = validator?.commission?.commissionRates?.rate?.toBigDecimal()?.movePointLeft(16)?.setScale(2, RoundingMode.DOWN)
                        commission.text = formatString("$commissionRate%", 3)

                        val stakedAmount = delegation.balance.amount.toBigDecimal().movePointLeft(decimal)
                        staked.text = formatString(stakedAmount.toPlainString(), decimal)

                        line.cosmosRewards.firstOrNull { it.validatorAddress == validator?.operatorAddress }?.rewardList?.let { rewards ->
                            rewards.firstOrNull { it.denom == denom }?.let { mainDenomReward ->
                                val mainDenomRewardAmount = mainDenomReward.amount.toBigDecimal().movePointLeft(18).movePointLeft(decimal).setScale(decimal, RoundingMode.DOWN)
                                rewardAmount.text = formatString(mainDenomRewardAmount.toPlainString(), decimal)
                            }

                            var anotherCnt = 0
                            rewards.filter { it.denom != denom }.forEach { anotherRewards ->
                                val anotherAmount = anotherRewards.amount.toBigDecimal().movePointLeft(18).setScale(0, RoundingMode.DOWN)
                                if (anotherAmount != BigDecimal.ZERO) {
                                    anotherCnt += 1
                                }
                            }
                            if (anotherCnt > 0) {
                                rewardTitle.text = "Reward + " + anotherCnt.toString()
                            } else {
                                rewardTitle.text = "Reward"
                            }

                        } ?: run {
                            rewardTitle.text = "Reward"
                            rewardAmount.text = formatString(BigDecimal.ZERO.movePointLeft(decimal).toPlainString(), decimal)
                        }
                    }
                }
            }
        }
    }
}