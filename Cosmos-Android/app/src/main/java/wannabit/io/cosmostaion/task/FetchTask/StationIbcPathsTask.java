package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResIbcPaths;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_NETWORK;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_IBC_PATHS;

public class StationIbcPathsTask extends CommonTask {

    private String mChainId;

    public StationIbcPathsTask(BaseApplication app, TaskListener listener, String chainId) {
        super(app, listener);
        this.mChainId           = chainId;
        this.mResult.taskType   = TASK_FETCH_IBC_PATHS;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            WLog.w("IBC path URL " +  ApiClient.getStation(mApp).getIbcPaths(mChainId).request().url());
            Response<ResIbcPaths> response = ApiClient.getStation(mApp).getIbcPaths(mChainId).execute();
            if(!response.isSuccessful()) {
                mResult.isSuccess = false;
                mResult.errorCode = ERROR_CODE_NETWORK;
                return mResult;
            }

            if (response.body() != null && response.body().sendable != null) {
                mApp.getBaseDao().mIbcPaths = response.body().sendable;
            }
        } catch (Exception e) {
            WLog.w("StationIbcPathsTask Error " + e.getMessage());
        }
        return mResult;
    }
}
