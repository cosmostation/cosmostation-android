package wannabit.io.cosmostaion.ui.viewmodel

import SingleLiveEvent
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.ui.main.CosmostationApp

class ApplicationViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        val shared
            get() = CosmostationApp.instance.applicationViewModel
    }

    private var _currentAccountResult = MutableLiveData<String>()
    val currentAccountResult: LiveData<String> get() = _currentAccountResult
    fun currentAccount() = viewModelScope.launch(Dispatchers.IO) {
        _currentAccountResult.postValue("")
    }


    val checkPwMnemonicResult = SingleLiveEvent<String>()
    fun checkPwMnemonic() = viewModelScope.launch {
        checkPwMnemonicResult.call()
    }

    val checkPwPrivateResult = SingleLiveEvent<String>()

    fun checkPwPrivate() = viewModelScope.launch {
        checkPwPrivateResult.call()
    }
}