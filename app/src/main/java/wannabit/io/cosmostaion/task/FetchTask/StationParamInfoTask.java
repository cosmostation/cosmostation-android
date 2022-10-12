package wannabit.io.cosmostaion.task.FetchTask;

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

    private BaseChain   mBaseChain;
    private String      mChainId;

    public StationParamInfoTask(BaseApplication app, TaskListener listener, BaseChain baseChain, String chainId) {
        super(app, listener);
        this.mBaseChain         = baseChain;
        this.mChainId           = chainId;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ChainParam> response;
            if (BaseChain.IS_TESTNET(mBaseChain)) {
                response = ApiClient.getStationTest(mApp).getParam(mChainId).execute();
            } else {
                response = ApiClient.getStation(mApp).getParam(mChainId).execute();
            }
            if(!response.isSuccessful()) {
                mResult.isSuccess = false;
                mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                return mResult;
            }

            if (response.isSuccessful() && response.body() != null && response.body().params != null) {
                mApp.getBaseDao().mChainParam = response.body().params;
            }
        } catch (Exception e) {
            WLog.w("StationParamInfoTask Error " + e.getMessage());
        }
        return mResult;
    }
}
