package com.fulldive.wallet.interactors.chains

import com.fulldive.wallet.di.modules.DefaultLocalStorageModule
import com.fulldive.wallet.extensions.safeCompletable
import com.joom.lightsaber.ProvidedBy
import io.reactivex.Completable
import wannabit.io.cosmostaion.base.BaseData
import wannabit.io.cosmostaion.dao.ChainParam
import javax.inject.Inject

@ProvidedBy(DefaultLocalStorageModule::class)
class StationLocalSource @Inject constructor(
    private val baseData: BaseData
) {
    fun setChainParams(chainParams: ChainParam): Completable {
        return safeCompletable {
            baseData.mChainParam = chainParams.params
            baseData.mPrices.clear()
        }
    }
}