package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.Param;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class MintScanUtilityParamTask extends CommonTask {

    private BaseChain mBaseChain;

    public MintScanUtilityParamTask(BaseApplication app, TaskListener listener, BaseChain baseChain) {
        super(app, listener);
        this.mBaseChain = baseChain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            ChainConfig chainConfig = ChainFactory.getChain(mBaseChain);
            Response<Param> response;
            if (chainConfig.baseChain().equals(BaseChain.KI_MAIN)) {
                response = ApiClient.getMintscan(mApp).getParam("ki-chain").execute();
            } else if (chainConfig.baseChain().equals(BaseChain.CRYPTO_MAIN)) {
                response = ApiClient.getMintscan(mApp).getParam("crypto-org").execute();
            } else {
                response = ApiClient.getMintscan(mApp).getParam(ChainFactory.getChain(mBaseChain).chainName()).execute();
            }

            if (response.isSuccessful() && response.body() != null) {
                mApp.getBaseDao().mParam = response.body();
            }

        } catch (Exception e) {
            WLog.w("MintScanUtilityParamTask Error " + e.getMessage());
        }
        return mResult;
    }
}
