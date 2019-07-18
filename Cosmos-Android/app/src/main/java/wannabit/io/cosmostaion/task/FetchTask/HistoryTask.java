package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqTx;
import wannabit.io.cosmostaion.network.res.ResHistory;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class HistoryTask extends CommonTask {

    private ReqTx   mReq;
    private BaseChain mChain;

    public HistoryTask(BaseApplication app, TaskListener listener, ReqTx mReq, BaseChain chain) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_HISTORY;
        this.mReq = mReq;
        this.mChain = chain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.COSMOS_MAIN)) {
                Response<ResHistory> response = ApiClient.getCosmosEs(mApp).getTx(mReq).execute();
                if(response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().hits.hits;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("HistoryTask : NOk");
                }

            } else if (mChain.equals(BaseChain.IRIS_MAIN)) {
                Response<ResHistory> response = ApiClient.getCosmosEs(mApp).getIrisTx(mReq).execute();
                if(response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().hits.hits;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("HistoryTask : NOk");
                }
            }

        } catch (Exception e) {
            WLog.w("HistoryTask Error " + e.getMessage());
        }
        return mResult;
    }
}
