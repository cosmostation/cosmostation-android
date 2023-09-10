package wannabit.io.cosmostaion.ui.viewmodel.chain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.data.model.CosmosHistory
import wannabit.io.cosmostaion.data.model.NetworkResult
import wannabit.io.cosmostaion.data.repository.chain.HistoryRepository

class HistoryViewModel(private val historyRepository: HistoryRepository) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private var _historyResult = MutableLiveData<NetworkResult<List<CosmosHistory>>>()
    val historyResult: LiveData<NetworkResult<List<CosmosHistory>>> get() = _historyResult

    fun history(chain: String, address: String?, limit: String, id: Int) = viewModelScope.launch(Dispatchers.IO) {
        when (val response = historyRepository.cosmosHistory(chain, address, limit, id)) {
            is NetworkResult.Success -> {
                response.data.let { data ->

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