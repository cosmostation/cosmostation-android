package wannabit.io.cosmostaion.ui.viewmodel.chain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import wannabit.io.cosmostaion.data.repository.chain.HistoryRepository

class HistoryViewModelProviderFactory(
    private val historyRepository: HistoryRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            return HistoryViewModel(historyRepository) as T
        }
        throw java.lang.IllegalArgumentException("ViewModel class not found")
    }
}