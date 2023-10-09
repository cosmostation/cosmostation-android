package wannabit.io.cosmostaion.ui.viewmodel.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.data.repository.account.AccountRepository
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.model.BaseAccount

class AccountViewModel(private val accountRepository: AccountRepository) : ViewModel() {

    private var _baseAccounts = MutableLiveData<List<BaseAccount>>()
    val baseAccounts: LiveData<List<BaseAccount>> get() = _baseAccounts

    fun insertAccount(baseAccount: BaseAccount) = CoroutineScope(Dispatchers.IO).launch {
        accountRepository.insertAccount(baseAccount)
        _baseAccounts.postValue(AppDatabase.getInstance().baseAccountDao().selectAll())
    }

    fun deleteAccount(baseAccount: BaseAccount) = CoroutineScope(Dispatchers.IO).launch {
        accountRepository.deleteAccount(baseAccount)
        _baseAccounts.postValue(AppDatabase.getInstance().baseAccountDao().selectAll())
    }
}