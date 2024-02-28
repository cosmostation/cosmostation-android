package wannabit.io.cosmostaion.data.model.req

data class JsonRpcRequest(
    val method: String, val jsonrpc: String, val id: Long, val params: List<Any?>
) {
    constructor(method: String, params: List<Any?>) : this(
        method, "2.0", 1, params
    )
}

data class EstimateGasParams(
    val from: String?,
    val to: String?,
    val data: String?
)