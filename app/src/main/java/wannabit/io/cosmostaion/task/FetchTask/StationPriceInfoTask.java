package wannabit.io.cosmostaion.task.FetchTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_PRICE_INFO;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Price;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class StationPriceInfoTask extends CommonTask {

    private final BaseChain mBaseChain;

    public StationPriceInfoTask(BaseApplication app, TaskListener listener, BaseChain baseChain) {
        super(app, listener);
        this.mBaseChain = baseChain;
        this.result.taskType = TASK_FETCH_PRICE_INFO;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ArrayList<Price>> response;
            if (mBaseChain.isTestNet()) {
                response = ApiClient.getStationTest(context).getPrice().execute();
            } else {
                response = ApiClient.getStation(context).getPrice().execute();
            }
            if (response.isSuccessful() && response.body() != null) {
                result.resultData = response.body();
                result.isSuccess = true;
            }
//            WLog.w("StationPriceInfoTask " + mApp.getBaseDao().mPrices.size());

        } catch (Exception e) {
            WLog.w("StationPriceInfoTask Error " + e.getMessage());

        }
        return result;
    }
}
