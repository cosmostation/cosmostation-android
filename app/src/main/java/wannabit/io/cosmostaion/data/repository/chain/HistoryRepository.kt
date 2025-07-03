package wannabit.io.cosmostaion.data.repository.chain

import com.google.gson.JsonObject
import retrofit2.Response
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.fetcher.IotaFetcher
import wannabit.io.cosmostaion.chain.fetcher.SuiFetcher
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.majorClass.ChainSolana
import wannabit.io.cosmostaion.data.model.res.CosmosHistory
import wannabit.io.cosmostaion.data.model.res.NetworkResult

interface HistoryRepository {

    suspend fun cosmosHistory(
        chain: String, address: String?, limit: String, searchAfter: String
    ): NetworkResult<Response<List<CosmosHistory>>>

    suspend fun suiFromHistory(
        fetcher: SuiFetcher, address: String
    ): NetworkResult<MutableList<JsonObject>?>

    suspend fun suiToHistory(
        fetcher: SuiFetcher, address: String
    ): NetworkResult<MutableList<JsonObject>?>

    suspend fun iotaFromHistory(
        fetcher: IotaFetcher, address: String
    ): NetworkResult<MutableList<JsonObject>?>

    suspend fun iotaToHistory(
        fetcher: IotaFetcher, address: String
    ): NetworkResult<MutableList<JsonObject>?>

    suspend fun ethHistory(
        chain: BaseChain, limit: String, searchAfter: String
    ): NetworkResult<Response<JsonObject?>>

    suspend fun bitHistory(chain: ChainBitCoin86, afterTxId: String): NetworkResult<MutableList<JsonObject>?>

    suspend fun bitBlockHeight(chain: ChainBitCoin86): NetworkResult<Long?>

    suspend fun solanaHistory(
        chain: ChainSolana
    ): NetworkResult<MutableList<JsonObject>?>
}