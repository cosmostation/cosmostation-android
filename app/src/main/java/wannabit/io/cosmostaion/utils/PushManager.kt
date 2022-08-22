package wannabit.io.cosmostaion.utils

import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import wannabit.io.cosmostaion.base.BaseData
import wannabit.io.cosmostaion.network.ApiClient
import wannabit.io.cosmostaion.network.req.PushStatusRequest
import wannabit.io.cosmostaion.network.req.PushSyncRequest
import wannabit.io.cosmostaion.network.req.PushSyncRequest.PushAccount

object PushManager {
    @JvmStatic
    fun syncAddresses(context: Context, baseDao: BaseData, token: String) {
        val accounts = baseDao.onSelectAccounts()
            .filter { item -> item.hasPrivateKey }
            .map { item -> PushAccount(item.address, item.baseChain) }

        ApiClient.getCosmostationOld(context).syncPushAddress(PushSyncRequest(token, accounts))
            .enqueue(object : Callback<Void?> {
                override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                    if (response.isSuccessful) {
                        baseDao.fcmToken = token
                    }
                }

                override fun onFailure(call: Call<Void?>, t: Throwable) {}
            })
    }

    @JvmStatic
    fun updateStatus(
        context: Context,
        baseDao: BaseData,
        enable: Boolean,
        token: String
    ) {
        ApiClient.getCosmostationOld(context)
            .putAlarmStatus(PushStatusRequest(enable, token))
            .enqueue(object : Callback<Void?> {
                override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                    if (response.isSuccessful) {
                        baseDao.alarmEnable = enable
                    }
                }

                override fun onFailure(call: Call<Void?>, t: Throwable) {}
            })
    }
}