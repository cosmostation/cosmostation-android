package wannabit.io.cosmostaion.model.viewModel.neutron

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.model.NetworkResult
import wannabit.io.cosmostaion.model.repository.neutron.AstroportRepository
import wannabit.io.cosmostaion.model.viewModel.BaseViewModel
import wannabit.io.cosmostaion.network.res.neutron.Pair
import wannabit.io.cosmostaion.network.res.neutron.ResPairData
import wannabit.io.cosmostaion.network.res.neutron.ResSwapRateData
import javax.inject.Inject

@HiltViewModel
class AstroportViewModel @Inject constructor(private val astroportRepository: AstroportRepository) : BaseViewModel() {

    private var _swapPairData = MutableLiveData<NetworkResult<ArrayList<ResPairData>?>>()
    val swapPairData: LiveData<NetworkResult<ArrayList<ResPairData>?>> get() = _swapPairData

    fun loadSwapPairData(chainConfig: ChainConfig) = backScope.launch {
        try {
            astroportRepository.getSwapPairData(chainConfig).let { response ->
                if (response.isSuccessful) {
                    _swapPairData.postValue(NetworkResult.Success(response.body()))
                } else {
                    _swapPairData.postValue(NetworkResult.Error("Error"))
                }
            }
        } catch (e: Exception) {
            _swapPairData.postValue(NetworkResult.Error(e.message, e.cause))
        }
    }

    private var _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> get() = _loading

    fun resetSwapRateDate() {
        _loading.postValue(true)
    }

    private var _swapRateData = MutableLiveData<NetworkResult<ResSwapRateData?>>()
    val swapRateData: LiveData<NetworkResult<ResSwapRateData?>> get() = _swapRateData

    fun loadSwapRateData(chainConfig: ChainConfig, inputCoin: Pair?, inputAmount: String, outputCoin: Pair?, contractAddress: String) = backScope.launch {
        try {
            astroportRepository.getSwapRateData(chainConfig, inputCoin, inputAmount, outputCoin, contractAddress)?.let { response ->
                if (response.isNotEmpty()) {
                    _swapRateData.postValue(NetworkResult.Success(Gson().fromJson(response, ResSwapRateData::class.java)))
                } else {
                    _swapRateData.postValue(NetworkResult.Error("Error"))
                }
            }
        } catch (e: Exception) {
            _swapRateData.postValue(NetworkResult.Error(e.message, e.cause))
        } finally {
            _loading.postValue(false)
        }
    }
}