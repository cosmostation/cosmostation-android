package wannabit.io.cosmostaion.data.viewmodel.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.data.repository.account.AccountRepository
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.ui.main.setting.general.PushManager
import wannabit.io.cosmostaion.data.viewmodel.event.SingleLiveEvent

class AccountViewModel(private val accountRepository: AccountRepository) : ViewModel() {

    private var _baseAccounts = MutableLiveData<List<BaseAccount>>()
    val baseAccounts: LiveData<List<BaseAccount>> get() = _baseAccounts

    fun insertAccount(baseAccount: BaseAccount) = CoroutineScope(Dispatchers.IO).launch {
        accountRepository.insertAccount(baseAccount)
    }

    fun deleteAccount(baseAccount: BaseAccount) = CoroutineScope(Dispatchers.IO).launch {
        accountRepository.deleteAccount(baseAccount)
        AppDatabase.getInstance().refAddressDao().delete(baseAccount.id)
        PushManager.updateStatus(Prefs.alarmEnable) { _, _ -> }
        _baseAccounts.postValue(AppDatabase.getInstance().baseAccountDao().selectAll())
    }

    val create = SingleLiveEvent<Any>()
    fun createByMnemonic(name: String, mnemonic: String, lastHDPath: String) =
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                accountRepository.createByMnemonic(name, mnemonic, lastHDPath)
            }
            create.call()
        }

    fun createByPrivate(name: String, privateKey: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            accountRepository.createByPrivate(name, privateKey)
        }
        create.call()
    }

}