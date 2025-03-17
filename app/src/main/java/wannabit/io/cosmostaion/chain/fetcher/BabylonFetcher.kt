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

    var btcReward: BigDecimal? = BigDecimal.ZERO

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

    private fun btcRewardValueSum(isUsd: Boolean? = false): BigDecimal {
        BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            return price.multiply(btcReward).movePointLeft(asset.decimals ?: 6)
                .setScale(6, RoundingMode.DOWN)
        }
        return BigDecimal.ZERO
    }

    override fun denomValue(denom: String, isUsd: Boolean?): BigDecimal? {
        return if (denom == chain.stakeDenom) {
            balanceValue(denom, isUsd).add(vestingValue(denom, isUsd))
                .add(rewardValue(denom, isUsd)).add(delegationValueSum(isUsd))
                .add(unbondingValueSum(isUsd)).add(btcRewardValueSum())

        } else {
            balanceValue(denom, isUsd).add(vestingValue(denom, isUsd))
                .add(rewardValue(denom, isUsd))
        }
    }

    override fun allAssetValue(isUsd: Boolean?): BigDecimal {
        return balanceValueSum(isUsd).add(vestingValueSum(isUsd)).add(delegationValueSum(isUsd))
            .add(unbondingValueSum(isUsd)).add(rewardValueSum(isUsd)).add(btcRewardValueSum(isUsd))
    }
}

fun JsonObject.btcReward(denom: String): BigDecimal {
    return if (this.has("reward_gauges")) {
        val rewardGaugaes = this["reward_gauges"].asJsonObject
        val btcDelegation = rewardGaugaes["btc_delegation"].asJsonObject

        if (!btcDelegation["coins"].asJsonArray.isEmpty && !btcDelegation["withdrawn_coins"].asJsonArray.isEmpty) {
            val coin =
                btcDelegation["coins"].asJsonArray.firstOrNull { it.asJsonObject["denom"].asString == denom }?.asJsonObject?.get(
                    "amount"
                )?.asString?.toBigDecimal() ?: BigDecimal.ZERO
            val withdraw =
                btcDelegation["withdrawn_coins"].asJsonArray.firstOrNull { it.asJsonObject["denom"].asString == denom }?.asJsonObject?.get(
                    "amount"
                )?.asString?.toBigDecimal() ?: BigDecimal.ZERO
            coin.subtract(withdraw)
        } else if (!btcDelegation["coins"].asJsonArray.isEmpty) {
            return btcDelegation["coins"].asJsonArray.firstOrNull { it.asJsonObject["denom"].asString == denom }?.asJsonObject?.get(
                "amount"
            )?.asString?.toBigDecimal() ?: BigDecimal.ZERO
        } else {
            BigDecimal.ZERO
        }

    } else {
        BigDecimal.ZERO
    }
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