package wannabit.io.cosmostaion.ui.main.chain.major

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin84
import wannabit.io.cosmostaion.chain.majorClass.ChainNamada
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.majorClass.SUI_MAIN_DENOM
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.common.hiddenStatus
import wannabit.io.cosmostaion.common.hiddenStatusWithSui
import wannabit.io.cosmostaion.common.priceChangeStatus
import wannabit.io.cosmostaion.common.priceChangeStatusColor
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.model.res.Coin
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemMajorCryptoBinding
import java.math.BigDecimal
import java.math.RoundingMode

class MajorCryptoViewHolder(
    private val binding: ItemMajorCryptoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bitcoinBind(chain: ChainBitCoin84) {
        binding.apply {
            stakeCoinView.setBackgroundResource(R.drawable.item_bg)
            pendingLayout.visibility = View.VISIBLE
            totalStakedLayout.visibility = View.GONE
            stakedLayout.visibility = View.GONE
            earnedLayout.visibility = View.GONE
            unstakedLayout.visibility = View.GONE
            reward.visibility = View.GONE

            tokenImg.setImageResource(chain.coinLogo)
            tokenName.text = chain.coinSymbol
            tokenPrice.text = formatAssetValue(BaseData.getPrice(chain.coinGeckoId))

            BaseData.lastUpDown(chain.coinGeckoId).let { lastUpDown ->
                tokenPriceChange.priceChangeStatusColor(lastUpDown)
                tokenPriceChange.text = priceChangeStatus(lastUpDown)
            }

            chain.btcFetcher()?.let { fetcher ->
                val price = BaseData.getPrice(chain.coinGeckoId)
                val availableAmount =
                    fetcher.btcBalances.movePointLeft(8).setScale(8, RoundingMode.DOWN)
                val pendingInputAmount =
                    fetcher.btcPendingInput.movePointLeft(8).setScale(8, RoundingMode.DOWN)
                val totalAmount = availableAmount.add(pendingInputAmount)
                val value = totalAmount.multiply(price).setScale(6, RoundingMode.DOWN)

                with(Prefs) {
                    total.visibility = if (hideValue) View.GONE else View.VISIBLE
                    totalValue.visibility = if (hideValue) View.GONE else View.VISIBLE
                    hidingValue.visibility = if (hideValue) View.VISIBLE else View.GONE

                    total.text = if (hideValue) "" else formatAmount(
                        totalAmount.toPlainString(), 6
                    )
                    totalValue.text = if (hideValue) "" else formatAssetValue(value)

                    available.hiddenStatus(
                        formatAmount(
                            availableAmount.toPlainString(), 6
                        )
                    )
                    pending.hiddenStatus(
                        formatAmount(
                            pendingInputAmount.toPlainString(), 6
                        )
                    )
                }
            }
        }
    }

    fun bind(chain: BaseChain) {
        binding.apply {
            stakeCoinView.setBackgroundResource(R.drawable.item_bg)
            pendingLayout.visibility = View.GONE
            totalStakedLayout.visibility = View.VISIBLE
            stakedLayout.visibility = View.VISIBLE
            earnedLayout.visibility = View.VISIBLE
            unstakedLayout.visibility = View.GONE
            reward.visibility = View.GONE

            BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
                tokenImg.setTokenImg(asset)
                tokenName.text = asset.symbol?.uppercase()

                tokenPrice.text = formatAssetValue(BaseData.getPrice(asset.coinGeckoId))
                BaseData.lastUpDown(asset.coinGeckoId).let { lastUpDown ->
                    tokenPriceChange.priceChangeStatusColor(lastUpDown)
                    tokenPriceChange.text = priceChangeStatus(lastUpDown)
                }

                (chain as ChainSui).suiFetcher()?.let { fetcher ->
                    val allSui = fetcher.allSuiAmount()?.movePointLeft(asset.decimals ?: 6)
                        ?.setScale(6, RoundingMode.DOWN) ?: BigDecimal.ZERO

                    val availableAmount =
                        fetcher.suiBalanceAmount(SUI_MAIN_DENOM)?.movePointLeft(asset.decimals ?: 6)
                            ?.setScale(6, RoundingMode.DOWN) ?: BigDecimal.ZERO

                    val stakedAmount = fetcher.stakedAmount().movePointLeft(asset.decimals ?: 6)
                        ?.setScale(6, RoundingMode.DOWN) ?: BigDecimal.ZERO

                    val principal = fetcher.principalAmount().movePointLeft(asset.decimals ?: 6)
                        ?.setScale(6, RoundingMode.DOWN) ?: BigDecimal.ZERO

                    val estimateReward =
                        fetcher.estimateRewardAmount().movePointLeft(asset.decimals ?: 6)
                            ?.setScale(6, RoundingMode.DOWN) ?: BigDecimal.ZERO

                    with(Prefs) {
                        total.visibility = if (hideValue) View.GONE else View.VISIBLE
                        totalValue.visibility = if (hideValue) View.GONE else View.VISIBLE
                        hidingValue.visibility = if (hideValue) View.VISIBLE else View.GONE

                        total.text = if (hideValue) "" else formatAmount(
                            allSui.toPlainString(), 6
                        )
                        totalValue.text =
                            if (hideValue) "" else formatAssetValue(fetcher.allSuiValue())

                        available.hiddenStatus(
                            formatAmount(
                                availableAmount.toPlainString(), 6
                            )
                        )

                        totalStaked.hiddenStatus(
                            formatAmount(
                                stakedAmount.toPlainString(), 6
                            )
                        )

                        staked.hiddenStatusWithSui(
                            formatAmount(
                                principal.toPlainString(), 6
                            )
                        )

                        earned.hiddenStatusWithSui(
                            formatAmount(
                                estimateReward.toPlainString(), 6
                            )
                        )
                    }
                }
            }
        }
    }

    fun namadaBind(chain: ChainNamada, coin: Coin) {
        binding.apply {
            stakeCoinView.setBackgroundResource(R.drawable.item_bg)
            listOf(pendingLayout, stakedLayout, earnedLayout).forEach {
                it.visibility = View.GONE
            }
            listOf(unstakedLayout).forEach {
                it.visibility = View.VISIBLE
            }

            BaseData.getAsset(chain.apiName, coin.denom)?.let { asset ->
                tokenImg.setTokenImg(asset)
                tokenName.text = asset.symbol?.uppercase()

                tokenPrice.text = formatAssetValue(BaseData.getPrice(asset.coinGeckoId))
                BaseData.lastUpDown(asset.coinGeckoId).let { lastUpDown ->
                    tokenPriceChange.priceChangeStatusColor(lastUpDown)
                    tokenPriceChange.text = priceChangeStatus(lastUpDown)
                }

                chain.namadaFetcher()?.let { fetcher ->
                    val balance =
                        fetcher.namadaBalanceAmount(coin.denom).movePointLeft(asset.decimals ?: 6)
                            ?.setScale(6, RoundingMode.DOWN) ?: BigDecimal.ZERO
                    val bonded = fetcher.namadaBondAmountSum().movePointLeft(asset.decimals ?: 6)
                        ?.setScale(6, RoundingMode.DOWN) ?: BigDecimal.ZERO
                    val unBonded =
                        fetcher.namadaUnBondAmountSum().movePointLeft(asset.decimals ?: 6)
                            ?.setScale(6, RoundingMode.DOWN) ?: BigDecimal.ZERO
                    val rewardAmount =
                        fetcher.namadaRewardAmountSum().movePointLeft(asset.decimals ?: 6)
                            ?.setScale(6, RoundingMode.DOWN) ?: BigDecimal.ZERO
                    val withdrawAmount =
                        fetcher.namadaWithdrawAmountSum().movePointLeft(asset.decimals ?: 6)
                            ?.setScale(6, RoundingMode.DOWN) ?: BigDecimal.ZERO

                    val totalAmount =
                        balance.add(bonded).add(unBonded).add(rewardAmount).add(withdrawAmount)

                    unstakedLayout.goneOrVisible(unBonded?.compareTo(BigDecimal.ZERO) == 0)
                    rewardLayout.visibleOrGone(
                        fetcher.namadaReward?.isNotEmpty() == true
                    )
                    withdrawLayout.goneOrVisible(withdrawAmount?.compareTo(BigDecimal.ZERO) == 0)

                    with(Prefs) {
                        total.visibility = if (hideValue) View.GONE else View.VISIBLE
                        totalValue.visibility = if (hideValue) View.GONE else View.VISIBLE
                        hidingValue.visibility = if (hideValue) View.VISIBLE else View.GONE

                        total.text = if (hideValue) "" else formatAmount(
                            totalAmount.toPlainString(), 6
                        )
                        totalValue.text = if (hideValue) "" else formatAssetValue(
                            fetcher.denomValue(
                                coin.denom, false
                            )
                        )

                        available.hiddenStatus(
                            formatAmount(
                                balance.toPlainString(), 6
                            )
                        )

                        totalStaked.hiddenStatus(
                            formatAmount(
                                bonded.toPlainString(), 6
                            )
                        )

                        unstaked.hiddenStatus(
                            formatAmount(
                                unBonded.toPlainString(), 6
                            )
                        )

                        reward.hiddenStatus(
                            formatAmount(
                                rewardAmount.toPlainString(), 6
                            )
                        )

                        withdraw.hiddenStatus(
                            formatAmount(
                                withdrawAmount.toPlainString(), 6
                            )
                        )
                    }
                }
            }
        }
    }
}