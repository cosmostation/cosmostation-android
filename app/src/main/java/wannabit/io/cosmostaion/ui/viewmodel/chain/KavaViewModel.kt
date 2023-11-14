package wannabit.io.cosmostaion.ui.viewmodel.chain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kava.cdp.v1beta1.QueryProto.QueryCdpsResponse
import com.kava.cdp.v1beta1.QueryProto.QueryParamsResponse
import com.kava.incentive.v1beta1.QueryProto
import com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse
import io.grpc.ManagedChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.repository.chain.KavaRepository

class KavaViewModel(private val kavaRepository: KavaRepository) : ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private var _incentiveResult = MutableLiveData<QueryProto.QueryRewardsResponse?>()
    val incentiveResult: LiveData<QueryProto.QueryRewardsResponse?> get() = _incentiveResult
    fun incentive(managedChannel: ManagedChannel, address: String?) = CoroutineScope(Dispatchers.IO).launch {
        when (val response = kavaRepository.incentive(managedChannel, address)) {
            is NetworkResult.Success -> {
                _incentiveResult.postValue(response.data)
            }

            is NetworkResult.Error -> {
                _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }

    private var _priceFeedResult = MutableLiveData<QueryPricesResponse?>()
    val priceFeedResult: LiveData<QueryPricesResponse?> get() = _priceFeedResult
    fun priceFeed(managedChannel: ManagedChannel) = CoroutineScope(Dispatchers.IO).launch {
        when (val response = kavaRepository.priceFeed(managedChannel)) {
            is NetworkResult.Success -> {
                _priceFeedResult.postValue(response.data)
            }

            is NetworkResult.Error -> {
                _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }

    private var _mintParamResult = MutableLiveData<QueryParamsResponse?>()
    val mintParamResult: LiveData<QueryParamsResponse?> get() = _mintParamResult
    fun mintParam(managedChannel: ManagedChannel) = CoroutineScope(Dispatchers.IO).launch {
        when (val response = kavaRepository.mintParam(managedChannel)) {
            is NetworkResult.Success -> {
                _mintParamResult.postValue(response.data)
            }

            is NetworkResult.Error -> {
                _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }

    private var _myCdpResult = MutableLiveData<QueryCdpsResponse?>()
    val myCdpResult: LiveData<QueryCdpsResponse?> get() = _myCdpResult
    fun myCdp(managedChannel: ManagedChannel, address: String?) = CoroutineScope(Dispatchers.IO).launch {
        when (val response = kavaRepository.myCdp(managedChannel, address)) {
            is NetworkResult.Success -> {
                _myCdpResult.postValue(response.data)
            }

            is NetworkResult.Error -> {
                _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }

//    fun history(context: Context, chain: String, address: String?, limit: String, searchId: Int?) =
//        CoroutineScope(
//            Dispatchers.IO
//        ).launch {
//            when (val response = historyRepository.cosmosHistory(chain, address, limit, searchId)) {
//                is NetworkResult.Success -> {
//                    response.data.let { data ->
//                        if (data.isSuccessful) {
//                            val result: MutableList<Pair<String, CosmosHistory>> = mutableListOf()
//                            data.body()?.forEach { history ->
//                                history.header?.let {
//                                    val headerDate = formatTxTimeToYear(context, it.timestamp)
//                                    result.add(Pair(headerDate, history))
//                                }
//                            }
//                            _historyResult.postValue(result)
//
//                        } else {
//                            _errorMessage.postValue("Error")
//                        }
//                    }
//                }
//
//                is NetworkResult.Error -> {
//                    _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
//                }
//            }
//        }
}