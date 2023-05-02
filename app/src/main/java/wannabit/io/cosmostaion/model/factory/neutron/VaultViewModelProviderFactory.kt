package wannabit.io.cosmostaion.model.factory.neutron

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import wannabit.io.cosmostaion.model.repository.neutron.VaultRepository
import wannabit.io.cosmostaion.model.viewModel.neutron.VaultViewModel

class VaultViewModelProviderFactory(
    private val vaultRepository: VaultRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VaultViewModel::class.java)) {
            return VaultViewModel(vaultRepository) as T
        }
        throw java.lang.IllegalArgumentException("ViewModel class not found")
    }
}