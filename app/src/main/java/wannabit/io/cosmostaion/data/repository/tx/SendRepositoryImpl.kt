package wannabit.io.cosmostaion.data.repository.tx

import android.util.Log
import com.cosmos.auth.v1beta1.QueryGrpc
import com.cosmos.auth.v1beta1.QueryProto.QueryAccountResponse
import com.cosmos.bank.v1beta1.TxProto
import com.cosmos.tx.v1beta1.ServiceGrpc.newBlockingStub
import com.cosmos.tx.v1beta1.ServiceProto
import com.cosmos.tx.v1beta1.ServiceProto.SimulateResponse
import com.cosmos.tx.v1beta1.TxProto.Fee
import com.google.gson.Gson
import com.google.protobuf.ByteString
import io.grpc.ManagedChannel
import org.json.JSONObject
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseConstant.ICNS_OSMOSIS_ADDRESS
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.data.model.req.ICNSInfoReq
import java.util.concurrent.TimeUnit

class SendRepositoryImpl : SendRepository {

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
    ): SimulateResponse? {
        return try {
            val simulStub = newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genSendSimulate(account, msgSend, fee, memo)
            simulStub.simulate(simulateTx)

        } catch (_: Exception) {
            null
        }
    }
}