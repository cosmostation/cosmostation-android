package wannabit.io.cosmostaion.network.model

data class PushStatusRequest(val subscribe: Boolean, val fcm_token: String)