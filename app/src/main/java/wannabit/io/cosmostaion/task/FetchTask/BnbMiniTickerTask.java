package wannabit.io.cosmostaion.task.FetchTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_BNB_MINI_TICKER;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.dao.BnbTicker;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class BnbMiniTickerTask extends CommonTask {

    public BnbMiniTickerTask(BaseApplication app, TaskListener listener) {
        super(app, listener);
        this.mResult.taskType   = TASK_FETCH_BNB_MINI_TICKER;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ArrayList<BnbTicker>> response = ApiClient.getBnbChain().getMiniTic().execute();
            if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                mResult.resultData = response.body();
            }
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.w("BnbMiniTickerTask Error " + e.getMessage());
        }
        return mResult;
    }
}