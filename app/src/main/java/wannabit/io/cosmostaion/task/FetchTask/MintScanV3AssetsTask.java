package wannabit.io.cosmostaion.task.FetchTask;

import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_NETWORK;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResV3Assets;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class MintScanV3AssetsTask extends CommonTask {

    public MintScanV3AssetsTask(BaseApplication app, TaskListener listener) {
        super(app, listener);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ResV3Assets> response = ApiClient.getMintscan(mApp).getV3Assets().execute();
            if (!response.isSuccessful()) {
                mResult.isSuccess = false;
                mResult.errorCode = ERROR_CODE_NETWORK;
                return mResult;
            }

            if (response.body() != null && response.body().assets != null) {
                mApp.getBaseDao().mV3Assets = response.body().assets;
            }
        } catch (Exception e) {
            WLog.w("MintScanAssetsTask Error " + e.getMessage());
        }
        return mResult;
    }
}

