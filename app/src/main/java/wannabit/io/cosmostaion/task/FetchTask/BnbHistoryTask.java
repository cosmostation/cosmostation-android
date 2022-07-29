package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResBnbHistories;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class BnbHistoryTask extends CommonTask {

    public BnbHistoryTask(BaseApplication app, TaskListener listener) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_BNB_HISTORY;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ResBnbHistories> response = null;
            if (strings.length == 3) {
                response = ApiClient.getChainApi(BaseChain.BNB_MAIN).getHistory(strings[0], strings[1], strings[2]).execute();
            } else {
                response = ApiClient.getChainApi(BaseChain.BNB_MAIN).getHistoryAsset(strings[0], strings[1], strings[2], strings[3]).execute();
            }

            if (response.isSuccessful() && response.body() != null) {
                mResult.resultData = response.body().tx;
                mResult.isSuccess = true;
            } else {
                WLog.w("HistoryTask : NOk");
            }


        } catch (Exception e) {
            e.printStackTrace();
            WLog.w("HistoryTask Error " + e.getMessage());
        }
        return mResult;
    }
}
