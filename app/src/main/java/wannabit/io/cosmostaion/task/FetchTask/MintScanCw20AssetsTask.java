package wannabit.io.cosmostaion.task.FetchTask;

import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_NETWORK;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_MINTSCAN_CW20_ASSETS;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResCw20Assets;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class MintScanCw20AssetsTask extends CommonTask {

    private BaseChain mBaseChain;

    public MintScanCw20AssetsTask(BaseApplication app, TaskListener listener, BaseChain baseChain) {
        super(app, listener);
        this.mResult.taskType = TASK_FETCH_MINTSCAN_CW20_ASSETS;
        this.mBaseChain = baseChain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ResCw20Assets> response = ApiClient.getMintscan(mApp).getCw20Assets(ChainFactory.getChain(mBaseChain).chainName()).execute();
            if(!response.isSuccessful()) {
                mResult.isSuccess = false;
                mResult.errorCode = ERROR_CODE_NETWORK;
                return mResult;
            }

            if (response.body() != null && response.body().assets != null) {
                mResult.isSuccess = true;
                mResult.resultData = response.body().assets;
            }
        } catch (Exception e) {
            WLog.w("MintScanCw20AssetsTask Error " + e.getMessage());
        }
        return mResult;
    }
}
