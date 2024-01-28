package wannabit.io.cosmostaion.data.model.req

data class PushSyncReq(
    var fcm_token: String, var accounts: MutableList<PushAccount?>
)

data class PushAccount(
    var address: String, var chain: String
)

data class PushStatusRequest(val subscribe: Boolean, val fcm_token: String)