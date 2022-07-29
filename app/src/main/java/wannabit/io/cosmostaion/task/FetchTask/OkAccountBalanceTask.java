package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResOkAccountToken;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class OkAccountBalanceTask extends CommonTask {

    private Account mAccount;

    public OkAccountBalanceTask(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.mAccount           = account;
        this.mResult.taskType   = BaseConstant.TASK_FETCH_OK_ACCOUNT_BALANCE;

    }


    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ResOkAccountToken> response = ApiClient.getOkexChain().getAccountBalance(mAccount.address).execute();
            if (!response.isSuccessful()) {
                mResult.isSuccess = false;
                mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                return mResult;
            }

            if (response.body() != null) {
                mResult.isSuccess = true;
                mApp.getBaseDao().onUpdateBalances(mAccount.id, WUtil.getBalancesFromOkLcd(mAccount.id, response.body()));

            } else {
                mApp.getBaseDao().onDeleteBalance(""+mAccount.id);

            }

        } catch (Exception e) {
            WLog.w("OkAccountBalanceTask Error " + e.getMessage());

        }
        return mResult;
    }

}