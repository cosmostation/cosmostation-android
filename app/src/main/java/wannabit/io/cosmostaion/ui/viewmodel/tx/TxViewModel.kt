package wannabit.io.cosmostaion.ui.viewmodel.tx

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cosmos.base.abci.v1beta1.AbciProto
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.tx.v1beta1.TxProto.Fee
import com.google.gson.JsonObject
import com.google.protobuf.Any
import com.ibc.applications.transfer.v1.TxProto.MsgTransfer
import com.ibc.core.client.v1.ClientProto
import io.grpc.ManagedChannel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import org.web3j.protocol.Web3j
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.SuiFetcher
import wannabit.io.cosmostaion.chain.cosmosClass.ChainArchway
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOsmosis
import wannabit.io.cosmostaion.chain.cosmosClass.ChainStargaze
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin84
import wannabit.io.cosmostaion.common.isHexString
import wannabit.io.cosmostaion.cosmos.BitCoinJS
import wannabit.io.cosmostaion.data.model.req.LFee
import wannabit.io.cosmostaion.data.model.req.Msg
import wannabit.io.cosmostaion.data.model.res.AssetPath
import wannabit.io.cosmostaion.data.model.res.LegacyRes
import wannabit.io.cosmostaion.data.model.res.NameService
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.data.repository.tx.TxRepository
import wannabit.io.cosmostaion.ui.tx.step.SendAssetType
import wannabit.io.cosmostaion.ui.viewmodel.event.SingleLiveEvent

class TxViewModel(private val txRepository: TxRepository) : ViewModel() {

    var nameServices = SingleLiveEvent<MutableList<NameService>>()

    fun icnsAddress(recipientChain: BaseChain, userInput: String, prefix: String) =
        viewModelScope.launch(Dispatchers.IO) {
            val nameServiceList = mutableListOf<NameService>()
            when (recipientChain) {
                is ChainStargaze -> {
                    val osIcnsDeferred = async {
                        txRepository.osIcnsAddress(
                            ChainOsmosis().cosmosFetcher()?.getChannel(), userInput, prefix
                        )
                    }
                    val starIcnsDeferred = async {
                        txRepository.sgIcnsAddress(
                            ChainStargaze().cosmosFetcher()?.getChannel(), userInput
                        )
                    }
                    val responses = awaitAll(osIcnsDeferred, starIcnsDeferred)
                    if (responses[0]?.isNotEmpty() == true) {
                        nameServiceList.add(
                            NameService(
                                NameService.NameServiceType.ICNS, userInput, responses[0].toString()
                            )
                        )
                    }
                    if (responses[1]?.isNotEmpty() == true) {
                        nameServiceList.add(
                            NameService(
                                NameService.NameServiceType.STARGAZE,
                                userInput,
                                responses[1].toString()
                            )
                        )
                    }
                    nameServices.postValue(nameServiceList)
                }

                is ChainArchway -> {
                    val osIcnsDeferred = async {
                        txRepository.osIcnsAddress(
                            ChainOsmosis().cosmosFetcher()?.getChannel(), userInput, prefix
                        )
                    }
                    val archIcnsDeferred = async {
                        txRepository.archIcnsAddress(
                            ChainArchway().cosmosFetcher()?.getChannel(), userInput
                        )
                    }

                    val responses = awaitAll(osIcnsDeferred, archIcnsDeferred)
                    if (responses[0]?.isNotEmpty() == true) {
                        nameServiceList.add(
                            NameService(
                                NameService.NameServiceType.ICNS, userInput, responses[0].toString()
                            )
                        )
                    }
                    if (responses[1]?.isNotEmpty() == true) {
                        nameServiceList.add(
                            NameService(
                                NameService.NameServiceType.ARCHWAY,
                                userInput,
                                responses[1].toString()
                            )
                        )
                    }
                    nameServices.postValue(nameServiceList)
                }

                else -> {
                    val osIcnsDeferred = async {
                        txRepository.osIcnsAddress(
                            ChainOsmosis().cosmosFetcher()?.getChannel(), userInput, prefix
                        )
                    }
                    val response = osIcnsDeferred.await()
                    if (response?.isNotEmpty() == true) {
                        nameServiceList.add(
                            NameService(
                                NameService.NameServiceType.ICNS, userInput, response.toString()
                            )
                        )
                    }
                    nameServices.postValue(nameServiceList)
                }
            }
        }

