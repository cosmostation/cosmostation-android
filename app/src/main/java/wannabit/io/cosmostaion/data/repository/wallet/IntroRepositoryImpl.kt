package wannabit.io.cosmostaion.data.repository.wallet

import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import wannabit.io.cosmostaion.common.safeApiCall
import wannabit.io.cosmostaion.data.api.RetrofitInstance.mintscanApi
import wannabit.io.cosmostaion.data.api.RetrofitInstance.walletApi
import wannabit.io.cosmostaion.data.model.AppVersion
import wannabit.io.cosmostaion.data.model.AssetResponse
import wannabit.io.cosmostaion.data.model.NetworkResult
import wannabit.io.cosmostaion.data.model.Price

class IntroRepositoryImpl : IntroRepository {

    override suspend fun version(): NetworkResult<Response<AppVersion>> {
        return safeApiCall(Dispatchers.IO) {
            walletApi.version()
        }
    }

    override suspend fun price(): NetworkResult<Response<List<Price>>> {
        return safeApiCall(Dispatchers.IO) {
            mintscanApi.price()
        }
    }

    override suspend fun asset(): NetworkResult<Response<AssetResponse>> {
        return safeApiCall(Dispatchers.IO) {
            mintscanApi.asset()
        }
    }
}