package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosLine
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
//            is ChainOkt996Keccak, is ChainOktEvm -> {
//                bindOkt(line)
//            }
//
//            is ChainNeutron -> {
//                bindNeutron(line)
//            }

            else -> {
                binding.apply {
                    stakeCoinView.setBackgroundResource(R.drawable.item_bg)

                    chain.stakeDenom?.let { stakeDenom ->
                        BaseData.getAsset(chain.apiName, stakeDenom)?.let { asset ->
                            tokenImg.setTokenImg(asset)
                            tokenName.text = asset.symbol?.uppercase()

                            tokenPrice.text = formatAssetValue(BaseData.getPrice(asset.coinGeckoId))
                            BaseData.lastUpDown(asset.coinGeckoId).let { lastUpDown ->
                                tokenPriceChange.priceChangeStatusColor(lastUpDown)
                                tokenPriceChange.text = priceChangeStatus(lastUpDown)
                            }

                            asset.decimals?.let { decimal ->
                                val availableAmount = chain.grpcFetcher.balanceAmount(stakeDenom)
                                    .movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
                                val vestingAmount = chain.grpcFetcher.vestingAmount(stakeDenom)
                                    .movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
                                val stakedAmount =
                                    chain.grpcFetcher.delegationAmountSum().movePointLeft(decimal)
                                        .setScale(6, RoundingMode.DOWN)
                                val unStakingAmount =
                                    chain.grpcFetcher.unbondingAmountSum().movePointLeft(decimal)
                                        .setScale(6, RoundingMode.DOWN)
                                val rewardAmount = chain.grpcFetcher.rewardAmountSum(stakeDenom)
                                    .movePointLeft(decimal).setScale(6, RoundingMode.DOWN)

                                vestingLayout.goneOrVisible(vestingAmount.compareTo(BigDecimal.ZERO) == 0)
                                unstakingLayout.goneOrVisible(unStakingAmount.compareTo(BigDecimal.ZERO) == 0)
                                rewardLayout.visibleOrGone(
                                    chain.grpcFetcher.rewardAllCoins().isNotEmpty()
                                )

                                if (chain.grpcFetcher.rewardAllCoins().isNotEmpty()) {
                                    rewardTitle.text =
                                        context.getString(R.string.str_reward) + if (chain.grpcFetcher.rewardOtherDenoms() > 0) " +${chain.grpcFetcher.rewardOtherDenoms()}" else ""
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
                                        chain.grpcFetcher.denomValue(stakeDenom)
                                    )
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

//            val neutronChain = line as? ChainNeutron
//            neutronChain?.let { chain ->
//                stakedTitle.text = "Vault deposited"
//                chain.stakeDenom?.let { denom ->
//                    BaseData.getAsset(chain.apiName, denom)?.let { asset ->
//                        tokenImg.setTokenImg(asset)
//                        tokenName.text = asset.symbol?.uppercase()
//
//                        tokenPrice.text = formatAssetValue(BaseData.getPrice(asset.coinGeckoId))
//                        BaseData.lastUpDown(asset.coinGeckoId).let { lastUpDown ->
//                            tokenPriceChange.priceChangeStatusColor(lastUpDown)
//                            tokenPriceChange.text = priceChangeStatus(lastUpDown)
//                        }
//
//                        asset.decimals?.let { decimal ->
//                            val availableAmount = line.balanceAmount(denom).movePointLeft(decimal)
//                                .setScale(6, RoundingMode.DOWN)
//
//                            chain.neutronVestingAmount()?.let { neutronVestingAmount ->
//                                val vestingAmount = neutronVestingAmount.movePointLeft(decimal)
//                                    .setScale(6, RoundingMode.DOWN)
//                                vestingLayout.goneOrVisible(vestingAmount.compareTo(BigDecimal.ZERO) == 0)
//
//                                val depositedAmount =
//                                    chain.neutronDeposited.movePointLeft(decimal)
//                                        .setScale(6, RoundingMode.DOWN)
//
//                                val totalAmount =
//                                    availableAmount.add(vestingAmount).add(depositedAmount)
//                                val value = line.denomValue(denom)
//
//                                with(Prefs) {
//                                    total.visibility = if (hideValue) View.GONE else View.VISIBLE
//                                    totalValue.visibility =
//                                        if (hideValue) View.GONE else View.VISIBLE
//                                    hidingValue.visibility =
//                                        if (hideValue) View.VISIBLE else View.GONE
//
//                                    available.hiddenStatus(
//                                        formatAmount(
//                                            availableAmount.toPlainString(), 6
//                                        )
//                                    )
//                                    vesting.hiddenStatus(
//                                        formatAmount(
//                                            vestingAmount.toPlainString(), 6
//                                        )
//                                    )
//                                    staked.hiddenStatus(
//                                        formatAmount(
//                                            depositedAmount.toPlainString(), 6
//                                        )
//                                    )
//
//                                    total.text = if (hideValue) "" else formatAmount(
//                                        totalAmount.toPlainString(), 6
//                                    )
//                                    totalValue.text = if (hideValue) "" else formatAssetValue(value)
//                                }
//                            }
//                        }
//                    }
//                }
//            }
        }
    }

    private fun bindOkt(line: BaseChain) {
        binding.apply {
            vestingTitle.text = "Deposited"
            stakedTitle.text = "Withdrawing"
            stakeCoinView.setBackgroundResource(R.drawable.item_bg)
            unstakingLayout.visibility = View.GONE
            rewardLayout.visibility = View.GONE

//            if (line is ChainOkt996Keccak) {
//                line.stakeDenom?.let { stakeDenom ->
//                    tokenImg.setTokenImg(ChainOkt996Keccak().assetImg(stakeDenom))
//                    tokenName.text = stakeDenom.uppercase()
//
//                    tokenPrice.text = formatAssetValue(BaseData.getPrice(OKT_GECKO_ID))
//                    BaseData.lastUpDown(OKT_GECKO_ID).let { lastUpDown ->
//                        tokenPriceChange.priceChangeStatusColor(lastUpDown)
//                        tokenPriceChange.text = priceChangeStatus(lastUpDown)
//                    }
//
//                    val availableAmount = line.lcdBalanceAmount(stakeDenom)
//                    val depositAmount = line.lcdOktDepositAmount()
//                    val withdrawAmount = line.lcdOktWithdrawAmount()
//                    if (withdrawAmount > BigDecimal.ZERO) {
//                        stakedLayout.visibility = View.VISIBLE
//                    } else {
//                        stakedLayout.visibility = View.GONE
//                    }
//
//                    with(Prefs) {
//                        total.visibility = if (hideValue) View.GONE else View.VISIBLE
//                        totalValue.visibility = if (hideValue) View.GONE else View.VISIBLE
//                        hidingValue.visibility = if (hideValue) View.VISIBLE else View.GONE
//
//                        available.hiddenStatus(formatAmount(availableAmount.toPlainString(), 18))
//                        vesting.hiddenStatus(formatAmount(depositAmount.toPlainString(), 18))
//                        staked.hiddenStatus(formatAmount(withdrawAmount.toPlainString(), 18))
//
//                        total.text = if (hideValue) "" else formatAmount(
//                            (availableAmount + depositAmount + withdrawAmount).toPlainString(), 18
//                        )
//                        totalValue.text =
//                            if (hideValue) "" else formatAssetValue(line.allAssetValue(false))
//                    }
//                }
//
//            } else if (line is ChainOktEvm) {
//                line.stakeDenom?.let { stakeDenom ->
//                    tokenImg.setTokenImg(ChainOktEvm().assetImg(stakeDenom))
//                    tokenName.text = stakeDenom.uppercase()
//
//                    tokenPrice.text = formatAssetValue(BaseData.getPrice(OKT_GECKO_ID))
//                    BaseData.lastUpDown(OKT_GECKO_ID).let { lastUpDown ->
//                        tokenPriceChange.priceChangeStatusColor(lastUpDown)
//                        tokenPriceChange.text = priceChangeStatus(lastUpDown)
//                    }
//
//                    val availableAmount = line.lcdBalanceAmount(stakeDenom)
//                    val depositAmount = line.lcdOktDepositAmount()
//                    val withdrawAmount = line.lcdOktWithdrawAmount()
//                    if (withdrawAmount > BigDecimal.ZERO) {
//                        stakedLayout.visibility = View.VISIBLE
//                    } else {
//                        stakedLayout.visibility = View.GONE
//                    }
//
//                    with(Prefs) {
//                        total.visibility = if (hideValue) View.GONE else View.VISIBLE
//                        totalValue.visibility = if (hideValue) View.GONE else View.VISIBLE
//                        hidingValue.visibility = if (hideValue) View.VISIBLE else View.GONE
//
//                        available.hiddenStatus(formatAmount(availableAmount.toPlainString(), 18))
//                        vesting.hiddenStatus(formatAmount(depositAmount.toPlainString(), 18))
//                        staked.hiddenStatus(formatAmount(withdrawAmount.toPlainString(), 18))
//
//                        total.text = if (hideValue) "" else formatAmount(
//                            (availableAmount + depositAmount + withdrawAmount).toPlainString(), 18
//                        )
//                        totalValue.text =
//                            if (hideValue) "" else formatAssetValue(line.allAssetValue(false))
//                    }
//                }
//            }
        }
    }
}