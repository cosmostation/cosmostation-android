package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResIovToken;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class IovTokenListTask extends CommonTask {

    public IovTokenListTask(BaseApplication app, TaskListener listener) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_IOV_TOKENS;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ResIovToken> response = ApiClient.getIovChain(mApp).getTokens().execute();
            if(response.isSuccessful() && response.body() != null) {
                mResult.resultData = response.body().allTokens;
                mResult.isSuccess = true;

            } else {
                WLog.w("IovTokenListTask : NOk");
            }

        } catch (Exception e) {
            WLog.w("IovTokenListTask Error " + e.getMessage());
        }
        return mResult;
    }
}
