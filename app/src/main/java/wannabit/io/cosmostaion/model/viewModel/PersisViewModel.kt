package wannabit.io.cosmostaion.model.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pstake.liquidstakeibc.v1beta1.QueryGrpc
import pstake.liquidstakeibc.v1beta1.QueryOuterClass
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.network.ChannelBuilder
import java.util.concurrent.TimeUnit

class PersisViewModel : BaseViewModel() {
    private var _cValue = MutableLiveData<String>()
    val cValue: LiveData<String> get() = _cValue

    fun loadCValue(baseChain: BaseChain) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(baseChain)).withDeadlineAfter(ChannelBuilder.TIME_OUT.toLong(), TimeUnit.SECONDS)
            val request = QueryOuterClass.QueryExchangeRateRequest.newBuilder().setChainId("cosmoshub-4").build()
            val response = mStub.exchangeRate(request)
            _cValue.postValue(response.rate)
        } catch (_: Exception) {
            _cValue.postValue("")
        }
    }
}