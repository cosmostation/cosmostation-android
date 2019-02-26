package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqTx;
import wannabit.io.cosmostaion.network.req.ReqTxVal;
import wannabit.io.cosmostaion.network.res.ResHistory;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class ValHistoryTask extends CommonTask {

    private ReqTxVal mReq;

    public ValHistoryTask(BaseApplication app, TaskListener listener, ReqTxVal mReq) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_VAL_HISTORY;
        this.mReq = mReq;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ResHistory> response = ApiClient.getEsService(mApp).getValTx(mReq).execute();
            if(response.isSuccessful() && response.body() != null) {
                mResult.resultData = response.body().hits.hits;
                mResult.isSuccess = true;
            } else {
                WLog.w("ValHistoryTask : NOk");
            }
//            ApiClient.getEsService(mApp).getValTx(mReq).enqueue(new Callback<ResHistory>() {
//                @Override
//                public void onResponse(Call<ResHistory> call, Response<ResHistory> response) {
//                    WLog.w("ValHistoryTask : OK");
//                    if(response.isSuccessful() && response.body() != null) {
//                        WLog.w("ValHistoryTask : OK : " + response.body().hits.hits.size());
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResHistory> call, Throwable t) {
//                    WLog.w("ValHistoryTask : onFailure " + t.getMessage());
//                    t.printStackTrace();
//                }
//            });

        } catch (Exception e) {
            WLog.w("ValHistoryTask Error " + e.getMessage());
        }
        return mResult;
    }
}
