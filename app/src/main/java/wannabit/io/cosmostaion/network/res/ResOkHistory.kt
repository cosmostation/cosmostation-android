package wannabit.io.cosmostaion.network.res

import com.google.gson.annotations.SerializedName

data class ResOkHistory(
    @SerializedName("code")
    var code: Int,

    @SerializedName("msg")
    var message: String,

    @SerializedName("detail_msg")
    var detailMessage: String,

    @SerializedName("data")
    var data: ResOkHistoryData
)

class ResOkHistoryData(
    @SerializedName("hits")
    var hits: List<ResOkHistoryHit>
)

class ResOkHistoryHit(
    @SerializedName("hash")
    var hash: String,

    @SerializedName("blocktime")
    var blocktime: Long,

    @SerializedName("blockHash")
    var blockHash: String,

    @SerializedName("blockHeight")
    var blockHeight: Long,

    @SerializedName("transactionData")
    var transactionDatas: List<ResOkHistoryTransactionData>,

    @SerializedName("blockTimeU0")
    var blockTimeU0: Long
)

class ResOkHistoryTransactionData(
    @SerializedName("type")
    var type: String
)
