package wannabit.io.cosmostaion.task.FetchTask;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdAccountInfo;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class AccountInfoTask extends CommonTask {

    private ArrayList<Account> mAccounts;

    public AccountInfoTask(BaseApplication app, TaskListener listener, ArrayList<Account> accounts) {
        super(app, listener);
        this.mAccounts          = accounts;
        this.mResult.taskType   = BaseConstant.TASK_FETCH_ACCOUNT;
}

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            for(Account account : mAccounts) {
                Response<ResLcdAccountInfo> response = ApiClient.getWannabitChain(mApp, BaseChain.getChain(account.baseChain)).getAccountInfo(account.address).execute();
                if(response == null) {
                    WLog.w("AccountInfoTask null " + account.address);
                }
                if(response.isSuccessful()) {
                    mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromLcd(account.id, response.body()));
                    mApp.getBaseDao().onUpdateBalances(WUtil.getBalancesFromLcd(account.id, response.body()));
                    WLog.w("AccountInfoTask isSuccessful : " + account.address);
                }
            }
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.w("AccountInfoTask Error " + e.getMessage());
            for(Account account : mAccounts) {
                mApp.getBaseDao().onDeleteBalance(""+account.id);
            }

        }
        return mResult;
    }
}
