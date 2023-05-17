package wannabit.io.cosmostaion.task.FetchTask;

import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_NETWORK;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_MINTSCAN_ERC20_ASSETS;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResMintscanAssets;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class MintscanErc20AssetsTask extends CommonTask {

    private BaseChain mBaseChain;

    public MintscanErc20AssetsTask(BaseApplication app, TaskListener listener, BaseChain baseChain) {
        super(app, listener);
        this.mResult.taskType = TASK_FETCH_MINTSCAN_ERC20_ASSETS;
        this.mBaseChain = baseChain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            ChainConfig chainConfig = ChainFactory.getChain(mBaseChain);
            if (!chainConfig.erc20CoinSupport()) {
                mResult.isSuccess = false;
                return mResult;
            }
            Response<ResMintscanAssets> response = ApiClient.getMintscan().getErc20Assets(chainConfig.chainName()).execute();
            if (!response.isSuccessful()) {
                mResult.isSuccess = false;
                mResult.errorCode = ERROR_CODE_NETWORK;
                return mResult;
            }

            if (response.body() != null && response.body().assets != null) {
                mResult.isSuccess = true;
                mResult.resultData = response.body().assets;
            }
        } catch (Exception e) {
            WLog.w("MintscanErc20AssetsTask Error " + e.getMessage());
        }
        return mResult;
    }
}
