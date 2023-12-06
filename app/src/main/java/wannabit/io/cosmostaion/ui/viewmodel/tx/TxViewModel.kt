package wannabit.io.cosmostaion.ui.viewmodel.tx

import SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binance.dex.api.client.Wallet
import com.binance.dex.api.client.domain.TransactionMetadata
import com.binance.dex.api.client.domain.broadcast.HtltReq
import com.binance.dex.api.client.domain.broadcast.TransactionOption
import com.binance.dex.api.client.domain.broadcast.Transfer
import com.cosmos.bank.v1beta1.TxProto.MsgSend
import com.cosmos.base.abci.v1beta1.AbciProto
import com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestBlockRequest
import com.cosmos.base.tendermint.v1beta1.ServiceGrpc
import com.cosmos.base.v1beta1.CoinProto.Coin
import com.cosmos.distribution.v1beta1.DistributionProto.DelegationDelegatorReward
import com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddress
import com.cosmos.gov.v1beta1.TxProto
import com.cosmos.staking.v1beta1.TxProto.MsgBeginRedelegate
import com.cosmos.staking.v1beta1.TxProto.MsgCancelUnbondingDelegation
import com.cosmos.staking.v1beta1.TxProto.MsgDelegate
import com.cosmos.staking.v1beta1.TxProto.MsgUndelegate
import com.cosmos.tx.v1beta1.ServiceProto.SimulateResponse
import com.cosmos.tx.v1beta1.TxProto.Fee
import com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract
import com.ibc.applications.transfer.v1.TxProto.MsgTransfer
import com.ibc.core.channel.v1.QueryGrpc
import com.ibc.core.channel.v1.QueryProto
import com.ibc.core.client.v1.ClientProto
import com.ibc.lightclients.tendermint.v1.TendermintProto
import com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwap
import com.kava.bep3.v1beta1.TxProto.MsgCreateAtomicSwap
import com.kava.cdp.v1beta1.TxProto.MsgCreateCDP
import com.kava.cdp.v1beta1.TxProto.MsgDeposit
import com.kava.cdp.v1beta1.TxProto.MsgDrawDebt
import com.kava.cdp.v1beta1.TxProto.MsgRepayDebt
import com.kava.cdp.v1beta1.TxProto.MsgWithdraw
import com.kava.hard.v1beta1.TxProto.MsgBorrow
import com.kava.hard.v1beta1.TxProto.MsgRepay
import com.kava.incentive.v1beta1.QueryProto.QueryRewardsResponse
import io.grpc.ManagedChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import org.web3j.protocol.Web3j
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt60
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOsmosis
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.data.model.req.LFee
import wannabit.io.cosmostaion.data.model.req.Msg
import wannabit.io.cosmostaion.data.model.res.AssetPath
import wannabit.io.cosmostaion.data.model.res.LegacyRes
import wannabit.io.cosmostaion.data.model.res.NameService
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.data.repository.tx.TxRepository
import java.util.concurrent.TimeUnit

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

    val simulate = SingleLiveEvent<AbciProto.GasInfo>()

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

    private val _broadcastBnbTx = MutableLiveData<MutableList<TransactionMetadata>?>()
    val broadcastBnbTx: LiveData<MutableList<TransactionMetadata>?> get() = _broadcastBnbTx
    fun broadcastBnbSend(transfer: Transfer, wallet: Wallet, options: TransactionOption) = CoroutineScope(Dispatchers.IO).launch {
        val response = txRepository.broadcastBnbSendTx(transfer, wallet, options)
        _broadcastBnbTx.postValue(response)
    }

    fun broadcastIbcSend(
        managedChannel: ManagedChannel?,
        recipientChannel: ManagedChannel?,
        toAddress: String?,
        assetPath: AssetPath?,
        toSendDenom: String?,
        toSendAmount: String?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, selectedChain?.address)?.let {
            val blockStub = ServiceGrpc.newBlockingStub(recipientChannel).withDeadlineAfter(8L, TimeUnit.SECONDS)
            val blockRequest = GetLatestBlockRequest.newBuilder().build()
            val lastBlock = blockStub.getLatestBlock(blockRequest)

            val ibcClientStub = QueryGrpc.newBlockingStub(managedChannel).withDeadlineAfter(8L, TimeUnit.SECONDS)
            val ibcClientRequest = QueryProto.QueryChannelClientStateRequest.newBuilder()
                .setChannelId(assetPath?.channel)
                .setPortId(assetPath?.port)
                .build()
            val ibcClientResponse = ibcClientStub.channelClientState(ibcClientRequest)
            val lastHeight = TendermintProto.ClientState.parseFrom(ibcClientResponse.identifiedClientState.clientState.value).latestHeight
            val height = ClientProto.Height.newBuilder().setRevisionNumber(lastHeight.revisionNumber).setRevisionHeight(lastBlock.block.header.height + 200)

            val sendCoin = Coin.newBuilder().setDenom(toSendDenom).setAmount(toSendAmount).build()

            val msgTransfer = MsgTransfer.newBuilder()
                .setSender(selectedChain?.address)
                .setReceiver(toAddress)
                .setSourceChannel(assetPath?.channel)
                .setSourcePort(assetPath?.port)
                .setTimeoutHeight(height)
                .setTimeoutTimestamp(0)
                .setToken(sendCoin)
                .build()

            val response = txRepository.broadcastIbcSendTx(
                managedChannel,
                it,
                msgTransfer,
                fee,
                memo,
                selectedChain)
            _broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateIbcSend(
        managedChannel: ManagedChannel?,
        recipientChannel: ManagedChannel?,
        fromAddress: String?,
        toAddress: String?,
        assetPath: AssetPath?,
        toSendDenom: String?,
        toSendAmount: String?,
        fee: Fee?,
        memo: String,
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, fromAddress)?.let {
            val blockStub = ServiceGrpc.newBlockingStub(recipientChannel).withDeadlineAfter(8L, TimeUnit.SECONDS)
            val blockRequest = GetLatestBlockRequest.newBuilder().build()
            val lastBlock = blockStub.getLatestBlock(blockRequest)

            val ibcClientStub = QueryGrpc.newBlockingStub(managedChannel).withDeadlineAfter(8L, TimeUnit.SECONDS)
            val ibcClientRequest = QueryProto.QueryChannelClientStateRequest.newBuilder()
                .setChannelId(assetPath?.channel)
                .setPortId(assetPath?.port)
                .build()
            val ibcClientResponse = ibcClientStub.channelClientState(ibcClientRequest)
            val lastHeight = TendermintProto.ClientState.parseFrom(ibcClientResponse.identifiedClientState.clientState.value).latestHeight
            val height = ClientProto.Height.newBuilder().setRevisionNumber(lastHeight.revisionNumber).setRevisionHeight(lastBlock.block.header.height + 200)

            val sendCoin = Coin.newBuilder().setDenom(toSendDenom).setAmount(toSendAmount).build()

            val msgTransfer = MsgTransfer.newBuilder()
                .setSender(fromAddress)
                .setReceiver(toAddress)
                .setSourceChannel(assetPath?.channel)
                .setSourcePort(assetPath?.port)
                .setTimeoutHeight(height)
                .setTimeoutTimestamp(0)
                .setToken(sendCoin)
                .build()

            try {
                val response = txRepository.simulateIbcSendTx(managedChannel, it, msgTransfer, fee, memo) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateIbcSendTx(managedChannel, it, msgTransfer, fee, memo) as String
                errorMessage.postValue(errorResponse)
            }
        }
    }

    private val _broadcastErc20SendTx = MutableLiveData<String?>()
    val broadcastErc20SendTx: LiveData<String?> get() = _broadcastErc20SendTx
    fun broadcastErc20Send(
        web3j: Web3j,
        hexValue: String
    ) = CoroutineScope(Dispatchers.IO).launch {
        val response = txRepository.broadcastErcSendTx(web3j, hexValue)
        _broadcastErc20SendTx.postValue(response)
    }

    val simulateErc20Send = SingleLiveEvent<Pair<String?, String?>>()
    val erc20ErrorMessage = SingleLiveEvent<Pair<String?, String?>>()
    fun simulateErc20Send(
        toEthAddress: String?,
        toSendAmount: String?,
        selectedToken: Token?,
        selectedChain: CosmosLine
    ) = CoroutineScope(Dispatchers.IO).launch {
        val response = txRepository.simulateErcSendTx(toEthAddress, toSendAmount, selectedToken, selectedChain)
        if (response.second?.isNotEmpty() == true) {
            simulateErc20Send.postValue(response)
        } else {
            erc20ErrorMessage.postValue(response)
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

    fun broadCancelUnbonding(
        managedChannel: ManagedChannel?,
        address: String?,
        msgCancelUnbondingDelegation: MsgCancelUnbondingDelegation?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastCancelUnbondingTx(
                managedChannel,
                it,
                msgCancelUnbondingDelegation,
                fee,
                memo,
                selectedChain
            )
            _broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateCancelUnbonding(
        managedChannel: ManagedChannel?,
        address: String?,
        msgCancelUnbondingDelegation: MsgCancelUnbondingDelegation?,
        fee: Fee?,
        memo: String
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateCancelUnbondingTx(managedChannel, it, msgCancelUnbondingDelegation, fee, memo) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateCancelUnbondingTx(managedChannel, it, msgCancelUnbondingDelegation, fee, memo) as String
                errorMessage.postValue(errorResponse)
            }
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
                errorMessage.postValue(errorResponse)
            }
        }
    }

    fun broadVote(
        managedChannel: ManagedChannel?,
        address: String?,
        msgVotes: MutableList<TxProto.MsgVote?>?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastVoteTx(
                managedChannel,
                it,
                msgVotes,
                fee,
                memo,
                selectedChain
            )
            _broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateVote(
        managedChannel: ManagedChannel?,
        address: String?,
        msgVotes: MutableList<TxProto.MsgVote?>?,
        fee: Fee?,
        memo: String
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateVoteTx(managedChannel, it, msgVotes, fee, memo) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateVoteTx(managedChannel, it, msgVotes, fee, memo) as String
                errorMessage.postValue(errorResponse)
            }
        }
    }

    fun broadcastWasm(
        managedChannel: ManagedChannel?,
        address: String?,
        msgWasms: MutableList<MsgExecuteContract?>?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastWasmTx(
                managedChannel,
                it,
                msgWasms,
                fee,
                memo,
                selectedChain)
            _broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateWasm(
        managedChannel: ManagedChannel?,
        address: String?,
        msgWasms: MutableList<MsgExecuteContract?>?,
        fee: Fee?,
        memo: String
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateWasmTx(managedChannel, it, msgWasms, fee, memo) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateWasmTx(managedChannel, it, msgWasms, fee, memo) as String
                errorMessage.postValue(errorResponse)
            }
        }
    }

    fun broadClaimIncentive(
        managedChannel: ManagedChannel?,
        address: String?,
        incentive: QueryRewardsResponse,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastClaimIncentiveTx(
                managedChannel,
                it,
                incentive,
                fee,
                memo,
                selectedChain
            )
            _broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateClaimIncentive(
        managedChannel: ManagedChannel?,
        address: String?,
        incentive: QueryRewardsResponse,
        fee: Fee?,
        memo: String
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateClaimIncentiveTx(managedChannel, it, incentive, fee, memo) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateClaimIncentiveTx(managedChannel, it, incentive, fee, memo) as String
                errorMessage.postValue(errorResponse)
            }
        }
    }

    fun broadMintCreate(
        managedChannel: ManagedChannel?,
        address: String?,
        msgCreateCDP: MsgCreateCDP?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastMintCreateTx(
                managedChannel,
                it,
                msgCreateCDP,
                fee,
                memo,
                selectedChain
            )
            _broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateMintCreate(
        managedChannel: ManagedChannel?,
        address: String?,
        msgCreateCDP: MsgCreateCDP?,
        fee: Fee?,
        memo: String
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateMintCreateTx(managedChannel, it, msgCreateCDP, fee, memo) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateMintCreateTx(managedChannel, it, msgCreateCDP, fee, memo) as String
                errorMessage.postValue(errorResponse)
            }
        }
    }

    fun broadMintDeposit(
        managedChannel: ManagedChannel?,
        address: String?,
        msgDeposit: MsgDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastMintDepositTx(
                managedChannel,
                it,
                msgDeposit,
                fee,
                memo,
                selectedChain
            )
            _broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateMintDeposit(
        managedChannel: ManagedChannel?,
        address: String?,
        msgDeposit: MsgDeposit?,
        fee: Fee?,
        memo: String
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateMintDepositTx(managedChannel, it, msgDeposit, fee, memo) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateMintDepositTx(managedChannel, it, msgDeposit, fee, memo) as String
                errorMessage.postValue(errorResponse)
            }
        }
    }

    fun broadMintWithdraw(
        managedChannel: ManagedChannel?,
        address: String?,
        msgWithdraw: MsgWithdraw?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastMintWithdrawTx(
                managedChannel,
                it,
                msgWithdraw,
                fee,
                memo,
                selectedChain
            )
            _broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateMintWithdraw(
        managedChannel: ManagedChannel?,
        address: String?,
        msgWithdraw: MsgWithdraw?,
        fee: Fee?,
        memo: String
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateMintWithdrawTx(managedChannel, it, msgWithdraw, fee, memo) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateMintWithdrawTx(managedChannel, it, msgWithdraw, fee, memo) as String
                errorMessage.postValue(errorResponse)
            }
        }
    }

    fun broadMintBorrow(
        managedChannel: ManagedChannel?,
        address: String?,
        msgDrawDebt: MsgDrawDebt?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastMintBorrowTx(
                managedChannel,
                it,
                msgDrawDebt,
                fee,
                memo,
                selectedChain
            )
            _broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateMintBorrow(
        managedChannel: ManagedChannel?,
        address: String?,
        msgDrawDebt: MsgDrawDebt?,
        fee: Fee?,
        memo: String
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateMintBorrowTx(managedChannel, it, msgDrawDebt, fee, memo) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateMintBorrowTx(managedChannel, it, msgDrawDebt, fee, memo) as String
                errorMessage.postValue(errorResponse)
            }
        }
    }

    fun broadMintRepay(
        managedChannel: ManagedChannel?,
        address: String?,
        msgRepayDebt: MsgRepayDebt?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastMintRepayTx(
                managedChannel,
                it,
                msgRepayDebt,
                fee,
                memo,
                selectedChain
            )
            _broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateMintRepay(
        managedChannel: ManagedChannel?,
        address: String?,
        msgRepayDebt: MsgRepayDebt?,
        fee: Fee?,
        memo: String
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateMintRepayTx(managedChannel, it, msgRepayDebt, fee, memo) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateMintRepayTx(managedChannel, it, msgRepayDebt, fee, memo) as String
                errorMessage.postValue(errorResponse)
            }
        }
    }

    fun broadLendDeposit(
        managedChannel: ManagedChannel?,
        address: String?,
        msgDeposit: com.kava.hard.v1beta1.TxProto.MsgDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastLendDepositTx(
                managedChannel,
                it,
                msgDeposit,
                fee,
                memo,
                selectedChain
            )
            _broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateLendDeposit(
        managedChannel: ManagedChannel?,
        address: String?,
        msgDeposit: com.kava.hard.v1beta1.TxProto.MsgDeposit?,
        fee: Fee?,
        memo: String
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateLendDepositTx(managedChannel, it, msgDeposit, fee, memo) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateLendDepositTx(managedChannel, it, msgDeposit, fee, memo) as String
                errorMessage.postValue(errorResponse)
            }
        }
    }

    fun broadLendWithdraw(
        managedChannel: ManagedChannel?,
        address: String?,
        msgWithdraw: com.kava.hard.v1beta1.TxProto.MsgWithdraw?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastLendWithdrawTx(
                managedChannel,
                it,
                msgWithdraw,
                fee,
                memo,
                selectedChain
            )
            _broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateLendWithdraw(
        managedChannel: ManagedChannel?,
        address: String?,
        msgWithdraw: com.kava.hard.v1beta1.TxProto.MsgWithdraw?,
        fee: Fee?,
        memo: String
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateLendWithdrawTx(managedChannel, it, msgWithdraw, fee, memo) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateLendWithdrawTx(managedChannel, it, msgWithdraw, fee, memo) as String
                errorMessage.postValue(errorResponse)
            }
        }
    }

    fun broadLendBorrow(
        managedChannel: ManagedChannel?,
        address: String?,
        msgBorrow: MsgBorrow?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastLendBorrowTx(
                managedChannel,
                it,
                msgBorrow,
                fee,
                memo,
                selectedChain
            )
            _broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateLendBorrow(
        managedChannel: ManagedChannel?,
        address: String?,
        msgBorrow: MsgBorrow?,
        fee: Fee?,
        memo: String
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateLendBorrowTx(managedChannel, it, msgBorrow, fee, memo) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateLendBorrowTx(managedChannel, it, msgBorrow, fee, memo) as String
                errorMessage.postValue(errorResponse)
            }
        }
    }

    fun broadLendRepay(
        managedChannel: ManagedChannel?,
        address: String?,
        msgRepay: MsgRepay?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastLendRepayTx(
                managedChannel,
                it,
                msgRepay,
                fee,
                memo,
                selectedChain
            )
            _broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateLendRepay(
        managedChannel: ManagedChannel?,
        address: String?,
        msgRepay: MsgRepay?,
        fee: Fee?,
        memo: String
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateLendRepayTx(managedChannel, it, msgRepay, fee, memo) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateLendRepayTx(managedChannel, it, msgRepay, fee, memo) as String
                errorMessage.postValue(errorResponse)
            }
        }
    }

    fun broadPoolDeposit(
        managedChannel: ManagedChannel?,
        address: String?,
        msgDeposit: com.kava.swap.v1beta1.TxProto.MsgDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastPoolDepositTx(
                managedChannel,
                it,
                msgDeposit,
                fee,
                memo,
                selectedChain
            )
            _broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulatePoolDeposit(
        managedChannel: ManagedChannel?,
        address: String?,
        msgDeposit: com.kava.swap.v1beta1.TxProto.MsgDeposit?,
        fee: Fee?,
        memo: String
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulatePoolDepositTx(managedChannel, it, msgDeposit, fee, memo) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulatePoolDepositTx(managedChannel, it, msgDeposit, fee, memo) as String
                errorMessage.postValue(errorResponse)
            }
        }
    }

    fun broadPoolWithdraw(
        managedChannel: ManagedChannel?,
        address: String?,
        msgWithdraw: com.kava.swap.v1beta1.TxProto.MsgWithdraw?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastPoolWithdrawTx(
                managedChannel,
                it,
                msgWithdraw,
                fee,
                memo,
                selectedChain
            )
            _broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulatePoolWithdraw(
        managedChannel: ManagedChannel?,
        address: String?,
        msgWithdraw: com.kava.swap.v1beta1.TxProto.MsgWithdraw?,
        fee: Fee?,
        memo: String
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulatePoolWithdrawTx(managedChannel, it, msgWithdraw, fee, memo) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulatePoolWithdrawTx(managedChannel, it, msgWithdraw, fee, memo) as String
                errorMessage.postValue(errorResponse)
            }
        }
    }

    private val _broadCreateSwap = MutableLiveData<AbciProto.TxResponse>()
    val broadCreateSwap: LiveData<AbciProto.TxResponse> get() = _broadCreateSwap
    fun broadCreateSwap(
        managedChannel: ManagedChannel?,
        address: String?,
        msgCreateAtomicSwap: MsgCreateAtomicSwap?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastCreateSwapTx(
                managedChannel,
                it,
                msgCreateAtomicSwap,
                fee,
                memo,
                selectedChain
            )
            _broadCreateSwap.postValue(response?.txResponse)
        }
    }

    private val _broadClaimSwap = MutableLiveData<AbciProto.TxResponse>()
    val broadClaimSwap: LiveData<AbciProto.TxResponse> get() = _broadClaimSwap
    fun broadClaimSwap(
        managedChannel: ManagedChannel?,
        address: String?,
        msgClaimAtomicSwap: MsgClaimAtomicSwap?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = CoroutineScope(Dispatchers.IO).launch {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastClaimSwapTx(
                managedChannel,
                it,
                msgClaimAtomicSwap,
                fee,
                memo,
                selectedChain
            )
            _broadClaimSwap.postValue(response?.txResponse)
        }
    }

    private val _broadBnbCreateSwap = MutableLiveData<MutableList<TransactionMetadata>?>()
    val broadBnbCreateSwap: LiveData<MutableList<TransactionMetadata>?> get() = _broadBnbCreateSwap
    fun broadcastBnbCreateSwap(htltReq: HtltReq, wallet: Wallet, options: TransactionOption) = CoroutineScope(Dispatchers.IO).launch {
        val response = txRepository.broadcastBnbCreateSwapTx(htltReq, wallet, options)
        _broadBnbCreateSwap.postValue(response)
    }

    private val _broadcastOktTx = MutableLiveData<LegacyRes?>()
    val broadcastOktTx: LiveData<LegacyRes?> get() = _broadcastOktTx

    fun broadcastOktTx(msgs: MutableList<Msg>, fee: LFee, memo: String, selectedChain: ChainOkt60) = CoroutineScope(Dispatchers.IO).launch {
        val response = txRepository.broadcastOktTx(msgs, fee, memo, selectedChain)
        _broadcastOktTx.postValue(response)
    }
}