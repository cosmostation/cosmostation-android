package wannabit.io.cosmostaion.task.FetchTask;

import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_NETWORK;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResAssets;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class MintScanAssetsTask extends CommonTask {

    public MintScanAssetsTask(BaseApplication app, TaskListener listener) {
        super(app, listener);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ResAssets> response = ApiClient.getMintscan(mApp).getAssets().execute();
            if(!response.isSuccessful()) {
                mResult.isSuccess = false;
                mResult.errorCode = ERROR_CODE_NETWORK;
                return mResult;
            }

            if (response.body() != null && response.body().assets != null) {
                mApp.getBaseDao().mAssets = response.body().assets;
            }
        } catch (Exception e) {
            WLog.w("MintScanAssetsTask Error " + e.getMessage());
        }
        return mResult;
    }
}
