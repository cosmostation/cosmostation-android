package wannabit.io.cosmostaion.ui.main.setting.general

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.req.PushAccount
import wannabit.io.cosmostaion.data.model.req.PushSyncReq
import wannabit.io.cosmostaion.data.model.req.PushWallet
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType

object PushManager {
    @JvmStatic
    fun syncAddresses(token: String) {
        val account = BaseData.baseAccount ?: return

//        if (account.sortedDisplayCosmosLines().isNotEmpty()) {
//            account.sortedDisplayCosmosLines()
//                .map { chain -> chain.address?.let { PushAccount(it, chain.name) } }.toMutableList()
//                .let { accounts ->
//                    RetrofitInstance.walletApi.syncPushAddress(PushSyncReq(token, accounts))
//                        .enqueue(object : Callback<Void> {
//                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                                Prefs.fcmToken = token
//                            }
//
//                            override fun onFailure(call: Call<Void>, t: Throwable) {}
//                        })
//                }
//        }
    }

    @JvmStatic
    fun updateStatus(
        enable: Boolean, completion: (Boolean, String) -> Unit
    ) {
        if (Prefs.fcmToken.isEmpty()) {
            Prefs.alarmEnable = false
            completion(false, "Not FCM Token")
        }

        CoroutineScope(Dispatchers.IO).launch {
            val param = pushInfo(enable, Prefs.fcmToken)
            Log.e("Test1234 : ", param.toString())
            RetrofitInstance.mintscanApi.syncPush(param).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Prefs.alarmEnable = enable
                    BaseData.setLastPushTime()
                    completion(true, "Push Notification Updated.")
                    return
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Prefs.alarmEnable = false
                    completion(false, t.message.toString())
                    return
                }
            })
        }
    }

    private suspend fun pushInfo(enable: Boolean, fcmToken: String): PushSyncReq {
        val wallets: MutableList<PushWallet> = mutableListOf()
        withContext(Dispatchers.IO) {
            if (enable) {
                AppDatabase.getInstance().baseAccountDao().selectAll().forEach { account ->
                    pushWallet(account)?.let { wallet ->
                        wallets.add(wallet)
                    }
                }
            }
        }
        return PushSyncReq(fcmToken, enable, wallets)
    }

    private suspend fun pushWallet(account: BaseAccount): PushWallet? {
        var pushWallet: PushWallet? = null
        withContext(Dispatchers.IO) {
            val pushAccounts: MutableList<PushAccount> = mutableListOf()
            initAllKeyData().filter { !it.isTestnet }.forEach { chain ->
                if (chain.isCosmos()) {
                    val pushAccount = PushAccount(chain.apiName, chain.address)
                    pushAccounts.add(pushAccount)
                } else if (chain.supportEvm) {
                    val pushAccount = PushAccount(chain.apiName, chain.evmAddress)
                    pushAccounts.add(pushAccount)
                }
            }
            pushWallet =  PushWallet(
                account.id.toString() + account.uuid + account.id.toString(), account.name, pushAccounts
            )
        }
        return pushWallet
    }

    private suspend fun initAllKeyData(): MutableList<BaseChain> {
        val result = allChains()
        BaseData.baseAccount?.let { account ->
            withContext(Dispatchers.IO) {
                account.apply {
                    if (type == BaseAccountType.MNEMONIC) {
                        result.forEach { chain ->
                            if (chain.publicKey == null) {
                                chain.setInfoWithSeed(seed, chain.setParentPath, lastHDPath)
                            }
                        }

                    } else if (type == BaseAccountType.PRIVATE_KEY) {
                        result.forEach { chain ->
                            if (chain.publicKey == null) {
                                chain.setInfoWithPrivateKey(privateKey)
                            }
                        }
                    }
                }
            }
        }
        return result
    }
}