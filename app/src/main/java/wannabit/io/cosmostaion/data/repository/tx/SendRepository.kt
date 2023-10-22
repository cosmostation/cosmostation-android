package wannabit.io.cosmostaion.data.repository.tx

import com.cosmos.auth.v1beta1.QueryProto.QueryAccountResponse
import com.cosmos.bank.v1beta1.TxProto.MsgSend
import com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxResponse
import com.cosmos.tx.v1beta1.ServiceProto.SimulateResponse
import com.cosmos.tx.v1beta1.TxProto.Fee
import io.grpc.ManagedChannel
import wannabit.io.cosmostaion.chain.CosmosLine

interface SendRepository {

    suspend fun osIcnsAddress(managedChannel: ManagedChannel?, userInput: String?, prefix: String): String?

    suspend fun auth(managedChannel: ManagedChannel?, address: String?): QueryAccountResponse?

    suspend fun broadcastSendTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgSend: MsgSend?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) : BroadcastTxResponse?

    suspend fun simulateSendTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgSend: MsgSend?,
        fee: Fee?,
        memo: String
    ): SimulateResponse?
}