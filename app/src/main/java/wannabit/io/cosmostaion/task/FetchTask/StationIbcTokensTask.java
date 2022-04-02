package wannabit.io.cosmostaion.task.FetchTask;

import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_NETWORK;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_IBC_TOKENS;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResIbcTokens;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class StationIbcTokensTask extends CommonTask {

    private BaseChain mBaseChain;
    private String mChainId;

    public StationIbcTokensTask(BaseApplication app, TaskListener listener, BaseChain baseChain, String chainId) {
        super(app, listener);
        this.mBaseChain = baseChain;
        this.mChainId = chainId;
        this.result.taskType = TASK_FETCH_IBC_TOKENS;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ResIbcTokens> response;
            if (mBaseChain.isTestNet()) {
                response = ApiClient.getStationTest(context).getIbcTokens(mChainId).execute();
            } else {
                response = ApiClient.getStation(context).getIbcTokens(mChainId).execute();
            }
            if (!response.isSuccessful()) {
                result.isSuccess = false;
                result.errorCode = ERROR_CODE_NETWORK;
                return result;
            }

            if (response.body() != null && response.body().ibc_tokens != null) {
                context.getBaseDao().mIbcTokens = response.body().ibc_tokens;
            }
        } catch (Exception e) {
            WLog.w("StationIbcTokensTask Error " + e.getMessage());
        }
        return result;
    }
}