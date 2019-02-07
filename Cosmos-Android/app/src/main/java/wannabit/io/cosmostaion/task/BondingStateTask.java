package wannabit.io.cosmostaion.task;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdBondings;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class BondingStateTask extends CommonTask {

    private ArrayList<Account> mAccounts;

    public BondingStateTask(BaseApplication app, TaskListener listener, ArrayList<Account> accounts) {
        super(app, listener);
        this.mAccounts          = accounts;
        this.mResult.taskType   = BaseConstant.TASK_FETCH_BONDING_STATE;
    }


    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            for(Account account : mAccounts) {
                Response<ArrayList<ResLcdBondings>> response = ApiClient.getWannabitChain(mApp).getBondingList(account.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null && response.body().size() > 0) {
                        mApp.getBaseDao().onUpdateBondingState(account.id, WUtil.getBondingFromLcd(account.id, response.body()));
                    } else {
                        mApp.getBaseDao().onDeleteBondingStates(account.id);
                    }
                }
            }
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.w("BondingStateTask Error " + e.getMessage());
        }
        return mResult;
    }
}
