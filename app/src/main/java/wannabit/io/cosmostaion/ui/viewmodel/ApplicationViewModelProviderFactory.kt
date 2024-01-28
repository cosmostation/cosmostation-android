package wannabit.io.cosmostaion.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepository

class ApplicationViewModelProviderFactory(
    private val application: Application, private val walletRepository: WalletRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApplicationViewModel::class.java)) {
            return ApplicationViewModel(application, walletRepository) as T
        }
        throw java.lang.IllegalArgumentException("ViewModel class not found")
    }
}