package wannabit.io.cosmostaion.data.model.req

class WasmIbcSendMsg(var channel: String?, var remote_address: String?, var timeout: Long?)

class WasmIbcSendReq(contract: String?, amount: String?, msg: String?) {
    private var send: Send

    init {
        send = Send(contract, amount, msg)
    }

    inner class Send(var contract: String?, var amount: String?, var msg: String?)
}