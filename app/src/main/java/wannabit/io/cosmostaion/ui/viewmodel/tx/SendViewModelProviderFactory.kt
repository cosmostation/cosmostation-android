package wannabit.io.cosmostaion.ui.viewmodel.tx

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import wannabit.io.cosmostaion.data.repository.tx.SendRepository

class SendViewModelProviderFactory(
    private val txRepository: SendRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SendViewModel::class.java)) {
            return SendViewModel(txRepository) as T
        }
        throw java.lang.IllegalArgumentException("ViewModel class not found")
    }
}