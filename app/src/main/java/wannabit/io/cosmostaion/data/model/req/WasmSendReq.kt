package wannabit.io.cosmostaion.data.model.req

data class WasmSendReq(val transfer: TransferReq) {

    data class TransferReq(val recipient: String, val amount: String)

    constructor(recipient: String, amount: String) : this(TransferReq(recipient, amount))
}

data class WasmCw721SendReq(val transfer_nft: Cw721SendReq) {

    data class Cw721SendReq(val recipient: String, val token_id: String)

    constructor(recipient: String, token_id: String) : this(Cw721SendReq(recipient, token_id))
}