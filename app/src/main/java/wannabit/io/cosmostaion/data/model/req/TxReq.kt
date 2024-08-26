package wannabit.io.cosmostaion.data.model.req

data class SimulateTxReq(
    val txBytes: String
)

data class BroadcastTxReq(
    val mode: Int, val tx_bytes: String
)

//Sui
data class SuiStakeReq(
    val address: String,
    val validatorAddress: String?,
    val gas: String,
    val amount: String,
    val rpc: String
)

data class SuiUnStakeReq(
    val address: String, val objectId: String?, val gas: String, val rpc: String
)

data class SuiTransactionBlock(val txBlock: String, val address: String, val rpc: String)

