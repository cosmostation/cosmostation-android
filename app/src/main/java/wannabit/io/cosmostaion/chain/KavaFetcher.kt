package wannabit.io.cosmostaion.chain

import com.cosmos.base.v1beta1.CoinProto
import com.kava.cdp.v1beta1.GenesisProto
import com.kava.cdp.v1beta1.QueryProto.CDPResponse
import com.kava.hard.v1beta1.HardProto
import com.kava.hard.v1beta1.QueryProto
import com.kava.incentive.v1beta1.QueryProto.QueryRewardsResponse
import com.kava.swap.v1beta1.QueryProto.DepositResponse
import com.kava.swap.v1beta1.QueryProto.PoolResponse
import java.math.BigDecimal
import java.math.RoundingMode

class KavaFetcher(chain: BaseChain) : FetcherGrpc(chain) {

    data class LendingData(
        var lendingParam: HardProto.Params?,
        val lendingRates: MutableList<QueryProto.MoneyMarketInterestRate>?,
        val lendingTotalDeposits: MutableList<CoinProto.Coin>?,
        val lendingTotalBorrows: MutableList<CoinProto.Coin>?,
        val lendingMyDeposits: MutableList<QueryProto.DepositResponse>?,
        val lendingMyBorrows: MutableList<QueryProto.BorrowResponse>?,
        val lendingReserve: MutableList<CoinProto.Coin>?
    )

    data class SwapData(
        var swapPools: MutableList<PoolResponse>?,
        var swapMyDeposits: MutableList<DepositResponse>?
    )
}

fun QueryRewardsResponse.allIncentiveCoins(): MutableList<CoinProto.Coin> {
    val result: MutableList<CoinProto.Coin> = mutableListOf()

    usdxMintingClaimsList.forEach { claim ->
        if (claim.baseClaim.hasReward()) {
            val usdxReward = claim.baseClaim.reward
            val amount = usdxReward.amount.toBigDecimal()
            if (amount > BigDecimal.ZERO) {
                result.indexOfFirst { it.denom == usdxReward.denom }.let { already ->
                    if (already != -1) {
                        val sumReward = result[already].amount.toBigDecimal().add(amount)
                        result[already] = CoinProto.Coin.newBuilder().setDenom(usdxReward.denom)
                            .setAmount(sumReward.toPlainString()).build()
                    } else {
                        result.add(usdxReward)
                    }
                }
            }
        }
    }

    hardLiquidityProviderClaimsList.forEach { hardIncen ->
        hardIncen.baseClaim.rewardList.forEach { hardReward ->
            val amount = hardReward.amount.toBigDecimal()
            if (amount > BigDecimal.ZERO) {
                result.indexOfFirst { it.denom == hardReward.denom }.let { already ->
                    if (already != -1) {
                        val sumReward = result[already].amount.toBigDecimal().add(amount)
                        result[already] = CoinProto.Coin.newBuilder().setDenom(hardReward.denom)
                            .setAmount(sumReward.toPlainString()).build()
                    } else {
                        result.add(hardReward)
                    }
                }
            }
        }
    }

    delegatorClaimsList.forEach { claim ->
        claim.baseClaim.rewardList.forEach { deleClaim ->
            val amount = deleClaim.amount.toBigDecimal()
            if (amount > BigDecimal.ZERO) {
                result.indexOfFirst { it.denom == deleClaim.denom }.let { already ->
                    if (already != -1) {
                        val sumReward = result[already].amount.toBigDecimal().add(amount)
                        result[already] = CoinProto.Coin.newBuilder().setDenom(deleClaim.denom)
                            .setAmount(sumReward.toPlainString()).build()
                    } else {
                        result.add(deleClaim)
                    }
                }
            }
        }
    }

    swapClaimsList.forEach { claim ->
        claim.baseClaim.rewardList.forEach { swapClaim ->
            val amount = swapClaim.amount.toBigDecimal()
            if (amount > BigDecimal.ZERO) {
                result.indexOfFirst { it.denom == swapClaim.denom }.let { already ->
                    if (already != -1) {
                        val sumReward = result[already].amount.toBigDecimal().add(amount)
                        result[already] = CoinProto.Coin.newBuilder().setDenom(swapClaim.denom)
                            .setAmount(sumReward.toPlainString()).build()
                    } else {
                        result.add(swapClaim)
                    }
                }
            }
        }
    }

    earnClaimsList.forEach { claim ->
        claim.baseClaim.rewardList.forEach { earnClaim ->
            val amount = earnClaim.amount.toBigDecimal()
            if (amount > BigDecimal.ZERO) {
                result.indexOfFirst { it.denom == earnClaim.denom }.let { already ->
                    if (already != -1) {
                        val sumReward = result[already].amount.toBigDecimal().add(amount)
                        result[already] = CoinProto.Coin.newBuilder().setDenom(earnClaim.denom)
                            .setAmount(sumReward.toPlainString()).build()
                    } else {
                        result.add(earnClaim)
                    }
                }
            }
        }
    }
    return result
}

fun QueryRewardsResponse.hasUsdxMinting(): Boolean {
    if (usdxMintingClaimsCount > 0 && usdxMintingClaimsList[0].hasBaseClaim() && usdxMintingClaimsList[0].baseClaim.hasReward() && usdxMintingClaimsList[0].baseClaim.reward.amount != "0") {
        return true
    }
    return false
}

