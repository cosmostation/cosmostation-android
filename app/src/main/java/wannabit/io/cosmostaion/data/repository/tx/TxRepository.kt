package wannabit.io.cosmostaion.data.repository.tx

import com.cosmos.base.abci.v1beta1.AbciProto
import com.cosmos.tx.v1beta1.TxProto.Fee
import com.ibc.applications.transfer.v1.TxProto.MsgTransfer
import io.grpc.ManagedChannel
import org.web3j.protocol.Web3j
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.data.model.req.LFee
import wannabit.io.cosmostaion.data.model.req.Msg
import wannabit.io.cosmostaion.data.model.res.LegacyRes
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.ui.tx.step.SendAssetType

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
    ): Any?
}