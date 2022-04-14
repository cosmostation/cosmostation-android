package com.fulldive.wallet.interactors.chains

import com.fulldive.wallet.di.modules.DefaultInteractorsModule
import com.joom.lightsaber.ProvidedBy
import io.reactivex.Completable
import wannabit.io.cosmostaion.base.BaseChain
import javax.inject.Inject

@ProvidedBy(DefaultInteractorsModule::class)
class StationInteractor @Inject constructor(
    private val stationRepository: StationRepository
) {

    fun updateStationParams(baseChain: BaseChain, network: String): Completable {
        return stationRepository.updateChainParams(baseChain, network)
    }
}