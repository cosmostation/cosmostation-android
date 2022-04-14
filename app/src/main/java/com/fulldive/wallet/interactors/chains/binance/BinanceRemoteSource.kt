package com.fulldive.wallet.interactors.chains.binance

import android.content.Context
import com.fulldive.wallet.di.modules.DefaultLocalStorageModule
import com.fulldive.wallet.extensions.safeSingle
import com.joom.lightsaber.ProvidedBy
import io.reactivex.Single
import wannabit.io.cosmostaion.dao.BnbTicker
import wannabit.io.cosmostaion.dao.BnbToken
import wannabit.io.cosmostaion.network.ApiClient
import wannabit.io.cosmostaion.network.res.ResBnbAccountInfo
import wannabit.io.cosmostaion.network.res.ResBnbFee
import wannabit.io.cosmostaion.network.res.ResNodeInfo
import javax.inject.Inject

@ProvidedBy(DefaultLocalStorageModule::class)
class BinanceRemoteSource @Inject constructor(
    private val context: Context
) {
    fun requestTokens(limit: Int): Single<List<BnbToken>> {
        return safeSingle {
            ApiClient.getBnbChain(context).getTokens(limit).execute().body()
        }
    }

    fun requestMiniTokens(limit: Int): Single<List<BnbToken>> {
        return safeSingle {
            ApiClient.getBnbChain(context).getMiniTokens(limit).execute().body()
        }
    }

    fun requestTickers(): Single<List<BnbTicker>> {
        return safeSingle {
            ApiClient.getBnbChain(context).tic.execute().body()
        }
    }

    fun requestMiniTickers(): Single<List<BnbTicker>> {
        return safeSingle {
            ApiClient.getBnbChain(context).miniTic.execute().body()
        }
    }

    fun requestFees(): Single<List<ResBnbFee>> {
        return safeSingle {
            ApiClient.getBnbChain(context).fees.execute().body()
        }
    }

    fun requestAccount(address: String): Single<ResBnbAccountInfo> {
        return safeSingle {
            ApiClient.getBnbChain(context).getAccountInfo(address).execute().body()
        }
    }

    fun requestNodeInfo(): Single<ResNodeInfo> {
        return safeSingle {
            ApiClient.getBnbChain(context).nodeInfo.execute().body()
        }
    }
}