package wannabit.io.cosmostaion.task.FetchTask;

import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_NETWORK;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_MINTSCAN_ASSETS;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResAssets;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class MintScanAssetsTask extends CommonTask {

    private final BaseChain mChain;

    public MintScanAssetsTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.result.taskType = TASK_FETCH_MINTSCAN_ASSETS;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            WLog.w("MinScan Assets URL " + ApiClient.getMintscan(context).getAssets(WDp.getChainNameByBaseChain(mChain)).request().url());
            Response<ResAssets> response = ApiClient.getMintscan(context).getAssets(WDp.getChainNameByBaseChain(mChain)).execute();
            if (!response.isSuccessful()) {
                result.isSuccess = false;
                result.errorCode = ERROR_CODE_NETWORK;
                return result;
            }

            if (response.body() != null && response.body().assets != null) {
                context.getBaseDao().mAssets = response.body().assets;
            }
        } catch (Exception e) {
            WLog.w("MintScanAssetsTask Error " + e.getMessage());
        }
        return result;
    }
}
