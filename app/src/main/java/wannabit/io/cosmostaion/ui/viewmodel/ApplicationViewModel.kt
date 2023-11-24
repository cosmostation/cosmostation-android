package wannabit.io.cosmostaion.ui.viewmodel

import SingleLiveEvent
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.ui.main.CosmostationApp

class ApplicationViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        val shared
            get() = CosmostationApp.instance.applicationViewModel
    }

    private var _currentAccountResult = MutableLiveData<BaseAccount?>()
    val currentAccountResult: LiveData<BaseAccount?> get() = _currentAccountResult
    fun currentAccount(baseAccount: BaseAccount?) = viewModelScope.launch(Dispatchers.IO) {
        _currentAccountResult.postValue(baseAccount)
    }


    val checkPwMnemonicResult = SingleLiveEvent<String>()
    fun checkPwMnemonic() = viewModelScope.launch {
        checkPwMnemonicResult.call()
    }


    val checkPwPrivateResult = SingleLiveEvent<String>()
    fun checkPwPrivate() = viewModelScope.launch {
        checkPwPrivateResult.call()
    }


    var txRecreateResult = SingleLiveEvent<Boolean>()
    fun txRecreate() = viewModelScope.launch(Dispatchers.IO) {
        txRecreateResult.postValue(true)
    }


    var walletEditResult = SingleLiveEvent<Boolean>()
    fun walletEdit() = viewModelScope.launch(Dispatchers.IO) {
        walletEditResult.postValue(true)
    }
}