package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.OktFetcher
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.cosmosClass.OKT_GECKO_ID
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.common.hiddenStatus
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

    fun bind(context: Context, chain: BaseChain) {
        when (chain) {
            is ChainOktEvm -> {
                bindOkt(chain)
            }

            is ChainNeutron -> {
                bindNeutron(chain)
            }

            else -> {
                binding.apply {
                    stakeCoinView.setBackgroundResource(R.drawable.item_bg)

                    chain.stakeDenom.let { stakeDenom ->
                        BaseData.getAsset(chain.apiName, stakeDenom)?.let { asset ->
                            tokenImg.setTokenImg(asset)
                            tokenName.text = asset.symbol?.uppercase()

                            tokenPrice.text = formatAssetValue(BaseData.getPrice(asset.coinGeckoId))
                            BaseData.lastUpDown(asset.coinGeckoId).let { lastUpDown ->
                                tokenPriceChange.priceChangeStatusColor(lastUpDown)
                                tokenPriceChange.text = priceChangeStatus(lastUpDown)
                            }

                            asset.decimals?.let { decimal ->
                                val availableAmount = chain.cosmosFetcher?.balanceAmount(stakeDenom)
                                    ?.movePointLeft(decimal)?.setScale(6, RoundingMode.DOWN)
                                    ?: BigDecimal.ZERO
                                val vestingAmount = chain.cosmosFetcher?.vestingAmount(stakeDenom)
                                    ?.movePointLeft(decimal)?.setScale(6, RoundingMode.DOWN)
                                    ?: BigDecimal.ZERO
                                val stakedAmount =
                                    chain.cosmosFetcher?.delegationAmountSum()
                                        ?.movePointLeft(decimal)
                                        ?.setScale(6, RoundingMode.DOWN) ?: BigDecimal.ZERO
                                val unStakingAmount =
                                    chain.cosmosFetcher?.unbondingAmountSum()
                                        ?.movePointLeft(decimal)
                                        ?.setScale(6, RoundingMode.DOWN) ?: BigDecimal.ZERO
                                val rewardAmount = chain.cosmosFetcher?.rewardAmountSum(stakeDenom)
                                    ?.movePointLeft(decimal)?.setScale(6, RoundingMode.DOWN)
                                    ?: BigDecimal.ZERO

                                vestingLayout.goneOrVisible(vestingAmount?.compareTo(BigDecimal.ZERO) == 0)
                                unstakingLayout.goneOrVisible(unStakingAmount?.compareTo(BigDecimal.ZERO) == 0)
                                rewardLayout.visibleOrGone(
                                    chain.cosmosFetcher?.rewardAllCoins()?.isNotEmpty() == true
                                )

                                if (chain.cosmosFetcher?.rewardAllCoins()?.isNotEmpty() == true) {
                                    rewardTitle.text =
                                        context.getString(R.string.str_reward) + if (chain.cosmosFetcher?.rewardOtherDenoms()!! > 0) " +${chain.cosmosFetcher?.rewardOtherDenoms()}" else ""
                                }

                                with(Prefs) {
                                    total.visibility = if (hideValue) View.GONE else View.VISIBLE
                                    totalValue.visibility =
                                        if (hideValue) View.GONE else View.VISIBLE
                                    hidingValue.visibility =
                                        if (hideValue) View.VISIBLE else View.GONE

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
                                    totalValue.text = if (hideValue) "" else formatAssetValue(
                                        chain.cosmosFetcher?.denomValue(stakeDenom)
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

    private fun bindNeutron(chain: ChainNeutron) {
        binding.apply {
            stakeCoinView.setBackgroundResource(R.drawable.item_bg)
            unstakingLayout.visibility = View.GONE
            rewardLayout.visibility = View.GONE

            stakedTitle.text = "Vault deposited"
            BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
                tokenImg.setTokenImg(asset)
                tokenName.text = asset.symbol?.uppercase()

                tokenPrice.text = formatAssetValue(BaseData.getPrice(asset.coinGeckoId))
                BaseData.lastUpDown(asset.coinGeckoId).let { lastUpDown ->
                    tokenPriceChange.priceChangeStatusColor(lastUpDown)
                    tokenPriceChange.text = priceChangeStatus(lastUpDown)
                }

                chain.neutronFetcher()?.let { neutronFetcher ->
                    val availableAmount = chain.cosmosFetcher?.balanceAmount(chain.stakeDenom)
                        ?.movePointLeft(asset.decimals ?: 6)?.setScale(6, RoundingMode.DOWN)
                    neutronFetcher.neutronVestingAmount()?.let { neutronVestingAmount ->
                        val vestingAmount = neutronVestingAmount.movePointLeft(asset.decimals ?: 6)
                            .setScale(6, RoundingMode.DOWN)
                        vestingLayout.goneOrVisible(vestingAmount.compareTo(BigDecimal.ZERO) == 0)

                        val depositedAmount =
                            neutronFetcher.neutronDeposited.movePointLeft(asset.decimals ?: 6)
                                .setScale(6, RoundingMode.DOWN)

                        val totalAmount = availableAmount?.add(vestingAmount)?.add(depositedAmount)
                        val value = neutronFetcher.denomValue(chain.stakeDenom)

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

            tokenPrice.text = formatAssetValue(BaseData.getPrice(OKT_GECKO_ID))
            BaseData.lastUpDown(OKT_GECKO_ID).let { lastUpDown ->
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
                totalValue.text =
                    if (hideValue) "" else formatAssetValue(
                        oktFetcher?.allAssetValue(false) ?: BigDecimal.ZERO
                    )
            }
        }
    }
}