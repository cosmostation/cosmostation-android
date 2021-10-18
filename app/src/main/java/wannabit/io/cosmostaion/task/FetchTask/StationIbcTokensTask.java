package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResIbcTokens;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_NETWORK;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_IBC_TOKENS;

public class StationIbcTokensTask extends CommonTask {

    private String mChainId;

    public StationIbcTokensTask(BaseApplication app, TaskListener listener, String chainId) {
        super(app, listener);
        this.mChainId           = chainId;
        this.mResult.taskType   = TASK_FETCH_IBC_TOKENS;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            WLog.w("IBC Path URL " +  ApiClient.getStation(mApp).getIbcTokens(mChainId).request().url());
            Response<ResIbcTokens> response = ApiClient.getStation(mApp).getIbcTokens(mChainId).execute();
            if(!response.isSuccessful()) {
                mResult.isSuccess = false;
                mResult.errorCode = ERROR_CODE_NETWORK;
                return mResult;
            }

            if (response.body() != null && response.body().ibc_tokens != null) {
                mApp.getBaseDao().mIbcTokens = response.body().ibc_tokens;
            }
        } catch (Exception e) {
            WLog.w("StationIbcTokensTask Error " + e.getMessage());
        }
        return mResult;
    }
}