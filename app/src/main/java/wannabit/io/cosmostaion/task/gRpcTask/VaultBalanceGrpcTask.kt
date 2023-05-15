package wannabit.io.cosmostaion.task.gRpcTask

import com.google.gson.Gson
import com.google.protobuf.ByteString
import cosmwasm.wasm.v1.QueryGrpc
import cosmwasm.wasm.v1.QueryOuterClass
import org.json.JSONObject
import wannabit.io.cosmostaion.base.BaseApplication
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.network.ChannelBuilder
import wannabit.io.cosmostaion.network.req.neutron.VotingPower
import wannabit.io.cosmostaion.network.req.neutron.VotingPowerReq
import wannabit.io.cosmostaion.task.CommonTask
import wannabit.io.cosmostaion.task.TaskListener
import wannabit.io.cosmostaion.task.TaskResult
import java.util.concurrent.TimeUnit

class VaultBalanceGrpcTask(
    app: BaseApplication?,
    listener: TaskListener?,
    val baseChain: BaseChain?,
    val account: Account?,
    val contractAddress: String?
) : CommonTask(app, listener) {

    override fun doInBackground(vararg strings: String?): TaskResult {
        mResult.taskType = BaseConstant.TASK_GRPC_FETCH_VAULT_BALANCE
        try {
            val req = VotingPowerReq(VotingPower(account!!.address))
            val jsonData = Gson().toJson(req)
            val queryData = ByteString.copyFromUtf8(jsonData)

            val mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(baseChain)).withDeadlineAfter(ChannelBuilder.TIME_OUT.toLong(), TimeUnit.SECONDS)
            val request = QueryOuterClass.QuerySmartContractStateRequest.newBuilder().setAddress(contractAddress).setQueryData(queryData).build()
            val response: QueryOuterClass.QuerySmartContractStateResponse = mStub.smartContractState(request)
            val json = JSONObject(response.data.toStringUtf8())

            mResult.isSuccess = true
            mResult.resultData = json.getString("power")

        } catch (_: Exception) { }
        return mResult
    }
}