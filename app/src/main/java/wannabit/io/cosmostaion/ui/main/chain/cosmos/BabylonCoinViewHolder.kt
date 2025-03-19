package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.testnetClass.ChainBabylonTestnet
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.common.hiddenStatus
import wannabit.io.cosmostaion.common.priceChangeStatus
import wannabit.io.cosmostaion.common.priceChangeStatusColor
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemBabylonCoinBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BabylonCoinViewHolder(
    private val binding: ItemBabylonCoinBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(context: Context, chain: BaseChain, listener: CoinAdapter.ClickListener) {
        binding.apply {
            stakeCoinView.setBackgroundResource(R.drawable.item_bg)
            btcStakedStatusLayout.setOnClickListener {
                listener.btcStatus()
            }

            chain.stakeDenom.let { stakeDenom ->
                BaseData.getAsset(chain.apiName, stakeDenom)?.let { asset ->
                    tokenImg.setTokenImg(asset)
                    tokenName.text = asset.symbol

                    tokenPrice.text = formatAssetValue(BaseData.getPrice(asset.coinGeckoId))
                    BaseData.lastUpDown(asset.coinGeckoId).let { lastUpDown ->
                        tokenPriceChange.priceChangeStatusColor(lastUpDown)
                        tokenPriceChange.text = priceChangeStatus(lastUpDown)
                    }

                    val availableAmount = chain.cosmosFetcher?.balanceAmount(stakeDenom)
                        ?.movePointLeft(asset.decimals ?: 6)?.setScale(6, RoundingMode.DOWN)
                        ?: BigDecimal.ZERO

                    val vestingAmount = chain.cosmosFetcher?.vestingAmount(stakeDenom)
                        ?.movePointLeft(asset.decimals ?: 6)?.setScale(6, RoundingMode.DOWN)
                        ?: BigDecimal.ZERO

                    val stakedAmount = chain.cosmosFetcher?.delegationAmountSum()
                        ?.movePointLeft(asset.decimals ?: 6)?.setScale(6, RoundingMode.DOWN)
                        ?: BigDecimal.ZERO

                    val unStakingAmount = chain.cosmosFetcher?.unbondingAmountSum()
                        ?.movePointLeft(asset.decimals ?: 6)?.setScale(6, RoundingMode.DOWN)
                        ?: BigDecimal.ZERO

                    val rewardAmount = chain.cosmosFetcher?.rewardAmountSum(stakeDenom)
                        ?.movePointLeft(asset.decimals ?: 6)?.setScale(6, RoundingMode.DOWN)
                        ?: BigDecimal.ZERO

                    val btcRewardAmount =
                        (chain as ChainBabylonTestnet).babylonFetcher?.btcRewardAmountSum(stakeDenom)
                            ?.movePointLeft(
                                asset.decimals ?: 6
                            )?.setScale(6, RoundingMode.DOWN) ?: BigDecimal.ZERO

                    vestingLayout.goneOrVisible(vestingAmount?.compareTo(BigDecimal.ZERO) == 0)

                    if (chain.cosmosFetcher?.rewardAllCoins()?.isNotEmpty() == true) {
                        rewardTitle.text =
                            context.getString(R.string.str_reward) + if (chain.cosmosFetcher?.rewardOtherDenoms()!! > 0) " +${chain.cosmosFetcher?.rewardOtherDenoms()}" else ""
                    }

                    if (chain.babylonFetcher?.btcRewards?.isNotEmpty() == true) {
                        btcRewardTitle.text =
                            context.getString(R.string.str_btc_reward) + if ((chain.babylonFetcher?.btcRewardOtherDenoms()
                                    ?: 0) > 0
                            ) " +${chain.babylonFetcher?.btcRewardOtherDenoms()}" else ""
                    }

                    with(Prefs) {
                        total.visibility = if (hideValue) View.GONE else View.VISIBLE
                        totalValue.visibility = if (hideValue) View.GONE else View.VISIBLE
                        hidingValue.visibility = if (hideValue) View.VISIBLE else View.GONE

                        available.hiddenStatus(
                            formatAmount(
                                availableAmount.toPlainString(), 6
                            )
                        )
                        vesting.hiddenStatus(
                            formatAmount(
                                vestingAmount.toPlainString(), 6
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
                        btcReward.hiddenStatus(
                            formatAmount(
                                btcRewardAmount.toPlainString(), 6
                            )
                        )

                        total.text = if (hideValue) "" else formatAmount(
                            (availableAmount + vestingAmount + stakedAmount + unStakingAmount + rewardAmount + btcRewardAmount).toPlainString(),
                            6
                        )
                        totalValue.text = if (hideValue) {
                            ""
                        } else {
                            formatAssetValue(
                                chain.cosmosFetcher?.denomValue(stakeDenom) ?: BigDecimal.ZERO
                            )
                        }
                    }
                }
            }
        }
    }
}