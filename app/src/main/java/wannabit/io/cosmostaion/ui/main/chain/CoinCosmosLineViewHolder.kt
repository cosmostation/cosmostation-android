package wannabit.io.cosmostaion.ui.main.chain

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.formatString
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.common.priceChangeStatus
import wannabit.io.cosmostaion.common.priceChangeStatusColor
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.ItemCosmosLineCoinBinding
import java.math.BigDecimal
import java.math.RoundingMode

class CoinCosmosLineViewHolder(
    private val binding: ItemCosmosLineCoinBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(context: Context, line: CosmosLine) {
        if (line is ChainBinanceBeacon) {
            bindBeaconAsset(context, line)

        } else if (line is ChainNeutron) {
            bindNeutron(line)

        } else {
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
                            available.text = formatString(availableAmount.toPlainString(), 6)

                            val vestingAmount = line.vestingAmount(stakeDenom).movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
                            vestingLayout.goneOrVisible(vestingAmount.compareTo(BigDecimal.ZERO) == 0)
                            vesting.text = formatString(vestingAmount.toPlainString(), 6)

                            val stakedAmount = line.delegationAmountSum().movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
                            staked.text = formatString(stakedAmount.toPlainString(), 6)

                            val unStakingAmount = line.unbondingAmountSum().movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
                            unstakingLayout.goneOrVisible(unStakingAmount.compareTo(BigDecimal.ZERO) == 0)
                            unstaking.text = formatString(unStakingAmount.toPlainString(), 6)

                            val rewardAmount = line.rewardAmountSum(stakeDenom).movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
                            rewardLayout.visibleOrGone(line.rewardAllCoins().isNotEmpty())
                            if (line.rewardAllCoins().isNotEmpty()) {
                                if (line.rewardOtherDenoms() > 0) {
                                    rewardTitle.text = context.getString(R.string.str_reward) + " +" + line.rewardOtherDenoms()
                                } else {
                                    rewardTitle.text = context.getString(R.string.str_reward)
                                }
                                reward.text = formatString(rewardAmount.toPlainString(), 6)
                            }

                            val totalAmount = availableAmount.add(vestingAmount).add(stakedAmount)
                                .add(unStakingAmount).add(rewardAmount)
                            total.text = formatString(totalAmount.toPlainString(), 6)
                            val value = line.denomValue(stakeDenom)
                            totalValue.text = formatAssetValue(value)
                        }
                    }
                }
            }
        }
    }

    private fun bindBeaconAsset(context: Context, line: CosmosLine) {
        binding.apply {
            vestingTitle.text = context.getString(R.string.str_frozen)
            stakedTitle.text = context.getString(R.string.str_locked)
            stakeCoinView.setBackgroundResource(R.drawable.item_bg)

            line.stakeDenom?.let { stakeDenom ->
                tokenImg.setTokenImg(ChainBinanceBeacon().assetImg(stakeDenom))
                tokenName.text = stakeDenom.uppercase()

                tokenPrice.text = formatAssetValue(BaseData.getPrice(ChainBinanceBeacon().BNB_GECKO_ID))
                BaseData.lastUpDown(ChainBinanceBeacon().BNB_GECKO_ID).let { lastUpDown ->
                    tokenPriceChange.priceChangeStatusColor(lastUpDown)
                    tokenPriceChange.text = priceChangeStatus(lastUpDown)
                }

                val availableAmount = line.lcdBalanceAmount(stakeDenom).movePointLeft(0).setScale(8, RoundingMode.HALF_UP)
                available.text = formatString(availableAmount.toPlainString(), 8)

                val frozenAmount = line.lcdBnbFrozenAmount(stakeDenom).movePointLeft(0).setScale(8, RoundingMode.HALF_UP)
                vesting.text = formatString(frozenAmount.toPlainString(), 8)

                val lockedAmount = line.lcdBnbLockedAmount(stakeDenom).movePointLeft(0).setScale(8, RoundingMode.HALF_UP)
                staked.text = formatString(lockedAmount.toPlainString(), 8)

                unstakingLayout.visibility = View.GONE
                rewardLayout.visibility = View.GONE

                val totalAmount = availableAmount.add(availableAmount).add(frozenAmount)
                    .add(lockedAmount)
                total.text = formatString(totalAmount.toPlainString(), 8)
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
                            available.text = formatString(availableAmount.toPlainString(), 6)

                            neutronChain.neutronVestingAmount()?.let { neutronVestingAmount ->
                                val vestingAmount = neutronVestingAmount.movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
                                vestingLayout.goneOrVisible(vestingAmount.compareTo(BigDecimal.ZERO) == 0)
                                vesting.text = formatString(vestingAmount.toPlainString(), 6)

                                val depositedAmount = neutronChain.neutronDeposited.movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
                                staked.text = formatString(depositedAmount.toPlainString(), 6)

                                val totalAmount = availableAmount.add(vestingAmount).add(depositedAmount)
                                total.text = formatString(totalAmount.toPlainString(), 6)
                                val value = line.denomValue(denom)
                                totalValue.text = formatAssetValue(value)
                            }
                        }
                    }
                }
            }
        }
    }
}