package wannabit.io.cosmostaion.utils

import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import wannabit.io.cosmostaion.base.BaseData
import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.network.ApiClient
import wannabit.io.cosmostaion.network.req.PushStatusRequest
import wannabit.io.cosmostaion.network.req.PushSyncRequest
import wannabit.io.cosmostaion.network.req.PushSyncRequest.PushAccount
import java.util.function.Function
import java.util.stream.Collectors

object PushManager {
    @JvmStatic
    fun syncAddresses(context: Context, baseDao: BaseData, token: String) {
        val request = PushSyncRequest(
            token,
            baseDao.onSelectAccounts()
                .map { item -> PushAccount(item.address, item.baseChain) }
        )

        ApiClient.getCosmostationOld(context).syncPushAddress(request)
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
        txEnable: Boolean,
        noticeEnable: Boolean,
        token: String
    ) {
        ApiClient.getCosmostationOld(context)
            .putAlarmStatus(PushStatusRequest(txEnable, noticeEnable, token))
            .enqueue(object : Callback<Void?> {
                override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                    if (response.isSuccessful) {
                        baseDao.txPushEnable = txEnable
                        baseDao.noticePushEnable = noticeEnable
                    }
                }

                override fun onFailure(call: Call<Void?>, t: Throwable) {}
            })
    }

}