package wannabit.io.cosmostaion.model.viewModel.persistence

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pstake.lscosmos.v1beta1.QueryOuterClass
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.model.NetworkResult
import wannabit.io.cosmostaion.model.viewModel.BaseViewModel
import wannabit.io.cosmostaion.network.ChannelBuilder
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class PersisViewModel @Inject constructor(): BaseViewModel() {
    private var _cValue = MutableLiveData<NetworkResult<String>>()
    val cValue: LiveData<NetworkResult<String>> get() = _cValue

    fun loadCValue(baseChain: BaseChain) = backScope.launch {
        try {
            val mStub = pstake.lscosmos.v1beta1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(baseChain)).withDeadlineAfter(ChannelBuilder.TIME_OUT.toLong(), TimeUnit.SECONDS)
            val request = QueryOuterClass.QueryCValueRequest.newBuilder().build()
            val response = mStub.cValue(request)
            _cValue.postValue(NetworkResult.Success(response.cValue))
        } catch (e: Exception) {
            _cValue.postValue(NetworkResult.Error(e.message, e.cause))
        }
    }
}