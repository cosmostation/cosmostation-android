package wannabit.io.cosmostaion.chain

import android.net.Uri
import android.os.Parcelable
import com.cosmos.base.v1beta1.CoinProto.Coin
import com.cosmos.distribution.v1beta1.DistributionProto.DelegationDelegatorReward
import com.cosmos.staking.v1beta1.StakingProto
import com.cosmos.staking.v1beta1.StakingProto.DelegationResponse
import com.cosmos.staking.v1beta1.StakingProto.UnbondingDelegation
import kotlinx.parcelize.Parcelize
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.CosmostationConstants.CHAIN_BASE_URL
import wannabit.io.cosmostaion.data.model.res.AccountResponse
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.database.Prefs
import java.math.BigDecimal
import java.math.RoundingMode

@Parcelize
open class CosmosLine : BaseChain(), Parcelable {

    private var duration = 20L

    var rewardAddress: String? = ""
    var cosmosAuth: com.google.protobuf.Any? = null
    var cosmosValidators = mutableListOf<StakingProto.Validator>()
    var cosmosBalances: MutableList<Coin>? = null
    var cosmosVestings = mutableListOf<Coin>()
    var cosmosDelegations = mutableListOf<DelegationResponse>()
    var cosmosUnbondings = mutableListOf<UnbondingDelegation>()
    var cosmosRewards = mutableListOf<DelegationDelegatorReward>()

    var tokens = mutableListOf<Token>()

    var lcdAccountInfo: AccountResponse? = null

//    var cw721s = mutableListOf<JsonObject>()
//    var cw721Fetched = false
//    var cw721Models = mutableListOf<Cw721Model>()

    open fun lcdBalanceValue(denom: String?, isUsd: Boolean? = false): BigDecimal {
        return BigDecimal.ZERO
    }

    fun voteThreshold(): String {
        return getChainListParam()?.get("voting_threshold")?.asString ?: run {
            "0"
        }
    }

    fun balanceAmount(denom: String): BigDecimal {
        if (cosmosBalances?.isNotEmpty() == true) {
            return cosmosBalances?.firstOrNull { it.denom == denom }?.amount?.toBigDecimal()
                ?: BigDecimal.ZERO
        }
        return BigDecimal.ZERO
    }

    fun balanceValue(denom: String, isUsd: Boolean? = false): BigDecimal {
        BaseData.getAsset(apiName, denom)?.let { asset ->
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
        BaseData.getAsset(apiName, denom)?.let { asset ->
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
        BaseData.getAsset(apiName, stakeDenom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            val amount = delegationAmountSum()
            asset.decimals?.let { decimal ->
                return price.multiply(amount).movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
            }
        }
        return BigDecimal.ZERO
    }

    fun unbondingAmountSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        cosmosUnbondings.forEach { unBonding ->
            unBonding.entriesList.forEach { entry ->
                sum = sum.add(entry.balance.toBigDecimal())
            }
        }
        return sum
    }

    private fun unbondingValueSum(isUsd: Boolean? = false): BigDecimal {
        BaseData.getAsset(apiName, stakeDenom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            val amount = unbondingAmountSum()
            asset.decimals?.let { decimal ->
                return price.multiply(amount).movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
            }
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
        BaseData.getAsset(apiName, denom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            val amount = rewardAmountSum(denom)
            asset.decimals?.let { decimal ->
                return price.multiply(amount).movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
            }
        }
        return BigDecimal.ZERO
    }

    fun rewardAllCoins(): List<Coin> {
        val result: MutableList<Coin> = mutableListOf()
        cosmosRewards.forEach { reward ->
            reward.rewardList.forEach { coin ->
                val calAmount =
                    coin.amount.toBigDecimal().movePointLeft(18).setScale(0, RoundingMode.DOWN)
                if (calAmount > BigDecimal.ZERO) {
                    result.add(
                        Coin.newBuilder().setDenom(coin.denom).setAmount(calAmount.toPlainString())
                            .build()
                    )
                }
            }
        }
        return result
    }

    private fun rewardValueSum(isUsd: Boolean? = false): BigDecimal {
        var sum = BigDecimal.ZERO
        rewardAllCoins().forEach { rewardCoin ->
            BaseData.getAsset(apiName, rewardCoin.denom)?.let { asset ->
                val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
                val amount = rewardCoin.amount.toBigDecimal()
                val value = asset.decimals?.let {
                    price.multiply(amount)?.movePointLeft(it)?.setScale(6, RoundingMode.DOWN)
                }
                sum = sum.add(value)
            }
        }
        return sum
    }

    fun valueAbleRewards(): MutableList<DelegationDelegatorReward?> {
        val result: MutableList<DelegationDelegatorReward?> = mutableListOf()

        cosmosRewards.forEach { reward ->
            var eachRewardValue = BigDecimal.ZERO
            for (i in 0 until reward.rewardCount) {
                val rewardAmount = reward.getReward(i).amount.toBigDecimal().movePointLeft(18)
                    .setScale(0, RoundingMode.DOWN)
                BaseData.getAsset(apiName, reward.getReward(i).denom)?.let { asset ->
                    val price = BaseData.getPrice(asset.coinGeckoId, true)
                    val value = price.multiply(rewardAmount).movePointLeft(asset.decimals ?: 6)
                        .setScale(6, RoundingMode.DOWN)
                    eachRewardValue = eachRewardValue.add(value)

                    if (eachRewardValue >= BigDecimal("0.1")) {
                        result.add(reward)
                        return@forEach
                    }
                }
            }
        }
        return result
    }

    fun compoundAbleRewards(): MutableList<DelegationDelegatorReward?> {
        val result: MutableList<DelegationDelegatorReward?> = mutableListOf()
        cosmosRewards.forEach { reward ->
            reward.rewardList.firstOrNull { it.denom == stakeDenom }?.amount?.let { amount ->
                val rewardAmount =
                    amount.toBigDecimal().movePointLeft(18).setScale(0, RoundingMode.DOWN)
                BaseData.getAsset(apiName, stakeDenom)?.let { asset ->
                    val price = BaseData.getPrice(asset.coinGeckoId, true)
                    val value = price.multiply(rewardAmount).movePointLeft(asset.decimals ?: 6)
                        .setScale(6, RoundingMode.DOWN)
                    if (value >= BigDecimal("0.1")) {
                        result.add(reward)
                    }
                }
            }
        }
        return result
    }

    open fun allStakingDenomAmount(): BigDecimal? {
        return balanceAmount(stakeDenom).add(vestingAmount(stakeDenom))?.add(delegationAmountSum())
            ?.add(unbondingAmountSum())?.add(rewardAmountSum(stakeDenom))
    }

    override fun explorerProposal(id: String?): Uri? {
        getChainListParam()?.getAsJsonObject("explorer")
            ?.get("proposal")?.asString?.let { urlString ->
                id?.let {
                    return Uri.parse(urlString.replace("\${id}", it))
                } ?: run {
                    return null
                }
            }
        return null
    }

    fun monikerImg(opAddress: String?): String {
        return "$CHAIN_BASE_URL$apiName/moniker/$opAddress.png"
    }

    fun getGrpc(): Pair<String, Int> {
        val endPoint = Prefs.getGrpcEndpoint(this)
        if (endPoint.isNotEmpty() && endPoint.split(":").count() == 2) {
            val host = endPoint.split(":")[0].trim()
            val port = endPoint.split(":").getOrNull(1)?.trim()?.toIntOrNull() ?: 443
            return Pair(host, port)
        }
        return Pair(grpcHost, grpcPort)
    }
}