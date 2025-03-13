package wannabit.io.cosmostaion.data.viewmodel.chain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepository

class BabylonViewModelProviderFactory(
    private val walletRepository: WalletRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BabylonViewModel::class.java)) {
            return BabylonViewModel(walletRepository) as T
        }
        throw java.lang.IllegalArgumentException("ViewModel class not found")
    }
}