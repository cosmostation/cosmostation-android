package com.fulldive.wallet.interactors

import android.content.Context
import com.fulldive.wallet.di.modules.DefaultInteractorsModule
import com.joom.lightsaber.ProvidedBy
import io.reactivex.Single
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.model.type.BnbHistory
import wannabit.io.cosmostaion.network.ApiClient
import wannabit.io.cosmostaion.network.res.ResApiNewTxListCustom
import wannabit.io.cosmostaion.network.res.ResBnbHistories
import wannabit.io.cosmostaion.network.res.ResOkHistoryHit
import javax.inject.Inject

@ProvidedBy(DefaultInteractorsModule::class)
class HistoryInteractor @Inject constructor(
    private val context: Context
) {

    fun getAccountTxHistory(
        chain: BaseChain,
        address: String,
        limit: Int = 50
    ): Single<List<ResApiNewTxListCustom>> {
        val historyApi = ApiClient.getChainHistoryApi(chain)
        return historyApi?.getNewAccountTxCustomRx(address, limit)
            ?: Single.error(Exception("HistoryApi for this chain isn't exists"))
    }

    fun getOkAccountTxHistory(
        address: String,
        limit: Int = 20
    ): Single<List<ResOkHistoryHit>> {
        return ApiClient.getOecApi(context).getNewOecTxsRx(address, limit).map { it.data.hits }
    }

    fun getBinanceAccountTxHistory(
        address: String,
        startTime: String,
        endTime: String,
        txAsset: String = ""
    ): Single<List<BnbHistory>> {
        val apiClient = ApiClient.getBnbChain(context)
        return if (txAsset.isEmpty()) {
            apiClient.getHistoryRx(address, startTime, endTime)
        } else {
            apiClient.getHistoryAssetRx(address, startTime, endTime, txAsset)
        }
            .map(ResBnbHistories::tx)
    }
}