package wannabit.io.cosmostaion.data.repository.chain

import retrofit2.Response
import wannabit.io.cosmostaion.data.model.res.CosmosHistory
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.OktHistoryResponse

interface HistoryRepository {

    suspend fun cosmosHistory(
        chain: String,
        address: String?,
        limit: String,
        searchAfter: String
    ): NetworkResult<Response<List<CosmosHistory>>>

    suspend fun oktHistory(
        device: String,
        address: String?,
        limit: String
    ): NetworkResult<Response<OktHistoryResponse>>
}