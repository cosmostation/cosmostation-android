package wannabit.io.cosmostaion.ui.viewmodel.intro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.data.model.AppVersion
import wannabit.io.cosmostaion.data.model.NetworkResult
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepository
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.CryptoHelper

class WalletViewModel(private val walletRepository: WalletRepository) : ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage


    private var _walletAppVersionResult = MutableLiveData<AppVersion>()
    val walletAppVersionResult: LiveData<AppVersion> get() = _walletAppVersionResult

    fun walletAppVersion() = viewModelScope.launch(Dispatchers.IO) {
        when (val response = walletRepository.version()) {
            is NetworkResult.Success -> {
                response.data.let { data ->
                    if (data.isSuccessful) {
                        _walletAppVersionResult.postValue(data.body())
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

    private var _walletPriceResult = MutableLiveData<String>()
    val walletPriceResult: LiveData<String> get() = _walletPriceResult

    fun price(currency: String, force: Boolean? = false) = viewModelScope.launch(Dispatchers.IO) {
        if (!BaseData.priceUpdateIfNeed() && force == false) {
            return@launch
        }
        when (val response = walletRepository.price(currency)) {
            is NetworkResult.Success -> {
                response.data.let { data ->
                    if (data.isSuccessful) {
                        BaseData.prices = data.body()
                        BaseData.setLastPriceTime()
                        BaseData.baseAccount?.updateAllValue()

                        _walletPriceResult.postValue(BaseConstant.SUCCESS)
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

    private var _changeResult = MutableLiveData<String>()
    val changeResult: LiveData<String> get() = _changeResult

    fun changeObserve() = viewModelScope.launch(Dispatchers.IO) {
        _changeResult.postValue(BaseConstant.SUCCESS)
    }

    private var _pwCheckResult = MutableLiveData<String>()
    val pwCheckResult: LiveData<String> get() = _pwCheckResult

    fun checkPassword(input: String) = viewModelScope.launch(Dispatchers.IO) {
        val checkPw = AppDatabase.getInstance().passwordDao().selectAll()
        checkPw.forEach { password ->
            if (!CryptoHelper.verifyData(input, password.resource, CosmostationConstants.ENCRYPT_PASSWORD_KEY)) {
                _pwCheckResult.postValue(BaseConstant.FAIL)
            } else {
                _pwCheckResult.postValue(BaseConstant.SUCCESS)
            }
        }
    }
}