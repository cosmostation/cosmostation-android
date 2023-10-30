package wannabit.io.cosmostaion.ui.viewmodel.tx

import SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cosmos.bank.v1beta1.TxProto.MsgSend
import com.cosmos.base.abci.v1beta1.AbciProto
import com.cosmos.staking.v1beta1.TxProto.MsgUndelegate
import com.cosmos.tx.v1beta1.ServiceProto.SimulateResponse
import com.cosmos.tx.v1beta1.TxProto.Fee
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOsmosis
import wannabit.io.cosmostaion.data.model.res.NameService
import wannabit.io.cosmostaion.data.repository.tx.SendRepository

class SendViewModel(private val sendRepository: SendRepository) : ViewModel() {

    var nameServices = SingleLiveEvent<MutableList<NameService>>()
    fun icnsAddress(userInput: String, prefix: String) = CoroutineScope(Dispatchers.IO).launch {
        val nameServiceList = mutableListOf<NameService>()
        val osIcsnResponse = async { sendRepository.osIcnsAddress(getChannel(ChainOsmosis()), userInput, prefix) }

        val responses = listOf(osIcsnResponse).awaitAll()

        responses[0]?.let { icnsAddress ->
            if (icnsAddress.isNotEmpty()) nameServiceList.add(NameService(NameService.NameServiceType.ICNS, userInput, icnsAddress))
        }
        nameServices.postValue(nameServiceList)
    }


    val errorMessage = SingleLiveEvent<String>()

    val simulate = SingleLiveEvent<AbciProto.GasInfo>()
    fun simulateSend(
        managedChannel: ManagedChannel?,
        address: String?,
        msgSend: MsgSend?,
        fee: Fee?,
        memo: String
    ) = CoroutineScope(Dispatchers.IO).launch {
        sendRepository.auth(managedChannel, address)?.let {
            try {
                val response = sendRepository.simulateSendTx(managedChannel, it, msgSend, fee, memo) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = sendRepository.simulateSendTx(managedChannel, it, msgSend, fee, memo) as String
                errorMessage.postValue(errorResponse)
            }
        }
    }


    private val _broadcastTx = MutableLiveData<AbciProto.TxResponse>()
    val broadcastTx: LiveData<AbciProto.TxResponse> get() = _broadcastTx

    fun broadcastSend(
        managedChannel: ManagedChannel?,
        address: String?,
        msgSend: MsgSend?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = CoroutineScope(Dispatchers.IO).launch {
        sendRepository.auth(managedChannel, address)?.let {
            val response = sendRepository.broadcastSendTx(
                managedChannel,
                it,
                msgSend,
                fee,
                memo,
                selectedChain)
            _broadcastTx.postValue(response?.txResponse)
        }
    }

    fun broadUnDelegate(
        managedChannel: ManagedChannel?,
        address: String?,
        msgUnDelegate: MsgUndelegate?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = CoroutineScope(Dispatchers.IO).launch {
        sendRepository.auth(managedChannel, address)?.let {
            val response = sendRepository.broadcastUnDelegateTx(
                managedChannel,
                it,
                msgUnDelegate,
                fee,
                memo,
                selectedChain
            )
            _broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateUnDelegate(
        managedChannel: ManagedChannel?,
        address: String?,
        msgUnDelegate: MsgUndelegate?,
        fee: Fee?,
        memo: String
    ) = CoroutineScope(Dispatchers.IO).launch {
        sendRepository.auth(managedChannel, address)?.let {
            try {
                val response = sendRepository.simulateUnDelegateTx(managedChannel, it, msgUnDelegate, fee, memo) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = sendRepository.simulateUnDelegateTx(managedChannel, it, msgUnDelegate, fee, memo) as String
                errorMessage.postValue(errorResponse)
            }
        }
    }

    private fun getChannel(line: CosmosLine): ManagedChannel {
        return ManagedChannelBuilder.forAddress(line.grpcHost, line.grpcPort).useTransportSecurity().build()
    }
}