    val broadcastEvmSendTx = SingleLiveEvent<String?>()
    fun broadcastEvmSend(
        web3j: Web3j, hexValue: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        val response = txRepository.broadcastEvmSendTx(web3j, hexValue)
        broadcastEvmSendTx.postValue(response)
    }

    val simulateEvmSend = SingleLiveEvent<Pair<String?, String?>>()
    val erc20ErrorMessage = SingleLiveEvent<Pair<String?, String?>>()

    fun simulateEvmSend(
        toEthAddress: String?,
        toSendAmount: String?,
        selectedToken: Token?,
        sendAssetType: SendAssetType,
        selectedChain: BaseChain,
        selectedFeeInfo: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        val response = txRepository.simulateEvmSendTx(
            toEthAddress, toSendAmount, selectedToken, sendAssetType, selectedChain, selectedFeeInfo
        )
        if (response.second?.isNotEmpty() == true) {
            simulateEvmSend.postValue(response)
        } else {
            erc20ErrorMessage.postValue(response)
        }
    }

    val broadcastEvmDelegateTx = SingleLiveEvent<String?>()
    fun broadcastEvmDelegate(
        web3j: Web3j, hexValue: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        val response = txRepository.broadcastEvmDelegateTx(web3j, hexValue)
        broadcastEvmDelegateTx.postValue(response)
    }

    val simulateEvmDelegate = SingleLiveEvent<Pair<String?, String?>>()

    fun simulateEvmDelegate(
        toValidatorEthAddress: String?,
        toDelegateAmount: String?,
        selectedChain: BaseChain,
        selectedFeeInfo: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        val response = txRepository.simulateEvmDelegateTx(
            toValidatorEthAddress, toDelegateAmount, selectedChain, selectedFeeInfo
        )
        if (response.second?.isNotEmpty() == true) {
            simulateEvmDelegate.postValue(response)
        } else {
            erc20ErrorMessage.postValue(response)
        }
    }

    val broadcastEvmUnDelegateTx = SingleLiveEvent<String?>()
    fun broadcastEvmUnDelegate(
        web3j: Web3j, hexValue: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        val response = txRepository.broadcastEvmUnDelegateTx(web3j, hexValue)
        broadcastEvmUnDelegateTx.postValue(response)
    }

    val simulateEvmUnDelegate = SingleLiveEvent<Pair<String?, String?>>()

    fun simulateEvmUnDelegate(
        validatorEthAddress: String?,
        toUnDelegateAmount: String?,
        selectedChain: BaseChain,
        selectedFeeInfo: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        val response = txRepository.simulateEvmUnDelegateTx(
            validatorEthAddress, toUnDelegateAmount, selectedChain, selectedFeeInfo
        )
        if (response.second?.isNotEmpty() == true) {
            simulateEvmUnDelegate.postValue(response)
        } else {
            erc20ErrorMessage.postValue(response)
        }
    }

    val broadcastEvmReDelegateTx = SingleLiveEvent<String?>()
    fun broadcastEvmReDelegate(
        web3j: Web3j, hexValue: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        val response = txRepository.broadcastEvmReDelegateTx(web3j, hexValue)
        broadcastEvmReDelegateTx.postValue(response)
    }

    val simulateEvmReDelegate = SingleLiveEvent<Pair<String?, String?>>()

    fun simulateEvmReDelegate(
        fromValidatorEthAddress: String?,
        toValidatorEthAddress: String?,
        toReDelegateAmount: String?,
        selectedChain: BaseChain,
        selectedFeeInfo: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        val response = txRepository.simulateEvmReDelegateTx(
            fromValidatorEthAddress,
            toValidatorEthAddress,
            toReDelegateAmount,
            selectedChain,
            selectedFeeInfo
        )
        if (response.second?.isNotEmpty() == true) {
            simulateEvmReDelegate.postValue(response)
        } else {
            erc20ErrorMessage.postValue(response)
        }
    }

    val broadcastEvmCancelUnStakingTx = SingleLiveEvent<String?>()
    fun broadcastEvmCancelUnStaking(
        web3j: Web3j, hexValue: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        val response = txRepository.broadcastEvmCancelUnStakingTx(web3j, hexValue)
        broadcastEvmCancelUnStakingTx.postValue(response)
    }

    val simulateEvmCancelUnStaking = SingleLiveEvent<Pair<String?, String?>>()

    fun simulateEvmCancelUnStaking(
        validatorEthAddress: String?,
        unDelegateAmount: String?,
        height: Long,
        selectedChain: BaseChain,
        selectedFeeInfo: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        val response = txRepository.simulateEvmCancelUnStakingTx(
            validatorEthAddress, unDelegateAmount, height, selectedChain, selectedFeeInfo
        )
        if (response.second?.isNotEmpty() == true) {
            simulateEvmCancelUnStaking.postValue(response)
        } else {
            erc20ErrorMessage.postValue(response)
        }
    }

