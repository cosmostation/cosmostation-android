package wannabit.io.cosmostaion.network.model

data class PushSyncRequest(val fcm_token: String, val accounts: List<PushAccount>)