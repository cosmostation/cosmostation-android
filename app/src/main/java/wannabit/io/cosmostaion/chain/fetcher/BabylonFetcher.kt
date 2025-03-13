package wannabit.io.cosmostaion.chain.fetcher

import com.babylon.epoching.v1.QueryProto.QueryCurrentEpochResponse
import com.babylon.epoching.v1.QueryProto.QueuedMessageResponse
import com.cosmos.base.v1beta1.CoinProto.Coin
import com.google.gson.JsonObject
import com.google.protobuf.Timestamp
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.dateToLong

class BabylonFetcher(private val chain: BaseChain) : CosmosFetcher(chain) {

    data class BabylonEpochData(
        var currentHeight: Long? = 0,
        var currentEpoch: QueryCurrentEpochResponse?,
        var epochMsg: MutableList<QueuedMessageResponse>
    )

    data class BabylonEpochTxType(
        val type: String,
        val validator: String?,
        val coin: Coin?
    )
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