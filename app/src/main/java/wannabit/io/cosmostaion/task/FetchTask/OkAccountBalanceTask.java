package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
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

    private final BaseChain mChain;
    private final Account mAccount;

    public OkAccountBalanceTask(BaseApplication app, TaskListener listener, Account account, BaseChain chain) {
        super(app, listener);
        this.mAccount = account;
        this.mChain = chain;
        this.result.taskType = BaseConstant.TASK_FETCH_OK_ACCOUNT_BALANCE;

    }


    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.OKEX_MAIN)) {
                Response<ResOkAccountToken> response = ApiClient.getOkexChain(context).getAccountBalance(mAccount.address).execute();
                if (!response.isSuccessful()) {
                    result.isSuccess = false;
                    result.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return result;
                }

                if (response.body() != null) {
                    result.isSuccess = true;
                    context.getBaseDao().onUpdateBalances(mAccount.id, WUtil.getBalancesFromOkLcd(mAccount.id, response.body()));

                } else {
                    context.getBaseDao().onDeleteBalance("" + mAccount.id);

                }

            }
            result.isSuccess = true;

        } catch (Exception e) {
            WLog.w("OkAccountBalanceTask Error " + e.getMessage());

        }
        return result;
    }

}