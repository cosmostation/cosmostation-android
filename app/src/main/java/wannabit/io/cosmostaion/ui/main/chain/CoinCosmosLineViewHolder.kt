package wannabit.io.cosmostaion.ui.main.chain

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.BNB_GECKO_ID
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt60
import wannabit.io.cosmostaion.chain.cosmosClass.OKT_GECKO_ID
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.common.priceChangeStatus
import wannabit.io.cosmostaion.common.priceChangeStatusColor
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemCosmosLineCoinBinding
import java.math.BigDecimal
import java.math.RoundingMode

class CoinCosmosLineViewHolder(
    private val binding: ItemCosmosLineCoinBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(context: Context, line: CosmosLine) {
        when (line) {
            is ChainBinanceBeacon -> { bindBeaconAsset(context, line) }
            is ChainOkt60 -> { bindOkt(line) }
            is ChainNeutron -> { bindNeutron(line) }
            else -> {
                binding.apply {
                    stakeCoinView.setBackgroundResource(R.drawable.item_bg)

                    line.stakeDenom?.let { stakeDenom ->
                        BaseData.getAsset(line.apiName, stakeDenom)?.let { asset ->
                            tokenImg.setTokenImg(asset)
                            tokenName.text = asset.symbol?.uppercase()

                            tokenPrice.text = formatAssetValue(BaseData.getPrice(asset.coinGeckoId))
                            BaseData.lastUpDown(asset.coinGeckoId).let { lastUpDown ->
                                tokenPriceChange.priceChangeStatusColor(lastUpDown)
                                tokenPriceChange.text = priceChangeStatus(lastUpDown)
                            }

                            asset.decimals?.let { decimal ->
                                val availableAmount = line.balanceAmount(stakeDenom).movePointLeft(decimal).setScale(6, RoundingMode.DOWN)

                                val vestingAmount = line.vestingAmount(stakeDenom).movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
                                vestingLayout.goneOrVisible(vestingAmount.compareTo(BigDecimal.ZERO) == 0)

                                val stakedAmount = line.delegationAmountSum().movePointLeft(decimal).setScale(6, RoundingMode.DOWN)

                                val unStakingAmount = line.unbondingAmountSum().movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
                                unstakingLayout.goneOrVisible(unStakingAmount.compareTo(BigDecimal.ZERO) == 0)

                                val rewardAmount = line.rewardAmountSum(stakeDenom).movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
                                rewardLayout.visibleOrGone(line.rewardAllCoins().isNotEmpty())

                                val totalAmount = availableAmount.add(vestingAmount).add(stakedAmount)
                                    .add(unStakingAmount).add(rewardAmount)
                                val value = line.denomValue(stakeDenom)
                                if (line.rewardAllCoins().isNotEmpty()) {
                                    if (line.rewardOtherDenoms() > 0) {
                                        rewardTitle.text = context.getString(R.string.str_reward) + " +" + line.rewardOtherDenoms()
                                    } else {
                                        rewardTitle.text = context.getString(R.string.str_reward)
                                    }
                                }

                                if (Prefs.hideValue) {
                                    total.visibility = View.GONE
                                    totalValue.visibility = View.GONE
                                    hideValue.visibility = View.VISIBLE
                                    available.text = "✱✱✱✱"
                                    vesting.text = "✱✱✱✱"
                                    staked.text = "✱✱✱✱"
                                    unstaking.text = "✱✱✱✱"
                                    reward.text = "✱✱✱✱"
                                    available.textSize = 12f
                                    vesting.textSize = 12f
                                    staked.textSize = 12f
                                    unstaking.textSize = 12f
                                    reward.textSize = 12f

                                } else {
                                    total.visibility = View.VISIBLE
                                    totalValue.visibility = View.VISIBLE
                                    hideValue.visibility = View.GONE

                                    available.text = formatAmount(availableAmount.toPlainString(), 6)
                                    vesting.text = formatAmount(vestingAmount.toPlainString(), 6)
                                    staked.text = formatAmount(stakedAmount.toPlainString(), 6)
                                    unstaking.text = formatAmount(unStakingAmount.toPlainString(), 6)
                                    reward.text = formatAmount(rewardAmount.toPlainString(), 6)

                                    total.text = formatAmount(totalAmount.toPlainString(), 6)
                                    totalValue.text = formatAssetValue(value)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun bindNeutron(line: CosmosLine) {
        binding.apply {
            stakeCoinView.setBackgroundResource(R.drawable.item_bg)
            unstakingLayout.visibility = View.GONE
            rewardLayout.visibility = View.GONE

            val neutronChain = line as? ChainNeutron
            neutronChain?.let { neutronChain ->
                stakedTitle.text = "Vault deposited"
                neutronChain.stakeDenom?.let { denom ->
                    BaseData.getAsset(neutronChain.apiName, denom)?.let { asset ->
                        tokenImg.setTokenImg(asset)
                        tokenName.text = asset.symbol?.uppercase()

                        tokenPrice.text = formatAssetValue(BaseData.getPrice(asset.coinGeckoId))
                        BaseData.lastUpDown(asset.coinGeckoId).let { lastUpDown ->
                            tokenPriceChange.priceChangeStatusColor(lastUpDown)
                            tokenPriceChange.text = priceChangeStatus(lastUpDown)
                        }

                        asset.decimals?.let { decimal ->
                            val availableAmount = line.balanceAmount(denom).movePointLeft(decimal).setScale(6, RoundingMode.DOWN)

                            neutronChain.neutronVestingAmount()?.let { neutronVestingAmount ->
                                val vestingAmount = neutronVestingAmount.movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
                                vestingLayout.goneOrVisible(vestingAmount.compareTo(BigDecimal.ZERO) == 0)

                                val depositedAmount = neutronChain.neutronDeposited.movePointLeft(decimal).setScale(6, RoundingMode.DOWN)

                                val totalAmount = availableAmount.add(vestingAmount).add(depositedAmount)
                                val value = line.denomValue(denom)

                                if (Prefs.hideValue) {
                                    total.visibility = View.GONE
                                    totalValue.visibility = View.GONE
                                    hideValue.visibility = View.VISIBLE
                                    available.text = "✱✱✱✱"
                                    vesting.text = "✱✱✱✱"
                                    staked.text = "✱✱✱✱"
                                    available.textSize = 12f
                                    vesting.textSize = 12f
                                    staked.textSize = 12f

                                } else {
                                    total.visibility = View.VISIBLE
                                    totalValue.visibility = View.VISIBLE
                                    hideValue.visibility = View.GONE

                                    available.text = formatAmount(availableAmount.toPlainString(), 6)
                                    vesting.text = formatAmount(vestingAmount.toPlainString(), 6)
                                    staked.text = formatAmount(depositedAmount.toPlainString(), 6)

                                    total.text = formatAmount(totalAmount.toPlainString(), 6)
                                    totalValue.text = formatAssetValue(value)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun bindBeaconAsset(context: Context, line: ChainBinanceBeacon) {
        binding.apply {
            vestingTitle.text = context.getString(R.string.str_frozen)
            stakedTitle.text = context.getString(R.string.str_locked)
            stakeCoinView.setBackgroundResource(R.drawable.item_bg)

            line.stakeDenom?.let { stakeDenom ->
                tokenImg.setTokenImg(ChainBinanceBeacon().assetImg(stakeDenom))
                tokenName.text = stakeDenom.uppercase()

                tokenPrice.text = formatAssetValue(BaseData.getPrice(BNB_GECKO_ID))
                BaseData.lastUpDown(BNB_GECKO_ID).let { lastUpDown ->
                    tokenPriceChange.priceChangeStatusColor(lastUpDown)
                    tokenPriceChange.text = priceChangeStatus(lastUpDown)
                }

                unstakingLayout.visibility = View.GONE
                rewardLayout.visibility = View.GONE

                if (Prefs.hideValue) {
                    total.visibility = View.GONE
                    totalValue.visibility = View.GONE
                    hideValue.visibility = View.VISIBLE
                    available.text = "✱✱✱✱"
                    vesting.text = "✱✱✱✱"
                    staked.text = "✱✱✱✱"
                    available.textSize = 12f
                    vesting.textSize = 12f
                    staked.textSize = 12f

                } else {
                    total.visibility = View.VISIBLE
                    totalValue.visibility = View.VISIBLE
                    hideValue.visibility = View.GONE

                    val availableAmount = line.lcdBalanceAmount(stakeDenom).movePointLeft(0).setScale(8, RoundingMode.DOWN)
                    available.text = formatAmount(availableAmount.toPlainString(), 8)

                    val frozenAmount = line.lcdBnbFrozenAmount(stakeDenom).movePointLeft(0).setScale(8, RoundingMode.DOWN)
                    vesting.text = formatAmount(frozenAmount.toPlainString(), 8)

                    val lockedAmount = line.lcdBnbLockedAmount(stakeDenom).movePointLeft(0).setScale(8, RoundingMode.DOWN)
                    staked.text = formatAmount(lockedAmount.toPlainString(), 8)

                    val totalAmount = availableAmount.add(availableAmount).add(frozenAmount)
                        .add(lockedAmount)
                    total.text = formatAmount(totalAmount.toPlainString(), 8)
                    val value = line.allValue()
                    totalValue.text = formatAssetValue(value)
                }
            }
        }
    }

    private fun bindOkt(line: ChainOkt60) {
        binding.apply {
            vestingTitle.text = "Deposited"
            stakedTitle.text = "Withdrawing"
            stakeCoinView.setBackgroundResource(R.drawable.item_bg)
            unstakingLayout.visibility = View.GONE
            rewardLayout.visibility = View.GONE

            line.stakeDenom?.let { stakeDenom ->
                tokenImg.setTokenImg(ChainOkt60().assetImg(stakeDenom))
                tokenName.text = stakeDenom.uppercase()

                tokenPrice.text = formatAssetValue(BaseData.getPrice(OKT_GECKO_ID))
                BaseData.lastUpDown(OKT_GECKO_ID).let { lastUpDown ->
                    tokenPriceChange.priceChangeStatusColor(lastUpDown)
                    tokenPriceChange.text = priceChangeStatus(lastUpDown)
                }

                val availableAmount = line.lcdBalanceAmount(stakeDenom)
                val depositAmount = line.lcdOktDepositAmount()
                val withdrawAmount = line.lcdOktWithdrawAmount()
                if (withdrawAmount > BigDecimal.ZERO) {
                    stakedLayout.visibility = View.VISIBLE
                } else {
                    stakedLayout.visibility = View.GONE
                }

                val totalAmount = availableAmount.add(depositAmount)
                    .add(withdrawAmount)
                val value = line.allValue()

                if (Prefs.hideValue) {
                    total.visibility = View.GONE
                    totalValue.visibility = View.GONE
                    hideValue.visibility = View.VISIBLE
                    available.text = "✱✱✱✱"
                    vesting.text = "✱✱✱✱"
                    staked.text = "✱✱✱✱"
                    available.textSize = 12f
                    vesting.textSize = 12f
                    staked.textSize = 12f

                } else {
                    total.visibility = View.VISIBLE
                    totalValue.visibility = View.VISIBLE
                    hideValue.visibility = View.GONE

                    available.text = formatAmount(availableAmount.toPlainString(), 18)
                    vesting.text = formatAmount(depositAmount.toPlainString(), 18)
                    staked.text = formatAmount(withdrawAmount.toPlainString(), 18)

                    total.text = formatAmount(totalAmount.toPlainString(), 18)
                    totalValue.text = formatAssetValue(value)
                }
            }
        }
    }
}