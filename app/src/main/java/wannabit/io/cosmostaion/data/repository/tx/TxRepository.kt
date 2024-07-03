package wannabit.io.cosmostaion.data.repository.tx

import com.cosmos.auth.v1beta1.QueryProto.QueryAccountResponse
import com.cosmos.bank.v1beta1.TxProto.MsgSend
import com.cosmos.distribution.v1beta1.DistributionProto.DelegationDelegatorReward
import com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddress
import com.cosmos.gov.v1beta1.TxProto
import com.cosmos.staking.v1beta1.TxProto.MsgBeginRedelegate
import com.cosmos.staking.v1beta1.TxProto.MsgCancelUnbondingDelegation
import com.cosmos.staking.v1beta1.TxProto.MsgDelegate
import com.cosmos.staking.v1beta1.TxProto.MsgUndelegate
import com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxResponse
import com.cosmos.tx.v1beta1.TxProto.Fee
import com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract
import com.ibc.applications.transfer.v1.TxProto.MsgTransfer
import com.kava.cdp.v1beta1.TxProto.MsgCreateCDP
import com.kava.cdp.v1beta1.TxProto.MsgDeposit
import com.kava.cdp.v1beta1.TxProto.MsgDrawDebt
import com.kava.cdp.v1beta1.TxProto.MsgRepayDebt
import com.kava.cdp.v1beta1.TxProto.MsgWithdraw
import com.kava.hard.v1beta1.TxProto.MsgBorrow
import com.kava.hard.v1beta1.TxProto.MsgRepay
import com.kava.incentive.v1beta1.QueryProto
import io.grpc.ManagedChannel
import org.web3j.protocol.Web3j
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
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

    suspend fun auth(managedChannel: ManagedChannel?, address: String?): QueryAccountResponse?

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
        selectedChain: EthereumLine,
        selectedFeeInfo: Int
    ): Pair<String?, String?>

    suspend fun broadcastEvmUnDelegateTx(
        web3j: Web3j, hexValue: String
    ): String?

    suspend fun simulateEvmUnDelegateTx(
        validatorAddress: String?,
        toUnDelegateAmount: String?,
        selectedChain: EthereumLine,
        selectedFeeInfo: Int
    ): Pair<String?, String?>

    suspend fun broadcastEvmReDelegateTx(
        web3j: Web3j, hexValue: String
    ): String?

    suspend fun simulateEvmReDelegateTx(
        fromValidatorAddress: String?,
        toValidatorAddress: String?,
        toReDelegateAmount: String?,
        selectedChain: EthereumLine,
        selectedFeeInfo: Int
    ): Pair<String?, String?>

    suspend fun broadcastEvmCancelUnStakingTx(
        web3j: Web3j, hexValue: String
    ): String?

    suspend fun simulateEvmCancelUnStakingTx(
        validatorAddress: String?,
        unDelegateAmount: String?,
        height: Long,
        selectedChain: EthereumLine,
        selectedFeeInfo: Int
    ): Pair<String?, String?>

    suspend fun broadcastEvmRVoteTx(
        web3j: Web3j, hexValue: String
    ): String?

    suspend fun simulateEvmVoteTx(
        proposalId: Long, proposalOption: Long, selectedChain: EthereumLine, selectedFeeInfo: Int
    ): Pair<String?, String?>

    suspend fun broadcastSendTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgSend: MsgSend?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): BroadcastTxResponse?

    suspend fun simulateSendTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgSend: MsgSend?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any?

    suspend fun broadcastOktTx(
        msgs: MutableList<Msg>, fee: LFee, memo: String, selectedChain: CosmosLine
    ): LegacyRes?

    suspend fun broadcastIbcSendTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgTransfer: MsgTransfer?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): BroadcastTxResponse?

    suspend fun simulateIbcSendTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgTransfer: MsgTransfer?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any?

    suspend fun broadcastWasmTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWasms: MutableList<MsgExecuteContract?>?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): BroadcastTxResponse?

    suspend fun simulateWasmTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWasms: MutableList<MsgExecuteContract?>?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any?

    suspend fun broadcastDelegateTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDelegate: MsgDelegate?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): BroadcastTxResponse?

    suspend fun simulateDelegateTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDelegate: MsgDelegate?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any?

    suspend fun broadcastUnDelegateTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgUnDelegate: MsgUndelegate?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): BroadcastTxResponse?

    suspend fun simulateUnDelegateTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgUnDelegate: MsgUndelegate?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any?

    suspend fun broadcastReDelegateTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgReDelegate: MsgBeginRedelegate?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): BroadcastTxResponse?

    suspend fun simulateReDelegateTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgReDelegate: MsgBeginRedelegate?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any?

    suspend fun broadcastCancelUnbondingTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgCancelUnbondingDelegation: MsgCancelUnbondingDelegation?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): BroadcastTxResponse?

    suspend fun simulateCancelUnbondingTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgCancelUnbondingDelegation: MsgCancelUnbondingDelegation?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any?

    suspend fun broadcastGetRewardsTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        rewards: MutableList<DelegationDelegatorReward?>,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): BroadcastTxResponse?

    suspend fun simulateGetRewardsTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        rewards: MutableList<DelegationDelegatorReward?>,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any?

    suspend fun broadcastCompoundingTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        rewards: MutableList<DelegationDelegatorReward?>,
        stakingDenom: String?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): BroadcastTxResponse?

    suspend fun simulateCompoundingTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        rewards: MutableList<DelegationDelegatorReward?>,
        stakingDenom: String?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any?

    suspend fun broadcastChangeRewardAddressTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgSetWithdrawAddress: MsgSetWithdrawAddress?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): BroadcastTxResponse?

    suspend fun simulateChangeRewardAddressTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgSetWithdrawAddress: MsgSetWithdrawAddress?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any?

    suspend fun broadcastVoteTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgVotes: MutableList<TxProto.MsgVote?>?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): BroadcastTxResponse?

    suspend fun simulateVoteTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgVotes: MutableList<TxProto.MsgVote?>?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): Any?

    suspend fun broadcastClaimIncentiveTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        incentive: QueryProto.QueryRewardsResponse,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): BroadcastTxResponse?

    suspend fun simulateClaimIncentiveTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        incentive: QueryProto.QueryRewardsResponse,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): Any?

    suspend fun broadcastMintCreateTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgCreateCDP: MsgCreateCDP?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): BroadcastTxResponse?

    suspend fun simulateMintCreateTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgCreateCDP: MsgCreateCDP?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): Any?

    suspend fun broadcastMintDepositTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDeposit: MsgDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): BroadcastTxResponse?

    suspend fun simulateMintDepositTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDeposit: MsgDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): Any?

    suspend fun broadcastMintWithdrawTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWithdraw: MsgWithdraw?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): BroadcastTxResponse?

    suspend fun simulateMintWithdrawTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWithdraw: MsgWithdraw?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): Any?

    suspend fun broadcastMintBorrowTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDrawDebt: MsgDrawDebt?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): BroadcastTxResponse?

    suspend fun simulateMintBorrowTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDrawDebt: MsgDrawDebt?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): Any?

    suspend fun broadcastMintRepayTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgRepayDebt: MsgRepayDebt?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): BroadcastTxResponse?

    suspend fun simulateMintRepayTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgRepayDebt: MsgRepayDebt?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): Any?

    suspend fun broadcastLendDepositTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDeposit: com.kava.hard.v1beta1.TxProto.MsgDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): BroadcastTxResponse?

    suspend fun simulateLendDepositTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDeposit: com.kava.hard.v1beta1.TxProto.MsgDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): Any?

    suspend fun broadcastLendWithdrawTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWithdraw: com.kava.hard.v1beta1.TxProto.MsgWithdraw?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): BroadcastTxResponse?

    suspend fun simulateLendWithdrawTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWithdraw: com.kava.hard.v1beta1.TxProto.MsgWithdraw?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): Any?

    suspend fun broadcastLendBorrowTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgBorrow: MsgBorrow?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): BroadcastTxResponse?

    suspend fun simulateLendBorrowTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgBorrow: MsgBorrow?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): Any?

    suspend fun broadcastLendRepayTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgRepay: MsgRepay?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): BroadcastTxResponse?

    suspend fun simulateLendRepayTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgRepay: MsgRepay?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): Any?

    suspend fun broadcastPoolDepositTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDeposit: com.kava.swap.v1beta1.TxProto.MsgDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): BroadcastTxResponse?

    suspend fun simulatePoolDepositTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDeposit: com.kava.swap.v1beta1.TxProto.MsgDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): Any?

    suspend fun broadcastPoolWithdrawTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWithdraw: com.kava.swap.v1beta1.TxProto.MsgWithdraw?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): BroadcastTxResponse?

    suspend fun simulatePoolWithdrawTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWithdraw: com.kava.swap.v1beta1.TxProto.MsgWithdraw?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): Any?

    suspend fun broadcastEarnDepositTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDeposit: com.kava.router.v1beta1.TxProto.MsgDelegateMintDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): BroadcastTxResponse?

    suspend fun simulateEarnDepositTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDeposit: com.kava.router.v1beta1.TxProto.MsgDelegateMintDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): Any?

    suspend fun broadcastEarnWithdrawTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWithdraw: com.kava.router.v1beta1.TxProto.MsgWithdrawBurn?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): BroadcastTxResponse?

    suspend fun simulateEarnWithdrawTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWithdraw: com.kava.router.v1beta1.TxProto.MsgWithdrawBurn?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): Any?
}