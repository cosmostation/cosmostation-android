package wannabit.io.cosmostaion.data.repository.chain

import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import wannabit.io.cosmostaion.common.safeApiCall
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.BnbHistoryResponse
import wannabit.io.cosmostaion.data.model.CosmosHistory
import wannabit.io.cosmostaion.data.model.NetworkResult

class HistoryRepositoryImpl : HistoryRepository {

    override suspend fun cosmosHistory(
        chain: String,
        address: String?,
        limit: String,
        searchAfter: String
    ): NetworkResult<Response<List<CosmosHistory>>> {
        return safeApiCall(Dispatchers.IO) {
            RetrofitInstance.mintscanApi.cosmosHistory(chain, address, limit, searchAfter)
        }
    }

    override suspend fun bnbHistory(
        address: String?,
        startTime: String,
        endTime: String
    ): NetworkResult<Response<BnbHistoryResponse>> {
        return safeApiCall(Dispatchers.IO) {
            RetrofitInstance.beaconApi.bnbHistory(address, startTime, endTime)
        }
    }
}