package wannabit.io.cosmostaion.task.FetchTask;

import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_NETWORK;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResGasRateParam;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class MintScanGasRateParamTask extends CommonTask {

    public MintScanGasRateParamTask(BaseApplication app, TaskListener listener) {
        super(app, listener);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ArrayList<ResGasRateParam>> response = ApiClient.getMintscan(mApp).getGasRate().execute();
            if (!response.isSuccessful()) {
                mResult.isSuccess = false;
                mResult.errorCode = ERROR_CODE_NETWORK;
                return mResult;
            }

            if (response.body() != null) {
                mApp.getBaseDao().mGasRateParams.clear();
                mApp.getBaseDao().mGasRateParams = response.body();
            }
        } catch (Exception e) {
            WLog.w("MintScanGasRateParamTask Error " + e.getMessage());
        }
        return mResult;
    }
}
