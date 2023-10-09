package wannabit.io.cosmostaion.ui.viewmodel.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import wannabit.io.cosmostaion.data.repository.account.AccountRepository

class AccountViewModelProviderFactory(
    private val accountRepository: AccountRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountViewModel::class.java)) {
            return AccountViewModel(accountRepository) as T
        }
        throw java.lang.IllegalArgumentException("ViewModel class not found")
    }
}