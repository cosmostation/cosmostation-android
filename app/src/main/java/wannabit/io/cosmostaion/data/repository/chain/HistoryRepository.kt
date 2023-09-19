package wannabit.io.cosmostaion.data.repository.chain

import retrofit2.Response
import wannabit.io.cosmostaion.data.model.BnbHistoryResponse
import wannabit.io.cosmostaion.data.model.CosmosHistory
import wannabit.io.cosmostaion.data.model.NetworkResult

interface HistoryRepository {

    suspend fun cosmosHistory(chain: String, address: String?, limit: String, id: Int): NetworkResult<Response<List<CosmosHistory>>>

    suspend fun bnbHistory(address: String?, startTime: String, endTime: String): NetworkResult<Response<BnbHistoryResponse>>
}