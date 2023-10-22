package wannabit.io.cosmostaion.data.repository.wallet

import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import wannabit.io.cosmostaion.common.safeApiCall
import wannabit.io.cosmostaion.data.api.RetrofitInstance.mintscanApi
import wannabit.io.cosmostaion.data.api.RetrofitInstance.walletApi
import wannabit.io.cosmostaion.data.model.res.AppVersion
import wannabit.io.cosmostaion.data.model.res.AssetResponse
import wannabit.io.cosmostaion.data.model.res.ChainResponse
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.Price

class WalletRepositoryImpl : WalletRepository {

    override suspend fun version(): NetworkResult<Response<AppVersion>> {
        return safeApiCall(Dispatchers.IO) {
            walletApi.version()
        }
    }

    override suspend fun chain(): NetworkResult<Response<ChainResponse>> {
        return safeApiCall(Dispatchers.IO) {
            mintscanApi.chain()
        }
    }

    override suspend fun price(currency: String): NetworkResult<Response<List<Price>>> {
        return safeApiCall(Dispatchers.IO) {
            mintscanApi.price(currency)
        }
    }

    override suspend fun asset(): NetworkResult<Response<AssetResponse>> {
        return safeApiCall(Dispatchers.IO) {
            mintscanApi.asset()
        }
    }
}