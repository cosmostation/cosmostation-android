package wannabit.io.cosmostaion.ui.viewmodel.tx

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import wannabit.io.cosmostaion.data.repository.tx.TxRepository

class TxViewModelProviderFactory(
    private val txRepository: TxRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TxViewModel::class.java)) {
            return TxViewModel(txRepository) as T
        }
        throw java.lang.IllegalArgumentException("ViewModel class not found")
    }
}