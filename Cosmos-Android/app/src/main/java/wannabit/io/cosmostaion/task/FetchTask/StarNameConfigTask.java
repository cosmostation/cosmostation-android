package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResIovConfig;
import wannabit.io.cosmostaion.network.res.ResIovFee;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class StarNameConfigTask extends CommonTask {

    private BaseChain mChain;

    public StarNameConfigTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.mResult.taskType   = BaseConstant.TASK_FETCH_STARNAME_CONFIG;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.IOV_MAIN)) {
                Response<ResIovConfig> response = ApiClient.getIovChain(mApp).getConfiguration().execute();
                if (!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if (response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result.configuration;
                    mResult.isSuccess = true;
                }
            } else if (mChain.equals(BaseChain.IOV_TEST)) {
                Response<ResIovConfig> response = ApiClient.getIovTestChain(mApp).getConfiguration().execute();
                if (!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if (response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result.configuration;
                    mResult.isSuccess = true;
                }
            }

        } catch (Exception e) {
            WLog.w("StarNameFeeTask Error " + e.getMessage());
        }

        return mResult;
    }
}
