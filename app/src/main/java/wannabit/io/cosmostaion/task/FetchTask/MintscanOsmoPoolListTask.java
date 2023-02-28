package wannabit.io.cosmostaion.task.FetchTask;

import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_NETWORK;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_OSMOSIS_POOL_LIST;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.dao.SupportPool;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class MintscanOsmoPoolListTask extends CommonTask {

    private ChainConfig mChainConfig;

    public MintscanOsmoPoolListTask(BaseApplication app, TaskListener listener, ChainConfig chainConfig) {
        super(app, listener);
        this.mResult.taskType = TASK_FETCH_OSMOSIS_POOL_LIST;
        this.mChainConfig = chainConfig;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChainConfig == null) {
                mResult.isSuccess = false;
                return mResult;
            }

            Response<ArrayList<SupportPool>> response = ApiClient.getChainBase().getSupportPools(mChainConfig.chainName()).execute();
            if (!response.isSuccessful()) {
                mResult.isSuccess = false;
                mResult.errorCode = ERROR_CODE_NETWORK;
                return mResult;
            }

            if (response.body() != null && response.body().size() > 0) {
                mResult.isSuccess = true;
                mResult.resultData = response.body();
            }

        } catch (Exception e) {
            WLog.w("MintscanOsmoPoolListTask Error " + e.getMessage());
        }
        return mResult;
    }
}