    val broadcastEvmVoteTx = SingleLiveEvent<String?>()
    fun broadcastEvmVote(
        web3j: Web3j, hexValue: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        val response = txRepository.broadcastEvmRVoteTx(web3j, hexValue)
        broadcastEvmVoteTx.postValue(response)
    }

    val simulateEvmVote = SingleLiveEvent<Pair<String?, String?>>()

    fun simulateEvmVote(
        proposalId: Long, proposalOption: Long, selectedChain: BaseChain, selectedFeeInfo: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        val response = txRepository.simulateEvmVoteTx(
            proposalId, proposalOption, selectedChain, selectedFeeInfo
        )
        if (response.second?.isNotEmpty() == true) {
            simulateEvmVote.postValue(response)
        } else {
            erc20ErrorMessage.postValue(response)
        }
    }

    val errorMessage = SingleLiveEvent<String?>()

    val broadcastTx = SingleLiveEvent<AbciProto.TxResponse>()

    val broadcast = SingleLiveEvent<AbciProto.TxResponse?>()

    val simulate = SingleLiveEvent<String?>()

    val suiBroadcast = SingleLiveEvent<JsonObject>()

    val bitBroadcast = SingleLiveEvent<String?>()

    fun broadcast(
        managedChannel: ManagedChannel?,
        msgs: MutableList<Any>,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain
    ) = viewModelScope.launch(Dispatchers.IO) {
        try {
            txRepository.auth(managedChannel, selectedChain)
            val response = txRepository.broadcastTx(
                managedChannel, msgs, fee, memo, selectedChain
            )
            broadcast.postValue(response)

        } catch (e: Exception) {
            val errorResponse = txRepository.broadcastTx(
                managedChannel, msgs, fee, memo, selectedChain
            )
            errorMessage.postValue(errorResponse?.rawLog)
        }
    }

    fun simulate(
        managedChannel: ManagedChannel?,
        msgs: MutableList<Any>,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain
    ) = viewModelScope.launch(Dispatchers.IO) {
        try {
            txRepository.auth(managedChannel, selectedChain)
            val response = txRepository.simulateTx(managedChannel, msgs, fee, memo, selectedChain)
            if (response.toDoubleOrNull() != null) {
                simulate.postValue(response)
            } else {
                errorMessage.postValue(response)
            }

        } catch (e: Exception) {
            val errorResponse = txRepository.simulateTx(
                managedChannel, msgs, fee, memo, selectedChain
            )
            errorMessage.postValue(errorResponse)
        }
    }

    fun broadcastIbcSend(
        managedChannel: ManagedChannel?,
        toChain: BaseChain,
        toAddress: String?,
        assetPath: AssetPath?,
        toSendDenom: String?,
        toSendAmount: String?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain
    ) = viewModelScope.launch(Dispatchers.IO) {
        try {
            txRepository.auth(managedChannel, selectedChain)
            val toLastBlock = toChain.cosmosFetcher()?.lastHeight()
            val revisionNumber = selectedChain.cosmosFetcher?.ibcClient(assetPath)

            val sendCoin =
                CoinProto.Coin.newBuilder().setDenom(toSendDenom).setAmount(toSendAmount).build()
            val height = ClientProto.Height.newBuilder().setRevisionNumber(revisionNumber ?: 0)
                .setRevisionHeight((toLastBlock ?: 0) + (toChain.txTimeout() * 10))

            val msgTransfer =
                MsgTransfer.newBuilder().setSender(selectedChain.address).setReceiver(toAddress)
                    .setSourceChannel(assetPath?.channel).setSourcePort(assetPath?.port)
                    .setTimeoutHeight(height).setTimeoutTimestamp(0).setToken(sendCoin).build()

            val response = txRepository.broadcastIbcSendTx(
                managedChannel, msgTransfer, fee, memo, selectedChain
            )
            broadcast.postValue(response)

        } catch (e: Exception) {
            errorMessage.postValue("Unknown Error")
        }
    }

