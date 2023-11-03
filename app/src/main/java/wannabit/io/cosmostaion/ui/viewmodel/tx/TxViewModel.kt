package wannabit.io.cosmostaion.ui.viewmodel.tx

import SingleLiveEvent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cosmos.bank.v1beta1.TxProto.MsgSend
import com.cosmos.base.abci.v1beta1.AbciProto
import com.cosmos.distribution.v1beta1.DistributionProto.DelegationDelegatorReward
import com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddress
import com.cosmos.staking.v1beta1.TxProto.MsgBeginRedelegate
import com.cosmos.staking.v1beta1.TxProto.MsgDelegate
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
import wannabit.io.cosmostaion.data.repository.tx.TxRepository

class TxViewModel(private val txRepository: TxRepository) : ViewModel() {

    var nameServices = SingleLiveEvent<MutableList<NameService>>()
    fun icnsAddress(userInput: String, prefix: String) = CoroutineScope(Dispatchers.IO).launch {
        val nameServiceList = mutableListOf<NameService>()
        val osIcsnResponse = async { txRepository.osIcnsAddress(getChannel(ChainOsmosis()), userInput, prefix) }

        val responses = listOf(osIcsnResponse).awaitAll()

        responses[0]?.let { icnsAddress ->
            if (icnsAddress.isNotEmpty()) nameServiceList.add(NameService(NameService.NameServiceType.ICNS, userInput, icnsAddress))
        }
        nameServices.postValue(nameServiceList)
    }


    val errorMessage = SingleLiveEvent<String>()

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
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastSendTx(
                managedChannel,
                it,
                msgSend,
                fee,
                memo,
                selectedChain)
            _broadcastTx.postValue(response?.txResponse)
        }
    }

    val simulate = SingleLiveEvent<AbciProto.GasInfo>()
    fun simulateSend(
        managedChannel: ManagedChannel?,
        address: String?,
        msgSend: MsgSend?,
        fee: Fee?,
        memo: String
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateSendTx(managedChannel, it, msgSend, fee, memo) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateSendTx(managedChannel, it, msgSend, fee, memo) as String
                errorMessage.postValue(errorResponse)
            }
        }
    }

    fun broadDelegate(
        managedChannel: ManagedChannel?,
        address: String?,
        msgDelegate: MsgDelegate?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastDelegateTx(
                managedChannel,
                it,
                msgDelegate,
                fee,
                memo,
                selectedChain
            )
            _broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateDelegate(
        managedChannel: ManagedChannel?,
        address: String?,
        msgDelegate: MsgDelegate,
        fee: Fee?,
        memo: String
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateDelegateTx(managedChannel, it, msgDelegate, fee, memo) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateDelegateTx(managedChannel, it, msgDelegate, fee, memo) as String
                errorMessage.postValue(errorResponse)
            }
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
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastUnDelegateTx(
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
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateUnDelegateTx(managedChannel, it, msgUnDelegate, fee, memo) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateUnDelegateTx(managedChannel, it, msgUnDelegate, fee, memo) as String
                errorMessage.postValue(errorResponse)
            }
        }
    }

    fun broadReDelegate(
        managedChannel: ManagedChannel?,
        address: String?,
        msgReDelegate: MsgBeginRedelegate?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastReDelegateTx(
                managedChannel,
                it,
                msgReDelegate,
                fee,
                memo,
                selectedChain
            )
            _broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateReDelegate(
        managedChannel: ManagedChannel?,
        address: String?,
        msgReDelegate: MsgBeginRedelegate?,
        fee: Fee?,
        memo: String
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateReDelegateTx(managedChannel, it, msgReDelegate, fee, memo) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateReDelegateTx(managedChannel, it, msgReDelegate, fee, memo) as String
                errorMessage.postValue(errorResponse)
            }
        }
    }

    fun broadGetRewards(
        managedChannel: ManagedChannel?,
        address: String?,
        rewards: MutableList<DelegationDelegatorReward?>,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastGetRewardsTx(
                managedChannel,
                it,
                rewards,
                fee,
                memo,
                selectedChain
            )
            _broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateGetRewards(
        managedChannel: ManagedChannel?,
        address: String?,
        rewards: MutableList<DelegationDelegatorReward?>,
        fee: Fee?,
        memo: String
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateGetRewardsTx(managedChannel, it, rewards, fee, memo) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateGetRewardsTx(managedChannel, it, rewards, fee, memo) as String
                errorMessage.postValue(errorResponse)
            }
        }
    }

    fun broadCompounding(
        managedChannel: ManagedChannel?,
        address: String?,
        rewards: MutableList<DelegationDelegatorReward?>,
        stakingDenom: String?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastCompoundingTx(
                managedChannel,
                it,
                rewards,
                stakingDenom,
                fee,
                memo,
                selectedChain
            )
            _broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateCompounding(
        managedChannel: ManagedChannel?,
        address: String?,
        rewards: MutableList<DelegationDelegatorReward?>,
        stakingDenom: String?,
        fee: Fee?,
        memo: String
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateCompoundingTx(managedChannel, it, rewards, stakingDenom, fee, memo) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateCompoundingTx(managedChannel, it, rewards, stakingDenom, fee, memo) as String
                errorMessage.postValue(errorResponse)
            }
        }
    }

    fun broadChangeRewardAddress(
        managedChannel: ManagedChannel?,
        address: String?,
        msgSetWithdrawAddress: MsgSetWithdrawAddress?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastChangeRewardAddressTx(
                managedChannel,
                it,
                msgSetWithdrawAddress,
                fee,
                memo,
                selectedChain
            )
            _broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateChangeRewardAddress(
        managedChannel: ManagedChannel?,
        address: String?,
        msgSetWithdrawAddress: MsgSetWithdrawAddress?,
        fee: Fee?,
        memo: String
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateChangeRewardAddressTx(managedChannel, it, msgSetWithdrawAddress, fee, memo) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateChangeRewardAddressTx(managedChannel, it, msgSetWithdrawAddress, fee, memo) as String
                Log.e("Test1234 : ", errorResponse)
                errorMessage.postValue(errorResponse)
            }
        }
    }

    private fun getChannel(line: CosmosLine): ManagedChannel {
        return ManagedChannelBuilder.forAddress(line.grpcHost, line.grpcPort).useTransportSecurity().build()
    }
}