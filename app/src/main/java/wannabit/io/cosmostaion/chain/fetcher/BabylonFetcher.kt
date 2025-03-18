package wannabit.io.cosmostaion.chain.fetcher

import com.babylon.epoching.v1.QueryProto.QueryCurrentEpochResponse
import com.babylon.epoching.v1.QueryProto.QueuedMessageResponse
import com.cosmos.base.v1beta1.CoinProto.Coin
import com.google.gson.JsonObject
import com.google.protobuf.Timestamp
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.dateToLong
import java.math.BigDecimal
import java.math.RoundingMode

class BabylonFetcher(private val chain: BaseChain) : CosmosFetcher(chain) {

    var btcRewards: MutableList<Coin> = mutableListOf()

    data class BabylonEpochData(
        var currentHeight: Long? = 0,
        var currentEpoch: QueryCurrentEpochResponse?,
        var epochMsg: MutableList<QueuedMessageResponse>
    )

    data class BabylonEpochTxType(
        val type: String,
        val validator: String?,
        val coin: Coin?,
        val createHeight: Long? = 0
    )

    override fun denomValue(denom: String, isUsd: Boolean?): BigDecimal? {
        return if (denom == chain.stakeDenom) {
            balanceValue(denom, isUsd).add(vestingValue(denom, isUsd))
                .add(rewardValue(denom, isUsd)).add(delegationValueSum(isUsd))
                .add(unbondingValueSum(isUsd)).add(btcRewardValue(denom, isUsd))

        } else {
            balanceValue(denom, isUsd).add(vestingValue(denom, isUsd))
        }
    }

    override fun allAssetValue(isUsd: Boolean?): BigDecimal {
        return balanceValueSum(isUsd).add(vestingValueSum(isUsd)).add(delegationValueSum(isUsd))
            .add(unbondingValueSum(isUsd)).add(rewardValueSum(isUsd)).add(btcRewardValueSum(isUsd))
    }

    fun btcRewardAmountSum(denom: String): BigDecimal {
        var sum = BigDecimal.ZERO
        btcRewards.forEach { reward ->
            if (reward.denom == denom) {
                val rewardAmount = reward.amount?.toBigDecimalOrNull() ?: BigDecimal.ZERO
                sum = sum.add(rewardAmount)
            }
        }
        return sum
    }

    private fun btcRewardValue(denom: String, isUsd: Boolean? = false): BigDecimal {
        BaseData.getAsset(chain.apiName, denom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            val amount = btcRewardAmountSum(denom)
            return price.multiply(amount).movePointLeft(asset.decimals ?: 6)
                .setScale(6, RoundingMode.DOWN)
        }
        return BigDecimal.ZERO
    }

    private fun btcRewardValueSum(isUsd: Boolean? = false): BigDecimal {
        var sum = BigDecimal.ZERO
        btcRewards.forEach { rewardCoin ->
            BaseData.getAsset(chain.apiName, rewardCoin.denom)?.let { asset ->
                val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
                val amount = rewardCoin.amount.toBigDecimal()
                val value = price.multiply(amount)?.movePointLeft(asset.decimals ?: 6)
                    ?.setScale(6, RoundingMode.DOWN)
                sum = sum.add(value)
            }
        }
        return sum
    }

    fun btcRewardOtherDenoms(): Int {
        return btcRewards.map { it.denom }.distinct().count { it != chain.stakeDenom }
    }

    override fun allStakingDenomAmount(): BigDecimal? {
        return balanceAmount(chain.stakeDenom).add(vestingAmount(chain.stakeDenom))
            ?.add(delegationAmountSum())?.add(unbondingAmountSum())
            ?.add(rewardAmountSum(chain.stakeDenom))?.add(btcRewardAmountSum(chain.stakeDenom))
    }
}

fun JsonObject.btcReward(denom: String): MutableList<Coin> {
    val btcRewards: MutableList<Coin> = mutableListOf()
    if (this.has("reward_gauges")) {
        val rewardGaugaes = this["reward_gauges"].asJsonObject
        val btcDelegation = rewardGaugaes["btc_delegation"].asJsonObject

        if (!btcDelegation["coins"].asJsonArray.isEmpty && !btcDelegation["withdrawn_coins"].asJsonArray.isEmpty) {
            btcDelegation["coins"].asJsonArray.forEach { coin ->
                btcDelegation["withdrawn_coins"].asJsonArray.forEach { withdraw ->
                    if (coin.asJsonObject["denom"].asString == withdraw.asJsonObject["denom"].asString) {
                        val reward = coin.asJsonObject["amount"].asString.toBigDecimal()
                            .subtract(withdraw.asJsonObject["amount"].asString.toBigDecimal())
                        btcRewards.add(
                            Coin.newBuilder().setDenom(coin.asJsonObject["denom"].asString)
                                .setAmount(reward.toPlainString()).build()
                        )
                    }
                }
            }

        } else if (!btcDelegation["coins"].asJsonArray.isEmpty) {
            btcDelegation["coins"].asJsonArray.forEach { coin ->
                btcRewards.add(
                    Coin.newBuilder().setDenom(coin.asJsonObject["denom"].asString)
                        .setAmount(coin.asJsonObject["amount"].asString).build()
                )
            }

        } else {
            btcRewards.add(
                Coin.newBuilder().setDenom(denom)
                    .setAmount("0").build()
            )
        }

    } else {
        btcRewards.add(
            Coin.newBuilder().setDenom(denom)
                .setAmount("0").build()
        )
    }
    return btcRewards
}

fun JsonObject.currentEpoch(): QueryCurrentEpochResponse {
    val currentEpoch = this["current_epoch"].asString
    val epochBoundary = this["epoch_boundary"].asString
    return QueryCurrentEpochResponse.newBuilder().setCurrentEpoch(currentEpoch.toLong())
        .setEpochBoundary(epochBoundary.toLong()).build()
}

fun JsonObject.epochMsg(chain: BaseChain): MutableList<QueuedMessageResponse> {
    val result = mutableListOf<QueuedMessageResponse>()
    this["msgs"].asJsonArray.forEach { msg ->
        if (msg.asJsonObject["msg"].asString.contains(chain.address)) {
            val txId = msg.asJsonObject["tx_id"].asString
            val msgId = msg.asJsonObject["msg_id"].asString
            val blockHeight = msg.asJsonObject["block_height"].asString
            val blockTime = msg.asJsonObject["block_time"].asString
            val dpBlockTime = dateToLong("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSX", blockTime)
            val time = Timestamp.newBuilder().setSeconds(dpBlockTime / 1000).build()
            val msgString = msg.asJsonObject["msg"].asString

            val tempMessageResponse =
                QueuedMessageResponse.newBuilder().setTxId(txId).setMsgId(msgId)
                    .setBlockHeight(blockHeight.toLong()).setBlockTime(time).setMsg(msgString)
                    .build()
            result.add(tempMessageResponse)
        }
    }
    return result
}