    fun simulateIbcSend(
        managedChannel: ManagedChannel?,
        toChain: BaseChain,
        toAddress: String?,
        assetPath: AssetPath?,
        toSendDenom: String?,
        toSendAmount: String?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain
    ) = viewModelScope.launch(Dispatchers.IO) {
        try {
            txRepository.auth(managedChannel, selectedChain)
            val toLastBlock = toChain.cosmosFetcher()?.lastHeight()
            val revisionNumber = selectedChain.cosmosFetcher?.ibcClient(assetPath)

            val sendCoin =
                CoinProto.Coin.newBuilder().setDenom(toSendDenom).setAmount(toSendAmount).build()
            val height = ClientProto.Height.newBuilder().setRevisionNumber(revisionNumber ?: 0)
                .setRevisionHeight((toLastBlock ?: 0) + (toChain.txTimeout() * 10))

            val msgTransfer =
                MsgTransfer.newBuilder().setSender(selectedChain.address).setReceiver(toAddress)
                    .setSourceChannel(assetPath?.channel).setSourcePort(assetPath?.port)
                    .setTimeoutHeight(height).setTimeoutTimestamp(0).setToken(sendCoin).build()

            val response = txRepository.simulateIbcSendTx(
                managedChannel, msgTransfer, fee, memo, selectedChain
            )
            if (response.toLongOrNull() != null) {
                simulate.postValue(response)
            } else {
                errorMessage.postValue(response)
            }

        } catch (e: Exception) {
            errorMessage.postValue("Unknown Error")
        }
    }

    val broadcastOktTx = SingleLiveEvent<LegacyRes?>()
    fun broadcastOktTx(msgs: MutableList<Msg>, fee: LFee, memo: String, selectedChain: BaseChain) =
        viewModelScope.launch(Dispatchers.IO) {
            val response = txRepository.broadcastOktTx(msgs, fee, memo, selectedChain)
            broadcastOktTx.postValue(response)
        }

