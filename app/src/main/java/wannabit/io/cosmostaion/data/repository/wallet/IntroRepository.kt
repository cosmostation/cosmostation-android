package wannabit.io.cosmostaion.data.repository.wallet

import retrofit2.Response
import wannabit.io.cosmostaion.data.model.AppVersion
import wannabit.io.cosmostaion.data.model.AssetResponse
import wannabit.io.cosmostaion.data.model.NetworkResult
import wannabit.io.cosmostaion.data.model.Price

interface IntroRepository {
    suspend fun version(): NetworkResult<Response<AppVersion>>

    suspend fun price(): NetworkResult<Response<List<Price>>>

    suspend fun asset(): NetworkResult<Response<AssetResponse>>
}