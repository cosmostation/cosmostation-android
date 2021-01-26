package wannabit.io.cosmostaion.task.V1Task;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResParamMint_V1;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;

import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_V1_FETCH_PARAM_MINT;

public class ParamMintTask_V1 extends CommonTask {
    private Account mAccount;

    public ParamMintTask_V1(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.mAccount           = account;
        this.mResult.taskType   = TASK_V1_FETCH_PARAM_MINT;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (BaseChain.getChain(mAccount.baseChain).equals(COSMOS_TEST)) {
                Response<ResParamMint_V1> response = ApiClient.getCosmosTestChain(mApp).getParamMint().execute();
                if (response.isSuccessful() && response.body().params != null) {
                    mResult.resultData = response.body().params;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(IRIS_TEST)) {
                Response<ResParamMint_V1> response = ApiClient.getIrisTestChain(mApp).getParamMint().execute();
                if (response.isSuccessful() && response.body().params != null) {
                    mResult.resultData = response.body().params;
                }
            }
            mResult.isSuccess = true;

        } catch (Exception e) { }
        return mResult;
    }
}
