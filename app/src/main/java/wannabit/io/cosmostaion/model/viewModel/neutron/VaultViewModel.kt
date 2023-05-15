package wannabit.io.cosmostaion.model.viewModel.neutron

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.model.repository.neutron.VaultRepository
import wannabit.io.cosmostaion.model.viewModel.BaseViewModel

class VaultViewModel(private val vaultRepository: VaultRepository) : BaseViewModel() {

    private var _vaultDepositData = MutableLiveData<String?>()
    val vaultDepositData: LiveData<String?> get() = _vaultDepositData

    fun loadVaultDepositData(chainConfig: ChainConfig, contractAddress: String?) = backScope.launch {
        try {
            vaultRepository.getVaultDepositData(chainConfig, contractAddress)?.let { response ->
                if (response.isNotEmpty()) {
                    _vaultDepositData.postValue(response)
                }
            }
        } catch (_: Exception) { }
    }
}