package wannabit.io.cosmostaion.task.gRpcTask

import com.google.gson.Gson
import com.google.protobuf.ByteString
import cosmwasm.wasm.v1.QueryGrpc
import cosmwasm.wasm.v1.QueryOuterClass
import org.json.JSONObject
import wannabit.io.cosmostaion.base.BaseApplication
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.network.ChannelBuilder
import wannabit.io.cosmostaion.network.req.neutron.Allocation
import wannabit.io.cosmostaion.network.req.neutron.AllocationReq
import wannabit.io.cosmostaion.network.res.neutron.ResVestingData
import wannabit.io.cosmostaion.network.res.neutron.ResVotingData
import wannabit.io.cosmostaion.task.CommonTask
import wannabit.io.cosmostaion.task.TaskListener
import wannabit.io.cosmostaion.task.TaskResult
import java.util.concurrent.TimeUnit

class VestingGrpcTask(
    app: BaseApplication?,
    listener: TaskListener?,
    val chainConfig: ChainConfig?,
    val account: Account?,
) : CommonTask(app, listener) {

    override fun doInBackground(vararg strings: String?): TaskResult {
        mResult.taskType = BaseConstant.TASK_GRPC_FETCH_NEUTRON_VESTING
        try {
            val req = AllocationReq(Allocation(account!!.address))
            val jsonData = Gson().toJson(req)
            val queryData = ByteString.copyFromUtf8(jsonData)

            val mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(chainConfig?.baseChain())).withDeadlineAfter(ChannelBuilder.TIME_OUT.toLong(), TimeUnit.SECONDS)
            val request = QueryOuterClass.QuerySmartContractStateRequest.newBuilder().setAddress(BaseConstant.NEUTRON_VESTING_CONTRACT_ADDRESS).setQueryData(queryData).build()
            val response: QueryOuterClass.QuerySmartContractStateResponse = mStub.smartContractState(request)

            mResult.isSuccess = true
            mResult.resultData = Gson().fromJson(response.data.toStringUtf8(), ResVestingData::class.java)

        } catch (_: Exception) { }
        return mResult
    }
}