package wannabit.io.cosmostaion.chain

import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.distribution.v1beta1.DistributionProto
import com.cosmos.staking.v1beta1.StakingProto
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.data.model.Cw721Model
import wannabit.io.cosmostaion.data.model.res.AccountResponse
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.database.Prefs
import java.math.BigDecimal
import java.math.RoundingMode

class FetcherGrpc(chain: BaseChain) {

    var chain: BaseChain

    var cosmosAuth: com.google.protobuf.Any? = null
    var cosmosValidators = mutableListOf<StakingProto.Validator>()
    var cosmosBalances: MutableList<CoinProto.Coin>? = null
    var cosmosVestings = mutableListOf<CoinProto.Coin>()
    var cosmosDelegations = mutableListOf<StakingProto.DelegationResponse>()
    var cosmosUnbondings = mutableListOf<StakingProto.UnbondingDelegation>()
    var cosmosRewards = mutableListOf<DistributionProto.DelegationDelegatorReward>()

    var tokens = mutableListOf<Token>()

    var lcdAccountInfo: AccountResponse? = null

    var cw721s = mutableListOf<JsonObject>()
    var cw721Fetched = false
    var cw721Models = mutableListOf<Cw721Model>()

    init {
        this.chain = chain
    }

    fun balanceAmount(denom: String): BigDecimal {
        if (cosmosBalances?.isNotEmpty() == true) {
            return cosmosBalances?.firstOrNull { it.denom == denom }?.amount?.toBigDecimal()
                ?: BigDecimal.ZERO
        }
        return BigDecimal.ZERO
    }

    fun balanceValue(denom: String, isUsd: Boolean? = false): BigDecimal {
        BaseData.getAsset(chain.apiName, denom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            val amount = balanceAmount(denom)
            asset.decimals?.let { decimal ->
                return price.multiply(amount).movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
            } ?: run {
                return BigDecimal.ZERO
            }
        }
        return BigDecimal.ZERO
    }

    fun balanceValueSum(isUsd: Boolean? = false): BigDecimal {
        var sum = BigDecimal.ZERO
        if (cosmosBalances?.isNotEmpty() == true) {
            cosmosBalances?.forEach { balance ->
                sum = sum.add(balanceValue(balance.denom, isUsd))
            }
        }
        return sum
    }

    fun vestingAmount(denom: String): BigDecimal {
        if (cosmosVestings.isNotEmpty()) {
            return cosmosVestings.firstOrNull { it.denom == denom }?.amount?.toBigDecimal()
                ?: BigDecimal.ZERO
        }
        return BigDecimal.ZERO
    }

    private fun vestingValue(denom: String, isUsd: Boolean? = false): BigDecimal {
        BaseData.getAsset(chain.apiName, denom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            val amount = vestingAmount(denom)
            asset.decimals?.let { decimal ->
                return price.multiply(amount).movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
            }
        }
        return BigDecimal.ZERO
    }

    private fun vestingValueSum(isUsd: Boolean? = false): BigDecimal {
        var sum = BigDecimal.ZERO
        cosmosVestings.forEach { vesting ->
            sum = sum.add(vestingValue(vesting.denom, isUsd))
        }
        return sum
    }

    fun delegationAmountSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        cosmosDelegations.forEach { delegation ->
            delegation.balance?.let {
                sum = sum.add(delegation.balance?.amount?.toBigDecimal())
            } ?: run {
                sum = sum.add(BigDecimal.ZERO)
            }
        }
        return sum
    }

    private fun delegationValueSum(isUsd: Boolean? = false): BigDecimal {
        chain.stakeDenom?.let {
            BaseData.getAsset(chain.apiName, it)?.let { asset ->
                val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
                val amount = delegationAmountSum()
                asset.decimals?.let { decimal ->
                    return price.multiply(amount).movePointLeft(decimal)
                        .setScale(6, RoundingMode.DOWN)
                }
            }
            return BigDecimal.ZERO
        }
        return BigDecimal.ZERO
    }

    fun unbondingAmountSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        cosmosUnbondings.forEach { unbonding ->
            unbonding.entriesList.forEach { entry ->
                sum = sum.add(entry.balance.toBigDecimal())
            }
        }
        return sum
    }

    private fun unbondingValueSum(isUsd: Boolean? = false): BigDecimal {
        chain.stakeDenom?.let {
            BaseData.getAsset(chain.apiName, it)?.let { asset ->
                val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
                val amount = unbondingAmountSum()
                asset.decimals?.let { decimal ->
                    return price.multiply(amount).movePointLeft(decimal)
                        .setScale(6, RoundingMode.DOWN)
                }
            }
            return BigDecimal.ZERO
        }
        return BigDecimal.ZERO
    }

    fun rewardAmountSum(denom: String): BigDecimal {
        var sum = BigDecimal.ZERO
        cosmosRewards.forEach { reward ->
            val matchReward = reward.rewardList.firstOrNull { it.denom == denom }
            val rewardAmount = matchReward?.amount?.toBigDecimalOrNull() ?: BigDecimal.ZERO
            sum = sum.add(rewardAmount)
        }
        return sum.movePointLeft(18).setScale(0, RoundingMode.DOWN)
    }

    private fun rewardValue(denom: String, isUsd: Boolean? = false): BigDecimal {
        BaseData.getAsset(chain.apiName, denom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            val amount = rewardAmountSum(denom)
            asset.decimals?.let { decimal ->
                return price.multiply(amount).movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
            }
        }
        return BigDecimal.ZERO
    }

    fun rewardAllCoins(): List<CoinProto.Coin> {
        val result: MutableList<CoinProto.Coin> = mutableListOf()
        cosmosRewards.forEach { reward ->
            reward.rewardList.forEach { coin ->
                val calAmount =
                    coin.amount.toBigDecimal().movePointLeft(18).setScale(0, RoundingMode.DOWN)
                if (calAmount > BigDecimal.ZERO) {
                    result.add(
                        CoinProto.Coin.newBuilder().setDenom(coin.denom)
                            .setAmount(calAmount.toPlainString()).build()
                    )
                }
            }
        }
        return result
    }

    fun allStakingDenomAmount(): BigDecimal? {
        chain.stakeDenom?.let {
            return balanceAmount(it).add(vestingAmount(it))?.add(delegationAmountSum())
                ?.add(unbondingAmountSum())?.add(rewardAmountSum(it))
        }
        return BigDecimal.ZERO
    }

    fun getGrpc(): Pair<String, Int> {
        val endPoint = Prefs.getGrpcEndpoint(chain)
        if (endPoint.isNotEmpty() && endPoint.split(":").count() == 2) {
            val host = endPoint.split(":")[0].trim()
            val port = endPoint.split(":").getOrNull(1)?.trim()?.toIntOrNull() ?: 443
            return Pair(host, port)
        }
        return Pair(chain.grpcHost, chain.grpcPort)
    }
}