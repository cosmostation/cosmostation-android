package wannabit.io.cosmostaion.data.repository.tx

import com.cosmos.base.abci.v1beta1.AbciProto
import com.cosmos.tx.v1beta1.TxProto.Fee
import com.google.gson.JsonObject
import com.ibc.applications.transfer.v1.TxProto.MsgTransfer
import io.grpc.ManagedChannel
import org.web3j.protocol.Web3j
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.SuiFetcher
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin84
import wannabit.io.cosmostaion.cosmos.BitCoinJS
import wannabit.io.cosmostaion.data.model.req.LFee
import wannabit.io.cosmostaion.data.model.req.Msg
import wannabit.io.cosmostaion.data.model.res.LegacyRes
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.ui.tx.genTx.SendAssetType

interface TxRepository {

    suspend fun osIcnsAddress(
        managedChannel: ManagedChannel?, userInput: String?, prefix: String
    ): String?

    suspend fun sgIcnsAddress(
        managedChannel: ManagedChannel?, userInput: String?
    ): String?

    suspend fun archIcnsAddress(
        managedChannel: ManagedChannel?, userInput: String?
    ): String?

    suspend fun auth(managedChannel: ManagedChannel?, chain: BaseChain)

    suspend fun broadcastEvmSendTx(
        web3j: Web3j, hexValue: String
    ): String?

    suspend fun simulateEvmSendTx(
        toEthAddress: String?,
        toSendAmount: String?,
        selectedToken: Token?,
        sendAssetType: SendAssetType,
        selectedChain: BaseChain,
        selectedFeeInfo: Int
    ): Pair<String?, String?>

    suspend fun broadcastEvmDelegateTx(
        web3j: Web3j, hexValue: String
    ): String?

    suspend fun simulateEvmDelegateTx(
        toValidatorAddress: String?,
        toDelegateAmount: String?,
        selectedChain: BaseChain,
        selectedFeeInfo: Int
    ): Pair<String?, String?>

    suspend fun broadcastEvmUnDelegateTx(
        web3j: Web3j, hexValue: String
    ): String?

    suspend fun simulateEvmUnDelegateTx(
        validatorAddress: String?,
        toUnDelegateAmount: String?,
        selectedChain: BaseChain,
        selectedFeeInfo: Int
    ): Pair<String?, String?>

    suspend fun broadcastEvmReDelegateTx(
        web3j: Web3j, hexValue: String
    ): String?

    suspend fun simulateEvmReDelegateTx(
        fromValidatorAddress: String?,
        toValidatorAddress: String?,
        toReDelegateAmount: String?,
        selectedChain: BaseChain,
        selectedFeeInfo: Int
    ): Pair<String?, String?>

    suspend fun broadcastEvmCancelUnStakingTx(
        web3j: Web3j, hexValue: String
    ): String?

    suspend fun simulateEvmCancelUnStakingTx(
        validatorAddress: String?,
        unDelegateAmount: String?,
        height: Long,
        selectedChain: BaseChain,
        selectedFeeInfo: Int
    ): Pair<String?, String?>

    suspend fun broadcastEvmRVoteTx(
        web3j: Web3j, hexValue: String
    ): String?

    suspend fun simulateEvmVoteTx(
        proposalId: Long, proposalOption: Long, selectedChain: BaseChain, selectedFeeInfo: Int
    ): Pair<String?, String?>

    suspend fun broadcastTx(
        managedChannel: ManagedChannel?,
        msgs: MutableList<com.google.protobuf.Any>,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain
    ): AbciProto.TxResponse?

    suspend fun simulateTx(
        managedChannel: ManagedChannel?,
        msgs: MutableList<com.google.protobuf.Any>,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain
    ): String

    suspend fun broadcastOktTx(
        msgs: MutableList<Msg>, fee: LFee, memo: String, selectedChain: BaseChain
    ): LegacyRes?

