package wannabit.io.cosmostaion.model.viewModel.authz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cosmos.authz.v1beta1.Authz
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.model.NetworkResult
import wannabit.io.cosmostaion.model.repository.authz.AuthzRepository
import wannabit.io.cosmostaion.model.type.Coin
import wannabit.io.cosmostaion.model.viewModel.BaseViewModel

class AuthzViewModel(private val authzRepository: AuthzRepository) : BaseViewModel() {

    private var _authzGranterData = MutableLiveData<NetworkResult<MutableList<Authz.GrantAuthorization>>>()
    val authzGranterData: LiveData<NetworkResult<MutableList<Authz.GrantAuthorization>>> get() = _authzGranterData

    fun granterData(chainConfig: ChainConfig, granter: String) = backScope.launch {
        authzRepository.getGranterData(chainConfig, granter).let { response ->
            _authzGranterData.postValue(response)
        }
    }

    private var _authzGranteeData = MutableLiveData<MutableList<Pair<Authz.GrantAuthorization, Coin>>>()
    val authzGranteeData: LiveData<MutableList<Pair<Authz.GrantAuthorization, Coin>>> get() = _authzGranteeData

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun granteeData(chainConfig: ChainConfig, grantee: String) = backScope.launch {
        authzRepository.getGranteeData(chainConfig, grantee).let { grantResponse ->
            when (grantResponse) {
                is NetworkResult.Success -> {
                    if (grantResponse.data.isNotEmpty()) {
                        grantResponse.data.forEach { grant ->
                            authzRepository.getBalanceData(chainConfig, grant.granter).let { balanceResponse ->
                                when (balanceResponse) {
                                    is NetworkResult.Success -> {
                                        _authzGranteeData.postValue(mutableListOf(Pair(grant, balanceResponse.data)))
                                    }

                                    is NetworkResult.Error -> {
                                        _errorMessage.postValue("error type : ${balanceResponse.errorType} error message : ${balanceResponse.errorMessage}")
                                    }
                                }
                            }
                        }
                    } else {
                        _authzGranteeData.postValue(mutableListOf())
                    }
                }

                is NetworkResult.Error -> {
                    _errorMessage.postValue("error type : ${grantResponse.errorType} error message : ${grantResponse.errorMessage}")
                }
            }
        }
    }
}