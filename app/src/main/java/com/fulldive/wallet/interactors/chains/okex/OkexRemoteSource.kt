package com.fulldive.wallet.interactors.chains.okex

import android.content.Context
import com.fulldive.wallet.di.modules.DefaultLocalStorageModule
import com.fulldive.wallet.extensions.safeSingle
import com.joom.lightsaber.ProvidedBy
import io.reactivex.Single
import wannabit.io.cosmostaion.model.type.Validator
import wannabit.io.cosmostaion.network.ApiClient
import wannabit.io.cosmostaion.network.res.*
import javax.inject.Inject

@ProvidedBy(DefaultLocalStorageModule::class)
class OkexRemoteSource @Inject constructor(
    private val context: Context
) {
    fun requestValidatorsList(): Single<List<Validator>> {
        return safeSingle {
            ApiClient.getOkexChain(context).allValidatorDetailList.execute().body()
        }
    }

    fun requestTickersList(): Single<ResOkTickersList> {
        return safeSingle {
            ApiClient.getOkexChain(context).dexTickers.execute().body()
        }
    }

    fun requestTokensList(): Single<ResOkTokenList> {
        return safeSingle {
            ApiClient.getOkexChain(context).tokenList.execute().body()
        }
    }

    fun requestAccount(address: String): Single<ResOkAccountInfo> {
        return safeSingle {
            ApiClient.getOkexChain(context).getAccountInfo(address).execute().body()
        }
    }

    fun requestAccountBalance(address: String): Single<ResOkAccountToken> {
        return safeSingle {
            ApiClient.getOkexChain(context).getAccountBalance(address).execute().body()
        }
    }

    fun requestUnbonding(address: String): Single<ResOkUnbonding> {
        return safeSingle {
            ApiClient.getOkexChain(context).getWithdrawInfo(address).execute().body()
        }
    }

    fun requestStakingInfo(address: String): Single<ResOkStaking> {
        return safeSingle {
            ApiClient.getOkexChain(context).getDepositInfo(address).execute().body()
        }
    }

    fun requestNodeInfo(): Single<ResNodeInfo> {
        return safeSingle {
            ApiClient.getOkexChain(context).nodeInfo.execute().body()
        }
    }
}