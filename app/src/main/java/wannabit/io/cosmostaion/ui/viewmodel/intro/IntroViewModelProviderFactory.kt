package wannabit.io.cosmostaion.ui.viewmodel.intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import wannabit.io.cosmostaion.data.repository.wallet.IntroRepository

class IntroViewModelProviderFactory(
    private val walletRepository: IntroRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IntroViewModel::class.java)) {
            return IntroViewModel(walletRepository) as T
        }
        throw java.lang.IllegalArgumentException("ViewModel class not found")
    }
}