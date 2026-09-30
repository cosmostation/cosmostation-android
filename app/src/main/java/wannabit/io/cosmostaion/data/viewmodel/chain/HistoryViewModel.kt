package wannabit.io.cosmostaion.data.viewmodel.chain

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainGno
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.majorClass.ChainIota
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.common.dpMicroTimeToYear
import wannabit.io.cosmostaion.common.dpTimeToYear
import wannabit.io.cosmostaion.common.formatTxTime
import wannabit.io.cosmostaion.data.model.res.CosmosHistory
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.repository.chain.HistoryRepository

class HistoryViewModel(private val historyRepository: HistoryRepository) : ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private var _historyResult = MutableLiveData<MutableList<Pair<String, CosmosHistory>>>()
    val historyResult: LiveData<MutableList<Pair<String, CosmosHistory>>> get() = _historyResult

    fun history(
        context: Context, chain: String, address: String?, limit: String, searchAfter: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        when (val response = historyRepository.cosmosHistory(chain, address, limit, searchAfter)) {
            is NetworkResult.Success -> {
                response.data.let { data ->
                    if (data.isSuccessful) {
                        val result: MutableList<Pair<String, CosmosHistory>> = mutableListOf()
                        data.body()?.forEach { history ->
                            history.header?.let {
                                val headerDate = formatTxTime(context, it.timestamp)
                                result.add(Pair(headerDate, history))
                            }
                        }
                        _historyResult.postValue(result)

                    } else {
                        _errorMessage.postValue("Error")
                    }
                }
            }

            is NetworkResult.Error -> {
                _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }

    private var _ethHistoryResult = MutableLiveData<JsonObject>()
    val ethHistoryResult: LiveData<JsonObject> get() = _ethHistoryResult
    fun ethHistory(
        chain: BaseChain, limit: String, searchAfter: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        when (val response = historyRepository.ethHistory(chain, limit, searchAfter)) {
            is NetworkResult.Success -> {
                response.data.let { data ->
                    if (data.isSuccessful) {
                        _ethHistoryResult.postValue(data.body())

                    } else {
                        _errorMessage.postValue("Error")
                    }
                }
            }

            is NetworkResult.Error -> {
                _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }

    private var _majorHistoryResult = MutableLiveData<MutableList<Pair<String, JsonObject>>>()
    val majorHistoryResult: LiveData<MutableList<Pair<String, JsonObject>>> get() = _majorHistoryResult

    fun suiHistory(chain: ChainSui) = viewModelScope.launch(Dispatchers.IO) {
        chain.suiFetcher()?.let { fetcher ->
            fetcher.suiHistory.clear()

            try {
                val historyResult = historyRepository.suiHistory(chain, chain.mainAddress, null)
                if (historyResult is NetworkResult.Success) {
                    fetcher.suiHistory.addAll(historyResult.data.first)
                    fetcher.suiHistory.sortByDescending {
                        it["effects"]?.asJsonObject?.get("checkpoint")?.asJsonObject
                            ?.get("sequenceNumber")?.asLong ?: 0L
                    }
                    val result: MutableList<Pair<String, JsonObject>> = mutableListOf()
                    fetcher.suiHistory.forEach { history ->
                        val timestampMs =
                            java.time.Instant.parse(history["effects"].asJsonObject["timestamp"].asString)
                                .toEpochMilli()
                        val headerDate = dpTimeToYear(timestampMs)
                        result.add(Pair(headerDate, history))
                    }
                    _majorHistoryResult.postValue(result)

                } else if (historyResult is NetworkResult.Error) {
                    _errorMessage.postValue("error type : ${historyResult.errorType}  error message : ${historyResult.errorMessage}")
                }

            } catch (e: Exception) {
            }
        }
    }

    fun gnoHistory(chain: ChainGno) = viewModelScope.launch(Dispatchers.IO) {
        chain.gnoRpcFetcher()?.let { fetcher ->
            fetcher.gnoHistory.clear()

            try {
                val historyResult = historyRepository.gnoHistory(chain, chain.address)
                if (historyResult is NetworkResult.Success) {
                    fetcher.gnoHistory.addAll(historyResult.data ?: mutableListOf())

                    val result: MutableList<Pair<String, JsonObject>> = mutableListOf()
                    fetcher.gnoHistory.forEach { history ->
                        val headerDate = if (history.has("time")) {
                            val timestampMs =
                                java.time.Instant.parse(history["time"].asString).toEpochMilli()
                            dpTimeToYear(timestampMs)
                        } else {
                            ""
                        }
                        result.add(Pair(headerDate, history))
                    }
                    _majorHistoryResult.postValue(result)

                } else if (historyResult is NetworkResult.Error) {
                    _errorMessage.postValue("error type : ${historyResult.errorType}  error message : ${historyResult.errorMessage}")
                }

            } catch (e: Exception) {
            }
        }
    }

    fun iotaHistory(chain: ChainIota) = viewModelScope.launch(Dispatchers.IO) {
        chain.iotaFetcher()?.let { fetcher ->
            fetcher.iotaHistory.clear()

            try {
                val loadFromHistoryDeferred =
                    async { historyRepository.iotaFromHistory(fetcher, chain.mainAddress) }
                val loadToHistoryDeferred =
                    async { historyRepository.iotaToHistory(fetcher, chain.mainAddress) }

                val fromHistoryResult = loadFromHistoryDeferred.await()
                val toHistoryResult = loadToHistoryDeferred.await()

                if (fromHistoryResult is NetworkResult.Success && toHistoryResult is NetworkResult.Success) {
                    val result: MutableList<Pair<String, JsonObject>> = mutableListOf()
                    fetcher.iotaHistory.addAll(fromHistoryResult.data ?: mutableListOf())
                    toHistoryResult.data?.forEach { to ->
                        val existingItem =
                            fetcher.iotaHistory.firstOrNull { it["digest"].asString == to["digest"].asString }
                        if (existingItem == null) {
                            fetcher.iotaHistory.add(to)
                        }
                    }
                    fetcher.iotaHistory.sortByDescending {
                        it["checkpoint"].asString.toLongOrNull() ?: 0L
                    }
                    fetcher.iotaHistory.forEach { history ->
                        val headerDate = dpTimeToYear(history["timestampMs"].asString.toLong())
                        result.add(Pair(headerDate, history))
                    }
                    _majorHistoryResult.postValue(result)

                } else {
                    if (fromHistoryResult is NetworkResult.Error) {
                        _errorMessage.postValue("error type : ${fromHistoryResult.errorType}  error message : ${fromHistoryResult.errorMessage}")

                    } else if (toHistoryResult is NetworkResult.Error) {
                        _errorMessage.postValue("error type : ${toHistoryResult.errorType}  error message : ${toHistoryResult.errorMessage}")
                    }
                }

            } catch (_: Exception) {

            }
        }
    }

    fun bitHistory(chain: ChainBitCoin86, afterTxId: String) =
        viewModelScope.launch(Dispatchers.IO) {
            chain.btcFetcher()?.let { fetcher ->
                fetcher.btcBlockHeight = 0
                fetcher.btcHistory.clear()

                try {
                    val loadHistoryDeferred =
                        async { historyRepository.bitHistory(chain, afterTxId) }
                    val loadBlockHeightDeferred = async { historyRepository.bitBlockHeight(chain) }

                    val historyResult = loadHistoryDeferred.await()
                    val blockHeightResult = loadBlockHeightDeferred.await()

                    if (historyResult is NetworkResult.Success && blockHeightResult is NetworkResult.Success) {
                        fetcher.btcBlockHeight = blockHeightResult.data ?: 0
                        val result: MutableList<Pair<String, JsonObject>> = mutableListOf()
                        fetcher.btcHistory.addAll(historyResult.data ?: mutableListOf())
                        fetcher.btcHistory = fetcher.btcHistory.sortedWith { o1, o2 ->
                            val time1 =
                                o1["status"]?.asJsonObject?.get("block_time")?.asLong?.times(1000)
                            val time2 =
                                o2["status"]?.asJsonObject?.get("block_time")?.asLong?.times(1000)

                            when {
                                time1 == null && time2 == null -> 0
                                time1 == null -> -1
                                time2 == null -> 1
                                else -> time2.compareTo(time1)
                            }
                        }.toMutableList()

                        fetcher.btcHistory.forEach { history ->
                            val headerDate =
                                if (history["status"].asJsonObject["block_time"] != null) {
                                    dpTimeToYear(history["status"].asJsonObject["block_time"].asLong * 1000)
                                } else {
                                    "Mempool"
                                }
                            result.add(Pair(headerDate, history))
                        }
                        _majorHistoryResult.postValue(result)

                    } else {
                        if (historyResult is NetworkResult.Error) {
                            _errorMessage.postValue("error type : ${historyResult.errorType}  error message : ${historyResult.errorMessage}")

                        } else if (blockHeightResult is NetworkResult.Error) {
                            _errorMessage.postValue("error type : ${blockHeightResult.errorType}  error message : ${blockHeightResult.errorMessage}")
                        }
                    }

                } catch (_: Exception) {

                }
            }
        }

    fun moveHistory(chain: BaseChain) =
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = historyRepository.moveHistory(chain)) {
                is NetworkResult.Success -> {
                    val result: MutableList<Pair<String, JsonObject>> = mutableListOf()
                    response.data?.let { historys ->
                        historys.reversed().forEach { history ->
                            val headerDate =
                                dpMicroTimeToYear(history["timestamp"].asString.toLong())
                            result.add(Pair(headerDate, history))
                        }
                    }
                    _majorHistoryResult.postValue(result)
                }

                is NetworkResult.Error -> {
                    _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                }
            }
        }
}