package wannabit.io.cosmostaion.model.factory.neutron

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import wannabit.io.cosmostaion.model.repository.neutron.AstroportRepository
import wannabit.io.cosmostaion.model.viewModel.neutron.AstroportViewModel

class AstroportViewModelProviderFactory(
    private val astroportRepository: AstroportRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AstroportViewModel::class.java)) {
            return AstroportViewModel(astroportRepository) as T
        }
        throw java.lang.IllegalArgumentException("ViewModel class not found")
    }
}