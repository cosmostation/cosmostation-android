package wannabit.io.cosmostaion.data.model.req

data class WasmSendReq(val transfer: TransferReq) {

    data class TransferReq(val recipient: String, val amount: String)

    constructor(recipient: String, amount: String) : this(TransferReq(recipient, amount))
}