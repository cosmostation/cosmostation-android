package wannabit.io.cosmostaion.task.FetchTask;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResBnbAccountInfo;
import wannabit.io.cosmostaion.network.res.ResOkAccountInfo;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class AccountInfoTask extends CommonTask {

    private final Account mAccount;

    public AccountInfoTask(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.mAccount = account;
        this.result.taskType = BaseConstant.TASK_FETCH_ACCOUNT;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (BaseChain.getChain(mAccount.baseChain).equals(BNB_MAIN)) {
                Response<ResBnbAccountInfo> response = ApiClient.getBnbChain(context).getAccountInfo(mAccount.address).execute();
                if (response.isSuccessful()) {
                    context.getBaseDao().onUpdateAccount(WUtil.getAccountFromBnbLcd(mAccount.id, response.body()));
                    context.getBaseDao().onUpdateBalances(mAccount.id, WUtil.getBalancesFromBnbLcd(mAccount.id, response.body()));
                } else {
                    context.getBaseDao().onDeleteBalance("" + mAccount.id);
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(OKEX_MAIN)) {
                Response<ResOkAccountInfo> response = ApiClient.getOkexChain(context).getAccountInfo(mAccount.address).execute();
                if (response.isSuccessful()) {
                    context.getBaseDao().onUpdateAccount(WUtil.getAccountFromOkLcd(mAccount.id, response.body()));
                    context.getBaseDao().mOkAccountInfo = response.body();
                }

            }
            result.isSuccess = true;

        } catch (Exception e) {
            WLog.w("AccountInfoTask Error " + e.getMessage());
            context.getBaseDao().onDeleteBalance("" + mAccount.id);

        }
        return result;
    }
}
