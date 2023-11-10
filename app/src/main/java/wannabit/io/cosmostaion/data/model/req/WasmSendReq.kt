package wannabit.io.cosmostaion.data.model.req

class WasmSendReq(val recipient: String?, val amount: String?) {
    var transfer: TransferReq = TransferReq(recipient, amount)

    inner class TransferReq(var recipient: String?, var amount: String?)
}