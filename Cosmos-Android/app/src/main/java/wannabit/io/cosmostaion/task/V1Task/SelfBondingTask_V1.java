package wannabit.io.cosmostaion.task.V1Task;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResDelegation_V1;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_V1_FETCH_SELF_BONDING;

public class SelfBondingTask_V1 extends CommonTask {
    private Account mAccount;
    private String  mValOpAddress;
    private String  mAddress;

    public SelfBondingTask_V1(BaseApplication app, TaskListener listener, Account account, String opAddress, String address) {
        super(app, listener);
        this.mAccount           = account;
        this.mValOpAddress      = opAddress;
        this.mAddress           = address;
        this.mResult.taskType   = TASK_V1_FETCH_SELF_BONDING;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (BaseChain.getChain(mAccount.baseChain).equals(COSMOS_MAIN)) {
                Response<ResDelegation_V1> response = ApiClient.getCosmosChain(mApp).getSelfBondInfo(mValOpAddress, mAddress).execute();
                if (response.isSuccessful() && response.body().delegation_response != null) {
                    mResult.isSuccess = true;
                    mResult.resultData = response.body().delegation_response;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(COSMOS_TEST)) {
                Response<ResDelegation_V1> response = ApiClient.getCosmosTestChain(mApp).getSelfBondInfo(mValOpAddress, mAddress).execute();
                if (response.isSuccessful() && response.body().delegation_response != null) {
                    mResult.isSuccess = true;
                    mResult.resultData = response.body().delegation_response;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(IRIS_TEST)) {
                Response<ResDelegation_V1> response = ApiClient.getIrisTestChain(mApp).getSelfBondInfo(mValOpAddress, mAddress).execute();
                if (response.isSuccessful() && response.body().delegation_response != null) {
                    mResult.isSuccess = true;
                    mResult.resultData = response.body().delegation_response;
                }

            }

        } catch (Exception e) {
            WLog.w("Exception " + e.getMessage());
        }
        return mResult;
    }
}