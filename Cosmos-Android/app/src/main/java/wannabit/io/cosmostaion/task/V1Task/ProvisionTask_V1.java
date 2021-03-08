package wannabit.io.cosmostaion.task.V1Task;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResParamMint_V1;
import wannabit.io.cosmostaion.network.res.ResProvision_V1;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_V1_FETCH_PROVISION;

public class ProvisionTask_V1 extends CommonTask {
    private Account mAccount;

    public ProvisionTask_V1(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.mAccount           = account;
        this.mResult.taskType   = TASK_V1_FETCH_PROVISION;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (BaseChain.getChain(mAccount.baseChain).equals(COSMOS_MAIN)) {
                Response<ResProvision_V1> response = ApiClient.getCosmosChain(mApp).getProvision().execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.isSuccess = true;
                    mResult.resultData = response.body().getProvision();
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(AKASH_MAIN)) {
                Response<ResProvision_V1> response = ApiClient.getAkashChain(mApp).getProvision().execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.isSuccess = true;
                    mResult.resultData = response.body().getProvision();
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(COSMOS_TEST)) {
                Response<ResProvision_V1> response = ApiClient.getCosmosTestChain(mApp).getProvision().execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.isSuccess = true;
                    mResult.resultData = response.body().getProvision();
                }
            }

        } catch (Exception e) { }
        return mResult;
    }
}