package wannabit.io.cosmostaion.ui.viewmodel.skip

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.data.model.req.SkipMsgReq
import wannabit.io.cosmostaion.data.model.req.SkipRouteReq
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.SkipChainResponse
import wannabit.io.cosmostaion.data.model.res.SkipErrorResponse
import wannabit.io.cosmostaion.data.model.res.SkipMsgResponse
import wannabit.io.cosmostaion.data.model.res.SkipRouteResponse
import wannabit.io.cosmostaion.data.repository.skip.SkipRepository

class SkipViewModel(private val skipRepository: SkipRepository) : ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private var _skipChainsResult = MutableLiveData<SkipChainResponse?>()
    val skipChainsResult: LiveData<SkipChainResponse?> get() = _skipChainsResult
    fun skipChains() = viewModelScope.launch(Dispatchers.IO) {
        when (val response = skipRepository.skipChains()) {
            is NetworkResult.Success -> {
                _skipChainsResult.postValue(response.data)
            }

            is NetworkResult.Error -> {
                _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }

    suspend fun skipAssets(chain: BaseChain?): JsonObject? {
        return when (val response = skipRepository.skipAssets(chain)) {
            is NetworkResult.Success -> {
                response.data
            }

            is NetworkResult.Error -> {
                null
            }
        }
    }

    private var _skipRouteResult = MutableLiveData<SkipRouteResponse>()
    val skipRouteResult: LiveData<SkipRouteResponse> get() = _skipRouteResult

    private var _skipRouteErrorResult = MutableLiveData<SkipErrorResponse>()
    val skipRouteErrorResult: LiveData<SkipErrorResponse> get() = _skipRouteErrorResult

    fun skipRoute(req: SkipRouteReq) = viewModelScope.launch(Dispatchers.IO) {
        when (val response = skipRepository.skipRoute(req)) {
            is NetworkResult.Success -> {
                if (response.data.isSuccessful) {
                    _skipRouteResult.postValue(response.data.body())
                } else {
                    val errorResponse = Gson().fromJson(
                        response.data.errorBody()?.string(), SkipErrorResponse::class.java
                    )
                    _skipRouteErrorResult.postValue(errorResponse)
                }
            }

            is NetworkResult.Error -> {
                _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }

    private var _skipMsgResult = MutableLiveData<SkipMsgResponse>()
    val skipMsgResult: LiveData<SkipMsgResponse> get() = _skipMsgResult

    fun skipMsg(req: SkipMsgReq) = viewModelScope.launch(Dispatchers.IO) {
        when (val response = skipRepository.skipMsg(req)) {
            is NetworkResult.Success -> {
                if (response.data.isSuccessful) {
                    _skipMsgResult.postValue(response.data.body())
                } else {
                    val errorResponse = Gson().fromJson(
                        response.data.errorBody()?.string(), SkipErrorResponse::class.java
                    )
                    _skipRouteErrorResult.postValue(errorResponse)
                }
            }

            is NetworkResult.Error -> {
                _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }
}
