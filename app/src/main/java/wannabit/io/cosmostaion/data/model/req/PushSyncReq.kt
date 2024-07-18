package wannabit.io.cosmostaion.data.model.req

data class PushSyncReq(
    var pushToken: String, var enable: Boolean? = false, var wallets: MutableList<PushWallet>
)

data class PushWallet(
    var walletKey: String,
    var walletName: String,
    var accounts: MutableList<PushAccount> = mutableListOf()
)

data class PushAccount(
    var chain: String, var address: String
)
