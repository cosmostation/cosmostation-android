package wannabit.io.cosmostaion.data.viewmodel.chain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babylon.epoching.v1.QueryProto.QueryCurrentEpochResponse
import com.babylon.epoching.v1.QueryProto.QueuedMessageResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.fetcher.BabylonFetcher
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepository
import java.util.concurrent.TimeUnit

class BabylonViewModel(private val walletRepository: WalletRepository) : ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _epochData = MutableLiveData<BabylonFetcher.BabylonEpochData?>()
    val epochData: LiveData<BabylonFetcher.BabylonEpochData?> get() = _epochData

    fun epochData(chain: BaseChain) = viewModelScope.launch(Dispatchers.IO) {
        val channel = chain.cosmosFetcher()?.getChannel()
        try {
            val chainHeightDeferred = async { walletRepository.chainHeight(channel, chain) }
            val currentEpochDeferred = async { walletRepository.currentEpoch(channel, chain) }

            val responses = awaitAll(
                chainHeightDeferred, currentEpochDeferred
            )

            var currentHeight: Long? = 0
            var currentEpoch: QueryCurrentEpochResponse?
            val epochMsg: MutableList<QueuedMessageResponse> = mutableListOf()

            responses.forEach { response ->
                when (response) {
                    is NetworkResult.Success -> {
                        when (response.data) {
                            is Long -> {
                                currentHeight = response.data
                            }

                            is QueryCurrentEpochResponse -> {
                                currentEpoch = response.data
                                currentEpoch?.let {
                                    when (val epochMsgResponse = walletRepository.epochMessage(
                                        channel, chain, it.currentEpoch
                                    )) {
                                        is NetworkResult.Success -> {
                                            epochMsg.clear()
                                            epochMsgResponse.data.forEach { msg ->
                                                if (msg.msg.contains(chain.address)) {
                                                    epochMsg.add(msg)
                                                }
                                            }

                                            _epochData.postValue(
                                                BabylonFetcher.BabylonEpochData(
                                                    currentHeight, currentEpoch, epochMsg
                                                )
                                            )
                                        }

                                        is NetworkResult.Error -> {
                                            _errorMessage.postValue("error type : ${epochMsgResponse.errorType}  error message : ${epochMsgResponse.errorMessage}")
                                        }
                                    }

                                } ?: run {
                                    _errorMessage.postValue("error message : Not Implement")
                                }
                            }
                        }
                    }

                    is NetworkResult.Error -> {
                        _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                    }
                }
            }

        } finally {
            channel?.shutdown()
            try {
                if (channel?.awaitTermination(5, TimeUnit.SECONDS) == false) {
                    channel.shutdownNow()
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }


    private val _epochMessageTypeResult =
        MutableLiveData<MutableList<BabylonFetcher.BabylonEpochTxType>>()
    val epochMessageTypeResult: LiveData<MutableList<BabylonFetcher.BabylonEpochTxType>> get() = _epochMessageTypeResult

    fun epochMessageType(chain: BaseChain, epochMsgs: MutableList<QueuedMessageResponse>?) =
        viewModelScope.launch(Dispatchers.IO) {
            val channel = chain.cosmosFetcher()?.getChannel()
            try {
                when (val response = walletRepository.epochMessageType(channel, chain, epochMsgs)) {
                    is NetworkResult.Success -> {
                        _epochMessageTypeResult.postValue(response.data)
                    }

                    is NetworkResult.Error -> {
                        _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                    }
                }

            } finally {
                channel?.shutdown()
                try {
                    if (channel?.awaitTermination(5, TimeUnit.SECONDS) == false) {
                        channel.shutdownNow()
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
}