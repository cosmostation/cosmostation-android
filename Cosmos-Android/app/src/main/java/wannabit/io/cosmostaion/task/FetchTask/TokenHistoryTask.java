package wannabit.io.cosmostaion.task.FetchTask;

import com.google.gson.JsonObject;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqTxToken;
import wannabit.io.cosmostaion.network.req.ReqTxVal;
import wannabit.io.cosmostaion.network.res.ResHistory;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class TokenHistoryTask extends CommonTask {
    private ReqTxToken mReq;
    private BaseChain mChain;

    public TokenHistoryTask(BaseApplication app, TaskListener listener, ReqTxToken mReq, BaseChain chain) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_TOKEN_HISTORY;
        this.mReq = mReq;
        this.mChain = chain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.COSMOS_MAIN)) {
                Response<ResHistory> response = ApiClient.getCosmosEs(mApp).getTokenTx(mReq).execute();
                if(response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().hits.hits;
                    mResult.isSuccess = true;
                } else {
                    WLog.w("ValHistoryTask : NOk");
                }

            } else if (mChain.equals(BaseChain.IRIS_MAIN)) {
                Response<ResHistory> response = ApiClient.getCosmosEs(mApp).getIrisTokenTx(mReq).execute();
                if(response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().hits.hits;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("HistoryTask : NOk");
                }
            }


        } catch (Exception e) {
            WLog.w("ValHistoryTask Error " + e.getMessage());
        }
        return mResult;
    }

}
