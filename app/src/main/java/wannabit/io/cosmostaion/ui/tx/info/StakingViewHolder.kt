package wannabit.io.cosmostaion.ui.tx.info

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
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
    val context: Context, private val binding: ItemStakingInfoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        line: CosmosLine,
        validator: Validator?,
        delegation: StakingProto.DelegationResponse,
        cnt: Int,
        position: Int,
        listener: StakingInfoAdapter.ClickListener
    ) {
        binding.apply {
            delegationView.setBackgroundResource(R.drawable.item_bg)
            headerLayout.visibleOrGone(position == 0)
            headerCnt.text = cnt.toString()

            delegationView.setOnClickListener {
                listener.selectStakingAction(validator)
            }

            monikerImg.setMonikerImg(line, validator?.operatorAddress)
            moniker.text = validator?.description?.moniker
            if (validator?.jailed == true) {
                jailedImg.visibility = View.VISIBLE
                jailedImg.setImageResource(R.drawable.icon_jailed)
            } else if (validator?.status != StakingProto.BondStatus.BOND_STATUS_BONDED) {
                jailedImg.visibility = View.VISIBLE
                jailedImg.setImageResource(R.drawable.icon_inactive)
            } else {
                jailedImg.visibility = View.GONE
            }

            line.stakeDenom?.let { denom ->
                BaseData.getAsset(line.apiName, denom)?.let { asset ->
                    asset.decimals?.let { decimal ->
                        validator?.tokens?.toBigDecimal()?.movePointLeft(decimal)?.let { vpAmount ->
                            if (vpAmount < BigDecimal.ONE) {
                                votingPower.text = formatAmount(vpAmount.toString(), decimal)
                            } else {
                                votingPower.text = formatAmount(vpAmount.toString(), 0)
                            }
                        }

                        val commissionRate =
                            validator?.commission?.commissionRates?.rate?.toBigDecimal()
                                ?.movePointLeft(16)?.setScale(2, RoundingMode.DOWN)
                        commission.text = formatString("$commissionRate%", 3)
                        if (commissionRate.toString() == "0.00") {
                            commission.setTextColor(
                                ContextCompat.getColorStateList(
                                    context, R.color.color_accent_green
                                )
                            )
                        } else {
                            commission.setTextColor(
                                ContextCompat.getColorStateList(
                                    context, R.color.color_base01
                                )
                            )
                        }

                        val stakedAmount =
                            delegation.balance.amount.toBigDecimal().movePointLeft(decimal)
                        staked.text = formatAmount(stakedAmount.toPlainString(), decimal)

                        line.cosmosRewards.firstOrNull { it.validatorAddress == validator?.operatorAddress }?.rewardList?.let { rewards ->
                            rewards.firstOrNull { it.denom == denom }?.let { mainDenomReward ->
                                val mainDenomRewardAmount =
                                    mainDenomReward.amount.toBigDecimal().movePointLeft(18)
                                        .movePointLeft(decimal).setScale(decimal, RoundingMode.DOWN)
                                rewardAmount.text =
                                    formatAmount(mainDenomRewardAmount.toPlainString(), decimal)
                            }

                            var anotherCnt = 0
                            rewards.filter { it.denom != denom }.forEach { anotherRewards ->
                                val anotherAmount =
                                    anotherRewards.amount.toBigDecimal().movePointLeft(18)
                                        .setScale(0, RoundingMode.DOWN)
                                if (anotherAmount != BigDecimal.ZERO) {
                                    anotherCnt += 1
                                }
                            }
                            rewardTitle.text = if (anotherCnt > 0) {
                                "Reward + $anotherCnt"
                            } else {
                                "Reward"
                            }

                        } ?: run {
                            rewardTitle.text = "Reward"
                            rewardAmount.text = formatAmount(
                                BigDecimal.ZERO.movePointLeft(decimal).toPlainString(), decimal
                            )
                        }

                        val apr = line.param?.params?.apr ?: "0"
                        val staked = delegation.balance.amount.toBigDecimal()
                        val comm = BigDecimal.ONE.subtract(
                            validator?.commission?.commissionRates?.rate?.toBigDecimal()
                                ?.movePointLeft(18)?.setScale(18, RoundingMode.DOWN)
                        )
                        val est = staked.multiply(apr.toBigDecimal()).multiply(comm)
                            .setScale(0, RoundingMode.DOWN)
                            .divide(BigDecimal("12"), 0, RoundingMode.DOWN).movePointLeft(decimal)
                            .setScale(decimal, RoundingMode.DOWN)
                        estimateReward.text = formatAmount(est.toPlainString(), decimal)
                    }
                }
            }
        }
    }
}