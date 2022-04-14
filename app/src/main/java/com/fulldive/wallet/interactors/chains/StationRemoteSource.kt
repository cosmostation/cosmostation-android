package com.fulldive.wallet.interactors.chains

import android.content.Context
import com.fulldive.wallet.di.modules.DefaultLocalStorageModule
import com.fulldive.wallet.extensions.safeSingle
import com.joom.lightsaber.ProvidedBy
import io.reactivex.Single
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.dao.ChainParam
import wannabit.io.cosmostaion.network.ApiClient
import javax.inject.Inject

@ProvidedBy(DefaultLocalStorageModule::class)
class StationRemoteSource @Inject constructor(
    private val context: Context
) {

    fun requestChainParams(chain: BaseChain, network: String): Single<ChainParam> {
        return safeSingle {
            if (chain.isTestNet) {
                ApiClient.getStationTest(context).getParam(network).execute()
            } else {
                ApiClient.getStation(context).getParam(network).execute()
            }
                .body()
        }
    }
}