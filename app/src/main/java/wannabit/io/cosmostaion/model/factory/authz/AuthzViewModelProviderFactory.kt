package wannabit.io.cosmostaion.model.factory.authz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import wannabit.io.cosmostaion.model.repository.authz.AuthzRepository
import wannabit.io.cosmostaion.model.viewModel.authz.AuthzViewModel

class AuthzViewModelProviderFactory(
    private val authzRepository: AuthzRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthzViewModel::class.java)) {
            return AuthzViewModel(authzRepository) as T
        }
        throw java.lang.IllegalArgumentException("ViewModel class not found")
    }
}