package wannabit.io.cosmostaion.task.FetchTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_BNB_TICKER;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.BnbTicker;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class BnbTickerTask extends CommonTask {
    private BaseChain mBaseChain;

    public BnbTickerTask(BaseApplication app, TaskListener listener, BaseChain basechain) {
        super(app, listener);
        this.mBaseChain = basechain;
        this.result.taskType = TASK_FETCH_BNB_TICKER;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mBaseChain.equals(BaseChain.BNB_MAIN)) {
                Response<ArrayList<BnbTicker>> response = ApiClient.getBnbChain(context).getTic().execute();
                if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                    result.resultData = response.body();
                }

            }
            result.isSuccess = true;

        } catch (Exception e) {
            WLog.w("BnbTickerTask Error " + e.getMessage());
        }
        return result;
    }
}