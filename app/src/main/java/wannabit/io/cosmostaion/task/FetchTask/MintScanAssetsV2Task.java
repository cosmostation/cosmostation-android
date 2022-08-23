package wannabit.io.cosmostaion.task.FetchTask;

import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_NETWORK;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResAssetsV2;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class MintScanAssetsV2Task extends CommonTask {

    private BaseChain mChain;

    public MintScanAssetsV2Task(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain           = chain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ResAssetsV2> response = ApiClient.getMintscan(mApp).getAssetsV2(ChainFactory.getChain(mChain).chainName()).execute();
            if(!response.isSuccessful()) {
                mResult.isSuccess = false;
                mResult.errorCode = ERROR_CODE_NETWORK;
                return mResult;
            }

            if (response.body() != null && response.body().assets != null) {
                mApp.getBaseDao().mAssetsV2 = response.body().assets;
            }
        } catch (Exception e) {
            WLog.w("MintScanAssetsV2Task Error " + e.getMessage());
        }
        return mResult;
    }
}
