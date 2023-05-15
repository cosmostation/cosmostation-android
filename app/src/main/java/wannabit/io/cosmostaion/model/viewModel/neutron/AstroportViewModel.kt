package wannabit.io.cosmostaion.model.viewModel.neutron

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.model.repository.neutron.AstroportRepository
import wannabit.io.cosmostaion.model.viewModel.BaseViewModel
import wannabit.io.cosmostaion.network.res.neutron.Pair
import wannabit.io.cosmostaion.network.res.neutron.ResPairData
import wannabit.io.cosmostaion.network.res.neutron.ResSwapRateData

class AstroportViewModel(private val astroportRepository: AstroportRepository) : BaseViewModel() {

    private var _swapPairData = MutableLiveData<ArrayList<ResPairData>>()
    val swapPairData: LiveData<ArrayList<ResPairData>> get() = _swapPairData

    fun loadSwapPairData(c: Context, chainConfig: ChainConfig) = backScope.launch {
        try {
            astroportRepository.getSwapPairData(c, chainConfig).let { response ->
                if (response.isSuccessful) {
                    _swapPairData.postValue(response.body())
                }
            }
        } catch (_: Exception) { }
    }

    private var _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> get() = _loading

    fun resetSwapRateDate() {
        _loading.postValue(true)
    }

    private var _swapRateData = MutableLiveData<ResSwapRateData?>()
    val swapRateData: LiveData<ResSwapRateData?> get() = _swapRateData

    fun loadSwapRateData(chainConfig: ChainConfig, inputCoin: Pair?, inputAmount: String, outputCoin: Pair?, contractAddress: String) = backScope.launch {
        try {
            astroportRepository.getSwapRateData(chainConfig, inputCoin, inputAmount, outputCoin, contractAddress)?.let { response ->
                if (response.isNotEmpty()) {
                    _swapRateData.postValue(Gson().fromJson(response, ResSwapRateData::class.java))
                } else {
                    _swapRateData.postValue(null)
                }
            }
        } catch (_: Exception) {
            _swapRateData.postValue(null)
        } finally {
            _loading.postValue(false)
        }
    }
}