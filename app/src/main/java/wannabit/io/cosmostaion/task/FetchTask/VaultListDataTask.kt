package wannabit.io.cosmostaion.task.FetchTask

import wannabit.io.cosmostaion.base.BaseApplication
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.network.ApiClient
import wannabit.io.cosmostaion.task.CommonTask
import wannabit.io.cosmostaion.task.TaskListener
import wannabit.io.cosmostaion.task.TaskResult
import wannabit.io.cosmostaion.utils.WLog

class VaultListDataTask(
    app: BaseApplication?,
    listener: TaskListener?,
    val chainConfig: ChainConfig?
) : CommonTask(app, listener) {

    override fun doInBackground(vararg strings: String?): TaskResult {
        mResult.taskType = BaseConstant.TASK_GRPC_FETCH_VAULT_LIST
        try {
            val response = ApiClient.getNeutron().getVaultData(chainConfig?.chainName()).execute()
            if (!response.isSuccessful) {
                mResult.isSuccess = false
                mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK
                return mResult
            }
            if (response.body() != null && response.body()!!.isNotEmpty()) {
                mResult.resultData = response.body()
                mResult.isSuccess = true
            }
        } catch (e: Exception) {
            WLog.w("VaultListDataTask Error " + e.message)
        }
        return mResult
    }
}