package wannabit.io.cosmostaion.task.SingleFetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdBondings;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class SingleBondingStateTask extends CommonTask {

    private Account mAccount;
    private String  mValidatorAddr;

    public SingleBondingStateTask(BaseApplication app, TaskListener listener, Account account, String validatorAddr) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_SINGLE_BONDING;
        this.mAccount = account;
        this.mValidatorAddr = validatorAddr;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ResLcdBondings> response = ApiClient.getCosmosChain(mApp).getBonding(mAccount.address, mValidatorAddr).execute();
            if(response.isSuccessful()) {
                if(response.body() != null)
                    mApp.getBaseDao().onUpdateBondingState(mAccount.id, WUtil.getBondingFromLcd(mAccount.id, response.body()));
                else
                    mApp.getBaseDao().onDeleteBondingStates(mAccount.id);
            }
            mResult.isSuccess = true;


        } catch (Exception e) {
            WLog.w("SingleBondingStateTask Error " + e.getMessage());
        }
        return mResult;
    }
}
