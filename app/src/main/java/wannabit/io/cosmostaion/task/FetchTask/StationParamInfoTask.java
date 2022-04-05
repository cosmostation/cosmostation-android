package wannabit.io.cosmostaion.task.FetchTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_PARAM_INFO;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.ChainParam;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class StationParamInfoTask extends CommonTask {

    private final BaseChain mBaseChain;
    private final String mChainId;

    public StationParamInfoTask(BaseApplication app, TaskListener listener, BaseChain baseChain, String chainId) {
        super(app, listener);
        this.mBaseChain = baseChain;
        this.mChainId = chainId;
        this.result.taskType = TASK_FETCH_PARAM_INFO;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ChainParam> response;
            if (mBaseChain.isTestNet()) {
                response = ApiClient.getStationTest(context).getParam(mChainId).execute();
            } else {
                response = ApiClient.getStation(context).getParam(mChainId).execute();
            }
            if (!response.isSuccessful()) {
                result.isSuccess = false;
                result.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                return result;
            }

            if (response.isSuccessful() && response.body() != null && response.body().params != null) {
                context.getBaseDao().mChainParam = response.body().params;
            }
        } catch (Exception e) {
            WLog.w("StationParamInfoTask Error " + e.getMessage());
        }
        return result;
    }
}
