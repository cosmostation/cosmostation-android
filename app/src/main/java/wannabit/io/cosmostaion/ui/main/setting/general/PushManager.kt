package wannabit.io.cosmostaion.ui.main.setting.general

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.req.PushAccount
import wannabit.io.cosmostaion.data.model.req.PushStatusRequest
import wannabit.io.cosmostaion.data.model.req.PushSyncReq
import wannabit.io.cosmostaion.database.Prefs

object PushManager {
    @JvmStatic
    fun syncAddresses(token: String) {
        val account = BaseData.baseAccount ?: return
        if (account.sortedDisplayCosmosLines().isNotEmpty()) {
            account.sortedDisplayCosmosLines()
                .map { chain -> chain.address?.let { PushAccount(it, chain.name) } }.toMutableList()
                .let { accounts ->
                    RetrofitInstance.walletApi.syncPushAddress(PushSyncReq(token, accounts))
                        .enqueue(object : Callback<Void> {
                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                Prefs.fcmToken = token
                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {}
                        })
                }
        }
    }

    @JvmStatic
    fun updateStatus(
        enable: Boolean, token: String
    ) {
        RetrofitInstance.walletApi.putAlarmStatus(PushStatusRequest(enable, token))
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Prefs.alarmEnable = enable
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {}
            })
    }
}