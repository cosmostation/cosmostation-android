package wannabit.io.cosmostaion.task.gRpcTask.simulate

import cosmos.tx.v1beta1.ServiceGrpc
import wannabit.io.cosmostaion.base.BaseApplication
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.model.type.Coin
import wannabit.io.cosmostaion.model.type.Fee
import wannabit.io.cosmostaion.network.ChannelBuilder
import wannabit.io.cosmostaion.network.req.neutron.Bond
import wannabit.io.cosmostaion.network.req.neutron.BondReq
import wannabit.io.cosmostaion.network.req.neutron.Unbond
import wannabit.io.cosmostaion.network.req.neutron.UnbondReq
import wannabit.io.cosmostaion.task.CommonTask
import wannabit.io.cosmostaion.task.TaskListener
import wannabit.io.cosmostaion.task.TaskResult
import wannabit.io.cosmostaion.utils.WKey
import wannabit.io.cosmostaion.utils.WLog

class SimulContractExecuteGrpcTask(
    app: BaseApplication?,
    listener: TaskListener?,
    val account: Account?,
    val baseChain: BaseChain?,
    val fund: Coin?,
    val memo: String?,
    val fee: Fee?,
    val chainId: String?,
    val txType: Int?,
) : CommonTask(app, listener) {

    override fun doInBackground(vararg strings: String?): TaskResult {
        var req: Any? = null
        var contractAddress: String? = null
        try {
            if (txType == BaseConstant.CONST_PW_TX_VAULT_DEPOSIT) {
                req = BondReq(Bond())
                contractAddress = BaseConstant.NEUTRON_NTRN_VAULT_ADDRESS
            } else if (txType == BaseConstant.CONST_PW_TX_VAULT_WITHDRAW) {
                req = UnbondReq(Unbond(fund?.amount))
                contractAddress = BaseConstant.NEUTRON_NTRN_VAULT_ADDRESS
            }

            val txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(baseChain))
            val simulateTxRequest = Signer.getGrpcContractSimulateReq(WKey.onAuthResponse(baseChain, account), req, account?.address, contractAddress, fund, fee, memo, WKey.getECKey(mApp, account), chainId, account!!.customPath, baseChain)
            val response = txService.simulate(simulateTxRequest)
            mResult.resultData = response.gasInfo
            mResult.isSuccess = true

        } catch (e: Exception) {
            WLog.e("SimulContractExecuteGrpcTask " + e.message)
            mResult.isSuccess = false
            mResult.errorMsg = e.message
        }
        return mResult
    }
}