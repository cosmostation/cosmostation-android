package wannabit.io.cosmostaion.data.repository.chain

import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainGno
import wannabit.io.cosmostaion.chain.cosmosClass.GNO_BLOCK_TIME_QUERY
import wannabit.io.cosmostaion.chain.cosmosClass.GNO_HISTORY_QUERY
import wannabit.io.cosmostaion.chain.fetcher.IotaFetcher
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.majorClass.SUI_HISTORY_QUERY
import wannabit.io.cosmostaion.common.graphQlResponse
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

    override suspend fun suiHistory(
        chain: ChainSui, address: String, after: String?
    ): NetworkResult<Pair<MutableList<JsonObject>, String?>> {
        return try {
            val response = graphQlResponse(
                chain.mainUrl, SUI_HISTORY_QUERY,
                mapOf("addr" to address, "last" to 50, "before" to after)
            )
            val json = Gson().fromJson(response.body?.string(), JsonObject::class.java)
            val txConnection = json["data"]?.asJsonObject?.get("transactions")?.asJsonObject

            val result: MutableList<JsonObject> = mutableListOf()
            txConnection?.get("nodes")?.asJsonArray?.forEach { result.add(it.asJsonObject) }

            val pageInfo = txConnection?.get("pageInfo")?.asJsonObject
            val nextCursor = if (pageInfo?.get("hasPreviousPage")?.asBoolean == true) {
                pageInfo["startCursor"].asString
            } else null

            safeApiCall(Dispatchers.IO) { Pair(result, nextCursor) }

        } catch (e: Exception) {
            safeApiCall(Dispatchers.IO) { Pair(mutableListOf(), null) }
        }
    }

    override suspend fun gnoHistory(
        chain: ChainGno, address: String
    ): NetworkResult<MutableList<JsonObject>?> {
        return try {
            val response = graphQlResponse(
                chain.gnoIndexerUrl, GNO_HISTORY_QUERY, mapOf("addr" to address)
            )
            val json = Gson().fromJson(response.body?.string(), JsonObject::class.java)
            val txs = json["data"]?.asJsonObject?.get("getTransactions")?.takeIf { it.isJsonArray }
                ?.asJsonArray

            val result: MutableList<JsonObject> = mutableListOf()
            txs?.forEach { result.add(it.asJsonObject) }

            val heights = result.mapNotNull {
                it["block_height"]?.takeIf { h -> !h.isJsonNull }?.asLong
            }.distinct()

            if (heights.isNotEmpty()) {
                val heightFilters = heights.map { mapOf("height" to mapOf("eq" to it)) }
                val blockResponse = graphQlResponse(
                    chain.gnoIndexerUrl, GNO_BLOCK_TIME_QUERY, mapOf("heights" to heightFilters)
                )
                val blockJson = Gson().fromJson(blockResponse.body?.string(), JsonObject::class.java)
                val blocks = blockJson["data"]?.asJsonObject?.get("getBlocks")
                    ?.takeIf { it.isJsonArray }?.asJsonArray

                val timeMap = mutableMapOf<Long, String>()
                blocks?.forEach { block ->
                    val height = block.asJsonObject["height"].asLong
                    val time = block.asJsonObject["time"].asString
                    timeMap[height] = time
                }

                result.forEach { tx ->
                    val height = tx["block_height"]?.takeIf { !it.isJsonNull }?.asLong
                    timeMap[height]?.let { time ->
                        tx.addProperty("time", time)
                    }
                }
            }

            safeApiCall(Dispatchers.IO) { result }

        } catch (e: Exception) {
            safeApiCall(Dispatchers.IO) { mutableListOf() }
        }
    }

    override suspend fun iotaFromHistory(
        fetcher: IotaFetcher, address: String
    ): NetworkResult<MutableList<JsonObject>?> {
        return try {
            val param = listOf(
                mapOf(
                    "filter" to mapOf("FromAddress" to address), "options" to mapOf(
                        "showEffects" to true, "showInput" to true, "showBalanceChanges" to true
                    )
                ), null, 50, true
            )

            val iotaFromHistoryRequest = JsonRpcRequest(
                method = "iotax_queryTransactionBlocks", params = param
            )
            val iotaFromHistoryResponse = jsonRpcResponse(fetcher.iotaRpc(), iotaFromHistoryRequest)
            val iotaFromHistoryJsonObject = Gson().fromJson(
                iotaFromHistoryResponse.body?.string(), JsonObject::class.java
            )

            val result: MutableList<JsonObject> = mutableListOf()
            iotaFromHistoryJsonObject["result"].asJsonObject["data"].asJsonArray.forEach { data ->
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

    override suspend fun iotaToHistory(
        fetcher: IotaFetcher, address: String
    ): NetworkResult<MutableList<JsonObject>?> {
        return try {
            val param = listOf(
                mapOf(
                    "filter" to mapOf("ToAddress" to address), "options" to mapOf(
                        "showEffects" to true, "showInput" to true, "showBalanceChanges" to true
                    )
                ), null, 50, true
            )

            val iotaToHistoryRequest = JsonRpcRequest(
                method = "iotax_queryTransactionBlocks", params = param
            )
            val iotaToHistoryResponse = jsonRpcResponse(fetcher.iotaRpc(), iotaToHistoryRequest)
            val iotaToHistoryJsonObject = Gson().fromJson(
                iotaToHistoryResponse.body?.string(), JsonObject::class.java
            )

            val result: MutableList<JsonObject> = mutableListOf()
            iotaToHistoryJsonObject["result"].asJsonObject["data"].asJsonArray.forEach { data ->
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

    override suspend fun bitHistory(
        chain: ChainBitCoin86, afterTxId: String
    ): NetworkResult<MutableList<JsonObject>?> {
        return safeApiCall(Dispatchers.IO) {
            RetrofitInstance.bitApi(chain).bitTxHistory(chain.mainAddress, afterTxId)
        }
    }

    override suspend fun bitBlockHeight(chain: ChainBitCoin86): NetworkResult<Long?> {
        return safeApiCall(Dispatchers.IO) {
            RetrofitInstance.bitApi(chain).bitBlockHeight()
        }
    }

    override suspend fun moveHistory(chain: BaseChain): NetworkResult<MutableList<JsonObject>?> {
        return safeApiCall(Dispatchers.IO) {
            RetrofitInstance.moveApi(chain).moveTxHistory(chain.mainAddress)
        }
    }
}