package wannabit.io.cosmostaion.task.SingleFetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdWithDrawAddress;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.getChain;
import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_NETWORK;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_WITHDRAW_ADDRESS;

public class CheckWithdrawAddressTask extends CommonTask {

    private Account mAccount;

    public CheckWithdrawAddressTask(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.mResult.taskType = TASK_FETCH_WITHDRAW_ADDRESS;
        this.mAccount = account;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (getChain(mAccount.baseChain).equals(KAVA_MAIN)) {
                Response<ResLcdWithDrawAddress> response = ApiClient.getKavaChain(mApp).getWithdrawAddress(mAccount.address).execute();
                if(!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = ERROR_CODE_NETWORK;
                    return mResult;
                }
                if(response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            }

        } catch (Exception e) {
            WLog.w("CheckWithdrawAddressTask Error " + e.getMessage());
        }

        return mResult;
    }
}
