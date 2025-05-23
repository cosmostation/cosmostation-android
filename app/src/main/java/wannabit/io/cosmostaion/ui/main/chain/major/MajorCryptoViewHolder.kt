package wannabit.io.cosmostaion.ui.main.chain.major

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.majorClass.ChainIota
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.majorClass.IOTA_MAIN_DENOM
import wannabit.io.cosmostaion.chain.majorClass.SUI_MAIN_DENOM
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.hiddenStatus
import wannabit.io.cosmostaion.common.hiddenStatusWithSui
import wannabit.io.cosmostaion.common.priceChangeStatus
import wannabit.io.cosmostaion.common.priceChangeStatusColor
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemMajorCryptoBinding
import java.math.BigDecimal
import java.math.RoundingMode

class MajorCryptoViewHolder(
    private val binding: ItemMajorCryptoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bitcoinBind(chain: ChainBitCoin86) {
        binding.apply {
            stakeCoinView.setBackgroundResource(R.drawable.item_bg)
            pendingLayout.visibility = View.VISIBLE
            stakedLayout.visibility = View.GONE
            earnedLayout.visibility = View.GONE

            if (chain.accountKeyType.pubkeyType == PubKeyType.BTC_LEGACY || chain.accountKeyType.pubkeyType == PubKeyType.BTC_NESTED_SEGWIT) {
                totalStakedLayout.visibility = View.GONE
                unstakingLayout.visibility = View.GONE
                withdrawableLayout.visibility = View.GONE

            } else {
                totalStakedLayout.visibility = View.VISIBLE
                unstakingLayout.visibility = View.VISIBLE
                withdrawableLayout.visibility = View.VISIBLE
            }

            BaseData.getAssetWithSymbol(chain.apiName, chain.coinSymbol)?.let { asset ->
                tokenImg.setTokenImg(asset)
                tokenName.text = asset.symbol
                tokenPrice.text = formatAssetValue(BaseData.getPrice(asset.coinGeckoId))

                BaseData.lastUpDown(asset.coinGeckoId).let { lastUpDown ->
                    tokenPriceChange.priceChangeStatusColor(lastUpDown)
                    tokenPriceChange.text = priceChangeStatus(lastUpDown)
                }

                chain.btcFetcher()?.let { fetcher ->
                    val price = BaseData.getPrice(asset.coinGeckoId)

                    val availableAmount =
                        fetcher.btcBalances.movePointLeft(8).setScale(8, RoundingMode.DOWN)
                    val stakedAmount =
                        fetcher.btcStakingAmount().movePointLeft(8).setScale(8, RoundingMode.DOWN)
                    val unStakedAmount =
                        fetcher.btcUnStakingAmount().movePointLeft(8).setScale(8, RoundingMode.DOWN)
                    val withdrawAbleAmount = fetcher.btcWithdrawAbleAmount().movePointLeft(8)
                        .setScale(8, RoundingMode.DOWN)
                    val pendingInputAmount =
                        fetcher.btcPendingInput.movePointLeft(8).setScale(8, RoundingMode.DOWN)

                    val totalAmount = availableAmount.add(stakedAmount).add(unStakedAmount)
                        .add(withdrawAbleAmount).add(pendingInputAmount)
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
                        totalStaked.hiddenStatus(
                            formatAmount(
                                stakedAmount.toPlainString(), 6
                            )
                        )
                        unstaking.hiddenStatus(
                            formatAmount(
                                unStakedAmount.toPlainString(), 6
                            )
                        )
                        withdrawable.hiddenStatus(
                            formatAmount(
                                withdrawAbleAmount.toPlainString(), 6
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
    }

    fun bind(chain: BaseChain) {
        binding.apply {
            stakeCoinView.setBackgroundResource(R.drawable.item_bg)
            pendingLayout.visibility = View.GONE
            unstakingLayout.visibility = View.GONE
            withdrawableLayout.visibility = View.GONE
            totalStakedLayout.visibility = View.VISIBLE
            stakedLayout.visibility = View.VISIBLE
            earnedLayout.visibility = View.VISIBLE

            BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
                tokenImg.setTokenImg(asset)
                tokenName.text = asset.symbol

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

    fun iotaBind(chain: BaseChain) {
        binding.apply {
            stakeCoinView.setBackgroundResource(R.drawable.item_bg)
            pendingLayout.visibility = View.GONE
            unstakingLayout.visibility = View.GONE
            withdrawableLayout.visibility = View.GONE
            totalStakedLayout.visibility = View.VISIBLE
            stakedLayout.visibility = View.VISIBLE
            earnedLayout.visibility = View.VISIBLE

            BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
                tokenImg.setTokenImg(asset)
                tokenName.text = asset.symbol

                tokenPrice.text = formatAssetValue(BaseData.getPrice(asset.coinGeckoId))
                BaseData.lastUpDown(asset.coinGeckoId).let { lastUpDown ->
                    tokenPriceChange.priceChangeStatusColor(lastUpDown)
                    tokenPriceChange.text = priceChangeStatus(lastUpDown)
                }

                (chain as ChainIota).iotaFetcher()?.let { fetcher ->
                    val allIota = fetcher.allIotaAmount()?.movePointLeft(asset.decimals ?: 6)
                        ?.setScale(6, RoundingMode.DOWN) ?: BigDecimal.ZERO

                    val availableAmount = fetcher.iotaBalanceAmount(IOTA_MAIN_DENOM)
                        ?.movePointLeft(asset.decimals ?: 6)?.setScale(6, RoundingMode.DOWN)
                        ?: BigDecimal.ZERO

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
                            allIota.toPlainString(), 6
                        )
                        totalValue.text =
                            if (hideValue) "" else formatAssetValue(fetcher.allIotaValue())

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
}