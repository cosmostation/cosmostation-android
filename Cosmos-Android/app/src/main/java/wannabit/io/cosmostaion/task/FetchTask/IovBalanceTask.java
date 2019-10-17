package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResIovBalance;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class IovBalanceTask extends CommonTask {

    private Account mAccount;

    public IovBalanceTask(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.mAccount           = account;
        this.mResult.taskType   = BaseConstant.TASK_FETCH_IOV_BALANCE;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ResIovBalance> response = ApiClient.getIovChain(mApp).getBalance(mAccount.address).execute();
            if(response.isSuccessful()) {
                mApp.getBaseDao().onUpdateBalances(mAccount.id, WUtil.getIovBalances(mAccount.id, response.body()));

            }
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.w("IovBalanceTask Error " + e.getMessage());
            mApp.getBaseDao().onDeleteBalance(""+mAccount.id);

        }
        return mResult;
    }
}