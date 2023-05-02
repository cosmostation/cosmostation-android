package wannabit.io.cosmostaion.model.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cosmos.base.abci.v1beta1.Abci
import cosmos.tx.v1beta1.ServiceGrpc
import cosmos.tx.v1beta1.ServiceOuterClass.BroadcastTxRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pstake.lscosmos.v1beta1.QueryOuterClass
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.network.ChannelBuilder
import java.util.concurrent.TimeUnit

class PersisViewModel : BaseViewModel() {
    private var _cValue = MutableLiveData<String>()
    val cValue: LiveData<String> get() = _cValue

    fun loadCValue(baseChain: BaseChain) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val mStub = pstake.lscosmos.v1beta1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(baseChain)).withDeadlineAfter(ChannelBuilder.TIME_OUT.toLong(), TimeUnit.SECONDS)
            val request = QueryOuterClass.QueryCValueRequest.newBuilder().build()
            val response = mStub.cValue(request)
            _cValue.postValue(response.cValue)
        } catch (_: Exception) {
            _cValue.postValue("")
        }
    }
}