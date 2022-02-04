package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResOkHistory;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class OkHistoryTask extends CommonTask {
    private String      mAddress;

    public OkHistoryTask(BaseApplication app, TaskListener listener, String address) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_OK_HISTORY;
        this.mAddress = address;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ResOkHistory> response = ApiClient.getOecApi(mApp).getNewOecTxs(mAddress, "20").execute();
            if (response.isSuccessful() && response.body() != null) {
                mResult.resultData = response.body().data.hits;
                mResult.isSuccess = true;
            } else {
                WLog.w("OkHistoryTask : NOk");
            }

        } catch (Exception e) {
            e.printStackTrace();
            WLog.w("OkHistoryTask Error " + e.getMessage());
        }
        return mResult;
    }
}