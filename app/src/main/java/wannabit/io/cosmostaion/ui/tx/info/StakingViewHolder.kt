package wannabit.io.cosmostaion.ui.tx.info

import android.content.Context
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.staking.v1beta1.StakingProto
import com.cosmos.staking.v1beta1.StakingProto.Validator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatString
import wannabit.io.cosmostaion.common.setMonikerImg
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.ItemStakingInfoBinding
import wannabit.io.cosmostaion.ui.main.chain.cosmos.RewardDialog
import java.math.BigDecimal
import java.math.RoundingMode

class StakingViewHolder(
    val context: Context, private val binding: ItemStakingInfoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        chain: BaseChain,
        validator: Validator,
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

            delegationView.setOnLongClickListener { view ->
                val rewards =
                    chain.cosmosFetcher?.cosmosRewards?.filter { it.validatorAddress == delegation.delegation.validatorAddress }
                if (rewards?.isNotEmpty() == true) {
                    val scaleX = view.scaleX
                    val scaleY = view.scaleY
                    val customDialog = RewardDialog(context, chain, rewards.toMutableList())

                    if (scaleX == 1.0f && scaleY == 1.0f) {
                        view.animate().scaleX(1.1f).scaleY(1.1f).setDuration(300).start()
                        val handler = Handler()
                        handler.postDelayed({
                            customDialog.show()
                        }, 200)
                    }

                    customDialog.setOnDismissListener {
                        view.animate().scaleX(1.0f).scaleY(1.0f).setDuration(300).start()
                    }
                    true

                } else {
                    listener.selectStakingAction(validator)
                    true
                }
            }

            monikerImg.setMonikerImg(chain, validator.operatorAddress)
            moniker.text = validator.description?.moniker
            if (validator.jailed) {
                jailedImg.visibility = View.VISIBLE
                jailedImg.setImageResource(R.drawable.icon_jailed)
            } else if (validator.status != StakingProto.BondStatus.BOND_STATUS_BONDED) {
                jailedImg.visibility = View.VISIBLE
                jailedImg.setImageResource(R.drawable.icon_inactive)
            } else {
                jailedImg.visibility = View.GONE
            }

            BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
                asset.decimals?.let { decimal ->
                    val commissionRate = validator.commission?.commissionRates?.rate?.toBigDecimal()
                        ?.movePointLeft(16)?.setScale(2, RoundingMode.DOWN)
                    commission.text = formatString("$commissionRate%", 3)

                    val stakedAmount =
                        delegation.balance.amount.toBigDecimal().movePointLeft(decimal)
                    staked.text = formatAmount(stakedAmount.toPlainString(), decimal)

                    chain.cosmosFetcher?.cosmosRewards?.firstOrNull { it.validatorAddress == validator.operatorAddress }?.rewardList?.let { rewards ->
                        rewards.firstOrNull { it.denom == chain.stakeDenom }
                            ?.let { mainDenomReward ->
                                val mainDenomRewardAmount =
                                    mainDenomReward.amount.toBigDecimal().movePointLeft(18)
                                        .movePointLeft(decimal).setScale(decimal, RoundingMode.DOWN)
                                rewardAmount.text =
                                    formatAmount(mainDenomRewardAmount.toPlainString(), decimal)
                            } ?: run {
                            rewardTitle.text = "Reward"
                            rewardAmount.text = formatAmount(
                                BigDecimal.ZERO.movePointLeft(decimal).toPlainString(), decimal
                            )
                            estimateReward.text = formatAmount(
                                BigDecimal.ZERO.movePointLeft(decimal).toPlainString(), decimal
                            )
                            return
                        }

                        var anotherCnt = 0
                        rewards.filter { it.denom != chain.stakeDenom }.forEach { anotherRewards ->
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

                    val apr = chain.getChainParam()?.getAsJsonObject("params")?.get("apr")?.asString
                        ?: "0"
                    val staked = delegation.balance.amount.toBigDecimal()
                    val comm = BigDecimal.ONE.subtract(
                        validator.commission?.commissionRates?.rate?.toBigDecimal()
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