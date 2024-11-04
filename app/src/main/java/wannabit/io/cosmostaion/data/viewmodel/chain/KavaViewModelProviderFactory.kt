package wannabit.io.cosmostaion.data.viewmodel.chain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import wannabit.io.cosmostaion.data.repository.chain.KavaRepository

class KavaViewModelProviderFactory(private val kavaRepository: KavaRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KavaViewModel::class.java)) {
            return KavaViewModel(kavaRepository) as T
        }
        throw java.lang.IllegalArgumentException("ViewModel class not found")
    }
}