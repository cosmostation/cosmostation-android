package wannabit.io.cosmostaion.task.FetchTask

import wannabit.io.cosmostaion.base.BaseApplication
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.network.ApiClient
import wannabit.io.cosmostaion.task.CommonTask
import wannabit.io.cosmostaion.task.TaskListener
import wannabit.io.cosmostaion.task.TaskResult
import wannabit.io.cosmostaion.utils.WLog

class DappConfigListTask(
    app: BaseApplication?,
    listener: TaskListener?,
) : CommonTask(app, listener) {

    override fun doInBackground(vararg strings: String?): TaskResult {
        mResult.taskType = BaseConstant.TASK_FETCH_DAPP_CONFIG_LIST
        try {
            val response = ApiClient.getChainBase().supportConfigs.execute()
            if (!response.isSuccessful) {
                mResult.isSuccess = false
                mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK
                return mResult
            }
            if (response.body() != null) {
                mResult.resultData = response.body()
                mResult.isSuccess = true
            }
        } catch (e: Exception) {
            WLog.w("DappConfigListTask Error " + e.message)
        }
        return mResult
    }
}