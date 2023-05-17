package wannabit.io.cosmostaion.model.viewModel.neutron

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.model.NetworkResult
import wannabit.io.cosmostaion.model.repository.neutron.VaultRepository
import wannabit.io.cosmostaion.model.viewModel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class VaultViewModel @Inject constructor(private val vaultRepository: VaultRepository) : BaseViewModel() {

    private var _vaultDepositData = MutableLiveData<NetworkResult<String?>>()
    val vaultDepositData: LiveData<NetworkResult<String?>> get() = _vaultDepositData

    fun loadVaultDepositData(chainConfig: ChainConfig, contractAddress: String?) = backScope.launch {
        try {
            vaultRepository.getVaultDepositData(chainConfig, contractAddress)?.let { response ->
                _vaultDepositData.postValue(NetworkResult.Success(response))
            } ?: run {
                _vaultDepositData.postValue(NetworkResult.Error("Error"))
            }
        } catch (e: Exception) {
            _vaultDepositData.postValue(NetworkResult.Error(e.message, e.cause))
        }
    }
}