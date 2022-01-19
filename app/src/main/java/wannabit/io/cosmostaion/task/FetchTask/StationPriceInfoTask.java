package wannabit.io.cosmostaion.task.FetchTask;

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

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_PRICE_INFO;

public class StationPriceInfoTask extends CommonTask {

    private BaseChain mBaseChain;

    public StationPriceInfoTask(BaseApplication app, TaskListener listener, BaseChain baseChain) {
        super(app, listener);
        this.mBaseChain = baseChain;
        this.mResult.taskType   = TASK_FETCH_PRICE_INFO;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ArrayList<Price>> response;
            if (BaseChain.IS_TESTNET(mBaseChain)) {
                response = ApiClient.getStationTest(mApp).getPrice().execute();
            } else {
                response = ApiClient.getStation(mApp).getPrice().execute();
            }
            if (response.isSuccessful() && response.body() != null) {
                mResult.resultData = response.body();
                mResult.isSuccess = true;
            }
//            WLog.w("StationPriceInfoTask " + mApp.getBaseDao().mPrices.size());

        } catch (Exception e) {
            WLog.w("StationPriceInfoTask Error " + e.getMessage());

        }
        return mResult;
    }
}
