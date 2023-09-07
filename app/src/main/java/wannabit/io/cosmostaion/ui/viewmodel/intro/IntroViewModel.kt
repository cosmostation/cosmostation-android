package wannabit.io.cosmostaion.ui.viewmodel.intro

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.data.model.AppVersion
import wannabit.io.cosmostaion.data.model.NetworkResult
import wannabit.io.cosmostaion.data.repository.wallet.IntroRepository
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class IntroViewModel(private val walletRepository: IntroRepository) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage


    private var _wallAppVersionResult = MutableLiveData<AppVersion>()
    val wallAppVersionResult: LiveData<AppVersion> get() = _wallAppVersionResult

    fun walletAppVersion() = viewModelScope.launch(Dispatchers.IO) {
        when (val response = walletRepository.version()) {
            is NetworkResult.Success -> {
                response.data.let { data ->
                    if (data.isSuccessful) {
                        _wallAppVersionResult.postValue(data.body())
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

    fun price() = viewModelScope.launch(Dispatchers.IO) {
        if (!BaseData.priceUpdateIfNeed()) {
            return@launch
        }
        when (val response = walletRepository.price()) {
            is NetworkResult.Success -> {
                response.data.let { data ->
                    if (data.isSuccessful) {
                        BaseData.prices = data.body()
                        BaseData.setLastPriceTime()
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

    fun asset() = viewModelScope.launch(Dispatchers.IO) {
        when (val response = walletRepository.asset()) {
            is NetworkResult.Success -> {
                response.data.let { data ->
                    if (data.isSuccessful) {
                        BaseData.assets = data.body()?.assets
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