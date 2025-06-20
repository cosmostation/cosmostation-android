package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCoreum
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.chain.fetcher.OktFetcher
import wannabit.io.cosmostaion.chain.testnetClass.ChainGnoTestnet
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.common.hiddenStatus
import wannabit.io.cosmostaion.common.priceChangeStatus
import wannabit.io.cosmostaion.common.priceChangeStatusColor
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemCosmosLineCoinBinding
import java.math.BigDecimal
import java.math.RoundingMode

class CoinCosmosLineViewHolder(
    private val binding: ItemCosmosLineCoinBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(context: Context, chain: BaseChain) {
        when (chain) {
            is ChainOktEvm -> {
                bindOkt(chain)
            }

            else -> {
                binding.apply {
                    stakeCoinView.setBackgroundResource(R.drawable.item_bg)

                    BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
                        tokenImg.setTokenImg(asset)
                        tokenName.text = asset.symbol

                        tokenPrice.text = formatAssetValue(BaseData.getPrice(asset.coinGeckoId))
                        BaseData.lastUpDown(asset.coinGeckoId).let { lastUpDown ->
                            tokenPriceChange.priceChangeStatusColor(lastUpDown)
                            tokenPriceChange.text = priceChangeStatus(lastUpDown)
                        }

                        val availableAmount = if (chain is ChainGnoTestnet) {
                            chain.gnoRpcFetcher?.balanceAmount(chain.stakeDenom)
                                ?.movePointLeft(asset.decimals ?: 6)
                                ?.setScale(6, RoundingMode.DOWN) ?: BigDecimal.ZERO
                        } else {
                            chain.cosmosFetcher?.availableAmount(chain.stakeDenom)
                                ?.movePointLeft(asset.decimals ?: 6)
                                ?.setScale(6, RoundingMode.DOWN) ?: BigDecimal.ZERO
                        }
                        val vestingAmount = chain.cosmosFetcher?.vestingAmount(chain.stakeDenom)
                            ?.movePointLeft(asset.decimals ?: 6)?.setScale(6, RoundingMode.DOWN)
                            ?: BigDecimal.ZERO
                        val stakedAmount = chain.cosmosFetcher?.delegationAmountSum()
                            ?.movePointLeft(asset.decimals ?: 6)?.setScale(6, RoundingMode.DOWN)
                            ?: BigDecimal.ZERO
                        val unStakingAmount = chain.cosmosFetcher?.unbondingAmountSum()
                            ?.movePointLeft(asset.decimals ?: 6)?.setScale(6, RoundingMode.DOWN)
                            ?: BigDecimal.ZERO
                        val rewardAmount = chain.cosmosFetcher?.rewardAmountSum(chain.stakeDenom)
                            ?.movePointLeft(asset.decimals ?: 6)?.setScale(6, RoundingMode.DOWN)
                            ?: BigDecimal.ZERO

                        vestingLayout.goneOrVisible(vestingAmount?.compareTo(BigDecimal.ZERO) == 0)
                        vestingTitle.text = if (chain is ChainCoreum) {
                            context.getString(R.string.str_locked)
                        } else {
                            context.getString(R.string.str_vesting)
                        }

                        if (chain.cosmosFetcher?.rewardAllCoins()?.isNotEmpty() == true) {
                            rewardTitle.text =
                                context.getString(R.string.str_reward) + if (chain.cosmosFetcher?.rewardOtherDenoms()!! > 0) " +${chain.cosmosFetcher?.rewardOtherDenoms()}" else ""
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

                            total.text = if (hideValue) "" else formatAmount(
                                (availableAmount + vestingAmount + stakedAmount + unStakingAmount + rewardAmount).toPlainString(),
                                6
                            )
                            totalValue.text = if (hideValue) {
                                ""
                            } else {
                                if (chain is ChainGnoTestnet) {
                                    formatAssetValue(
                                        chain.gnoRpcFetcher?.denomValue(chain.stakeDenom)
                                            ?: BigDecimal.ZERO
                                    )
                                } else {
                                    formatAssetValue(
                                        chain.cosmosFetcher?.denomValue(chain.stakeDenom)
                                            ?: BigDecimal.ZERO
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun bindOkt(chain: BaseChain) {
        binding.apply {
            vestingTitle.text = "Deposited"
            stakedTitle.text = "Withdrawing"
            stakeCoinView.setBackgroundResource(R.drawable.item_bg)
            unstakingLayout.visibility = View.GONE
            rewardLayout.visibility = View.GONE

            when (chain) {
                is ChainOkt996Keccak -> updateTokenInfo(chain, chain.oktFetcher)
                is ChainOktEvm -> updateTokenInfo(chain, chain.oktFetcher)
            }
        }
    }

    private fun updateTokenInfo(chain: BaseChain, oktFetcher: OktFetcher?) {
        binding.apply {
            tokenImg.setTokenImg(chain.assetImg(chain.stakeDenom))
            tokenName.text = chain.stakeDenom.uppercase()

            val coinGeckoId = BaseData.getAsset(chain.apiName, chain.stakeDenom)?.coinGeckoId
            tokenPrice.text = formatAssetValue(BaseData.getPrice(coinGeckoId))
            BaseData.lastUpDown(coinGeckoId).let { lastUpDown ->
                tokenPriceChange.priceChangeStatusColor(lastUpDown)
                tokenPriceChange.text = priceChangeStatus(lastUpDown)
            }

            val availableAmount = oktFetcher?.oktBalanceAmount(chain.stakeDenom) ?: BigDecimal.ZERO
            val depositAmount = oktFetcher?.oktDepositAmount() ?: BigDecimal.ZERO
            val withdrawAmount = oktFetcher?.oktWithdrawAmount() ?: BigDecimal.ZERO
            if (BigDecimal.ZERO < withdrawAmount) {
                stakedLayout.visibility = View.VISIBLE
            } else {
                stakedLayout.visibility = View.GONE
            }

            with(Prefs) {
                total.visibility = if (hideValue) View.GONE else View.VISIBLE
                totalValue.visibility = if (hideValue) View.GONE else View.VISIBLE
                hidingValue.visibility = if (hideValue) View.VISIBLE else View.GONE

                available.hiddenStatus(formatAmount(availableAmount.toString(), 18))
                vesting.hiddenStatus(formatAmount(depositAmount.toPlainString(), 18))
                staked.hiddenStatus(formatAmount(withdrawAmount.toPlainString(), 18))

                total.text = if (hideValue) "" else formatAmount(
                    (availableAmount + depositAmount + withdrawAmount).toPlainString(), 18
                )
                totalValue.text = if (hideValue) "" else formatAssetValue(
                    oktFetcher?.allAssetValue(false) ?: BigDecimal.ZERO
                )
            }
        }
    }
}