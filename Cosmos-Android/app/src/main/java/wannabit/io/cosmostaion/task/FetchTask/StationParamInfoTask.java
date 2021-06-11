package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.ChainParam;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_PARAM_INFO;

public class StationParamInfoTask extends CommonTask {

    private String mChainId;

    public StationParamInfoTask(BaseApplication app, TaskListener listener, String chainId) {
        super(app, listener);
        this.mChainId            = chainId;
        this.mResult.taskType   = TASK_FETCH_PARAM_INFO;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ChainParam> response = ApiClient.getChainParam(mApp).getParam(mChainId).execute();
            if(!response.isSuccessful()) {
                mResult.isSuccess = false;
                mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                return mResult;
            }

            if (response.body() != null && response.body().params != null) {
                WLog.w("OK " + response.body().toString());
                mApp.getBaseDao().mChainParam = response.body().params;
                WLog.w("OK " + mApp.getBaseDao().mChainParam);
                WLog.w("OK " + mApp.getBaseDao().mChainParam.mMintParams);
                WLog.w("OK " + mApp.getBaseDao().mChainParam.mMintParams.mint_denom);
                mResult.isSuccess = true;
            } else {
                WLog.w("ERROR");

            }
        } catch (Exception e) {
            WLog.w("StationParamInfoTask Error " + e.getMessage());
        }
        return mResult;
    }
}
