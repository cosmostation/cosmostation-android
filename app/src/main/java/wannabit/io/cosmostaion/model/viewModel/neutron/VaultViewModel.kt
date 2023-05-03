package wannabit.io.cosmostaion.model.viewModel.neutron

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.model.repository.neutron.VaultRepository
import wannabit.io.cosmostaion.model.viewModel.BaseViewModel
import wannabit.io.cosmostaion.network.req.neutron.TotalPower
import wannabit.io.cosmostaion.network.req.neutron.TotalPowerReq
import wannabit.io.cosmostaion.network.req.neutron.VotingPower
import wannabit.io.cosmostaion.network.req.neutron.VotingPowerReq
import wannabit.io.cosmostaion.network.res.neutron.ResVaultData

class VaultViewModel(private val vaultRepository: VaultRepository) : BaseViewModel() {

    private var _vaultListData = MutableLiveData<List<ResVaultData?>?>()
    val vaultListData: LiveData<List<ResVaultData?>?> get() = _vaultListData

    fun loadVaultListData(chainConfig: ChainConfig) = backScope.launch {
        try {
            vaultRepository.getVaultData(chainConfig).let { response ->
                if (response.isSuccessful) {
                    _vaultListData.postValue(response.body())
                } else {
                    _vaultListData.postValue(null)
                }
            }
        } catch (_: Exception) { }
    }

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