package wannabit.io.cosmostaion.data.repository.tx

import android.util.Log
import com.cosmos.auth.v1beta1.QueryGrpc
import com.cosmos.auth.v1beta1.QueryProto.QueryAccountResponse
import com.cosmos.bank.v1beta1.TxProto
import com.cosmos.distribution.v1beta1.DistributionProto.DelegationDelegatorReward
import com.cosmos.gov.v1beta1.TxProto.MsgVote
import com.cosmos.tx.v1beta1.ServiceGrpc.newBlockingStub
import com.cosmos.tx.v1beta1.ServiceProto
import com.cosmos.tx.v1beta1.TxProto.Fee
import com.google.gson.Gson
import com.google.protobuf.ByteString
import com.kava.cdp.v1beta1.TxProto.MsgCreateCDP
import com.kava.cdp.v1beta1.TxProto.MsgDeposit
import com.kava.cdp.v1beta1.TxProto.MsgDrawDebt
import com.kava.cdp.v1beta1.TxProto.MsgRepayDebt
import com.kava.cdp.v1beta1.TxProto.MsgWithdraw
import com.kava.incentive.v1beta1.QueryProto
import io.grpc.ManagedChannel
import org.json.JSONObject
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseConstant.ICNS_OSMOSIS_ADDRESS
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.data.model.req.ICNSInfoReq
import java.util.concurrent.TimeUnit

class TxRepositoryImpl : TxRepository {

    private var duration = 8L

    override suspend fun osIcnsAddress(
        managedChannel: ManagedChannel?,
        userInput: String?,
        prefix: String
    ): String {
        try {
            val stub = com.cosmwasm.wasm.v1.QueryGrpc.newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val infoReq = ICNSInfoReq("$userInput.$prefix")
            val queryData = ByteString.copyFromUtf8(Gson().toJson(infoReq))
            val request = com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest.newBuilder().setAddress(ICNS_OSMOSIS_ADDRESS).setQueryData(queryData).build()

            stub.smartContractState(request)?.let {
                val json = JSONObject(it.data.toStringUtf8())
                return json.get("bech32_address").toString()
            }
        } catch (e: Exception) {
            Log.e("error message : ", e.message.toString())
        }
        return ""
    }

    override suspend fun auth(managedChannel: ManagedChannel?, address: String?): QueryAccountResponse {
        val authStub = QueryGrpc.newBlockingStub(managedChannel)
        val request = com.cosmos.auth.v1beta1.QueryProto.QueryAccountRequest.newBuilder().setAddress(address).build()
        return authStub.account(request)
    }

