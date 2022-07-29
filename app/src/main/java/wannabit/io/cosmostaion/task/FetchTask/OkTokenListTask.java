package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResOkTokenList;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class OkTokenListTask extends CommonTask {

    public OkTokenListTask(BaseApplication app, TaskListener listener) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_OK_TOKEN_LIST;

    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ResOkTokenList> response = ApiClient.getOkexChain().getTokenList().execute();
            if (!response.isSuccessful()) {
                mResult.isSuccess = false;
                mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                return mResult;
            }

            if (response.body() != null) {
                mResult.resultData = response.body();
                mResult.isSuccess = true;
            }

        } catch (Exception e) {
            WLog.w("OkDepositTask Error " + e.getMessage());

        }
        return mResult;
    }

}