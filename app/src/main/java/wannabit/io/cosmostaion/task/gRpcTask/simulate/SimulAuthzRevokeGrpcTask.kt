package wannabit.io.cosmostaion.task.gRpcTask.simulate

import cosmos.authz.v1beta1.Authz
import cosmos.tx.v1beta1.ServiceGrpc
import wannabit.io.cosmostaion.base.BaseApplication
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.model.type.Fee
import wannabit.io.cosmostaion.network.ChannelBuilder
import wannabit.io.cosmostaion.task.CommonTask
import wannabit.io.cosmostaion.task.TaskListener
import wannabit.io.cosmostaion.task.TaskResult
import wannabit.io.cosmostaion.utils.WKey
import wannabit.io.cosmostaion.utils.WLog

class SimulAuthzRevokeGrpcTask(
    app: BaseApplication?,
    listener: TaskListener?,
    val account: Account?,
    val baseChain: BaseChain?,
    private val grantsList: MutableList<Authz.GrantAuthorization>?,
    val memo: String?,
    val fee: Fee?,
    val chainId: String?,
    val txType: Int
) : CommonTask(app, listener) {

    override fun doInBackground(vararg strings: String?): TaskResult {
        try {
            val txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(baseChain))
            val simulateTxRequest = Signer.getGrpcAuthzRevokeSimulateReq(WKey.onAuthResponse(baseChain, account),
                grantsList as ArrayList<Authz.GrantAuthorization>?, fee, memo, WKey.getECKey(mApp, account), chainId, account!!.customPath, baseChain)
            val response = txService.simulate(simulateTxRequest)
            mResult.resultData = response.gasInfo
            mResult.isSuccess = true

        } catch (e: Exception) {
            WLog.e("SimulAuthzRevokeGrpcTask " + e.message)
            mResult.isSuccess = false
            mResult.errorMsg = e.message
        }
        return mResult
    }
}