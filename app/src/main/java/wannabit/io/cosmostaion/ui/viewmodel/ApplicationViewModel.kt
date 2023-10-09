package wannabit.io.cosmostaion.ui.viewmodel

import SingleLiveEvent
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.ui.main.CosmostationApp

class ApplicationViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        val shared
            get() = CosmostationApp.instance.applicationViewModel
    }

    private val _checkPwDeleteResult = MutableLiveData<String>()
    val checkPwDeleteResult: LiveData<String> get() = _checkPwDeleteResult

    fun checkPwDelete() = viewModelScope.launch(Dispatchers.IO) {
        _checkPwDeleteResult.postValue(BaseConstant.SUCCESS)
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