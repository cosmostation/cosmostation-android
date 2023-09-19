package wannabit.io.cosmostaion.ui.viewmodel.chain

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.common.formatTxTimeToYear
import wannabit.io.cosmostaion.data.model.BnbHistory
import wannabit.io.cosmostaion.data.model.CosmosHistory
import wannabit.io.cosmostaion.data.model.NetworkResult
import wannabit.io.cosmostaion.data.repository.chain.HistoryRepository

class HistoryViewModel(private val historyRepository: HistoryRepository) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private var _historyResult = MutableLiveData<MutableList<Pair<String, CosmosHistory>>>()
    val historyResult: LiveData<MutableList<Pair<String, CosmosHistory>>> get() = _historyResult

    fun history(context: Context, chain: String, address: String?, limit: String, id: Int) = viewModelScope.launch(Dispatchers.IO) {
        when (val response = historyRepository.cosmosHistory(chain, address, limit, id)) {
            is NetworkResult.Success -> {
                response.data.let { data ->
                    if (data.isSuccessful) {
                        val result: MutableList<Pair<String, CosmosHistory>> = mutableListOf()
                        data.body()?.forEach { history ->
                            history.header?.let {
                                val headerDate = formatTxTimeToYear(context, it.timestamp)
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

    private var _bnbHistoryResult = MutableLiveData<MutableList<Pair<String, BnbHistory>>>()
    val bnbHistoryResult: LiveData<MutableList<Pair<String, BnbHistory>>> get() = _bnbHistoryResult

    fun bnbHistory(context: Context, address: String?, startTime: String, endTime: String) = viewModelScope.launch(Dispatchers.IO) {
        when (val response = historyRepository.bnbHistory(address, startTime, endTime)) {
            is NetworkResult.Success -> {
                response.data.let { data ->
                    if (data.isSuccessful) {
                        val result: MutableList<Pair<String, BnbHistory>> = mutableListOf()
                        data.body()?.tx?.forEach { history ->
                            history.timeStamp?.let {
                                val headerDate = formatTxTimeToYear(context, it)
                                result.add(Pair(headerDate, history))
                            }
                        }
                        _bnbHistoryResult.postValue(result)
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

    fun clearDisposables() {
        disposables.clear()
    }
}