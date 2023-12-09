package wannabit.io.cosmostaion.ui.viewmodel.skip

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import wannabit.io.cosmostaion.data.repository.skip.SkipRepository

class SkipViewModelProviderFactory(
    private val skipRepository: SkipRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SkipViewModel::class.java)) {
            return SkipViewModel(skipRepository) as T
        }
        throw java.lang.IllegalArgumentException("ViewModel class not found")
    }
}