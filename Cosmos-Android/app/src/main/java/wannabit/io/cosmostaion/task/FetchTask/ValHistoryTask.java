package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqTxVal;
import wannabit.io.cosmostaion.network.res.ResHistory;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class ValHistoryTask extends CommonTask {

    private ReqTxVal mReq;
    private BaseChain mChain;

    public ValHistoryTask(BaseApplication app, TaskListener listener, ReqTxVal mReq, BaseChain chain) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_VAL_HISTORY;
        this.mReq = mReq;
        this.mChain = chain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.COSMOS_MAIN)) {
                Response<ResHistory> response = ApiClient.getCosmosEs(mApp).getValTx(mReq).execute();
                if(response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().hits.hits;
                    mResult.isSuccess = true;
                } else {
                    WLog.w("ValHistoryTask : NOk");
                }

            } else if (mChain.equals(BaseChain.IRIS_MAIN)) {
                Response<ResHistory> response = ApiClient.getCosmosEs(mApp).getIrisValTx(mReq).execute();
                if(response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().hits.hits;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("HistoryTask : NOk");
                }

            } else if (mChain.equals(BaseChain.KAVA_MAIN)) {
                Response<ResHistory> response = ApiClient.getCosmosEs(mApp).getKavaValTx(mReq).execute();
                if(response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().hits.hits;
                    mResult.isSuccess = true;
                } else {
                    WLog.w("ValHistoryTask : NOk");
                }
            }


        } catch (Exception e) {
            WLog.w("ValHistoryTask Error " + e.getMessage());
        }
        return mResult;
    }
}
