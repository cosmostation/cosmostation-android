package com.fulldive.wallet.interactors.chains

import com.fulldive.wallet.di.modules.DefaultRepositoryModule
import com.joom.lightsaber.ProvidedBy
import io.reactivex.Completable
import wannabit.io.cosmostaion.base.BaseChain
import javax.inject.Inject

@ProvidedBy(DefaultRepositoryModule::class)
class StationRepository @Inject constructor(
    private val stationLocalSource: StationLocalSource,
    private val stationRemoteSource: StationRemoteSource
) {
    fun updateChainParams(baseChain: BaseChain, network: String): Completable {
        return stationRemoteSource.requestChainParams(baseChain, network)
            .flatMapCompletable(stationLocalSource::setChainParams)

    }
}