    fun suiBroadcast(
        fetcher: SuiFetcher,
        sendDenom: String,
        sender: String,
        coins: MutableList<String>,
        recipient: MutableList<String>,
        amounts: MutableList<String>,
        gasBudget: String,
        selectedChain: BaseChain
    ) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = txRepository.broadcastSuiSend(
                fetcher, sendDenom, sender, coins, recipient, amounts, gasBudget, selectedChain
            )
            if (response["error"] == null) {
                suiBroadcast.postValue(response)
            } else {
                errorMessage.postValue(response["error"].asJsonObject["message"].asString)
            }

        } catch (e: Exception) {
            errorMessage.postValue(e.message.toString())
        }
    }

    fun suiSimulate(
        fetcher: SuiFetcher,
        sendDenom: String,
        sender: String,
        coins: MutableList<String>,
        recipient: MutableList<String>,
        amounts: MutableList<String>,
        gasBudget: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = txRepository.simulateSuiSend(
                fetcher, sendDenom, sender, coins, recipient, amounts, gasBudget
            )

            if (response.toLongOrNull() != null) {
                simulate.postValue(response)
            } else {
                errorMessage.postValue(response)
            }

        } catch (e: Exception) {
            errorMessage.postValue(e.message.toString())
        }
    }

    fun suiNftSendBroadcast(
        fetcher: SuiFetcher,
        sender: String,
        objectId: String,
        recipient: String,
        gasBudget: String,
        selectedChain: BaseChain
    ) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = txRepository.broadcastSuiNftSend(
                fetcher, sender, objectId, recipient, gasBudget, selectedChain
            )
            if (response["error"] == null) {
                suiBroadcast.postValue(response)
            } else {
                errorMessage.postValue(response["error"].asJsonObject["message"].asString)
            }

        } catch (e: Exception) {
            errorMessage.postValue(e.message.toString())
        }
    }

    fun suiNftSendSimulate(
        fetcher: SuiFetcher, sender: String, objectId: String, recipient: String, gasBudget: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = txRepository.simulateSuiNftSend(
                fetcher, sender, objectId, recipient, gasBudget
            )

            if (response.toLongOrNull() != null) {
                simulate.postValue(response)
            } else {
                errorMessage.postValue(response)
            }

        } catch (e: Exception) {
            errorMessage.postValue(e.message.toString())
        }
    }

    fun suiStakeBroadcast(
        fetcher: SuiFetcher,
        sender: String,
        validator: String,
        amount: String,
        gasBudget: String,
        selectedChain: BaseChain
    ) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = txRepository.broadcastSuiStake(
                fetcher, sender, validator, amount, gasBudget, selectedChain
            )
            if (response["error"] == null) {
                suiBroadcast.postValue(response)
            } else {
                errorMessage.postValue(response["error"].asJsonObject["message"].asString)
            }

        } catch (e: Exception) {
            errorMessage.postValue(e.message.toString())
        }
    }

    fun suiStakeSimulate(
        fetcher: SuiFetcher, sender: String, amount: String, validator: String, gasBudget: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = txRepository.simulateSuiStake(
                fetcher, sender, amount, validator, gasBudget
            )

            if (response.toLongOrNull() != null) {
                simulate.postValue(response)
            } else {
                errorMessage.postValue(response)
            }

        } catch (e: Exception) {
            errorMessage.postValue(e.message.toString())
        }
    }

    fun suiUnStakeBroadcast(
        fetcher: SuiFetcher,
        sender: String,
        objectId: String,
        gasBudget: String,
        selectedChain: BaseChain
    ) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = txRepository.broadcastSuiUnStake(
                fetcher, sender, objectId, gasBudget, selectedChain
            )
            if (response["error"] == null) {
                suiBroadcast.postValue(response)
            } else {
                errorMessage.postValue(response["error"].asJsonObject["message"].asString)
            }

        } catch (e: Exception) {
            errorMessage.postValue(e.message.toString())
        }
    }

    fun suiUnStakeSimulate(
        fetcher: SuiFetcher, sender: String, objectId: String, gasBudget: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = txRepository.simulateSuiUnStake(
                fetcher, sender, objectId, gasBudget
            )

            if (response.toLongOrNull() != null) {
                simulate.postValue(response)
            } else {
                errorMessage.postValue(response)
            }

        } catch (e: Exception) {
            errorMessage.postValue(e.message.toString())
        }
    }

    private var _bitTxDataResult = MutableLiveData<Pair<MutableList<JsonObject>?, String>>()
    val bitTxDataResult: LiveData<Pair<MutableList<JsonObject>?, String>> get() = _bitTxDataResult
    fun bitTxData(chain: ChainBitCoin84) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val loadUtxoDeferred = async { txRepository.mempoolUtxo(chain) }
            val utxoResult = loadUtxoDeferred.await()

            if (chain.accountKeyType.pubkeyType == PubKeyType.BTC_NATIVE_SEGWIT) {
                val loadEstimateSmartFeeDeferred = async { txRepository.estimateSmartFee(chain) }
                val estimateSmartFeeResult = loadEstimateSmartFeeDeferred.await()

                if (utxoResult is NetworkResult.Success && estimateSmartFeeResult is NetworkResult.Success) {
                    val utxoData = utxoResult.data
                    val smartFeeData = estimateSmartFeeResult.data
                    _bitTxDataResult.postValue(Pair(utxoData, smartFeeData))

                } else {
                    if (utxoResult is NetworkResult.Error) {
                        errorMessage.postValue("UTXO Error: ${utxoResult.errorMessage}")
                    }
                    if (estimateSmartFeeResult is NetworkResult.Error) {
                        errorMessage.postValue("Smart Fee Error: ${estimateSmartFeeResult.errorMessage}")
                    }
                }

            } else {

            }

        } catch (e: Exception) {
            errorMessage.postValue(e.message.toString())
        }
    }

    fun bitSendBroadcast(
        chain: ChainBitCoin84, txHex: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = txRepository.broadcastBitSend(
                chain, txHex
            )

            if (!response.isNullOrEmpty()) {
                bitBroadcast.postValue(response)
            } else {
                errorMessage.postValue(response)
            }

        } catch (e: Exception) {
            errorMessage.postValue(e.message.toString())
        }
    }

    fun bitSendSimulate(
        chain: ChainBitCoin84,
        bitcoinJS: BitCoinJS?,
        sender: String,
        receiver: String,
        toAmount: String,
        changedValue: String,
        opReturn: String?,
        utxo: MutableList<JsonObject>?,
    ) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = txRepository.simulateBitSend(
                chain, bitcoinJS, sender, receiver, toAmount, changedValue, opReturn, utxo
            )

            if (!response.isNullOrEmpty()) {
                if (isHexString(response)) {
                    simulate.postValue(response)
                } else {
                    val keyword = "Uncaught Error:"
                    val startIndex = response.indexOf(keyword)
                    val endKeyword = "(()=>"
                    val endIndex = response.indexOf(endKeyword)

                    val errorMsg = if (response.contains(keyword)) {
                        response.substring(startIndex, endIndex).trim()
                    } else {
                        "Unknown Error"
                    }
                    errorMessage.postValue(errorMsg)
                }
            } else {
                errorMessage.postValue(response)
            }

        } catch (e: Exception) {
            errorMessage.postValue(e.message.toString())
        }
    }
}