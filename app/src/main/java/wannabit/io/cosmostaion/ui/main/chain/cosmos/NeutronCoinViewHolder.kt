package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.common.hiddenStatus
import wannabit.io.cosmostaion.common.priceChangeStatus
import wannabit.io.cosmostaion.common.priceChangeStatusColor
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.data.model.res.Coin
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemNeturonCoinBinding
import java.math.BigDecimal
import java.math.RoundingMode

class NeutronCoinViewHolder(
    private val binding: ItemNeturonCoinBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(chain: ChainNeutron, coin: Coin) {
        binding.apply {
            stakeCoinView.setBackgroundResource(R.drawable.item_bg)

            BaseData.getAsset(chain.apiName, chain.getStakeAssetDenom())?.let { asset ->
                tokenImg.setTokenImg(asset)
                tokenName.text = asset.symbol

                tokenPrice.text = formatAssetValue(BaseData.getPrice(asset.coinGeckoId))
                BaseData.lastUpDown(asset.coinGeckoId).let { lastUpDown ->
                    tokenPriceChange.priceChangeStatusColor(lastUpDown)
                    tokenPriceChange.text = priceChangeStatus(lastUpDown)
                }

                chain.neutronFetcher()?.let { fetcher ->
                    val availableAmount = chain.cosmosFetcher?.availableAmount(chain.getStakeAssetDenom())
                        ?.movePointLeft(asset.decimals ?: 6)?.setScale(6, RoundingMode.DOWN)

                    fetcher.neutronVestingAmount()?.let { neutronVestingAmount ->
                        val vestingAmount = neutronVestingAmount.movePointLeft(asset.decimals ?: 6)
                            .setScale(6, RoundingMode.DOWN)
                        vestingLayout.goneOrVisible(vestingAmount.compareTo(BigDecimal.ZERO) == 0)

                        val stakedAmount = chain.cosmosFetcher?.delegationAmountSum()
                            ?.movePointLeft(asset.decimals ?: 6)?.setScale(6, RoundingMode.DOWN)
                            ?: BigDecimal.ZERO

                        val unStakingAmount = chain.cosmosFetcher?.unbondingAmountSum()
                            ?.movePointLeft(asset.decimals ?: 6)?.setScale(6, RoundingMode.DOWN)
                            ?: BigDecimal.ZERO

                        val rewardAmount = fetcher.neutronRewards.movePointLeft(asset.decimals ?: 6)
                            .setScale(6, RoundingMode.DOWN)

                        val depositedAmount =
                            fetcher.neutronDeposited.movePointLeft(asset.decimals ?: 6)
                                .setScale(6, RoundingMode.DOWN)

                        val totalAmount = availableAmount?.add(vestingAmount)?.add(stakedAmount)
                            ?.add(unStakingAmount)?.add(rewardAmount)?.add(depositedAmount)
                        val value = fetcher.denomValue(chain.getStakeAssetDenom())

                        with(Prefs) {
                            total.visibility = if (hideValue) View.GONE else View.VISIBLE
                            totalValue.visibility = if (hideValue) View.GONE else View.VISIBLE
                            hidingValue.visibility = if (hideValue) View.VISIBLE else View.GONE

                            available.hiddenStatus(
                                formatAmount(
                                    availableAmount.toString(), 6
                                )
                            )
                            vesting.hiddenStatus(
                                formatAmount(
                                    vestingAmount.toString(), 6
                                )
                            )
                            staked.hiddenStatus(
                                formatAmount(
                                    stakedAmount.toPlainString(), 6
                                )
                            )
                            unstaking.hiddenStatus(
                                formatAmount(
                                    unStakingAmount.toPlainString(), 6
                                )
                            )
                            reward.hiddenStatus(
                                formatAmount(
                                    rewardAmount.toPlainString(), 6
                                )
                            )
                            vault.hiddenStatus(
                                formatAmount(
                                    depositedAmount.toString(), 6
                                )
                            )

                            total.text = if (hideValue) "" else formatAmount(
                                totalAmount.toString(), 6
                            )
                            totalValue.text = if (hideValue) "" else value?.let {
                                formatAssetValue(it)
                            }
                        }
                    }
                }
            }
        }
    }
}