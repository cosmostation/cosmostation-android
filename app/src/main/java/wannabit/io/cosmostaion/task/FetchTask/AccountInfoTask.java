package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResBnbAccountInfo;
import wannabit.io.cosmostaion.network.res.ResLcdKavaAccountInfo;
import wannabit.io.cosmostaion.network.res.ResOkAccountInfo;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;

public class AccountInfoTask extends CommonTask {

    private Account mAccount;

    public AccountInfoTask(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.mAccount           = account;
        this.mResult.taskType   = BaseConstant.TASK_FETCH_ACCOUNT;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (BaseChain.getChain(mAccount.baseChain).equals(BNB_MAIN)) {
                Response<ResBnbAccountInfo> response = ApiClient.getBnbChain(mApp).getAccountInfo(mAccount.address).execute();
                if(response.isSuccessful()) {
                    mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromBnbLcd(mAccount.id, response.body()));
                    mApp.getBaseDao().onUpdateBalances(mAccount.id, WUtil.getBalancesFromBnbLcd(mAccount.id, response.body()));
                } else {
                    mApp.getBaseDao().onDeleteBalance(""+mAccount.id);
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(OKEX_MAIN)) {
                Response<ResOkAccountInfo> response = ApiClient.getOkexChain(mApp).getAccountInfo(mAccount.address).execute();
                if (response.isSuccessful()) {
                    mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromOkLcd(mAccount.id, response.body()));
                    mApp.getBaseDao().mOkAccountInfo = response.body();
                }

            }

            else if (BaseChain.getChain(mAccount.baseChain).equals(BNB_TEST)) {
                Response<ResBnbAccountInfo> response = ApiClient.getBnbTestChain(mApp).getAccountInfo(mAccount.address).execute();
                if(response.isSuccessful()) {
                    mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromBnbLcd(mAccount.id, response.body()));
                    mApp.getBaseDao().onUpdateBalances(mAccount.id, WUtil.getBalancesFromBnbLcd(mAccount.id, response.body()));
                } else {
                    mApp.getBaseDao().onDeleteBalance(""+mAccount.id);
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(OK_TEST)) {
                Response<ResOkAccountInfo> response = ApiClient.getOkTestChain(mApp).getAccountInfo(mAccount.address).execute();
                if (response.isSuccessful()) {
                    mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromOkLcd(mAccount.id, response.body()));
                    mApp.getBaseDao().mOkAccountInfo = response.body();
                }

            }
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.w("AccountInfoTask Error " + e.getMessage());
            mApp.getBaseDao().onDeleteBalance(""+mAccount.id);

        }
        return mResult;
    }
}
