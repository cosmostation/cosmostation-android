package wannabit.io.cosmostaion.data.model.req

data class SimulateTxReq(
    val txBytes: String
)

data class BroadcastTxReq(
    val mode: Int,
    val tx_bytes: String
)
