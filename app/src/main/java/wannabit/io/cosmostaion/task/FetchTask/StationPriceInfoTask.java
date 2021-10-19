package wannabit.io.cosmostaion.task.FetchTask;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.dao.Price;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_PRICE_INFO;

public class StationPriceInfoTask extends CommonTask {

    public StationPriceInfoTask(BaseApplication app, TaskListener listener) {
        super(app, listener);
        this.mResult.taskType   = TASK_FETCH_PRICE_INFO;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            WLog.w("StationPriceInfoTask " + ApiClient.getStation(mApp).getPrice().request().url());
            Response<ArrayList<Price>> response = ApiClient.getStation(mApp).getPrice().execute();
            if (response.isSuccessful()) {
                for (Price price: response.body()) {
                    mApp.getBaseDao().mPrices.add(price);
                }
            }
//            WLog.w("StationPriceInfoTask " + mApp.getBaseDao().mPrices.size());

        } catch (Exception e) {
            WLog.w("StationPriceInfoTask Error " + e.getMessage());

        }
        return mResult;
    }
}
