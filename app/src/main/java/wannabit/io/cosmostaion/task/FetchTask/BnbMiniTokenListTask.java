package wannabit.io.cosmostaion.task.FetchTask;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class BnbMiniTokenListTask extends CommonTask {

    public BnbMiniTokenListTask(BaseApplication app, TaskListener listener) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_BNB_MINI_TOKENS;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ArrayList<BnbToken>> response = ApiClient.getBnbChain().getMiniTokens("3000").execute();
            if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                mResult.resultData = response.body();
            }

            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.w("BnbTokenList Error " + e.getMessage());
        }
        return mResult;
    }
}