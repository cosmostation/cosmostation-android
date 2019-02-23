package wannabit.io.cosmostaion.task;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqTx;
import wannabit.io.cosmostaion.network.res.ResHistory;
import wannabit.io.cosmostaion.utils.WLog;

public class HistoryTask extends CommonTask {

    private ReqTx   mReq;

    public HistoryTask(BaseApplication app, TaskListener listener, ReqTx mReq) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_HISTORY;
        this.mReq = mReq;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ResHistory> response = ApiClient.getEsService(mApp).getTx(mReq).execute();
            if(response.isSuccessful() && response.body() != null) {
                mResult.resultData = response.body().hits.hits;
                mResult.isSuccess = true;
            } else {
                WLog.w("HistoryTask : NOk");
            }

        } catch (Exception e) {
            WLog.w("HistoryTask Error " + e.getMessage());
        }
        return mResult;
    }
}
