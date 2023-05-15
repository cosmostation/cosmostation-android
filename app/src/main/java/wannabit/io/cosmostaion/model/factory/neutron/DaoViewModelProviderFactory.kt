package wannabit.io.cosmostaion.model.factory.neutron

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import wannabit.io.cosmostaion.model.repository.neutron.DaoRepository
import wannabit.io.cosmostaion.model.viewModel.neutron.DaoViewModel

class DaoViewModelProviderFactory(
    private val daoRepository: DaoRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DaoViewModel::class.java)) {
            return DaoViewModel(daoRepository) as T
        }
        throw java.lang.IllegalArgumentException("ViewModel class not found")
    }
}