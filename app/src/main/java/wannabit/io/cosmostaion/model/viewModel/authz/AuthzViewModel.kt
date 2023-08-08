package wannabit.io.cosmostaion.model.viewModel.authz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cosmos.authz.v1beta1.Authz
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.model.NetworkResult
import wannabit.io.cosmostaion.model.repository.authz.AuthzRepository
import wannabit.io.cosmostaion.model.viewModel.BaseViewModel

class AuthzViewModel(private val authzRepository: AuthzRepository) : BaseViewModel() {

    private var _authzGranterData = MutableLiveData<NetworkResult<MutableList<Authz.GrantAuthorization>>>()
    val authzGranterData: LiveData<NetworkResult<MutableList<Authz.GrantAuthorization>>> get() = _authzGranterData

    fun granterData(chainConfig: ChainConfig, granter: String) = backScope.launch {
        authzRepository.getGranterData(chainConfig, granter).let { response ->
            _authzGranterData.postValue(response)
        }
    }
}