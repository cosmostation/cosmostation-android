package wannabit.io.cosmostaion.model.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.protobuf.ByteString
import cosmos.base.abci.v1beta1.Abci
import cosmos.tx.v1beta1.ServiceGrpc
import cosmos.tx.v1beta1.ServiceOuterClass
import cosmwasm.wasm.v1.QueryGrpc
import cosmwasm.wasm.v1.QueryOuterClass
import kotlinx.coroutines.*
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.network.ChannelBuilder
import java.util.concurrent.TimeUnit

open class BaseViewModel : ViewModel() {
    private val job = SupervisorJob()
    protected val scope = CoroutineScope(Dispatchers.Main + job)

    private val backJob = Job()
    protected val backScope = CoroutineScope(Dispatchers.IO + backJob)

    private var _txResponse = MutableLiveData<Abci.TxResponse>()
    val txResponse: LiveData<Abci.TxResponse> get() = _txResponse

    fun broadCastTx(baseChain: BaseChain, broadcastTxRequest: ServiceOuterClass.BroadcastTxRequest) = backScope.launch {
        val txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(baseChain))
        val response = txService.broadcastTx(broadcastTxRequest)
        _txResponse.postValue(response.txResponse)
    }

    // contract query
    fun getData(req: Any?, chainConfig: ChainConfig, contractAddress: String?): String? {
        try {
            val jsonData = Gson().toJson(req)
            val queryData = ByteString.copyFromUtf8(jsonData)

            val mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(chainConfig.baseChain())).withDeadlineAfter(ChannelBuilder.TIME_OUT.toLong(), TimeUnit.SECONDS)
            val request = QueryOuterClass.QuerySmartContractStateRequest.newBuilder().setAddress(contractAddress).setQueryData(queryData).build()

            mStub.smartContractState(request).apply {
                return this.data.toStringUtf8()
            }
        } catch (_: Exception) { }
        return null
    }

    override fun onCleared() {
        job.cancel()
        backJob.cancel()
        super.onCleared()
    }
}