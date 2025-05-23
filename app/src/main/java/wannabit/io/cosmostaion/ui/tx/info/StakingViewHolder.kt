package wannabit.io.cosmostaion.ui.tx.info

import android.content.Context
import android.graphics.PorterDuff
import android.os.Handler
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.staking.v1beta1.StakingProto
import com.cosmos.staking.v1beta1.StakingProto.Validator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainInitia
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.ChainZenrock
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatString
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.common.isActiveValidator
import wannabit.io.cosmostaion.common.setMonikerImg
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
        listener: StakingInfoAdapter.ClickListener
    ) {
        binding.apply {
            delegationView.setBackgroundResource(R.drawable.item_bg)
            rewardLayout.goneOrVisible(chain is ChainNeutron)
            estimateLayout.goneOrVisible(chain is ChainNeutron)
            clickImg.setColorFilter(
                ContextCompat.getColor(context, R.color.color_base03), PorterDuff.Mode.SRC_IN
            )
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
            moniker.text = validator.description?.moniker?.trim()
            if (validator.jailed) {
                jailedImg.visibility = View.VISIBLE
                jailedImg.setImageResource(R.drawable.icon_jailed)
            } else if (!validator.isActiveValidator(chain)) {
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

    fun initiaBind(
        chain: ChainInitia,
        validator: com.initia.mstaking.v1.StakingProto.Validator,
        delegation: com.initia.mstaking.v1.StakingProto.DelegationResponse,
        listener: StakingInfoAdapter.ClickListener
    ) {
        binding.apply {
            delegationView.setBackgroundResource(R.drawable.item_bg)
            clickImg.setColorFilter(
                ContextCompat.getColor(context, R.color.color_base03), PorterDuff.Mode.SRC_IN
            )
            delegationView.setOnClickListener {
                listener.selectInitiaStakingAction(validator)
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
                    listener.selectInitiaStakingAction(validator)
                    true
                }
            }

            monikerImg.setMonikerImg(chain, validator.operatorAddress)
            moniker.text = validator.description?.moniker?.trim()
            if (validator.jailed) {
                jailedImg.visibility = View.VISIBLE
                jailedImg.setImageResource(R.drawable.icon_jailed)
            } else if (!validator.isActiveValidator(chain)) {
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
                        delegation.balanceList.firstOrNull { it.denom == chain.stakeDenom }?.amount?.toBigDecimal()
                            ?.movePointLeft(decimal) ?: BigDecimal.ZERO
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
                    val staked =
                        delegation.balanceList.firstOrNull { it.denom == chain.stakeDenom }?.amount?.toBigDecimal()
                            ?: BigDecimal.ZERO
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

    fun zenrockBind(
        chain: ChainZenrock,
        validator: com.zrchain.validation.HybridValidationProto.ValidatorHV,
        delegation: com.zrchain.validation.StakingProto.DelegationResponse,
        listener: StakingInfoAdapter.ClickListener
    ) {
        binding.apply {
            delegationView.setBackgroundResource(R.drawable.item_bg)
            clickImg.setColorFilter(
                ContextCompat.getColor(context, R.color.color_base03), PorterDuff.Mode.SRC_IN
            )
            delegationView.setOnClickListener {
                listener.selectZenrockStakingAction(validator)
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
                    listener.selectZenrockStakingAction(validator)
                    true
                }
            }

            monikerImg.setMonikerImg(chain, validator.operatorAddress)
            moniker.text = validator.description?.moniker?.trim()
            if (validator.jailed) {
                jailedImg.visibility = View.VISIBLE
                jailedImg.setImageResource(R.drawable.icon_jailed)
            } else if (!validator.isActiveValidator(chain)) {
                jailedImg.visibility = View.VISIBLE
                jailedImg.setImageResource(R.drawable.icon_inactive)
            } else {
                jailedImg.visibility = View.GONE
            }

            BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
                val commissionRate = validator.commission?.commissionRates?.rate?.toBigDecimal()
                    ?.movePointLeft(16)?.setScale(2, RoundingMode.DOWN)
                commission.text = formatString("$commissionRate%", 3)

                val stakedAmount =
                    delegation.balance.amount.toBigDecimal().movePointLeft(asset.decimals ?: 6)
                staked.text = formatAmount(stakedAmount.toPlainString(), asset.decimals ?: 6)

                chain.zenrockFetcher()?.cosmosRewards?.firstOrNull { it.validatorAddress == validator.operatorAddress }?.rewardList?.let { rewards ->
                    rewards.firstOrNull { it.denom == chain.stakeDenom }
                        ?.let { mainDenomReward ->
                            val mainDenomRewardAmount =
                                mainDenomReward.amount.toBigDecimal().movePointLeft(18)
                                    .movePointLeft(asset.decimals ?: 6)
                                    .setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                            rewardAmount.text =
                                formatAmount(
                                    mainDenomRewardAmount.toPlainString(),
                                    asset.decimals ?: 6
                                )
                        } ?: run {
                        rewardTitle.text = "Reward"
                        rewardAmount.text = formatAmount(
                            BigDecimal.ZERO.movePointLeft(asset.decimals ?: 6).toPlainString(),
                            asset.decimals ?: 6
                        )
                        estimateReward.text = formatAmount(
                            BigDecimal.ZERO.movePointLeft(asset.decimals ?: 6).toPlainString(),
                            asset.decimals ?: 6
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
                        BigDecimal.ZERO.movePointLeft(asset.decimals ?: 6).toPlainString(),
                        asset.decimals ?: 6
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
                    .divide(BigDecimal("12"), 0, RoundingMode.DOWN)
                    .movePointLeft(asset.decimals ?: 6)
                    .setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                estimateReward.text = formatAmount(est.toPlainString(), asset.decimals ?: 6)
            }
        }
    }
}