package wannabit.io.cosmostaion.task.V1Task;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResValidatorInfo_V1;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;

import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_V1_FETCH_VALIDATOR_INFO;

public class ValidatorInfoTask_V1 extends CommonTask {
    private Account mAccount;
    private String  mValOpAddress;

    public ValidatorInfoTask_V1(BaseApplication app, TaskListener listener, Account account, String opAddress) {
        super(app, listener);
        this.mAccount           = account;
        this.mValOpAddress      = opAddress;
        this.mResult.taskType   = TASK_V1_FETCH_VALIDATOR_INFO;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (BaseChain.getChain(mAccount.baseChain).equals(COSMOS_MAIN)) {
                Response<ResValidatorInfo_V1> response = ApiClient.getCosmosChain(mApp).getValidatorInfo(mValOpAddress).execute();
                if (response.isSuccessful() && response.body().validator != null) {
                    mResult.isSuccess = true;
                    mResult.resultData = response.body().validator;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(COSMOS_TEST)) {
                Response<ResValidatorInfo_V1> response = ApiClient.getCosmosTestChain(mApp).getValidatorInfo(mValOpAddress).execute();
                if (response.isSuccessful() && response.body().validator != null) {
                    mResult.isSuccess = true;
                    mResult.resultData = response.body().validator;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(IRIS_MAIN)) {
                Response<ResValidatorInfo_V1> response = ApiClient.getIrisChain(mApp).getValidatorInfo(mValOpAddress).execute();
                if (response.isSuccessful() && response.body().validator != null) {
                    mResult.isSuccess = true;
                    mResult.resultData = response.body().validator;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(IRIS_TEST)) {
                Response<ResValidatorInfo_V1> response = ApiClient.getIrisTestChain(mApp).getValidatorInfo(mValOpAddress).execute();
                if (response.isSuccessful() && response.body().validator != null) {
                    mResult.isSuccess = true;
                    mResult.resultData = response.body().validator;
                }
            }

        } catch (Exception e) { }
        return mResult;
    }
}
