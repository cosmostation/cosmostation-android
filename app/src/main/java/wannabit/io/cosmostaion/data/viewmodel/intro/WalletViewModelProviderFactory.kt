package wannabit.io.cosmostaion.data.viewmodel.intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepository

class WalletViewModelProviderFactory(
    private val walletRepository: WalletRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WalletViewModel::class.java)) {
            return WalletViewModel(walletRepository) as T
        }
        throw java.lang.IllegalArgumentException("ViewModel class not found")
    }
}