    suspend fun broadcastIbcSendTx(
        managedChannel: ManagedChannel?,
        msgTransfer: MsgTransfer?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain
    ): AbciProto.TxResponse?

    suspend fun simulateIbcSendTx(
        managedChannel: ManagedChannel?,
        msgTransfer: MsgTransfer?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain
    ): String

    suspend fun unSafePaySui(
        fetcher: SuiFetcher,
        sender: String,
        coins: MutableList<String>,
        recipient: MutableList<String>,
        amounts: MutableList<String>,
        gasBudget: String
    ): NetworkResult<String>

    suspend fun unSafePay(
        fetcher: SuiFetcher,
        sender: String,
        coins: MutableList<String>,
        recipient: MutableList<String>,
        amounts: MutableList<String>,
        gasBudget: String
    ): NetworkResult<String>

    suspend fun unsafeTransferObject(
        fetcher: SuiFetcher, sender: String, objectId: String, recipient: String, gasBudget: String
    ): NetworkResult<String>

    suspend fun unsafeStake(
        fetcher: SuiFetcher, sender: String, amount: String, validator: String?, gasBudget: String
    ): NetworkResult<String>

    suspend fun unsafeUnStake(
        fetcher: SuiFetcher, sender: String, objectId: String, gasBudget: String
    ): NetworkResult<String>

    suspend fun suiDryRun(fetcher: SuiFetcher, txBytes: String): NetworkResult<JsonObject>

    suspend fun suiExecuteTx(
        fetcher: SuiFetcher, txBytes: String, signatures: MutableList<String>
    ): NetworkResult<JsonObject>

    suspend fun broadcastSuiSend(
        fetcher: SuiFetcher,
        sendDenom: String,
        sender: String,
        coins: MutableList<String>,
        recipient: MutableList<String>,
        amounts: MutableList<String>,
        gasBudget: String,
        selectedChain: BaseChain
    ): JsonObject

    suspend fun simulateSuiSend(
        fetcher: SuiFetcher,
        sendDenom: String,
        sender: String,
        coins: MutableList<String>,
        recipient: MutableList<String>,
        amounts: MutableList<String>,
        gasBudget: String
    ): String

    suspend fun broadcastSuiNftSend(
        fetcher: SuiFetcher,
        sender: String,
        objectId: String,
        recipient: String,
        gasBudget: String,
        selectedChain: BaseChain
    ): JsonObject

    suspend fun simulateSuiNftSend(
        fetcher: SuiFetcher, sender: String, objectId: String, recipient: String, gasBudget: String
    ): String

    suspend fun broadcastSuiStake(
        fetcher: SuiFetcher,
        sender: String,
        validator: String,
        amount: String,
        gasBudget: String,
        selectedChain: BaseChain
    ): JsonObject

    suspend fun simulateSuiStake(
        fetcher: SuiFetcher, sender: String, amount: String, validator: String, gasBudget: String
    ): String

    suspend fun broadcastSuiUnStake(
        fetcher: SuiFetcher,
        sender: String,
        objectId: String,
        gasBudget: String,
        selectedChain: BaseChain
    ): JsonObject

    suspend fun simulateSuiUnStake(
        fetcher: SuiFetcher, sender: String, objectId: String, gasBudget: String
    ): String


    // bit
    suspend fun mempoolUtxo(
        chain: ChainBitCoin84
    ): NetworkResult<MutableList<JsonObject>>

    suspend fun estimateSmartFee(chain: ChainBitCoin84): NetworkResult<String>

    suspend fun broadcastBitSend(chain: ChainBitCoin84, txHex: String): String?

    suspend fun simulateBitSend(
        chain: ChainBitCoin84,
        bitcoinJS: BitCoinJS?,
        sender: String,
        receiver: String,
        toAmount: String,
        changedValue: String,
        opReturn: String?,
        utxo: MutableList<JsonObject>?,
    ): String?
}