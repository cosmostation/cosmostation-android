package wannabit.io.cosmostaion.task.V1Task;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResWithdrawAddress_V1;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;

import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_V1_FETCH_WITHDRAW_ADDRESS;

public class WithdrawAddressTask_V1 extends CommonTask {
    private Account mAccount;

    public WithdrawAddressTask_V1(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.mAccount           = account;
        this.mResult.taskType   = TASK_V1_FETCH_WITHDRAW_ADDRESS;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (BaseChain.getChain(mAccount.baseChain).equals(COSMOS_MAIN)) {
                Response<ResWithdrawAddress_V1> response = ApiClient.getCosmosChain(mApp).getRewardAddress(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.isSuccess = true;
                    mResult.resultData = response.body().withdraw_address;
                }
            } else if (BaseChain.getChain(mAccount.baseChain).equals(COSMOS_TEST)) {
                Response<ResWithdrawAddress_V1> response = ApiClient.getCosmosTestChain(mApp).getRewardAddress(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.isSuccess = true;
                    mResult.resultData = response.body().withdraw_address;
                }
            } else if (BaseChain.getChain(mAccount.baseChain).equals(IRIS_MAIN)) {
                Response<ResWithdrawAddress_V1> response = ApiClient.getIrisChain(mApp).getRewardAddress(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.isSuccess = true;
                    mResult.resultData = response.body().withdraw_address;
                }
            } else if (BaseChain.getChain(mAccount.baseChain).equals(IRIS_TEST)) {
                Response<ResWithdrawAddress_V1> response = ApiClient.getIrisTestChain(mApp).getRewardAddress(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.isSuccess = true;
                    mResult.resultData = response.body().withdraw_address;
                }
            }

        } catch (Exception e) { }
        return mResult;
    }
}