fun QueryRewardsResponse.hardRewardDenoms(): MutableList<String> {
    val result = mutableListOf<String>()
    hardLiquidityProviderClaimsList.forEach { hardClaim ->
        hardClaim.baseClaim.rewardList.forEach { coin ->
            if (!result.contains(coin.denom)) {
                result.add(coin.denom)
            }
        }
    }
    return result
}

fun QueryRewardsResponse.delegatorRewardDenoms(): MutableList<String> {
    val result = mutableListOf<String>()
    delegatorClaimsList.forEach { delegClaim ->
        delegClaim.baseClaim.rewardList.forEach { coin ->
            if (!result.contains(coin.denom)) {
                result.add(coin.denom)
            }
        }
    }
    return result
}

fun QueryRewardsResponse.swapRewardDenoms(): MutableList<String> {
    val result = mutableListOf<String>()
    swapClaimsList.forEach { swapClaim ->
        swapClaim.baseClaim.rewardList.forEach { coin ->
            if (!result.contains(coin.denom)) {
                result.add(coin.denom)
            }
        }
    }
    return result
}

fun QueryRewardsResponse.earnRewardDenoms(): MutableList<String> {
    val result = mutableListOf<String>()
    earnClaimsList.forEach { earnClaim ->
        earnClaim.baseClaim.rewardList.forEach { coin ->
            if (!result.contains(coin.denom)) {
                result.add(coin.denom)
            }
        }
    }
    return result
}

fun GenesisProto.CollateralParam.liquidationRatioAmount(): BigDecimal {
    return liquidationRatio.toBigDecimal().movePointLeft(18).setScale(18, RoundingMode.DOWN)
}

fun GenesisProto.CollateralParam.expectCollateralUSDXValue(
    collateralAmount: BigDecimal?,
    priceFeed: com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse?
): BigDecimal {
    val collateralPrice = priceFeed?.kavaOraclePrice(liquidationMarketId)
    val collateralValue =
        collateralAmount?.multiply(collateralPrice)?.movePointLeft(conversionFactor.toInt())
            ?.setScale(6, RoundingMode.DOWN)
    return collateralValue?.movePointRight(6)?.setScale(0, RoundingMode.DOWN) ?: BigDecimal.ZERO
}

fun GenesisProto.CollateralParam.expectUSDXLTV(
    collateralAmount: BigDecimal?,
    priceFeed: com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse?
): BigDecimal {
    return expectCollateralUSDXValue(collateralAmount, priceFeed).divide(
        liquidationRatioAmount(), 0, RoundingMode.DOWN
    )
}

fun CDPResponse.collateralUSDXAmount(): BigDecimal {
    return collateralValue.amount.toBigDecimal().movePointLeft(6).setScale(6, RoundingMode.DOWN)
}

fun CDPResponse.UsdxLTV(collateralParam: GenesisProto.CollateralParam): BigDecimal {
    return collateralUSDXAmount().divide(
        collateralParam.liquidationRatioAmount(), 6, RoundingMode.DOWN
    )
}

fun CDPResponse.principalAmount(): BigDecimal {
    return principal.amount.toBigDecimal()
}

fun CDPResponse.debtAmount(): BigDecimal {
    return principalAmount().add(accumulatedFees.amount.toBigDecimal())
}

fun CDPResponse.debtUsdxValue(): BigDecimal {
    return debtAmount().movePointLeft(6).setScale(6, RoundingMode.DOWN)
}

fun CDPResponse.liquidationPrice(collateralParam: GenesisProto.CollateralParam): BigDecimal {
    val cDenomDecimal = collateralParam.conversionFactor.toInt()
    val collateralAmount = collateral.amount.toBigDecimal().movePointLeft(cDenomDecimal)
        .setScale(cDenomDecimal, RoundingMode.DOWN)
    val rawDebtAmount =
        debtAmount().multiply(collateralParam.liquidationRatioAmount()).movePointLeft(6)
            .setScale(6, RoundingMode.DOWN)
    return rawDebtAmount.divide(collateralAmount, 6, RoundingMode.DOWN)
}

fun com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse.kavaOraclePrice(marketId: String): BigDecimal {
    pricesList.firstOrNull { it.marketId == marketId }?.let { price ->
        return price.price.toBigDecimal().movePointLeft(18).setScale(6, RoundingMode.DOWN)
    } ?: run {
        return BigDecimal.ZERO
    }
}

fun MutableList<HardProto.MoneyMarket>.hardMoneyMarket(denom: String?): HardProto.MoneyMarket? {
    return firstOrNull { it.denom == denom }
}

fun MutableList<HardProto.MoneyMarket>.getLTV(denom: String?): BigDecimal {
    firstOrNull { it.denom == denom }?.let { market ->
        return market.borrowLimit.loanToValue.toBigDecimal().movePointLeft(18)
    } ?: run {
        return BigDecimal.ZERO
    }
}

fun MutableList<HardProto.MoneyMarket>.spotMarketId(denom: String?): String {
    firstOrNull { it.denom == denom }?.let { market ->
        return market.spotMarketId
    } ?: run {
        return ""
    }
}

fun DepositResponse.usdxAmount(): BigDecimal {
    return sharesValueList.firstOrNull { it.denom == "usdx" }?.amount?.toBigDecimal()
        ?: BigDecimal.ZERO
}

fun PoolResponse.usdxAmount(): BigDecimal {
    return coinsList.firstOrNull { it.denom == "usdx" }?.amount?.toBigDecimal() ?: BigDecimal.ZERO
}