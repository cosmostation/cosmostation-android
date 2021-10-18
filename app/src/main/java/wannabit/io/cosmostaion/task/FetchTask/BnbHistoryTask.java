package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Call;
import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqTx;
import wannabit.io.cosmostaion.network.res.ResBnbHistories;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class BnbHistoryTask extends CommonTask {

    private ReqTx   mReq;
    private BaseChain mChain;

    public BnbHistoryTask(BaseApplication app, TaskListener listener, ReqTx mReq, BaseChain chain) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_BNB_HISTORY;
        this.mReq = mReq;
        this.mChain = chain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.BNB_MAIN)) {
                Response<ResBnbHistories> response = null;
                if (strings.length == 3) {
                    response = ApiClient.getBnbChain(mApp).getHistory(strings[0], strings[1], strings[2]).execute();
                } else {
                    response = ApiClient.getBnbChain(mApp).getHistoryAsset(strings[0], strings[1], strings[2], strings[3]).execute();
                }

                if(response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().tx;
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }

            } else if (mChain.equals(BaseChain.BNB_TEST)) {
                Response<ResBnbHistories> response = null;
                if (strings.length == 3) {
                    response = ApiClient.getBnbTestChain(mApp).getHistory(strings[0], strings[1], strings[2]).execute();
                    Call a = ApiClient.getBnbTestChain(mApp).getHistory(strings[0], strings[1], strings[2]);
                    WLog.w("url " + a.request().url().toString());
                } else {
                    response = ApiClient.getBnbTestChain(mApp).getHistoryAsset(strings[0], strings[1], strings[2], strings[3]).execute();
//                    Call a = ApiClient.getBnbTestChain(mApp).getHistoryAsset(strings[0], strings[1], strings[2], strings[3]);
//                    WLog.w("url " + a.request().url().toString());
                }

                if(response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().tx;
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            WLog.w("HistoryTask Error " + e.getMessage());
        }
        return mResult;
    }
}
