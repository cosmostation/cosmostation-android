package wannabit.io.cosmostaion.fragment.txs.liquidstaking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cosmos.base.abci.v1beta1.Abci
import cosmos.tx.v1beta1.ServiceGrpc
import cosmos.tx.v1beta1.ServiceOuterClass.BroadcastTxRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pstake.lscosmos.v1beta1.QueryOuterClass
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.model.BaseViewModel
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

    private var _txResponse = MutableLiveData<Abci.TxResponse>()
    val txResponse: LiveData<Abci.TxResponse> get() = _txResponse

    fun broadCastTx(baseChain: BaseChain, broadcastTxRequest: BroadcastTxRequest) = CoroutineScope(Dispatchers.IO).launch {
        val txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(baseChain))
        val response = txService.broadcastTx(broadcastTxRequest)
        _txResponse.postValue(response.txResponse)
    }
}