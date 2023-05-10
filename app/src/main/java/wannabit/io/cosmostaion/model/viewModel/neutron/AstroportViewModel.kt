package wannabit.io.cosmostaion.model.viewModel.neutron

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.model.repository.neutron.AstroportRepository
import wannabit.io.cosmostaion.model.type.Coin
import wannabit.io.cosmostaion.model.viewModel.BaseViewModel
import wannabit.io.cosmostaion.network.res.neutron.ResPairData
import wannabit.io.cosmostaion.network.res.neutron.ResSwapRateData

class AstroportViewModel(private val astroportRepository: AstroportRepository) : BaseViewModel() {

    private var _swapListData = MutableLiveData<ResPairData?>()
    val swapListData: LiveData<ResPairData?> get() = _swapListData

    fun loadSwapListData(chainConfig: ChainConfig, contractAddress: String?) = backScope.launch {
        try {
            astroportRepository.getSwapPairListData(chainConfig, contractAddress)?.let { response ->
                if (response.isNotEmpty()) {
                    _swapListData.postValue(Gson().fromJson(response, ResPairData::class.java))
                }
            }
        } catch (_: Exception) { }
    }

    private var _swapRateData = MutableLiveData<ResSwapRateData?>()
    val swapRateData: LiveData<ResSwapRateData?> get() = _swapRateData

    fun loadSwapRateData(chainConfig: ChainConfig, offerCoin: Coin, askDenom:String, contractAddress: String?) = backScope.launch {
        try {
            astroportRepository.getSwapRateData(chainConfig, offerCoin, askDenom, contractAddress)?.let { response ->
                if (response.isNotEmpty()) {
                    _swapRateData.postValue(Gson().fromJson(response, ResSwapRateData::class.java))
                }
            }
        } catch (_: Exception) { }
    }
}