package wannabit.io.cosmostaion.task.FetchTask;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.IrisToken;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class IrisTokenListTask extends CommonTask {

    public IrisTokenListTask(BaseApplication app, TaskListener listener) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_IRIS_TOKENS;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ArrayList<IrisToken>> response = ApiClient.getIrisChain(mApp).getTokens().execute();
            if(response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                mResult.resultData = response.body();
                mResult.isSuccess = true;

            } else {
                WLog.w("IrisTokenList : NOk");
            }

        } catch (Exception e) {
            WLog.w("IrisTokenList Error " + e.getMessage());
        }
        return mResult;
    }
}