    override suspend fun broadcastSendTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgSend: TxProto.MsgSend?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genSendBroadcast(account, msgSend, fee, memo, selectedChain)
            return txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateSendTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgSend: TxProto.MsgSend?,
        fee: Fee?,
        memo: String
    ): Any? {
        return try {
            val simulStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genSendSimulate(account, msgSend, fee, memo)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastIbcSendTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgTransfer: com.ibc.applications.transfer.v1.TxProto.MsgTransfer?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genIbcSendBroadcast(account, msgTransfer, fee, memo, selectedChain)
            return txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateIbcSendTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgTransfer: com.ibc.applications.transfer.v1.TxProto.MsgTransfer?,
        fee: Fee?,
        memo: String
    ): Any? {
        return try {
            val simulStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genIbcSendSimulate(account, msgTransfer, fee, memo)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastWasmTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWasms: MutableList<com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract?>?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genWasmBroadcast(account, msgWasms, fee, memo, selectedChain)
            return txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateWasmTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWasms: MutableList<com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract?>?,
        fee: Fee?,
        memo: String
    ): Any? {
        return try {
            val simulStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genWasmSimulate(account, msgWasms, fee, memo)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastDelegateTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDelegate: com.cosmos.staking.v1beta1.TxProto.MsgDelegate?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genDelegateBroadcast(account, msgDelegate, fee, memo, selectedChain)
            return txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateDelegateTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDelegate: com.cosmos.staking.v1beta1.TxProto.MsgDelegate?,
        fee: Fee?,
        memo: String
    ): Any? {
        return try {
            val simulStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genDelegateSimulate(account, msgDelegate, fee, memo)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastUnDelegateTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgUnDelegate: com.cosmos.staking.v1beta1.TxProto.MsgUndelegate?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genUnDelegateBroadcast(account, msgUnDelegate, fee, memo, selectedChain)
            return txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateUnDelegateTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgUnDelegate: com.cosmos.staking.v1beta1.TxProto.MsgUndelegate?,
        fee: Fee?,
        memo: String
    ): Any? {
        return try {
            val simulStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genUnDelegateSimulate(account, msgUnDelegate, fee, memo)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastReDelegateTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgReDelegate: com.cosmos.staking.v1beta1.TxProto.MsgBeginRedelegate?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genReDelegateBroadcast(account, msgReDelegate, fee, memo, selectedChain)
            return txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateReDelegateTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgReDelegate: com.cosmos.staking.v1beta1.TxProto.MsgBeginRedelegate?,
        fee: Fee?,
        memo: String
    ): Any? {
        return try {
            val simulStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genReDelegateSimulate(account, msgReDelegate, fee, memo)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastGetRewardsTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        rewards: MutableList<DelegationDelegatorReward?>,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genClaimRewardsBroadcast(account, rewards, fee, memo, selectedChain)
            return txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateGetRewardsTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        rewards: MutableList<DelegationDelegatorReward?>,
        fee: Fee?,
        memo: String
    ): Any? {
        return try {
            val simulStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genClaimRewardsSimulate(account, rewards, fee, memo)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastCompoundingTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        rewards: MutableList<DelegationDelegatorReward?>,
        stakingDenom: String?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genCompoundingBroadcast(account, rewards, stakingDenom, fee, memo, selectedChain)
            return txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateCompoundingTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        rewards: MutableList<DelegationDelegatorReward?>,
        stakingDenom: String?,
        fee: Fee?,
        memo: String
    ): Any? {
        return try {
            val simulStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genCompoundingSimulate(account, rewards, stakingDenom, fee, memo)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastChangeRewardAddressTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgSetWithdrawAddress: com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddress?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genChangeRewardAddressBroadcast(account, msgSetWithdrawAddress, fee, memo, selectedChain)
            return txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateChangeRewardAddressTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgSetWithdrawAddress: com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddress?,
        fee: Fee?,
        memo: String
    ): Any? {
        return try {
            val simulStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genChangeRewardAddressSimulate(account, msgSetWithdrawAddress, fee, memo)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastVoteTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgVotes: MutableList<MsgVote?>?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genVoteBroadcast(account, msgVotes, fee, memo, selectedChain)
            return txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateVoteTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgVotes: MutableList<MsgVote?>?,
        fee: Fee?,
        memo: String
    ): Any? {
        return try {
            val simulStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genVoteSimulate(account, msgVotes, fee, memo)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastClaimIncentiveTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        incentive: QueryProto.QueryRewardsResponse,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genClaimIncentiveBroadcast(account, incentive, fee, memo, selectedChain)
            return txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateClaimIncentiveTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        incentive: QueryProto.QueryRewardsResponse,
        fee: Fee?,
        memo: String
    ): Any? {
        return try {
            val simulStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genClaimIncentiveSimulate(account, incentive, fee, memo)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastMintCreateTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgCreateCDP: MsgCreateCDP?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genMintCreateBroadcast(account, msgCreateCDP, fee, memo, selectedChain)
            return txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateMintCreateTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgCreateCDP: MsgCreateCDP?,
        fee: Fee?,
        memo: String
    ): Any? {
        return try {
            val simulStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genMintCreateSimulate(account, msgCreateCDP, fee, memo)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastMintDepositTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDeposit: MsgDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genMintDepositBroadcast(account, msgDeposit, fee, memo, selectedChain)
            return txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateMintDepositTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDeposit: MsgDeposit?,
        fee: Fee?,
        memo: String
    ): Any? {
        return try {
            val simulStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genMintDepositSimulate(account, msgDeposit, fee, memo)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastMintWithdrawTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWithdraw: MsgWithdraw?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genMintWithdrawBroadcast(account, msgWithdraw, fee, memo, selectedChain)
            return txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateMintWithdrawTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWithdraw: MsgWithdraw?,
        fee: Fee?,
        memo: String
    ): Any? {
        return try {
            val simulStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genMintWithdrawSimulate(account, msgWithdraw, fee, memo)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastMintBorrowTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDrawDebt: MsgDrawDebt?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genMintBorrowBroadcast(account, msgDrawDebt, fee, memo, selectedChain)
            return txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateMintBorrowTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDrawDebt: MsgDrawDebt?,
        fee: Fee?,
        memo: String
    ): Any? {
        return try {
            val simulStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genMintBorrowSimulate(account, msgDrawDebt, fee, memo)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastMintRepayTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgRepayDebt: MsgRepayDebt?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genMintRepayBroadcast(account, msgRepayDebt, fee, memo, selectedChain)
            return txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateMintRepayTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgRepayDebt: MsgRepayDebt?,
        fee: Fee?,
        memo: String
    ): Any? {
        return try {
            val simulStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genMintRepaySimulate(account, msgRepayDebt, fee, memo)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastLendDepositTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDeposit: com.kava.hard.v1beta1.TxProto.MsgDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genLendDepositBroadcast(account, msgDeposit, fee, memo, selectedChain)
            return txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateLendDepositTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDeposit: com.kava.hard.v1beta1.TxProto.MsgDeposit?,
        fee: Fee?,
        memo: String
    ): Any? {
        return try {
            val simulStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genLendDepositSimulate(account, msgDeposit, fee, memo)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastLendWithdrawTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWithdraw: com.kava.hard.v1beta1.TxProto.MsgWithdraw?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genLendWithdrawBroadcast(account, msgWithdraw, fee, memo, selectedChain)
            return txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateLendWithdrawTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWithdraw: com.kava.hard.v1beta1.TxProto.MsgWithdraw?,
        fee: Fee?,
        memo: String
    ): Any? {
        return try {
            val simulStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genLendWithdrawSimulate(account, msgWithdraw, fee, memo)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastLendBorrowTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgBorrow: com.kava.hard.v1beta1.TxProto.MsgBorrow?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genLendBorrowBroadcast(account, msgBorrow, fee, memo, selectedChain)
            return txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateLendBorrowTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgBorrow: com.kava.hard.v1beta1.TxProto.MsgBorrow?,
        fee: Fee?,
        memo: String
    ): Any? {
        return try {
            val simulStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genLendBorrowSimulate(account, msgBorrow, fee, memo)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastLendRepayTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgRepay: com.kava.hard.v1beta1.TxProto.MsgRepay?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genLendRepayBroadcast(account, msgRepay, fee, memo, selectedChain)
            return txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateLendRepayTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgRepay: com.kava.hard.v1beta1.TxProto.MsgRepay?,
        fee: Fee?,
        memo: String
    ): Any? {
        return try {
            val simulStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genLendRepaySimulate(account, msgRepay, fee, memo)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastPoolDepositTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDeposit: com.kava.swap.v1beta1.TxProto.MsgDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genPoolDepositBroadcast(account, msgDeposit, fee, memo, selectedChain)
            return txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulatePoolDepositTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDeposit: com.kava.swap.v1beta1.TxProto.MsgDeposit?,
        fee: Fee?,
        memo: String
    ): Any? {
        return try {
            val simulStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genPoolDepositSimulate(account, msgDeposit, fee, memo)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastPoolWithdrawTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWithdraw: com.kava.swap.v1beta1.TxProto.MsgWithdraw?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genPoolWithdrawBroadcast(account, msgWithdraw, fee, memo, selectedChain)
            return txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulatePoolWithdrawTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWithdraw: com.kava.swap.v1beta1.TxProto.MsgWithdraw?,
        fee: Fee?,
        memo: String
    ): Any? {
        return try {
            val simulStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genPoolWithdrawSimulate(account, msgWithdraw, fee, memo)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }
}