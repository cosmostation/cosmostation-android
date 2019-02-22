package wannabit.io.cosmostaion.task;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.utils.WLog;

public class CheckWithdrawAddressTask extends CommonTask {

    private Account mAccount;

    public CheckWithdrawAddressTask(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_WITHDRAW_ADDRESS;
        this.mAccount = account;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<String> response = ApiClient.getWannabitChain(mApp).getWithdrawAddress(mAccount.address).execute();
            if(!response.isSuccessful()) {
                mResult.isSuccess = false;
                mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                return mResult;
            }

            if(response.body() != null) {
                mResult.resultData = response.body();
                mResult.isSuccess = true;
            }


        } catch (Exception e) {
            WLog.w("AllValidatorInfo Error " + e.getMessage());
        }

        return mResult;
    }
}
