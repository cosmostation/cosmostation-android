package wannabit.io.cosmostaion.task.V1Task;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResRedelegations_V1;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_V1_FETCH_REDELEGATION_FROM_TO;

public class RedelegationFromToTask_V1 extends CommonTask {
    private Account mAccount;
    private String  mFromValOpAddress;
    private String  mToValOpAddress;

    public RedelegationFromToTask_V1(BaseApplication app, TaskListener listener, Account account, String opFromAddress, String opToAddress) {
        super(app, listener);
        this.mAccount               = account;
        this.mFromValOpAddress      = opFromAddress;
        this.mToValOpAddress        = opToAddress;
        this.mResult.taskType       = TASK_V1_FETCH_REDELEGATION_FROM_TO;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (BaseChain.getChain(mAccount.baseChain).equals(COSMOS_MAIN)) {
                Response<ResRedelegations_V1> response = ApiClient.getCosmosChain(mApp).getRedelegationFromTo(mAccount.address, mFromValOpAddress, mToValOpAddress).execute();
                if (response.isSuccessful() && response.body().redelegation_responses != null) {
                    mResult.isSuccess = true;
                    mResult.resultData = response.body().redelegation_responses;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(IRIS_MAIN)) {
                Response<ResRedelegations_V1> response = ApiClient.getIrisChain(mApp).getRedelegationFromTo(mAccount.address, mFromValOpAddress, mToValOpAddress).execute();
                if (response.isSuccessful() && response.body().redelegation_responses != null) {
                    mResult.isSuccess = true;
                    mResult.resultData = response.body().redelegation_responses;
                }
            }

        } catch (Exception e) {
            WLog.w("Exception " + e.getMessage());
        }
        return mResult;
    }
}
