package wannabit.io.cosmostaion.data.repository.chain

import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.fetcher.SuiFetcher
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.common.jsonRpcResponse
import wannabit.io.cosmostaion.common.safeApiCall
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.req.JsonRpcRequest
import wannabit.io.cosmostaion.data.model.res.CosmosHistory
import wannabit.io.cosmostaion.data.model.res.NetworkResult

class HistoryRepositoryImpl : HistoryRepository {

    override suspend fun cosmosHistory(
        chain: String, address: String?, limit: String, searchAfter: String
    ): NetworkResult<Response<List<CosmosHistory>>> {
        return safeApiCall(Dispatchers.IO) {
            RetrofitInstance.mintscanApi.cosmosHistory(chain, address, limit, searchAfter)
        }
    }

    override suspend fun suiFromHistory(
        fetcher: SuiFetcher, address: String
    ): NetworkResult<MutableList<JsonObject>?> {
        return try {
            val param = listOf(
                mapOf(
                    "filter" to mapOf("FromAddress" to address), "options" to mapOf(
                        "showEffects" to true, "showInput" to true, "showBalanceChanges" to true
                    )
                ), null, 50, true
            )

            val suiFromHistoryRequest = JsonRpcRequest(
                method = "suix_queryTransactionBlocks", params = param
            )
            val suiFromHistoryResponse = jsonRpcResponse(fetcher.suiRpc(), suiFromHistoryRequest)
            val suiFromHistoryJsonObject = Gson().fromJson(
                suiFromHistoryResponse.body?.string(), JsonObject::class.java
            )

            val result: MutableList<JsonObject> = mutableListOf()
            suiFromHistoryJsonObject["result"].asJsonObject["data"].asJsonArray.forEach { data ->
                result.add(data.asJsonObject)
            }
            safeApiCall(Dispatchers.IO) {
                result
            }

        } catch (e: Exception) {
            safeApiCall(Dispatchers.IO) {
                mutableListOf()
            }
        }
    }

    override suspend fun suiToHistory(
        fetcher: SuiFetcher, address: String
    ): NetworkResult<MutableList<JsonObject>?> {
        return try {
            val param = listOf(
                mapOf(
                    "filter" to mapOf("ToAddress" to address), "options" to mapOf(
                        "showEffects" to true, "showInput" to true, "showBalanceChanges" to true
                    )
                ), null, 50, true
            )

            val suiToHistoryRequest = JsonRpcRequest(
                method = "suix_queryTransactionBlocks", params = param
            )
            val suiToHistoryResponse = jsonRpcResponse(fetcher.suiRpc(), suiToHistoryRequest)
            val suiToHistoryJsonObject = Gson().fromJson(
                suiToHistoryResponse.body?.string(), JsonObject::class.java
            )

            val result: MutableList<JsonObject> = mutableListOf()
            suiToHistoryJsonObject["result"].asJsonObject["data"].asJsonArray.forEach { data ->
                result.add(data.asJsonObject)
            }
            safeApiCall(Dispatchers.IO) {
                result
            }

        } catch (e: Exception) {
            safeApiCall(Dispatchers.IO) {
                mutableListOf()
            }
        }
    }

    override suspend fun ethHistory(
        chain: BaseChain, limit: String, searchAfter: String
    ): NetworkResult<Response<JsonObject?>> {
        return safeApiCall(Dispatchers.IO) {
            RetrofitInstance.mintscanJsonApi.evmHistory(
                chain.apiName, chain.evmAddress, limit, searchAfter
            )
        }
    }

    override suspend fun bitHistory(chain: ChainBitCoin86): NetworkResult<MutableList<JsonObject>?> {
        return safeApiCall(Dispatchers.IO) {
            RetrofitInstance.bitApi(chain).bitTxHistory(chain.mainAddress)
        }
    }

    override suspend fun bitBlockHeight(chain: ChainBitCoin86): NetworkResult<Long?> {
        return safeApiCall(Dispatchers.IO) {
            RetrofitInstance.bitApi(chain).bitBlockHeight()
        }
    }
}