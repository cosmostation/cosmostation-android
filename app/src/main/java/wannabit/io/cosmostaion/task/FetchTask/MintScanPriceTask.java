package wannabit.io.cosmostaion.task.FetchTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_MINTSCAN_PRICES;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.dao.Price;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class MintScanPriceTask extends CommonTask {

    private String mCurrency;

    public MintScanPriceTask(BaseApplication app, TaskListener listener, String currency) {
        super(app, listener);
        this.mCurrency = currency;
        this.mResult.taskType = TASK_FETCH_MINTSCAN_PRICES;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mApp.getBaseDao().needPriceUpdate()) {
                Response<ArrayList<Price>> response = ApiClient.getMintscan(mApp).getPrice(mCurrency.toLowerCase()).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                }
            }

        } catch (Exception e) {
            WLog.w("MintScanPriceTask Error " + e.getMessage());
        }
        return mResult;
    }
}
